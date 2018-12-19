package eoj3.hypercube.problem;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Points {
    private boolean enabled;
    private Groups groups;

    Points() {
        enabled = false;
        groups = new Groups();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }
}
