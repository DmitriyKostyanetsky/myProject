package aplana.HW7;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/feature/"},
        glue = {"src.test.java"},
        tags = {"@all"}
        )
public class CucumberRunner { }