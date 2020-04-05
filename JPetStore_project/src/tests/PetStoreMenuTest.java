package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.HomePage;
import pages.PetStoreMenuPage;

public class PetStoreMenuTest {

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
		driver.navigate().to(this.locators.getProperty("petStoreURL"));

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLeftMenu() throws InterruptedException {

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		List<WebElement> links = psmp.getLeftMenu();

		for (int i = 0; i < links.size(); i++) {

			sa.assertTrue(psmp.isUrlGood(links.get(i)));
			sa.assertTrue(psmp.isRightPage(links.get(i)));
			links = psmp.getLeftMenu();
		}

		sa.assertAll();

	}

	@Test
	public void testImageMenu() throws InterruptedException {

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		List<WebElement> links = psmp.getImageMenu();

		for (int i = 0; i < links.size(); i++) {

			sa.assertTrue(psmp.isUrlGood(links.get(i)));
			sa.assertTrue(psmp.isRightPage(links.get(i)));
			links = psmp.getImageMenu();
		}

		sa.assertAll();

	}

	@Test
	public void testQuickMenu() throws InterruptedException {

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		List<WebElement> links = psmp.getQuickMenu();

		for (int i = 0; i < links.size(); i++) {

			sa.assertTrue(psmp.isUrlGood(links.get(i)));
			sa.assertTrue(psmp.isRightPage(links.get(i)));
			links = psmp.getQuickMenu();
		}

		sa.assertAll();

	}

	@Test
	public void testSignInButton() throws InterruptedException {

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		sa.assertTrue(psmp.isSignInPage());

	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
