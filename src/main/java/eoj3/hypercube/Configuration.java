package eoj3.hypercube;

import eoj3.hypercube.problem.Problem;
import eoj3.hypercube.problem.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;

public class Configuration {

    private String workingDirectory;
    private Problem problem;

    public Configuration() {
        this.workingDirectory = System.getProperty("user.dir") + "/workspace";
        problem = new Problem();
        loadOrNewProblem();
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

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    private File getProblemXmlFile() {
        return new File(this.workingDirectory + "/problem.xml");
    }

    private void loadOrNewProblem() {
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
        saveProblem();
    }

    private void saveProblem() {
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
