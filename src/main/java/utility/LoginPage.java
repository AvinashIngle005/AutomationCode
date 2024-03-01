package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.CommonUtility;

public class LoginPage extends CommonUtility{
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath = "//div[@class='header__icons']/a[@class='header__icon header__icon--account link focus-inset small-hide']")
	WebElement loginIcon;
	
	@FindBy(xpath = "//input[@id='CustomerEmail']")
	WebElement loginEmail;
	
	@FindBy(xpath = "//input[@id='CustomerPassword']")
	WebElement loginPassword;
	
	@FindBy(xpath = "//button[contains(text(),'Sign in')]")
	WebElement signInButton;
	
	@FindBy(xpath = "//h2[contains(text(),'Account details')]/following-sibling::p")
	WebElement userDetails;
	
	@FindBy(xpath = "//h2[@class='form__message']/following-sibling::div/ul/li")
	WebElement loginError;
	
	
	/*
	 * This Method Verify Successful Login Process 
	 */
	public void successfullLogin(String email,String password,String name) {
		waitUntilVisibilityOf(loginIcon);
		loginIcon.click();
		waitUntilVisibilityOf(loginEmail);
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		signInButton.click();
		waitUntilVisibilityOf(userDetails);
		if(userDetails.getText().contains(name)) {
			System.out.println("Login Successfull");
		}
	}


	/*
	 * This Method Verify the error message for submitting wrong password during login
	 */
	public void unsuccessfullLogin(String email, String password, String errorMessage) {
		waitUntilVisibilityOf(loginIcon);
		loginIcon.click();
		waitUntilVisibilityOf(loginEmail);
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		signInButton.click();
		if(loginError.getText().equalsIgnoreCase(errorMessage)) {
			System.out.println("Error Message Verified");
		}
		
	}

}
