package eoj3.hypercube.controllers;

import eoj3.hypercube.models.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ConfigurationBean {
    @Bean(name = "configuration")
    public Configuration configuration() {
        return new Configuration();
    }

}
