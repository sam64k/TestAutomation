package stepDefinations;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import browsersession.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class LoginSteps {
	Browser browser;
	@Given("user enters username")
	public void user_enters_username() throws InterruptedException {

		JavascriptExecutor jse = (JavascriptExecutor)Browser.session;
		Boolean pageload = jse.executeScript("return document.readyState").toString().equals("complete");
		System.out.println("Is Page load: "+pageload);
		Thread.sleep(1000);
		WebElement userName =(WebElement)jse.
				executeScript("return document.querySelector('amplify-sign-in').shadowRoot.querySelector('amplify-form-section > amplify-auth-fields').shadowRoot.querySelector('amplify-username-field').shadowRoot.querySelector('amplify-form-field').shadowRoot.querySelector('input')");

		userName.sendKeys(Setup.username);

	}
	@Given("user enters password")
	public void user_enters_password() {
		JavascriptExecutor jse = (JavascriptExecutor)Browser.session;
		WebElement password =(WebElement)jse.
				executeScript("return document.querySelector('amplify-sign-in').shadowRoot.querySelector('amplify-form-section > amplify-auth-fields').shadowRoot.querySelector('amplify-password-field').shadowRoot.querySelector('amplify-form-field').shadowRoot.querySelector('input[id=\\'password\\']')");
		password.sendKeys(Setup.password);	
	}
	@Given("user clicks on Sign In")
	public void user_clicks_on_sign_in() throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)Browser.session;
		//Thread.sleep(2000);
		String str = jse.executeScript("return document.querySelector('amplify-sign-in').shadowRoot.querySelector('amplify-button[data-test=\\'sign-in-sign-in-button\\']').shadowRoot.querySelector('.button').hidden").toString();
		System.out.println("Sign in hidden should be false:"+str);
		WebElement signButton = (WebElement)jse.executeScript("return document.querySelector('amplify-sign-in').shadowRoot.querySelector('amplify-button[data-test=\\'sign-in-sign-in-button\\']').shadowRoot.querySelector('.button')");
		Actions actions = new Actions(Browser.session);
		actions.moveToElement(signButton).click().perform();
		//signButton.click();	
	}
	@Then("user logged in successfully")
	public void user_logged_in_successfully() {
		// Write code here that turns the phrase above into concrete actions
		WebElement webelement = 
				Browser.session.findElement(By.xpath("//button[@title='Sign Out']"));

		Assert.assertNotNull(webelement.isDisplayed());

	}
	@Given("user Signs Out")
	public void user_signs_out() {
		// Write code here that turns the phrase above into concrete actions
		Actions action = new Actions(Browser.session);
		WebElement signout = Browser.session.findElement(By.xpath("//*[@title='Sign Out']/span[1]"));
		action.moveToElement(signout).click().perform();
		signout.click();

	}
}
