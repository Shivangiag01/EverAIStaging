package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.LoginPageLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class ErrorLoginValidation extends BasicInitialization{

	@Test(dataProvider="getData")
	public void tc_001_verifyinvalidEmailLogin(HashMap<String,String> input) {		
		String errormsg= lp.verifyEmailError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(errormsg, "Invalid email address");
	}
	
	@Test(dataProvider="getData1")
	public void tc_002_verifyinvalidcred(HashMap<String,String> input) {		
		String errormsg= lp.verifyCredentialsError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(errormsg, "Invalid username or password");
	}
	
	@Test(dataProvider="getData2")
	public void tc_003_missingfield(HashMap<String,String> input) {		
		boolean buttonstate= lp.verifyMissingFieldValueError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(buttonstate, false);
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(5)},{data.get(6)},{data.get(7)},{data.get(8)},{data.get(9)},{data.get(10)}
		};
	}
	
	@DataProvider
	public Object[][] getData1() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(11)},{data.get(12)},{data.get(13)},{data.get(14)},{data.get(15)}
		};
	}
	
	@DataProvider
	public Object[][] getData2() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(16)},{data.get(17)}
		};
	}
}
