package stepDefinations;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersession.Browser;
import browsersession.BrowserType;
import browsersession.ChromeSession;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;



public class Steps {

	Browser browser;
	private static Logger logger = (Logger) LogManager.getLogger();

	@Given("test sample code {string}")
	public void test_sample_code(String path) {
		// Write code here that turns the phrase above into concrete actions
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		logger.fatal("This is a fatal message");
	}

	@Given("user launches Brexit App in {string}")
	public void user_launches_brexit_app_in(String browserName) {

		if(BrowserType.valueOf(browserName) == BrowserType.CHROME) {
			System.out.println("####-Launch Chrome Browser-####");
			browser = new ChromeSession();
			browser.launchBrowser();

		}
		else if(BrowserType.valueOf(browserName) == BrowserType.FIREFOX) {
			System.out.println("####-Launch FireFox Browser-####");
			System.out.println("FireFox not implemented");
		}
		else if(BrowserType.valueOf(browserName) == BrowserType.IE) {
			System.out.println("####-Launch IE Browser-####");
			System.out.println("IE not implemented");
		}
		else {
			System.out.println("Browser not implemented or incorrect browser name ");

		}

		browser.navigatetoURL(Setup.url);

	}
	@Then("user clicks on menu button")
	public void user_clicks_on_menu_button() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Actions action = new Actions(Browser.session);
		//WebElement menu = Browser.session.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root'][1]"));
		//System.out.println("menu.isdisplayed :"+menu.isDisplayed());
		//System.out.println("menu.isEnabled :"+menu.isEnabled());

		WebDriverWait wait = new WebDriverWait(Browser.session, Duration.ofSeconds(10));
		WebElement menu = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root'][1]")));

		//		WebElement menu = Browser.session.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root'][1]"));

		//**wait for page load
		//		Thread.sleep(3000);
		action.moveToElement(menu).click().perform();
	}
	@Then("user navigates to consignment page")
	public void user_navigates_to_consignment_page() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		Actions action = new Actions(Browser.session);
		WebElement consignment = Browser.session.findElement(By.xpath("//a[@href='/consignments']"));
		action.moveToElement(consignment).click().perform();
		//wait for page to load
		Thread.sleep(2000);

	}
	@Then("search for consignment {string}")
	public void search_for_consignment(String consignmentID) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		//WebDriverWait wait = new WebDriverWait(Browser.session,5);
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='consignmentId' or @name='consignmentId']")));
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='consignmentId' or @name='consignmentId']"));
		element.sendKeys(consignmentID);
		element.sendKeys(Keys.ENTER);
	}

	@Then("user sorts with cosignment status as {string}")
	public void user_sorts_with_cosignment_status_as(String consignmentStatus) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='statusLabel']"));
		element.click();
		
		element = Browser.session.findElement(By.xpath("//*[text()='"+consignmentStatus+"'][@role='option']"));
		element.click();
		WebDriverWait wait = new WebDriverWait(Browser.session,Duration.ofSeconds(5));
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@role='progressbar']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@role='progressbar']")));
		
		//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("consignments-rows")));
		System.out.println("test");
		
	}
	@Then("user selects record from search result")
	public void user_selects_record_from_search_result() {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='consignments-rows']/tr[1]"));
		element.click();

	}

	@Then("user reset search criteria")
	public void user_reset_search_criteria() {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//table[1]//td[5]//input"));
		element.click(); 
		element = Browser.session.findElement(By.xpath("//*[text()='Clear']/parent::button"));
		element.click();
	}


	@Then("verify that closed consignment cannot be edited")
	public void verify_that_closed_consignment_cannot_be_edited() {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='notistack-snackbar']"));
		String message = element.getAttribute("text");
		System.out.println("Message : " +message);
	}


	@Then("user verify consignment {string} is created")
	public void user_verify_consignment_is_created(String consignmentID) {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='consignments-rows']//td[text()='"+consignmentID+"']"));
		Assert.assertTrue(element.isDisplayed());
	}
	@Then("user Verify consignment status is {string}")
	public void user_verify_consignment_status_is(String status) {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='consignments-rows']//td[text()='"+status+"']"));
		Assert.assertTrue(element.isDisplayed());
	}

}
