package everailabs.EverAIDashboard;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import everailabs.Locators.UM_UsersLocators;
import everailabs.ReferenceClasses.BasicInitialization;

public class UM_UIValidations extends BasicInitialization {

	@Test
	public void tc_001_verifyUIElements() {
		UM_UsersLocators ul = new UM_UsersLocators(driver);
		HashMap<String, String> value = ul.verifyHeaderandMenuUIElements();
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
	public void tc_002_verifyTableElements() {
		UM_UsersLocators ul = new UM_UsersLocators(driver);
		 ul.verifyTableElements();
		 
	}
}
