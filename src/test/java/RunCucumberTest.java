import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
plugin= {"pretty", "html:target/cucumber-reports"},
        strict=true,
        features = "src/test/resources/Feature1.feature",
        monochrome=true,
        dryRun=false
)

public class RunCucumberTest {
}
