package eoj3.hypercube.controllers;

import eoj3.hypercube.models.Configuration;
import eoj3.hypercube.models.Problem;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class MainController {

    private Configuration configuration;

    public MainController() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigurationBean.class);
        configuration = context.getBean("configuration", Configuration.class);
    }

    @GetMapping(path = "/config")
    public Configuration get() {
        return configuration;
    }

    @PutMapping(path = "/config", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody Configuration newConfig) {
        this.configuration.setWorkingDirectory(newConfig.getWorkingDirectory());
    }

    @PutMapping(path = "/dashboard", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postDashboard(@RequestBody Problem newProblem) {
        Problem p = this.configuration.getProblem();
        p.setTimeLimit(newProblem.getTimeLimit());
        p.setMemoryLimit(newProblem.getMemoryLimit());
        this.configuration.save();
    }
}