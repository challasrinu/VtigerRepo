package Generic_Utilities;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic_Utilities.JavaUtility;

public class WebDriverUtility {
	Actions actions;
	WebDriverWait wait;
WebDriver driver;
/**
 * This method launches the user desired browser
 * @param browser
 * @return webDriver
 */
public WebDriver launchBrowser(String browser) {
	if(browser.equalsIgnoreCase("chrome"))
		driver=new ChromeDriver();
	else if(browser.equalsIgnoreCase("firefox"))
		driver=new FirefoxDriver();
	else if(browser.equalsIgnoreCase("Edge"))
		driver=new EdgeDriver();
	else
		System.out.println("Invalid Browser");
	return driver;
}
/**
 * This method maximizes the browser window
 */
public void maximizeBrowser() {
	driver.manage().window().maximize();
}
/**
 * This method navigates to an application
 * @param url
 */
public void navigateToApp(String url) {
	driver.get(url);
}
/**
 * This method waits till element or elements are found
 * @param time
 */
public void waittillElementFound(long time) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));	
}
/**
 * This method waits till the element is displayed on the webPage
 * @param time
 * @param element
 * @return
 */
public WebElement explicitWait(long time, WebElement element) {
	new WebDriverWait(driver,Duration.ofSeconds(time));
	return wait.until(ExpectedConditions.visibilityOf(element));
}
/**
 * This method waits till the element is enabled to receive click
 * @param time
 * @param element
 * @return WebElement
 */
public WebElement explicitwait(long time, By element) {
	 wait = new WebDriverWait(driver,Duration.ofSeconds(time));
	 return wait.until(ExpectedConditions.elementToBeClickable(element));
}
/**
 * This method waits till title of the web page appears
 * @param time
 * @param element
 * @param title
 * @return Boolean
 */
public Boolean explicittwait(long time,String title) {
	wait=new WebDriverWait(driver,Duration.ofSeconds(time));
	return wait.until(ExpectedConditions.titleContains(title));
}
/**
 * This method is used to mouse hover on an element
 * @param element
 */
public void mouseHover(WebElement element) {
	 actions = new Actions(driver);
   actions.moveToElement(element).perform();	
}
/**
 * This method is used to perform double click on an element
 * @param element
 */
public void doubleClickonElement(WebElement element) {
	actions =new Actions(driver);
	actions.doubleClick(element).perform();
}
/**
 * This method is used to right click on an element
 * @param element
 */
public void rightClick(WebElement element) {
	actions =new Actions(driver);
	actions.contextClick(element).perform();
}
/**
 * This method is used to drag and drop an element to destination
 * @param element
 * @param dest
 */
public void dragAndDropAnElement(WebElement element, WebElement dest) {
	actions =new Actions(driver);
	actions.dragAndDrop(element, dest).perform();
}
/**
 * This method selects an option from drag down based on index
 * @param element
 * @param index
 */
public void handleDropDown(WebElement element, int index) {
	Select select=new Select(element);
	select.selectByIndex(index);
}
/**
 * This method selects an option from drop down based on value attribute
 * @param element
 * @param Value
 */
public void handleDropDown(WebElement element, String Value) {
	Select select=new Select(element);
	select.selectByValue(Value);
}
/**
 * This method selects an option from drag and drop based on visible text
 * @param Text
 * @param element
 */
public void handleDropDown(String Text,WebElement element) {
	Select select=new Select(element);
	select.selectByVisibleText(Text);
}
/**
 * This method is used to switch to frame based on frame index
 * @param index 
 * @param index
 */
public void switchToFrame(int index) {
	driver.switchTo().frame(index);
}
/**
 * This method is used to switch to frame based on frame element
 * @param frameElement
 */
public void switchToFrame(WebElement frameElement) {
	driver.switchTo().frame(frameElement);
}
/**
 * This method is used to switch to frame based on id or name attribute value
 * @param idOrNameAttribute
 */
public void switchToFrame(String idOrNameAttribute) {
	driver.switchTo().frame(idOrNameAttribute);	
}	
/**
 * This method is used to switch to back from home
 */
public void switchBackFromFrame() {
	driver.switchTo().defaultContent();
}
/**
 * This method captures the screenshot of a webpage
 * @param driver
 * @return String
 */
	public String getScreenshot(WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}
	public String getScreenshot(WebDriver driver,String classname, JavaUtility jutil) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots/"+ classname +"_"+jutil.getCurrentTime()+".png");
		try {
			FileUtils.copyFile(temp, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}
	/**
	 * This method scrolls till the element based on element reference
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",element);
	}
	/**
	 * This method scrolls till the element based on element location
	 * @param location
	 */
	public void scrollToElement(Point location) {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+ location.getX()+","+ location.getY()+")");
	}
	/**
	 * this method handles alert popup
	 * @param status
	 */
	public void handleAlert(String status) {
		if (status.equalsIgnoreCase("ok"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
	/**
	 * This method returns current window reference
	 * @return
	 */
	public String getParentWindowID() {
		return driver.getWindowHandle();
	}	
	/**
	 * This method switches to expected window or tab based on window title
	 * @param expectedTitle
	 */
	public void switchToChildwindow(String expectedUrl) {
		Set<String> windowIDs = driver.getWindowHandles();
//		for (String id : windowIDs) {
//			driver.switchTo().window(id);
//			if(driver.getTitle().contains(expectedTitle));
//			driver.switchTo().window(id);
//			break;	
//		}
		Iterator<String> it = windowIDs.iterator();
		while(it.hasNext()) {
			driver.switchTo().window(it.next());
			if(driver.getCurrentUrl().contains(expectedUrl))
				break;
		}
	}
	/**
	 * This method closes the current window
	 */
	public void closeWindow() {
		driver.close();
	}
	/**
	 * This method closes all the windows
	 */
	public void quitAllwindows() {
		driver.quit();
	}
	}

