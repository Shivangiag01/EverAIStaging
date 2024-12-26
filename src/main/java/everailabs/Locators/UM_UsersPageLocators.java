package everailabs.Locators;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import everailabs.Abstraction.AbstractMethodClass;

public class UM_UsersPageLocators extends AbstractMethodClass {
	WebDriver driver;

	public UM_UsersPageLocators(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='MuiStack-root css-1yls19f']/div/div/button")
	WebElement adduser;

	@FindBy(xpath = "//div[@role='dialog']")
	WebElement form;

	@FindBy(css = "div.css-p9gxu7 > div:first-of-type")
	WebElement formheader;

	@FindBy(css = "div.css-p9gxu7 > div:nth-of-type(2)")
	WebElement formheadsubtext;

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement fname;

	@FindBy(xpath = "//input[@name='last_name']")
	WebElement lname;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	@FindBy(xpath = "(//div[contains(@class,'css-mp9f0v')])[1]")
	WebElement stdcode;
	
	@FindBy(xpath = "//input[@name='phone']")
	WebElement phone;
	
	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li")
	 List<WebElement> countryCodeList ;
	
	@FindBy(xpath = "(//div[contains(@class,'css-mp9f0v')])[2]")
	WebElement division;
	
	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li")
	 List<WebElement> divisionList ;
	
	@FindBy(xpath = "(//div[contains(@class,'css-mp9f0v')])[3]")
	WebElement department;
	
	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li")
	 List<WebElement> deptList ;
	
	@FindBy(css="div.css-pgfpy0")
	WebElement dateplaceholder;
	
	@FindBy(css="button.css-15guoxn")
	WebElement calendar;
	
	@FindBy(xpath = "//div[contains(@class,'css-naa195')]/div//button[contains(@class,'css-76a6x1')]")
	List<WebElement> currentmonthdateList;
		
	@FindBy(xpath = "//input[@name='class_id']")
	WebElement classID;
	
	@FindBy(xpath = "//input[@name='internal_user_id']")
	WebElement internalID;
	
	@FindBy(xpath = "//input[@name='external_user_id']")
	WebElement externalID;

	@FindBy(xpath = "(//div[contains(@class,'css-mp9f0v')])[4]")
	WebElement reporting;

	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li[5]")
	WebElement selectuser;

	@FindBy(xpath = "//input[contains(@class,'css-1ooubvk')]")
	WebElement assignrole;

	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/div[1]")
	WebElement firstelement;

	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/div/h6")
	List<WebElement> rolelist;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement button;

	@FindBy(css = "div.css-127h8j3")
	WebElement confirmationmsg;

	public String addUserWithMandatoryFields(String first_name, String last_name, String user_email) {
		adduser.click();
		WebElementVisibleWait(form);
		String headermsg = formheader.getText();
		String subheadermsg = formheadsubtext.getText();
		fname.sendKeys(first_name);
		lname.sendKeys(last_name);
		email.sendKeys(user_email);
		reporting.click();
		WebElementVisibleWait(selectuser);
		selectuser.click();
		String selected_reporting = selectuser.getText();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		assignrole.click();
		Actions act = new Actions(driver);
		act.moveToElement(firstelement).perform();
		WebElementListVisibleWait(rolelist);
		for (WebElement role : rolelist) {
			String rolename = role.getText();
			if (rolename.equalsIgnoreCase("admin")) {
				role.click();
			}
		}
		if (button.isEnabled()) {
			button.click();
		}
		WebElementVisibleWait(confirmationmsg);
		return confirmationmsg.getText();
	}

	
	public String addUserWithOptionalFields(String first_name, String last_name, String user_email,String contact,String date,String div,String classid,String did, String deptid) {
		adduser.click();
		WebElementVisibleWait(form);
		fname.sendKeys(first_name);
		lname.sendKeys(last_name);
		email.sendKeys(user_email);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", stdcode);			
	    if (!countryCodeList.isEmpty()) {
	           Random random = new Random();
	           int randomIndex = random.nextInt(countryCodeList.size());
	           WebElement randomElement = countryCodeList.get(randomIndex);
	           randomElement.click();
	           phone.sendKeys(contact);
	    }else {
	        System.out.println("No country codes found in the dropdown.");
	    }	
	    if(date==null) {
	    calendar.click();
	    WebElementListVisibleWait(currentmonthdateList);
	    if (!currentmonthdateList.isEmpty()) {
	           Random random = new Random();
	           int randomIndex = random.nextInt(currentmonthdateList.size());
	           WebElement randomElement = currentmonthdateList.get(randomIndex);
	           randomElement.click();
	    }else {
	        System.out.println("Date not selected");
	    }	
	    }else {calendar.sendKeys(date);}
	    
	    System.out.println(first_name + "  "+ div);
	    if(div == null) {
	    division.click();
	    if (!divisionList.isEmpty()) {
	           Random random = new Random();
	           int randomIndex = random.nextInt(divisionList.size());
	           WebElement randomElement = divisionList.get(randomIndex);
	           randomElement.click();
	    }else {
	    	System.out.println("No Division found");
	    }
	    department.click();
	    if (!deptList.isEmpty()) {
	           Random random = new Random();
	           int randomIndex = random.nextInt(deptList.size());
	           WebElement randomElement = deptList.get(randomIndex);
	           randomElement.click();
	     }else {
	        System.out.println("No Department found");
	    }
	    }
	    else {
	    	division.sendKeys(div);
	    	System.out.println("div is null so skipping");
	    }
	    classID.sendKeys(classid);
	    internalID.sendKeys(did);
	    externalID.sendKeys(deptid);
	    reporting.click();
		WebElementVisibleWait(selectuser);
		selectuser.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		assignrole.click();
		Actions act = new Actions(driver);
		act.moveToElement(firstelement).perform();
		WebElementListVisibleWait(rolelist);
		for (WebElement role : rolelist) {
			String rolename = role.getText();
			if (rolename.equalsIgnoreCase("admin")) {
				role.click();
			}
		}
		if (button.isEnabled()) {
			button.click();
		}
		WebElementVisibleWait(confirmationmsg);
		String msg = confirmationmsg.getText();
		return msg;
	}


}
