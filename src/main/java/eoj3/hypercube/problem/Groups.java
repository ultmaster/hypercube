package eoj3.hypercube.problem;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

@XmlRootElement
public class Groups {
    private boolean enabled;
    private Map<Integer, Integer> dependencies;
    private List<Integer> points;

    public Groups() {
        enabled = false;
        dependencies = null;
        points = null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Map<Integer, Integer> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Map<Integer, Integer> dependencies) {
        this.dependencies = dependencies;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }
}
