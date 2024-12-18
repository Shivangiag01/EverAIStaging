package everailabs.ReferenceClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicInitialization {
	public WebDriver driver;

	public WebDriver browserSelection() throws IOException {
		Properties prop = new Properties();
		FileInputStream globaldatafile = new FileInputStream(System.getProperty("user.dir") + "src\\main\\resources\\GlobalProp.properties");
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

	@BeforeTest
	public void launchApp() throws IOException {
		driver = browserSelection();
		driver.manage().window().maximize();
		driver.get("");
	}
	
	
	public List<HashMap<String,String>> getJsonData(String filename) throws IOException {
        String jsondata= FileUtils.readFileToString(new File(filename),StandardCharsets.UTF_8);		
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> map= mapper.readValue(jsondata, new TypeReference<List<HashMap<String,String>>>(){});
	    return map;
	}

	
	public String getScreenShot(String testcasename) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		String screenshotfilepath= System.getProperty("user.dir")+"//Reports//"+testcasename+".png";
		File dest= new File(screenshotfilepath);
		FileUtils.copyFile(source, dest);
		return screenshotfilepath;
	}
	
	
	@AfterTest
	public void exit() {
		driver.quit();
	}

}
