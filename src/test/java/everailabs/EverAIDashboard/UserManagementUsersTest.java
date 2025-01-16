package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.UM_RolesLocators;
import everailabs.Locators.UM_UsersLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class UserManagementUsersTest extends BasicInitialization {

	@Test
	public void tc_001_verifyAddUserFormHeader() {
		if (um == null) {
            um = new UM_UsersLocators(driver);
     }
		HashMap<String,String> map=	um.addUserForm();
		Assert.assertEquals(map.get("header"), "Add User");
		Assert.assertEquals(map.get("subheader"), "Add users and manage access to their respective products.");
	}
	
	
	@Test(dataProvider="getUserData")
	public void tc_002_verifyAddUserWithMandatoryFields(HashMap<String, String> input) {
		if (um == null) {
            um = new UM_UsersLocators(driver);
     }
		String finalmsg= um.addUserWithMandatoryFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("reprtingTo"),input.get("assignroles"));		
		Assert.assertEquals(finalmsg, "New User Added Successfully","The final message does not match the expected message.");			
	}
	
	@Test(dataProvider="getUserDataMissingFields")
	public void tc_003_ErrorValidationWithMissingMandatoryFields(HashMap<String, String> input) {
		if (um == null) {
            um = new UM_UsersLocators(driver);
     }
		Boolean buttonstate= um.addUserWithMissingMandatoryFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("reprtingTo"),input.get("assignroles"));		
		Assert.assertEquals(buttonstate,false);			
	}
	
	@Test(dataProvider="getUserDataOptional")
	public void tc_004_verifyAddUserWithOptionalFields(HashMap<String, String> input) {
		if (um == null) {
            um = new UM_UsersLocators(driver);
     }
		String finalmsg= um.addUserWithOptionalFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("countrycode"),input.get("contactno"),input.get("date"),input.get("division"),input.get("classID"),input.get("internalID"),input.get("externalID"));		
		Assert.assertEquals(finalmsg, "New User Added Successfully","The final message does not match the expected message.");	
	}
	
	@Test(dataProvider="editUserData")
	public void tc_005_edituserdetails(HashMap<String, String> input) {
		if (um == null) {
            um = new UM_UsersLocators(driver);
     }
		String finalmsg= um.editUser(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("countrycode"),input.get("contactno"),input.get("date"),input.get("division"),input.get("classID"),input.get("internalID"),input.get("externalID"));		
		Assert.assertEquals(finalmsg, "User Updated Successfully","The final message does not match the expected message.");			
	}
		
	
	
	@DataProvider
	public Object[][] getUserData() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata.json");
		return new Object[][] {
			{data.get(0)}
		};		
	}
	
	@DataProvider
	public Object[][] getUserDataMissingFields() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata.json");
		return new Object[][] {
			{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}
		};		
	}
	
	@DataProvider
	public Object[][] getUserDataOptional() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata_optionalfields.json");
		return new Object[][] {
			{data.get(0)}
			//,{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)},{data.get(5)},{data.get(6)}
		
		};		
	}
	
	@DataProvider
	public Object[][] editUserData() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata_optionalfields.json");
		return new Object[][] {
			{data.get(7)}
		
		};		
	}
}
