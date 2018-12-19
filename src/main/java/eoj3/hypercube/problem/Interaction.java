package eoj3.hypercube.problem;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Interaction {
    private boolean enable;
    private Program interactor;

    public Interaction() {
        this.enable = false;
        this.interactor = null;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Program getInteractor() {
        return interactor;
    }

    public void setInteractor(Program interactor) {
        this.interactor = interactor;
    }
}
