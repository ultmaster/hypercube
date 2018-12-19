package eoj3.hypercube.problem;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Problem {
    private int hypercubeVersion;
    private int timeLimit;
    private int memoryLimit;
    private boolean testsWellFormed;
    private Interaction interaction;
    private Program checker;
    private Program validator;
    private Points points;
    private List<Test> tests;

    public Problem() {
        hypercubeVersion = 1;
        timeLimit = 1000;
        memoryLimit = 512;
        testsWellFormed = true;
        points = new Points();
        interaction = new Interaction();
        checker = new Program();
        validator = new Program();
        points = new Points();
        tests = new ArrayList<Test>();
    }

    @XmlAttribute
    public int getHypercubeVersion() {
        return hypercubeVersion;
    }

    public void setHypercubeVersion(int hypercubeVersion) {
        this.hypercubeVersion = hypercubeVersion;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public boolean isTestsWellFormed() {
        return testsWellFormed;
    }

    public void setTestsWellFormed(boolean testsWellFormed) {
        this.testsWellFormed = testsWellFormed;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public Program getChecker() {
        return checker;
    }

    public void setChecker(Program checker) {
        this.checker = checker;
    }

    public Program getValidator() {
        return validator;
    }

    public void setValidator(Program validator) {
        this.validator = validator;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
