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

	@Test(dataProvider="getData1")
	public void tc_001_verifyInvalidEmailLogin(HashMap<String,String> input) {		
		String errormsg= lp.verifyEmailError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(errormsg, "Invalid email address");
	}
	
	@Test(dataProvider="getData2")
	public void tc_002_verifyInvalidCred(HashMap<String,String> input) {		
		String errormsg= lp.verifyInvalidCredentialsError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(errormsg, "Invalid Username or Password");
	}
	
	@Test(dataProvider="getData3")
	public void tc_003_MissingFieldError(HashMap<String,String> input) {		
		boolean buttonstate= lp.verifyMissingFieldValueError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(buttonstate, false);
	}
	
	
	@Test(dataProvider="getData4")
	public void tc_004_verifyInactiveAccount(HashMap<String,String> input) {		
		String errormsg= lp.verifyInactiveAccountError(input.get("useremail"), input.get("password"));	
		Assert.assertEquals(errormsg, "User account is not yet activated");
	}
	
	
	@DataProvider
	public Object[][] getData1() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(5)},{data.get(6)},{data.get(7)},{data.get(8)},{data.get(9)},{data.get(10)}
		};
	}
	
	@DataProvider
	public Object[][] getData2() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(11)},{data.get(12)},{data.get(13)},{data.get(14)},{data.get(15)}
		};
	}
	
	@DataProvider
	public Object[][] getData3() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(16)},{data.get(17)}
		};
	}
	
	
	@DataProvider
	public Object[][] getData4() throws IOException {
		List<HashMap<String,String>> data=getJsonData(System.getProperty("user.dir") + "\\src\\main\\resources\\logincreds.json");
		return new Object[][] {
			{data.get(18)}
		};
	}
}
