package eoj3.hypercube;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/config")
public class ConfigurationController {

    private Configuration configuration;

    public ConfigurationController() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConfigurationBean.class);
        configuration = context.getBean("configuration", Configuration.class);
    }

    @GetMapping(path = "")
    public Configuration get() {
        return configuration;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody Configuration newConfig) {
        this.configuration.setWorkingDirectory(newConfig.getWorkingDirectory());
    }
}
