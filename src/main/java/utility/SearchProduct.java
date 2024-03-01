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

	public boolean verifySearchTextField() {
		searchButton.click();
		waitUntilVisibilityOf(searchTextBox);
		return searchTextBox.isDisplayed();
	}

	public void enterProductFullName(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input);
	}

	public void enterProductFullNameInUpperCase(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input.toUpperCase());
	}

	public void enterProductFullNameInLowerCase(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input.toLowerCase());
	}

	public void enterFirstThreeLetterOfProductlName(String input) {
		searchTextBox.clear();
		searchTextBox.sendKeys(input.substring(0, 4));
	}

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

	public boolean verifySearchSuggestionAndSearchResultIsRelevant(String input) throws InterruptedException {
		waitUntilVisibilityElementLocated(suggestion);
		WebElement searchResult = suggestions.stream().filter(s -> s.getText().equalsIgnoreCase(input)).findFirst()
				.get();
		searchResult.click();
		waitUntilVisibilityOf(searchedProductName);
		if (searchedProductName.getText().equals(input)) {
			driver.navigate().back();
//			Thread.sleep(10000);
			return true;
		} else {

			return false;
		}
	}

}