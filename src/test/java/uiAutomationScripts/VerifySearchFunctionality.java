package uiAutomationScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BasePage;

public class VerifySearchFunctionality extends BasePage {

	@Test(dataProvider = "getData")
	public void verifySearchSuggestions(HashMap<String, String> input) {
		// verify search textbox is displayed
		Assert.assertTrue(searchProduct.verifySearchTextField());

		// verify search results by entering full name of product
		searchProduct.enterProductFullName(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));

		// verify search results by entering first three letters of product name
		searchProduct.enterFirstThreeLetterOfProductlName(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));

		// verify search results by entering full name of product in lower case
		searchProduct.enterProductFullNameInLowerCase(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));

		// verify search results by entering full name of product in upper case
		searchProduct.enterProductFullNameInUpperCase(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));
	}

	@Test(dataProvider = "getData")
	public void verifyAccuracyAndRelevanceOfSearchResults(HashMap<String, String> input) throws InterruptedException {
		// verify search textbox is displayed
		Assert.assertTrue(searchProduct.verifySearchTextField());

		// verify search results by entering full name of product
		searchProduct.enterProductFullName(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));
		Assert.assertTrue(searchProduct.verifyNumberOfSearchSuggestionsIsOne());
		Assert.assertTrue(searchProduct.verifySearchSuggestionAndSearchResultIsRelevant(input.get("product")));

		// verify search results by entering first three letters of product name
		searchProduct.enterFirstThreeLetterOfProductlName(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));
		Assert.assertTrue(searchProduct.verifyNumberOfSearchSuggestionsIsOne());
		Assert.assertTrue(searchProduct.verifySearchSuggestionAndSearchResultIsRelevant(input.get("product")));

		// verify search results by entering full name of product in lower case
		searchProduct.enterProductFullNameInLowerCase(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));
		Assert.assertTrue(searchProduct.verifyNumberOfSearchSuggestionsIsOne());
		Assert.assertTrue(searchProduct.verifySearchSuggestionAndSearchResultIsRelevant(input.get("product")));

		// verify search results by entering full name of product in upper case
		searchProduct.enterProductFullNameInUpperCase(input.get("product"));
		Assert.assertTrue(searchProduct.verifySearchSuggestion(input.get("product")));
		Assert.assertTrue(searchProduct.verifyNumberOfSearchSuggestionsIsOne());
		Assert.assertTrue(searchProduct.verifySearchSuggestionAndSearchResultIsRelevant(input.get("product")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\testData\\SearchProductTestData.json");
		return new Object[][] { { data.get(0) } };

	}
}
