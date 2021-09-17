package stepDefinations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;

public class Setup {
	public static String url,username,password,UPIfilepath;
	@Before("readxml")
	@Given("intitilizing test environment")
	public void intitilizing_test_environment() throws InvalidPropertiesFormatException, FileNotFoundException, IOException {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("doing env setup");
		Properties prop = new Properties();
		prop.loadFromXML(new FileInputStream("src\\test\\resources\\config\\configuration.xml"));
		url = prop.getProperty("environmentURL");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
		UPIfilepath = prop.getProperty("UPI_JsonFile_path");
		System.out.println("Application under test : "+prop.getProperty("environmentURL"));
		System.out.println("UPI Json File : "+prop.getProperty("UPI_JsonFile_path"));
	}
	
	@Given("read UPI consignment file")
	public void reads_upi_consignment_file(Scenario sc) {
	    // Write code here that turns the phrase above into concrete actions
		 //sc.getSourceTagNames().get(0);
		
	    JSONParser parser = new JSONParser();
	    try {
	    JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(UPIfilepath));
	    
	    	for(Object o: jsonArray) {
	    		JSONObject testData = (JSONObject) o;
	    		String testcaseID = (String) testData.get("TestData");
	    		String UPI = (String) testData.get("UPI");
	    		System.out.println("Test case id : "+testcaseID);
	    		System.out.println("Test Upi : "+UPI);
	    	}
	    }
	    catch(IOException | ParseException ex) {
	    	ex.printStackTrace();
	    }
	    
	}

}
