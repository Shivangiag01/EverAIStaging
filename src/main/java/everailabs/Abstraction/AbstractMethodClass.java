package everailabs.Abstraction;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMethodClass {
	WebDriver driver;
	
	public AbstractMethodClass(WebDriver driver) {
		this.driver=driver;
	}

	public void WebElementVisibleWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void WebElementListVisibleWait(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public void WebElementListVisibleLongWait(By locator) {
		Wait<WebDriver> fluentWait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(25))
			    .pollingEvery(Duration.ofMillis(500));
	fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	
	public void WebElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void WebElementInvisibleWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void WebLocatorVisibleWait(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void WebLocatorInvisibleWait(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	
	// Utility method to wait for rows to load
		protected void waitForRowsToLoad(By locator) {
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}

	
}
