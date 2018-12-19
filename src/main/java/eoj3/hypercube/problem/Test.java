package eoj3.hypercube.problem;

import org.springframework.data.annotation.ReadOnlyProperty;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Test {
    private String inputPath;
    private String outputPath;

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public int getLength() {
        return 5;
    }

    public String getInputPreview() {
        return "this is input";
    }
}
