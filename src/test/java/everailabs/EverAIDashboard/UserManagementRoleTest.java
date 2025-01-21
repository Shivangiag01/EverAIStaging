package everailabs.EverAIDashboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import everailabs.Locators.UM_RolesLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class UserManagementRoleTest extends BasicInitialization{
	
	
	@BeforeMethod
	public void initializeClass() {
		umr= new UM_RolesLocators(driver);
	}
	
	@Test
	public void tc_001_verifyColumnsELements() {		
		List<String> columnName= umr.verifyColumnName();
		Assert.assertEquals(columnName.get(0),"Role");
		Assert.assertEquals(columnName.get(1),"Product");
		Assert.assertEquals(columnName.get(2),"Assigned Users");
		Assert.assertEquals(columnName.get(3),"Created On");
		Assert.assertEquals(columnName.get(4),"Created By");
		
	}
	
	@Test(dataProvider="getSerachString")
	public void tc_002_verifySearchWithRoleName(HashMap<String, String> input) {
		List<String> matchedrows= umr.verifySearchWithRoleName(input.get("searchstring"));
		for(String matchedrow:matchedrows) {
			Assert.assertTrue(matchedrow.toLowerCase().contains(input.get("searchstring").toLowerCase().trim()), "Value did not matched");
		}
	}
	
	@Test
	public void tc_003_verifyAddRoleFormElements() {
		HashMap<String,String> map= umr.addRoleForm();
		Assert.assertEquals(map.get("header"), "Create New Role");
		Assert.assertEquals(map.get("subheader"), "Create new role to manage access for different products.");
	}

	
	@Test(dataProvider="getRoleData")
	public void tc_004_verifyAddNewRole(HashMap<String, String> input) {
		HashMap<String,String> map= umr.addNewRole(input.get("rolename"));
		Assert.assertEquals(map.get("msg"), "New Role Added Successfully", "The final message does not match the expected message.");	
	}
	
	
	@Test(dataProvider="getRoleData")
	public void tc_005_VerifyErroronExistingRole(HashMap<String, String> input) {
		HashMap<String,String> map= umr.addNewRole(input.get("rolename"));
		Assert.assertEquals(map.get("msg"), "Role already exist with name '"+map.get("role_name")+"' and product '"+map.get("prod_name")+"'", "The final message does not match the expected message.");	
	}
	
	@Test
	public void tc_006_VerifyEditRole() {
		HashMap<String,String> map=umr.editRole("Refactoring");	
		Assert.assertEquals(map.get("msg"), "Role Updated Successfully", "The final message does not match the expected message.");	
		}
	
	
	@Test
	public void tc_007_VerifyErrorForEditingRoleWithExistingRoleName() {
		HashMap<String,String> map=umr.editRole("Testing");	
		Assert.assertEquals(map.get("msg"), "Role already exists with name '"+map.get("role_name")+"' and product '"+map.get("prod_name")+"'", "The final message does not match the expected message.");	
		}
	
	@DataProvider
	public Object[][] getSerachString() throws IOException {
	List<HashMap<String, String>> inputdata= getJsonData(System.getProperty("user.dir")+"\\src\\main\\resources\\rolesearchelement.json");
	Object[][] data = new Object[inputdata.size()][1]; 

    for (int i = 0; i < inputdata.size(); i++) {
        data[i][0] = inputdata.get(i);
    }
    return data;	
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
