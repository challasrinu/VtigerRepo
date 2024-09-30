package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateNewLead {
public static void main(String[] args) {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("http://localhost:8888");
	if(driver.getTitle().contains("vtiger")) {
		System.out.println("Vtiger LoginPage is displayed");
	}
	else {
		System.out.println("Invalid URL");
		driver.quit();
	}
	driver.findElement(By.name("user_name")).sendKeys("admin");
	driver.findElement(By.name("user_password")).sendKeys("admin");
	driver.findElement(By.id("submitButton")).click();
	if(driver.getTitle().contains("Home")) {
		System.out.println("Vtiger HomePage is displayed");
	}
	else {
		System.out.println("Invalid ceredicials");
		driver.quit();
	}
	driver.findElement(By.linkText("Leads")).click();
	if(driver.getTitle().contains("Leads"))
		System.out.println("Leads Page is displayed");
	else
		System.out.println("Navigated to different Page");
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Srinivas");
	driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Tata");
	driver.findElement(By.xpath("//input[@type='submit']")).submit();
	driver.findElement(By.name("Duplicate")).click();
	driver.findElement(By.xpath("//input[@type='submit']")).submit();
	driver.findElement(By.xpath("//input[@accesskey='D']")).click();
	WebElement adminwidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	Actions actions=new Actions(driver);
	actions.moveToElement(adminwidget).perform();
	 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	   driver.quit();
}
}
