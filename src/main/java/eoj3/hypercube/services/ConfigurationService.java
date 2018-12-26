package eoj3.hypercube.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eoj3.hypercube.models.Configuration;
import eoj3.hypercube.models.Problem;
import eoj3.hypercube.models.Test;
import eoj3.hypercube.utils.Glimpse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ConfigurationService {

    private Configuration configuration;

    public ConfigurationService() {
        this.configuration = new Configuration();
        initialize();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getWorkingDirectory() {
        return this.configuration.getWorkingDirectory();
    }

    public void setWorkingDirectory(String workingDirectory) {
        File dir = new File(workingDirectory);
        if (dir.exists() && dir.isDirectory()) {
            this.configuration.setWorkingDirectory(workingDirectory);
            initialize();
        } else {
            throw new RuntimeException("Illegal working directory");
        }
    }

    public Problem getProblem() {
        return this.configuration.getProblem();
    }

    public void save() {
        this.configuration.save();
        this.getMeta();
    }

    private void initialize() {
        if (!this.configuration.load())
            this.configuration.create();
        this.getMeta();
    }

    private String getSubDirectory(String subDirectoryName) {
        Path path = Paths.get(this.configuration.getWorkingDirectory(), subDirectoryName);
        if (!path.toFile().exists())
            path.toFile().mkdirs();
        return path.toString();
    }

    public String getTempDirectory() {
        return getSubDirectory("tmp");
    }

    public String getTempFileName(String ext) {
        return Paths.get(getTempDirectory(), RandomStringUtils.randomAlphanumeric(8) + "." + ext).toString();
    }

    public String getTestsDirectory() {
        return getSubDirectory("tests");
    }

    private void getMeta() {
        getMetaTests(this.configuration.getProblem().getTests());
    }

    private void getMetaTests(List<Test> tests) {
        tests.forEach(this::getMetaEachTest);
    }

    private void getMetaEachTest(Test test) {
        Path inputPath = Paths.get(getTestsDirectory(), test.getFileName());
        Path outputPath = Paths.get(getTestsDirectory(), test.getFileName() + ".a");
        test.setLength((double) (inputPath.toFile().length() + outputPath.toFile().length()) / 1024);
        test.setInputPreview(Glimpse.glimpse(inputPath.toFile()));
        test.setOutputPreview(Glimpse.glimpse(outputPath.toFile()));
    }

    public void updateDashboard(Problem newProblem) {
        Problem p = this.configuration.getProblem();
        p.setTimeLimit(newProblem.getTimeLimit());
        p.setMemoryLimit(newProblem.getMemoryLimit());
        this.configuration.save();
    }
}
