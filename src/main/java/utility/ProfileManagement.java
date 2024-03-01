package utility;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import commonUtility.CommonUtility;

public class ProfileManagement extends CommonUtility {
	WebDriver driver;

	public ProfileManagement(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='header__icons']/a[@class='header__icon header__icon--account link focus-inset small-hide']")
	WebElement loginIcon;

	@FindBy(xpath = "//form[@id='customer_login']//a[contains(text(),'Create account')]")
	WebElement createAccountLink;

	@FindBy(xpath = "//input[@id='RegisterForm-FirstName']")
	WebElement firstName;

	@FindBy(xpath = "//input[@id='RegisterForm-LastName']")
	WebElement lastName;

	@FindBy(xpath = "//input[@id='RegisterForm-email']")
	WebElement email;

	@FindBy(xpath = "//input[@id='RegisterForm-password']")
	WebElement password;

	@FindBy(xpath = "//button[contains(text(),'Create')]")
	WebElement createButton;

	@FindBy(xpath = "//h2[@class='form__message']")
	WebElement headerErrorMessage;

	@FindBy(xpath = "//span[@id='RegisterForm-email-error']")
	WebElement emailErrorMessage;

	@FindBy(xpath = "//span[@id='RegisterForm-password-error']")
	WebElement passwordErrorMessage;

	@FindBy(xpath = "//div[@class='customer account']/div/a")
	WebElement logoutButton;

	@FindBy(xpath = "//span[@id='recaptcha-anchor']")
	WebElement notARobotCheckBox;

	@FindBy(xpath = "//input[@value='Submit']")
	WebElement submitButton;

	@FindBy(xpath = "(//div[@class='title-wrapper-with-link'])[1]/h2")
	WebElement categoryHeading;

	@FindBy(xpath = "//div[@class='customer account']/div/div/following-sibling::div/a")
	WebElement viewAddress;

	@FindBy(xpath = "//main[@id='MainContent']/div/ul/li/button[@id='EditFormButton_8785879335133']")
	WebElement editButton;

	@FindBy(xpath = "//button[@aria-label='Delete 1']")
	WebElement deleteButton;

	@FindBy(xpath = "//input[@id='AddressAddress1_8785879335133']")
	WebElement address;

	@FindBy(xpath = "//input[@id='AddressCity_8785879335133']")
	WebElement city;

	@FindBy(xpath = "//button[contains(text(),'Update address')]")
	WebElement updateButton;

	@FindBy(xpath = "//h2[contains(text(),'Default')]/following-sibling::p")
	WebElement updatedAddress;

	@FindBy(xpath = "//div[@id='WebPixelsManagerSandboxContainer']/iframe")
	WebElement iframe;

	public boolean verifyAccountCreationIcon() {
		waitUntilVisibilityOf(loginIcon);
		System.out.println("Login link is visibile");
		return loginIcon.isDisplayed();
	}

	public void verifyInvalidEmailErrorMessage(String firstNameValue, String lastNameValue, String emailValue,
			String headerErrorMessageValue, String emailErrorMessageValue) {
		String passwordValue = RandomStringUtils.randomAlphabetic(5);
		waitUntilVisibilityOf(loginIcon);
		loginIcon.click();
		waitUntilVisibilityOf(createAccountLink);
		Assert.assertTrue(createAccountLink.isDisplayed());
		createAccountLink.click();
		waitUntilVisibilityOf(firstName);
		Assert.assertTrue(firstName.isDisplayed());
		firstName.sendKeys(firstNameValue);
		Assert.assertTrue(lastName.isDisplayed());
		lastName.sendKeys(lastNameValue);
		Assert.assertTrue(email.isDisplayed());
		email.sendKeys(emailValue);
		Assert.assertTrue(password.isDisplayed());
		password.sendKeys(passwordValue);
		Assert.assertTrue(createButton.isDisplayed());
		createButton.click();
		waitUntilVisibilityOf(headerErrorMessage);
		if (headerErrorMessage.getText().equalsIgnoreCase(headerErrorMessageValue)) {
			Assert.assertTrue(headerErrorMessage.getText().equalsIgnoreCase(headerErrorMessageValue));
			System.out.println("Header error Message is validated : " + headerErrorMessage.getText());

		}

		if (emailErrorMessage.getText().equalsIgnoreCase(emailErrorMessageValue)) {
			Assert.assertTrue(emailErrorMessage.getText().equalsIgnoreCase(emailErrorMessageValue));
			System.out.println("Email error Message is validated : " + emailErrorMessage.getText());

		}
	}

