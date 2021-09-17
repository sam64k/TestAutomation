package stepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import browsersession.Browser;
import io.cucumber.java.en.Then;

public class CloseConsignment {
	Browser browser;
	WebElement element;
	@Then("user clicks on close consignment")
	public void user_clicks_on_close_consignment() {
	    // Write code here that turns the phrase above into concrete actions
		element = Browser.session.findElement(By.xpath("//*[@id='consignments-rows']//button[1]"));
		element.click();

		element = Browser.session.findElement(By.xpath("//p[text()='Close/Seal consignment']"));
		element.click();
	}
	@Then("user seal and print consignment")
	public void user_seal_and_print_consignment() {
	    // Write code here that turns the phrase above into concrete actions
		element = Browser.session.findElement(By.xpath("//*[text()='Seal & Print']/parent::button[1]"));
		element.click();
	}
}
