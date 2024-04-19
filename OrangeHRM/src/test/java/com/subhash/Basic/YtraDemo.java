package com.subhash.Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class YtraDemo  extends Basetest{
	
	
	@Test
	public void test1() throws InterruptedException
	{
	
	WebElement depatureFrom=driver.findElement(By.id("BE_flight_origin_city"));
	WebElement arrival=driver.findElement(By.id("BE_flight_arrival_city"));
	javaScriptType(depatureFrom, 10, "Chennai");
	Thread.sleep(5000);
	javaScriptType(arrival, 10, "Goa");
	Thread.sleep(5000);
	
	
	}
	
	@Test
	public void verifyYatraTitle()
	{
		String actualTitle=driver.getTitle();
		String expectedTitle="Flight, Cheap Air Tickets , Hotels, Holiday, Trains Package Booking - Yatra.com";
		
		if(actualTitle.equalsIgnoreCase(expectedTitle))
		  System.out.println("Title matched for yatra application");
		else
			System.out.println("Titile mismatched");
		
	}
	
	
	
	


}
