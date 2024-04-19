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
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LOGLevelSC_ExtentReport5 {

	public static WebDriver driver;

	public static void main(String[] args) throws IOException {
			
		String title=testFacebookTitle();
		System.out.println(title);
		
		File file = new File("Report1.html");

		ExtentReports extentReport = new ExtentReports();

		ExtentSparkReporter spark = new ExtentSparkReporter(file);
		extentReport.attachReporter(spark);

		String base64=captureScreenshot();
		String path1=captureScreenshot("Takescreen123");

		extentReport
		.createTest("Test1", "This is test1")
		.info("This base64 test")
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64, "This is base64 test1").build());
		
		
		
		extentReport
		.createTest("Test1", "This is test1")
		.info("This base64 test")
		.fail(MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
		
		

		extentReport.flush();
		driver.quit();
		Desktop.getDesktop().browse(file.toURI());

	}

	public  static String captureScreenshot(String Name) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File src=tss.getScreenshotAs(OutputType.FILE);
		File dest=new File("E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\captureScreenshot\\"+Name+".png");
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
	
	public static String testFacebookTitle()
	{
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.facebook.com/login/");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		String expectedTitle = "1Log in to Facebook1";

		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, actualTitle, "Title mismatched");

		return actualTitle;

	}


}
