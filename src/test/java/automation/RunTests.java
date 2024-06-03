package automation;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports.html"
               },
        features = "src/main/resources/features", // Correct path for test features
        glue = "automation.glue" // Specify the package containing step definitions
)
public class RunTests {
    // No need for a @Test method here
}