	public void verifyInvalidPasswordErrorMessage(String firstNameValue, String lastNameValue, String passwordValue,
			String headerErrorMessageValue, String passwordErrorMessageValue) {
		String value = RandomStringUtils.randomAlphabetic(4);
		String emailValue = value.concat("@").concat("test.com");
		waitUntilVisibilityOf(loginIcon);
		loginIcon.click();
		waitUntilVisibilityOf(createAccountLink);
		Assert.assertTrue(createAccountLink.isDisplayed());
		createAccountLink.click();
		waitUntilVisibilityOf(firstName);
		Assert.assertTrue(firstName.isDisplayed());
		firstName.sendKeys(firstNameValue);
		Assert.assertTrue(lastName.isDisplayed());
		lastName.sendKeys(lastNameValue);
		Assert.assertTrue(email.isDisplayed());
		email.sendKeys(emailValue);
		Assert.assertTrue(password.isDisplayed());
		password.sendKeys(passwordValue);
		Assert.assertTrue(createButton.isDisplayed());
		createButton.click();
		waitUntilVisibilityOf(headerErrorMessage);
		if (headerErrorMessage.getText().equalsIgnoreCase(headerErrorMessageValue)) {
			Assert.assertTrue(headerErrorMessage.getText().equalsIgnoreCase(headerErrorMessageValue));
			System.out.println("Header error Message is validated : " + headerErrorMessage.getText());

		}

		if (passwordErrorMessage.getText().equalsIgnoreCase(passwordErrorMessageValue)) {
			Assert.assertTrue(passwordErrorMessage.getText().equalsIgnoreCase(passwordErrorMessageValue));
			System.out.println("Password error Message is validated : " + passwordErrorMessage.getText());

		}
	}

	public void verifySuccessfullAccountCreation(String lastNameValue) throws InterruptedException {
		String value = RandomStringUtils.randomAlphabetic(4);
		String passwordValue = RandomStringUtils.randomAlphabetic(6);
		String emailValue = value.concat("@").concat("test.com");
		waitUntilVisibilityOf(loginIcon);
		loginIcon.click();
		waitUntilVisibilityOf(createAccountLink);
		Assert.assertTrue(createAccountLink.isDisplayed());
		createAccountLink.click();
		waitUntilVisibilityOf(firstName);
		Assert.assertTrue(firstName.isDisplayed());
		firstName.sendKeys(value);
		Assert.assertTrue(lastName.isDisplayed());
		lastName.sendKeys(lastNameValue);
		Assert.assertTrue(email.isDisplayed());
		email.sendKeys(emailValue);
		Assert.assertTrue(password.isDisplayed());
		password.sendKeys(passwordValue);
		Assert.assertTrue(createButton.isDisplayed());
		createButton.click();
		Thread.sleep(2000);
		waitUntilVisibilityOf(loginIcon);
		loginIcon.click();
		waitUntilVisibilityOf(logoutButton);
		Assert.assertTrue(logoutButton.isDisplayed());
		if (logoutButton.isDisplayed()) {
			System.out.println("Successfully created a new profile");
		}

	}

	public void editProfile(String addressValue, String cityValue) throws InterruptedException {
		waitUntilVisibilityOf(viewAddress);
		viewAddress.click();
//		Thread.sleep(5000);
		driver.switchTo()
				.frame("web-pixel-sandbox-CUSTOM-shopify-custom-pixel-LAX-4d8af208w39648077pb05fb6acmf7ef51d2");
		editButton.click();
//		deleteButton.click();
		waitUntilVisibilityOf(address);
		address.sendKeys(addressValue);
		city.sendKeys(cityValue);
		updateButton.click();
		if (updatedAddress.getText().contains(addressValue) && updatedAddress.getText().contains(cityValue)) {
			System.out.println("Address Sucessfully Updated");
		}
	}

	public void deleteAddress() {
		waitUntilVisibilityOf(viewAddress);
		viewAddress.click();
//		driver.switchTo().frame(1);
		driver.switchTo().defaultContent();
		deleteButton.click();
		driver.switchTo().alert().accept();

	}

	
}
