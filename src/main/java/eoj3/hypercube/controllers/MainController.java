package eoj3.hypercube.controllers;

import eoj3.hypercube.AppConfig;
import eoj3.hypercube.models.Configuration;
import eoj3.hypercube.models.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path="/api")
public class MainController {

    private Configuration configuration;

    @Autowired
    public MainController(Configuration configuration) {
        this.configuration = configuration;
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

    class FormWrapper {
        private MultipartFile[] files;

        public FormWrapper() {
        }

        public MultipartFile[] getFiles() {
            return files;
        }

        public void setFiles(MultipartFile[] files) {
            this.files = files;
        }
    }

    @PostMapping(path = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@ModelAttribute FormWrapper wrapper) {
        System.out.println(wrapper.getFiles().length);
    }
}
