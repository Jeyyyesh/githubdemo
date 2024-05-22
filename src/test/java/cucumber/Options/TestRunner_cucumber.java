package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Feature",
plugin = {"pretty","html:target/htmlreports/reports.html","json:target/jsonreports/cucmber-report.json"},
glue = "StepDefinition") 
//tags = "@deleteplace")
public class TestRunner_cucumber 
{

}
