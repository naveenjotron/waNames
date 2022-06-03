package StepDefinition;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/Features/Whatsapp.feature"},
glue={"StepDefinition"},
plugin={"pretty","html:target/HtmlReports.html"} )

public class TestRunner {

}
