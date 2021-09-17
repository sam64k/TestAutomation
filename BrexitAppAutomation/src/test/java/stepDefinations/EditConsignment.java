package stepDefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import browsersession.Browser;
import io.cucumber.java.en.Then;

public class EditConsignment {
	Browser browser;
	@Then("user clicks on edit consignment")
	public void user_clicks_on_edit_consignment() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='consignments-rows']//button[1]"));
		element.click();
		element = Browser.session.findElement(By.xpath("//a[text()='Edit consignment info']"));
		element.click();
	}
	@Then("user clicks on apply button")
	public void user_clicks_on_apply_button() {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement element = Browser.session.findElement(By.xpath("//*[@type='submit']"));
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    element.click();
	}


}
