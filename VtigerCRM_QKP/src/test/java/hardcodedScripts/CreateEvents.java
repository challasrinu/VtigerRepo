package hardcodedScripts;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateEvents {
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
	WebElement NewEvent = driver.findElement(By.id("qccombo"));
	Select select=new Select(NewEvent);
	select.selectByValue("Events");
	driver.findElement(By.name("subject")).sendKeys("Pawankalyan_Event");
	driver.findElement(By.id("jscal_trigger_date_start")).click();
	int reqStartYear = 2027;
	String reqStartDate="9";
	String reqStartMonth="May";
	String CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
	String[] str = CurrentMonthYear.split(", ");
	int CurrentYear = Integer.parseInt(str[1]);
  while(CurrentYear<reqStartYear) {       
	  driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']")).click();
	  CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
        str = CurrentMonthYear.split(", ");
	   CurrentYear=Integer.parseInt(str[1]);
  }
   int CurrentMonth = DateTimeFormatter
		            .ofPattern("MMMM")
                    .withLocale(Locale.ENGLISH)
                    .parse(str[0])
                    .get(ChronoField.MONTH_OF_YEAR);
   int reqStartMonthInNum = DateTimeFormatter.ofPattern("MMMM")
   .withLocale(Locale.ENGLISH)
   .parse(reqStartMonth)
   .get(ChronoField.MONTH_OF_YEAR);
   while(CurrentMonth<reqStartMonthInNum) {
	   driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='›']")).click();
		  CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
		  str=CurrentMonthYear.split(", ");
		  CurrentMonth = DateTimeFormatter.ofPattern("MMMM")
                  .withLocale(Locale.ENGLISH)
                  .parse(str[0])
                  .get(ChronoField.MONTH_OF_YEAR);
   }
   while(CurrentMonth>reqStartMonthInNum) {
	   driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='‹']")).click();
		  CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
		  str=CurrentMonthYear.split(", ");
		  CurrentMonth = DateTimeFormatter.ofPattern("MMMM")
              .withLocale(Locale.ENGLISH)
              .parse(str[0])
              .get(ChronoField.MONTH_OF_YEAR);
}
   driver.findElement(By.xpath("//div[@class='calendar' and contains(@style, 'block')]/descendant::td[text()='"+reqStartDate+"']")).click();
   driver.findElement(By.id("jscal_trigger_due_date")).click();
  int reqEndYear=2027;
  String reqEndDate="22";
  String reqEndMonth="Septemper";
	  CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
	  str=CurrentMonthYear.split(", ");
	  CurrentYear=Integer.parseInt(str[1]);
	  while(CurrentYear<reqEndYear) {       
	  driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td[text()='»']")).click();
	  CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
			str = CurrentMonthYear.split(", ");
			CurrentYear = Integer.parseInt(str[1]);
	  }
			 CurrentMonth = DateTimeFormatter.ofPattern("MMMM")
	                  .withLocale(Locale.ENGLISH)
	                  .parse(str[0])
	                  .get(ChronoField.MONTH_OF_YEAR);
			 int reqEndMonthInNum = DateTimeFormatter.ofPattern("MMMM")
	                  .withLocale(Locale.ENGLISH)
	                  .parse(str[0])
	                  .get(ChronoField.MONTH_OF_YEAR);
	  
   while(CurrentMonth > reqEndMonthInNum) {
	  driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [text()='‹']")).click();
	  CurrentMonthYear = driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [@class='title']")).getText();
		str = CurrentMonthYear.split(", ");
		 CurrentMonth = DateTimeFormatter.ofPattern("MMMM")
                 .withLocale(Locale.ENGLISH)
                 .parse(str[0])
                 .get(ChronoField.MONTH_OF_YEAR);
   }
   driver.findElement(By.xpath("//div[@class='calendar' and contains(@style,'block')]/descendant::td [text()='"+reqEndDate+"']")).click();
   driver.findElement(By.xpath("//input[@type='submit']")).click();
   WebElement adminwidget = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
   Actions action=new Actions(driver);
   action.moveToElement(adminwidget).perform();
   driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
   driver.quit();
}
			
}

