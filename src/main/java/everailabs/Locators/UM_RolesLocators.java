package everailabs.Locators;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Roles']")
	WebElement roletab;

	@FindBy(xpath = "//button[contains(@class,'css-1ly41nf')]")
	WebElement addrole;

	@FindBy(xpath = "//div[contains(@class,'css-1fei0dn')]")
	WebElement roleform;

	@FindBy(xpath = "(//div[contains(@class,'css-p9gxu7')]/div)[1]")
	WebElement header;

	@FindBy(xpath = "(//div[contains(@class,'css-p9gxu7')]/div)[2]")
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

	@FindBy(css = "div.MuiDataGrid-columnHeaderDraggableContainer")
	List<WebElement> columnlist;
	
	@FindBy(css = "svg.MuiCircularProgress-svg")
	WebElement loader;
	
	@FindBy(xpath = "//button[@aria-label='Go to next page']")
	List<WebElement> nextButton;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement searchbox;
	
	@FindBy(css="div.MuiDataGrid-row--dynamicHeight")
	List<WebElement> rows;
	
	
	@FindBy(css="div.css-8qhrww")
	WebElement noData;

	public List<String> verifyColumnName() {
		roletab.click();
		WebElementListVisibleWait(columnlist);
		List<String> columnNameList = new ArrayList<>(); // Local list
		System.out.println("here" + columnNameList.size());
		for (WebElement column : columnlist) {
			String columnName = column.getText();
			System.out.println(columnName);
			columnNameList.add(columnName);
		}
		return columnNameList;
	}
		
	public List<String> verifySearchWithRoleName(String input) {
		roletab.click();
		WebElementListVisibleWait(columnlist);
		WebElementVisibleWait(searchbox);
		WebElementListVisibleWait(rows);
		System.out.println("rows are in visible state");
		searchbox.clear();
		searchbox.sendKeys(input);
		searchbox.sendKeys(Keys.RETURN);
		WebElementInvisibleWait(loader);	
		List<String> allMatchingRows = new ArrayList<>();
		boolean hasNextPage = true;
		
				while (hasNextPage) {
			for (WebElement row : rows) {
				String rowData = row.findElement(By.cssSelector("div[data-field='name'] div span")).getText();
				String rowData1 = row.findElement(By.cssSelector("div[data-field='product_module_permissions'] div span")).getText();				
				System.out.println(rowData + " role present for "+rowData1);
				allMatchingRows.add(rowData);
			}
			if (!nextButton.isEmpty() && nextButton.get(0).isEnabled()) {
				System.out.println("Navigating to the next page");
				nextButton.get(0).click();
				WebElementInvisibleWait(loader);
			} else {
				hasNextPage = false; // Stop the loop if no "Next" button or disabled
			}
		}
		
		return allMatchingRows;
		
	}		
		

	String name;
	public HashMap<String, String> addRoleForm() {
		roletab.click();
		WebElementVisibleWait(addrole);
		addrole.click();
		WebElementVisibleWait(roleform);
		String headermsg = header.getText();
		String subheadermsg = subheader.getText();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("header", headermsg);
		map.put("subheader", subheadermsg);
		cross.click();
		return map;
	}

	public HashMap<String, String> addNewRole(String role) {
		String prodname = null;
		WebElementVisibleWait(addrole);
		addrole.click();
		WebElementVisibleWait(roleform);
		rolename.sendKeys(role);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		product.click();
		WebElementListVisibleWait(productlist);
		if (!productlist.isEmpty()) {
			for (WebElement prod : productlist) {
				prod.click();
				prodname = prod.getText();
				System.out.println("creating for...  " + prodname);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0, 200);");
				System.out.println("Scrolling");
				js.executeScript("arguments[0].click();", createbutton);
				product.click();
				WebElementListVisibleWait(productlist);
			}
		}
		String fmsg = confirmationmsg.getText();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("role_name", role);
		map.put("prod_name", prodname);
		map.put("msg", fmsg);
		return map;
	}

}
