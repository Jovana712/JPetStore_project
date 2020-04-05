package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getUserID() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("userIDInp")));
	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("newPasswordInp")));
	}

	public WebElement getRepeatPassword() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeatPasswordInp")));
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("firstNameInp")));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("lastNameInp")));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("emailInp")));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phoneInp")));
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address1Inp")));
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address2Inp")));
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("cityInp")));
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("stateInp")));
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zipInp")));
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("countryInp")));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("submitBtn")));
	}

	public void submitBtn() {
		this.getSubmit().click();

	}

	public boolean isSuccessfulRegistration() {
		try {

			this.driver.findElement(By.xpath(this.locators.getProperty("myAccountLink"))).isDisplayed();

			return true;

		} catch (Exception e) {

			return false;
		}

	}

}
