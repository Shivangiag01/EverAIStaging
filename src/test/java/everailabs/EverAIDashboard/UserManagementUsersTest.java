package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.UM_RolesLocators;
import everailabs.Locators.UM_UsersLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class UserManagementUsersTest extends BasicInitialization {
	
	@BeforeMethod
	public void initializeClass() {
		um = new UM_UsersLocators(driver);
	}
	
	@Test
	public void tc_001_verifyHeaderandNavigationMenuUIElements() {		
		HashMap<String, String> value = um.verifyHeaderandMenuUIElements();
		Assert.assertEquals(value.get("Name"), "Shivangi Agarwal");
		Assert.assertEquals(value.get("Role"), "Manager");
		Assert.assertEquals(value.get("item1"), "Home");
		Assert.assertEquals(value.get("item2"), "User Management");
		Assert.assertEquals(value.get("item3"), "Reports");
		Assert.assertEquals(value.get("item4"), "Settings");
		Assert.assertEquals(value.get("item5"), "Notifications");
		Assert.assertEquals(value.get("item6"), "Help & Support");
	}
	
	@Test
	public void tc_002_verifyTableElements_ColumnName() {
		um.verifyTableElements_ColumnName();
		 
	}
	
	@Test(dataProvider="getSerachString")
	public void tc_003_verifySearchWithNameOrEmail(HashMap<String, String> input) throws InterruptedException {
		List<String> rows= um.verifySearchWithNameOrEmail(input.get("searchstring"));
		for (String row:rows) {
			System.out.println(input.get("searchstring").toUpperCase().trim());
			Assert.assertTrue(row.toUpperCase().contains(input.get("searchstring").toUpperCase().trim()),"Row does not match search criteria: " + row + "  "+input.get("searchstring").toUpperCase());
		}
		 
	}
	
	@Test
	public void tc_004_verifyAddUserFormHeader() {
		HashMap<String,String> map=	um.addUserForm();
		Assert.assertEquals(map.get("header"), "Add User");
		Assert.assertEquals(map.get("subheader"), "Add users and manage access to their respective products.");
	}
	
	
	@Test(dataProvider="getUserMandatoryFieldData")
	public void tc_005_verifyAddUserWithMandatoryFields(HashMap<String, String> input) {
		String finalmsg= um.addUserWithMandatoryFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("reprtingTo"),input.get("assignroles"));		
		Assert.assertEquals(finalmsg, "New User Added Successfully","The final message does not match the expected message.");			
	}
	
	@Test(dataProvider="getUserDataMissingFields")
	public void tc_006_ErrorValidationWithMissingMandatoryFields(HashMap<String, String> input) {
		Boolean buttonstate= um.addUserWithMissingMandatoryFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("reprtingTo"),input.get("assignroles"));		
		Assert.assertEquals(buttonstate,false);			
	}
	
	@Test(dataProvider="getUserDataOptional")
	public void tc_007_verifyAddUserWithOptionalFields(HashMap<String, String> input) {
		String finalmsg= um.addUserWithOptionalFields(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("countrycode"),input.get("contactno"),input.get("date"),input.get("division"),input.get("classID"),input.get("internalID"),input.get("externalID"));		
		Assert.assertEquals(finalmsg, "New User Added Successfully","The final message does not match the expected message.");	
	}
	
	@Test(dataProvider="editUserData")
	public void tc_008_edituserdetails(HashMap<String, String> input) {
		String finalmsg= um.editUser(input.get("firstname"),input.get("lastname"),input.get("email"),input.get("countrycode"),input.get("contactno"),input.get("date"),input.get("division"),input.get("classID"),input.get("internalID"),input.get("externalID"));		
		Assert.assertEquals(finalmsg, "User Updated Successfully","The final message does not match the expected message.");			
	}
		
	
	
	
	@DataProvider
	public Object[][] getSerachString() throws IOException {
	List<HashMap<String, String>> inputdata= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\searchelement.json");
	Object[][] data = new Object[inputdata.size()][1]; 

    for (int i = 0; i < inputdata.size(); i++) {
        data[i][0] = inputdata.get(i);
    }
    return data;	
	}
	
	
	@DataProvider
	public Object[][] getUserMandatoryFieldData() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata_mandatoryfields.json");
		return new Object[][] {
			{data.get(0)}
		};		
	}
	
	@DataProvider
	public Object[][] getUserDataMissingFields() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\adduserdata_mandatoryfields.json");
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
