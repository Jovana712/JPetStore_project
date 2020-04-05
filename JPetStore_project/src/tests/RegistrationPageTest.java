package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.RegistrationPage;
import utils.ExcelUtils;

public class RegistrationPageTest {

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
		driver.navigate().to(this.locators.getProperty("registerUrl"));

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void RegistrationTest() {

		RegistrationPage rp = new RegistrationPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		ExcelUtils.setUniqueIdRand();

		for (int i = 1; i < 10; i++) {

			rp.getUserID().clear();
			rp.getUserID().sendKeys(ExcelUtils.getDataAt(i, 0));

			rp.getNewPassword().clear();
			rp.getNewPassword().sendKeys(ExcelUtils.getDataAt(i, 1));

			rp.getRepeatPassword().clear();
			rp.getRepeatPassword().sendKeys(ExcelUtils.getDataAt(i, 1));

			rp.getFirstName().clear();
			rp.getFirstName().sendKeys(ExcelUtils.getDataAt(i, 2));

			rp.getLastName().clear();
			rp.getLastName().sendKeys(ExcelUtils.getDataAt(i, 3));

			rp.getEmail().clear();
			rp.getEmail().sendKeys(ExcelUtils.getDataAt(i, 4));

			rp.getPhone().clear();
			rp.getPhone().sendKeys(ExcelUtils.getDataAt(i, 5));

			rp.getAddress1().clear();
			rp.getAddress1().sendKeys(ExcelUtils.getDataAt(i, 6));

			rp.getAddress2().clear();
			rp.getAddress2().sendKeys(ExcelUtils.getDataAt(i, 7));

			rp.getCity().clear();
			rp.getCity().sendKeys(ExcelUtils.getDataAt(i, 8));

			rp.getState().clear();
			rp.getState().sendKeys(ExcelUtils.getDataAt(i, 9));

			rp.getZip().clear();
			rp.getZip().sendKeys(ExcelUtils.getDataAt(i, 10));

			rp.getCountry().clear();
			rp.getCountry().sendKeys(ExcelUtils.getDataAt(i, 11));

			rp.submitBtn();
			sa.assertTrue(rp.isSuccessfulRegistration());
			this.driver.navigate().back();

		}

		sa.assertAll();

	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}

}
