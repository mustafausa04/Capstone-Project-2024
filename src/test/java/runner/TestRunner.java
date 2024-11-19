package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//here we need to tell junit to run the TestRunner class as a cucumber test so we do the below
@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features/",
        glue = "stepdefinitions"
)


public class TestRunner {
    //this file will allow us to execute and run our feature files/step definitions
}
