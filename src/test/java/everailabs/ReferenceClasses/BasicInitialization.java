package everailabs.ReferenceClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import everailabs.Locators.LoginPageLocators;
import everailabs.Locators.UM_RolesLocators;
import everailabs.Locators.UM_UsersLocators;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicInitialization {
	public static WebDriver driver;
	private static boolean sharedBrowserSession = false;
	protected LoginPageLocators lp;
	protected UM_UsersLocators um;
	protected UM_RolesLocators umr;

	public WebDriver browserSelection() throws IOException {
		Properties prop = new Properties();
		FileInputStream globaldatafile = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\GlobalProp.properties");
		prop.load(globaldatafile);
		String browsername = prop.getProperty("browser");
		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		return driver;
	}

	@BeforeMethod
	public void launchApp() throws IOException {
		if (driver == null || !sharedBrowserSession) {
			driver = browserSelection();
			driver.manage().window().maximize();
			driver.get("https://green-cliff-0a000fe0f-preview.eastus2.4.azurestaticapps.net");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			lp = new LoginPageLocators(driver);
		}
	}

	public List<HashMap<String, String>> getJsonData(String filename) throws IOException {
		String jsondata = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> map = mapper.readValue(jsondata,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return map;
	}

	public String getScreenShot(String testcasename) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    String timestamp = LocalDateTime.now().format(formatter);
	    	File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotfilepath = System.getProperty("user.dir") + "//Reports//" + testcasename + "_" + timestamp
				+ ".png";
		File dest = new File(screenshotfilepath);
		FileUtils.copyFile(source, dest);
		return screenshotfilepath;
	}

	@AfterMethod(alwaysRun = true)
	public void exit() {
		if (!sharedBrowserSession && driver != null) {
			System.out.println("Closing browser...");
			driver.quit();
			driver = null; // Reset driver to ensure a fresh instance
		}
	}
	
	public  void enableSharedBrowserSession() {
		sharedBrowserSession = true;
	}

	public void disableSharedBrowserSession() {
		sharedBrowserSession = false;
	}

	
	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
	    if (driver != null) {
	        System.out.println("Closing browser after suite...");
	        driver.quit();
	        driver = null;
	        sharedBrowserSession = false;
	    }
	}
	
}
