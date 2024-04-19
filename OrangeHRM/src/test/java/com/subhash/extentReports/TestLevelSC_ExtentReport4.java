package com.subhash.extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestLevelSC_ExtentReport4 {

	public static WebDriver driver;

	public static void main(String[] args) throws IOException {

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/login/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String expectedTitle = "Log in toFacebook";

		String actualTitle = driver.getTitle();
		
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("Both titles are equals");
		}
		else
		{
			System.out.println("both titles are not equals");
		}

		System.out.println("done");

		File file = new File("AllData.html");

		ExtentReports extentReport = new ExtentReports();

		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		extentReport.attachReporter(spark);

		String base64=captureScreenshot();
		String path1=captureScreenshot1();

		extentReport.createTest("Test1")
		.log(Status.PASS, "Test1 passed")
		.addScreenCaptureFromBase64String(base64);
		
		extentReport.createTest("Test2")
		.log(Status.FAIL, "Test2 failes")
		.addScreenCaptureFromBase64String(base64," facebook FaileBase64 test2");
		
		extentReport.createTest("Test3")
		.log(Status.SKIP, "Test3 skipped")
		.addScreenCaptureFromPath(path1);
		
		extentReport.createTest("Test4")
		.log(Status.FAIL, "Test4 failes")
		.addScreenCaptureFromPath(path1, "facebook failedPath test4");


		extentReport.flush();
		driver.quit();
		//Desktop.getDesktop().browse(file.toURI());

	}

	public  static String captureScreenshot1() {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File src=tss.getScreenshotAs(OutputType.FILE);
		File dest=new File("Testing.jpg");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return dest.getAbsolutePath();
	}
	
	public static String captureScreenshot() {
		TakesScreenshot tss = (TakesScreenshot) driver;
		String base64Code = tss.getScreenshotAs(OutputType.BASE64);
		return base64Code;
	}


}
