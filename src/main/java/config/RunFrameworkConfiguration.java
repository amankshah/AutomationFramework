package config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import io.cucumber.spring.CucumberContextConfiguration;

@Configuration
@CompontentScan("main")
public class RunFrameworkConfiguration {
    public RunFrameworkConfiguration() {
    }
}
