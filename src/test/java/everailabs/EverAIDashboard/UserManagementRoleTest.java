package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.UM_RolesLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class UserManagementRoleTest extends BasicInitialization{
	
	@Test
	public void tc_001_verifyAddRoleFormElements() {
		umr= new UM_RolesLocators(driver);
		HashMap<String,String> map= umr.addRoleForm();
		Assert.assertEquals(map.get("header"), "Create New Role");
		Assert.assertEquals(map.get("subheader"), "Create new role to manage access for different products.");
	}

	
	@Test(dataProvider="getRoleData")
	public void tc_002_verifyAddNewRole(HashMap<String, String> input) {
		umr= new UM_RolesLocators(driver);
		HashMap<String,String> map= umr.addNewRole(input.get("rolename"));
		Assert.assertEquals(map.get("msg"), "New Role Added Successfully", "The final message does not match the expected message.");	
	}
	
	
	@Test(dataProvider="getRoleData")
	public void tc_003_ErroronExistingRole(HashMap<String, String> input) {
		umr= new UM_RolesLocators(driver);
		HashMap<String,String> map= umr.addNewRole(input.get("rolename"));
		Assert.assertEquals(map.get("msg"), "Role already exist with name '"+map.get("role_name")+"' and product '"+map.get("prod_name")+"'", "The final message does not match the expected message.");	
	}
	
	@DataProvider
	public Object[][] getRoleData() throws IOException {
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\addrole.json");
		return new Object[][] {
			{data.get(0)}
			//,{data.get(1)},{data.get(2)}
		};		
	}
}
