package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getUsername() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("usernameInp")));
	}

	public void setUsername(String username) {
		this.getUsername().clear();
		this.getUsername().sendKeys(username);
	}

	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("passwordInp")));
	}

	public void setPassword(String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}

	public WebElement getLoginBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("loginBtn")));
	}

	public void login(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
		this.getLoginBtn().click();

	}

	public WebElement getLogoutBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("signOutLink")));
	}

	public boolean isSuccessfulLogin() {
		try {
			if (getLogoutBtn().isDisplayed()) {
				return true;

			} else {

				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void logout() {
		this.getLogoutBtn().click();
	}

	public WebElement getRegisterBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("registerBtn")));
	}

	public void registerBtnClick() {
		this.getRegisterBtn().click();
	}

	public boolean isRegisterPage() {
		if (this.driver.findElement(By.xpath(this.locators.getProperty("submitBtn"))).isDisplayed()) {
			return true;
		}
		return false;

	}

}
