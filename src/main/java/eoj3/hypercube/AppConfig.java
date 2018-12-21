package eoj3.hypercube;

import eoj3.hypercube.models.Configuration;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class AppConfig {
    @Bean(name = "configuration")
    public Configuration configuration() {
        return new Configuration();
    }

}
