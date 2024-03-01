package uiAutomationScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import commonUtility.BasePage;

public class VerifyContactPage  extends BasePage{
	
	@Test(dataProvider="getData")
	public void verifyConatctPageWithInvalidDetails(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifySuccessfullAccountCreation(input.get("lastName"));
		contactPage.submitContactInfoWithoutEmail(input.get("phoneNumber"),input.get("ConatctEmailErrorMessage"));
	}
	
	@Test(dataProvider="getData")
	public void verifyConatctPageWithValidDetails(HashMap<String, String> input) throws InterruptedException {
		Assert.assertTrue(profileManagement.verifyAccountCreationIcon());
		profileManagement.verifySuccessfullAccountCreation(input.get("lastName"));
		contactPage.submitContactInfoWithValidDetails(input.get("phoneNumber"),input.get("contactFormSubmissionMessage"));
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\testData\\SearchProductTestData.json");
		return new Object[][] { { data.get(0) } };

	}
}
