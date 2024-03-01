package utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUtility.CommonUtility;

public class SearchProduct extends CommonUtility {

	WebDriver driver;

	public SearchProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//summary[@aria-label='Search']/span")
	WebElement searchButton;

	@FindBy(xpath = "//input[@id='Search-In-Modal']")
	WebElement searchTextBox;

	@FindBy(xpath = "//ul[@id='predictive-search-results-list']/li/a/div/h3")
	List<WebElement> suggestions;

	@FindBy(xpath = "//div[@class='card-wrapper']")
	List<WebElement> products;

	@FindBy(xpath = "//div[@id='ProductInfo-template--15328405717213__main']/h1")
	WebElement searchedProductName;

	By suggestion = By.xpath("//ul[@id='predictive-search-results-list']/li/a/div/h3");

	/*
	 * This Method is used to check search textfield is displayed
	 */
	public boolean verifySearchTextField() {
		searchButton.click();
		waitUntilVisibilityOf(searchTextBox);
		return searchTextBox.isDisplayed();
	}
	
	
	/*
	 * This Method is used to enter product full name
	 */
	public void enterProductFullName(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input);
	}

	/*
	 * This Method is used to enter product full name in Upper case
	 */
	public void enterProductFullNameInUpperCase(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input.toUpperCase());
	}

	/*
	 * This Method is used to enter product full name in lower case
	 */
	public void enterProductFullNameInLowerCase(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input.toLowerCase());
	}

	/*
	 * This Method is used to enter product name's first three letters
	 */
	public void enterFirstThreeLetterOfProductlName(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input.substring(0, 4));
	}

	/*
	 * This Method is used to check if the search suggestion include particular product 
	 */
	public boolean verifySearchSuggestion(String input) {
		waitUntilVisibilityElementLocated(suggestion);
		WebElement searchResult = suggestions.stream().filter(s -> s.getText().equalsIgnoreCase(input)).findFirst()
				.get();
		if (searchResult.getText().equals(input)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * This Method is used to check if the search suggestion shouws accurate sugestions i.e it shows particular product only
	 */
	public boolean verifyNumberOfSearchSuggestionsIsOne() {
		waitUntilVisibilityElementLocated(suggestion);
		if (suggestions.size() == 1) {
			System.out.println("Only " + suggestions.size() + " Search Suggetion i.e accurate suggestion");
			return true;
		} else {
			System.out.println(suggestions.size() + " no. of Search Suggetions");
			return false;
		}

	}

	/*
	 * This Method is used to check if the search suggestion shouws accurate sugestions for multiple product searches
	 */
	public boolean verifySearchSuggestionAndSearchResultIsRelevant(String input)   {
		waitUntilVisibilityElementLocated(suggestion);
		WebElement searchResult = suggestions.stream().filter(s -> s.getText().equalsIgnoreCase(input)).findFirst()
				.get();
		searchResult.click();
		waitUntilVisibilityOf(searchedProductName);
		if (searchedProductName.getText().equals(input)) {
			driver.navigate().back();
			return true;
		} else {

			return false;
		}
	}

}