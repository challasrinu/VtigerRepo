package hardcodedScripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Create_NewConatct_Existing_Organisation {
public static void main(String[] args) {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://localhost:8888");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	if(driver.getTitle().contains("vtiger CRM")) {
		System.out.println("Vtiger LoginPage is displayed");
	}
	else {
		System.out.println("Invalid URL");
		driver.quit();
	}
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
	driver.findElement(By.id("submitButton")).submit();
	if(driver.getTitle().contains("Home")) {
		System.out.println("Vtiger HomePage is displayed");
	}
	else {
		System.out.println("Invalid ceredicials");
		driver.quit();
	}
	driver.findElement(By.linkText("Contacts")).click();
	if(driver.getTitle().contains("Contacts"))
		System.out.println("Contacts Page is displayed");
	else
	System.out.println("Contacts Page is not displayed");
	driver.quit();
	driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	WebElement pageHeader = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
	if(pageHeader.isDisplayed())
		System.out.println("Creating New Contact Page is Displayed");
	else
		driver.quit();
	driver.findElement(By.name("lastname")).sendKeys("challa");
	driver.findElement(By.xpath("//img[contains(@onclick,'module=Accounts&action')]")).click();
	String parentID = driver.getWindowHandle();
	Set<String> windowIDs = driver.getWindowHandles();
	for (String id : windowIDs) {
		driver.switchTo().window(id);
	}
	driver.findElement(By.xpath("//a[text()='Apple']")).click();
	driver.switchTo().window(parentID);
	driver.findElement(By.xpath("//input[contains(@title,'Save')]")).click();
	String newContactPageHeader = driver.findElement(By.cssSelector("dvHeaderText")).getText();
	if(newContactPageHeader.contains("Apple"))
		System.out.println("Contact created successfully");
	else
		driver.quit();
	driver.findElement(By.xpath("//input[@name='Delete']")).click();
	driver.switchTo().alert().accept();
	if(driver.getTitle().contains("Contacts"))
		System.out.println("Contacts Page is Displayed");
	else
		driver.quit();
	WebElement adminWidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions actions=new Actions(driver);
	actions.moveToElement(adminWidget).perform();
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();
}
}
