package eoj3.hypercube;

public class Configuration {

    private String workingDirectory;

    public Configuration() {
        this.workingDirectory = "not set yet";
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
}
