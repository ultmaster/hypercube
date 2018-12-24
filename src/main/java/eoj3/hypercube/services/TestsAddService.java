package eoj3.hypercube.services;

import eoj3.hypercube.models.Configuration;
import eoj3.hypercube.models.Test;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.EdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Service
public class TestsAddService {
    private ConfigurationService configurationService;

    @Autowired
    public TestsAddService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    static public class FormWrapper {
        private MultipartFile[] files;

        public FormWrapper() {
        }

        public MultipartFile[] getFiles() {
            return files;
        }

        public void setFiles(MultipartFile[] files) {
            this.files = files;
        }
    }

    static public class InputOutputPair implements Comparable<InputOutputPair> {
        private final String inputName;
        private final String outputName;
        private final List orderSeq;

        private boolean sameType(char x, char y) {
            return Character.isDigit(x) == Character.isDigit(y);
        }

        InputOutputPair(String inputName, String outputName) {
            this.inputName = inputName;
            this.outputName = outputName;
            orderSeq = new ArrayList<>();
            for (int i = 0, j; i < inputName.length(); ) {
                for (j = i; j < inputName.length() && sameType(inputName.charAt(i), inputName.charAt(j)); ++j) ;
                String token = inputName.substring(i, j);
                try {
                    if (Character.isDigit(inputName.charAt(i))) {
                        orderSeq.add(Long.parseLong(token));
                    } else {
                        orderSeq.add(token);
                    }
                } catch (NumberFormatException e) {
                    orderSeq.add(token);
                }
                i = j;
            }
        }

        String getInputName() {
            return inputName;
        }

        String getOutputName() {
            return outputName;
        }

        List getOrderSeq() {
            return orderSeq;
        }

        @Override
        public int compareTo(InputOutputPair o) {
            List seq = this.getOrderSeq(), thatSeq = o.getOrderSeq();
            for (int i = 0; i < seq.size() || i < thatSeq.size(); ++i) {
                if (i >= seq.size()) return -1;
                if (i >= thatSeq.size()) return 1;
                Object obj = seq.get(i), thatObj = thatSeq.get(i);
                int cp;
                if (obj instanceof Long) {
                    if (thatObj instanceof Long)
                        cp = ((Long) obj).compareTo((Long) thatObj);
                    else cp = -1;
                } else {
                    if (thatObj instanceof Long) cp = 1;
                    else cp = ((String) obj).compareTo((String) thatObj);
                }
                if (cp != 0) return cp;
            }
            return 0;
        }

        @Override
        public String toString() {
            return this.getInputName() + " -> " + this.getOutputName();
        }
    }

    public void processForm(FormWrapper formWrapper) {
        for (MultipartFile file : formWrapper.getFiles()) {
            if (file.isEmpty()) continue;

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(configurationService.getTempFileName("zip"));
                Files.write(path, bytes);

                ZipFile zipFile = new ZipFile(path.toFile());
                List<InputOutputPair> found = sortedEntries(zipFile);
                List<String> names = getNextAvailableFileName(found.size());
                Iterator<String> iter = names.iterator();
                List<Test> tests = configurationService.getProblem().getTests();
                String parentDirectory = configurationService.getTestsDirectory();

                Map<String, String> unzipDestination = new HashMap<>();
                for (InputOutputPair inputOutputPair : found) {
                    String name = iter.next();
                    unzip(zipFile, inputOutputPair.getInputName(), Paths.get(parentDirectory, name).toString());
                    unzip(zipFile, inputOutputPair.getOutputName(), Paths.get(parentDirectory, name + ".a").toString());
                    Test test = new Test();
                    test.setFileName(name);
                    test.setDescription(inputOutputPair.toString());
                    tests.add(test);
                }

                configurationService.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void unzip(ZipFile file, String src, String dest) throws IOException {
        InputStream is = file.getInputStream(file.getEntry(src));
        OutputStream os = new FileOutputStream(dest);
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0)
            os.write(buffer, 0, len);
        os.close();
        is.close();
    }

    public List<InputOutputPair> sortedEntries(ZipFile zipFile) {
        final Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
        SortedSet<String> entries = new TreeSet<>();
        while (enumeration.hasMoreElements())
            entries.add(enumeration.nextElement().getName());

        Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        entries.forEach(graph::addVertex);
        for (String entry : entries) {
            for (String match : this.possibleMatchingName(entry))
                if (entries.contains(match))
                    graph.addEdge(entry, match);
        }

        MatchingAlgorithm matchingAlgorithm = new EdmondsMaximumCardinalityMatching<>(graph);
        MatchingAlgorithm.Matching match = matchingAlgorithm.getMatching();
        List<InputOutputPair> ans = new ArrayList<>();

        for (Object object : match.getEdges()) {
            DefaultEdge edge = (DefaultEdge) object;
            ans.add(new InputOutputPair(graph.getEdgeSource(edge), graph.getEdgeTarget(edge)));
        }

        Collections.sort(ans);

        return ans;
    }

    private Set<String> possibleMatchingName(String inputName) {
        Map<String, String[]> patterns = new HashMap<>();
        patterns.put("(.*)\\.in$", new String[]{"$1.out", "$1.ans"});
        patterns.put("(.*)\\.IN$", new String[]{"$1.OUT", "$1.ANS"});
        patterns.put("input(.*)", new String[]{"output$1", "answer$1"});
        patterns.put("INPUT(.*)", new String[]{"OUTPUT$1", "ANSWER$1"});
        patterns.put("(\\d+)", new String[]{"$1.a"});
        patterns.put("(.*)\\.in(.+)$", new String[]{"$1.out$2", "$1.ans$2"});
        Set<String> res = new HashSet<>();
        patterns.forEach((pattern, values) -> {
            if (inputName.matches(pattern))
                for (String value : values)
                    res.add(inputName.replaceAll(pattern, value));
        });
        return res;
    }

    private List<String> getNextAvailableFileName(int count) {
        List<String> found = new ArrayList<>();
        String testsDirectory = configurationService.getTestsDirectory();
        Set<String> exists = new HashSet<>(Arrays.asList(Objects.requireNonNull(new File(testsDirectory).list())));
        for (int i = 1; ; ++i) {
            String token = String.format("%03d", i);
            if (!exists.contains(token)) found.add(token);
            if (found.size() == count) break;
        }
        return found;
    }

}
