package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;
import pages.PetStoreMenuPage;
import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class SignInPageTest {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {

		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/locators.properties"));

		driver.manage().window().maximize();
		driver.navigate().to(this.locators.getProperty("signInUrl"));

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void loginInTest() throws FileNotFoundException, IOException {

		SignInPage sip = new SignInPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);

			sip.login(username, password);
			sa.assertTrue(sip.isSuccessfulLogin());
			sip.logout();
			driver.navigate().to(this.locators.getProperty("signInUrl"));
		}

		sa.assertAll();
	}

	@Test
	public void registerBtnFuncTest() throws FileNotFoundException, IOException {

		SignInPage sip = new SignInPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		sip.registerBtnClick();
		sa.assertTrue(sip.isRegisterPage());

	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}
}
