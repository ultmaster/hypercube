package eoj3.hypercube.controllers;

import eoj3.hypercube.models.Configuration;
import eoj3.hypercube.models.Problem;
import eoj3.hypercube.services.ConfigurationService;
import eoj3.hypercube.services.TestsAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api")
public class MainController {

    private ConfigurationService configurationService;
    private TestsAddService testsAddService;

    @Autowired
    public MainController(ConfigurationService configurationService, TestsAddService testsAddService) {
        this.configurationService = configurationService;
        this.testsAddService = testsAddService;
    }

    @GetMapping(path = "/config")
    public Configuration getConfig() {
        return configurationService.getConfiguration();
    }

    @PutMapping(path = "/config", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putConfig(@RequestBody Configuration newConfig) {
        this.configurationService.setWorkingDirectory(newConfig.getWorkingDirectory());
    }

    @PutMapping(path = "/dashboard", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void putDashboard(@RequestBody Problem newProblem) {
        this.configurationService.updateDashboard(newProblem);
    }

    @PostMapping(path = "/tests/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void postTests(@ModelAttribute TestsAddService.FormWrapper formWrapper) {
        testsAddService.processForm(formWrapper);
    }
}
