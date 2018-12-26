package eoj3.hypercube.models;

import java.util.List;

public class CommandLineTemplate {
    private String fileName;
    private List<String> arguments;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }
}
