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

import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class CartPageTest {

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
		driver.navigate().to(this.locators.getProperty("carturl"));
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void addToCartTest() {
		
		StoreItemPage storeItem = new StoreItemPage(driver, locators, waiter);
		CartPage pageCart = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);

		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			storeItem.addToCartClick();
			sa.assertTrue(pageCart.isItemAddedToCart(ExcelUtils.getDataAt(i, 0)));

		}
		sa.assertAll();
	}

	@Test
	public void isCookiesFuncTest() {
		
		StoreItemPage storeItem = new StoreItemPage(driver, locators, waiter);
		CartPage pageCart = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		this.driver.navigate().to(locators.getProperty("carturl"));
		
		for (int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			driver.navigate().to(ExcelUtils.getDataAt(i, 1));
			storeItem.addToCartClick();
			
		}
		
		this.driver.navigate().to(locators.getProperty("carturl"));
		pageCart.deleteAllCookies();
		driver.navigate().refresh();
		sa.assertTrue(pageCart.isCartEmpty());
		sa.assertAll();

	}

	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}
}
