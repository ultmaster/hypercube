package eoj3.hypercube.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Problem {
    private Integer hypercubeVersion;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Boolean testsWellFormed;
    private String interactor;
    private String checker;
    private String validator;
    private List<Program> programs;
    private List<Test> tests;
    private Boolean pointsEnabled;
    private Boolean groupEnabled;
    private Map<Integer, Integer> groupDependencies;
    private List<Integer> groupPoints;

    Problem() {
        hypercubeVersion = 1;
        timeLimit = 1000;
        memoryLimit = 512;
        testsWellFormed = true;
        pointsEnabled = groupEnabled = false;
        tests = new ArrayList<Test>();
    }

    @XmlAttribute
    public Integer getHypercubeVersion() {
        return hypercubeVersion;
    }

    public void setHypercubeVersion(Integer hypercubeVersion) {
        this.hypercubeVersion = hypercubeVersion;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        if (timeLimit != null)
            this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        if (memoryLimit != null)
            this.memoryLimit = memoryLimit;
    }

    public Boolean getTestsWellFormed() {
        return testsWellFormed;
    }

    public void setTestsWellFormed(Boolean testsWellFormed) {
        if (testsWellFormed != null)
            this.testsWellFormed = testsWellFormed;
    }

    public String getInteractor() {
        return interactor;
    }

    public void setInteractor(String interactor) {
        if (interactor != null)
            this.interactor = interactor;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        if (checker != null)
            this.checker = checker;
    }

    public String getValidator() {
        return validator;
    }

    public void setValidator(String validator) {
        if (validator != null)
            this.validator = validator;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        if (programs != null)
            this.programs = programs;
    }

    @XmlElementWrapper
    @XmlElement(name="test")
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        if (tests != null)
            this.tests = tests;
    }

    public Boolean getPointsEnabled() {
        return pointsEnabled;
    }

    public void setPointsEnabled(Boolean pointsEnabled) {
        if (pointsEnabled != null)
            this.pointsEnabled = pointsEnabled;
    }

    public Boolean getGroupEnabled() {
        return groupEnabled;
    }

    public void setGroupEnabled(Boolean groupEnabled) {
        if (groupEnabled != null)
            this.groupEnabled = groupEnabled;
    }

    public Map<Integer, Integer> getGroupDependencies() {
        return groupDependencies;
    }

    public void setGroupDependencies(Map<Integer, Integer> groupDependencies) {
        if (groupDependencies != null)
            this.groupDependencies = groupDependencies;
    }

    public List<Integer> getGroupPoints() {
        return groupPoints;
    }

    public void setGroupPoints(List<Integer> groupPoints) {
        if (groupPoints != null)
            this.groupPoints = groupPoints;
    }
}
