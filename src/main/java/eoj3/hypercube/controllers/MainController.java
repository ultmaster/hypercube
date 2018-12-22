package eoj3.hypercube.controllers;

import eoj3.hypercube.AppConfig;
import eoj3.hypercube.models.Configuration;
import eoj3.hypercube.models.Problem;
import eoj3.hypercube.services.TestsAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path="/api")
public class MainController {

    private Configuration configuration;
    private TestsAddService testsAddService;

    @Autowired
    public MainController(Configuration configuration, TestsAddService testsAddService) {
        this.configuration = configuration;
        this.testsAddService = testsAddService;
    }

    @GetMapping(path = "/config")
    public Configuration getConfig() {
        return configuration;
    }

    @PutMapping(path = "/config", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putConfig(@RequestBody Configuration newConfig) {
        this.configuration.setWorkingDirectory(newConfig.getWorkingDirectory());
    }

    @PutMapping(path = "/dashboard", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putDashboard(@RequestBody Problem newProblem) {
        Problem p = this.configuration.getProblem();
        p.setTimeLimit(newProblem.getTimeLimit());
        p.setMemoryLimit(newProblem.getMemoryLimit());
        this.configuration.save();
    }

    @PostMapping(path = "/tests/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postTests(@ModelAttribute TestsAddService.FormWrapper formWrapper) {
        testsAddService.processForm(formWrapper);
    }
}
