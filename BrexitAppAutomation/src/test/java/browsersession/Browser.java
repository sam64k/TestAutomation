package browsersession;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Browser {
	public static WebDriver session;
	final int IMPLICIT_WAIT=10;
	
	Browser(){
		session=null;
	}

	public abstract void launchBrowser();
	
	public void navigatetoURL(String url) {
		session.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		session.get(url);
	}
	
	public void closeBrowser() {
		if(session != null) {
			session.quit();
			System.out.println("******Browser session closed*******");
		}
	}
	public static WebElement waitForPresenceOfElement(String xpath, int seconds) {
		WebDriverWait wait = new WebDriverWait(Browser.session,Duration.ofSeconds(seconds));
		WebElement element = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
				);
		return element;
	}
}
