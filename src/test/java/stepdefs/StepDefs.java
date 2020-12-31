package stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class StepDefs {

	Scenario scenario;
	
	@Before
	public void SetUp(Scenario s) {
		scenario = s;
	}
	
	@Given("I open the Browser and Navigate to the URL {string}")
	public void i_open_the_Browser_and_Navigate_to_the_URL(String string) {

		WebDriver driver = new ChromeDriver();
		driver.get(string);
		scenario.write("Opened the Browser with URL: " + string);
		
	}
	
}
