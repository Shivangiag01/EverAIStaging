package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.UM_RolesPageLocators;
import everailabs.Locators.UM_UsersPageLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class UserManagementTest extends BasicInitialization {

	@Test(dataProvider="getUserData")
	public void tc_001_verifyAddUserWithMandatoryFields(HashMap<String, String> input) {
		UM_UsersPageLocators umr = new UM_UsersPageLocators(driver);
		String finalmsg= umr.addUserWithMandatoryFields(input.get("firstname"),input.get("lastname"),input.get("email"));		
		Assert.assertEquals(finalmsg, "New User Added " +"'"+ input.get("firstname")+ " " + input.get("lastname")+"'", 
				"The final message does not match the expected message.");			
	}
	
	
	@Test(dataProvider="getUserDataOptional")
	public void tc_002_verifyAddUserWithOptionalFields(HashMap<String, String> input) {
		UM_UsersPageLocators umr = new UM_UsersPageLocators(driver);
		String finalmsg= umr.addUserWithOptionalFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("contactno"),input.get("date"),input.get("division"),input.get("classID"),input.get("internalID"),input.get("externalID"));		
		Assert.assertEquals(finalmsg, "New User Added " +"'"+ input.get("firstname")+ " " + input.get("lastname")+"'", 
				"The final message does not match the expected message.");	
	}
	
	
	
	@DataProvider
	public Object[][] getUserData() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata.json");
		return new Object[][] {
			{data.get(0)}
		};		
	}
	
	
	@DataProvider
	public Object[][] getUserDataOptional() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata_optionalfields.json");
		return new Object[][] {
			{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}
		
		};		
	}
}
