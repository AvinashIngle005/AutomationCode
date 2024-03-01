package utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commonUtility.CommonUtility;

public class CartPage extends CommonUtility {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='price__regular']/span[@class='price-item price-item--regular']")
	WebElement productPrice;

	@FindBy(xpath = "//quantity-input[@class='quantity']/button[@name='plus']")
	WebElement addButton;

	@FindBy(xpath = "//button[@name='add']")
	WebElement addToCart;

	@FindBy(xpath = "//a[@id='cart-icon-bubble']")
	WebElement cartButton;

	@FindBy(xpath = "//div[@class='title-wrapper-with-link']/h1[contains(text(),'Your cart')]")
	WebElement cartText;

	@FindBy(xpath = "//td[@class='cart-item__details']/a")
	WebElement cartProductName;

	@FindBy(xpath = "//input[@class='quantity__input']")
	WebElement cartProductQuantity;

	@FindBy(xpath = "//td[@class='cart-item__totals right small-hide']//span[@class='price price--end']")
	WebElement cartPrice;

	@FindBy(xpath = "//button[@id='checkout']")
	WebElement chechoutButton;

	@FindBy(xpath = "//input[@id='basic-paymentOnDelivery']")
	WebElement paymentMethodButton;

	@FindBy(xpath = "(//div[@class='nfgb6p0']/div/div/following-sibling::div)[1]")
	WebElement subtotalText;

	@FindBy(xpath = "//li[@class='discounts__discount discounts__discount--end']")
	WebElement totalDiscount;

	@FindBy(xpath = "//button[@class='QT4by _1fragemjx rqC98 EbLsk _7QHNJ VDIfJ j6D1f janiy']")
	WebElement completeOrderButton;

	@FindBy(xpath = "//input[@id='TextField2']")
	WebElement cityTextBox;

	@FindBy(xpath = "//select[@id='Select1']")
	WebElement stateDropDown;

	@FindBy(xpath = "//input[@id='TextField3']") /// Maharashtra MH
	WebElement pinCodeTextBox;

	@FindBy(xpath = "//input[@id='shipping-address1']")
	WebElement addressTextBox;

	@FindBy(xpath = "//h2[contains(text(),'Thank you')]")
	WebElement successMessage;

	By suggestion = By.xpath("//ul[@id='predictive-search-results-list']/li/a/div/h3");

	@FindBy(xpath = "//ul[@id='predictive-search-results-list']/li/a/div/h3")
	List<WebElement> suggestions;

	@FindBy(xpath = "//fieldset[@id='shipping_methods']//div[@class='B4zH6 Zb82w HKtYc OpmPd']")
	WebElement shippingMethod;

	/*
	 * This Method Verify the entire checkout process along with login process,
	 * adding a product to cart, increasing the qty of that product to verify the
	 * sum of the product is matched or not, and checkout page
	 */

	public void checkoutProcess(String product, String city, String address, String pincode)
			throws InterruptedException {

		waitUntilVisibilityElementLocated(suggestion);
		WebElement searchResult = suggestions.stream().filter(s -> s.getText().equalsIgnoreCase(product)).findFirst()
				.get();
		if (searchResult.getText().equals(product)) {
			searchResult.click();
		}
		waitUntilVisibilityOf(productPrice);
		String price = productPrice.getText().split(" ")[1];
		Double priceValue = Double.parseDouble(price);
		Double priceForThreeQty = priceValue * 3;
		waitUntilVisibilityOf(addButton);
		for (int i = 0; i < 2; i++) {
			addButton.click();
		}
		addToCart.click();
		waitUntilVisibilityOf(cartButton);
		Thread.sleep(5000);
		cartButton.click();
		Assert.assertTrue(cartProductName.getText().equalsIgnoreCase(product),
				"Product in the cart matched with added product");
		Assert.assertTrue(cartProductQuantity.getAttribute("value").equalsIgnoreCase("3"), "Quantity matched");
		String discount = totalDiscount.getText().split("Rs. ")[1].split("\\)")[0];
		double discountValue = Double.parseDouble(discount);
//			double totalDiscount=discountValue*3;
		String cartPricevalue = cartPrice.getText().split(" ")[1];
		Double cartpriceValue = Double.parseDouble(cartPricevalue);
		Assert.assertTrue(cartpriceValue - discountValue == (priceForThreeQty - discountValue), "Price matched");
		chechoutButton.click();
		waitUntilVisibilityOf(paymentMethodButton);
		paymentMethodButton.click();
		String subTotal = subtotalText.getText().substring(1);
		Double subToatlCheckout = Double.parseDouble(subTotal);
//			Assert.assertTrue(subToatlCheckout==priceForThreeQty,"Price matched");
		addressTextBox.sendKeys(address);
		cityTextBox.sendKeys(city);
		pinCodeTextBox.sendKeys(pincode);
		Select s = new Select(stateDropDown);
		s.selectByVisibleText("Maharashtra");
		waitUntilVisibilityOf(shippingMethod);
		shippingMethod.click();
		completeOrderButton.click();
		waitUntilVisibilityOf(successMessage);
		if (successMessage.isDisplayed()) {
			System.out.println("Order Placed Successfully");
		}
	}

}
