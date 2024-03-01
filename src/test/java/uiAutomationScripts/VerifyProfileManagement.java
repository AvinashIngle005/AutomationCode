package uiAutomationScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BasePage;

public class VerifyProfileManagement extends BasePage {

	@Test(dataProvider = "getData")
	public void verifyAccountCreationWithInvalidEmail(HashMap<String, String> input) {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifyInvalidEmailErrorMessage(input.get("firstName"), input.get("lastName"),
				input.get("invalidEmail"), input.get("headerErrorMessage"), input.get("emailErrorMessage"));

	}

	@Test(dataProvider = "getData")
	public void verifyAccountCreationWithPasswordBelowBoundaryValue(HashMap<String, String> input) {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifyInvalidEmailErrorMessage(input.get("firstName"), input.get("lastName"),
				input.get("invalidPassword"), input.get("headerErrorMessage"), input.get("passwordErrorMessage"));

	}

	@Test(dataProvider = "getData")
	public void verifyAccountCreation(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifySuccessfullAccountCreation(input.get("lastName"));
	}

	@Test(dataProvider = "getData")
	public void verifyAccountCreationAndEditDetails(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifySuccessfullAccountCreation(input.get("lastName"));
		profileManagement.editProfile(input.get("address"), input.get("city"));
	}

	@Test(dataProvider = "getData")
	public void verifyAccountCreationAnddeleteDetails(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifySuccessfullAccountCreation(input.get("lastName"));
		profileManagement.deleteAddress();
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\testData\\SearchProductTestData.json");
		return new Object[][] { { data.get(0) } };

	}

}
