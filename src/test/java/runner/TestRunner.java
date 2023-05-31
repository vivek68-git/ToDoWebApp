package runner;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import stepdefinitions.ApplicationHooks;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/java/resources/features"},
		glue = {"stepdefinitions"},
		plugin = {"pretty",
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter::target/cucumber-reports/report.html"
				"timeline:test-output-thread/"
		}

		)

public class TestRunner{
	@AfterClass
	public static void writeExtentReport() {
		ApplicationHooks hooks = new ApplicationHooks();
		Reporter.loadXMLConfig(new File(hooks.getReportConfigPath()));
	}
}