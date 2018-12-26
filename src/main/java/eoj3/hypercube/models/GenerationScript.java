package eoj3.hypercube.models;

import java.util.List;

public class GenerationScript {

    private List<CommandLineTemplate> chain;
    private Integer redirectStdoutTo;       // either this is null
    private List<Integer> testIndices;      // or this is null

    public List<CommandLineTemplate> getChain() {
        return chain;
    }

    public void setChain(List<CommandLineTemplate> chain) {
        this.chain = chain;
    }

    public Integer getRedirectStdoutTo() {
        return redirectStdoutTo;
    }

    public void setRedirectStdoutTo(Integer redirectStdoutTo) {
        this.redirectStdoutTo = redirectStdoutTo;
    }

    public List<Integer> getTestIndices() {
        return testIndices;
    }

    public void setTestIndices(List<Integer> testIndices) {
        this.testIndices = testIndices;
    }
}
