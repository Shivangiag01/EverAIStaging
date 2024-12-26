package everailabs.Locators;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import everailabs.Abstraction.AbstractMethodClass;

public class LoginPageLocators extends AbstractMethodClass {
     WebDriver driver;
   
	
	public LoginPageLocators(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbtn;	
	
	@FindBy(css="span.css-b58x00")
	WebElement head;
	
	@FindBy(css="span.css-zp900n")
	WebElement headsub;
	
	@FindBy(css="img.css-1fk4y32")
	WebElement img;
	
	@FindBy(css="img.css-10h6mrw")
	WebElement logoimg;
	
	@FindBy(xpath="(//div[@class='MuiStack-root css-1mnjkd']/div)[1]")
	WebElement loggedinuser;
	
	@FindBy(css="p.css-10u41sz")
	WebElement invalidemail;
	
	@FindBy(css="p.css-1afwgxb")
	WebElement invalidcredentials;
	
	public HashMap<String,String> verifyLoginPageElements() {
		String h1= head.getText();
		String h2= headsub.getText();	
		String imgurl= img.getDomProperty("src");	
		String logourl= img.getDomProperty("src");
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("header1", h1);
		map.put("header2", h2);
		map.put("imageurl", imgurl);
		map.put("logoimgurl", logourl);
		return  map;
	}
	
	public String verifyLogin(String useremail, String pass) {		
		email.sendKeys(useremail);
		password.sendKeys(pass);
		if(loginbtn.isEnabled()) {
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", loginbtn);
		WebElementVisibleWait(loggedinuser);		
	}
		return loggedinuser.getText();
	}
	
	public String verifyEmailError(String useremail, String pass) {
		email.sendKeys(useremail);
		password.sendKeys(pass);
		WebElementVisibleWait(invalidemail);
		String invalidemailmsg=invalidemail.getText();
		return invalidemailmsg;
	}
	
	public String verifyCredentialsError(String useremail, String pass) {
		email.sendKeys(useremail);
		password.sendKeys(pass);
		if(loginbtn.isEnabled()) {
			  JavascriptExecutor js= (JavascriptExecutor)driver;
			  js.executeScript("arguments[0].click();", loginbtn);
			  WebElementVisibleWait(invalidcredentials);
	}
		return invalidcredentials.getText();
	}
	
	public boolean verifyMissingFieldValueError(String useremail, String pass) {
		email.sendKeys(useremail);
		password.sendKeys(pass);
		return loginbtn.isEnabled();			 
	}
		
	
	
}
