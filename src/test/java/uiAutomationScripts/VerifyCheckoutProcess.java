package uiAutomationScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BasePage;

public class VerifyCheckoutProcess extends BasePage {

	@Test(dataProvider = "getData")
	public void verifySuccessfullAccountCreation(HashMap<String, String> input) throws InterruptedException {
		
		loginPage.successfullLogin(input.get("existingEmail"),input.get("existingPassword"),input.get("existingName"));
		
		// verify search textbox is displayed
		Assert.assertTrue(searchProduct.verifySearchTextField());

		// verify search results by entering full name of product
		searchProduct.enterProductFullName(input.get("product"));
		cartPage.checkoutProcess(input.get("product"),input.get("city"),input.get("address"),input.get("pincode"));
		
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\testData\\SearchProductTestData.json");
		return new Object[][] { { data.get(0) } };

	}
}
