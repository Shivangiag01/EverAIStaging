package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.LoginPageLocators;
import everailabs.Locators.UM_UsersLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class LoginValidation extends BasicInitialization{

	@Test
	public void tc_001_VerifyLoginPageElements() {
		HashMap<String,String> map= lp.verifyLoginPageElements();
		Assert.assertEquals(map.get("header1"), "Let's Start");
		Assert.assertEquals(map.get("header2"), "Enter your Email to Login");
		Assert.assertEquals(map.get("imageurl"), "/assets/backgroundLogin-DyvrcmwL.svg");
		Assert.assertEquals(map.get("logoimgurl"), "/assets/logo-L894aYV5.svg");
	}
	
	
	@Test(dataProvider="getData")
	public void tc_002_verifyloginWithValidCreds(HashMap<String,String> input) {		
		String loggeinuser= lp.verifyLogin(input.get("useremail"), input.get("password"));
		Assert.assertEquals(loggeinuser, input.get("username"));	
		disableSharedBrowserSession();
	}
	
	
	@Test(dataProvider="getData1")
	public void tc_003_login(HashMap<String,String> input) {		
		String loggeinuser= lp.verifyLogin(input.get("useremail"), input.get("password"));
		Assert.assertEquals(loggeinuser, input.get("username"));
		enableSharedBrowserSession();
		//um = new UM_UsersPageLocators(driver);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)}
		};
	}
	
	@DataProvider
	public Object[][] getData1() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(0)}
			
		};
	}
}
