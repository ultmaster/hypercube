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
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
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

    public boolean load() {
        File file = getProblemXmlFile();
        try {
            if (file.exists()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Problem.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                this.problem = (Problem) jaxbUnmarshaller.unmarshal(file);
                return true;
            }
        } catch (JAXBException e) {
            return false;
        }
        return false;
    }

    public void create() {
        this.problem = new Problem();
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
