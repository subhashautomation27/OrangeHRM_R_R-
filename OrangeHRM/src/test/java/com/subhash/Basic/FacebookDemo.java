package com.subhash.Basic;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FacebookDemo  extends Basetest{
    
	
	
	@Test
	public void facebookLoginDetails() throws InterruptedException
	{
		WebElement name=driver.findElement(By.id("email"));
		WebElement password=driver.findElement(By.id("pass"));
		String name1=getTimeStamp();
		sendkeys(name, 10, prop.getProperty("userName")+getTimeStamp()+"@gmail.com");
		Thread.sleep(3000);
		sendkeys(password, 10,prop.getProperty("password"));
		Thread.sleep(5000);
		
	}
	
	
	public WebElement waitDuration(WebElement ele, long timeout)
	{  
		WebElement webelement=null;
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeout));
		webelement=wait.until(ExpectedConditions.visibilityOf(ele));
		return webelement;
	}
	
	public void sendkeys(WebElement ele,int i,String name)
	{
		WebElement ele1=waitDuration(ele,i);
		ele1.sendKeys(name);
	}
	
	public static String getTimeStamp()
	{
		Date date=new Date();
		
		return date.toString().replace(":", " _").replace(" ", "_");
		
		
	}

}
