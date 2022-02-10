package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features={"C:\\Users\\saurasahu\\IdeaProjects\\Testng_Cucumber_1\\src\\test\\java\\Feature\\Cart.feature"},
        glue={"StepDefinition"},plugin= {"pretty"})

public class TestRunner {

}
