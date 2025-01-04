package everailabs.Locators;

import java.time.Duration;
import java.util.HashMap;
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
		System.out.println("driver is..." + driver);
	}

	@FindBy(xpath = "//div[@class='MuiStack-root css-1yls19f']/div/div/button")
	WebElement adduser;

	@FindBy(xpath = "//div[@role='dialog']")
	WebElement form;

	@FindBy(xpath = "(//div[contains(@class,'css-p9gxu7')]/div)[1]")
	WebElement formheader;

	@FindBy(css = "div.css-p9gxu7 > div:nth-of-type(2)")
	WebElement formheadsubtext;
	
	@FindBy(xpath = "//button[contains(@class,'css-19f9g41')]")
	WebElement cross;
	

	@FindBy(xpath = "//input[@name='first_name']")
	WebElement fname;

	@FindBy(xpath = "//input[@name='last_name']")
	WebElement lname;

	@FindBy(xpath = "//input[@name='email']")
	WebElement email;

	@FindBy(xpath = "(//div[contains(@class,'css-qz50f9')])[1]")
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
	
		
	@FindBy(css="input.css-1iohxmk")
	WebElement calendar;
	
	@FindBy(xpath = "//div[contains(@class,'css-naa195')]/div//button[contains(@class,'css-1fowdqw')]")
	List<WebElement> currentmonthdateList;
		
	@FindBy(xpath = "//input[@name='class_id']")
	WebElement classID;
	
	@FindBy(xpath = "//input[@name='internal_user_id']")
	WebElement internalID;
	
	@FindBy(xpath = "//input[@name='external_user_id']")
	WebElement externalID;

	@FindBy(xpath = "(//div[contains(@class,'css-qz50f9')])[4]")
	WebElement reporting;

	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li[5]")
	WebElement selectuser;

	@FindBy(xpath = "//input[contains(@class,'css-uu1mj6')]")
	WebElement assignrole;

	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/div[1]")
	WebElement firstelement;

	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/div/h6")
	List<WebElement> rolelist;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement button;
	
	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li")
	WebElement edituserbutton;

	@FindBy(xpath = "//div[contains(@class,'css-1xdhyk6')]")
	WebElement utable;
	
	@FindBy(xpath = "//button[contains(@class,'css-rdurn1')]")
	WebElement ellipsismenu;
	
	@FindBy(xpath = "//div[contains(@class,'css-1vouojk')]")
	WebElement userdata;

	
	@FindBy(css = "div.css-127h8j3")
	WebElement confirmationmsg;
	
	
		
	public HashMap<String,String> addUserForm() {
		adduser.click();
		WebElementVisibleWait(form);
		String headermsg = formheader.getText();
		String subheadermsg = formheadsubtext.getText();
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("header", headermsg);
		map.put("subheader", subheadermsg);	
		cross.click();
		return map;
	}

	public String addUserWithMandatoryFields(String first_name, String last_name, String user_email,String reportingTo,String aroles) {
		adduser.click();
		WebElementVisibleWait(form);
		fname.sendKeys(first_name);
		lname.sendKeys(last_name);
		email.sendKeys(user_email);
		if(reportingTo==null) {
		reporting.click();
		WebElementVisibleWait(selectuser);
		selectuser.click();
		}		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		if(aroles==null) {
		assignrole.click();
		Actions act = new Actions(driver);
		act.moveToElement(firstelement).perform();
		WebElementListVisibleWait(rolelist);
		if(!rolelist.isEmpty()) {
			Random random = new Random();
	           int randomIndex = random.nextInt(rolelist.size());
	           WebElement randomElement = rolelist.get(randomIndex);
	           randomElement.click();
		}
		}
		if (button.isEnabled()) {
			button.click();
		}
		WebElementVisibleWait(confirmationmsg);
		return confirmationmsg.getText();
	}
	
	
	public boolean addUserWithMissingMandatoryFields(String first_name, String last_name, String user_email,String reportingTo,String aroles) {
		WebElementVisibleWait(adduser);
		adduser.click();
		WebElementVisibleWait(form);
		fname.sendKeys(first_name);
		lname.sendKeys(last_name);
		email.sendKeys(user_email);
		System.out.println("value of reprting field " + reportingTo);
		if(reportingTo == null) {
		reporting.click();
		WebElementVisibleWait(selectuser);
		selectuser.click();
		}else {
			reporting.sendKeys(reportingTo);
			}		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		if(aroles==null) {
		assignrole.click();
		Actions act = new Actions(driver);
		act.moveToElement(firstelement).perform();
		WebElementListVisibleWait(rolelist);
		if(!rolelist.isEmpty()) {
			Random random = new Random();
	           int randomIndex = random.nextInt(rolelist.size());
	           WebElement randomElement = rolelist.get(randomIndex);
	           randomElement.click();
		}
		}else {
			assignrole.sendKeys(aroles);
			}
		cross.click();
		return (button.isEnabled());		
	}

	
	public String addUserWithOptionalFields(String first_name, String last_name, String user_email,String contact,String date,String div,String classid,String did, String deptid) {
		adduser.click();
		WebElementVisibleWait(form);
		fname.sendKeys(first_name);
		lname.sendKeys(last_name);
		email.sendKeys(user_email);
		/*
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", stdcode);			
	    if (!countryCodeList.isEmpty()) {
	           Random random = new Random();
	           int randomIndex = random.nextInt(countryCodeList.size());
	           WebElement randomElement = countryCodeList.get(randomIndex);
	           randomElement.click();
	         
	    }else {
	        System.out.println("No country codes found in the dropdown.");
	    }	
	    */
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
		if(!rolelist.isEmpty()) {
			Random random = new Random();
	           int randomIndex = random.nextInt(rolelist.size());
	           WebElement randomElement = rolelist.get(randomIndex);
	           randomElement.click();
		}
		if (button.isEnabled()) {
			button.click();
		}
		WebElementVisibleWait(confirmationmsg);
		String msg = confirmationmsg.getText();
		return msg;
	}

	
	public String editUser(String first_name, String last_name, String user_email,String contact,String date,String div,String classid,String did, String deptid) {
		WebElementVisibleWait(userdata);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ellipsismenu);
		WebElementVisibleWait(ellipsismenu);
		System.out.println("here");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		js.executeScript("arguments[0].click();", ellipsismenu);
		System.out.println("ellipsis menu clicked");
		WebElementVisibleWait(edituserbutton);
		js.executeScript("arguments[0].click();", edituserbutton);		
		System.out.println("edit button menu clicked");
		WebElementVisibleWait(form);
		fname.clear();
		lname.clear();
		classID.clear();
	    internalID.clear();
	    externalID.clear();
		fname.sendKeys(first_name);
		lname.sendKeys(last_name);
		if(email.isEnabled()) {
		email.sendKeys(user_email);
		}else {System.out.println("email field can't be edited");}		
		js.executeScript("arguments[0].click();", stdcode);			
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
