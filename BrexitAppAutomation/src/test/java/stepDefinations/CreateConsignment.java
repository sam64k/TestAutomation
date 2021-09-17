package stepDefinations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browsersession.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CreateConsignment {
	Browser browser;
	String consignmentID=null;
	List<String> UPIList;
	private static Logger logger = (Logger) LogManager.getLogger();
	
	@Then("user clicks on Create New button")
	public void user_clicks_on_create_new_button() {
		// Write code here that turns the phrase above into concrete actions
		WebElement createNew = Browser.session.findElement(By.xpath("//*[@id='create-new-button']"));
		createNew.click();
	}
	@Then("user selects Departure date {string} and time HH {string} MM {string}")
	public void user_selects_departure_date_and_time_hh_mm(String DD, String HH, String MM) {
		// Write code here that turns the phrase above into concrete actions
		int inc = Integer.parseInt(DD);
		LocalDate today = LocalDate.now();
	    int day = today.getDayOfMonth()+inc;
		WebElement date = Browser.session.findElement(By.xpath("//input[@value='Pick time of departure']"));
		date.click();
		date = Browser.session.findElement(By.xpath("//p[text()='"+day+"']"));
		//date = Browser.session.findElement(By.xpath("./.."));
		JavascriptExecutor jse = (JavascriptExecutor)Browser.session;
		date = (WebElement) jse.executeScript("return arguments[0].parentNode;",date);
		date.isDisplayed();
		date.click();

		WebElement time = Browser.session.findElement(By.xpath("//span[text()='"+HH+"']"));
		Actions action = new Actions(Browser.session);
		action.moveToElement(time).click().perform();
		time = Browser.session.findElement(By.xpath("//span[text()='"+MM+"']"));
		action.moveToElement(time).click().perform();

		WebElement ok = Browser.session.findElement(By.xpath("//span[text()='OK']/parent::button[1]"));
		ok.click();

	}

	@Then("user enters vessel number {string}")
	public void user_enters_vessel_number(String vesselNumber) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//input[starts-with(@name,'vesselNumber')]"));
		while(!element.getAttribute("value").equals("")) {
			//element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
			//element.sendKeys(Keys.BACK_SPACE);
			element.click();
			element.clear();
		}
		element.sendKeys(vesselNumber);
	}
	@Then("user enters trailer Number {string}")
	public void user_enters_trailer_number(String trailerNumber) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@name='userDefinedId' or @id='userDefinedId']"));
		while(!element.getAttribute("value").equals("")) {
			//element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
			//element.sendKeys(Keys.BACK_SPACE);
			element.click();
			
			element.clear();
		}
		element.sendKeys(trailerNumber);
		//JavascriptExecutor jse = (JavascriptExecutor)Browser.session;
		//jse.executeScript("arguments[0].setAttribute('value','"+trailerNumber+"')", element);
	}

	@Then("user selects {string} from Carrier dropdown")
	public void user_selects_from_carrier_dropdown(String string) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		WebElement carrier = Browser.session.findElement(By.xpath("//*[@id='mui-component-select-handler']"));
		carrier.click();
		Thread.sleep(1000);
		carrier = Browser.session.findElement((By.xpath("//li[text()='"+string+"']")));
		carrier.click();
	}

	@Then("user selects {string} from Destination dropdown")
	public void user_selects_from_destination_dropdown(String string) {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("not implemented destination dropdown");
	}
	@Then("user selects {string} from Shipping Line dropdown")
	public void user_selects_from_shipping_line_dropdown(String shippingLine) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		WebElement shippingline = Browser.session.findElement(By.xpath("//*[@id='mui-component-select-ship_line']"));
		shippingline.click();
		Thread.sleep(1000);
		shippingline = Browser.session.findElement((By.xpath("//li[text()='"+shippingLine+"']")));
		shippingline.click();
	}

	@Then("verify nationality is {string}")
	public void verify_nationality_is(String nationality) {
		// Write code here that turns the phrase above into concrete actions
		WebElement element  = Browser.session.findElement(By.xpath("//input[@value='Cyprus']"));
		Assert.assertTrue(element.isDisplayed());
	}
	@Then("Verify port is {string}")
	public void verify_port_is(String port) {
		// Write code here that turns the phrase above into concrete actions
		WebElement element  = Browser.session.findElement(By.xpath("//input[@value='Holyhead']"));
		Assert.assertTrue(element.isDisplayed());
	}
	@Then("user clicks on create button")
	public void user_clicks_on_create_button() {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement element = Browser.session.findElement(By.xpath("//span[text()='Create']/parent::button[1]"));
	    element.click();
	}
	@Then("user scans UPI {string}")
	public void user_scans_upi(String UPI) {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement element = Browser.session.findElement(By.xpath("//input[@id='upi'][@name='upi']"));
	    element.sendKeys(UPI);
	    element.sendKeys(Keys.ENTER);
	}
	@Given("extract UPI from JSON file {string}")
	public void extract_upi_from_json_file(String JSONFilePath) {
	    // Write code here that turns the phrase above into concrete actions
		JSONParser parser = new JSONParser();
		UPIList = new ArrayList<>();
		
		try {
			JSONArray a = (JSONArray) parser.parse(new FileReader(JSONFilePath));
			int i=0;
			for(Object o : a) {
				i=i+1;
				JSONObject parcel = (JSONObject) o;
				String UPI = (String) parcel.get("UPI");
				UPIList.add(UPI);
			}
			logger.info("Total parcels in JSON file :"+i);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Given("user initiates bulk scanning of parcels")
	public void user_initiates_bulk_scanning_of_parcels() {
	    // Write code here that turns the phrase above into concrete actions
	    WebDriverWait wait = new WebDriverWait(Browser.session,Duration.ofSeconds(15));
	    WebElement element;
	  
	    for(String UPIParcel : UPIList) {
	    element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='upi'][@name='upi']")));
	    //if(element.getAttribute("value") == null) {
	    element.sendKeys(UPIParcel);
	    element.sendKeys(Keys.ENTER);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='"+UPIParcel+"']")));
	    element = Browser.session.findElement(By.xpath("//p[text()='"+UPIParcel+"']/parent::div/h5"));
	    System.out.println("Scanning Parcel : "+UPIParcel+" >> "+element.getText());
	    wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//p[text()='"+UPIParcel+"']"), 0));
	    //wait.until(ExpectedConditions.)
	    }
	}

	@Then("verify {string} message is displayed")
	public void verify_message_is_displayed(String message) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement element = Browser.session.findElement(By.xpath("//h5[text()='"+message+"']"));
	    System.out.println("message displayed :"+element.isDisplayed());
	    Assert.assertTrue(element.isDisplayed());
	    Thread.sleep(1500);
	}
	@Then("capture consignment ID")
	public void capture_consignment_id() {
	    // Write code here that turns the phrase above into concrete actions
		String xpath = "//*[@id='consignment-id']";
		int timeoutSS = 10;
		WebElement element = Browser.waitForPresenceOfElement(xpath, timeoutSS);
		consignmentID = element.getText();
		System.out.println("Consignment ID : "+consignmentID);
		
	}
	@Then("user search for consignment ID")
	public void user_search_for_consignment_id() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement element = Browser.session.findElement(By.xpath("//*[@id='consignmentId' or @name='consignmentId']"));
	    if(consignmentID != null) {
		element.sendKeys(consignmentID);
	    element.sendKeys(Keys.ENTER);
	    }
	    else
	    	System.out.println("Consignment ID is NULL!!");
	}
}
