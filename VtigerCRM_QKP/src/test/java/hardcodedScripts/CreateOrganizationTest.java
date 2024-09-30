package hardcodedScripts;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationTest {
public static void main(String[] args) {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://localhost:8888");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
	driver.findElement(By.xpath("//a[@href='index.php?module=Accounts&action=index']")).click();
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	driver.findElement(By.name("accountname")).sendKeys("iApple");
	driver.findElement(By.name("button")).click();
	driver.findElement(By.name("Delete")).click();
	Alert alert = driver.switchTo().alert();
	alert.accept();
	driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	driver.findElement(By.linkText("Sign Out")).click();
}
}
