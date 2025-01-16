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

public class UM_RolesLocators extends AbstractMethodClass {
	WebDriver driver;
	
	public UM_RolesLocators(WebDriver driver) {		
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
	
	@FindBy(xpath="(//div[contains(@class,'css-p9gxu7')]/div)[1]")
	WebElement header;
	
	@FindBy(xpath="(//div[contains(@class,'css-p9gxu7')]/div)[2]")
	WebElement subheader;
	
	@FindBy(xpath = "//button[contains(@class,'css-19f9g41')]")
	WebElement cross;
	

	@FindBy(xpath = "//input[@name='name']")
	WebElement rolename;

	@FindBy(css = "div.css-qz50f9")
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
	
	public HashMap<String,String> addNewRole(String role) {
		String prodname=null;
		WebElementVisibleWait(addrole);
		addrole.click();
		WebElementVisibleWait(roleform);
		rolename.sendKeys(role);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		product.click();
		WebElementListVisibleWait(productlist);
		if(!productlist.isEmpty()) {
			for(WebElement prod:productlist) {
				prod.click();
				prodname=prod.getText();
				System.out.println("creating for...  "+prodname);
				JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0, 200);");
		        System.out.println("Scrolling");
				js.executeScript("arguments[0].click();", createbutton);
				product.click();
	            WebElementListVisibleWait(productlist);
			}
		}
		String fmsg=confirmationmsg.getText();
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("role_name", role);
		map.put("prod_name", prodname);
		map.put("msg", fmsg);		
		return 	map;	
	}
	
}
