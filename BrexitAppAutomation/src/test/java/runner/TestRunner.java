package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features/test.feature",
		dryRun = false,
		monochrome = true,
		//tags = "@regression",
		glue = "stepDefinations",
		plugin = { "pretty","html:target/cucumber-reports1.html",
					"json:target/cucumber-reports1.json"	}
		)
public class TestRunner {

}
