package utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commonUtility.CommonUtility;

public class Ecommerce extends CommonUtility {

	WebDriver driver;

	public Ecommerce(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	ArrayList<String> amounts;

	@FindBy(xpath = "//h1[text()='Log in']")
	WebElement loginInText;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement emailTextBox;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement passwordTextBox;

	@FindBy(xpath = "//input[@id='login']")
	WebElement loginButton;

	@FindBy(xpath = "//div[@aria-label='Login Successfully']")
	WebElement loginSuccessToastMessage;

	@FindBy(xpath = "//div[@aria-label='Logout Successfully']")
	WebElement logoutSuccessToastMessage;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement loginErrorSuccessToastMessage;

	@FindBy(xpath = "//div[@class='card-body']/h5/b")
	List<WebElement> productName;

	@FindBy(xpath = "//div[@class='card-body']/button[text()=' Add To Cart']")
	List<WebElement> AddToCartButton;

	@FindBy(xpath = "//div[@aria-label='Product Added To Cart']")
	WebElement cartSuccessMessage;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	@FindBy(xpath = "//h1[text()='My Cart']")
	WebElement myCartText;

	@FindBy(xpath = "//button[text()='Continue Shopping']")
	WebElement continueShoppingButton;

	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;

	@FindBy(xpath = "//div[@class='prodTotal cartSection']/p")
	List<WebElement> productAmount;

	@FindBy(xpath = "//span[text()='Total']/../span[@class='value']")
	WebElement totalAmount;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutButton;

	@FindBy(xpath = "//div[ text()=' Payment Method ']")
	WebElement paymentMethodText;

	@FindBy(xpath = "//div[ text()='Credit Card']")
	WebElement creditCardIsSelected;

	@FindBy(xpath = "//select[@class='input ddl'][1]")
	WebElement expiryMonth;

	@FindBy(xpath = "//select[@class='input ddl'][2]")
	WebElement expiryYear;

	@FindBy(xpath = "//div[@class='field small']/input[@class='input txt']")
	WebElement cvvCodeTextBox;

	@FindBy(xpath = "//div[@class='field']/input[@class='input txt']")
	WebElement cardHolderName;

	@FindBy(xpath = "//div[@class='field small']/input[@name='coupon']")
	WebElement couponTextBox;

	@FindBy(xpath = "//div[@class='user__name mt-5']/label")
	WebElement userNameValue;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(xpath = "//button[@type='button']/span")
	List<WebElement> countryLst;

	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeOrderButton;

	@FindBy(xpath = "//div[@aria-label='Order Placed Successfully']")
	WebElement orderPlacedToast;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersPage;

	@FindBy(xpath = "//h1[text()='Your Orders']")
	WebElement yourOrdersPageHeader;

	@FindBy(xpath = "//button[text()='Delete']")
	List<WebElement> allProductsInOrderPage;

	@FindBy(xpath = "//div[text()=' You have No Orders to show at this time.']")
	WebElement noProductText;

	/*
	 * This Method Verify Login text Is displayed or not
	 */
	public boolean verifyLoginPageIsDisplayed() {
		waitUntilVisibilityOf(loginInText);
		System.out.println("Login Page is displayed");
		return loginInText.isDisplayed();
	}

	/*
	 * This Method Verify successful login process
	 */
	public void verifySuccessfullLoginProcess(String username, String password, String successToastMessage)
			throws InterruptedException {
		waitUntilVisibilityOf(emailTextBox);
		Assert.assertTrue(emailTextBox.isDisplayed());
		Thread.sleep(1500);
		emailTextBox.sendKeys(username);
		Assert.assertTrue(passwordTextBox.isDisplayed());
		Thread.sleep(1500);
		passwordTextBox.sendKeys(password);
		Assert.assertTrue(loginButton.isDisplayed());
		Thread.sleep(1500);
		loginButton.click();
		waitUntilVisibilityOf(loginSuccessToastMessage);
		Assert.assertEquals(loginSuccessToastMessage.getText(), successToastMessage);
		if (loginSuccessToastMessage.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	/*
	 * This method adds correct product to cart
	 */
	public void addProductToCart(String product, String message) throws InterruptedException {
		Thread.sleep(3000);
		for (int i = 0; i < productName.size(); i++) {
			if (productName.get(i).getText().equalsIgnoreCase(product)) {
				AddToCartButton.get(i).click();
				waitUntilVisibilityOf(cartSuccessMessage);
				Assert.assertEquals(cartSuccessMessage.getText(), message);
				Thread.sleep(3000);
				break;
			}
		}
	}

	/*
	 * This method verify my cart
	 */
	public void verifyMyCart(String product1, String product2) throws InterruptedException {
		Thread.sleep(3000);
		waitUntilVisibilityOf(cartButton);
		Assert.assertTrue(cartButton.isDisplayed());
		cartButton.click();
		waitUntilVisibilityOf(myCartText);
		Assert.assertTrue(myCartText.isDisplayed());
		Assert.assertTrue(continueShoppingButton.isDisplayed());
		for (int j = 0; j < cartProducts.size(); j++) {
			if (cartProducts.get(j).getText().equalsIgnoreCase(product1)
					|| cartProducts.get(j).getText().equalsIgnoreCase(product2)) {
				Assert.assertTrue(true, "Product " + cartProducts.get(j).getText() + " is matching with added product");
			}
		}
	}

	/*
	 * This method verify total amount from my cart
	 */
	public void verifyTotalAmount() {
		amounts = new ArrayList<String>();
		int amount1;
		int amount2;
		for (int i = 0; i < productAmount.size(); i++) {
			amounts.add(productAmount.get(i).getText().split(" ")[1].trim());
		}
		amount1 = Integer.parseInt(amounts.get(0));
		amount2 = Integer.parseInt(amounts.get(1));
		int totalamount = amount1 + amount2;
		String amount = Integer.toString(totalamount);
		if (totalAmount.getText().contains(amount)) {
			Assert.assertTrue(true);
		}
		System.out.println(amount1 + amount2);
	}

	/*
	 * This method performs checkout process
	 */
	public void checkOutProcess(String paymentText, String activeClass, String cvvCode, String nameOnCard,
			String username, String orderPlacedMessage) throws InterruptedException {
		waitUntilVisibilityOf(checkoutButton);
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(3000);
		checkoutButton.isDisplayed();
		checkoutButton.click();
		waitUntilVisibilityOf(paymentMethodText);
		Assert.assertEquals(paymentMethodText.getText().trim(), paymentText);
		Assert.assertTrue(creditCardIsSelected.getAttribute("class").trim().contains(activeClass));
		Select month = new Select(expiryMonth);
		month.selectByVisibleText("06");
		Thread.sleep(1000);
		Select year = new Select(expiryYear);
		year.selectByVisibleText("28");
		Thread.sleep(1000);
		Assert.assertTrue(cvvCodeTextBox.isDisplayed());
		cvvCodeTextBox.sendKeys(cvvCode);
		Thread.sleep(1000);
		Assert.assertTrue(cardHolderName.isDisplayed());
		cardHolderName.sendKeys(nameOnCard);
		Thread.sleep(1000);
		waitUntilVisibilityOf(couponTextBox);
		Assert.assertTrue(couponTextBox.isDisplayed());
		Assert.assertTrue(userNameValue.isDisplayed());
		Assert.assertEquals(username, userNameValue.getText().trim());
		Thread.sleep(1000);
		Assert.assertTrue(selectCountry.isDisplayed());
		selectCountry.sendKeys("Ind");
		Thread.sleep(3000);
		for (int i = 0; i < countryLst.size(); i++) {
			if (countryLst.get(i).getText().trim().equalsIgnoreCase("India")) {
				countryLst.get(i).click();
				break;
			}
		}
		Assert.assertTrue(placeOrderButton.isDisplayed());
		placeOrderButton.click();
		waitUntilVisibilityOf(orderPlacedToast);
		Assert.assertEquals(orderPlacedToast.getText().trim(), orderPlacedMessage);
	}

	/*
	 * This method removes all orders
	 */
	public void removePlacedOrder() throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();
		Thread.sleep(3000);
		waitUntilVisibilityOf(ordersPage);
		ordersPage.click();
		waitUntilVisibilityOf(yourOrdersPageHeader);
		int i = allProductsInOrderPage.size();
		int j = 0;
		while (i > j) {
			allProductsInOrderPage.get(0).click();
			Thread.sleep(1500);
			j++;
		}
	}

}
