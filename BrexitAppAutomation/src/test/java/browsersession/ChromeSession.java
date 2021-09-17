package browsersession;

import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class ChromeSession extends Browser{
	
	@Override
	public void launchBrowser() {
		System.out.println("******Launching Chrome browser*******");
		ChromeDriverManager.chromedriver().setup();
		session = new ChromeDriver();
		session.manage().deleteAllCookies();
		session.manage().window().maximize();
	}
	public void waitForPageLoad() {
		
    }


}
