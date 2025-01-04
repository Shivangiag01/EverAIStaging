package everailabs.Locators;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import everailabs.Abstraction.AbstractMethodClass;

public class UM_RolesPageLocators extends AbstractMethodClass {
	WebDriver driver;
	
	public UM_RolesPageLocators(WebDriver driver) {		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[text()='Roles']")
	WebElement roletab;	
	
	@FindBy(xpath="//button[contains(@class,'css-1ly41nf')]")
	WebElement addrole;
	
	@FindBy(xpath="//div[contains(@class,'css-1fei0dn')]")
	WebElement roleform;
	
	@FindBy(xpath="(//div[contains(@class,'css-p9gxu7')]/div)[2]")
	WebElement header;
	
	@FindBy(xpath="(//div[contains(@class,'css-p9gxu7')]/div)[3]")
	WebElement subheader;
	
	@FindBy(xpath = "//button[contains(@class,'css-19f9g41')]")
	WebElement cross;
	

	@FindBy(xpath = "//input[@name='name']")
	WebElement rolename;

	@FindBy(xpath = "//div[contains(@class,'css-mp9f0v')]")
	WebElement product;
	
	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]")
	WebElement productlistview;
	
	@FindBy(xpath = "//ul[contains(@class,'css-ubifyk')]/li")
	List<WebElement> productlist;
	
	@FindBy(xpath = "//button[contains(@class,'css-8ce5gy')]")
	WebElement createbutton;
	
	@FindBy(css = "div.css-127h8j3")
	WebElement confirmationmsg;
	
	
	
	String name;
	public HashMap<String, String> addRoleForm() {
		roletab.click();
		WebElementVisibleWait(addrole);
		addrole.click();
		WebElementVisibleWait(roleform);
		String headermsg = header.getText();
		String subheadermsg = subheader.getText();
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("header", headermsg);
		map.put("subheader", subheadermsg);	
		cross.click();
		return map;
	}
	
	public void addNewRole(String role) {
		WebElementVisibleWait(addrole);
		addrole.click();
		WebElementVisibleWait(roleform);
		rolename.sendKeys(role);
		product.click();
		WebElementListVisibleWait(productlist);
		if(!productlist.isEmpty()) {
			for(WebElement prod:productlist) {
				prod.click();
				String prodname=prod.getText();
				System.out.println("created for...  "+prodname);
				JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0, 200);");
		        System.out.println("Scrolling");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				js.executeScript("arguments[0].click();", createbutton);
			}
			if(confirmationmsg.getText().equalsIgnoreCase("Role already exists with same name and product")) {
				System.out.println(confirmationmsg.getText());
				cross.click();
			}
		}
		
	}
	
	
	
	
	
	
	
	/*
	
	public List<HashMap<String,String>> createNewRole(String role,String productname) {
		WebElementClickable(addrole);
		addrole.click();
		WebElementVisibleWait(roleform);
		rolename.sendKeys(role);
		product.click();
		WebElementListVisibleWait(productlist);
		
		List<HashMap<String, String>> results = new ArrayList<>();
		
		if (!productlist.isEmpty()) {
		for(WebElement prodname:productlist) {
			prodname.click();
			name= prodname.getText();	        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200);");
        System.out.println("Scrolling");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.stalenessOf(createbutton)); // Wait if the button is stale
        wait.until(ExpectedConditions.visibilityOf(createbutton)); 
        js.executeScript("arguments[0].click();", createbutton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
      
	}	HashMap<String,String> map= new HashMap<String,String>();
		WebElementVisibleWait(confirmationmsg);
		String msg= confirmationmsg.getText();
		if(msg.equalsIgnoreCase("")) {
			
		}else {		
		map.put("confirmmsg", msg);
		map.put("pname", name);	
		results.add(map);
		}
			return results;
	}
	
	
*/
}
