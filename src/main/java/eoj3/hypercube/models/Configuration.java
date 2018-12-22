package eoj3.hypercube.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.RandomStringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Configuration {

    private String workingDirectory;
    private Problem problem;

    public Configuration() {
        this.workingDirectory = Paths.get(System.getProperty("user.dir"), "workspace").toString();
        problem = new Problem();
        loadOrNew();
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        File dir = new File(workingDirectory);
        if (dir.exists() && dir.isDirectory()) {
            this.workingDirectory = workingDirectory;
        } else {
            throw new RuntimeException("Illegal working directory");
        }
    }

    private String getSubDirectory(String subDirectoryName) {
        Path path = Paths.get(getWorkingDirectory(), subDirectoryName);
        if (!path.toFile().exists())
            path.toFile().mkdirs();
        return path.toString();
    }

    @JsonIgnore
    public String getTempDirectory() {
        return getSubDirectory("tmp");
    }

    @JsonIgnore
    public String getTempFilename(String ext) {
        return Paths.get(getTempDirectory(), RandomStringUtils.randomAlphanumeric(8) + "." + ext).toString();
    }

    @JsonIgnore
    public String getTestsDirectory() {
        return getSubDirectory("tests");
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    private File getProblemXmlFile() {
        return new File(this.workingDirectory + "/problem.xml");
    }

    public void loadOrNew() {
        File file = getProblemXmlFile();
        try {
            if (file.exists()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Problem.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                this.problem = (Problem) jaxbUnmarshaller.unmarshal(file);
                return;
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        problem = new Problem();
        this.save();
    }

    public void save() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Problem.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(problem, getProblemXmlFile());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
