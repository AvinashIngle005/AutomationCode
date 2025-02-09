package uiAutomationScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BasePage;

public class VerifyEcommerceFlow extends BasePage {
	@Test(dataProvider = "getData")
	public void verifySuccessfullAccountCreation(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(ecommerce.verifyLoginPageIsDisplayed());
		ecommerce.verifySuccessfullLoginProcess(input.get("email"), input.get("password"), input.get("successLogin"));
		ecommerce.addProductToCart(input.get("firstProduct"), input.get("cartSuccessMessage"));
		Thread.sleep(5);
		ecommerce.addProductToCart(input.get("secondProduct"), input.get("cartSuccessMessage"));
		ecommerce.verifyMyCart(input.get("firstProduct"), input.get("secondProduct"));
		ecommerce.verifyTotalAmount();
		ecommerce.checkOutProcess(input.get("paymentMethod"), input.get("activeClass"), input.get("cvvCode"),
				input.get("nameOnCard"),input.get("email"), input.get("orderPlacedMessage"));
		ecommerce.removePlacedOrder();
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") + "\\testData\\input.json");
		return new Object[][] { { data.get(0) } };

	}

}
