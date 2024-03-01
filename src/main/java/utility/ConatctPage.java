package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.CommonUtility;

public class ConatctPage extends CommonUtility {
	WebDriver driver;

	public ConatctPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@class='customer account']/div/div/following-sibling::div/a")
	WebElement viewAddress;

	@FindBy(xpath = "//span[contains(text(),'Contact')]/..")
	WebElement contactPageLink;

	@FindBy(xpath = "//input[@id='ContactForm-phone']")
	WebElement phoneNumberTextBox;

	@FindBy(xpath = "//input[@id='ContactForm-email']")
	WebElement emailTextBox;

	@FindBy(xpath = "//div[@class='contact__button']/button")
	WebElement sendButton;

	@FindBy(xpath = "//div[@class='form__message']/following-sibling::ul/li")
	WebElement ContactPageErrorMessaeg;

	@FindBy(xpath = "//form[@id='ContactForm']/div[@class='form-status form-status-list form__message']")
	WebElement ContactPageSuccessMessaeg;

	/*
	 * This Method Verify the error message for submitting contact form without
	 * email
	 */
	public void submitContactInfoWithoutEmail(String phoneNumber, String emailErrorMessage) {
		waitUntilVisibilityOf(contactPageLink);
		contactPageLink.click();
		waitUntilVisibilityOf(phoneNumberTextBox);
		emailTextBox.clear();
		phoneNumberTextBox.sendKeys(phoneNumber);
		sendButton.click();
		waitUntilVisibilityOf(phoneNumberTextBox);
		if (ContactPageErrorMessaeg.getText().equalsIgnoreCase(emailErrorMessage)) {
			System.out.println("Expected Email error verified");
		}
	}

	/*
	 * This Method Verify the Success message for submitting contact form with valid
	 * details
	 */
	public void submitContactInfoWithValidDetails(String phoneNumber, String successMessage) {
		waitUntilVisibilityOf(contactPageLink);
		contactPageLink.click();
		waitUntilVisibilityOf(phoneNumberTextBox);
		phoneNumberTextBox.sendKeys(phoneNumber);
		sendButton.click();
		waitUntilVisibilityOf(phoneNumberTextBox);
		if (ContactPageSuccessMessaeg.getText().equalsIgnoreCase(successMessage)) {
			System.out.println("Expected Success Message verified");
		}
	}

}
