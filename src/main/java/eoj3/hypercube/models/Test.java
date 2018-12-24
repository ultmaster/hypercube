package eoj3.hypercube.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Test {

    private String fileName;
    private String description;
    private String inputPreview;
    private String outputPreview;
    private double length; // KB

    @XmlAttribute
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public String getInputPreview() {
        return inputPreview;
    }

    public void setInputPreview(String inputPreview) {
        this.inputPreview = inputPreview;
    }

    @XmlTransient
    public String getOutputPreview() {
        return outputPreview;
    }

    public void setOutputPreview(String outputPreview) {
        this.outputPreview = outputPreview;
    }

    @XmlTransient
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
