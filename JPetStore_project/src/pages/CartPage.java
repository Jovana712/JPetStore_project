package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public List<WebElement> getItemsId() {
		return this.driver.findElements(By.xpath(locators.getProperty("itemIdList")));
	}

	public boolean isItemAddedToCart(String itemID) {
		List<WebElement> listId = this.getItemsId();
		for (int i = 0; i < listId.size(); i++) {
			if (listId.get(i).getText().contentEquals(itemID)) {
				return true;
			}
		}
		return false;
	}

	public void deleteAllCookies() {
		this.driver.manage().deleteAllCookies();

	}

	public boolean isCartEmpty() {
		try {
			this.driver.findElements(By.xpath(locators.getProperty("emptyCartMess")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
