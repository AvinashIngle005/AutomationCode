package uiAutomationScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BasePage;

public class VerifyLoginAndRegistrationProcess extends BasePage{

	@Test(dataProvider = "getData")
	public void verifySuccessfullAccountCreation(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifySuccessfullAccountCreation(input.get("lastName"));
	}
	
	@Test(dataProvider = "getData")
	public void verifySuccessfullLogin(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		loginPage.successfullLogin(input.get("existingEmail"),input.get("existingPassword"),input.get("existingName"));
	}
	

	@Test(dataProvider = "getData")
	public void verifyunSuccessfullLogin(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		loginPage.unsuccessfullLogin(input.get("existingEmail"),input.get("wrongPassword"),input.get("loginError"));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\testData\\SearchProductTestData.json");
		return new Object[][] { { data.get(0) } };

	}
}
