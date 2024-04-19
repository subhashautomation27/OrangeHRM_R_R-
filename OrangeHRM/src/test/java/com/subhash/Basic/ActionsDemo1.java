package com.subhash.Basic;

import static org.testng.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ActionsDemo1 
{

	public static WebDriver driver;
	public static String scsubFolder =null;
    
	@Parameters({"URL"})
	@BeforeTest
	public void setup(String URL) {

		String browserName = "chrome";
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		}

		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

	}

	@AfterTest
	public void teardown() {
		driver.close();
	}

	@Test(enabled = true,priority = 1)
	public void moveToElement()
	{

	    WebElement clickable = driver.findElement(By.id("clickable"));
	    new Actions(driver)
	            .moveToElement(clickable)
	            .pause(Duration.ofSeconds(1))
	            .clickAndHold()
	            .pause(Duration.ofSeconds(1))
	            .sendKeys("abc")
	            .perform();

	}
	
	
	@Test(enabled = true,priority = 2)
	public void verifyTitle()
	{
		String expectedTitle="BasicMouseInterfaceTest1";
		String actualTitle =driver.getTitle();
		
		assertEquals(actualTitle, expectedTitle ,"Title mismatched");
		
		System.out.println("success verifititle");
	}
	
	@Test(enabled = true,priority = 3)
	public void verifyCurrentURL()
	{
		String expectedURL="https://www.selenium.dev/selenium/web/mouse_interaction.html";
		String actualURL=driver.getCurrentUrl();
		
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(actualURL, expectedURL ,"URL mismatched");
		System.out.println("done currentURl");
		sa.assertAll();
	}
	 
	@Test(enabled = true,priority = 4)
	public void dargAndDropTest() throws InterruptedException
	{

		WebElement dragEle	=driver.findElement(By.id("draggable"));
		WebElement dropEle	=driver.findElement(By.id("droppable"));
		Actions ac=new Actions(driver);
		/*
		 * ac.clickAndHold(dragEle).moveToElement(dragEle).release().build().perform();
		 * Thread.sleep(3000);
		 */
		ac.dragAndDrop(dragEle, dropEle);
		System.out.println(driver.findElement(By.xpath("//*[@id='drop-status']")).isDisplayed());
		System.out.println("DargAndDrop done");
	}
	
	@AfterMethod
public void screenshot(ITestResult result) throws IOException
{
	if(result.getStatus()==ITestResult.FAILURE)
	{
		captureScreenshot(result.getName());
	}
}

	public void captureScreenshot(String Name) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File("./ScreenDemo1/"+ Name +".png");
		
		FileUtils.copyFile(src, dest);
		
	}
    

}
