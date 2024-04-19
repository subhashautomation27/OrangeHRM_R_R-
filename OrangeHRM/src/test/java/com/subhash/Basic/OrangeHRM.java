package com.subhash.Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OrangeHRM extends Basetest{

@Parameters({"UN","PW"})
	@Test(priority = 1,enabled = true,testName = "LoginDetails")
	public void LoginDetails(String UN,String PW) {
		WebElement userName = driver.findElement(By.name("username"));
		WebElement PassWord = driver.findElement(By.name("password"));
		WebElement btn = driver.findElement(By.xpath("//button[@type='submit']"));
		sendKeys(userName, UN);
		sendKeys(PassWord, PW);
		clickOnBtn(btn);
		String expectedTitile = "OrangeHRM";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(expectedTitile, actualTitle, "Title is miss matched");
		System.out.println("done");
		

	}

	@Test(priority = 2, dependsOnMethods = {"LoginDetails"},enabled =true ,testName = "verifYMyInfo")
	public void verifYMyInfo() throws InterruptedException {

		WebElement myInfolink = driver.findElement(By.xpath("//span[text()='My Info']"));

		jseClick(myInfolink);

		WebElement firstName = driver.findElement(By.xpath("//input[@name='firstName']"));
		WebElement middleName = driver.findElement(By.xpath("//input[@name='middleName']"));
		WebElement lastName = driver.findElement(By.xpath("//input[@name='lastName']"));

		// moveToElement(myInfolink);

		Thread.sleep(3000);
		// clickOnBtn(myInfolink);
		Thread.sleep(3000);

		String fNmae = getText(firstName);
		String mNmae = getText(middleName);
		String lNmae = getText(lastName);
		System.out.println("FIRSTNAME" + fNmae);
		System.out.println("MIDDLENAME" + mNmae);
		System.out.println("LASTNAME" + lNmae);

		/*
		 * Thread.sleep(3000); WebElement myInfolink =
		 * driver.findElement(By.xpath("(//button[@type='button'])[6]"));
		 * clickOnBtn(myInfolink);
		 */

	}

	@Parameters({"UN","PW"})
	@Test(priority = 3,enabled = true,testName ="verifyAmdinDetails" )
	public void verifyAmdinDetails(String UN,String PW) throws InterruptedException {
		
		WebElement userName = driver.findElement(By.name("username"));
		WebElement PassWord = driver.findElement(By.name("password"));
		WebElement btn = driver.findElement(By.xpath("//button[@type='submit']"));
		sendKeys(userName, UN);
		sendKeys(PassWord, PW);
		clickOnBtn(btn);
		
		WebElement myInfolink = driver.findElement(By.xpath("//span[text()='Admin']"));
		jseClick(myInfolink);
		scroolBy();
		
		
		  WebElement clickCheckBox=driver.findElement(By.xpath(
		  "(//input[@type='checkbox'])[last()]")); WebElement
		  clickPencilIcon=driver.findElement(By.xpath(
		  "(//button[@type='button'])[last()]")); WebElement
		  clickndChecbox=driver.findElement(By.xpath("//input[@type='checkbox']"));
		 
		
		
		  Thread.sleep(3000); scroolBy(); jseClick(clickCheckBox);
		  jseClick(clickPencilIcon); Thread.sleep(3000); jseClick(clickndChecbox);
		 
		
		
		

	}

	

	public void sendKeys(WebElement ele, String UserName) {
		ele.sendKeys(UserName);
	}

	public void clickOnBtn(WebElement ele) {
		ele.click();
	}

	public String getText(WebElement ele) {
		return ele.getAttribute("value");
	}

	public void moveToElement(WebElement ele) {
		Actions ac = new Actions(driver);
		ac.moveToElement(ele).click().build().perform();

	}

	public void jseClick(WebElement ele) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click()", ele);
	}
	
	public void scroolBy()
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,500)");
	}
	

}
