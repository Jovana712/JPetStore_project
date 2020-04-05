package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getSignInButton() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("buttonSignIn")));
	}

	public List<WebElement> getQuickMenu() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("quickMenuLinks")));

	}

	public List<WebElement> getLeftMenu() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("leftMenuLinks")));

	}

	public List<WebElement> getImageMenu() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("imageMenuLinks")));

	}

	public static int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean isUrlGood(WebElement we) {
		int status = PetStoreMenuPage.verifyURLStatus(we.getAttribute("href"));
		if (status > 400) {
			return false;
		}

		return true;
	}

	public boolean isRightPage(WebElement we) {
		String href = we.getAttribute("href");
		we.click();
		String petName = this.driver.findElement(By.xpath(locators.getProperty("catalogHeader"))).getText();
		petName = petName.toUpperCase();
		if (href.contains(petName)) {
			this.driver.navigate().back();
			return true;
		}

		this.driver.navigate().back();
		return false;
	}

	public void SignInButtonClick() {
		this.getSignInButton().click();

	}

	public boolean isSignInPage() {
		try {
			this.driver.findElement(By.xpath(locators.getProperty("enterDataMess"))).isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}