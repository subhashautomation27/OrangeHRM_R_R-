package com.subhash.extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.subhash.Basic.GetSysytemANDBrowserDetails;

public class ExtentReport5 {
	public static WebDriver driver ;
	public static void main(String[] args) throws IOException {

		// Get The Browser Details
		 driver = new ChromeDriver();
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		System.out.println(cap.getBrowserName() + " " + cap.getBrowserVersion());
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/login/");
		driver.findElement(By.id("email")).sendKeys("subashchndrabose");
		driver.findElement(By.id("pass")).sendKeys("login123");
		driver.findElement(By.id("loginbutton")).click();
		String errorMessage=driver.findElement(By.xpath("//div[contains(text(),'The email address or mobile number')]")).getText();
			System.out.println(errorMessage);
		// get the system details

		// System.getProperties().list(System.out);

		ExtentReports report = new ExtentReports();
		
		File subash_failed = new File(
				"/OrangeHRM/captureScreenshot/subash_failed.html");
		File subash_passed = new File(
				"/OrangeHRM/captureScreenshot/subash_passed.html");
		File subash_skipped = new File(
				"/OrangeHRM/captureScreenshot/subash_skipped.html");
		File subash_warning = new File(
				"/OrangeHRM/captureScreenshot/subash_warning.html");
		File fileName = new File("/OrangeHRM/captureScreenshot/subash_All.html");

		ExtentSparkReporter spark_ALL = new ExtentSparkReporter(fileName);

		ExtentSparkReporter spark_Failed = new ExtentSparkReporter(subash_failed);
		spark_Failed.filter().statusFilter().as(new Status[] { Status.FAIL }).apply();

		ExtentSparkReporter spark_Skipped = new ExtentSparkReporter(subash_skipped);
		spark_Skipped.filter().statusFilter().as(new Status[] { Status.SKIP }).apply();

		ExtentSparkReporter spark_passed = new ExtentSparkReporter(subash_passed);
		spark_passed.filter().statusFilter().as(new Status[] { Status.PASS }).apply();

		ExtentSparkReporter spark_warning = new ExtentSparkReporter(subash_warning);
		spark_warning.filter().statusFilter().as(new Status[] { Status.WARNING }).apply();

//Failed-Red
		spark_Failed.config().setTheme(Theme.STANDARD);
		spark_Failed.config().setDocumentTitle("Failed Document");
		spark_Failed.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark_Failed.config().setReportName("subash");
		spark_Failed.config().setCss(".badge-primary{background-color:#df6565}");

//PAssed-Green
		spark_passed.config().setTheme(Theme.STANDARD);
		spark_passed.config().setDocumentTitle("passed document");
		spark_passed.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark_passed.config().setReportName("subash chandra bose");
		spark_Failed.config().setCss(".badge-primary{background-color:#80df65}");

//Skipped-pink
		spark_Skipped.config().setTheme(Theme.STANDARD);
		spark_Skipped.config().setDocumentTitle("Skipped Document");
		spark_Skipped.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark_Skipped.config().setReportName("subash chandra bose");
		spark_Failed.config().setCss(".badge-primary{background-color:#dfdfdfe0}");

//warning-yellow
		spark_warning.config().setTheme(Theme.STANDARD);
		spark_warning.config().setDocumentTitle("Warning Document");
		spark_warning.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark_warning.config().setReportName("subash chandra bose");
		spark_Failed.config().setCss(".badge-primary{background-color:#dfcc65}");

//All Tests-default blue
		spark_ALL.config().setTheme(Theme.STANDARD);
		spark_ALL.config().setDocumentTitle("All Tests Document");
		spark_ALL.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark_ALL.config().setReportName("subash chandra bose");
		
		
		//  pass=2 , fail=3,skip=1, warning-1

		report.createTest("Test1", "My First test").assignAuthor("A", "B")
				.assignCategory("smoke", "regression").assignDevice("chrome")
				.log(Status.PASS, "My First test is pass")
				.addScreenCaptureFromBase64String(CaptureScreenshotFromBase64(), "This is BASE64 acebook page");

		/*
		 * report.createTest("Test2", "My second test").assignAuthor("C", "D")
		 * .assignCategory("smoke", "regression").assignDevice("chrome")
		 * .log(Status.FAIL, "My second test is fail")
		 * .addScreenCaptureFromPath(captureScreenshot(), "This is File Facebook page");
		 */

		report.createTest("Test3", "My thrid test").assignAuthor("E", "F")
				.assignCategory("smoke", "regression").assignDevice("chrome")
				.log(Status.SKIP, "My thrid test is skip")
		.addScreenCaptureFromBase64String(CaptureScreenshotFromBase64());

		report.createTest("Test4", "My fourth test").assignAuthor("G", "H")
				.assignCategory("smoke", "regression").assignDevice("chrome")
				.log(Status.WARNING, "My fourth test is warnning");
		/*
		 * report.createTest("Test5", "My fifth test").assignAuthor("I", "J")
		 * .assignCategory("smoke", "regression").assignDevice("chrome")
		 * .log(Status.PASS, "My fifth test is passed")
		 * .addScreenCaptureFromPath(captureScreenshot());
		 */

		/*
		 * report.createTest("Test6", "My sixth test").assignAuthor("K", "L")
		 * .assignCategory("smoke", "regression").assignDevice("chrome")
		 * .log(Status.FAIL, "My sixth test is fail")
		 * .addScreenCaptureFromPath(captureScreenshot());
		 */

		Throwable t = new Throwable("This is unknown exception");
		report.createTest("Test7", "Exception test").fail(t);

		/**
		 * os.name=Windows 10 java.home=C:\Program Files\Java\jdk-17.0.5
		 * java.version=17.0.5 user.name=user
		 * 
		 */

		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("JAVA Version", System.getProperty("java.version"));
		report.setSystemInfo("USER NAME", System.getProperty("user.name"));
		report.setSystemInfo("JAVA HOME", System.getProperty("java.home"));
		report.setSystemInfo("BrowserNmae", cap.getBrowserName());
		report.setSystemInfo("Browser Version", cap.getBrowserVersion());
		report.setSystemInfo("URL", "www.facebook.com");
		report.setSystemInfo("UN", "subash");
		report.setSystemInfo("PW", "subash123");
		

		report.attachReporter(spark_Failed,spark_passed,spark_warning,spark_Skipped,spark_ALL);
		/*
		 * report.attachReporter(spark_passed); report.attachReporter(spark_warning);
		 * report.attachReporter(spark_Skipped); report.attachReporter(spark_ALL);
		 */

		report.flush();
		driver.close();
		/*
		 * Desktop.getDesktop().browse( new File(
		 * "E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash_All.html"
		 * ).toURI());
		 * 
		 * Desktop.getDesktop().browse( new File(
		 * "E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash_failed.html"
		 * ).toURI()); Desktop.getDesktop().browse( new File(
		 * "E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash_passed.html"
		 * ).toURI()); Desktop.getDesktop().browse( new File(
		 * "E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash_skipped.html"
		 * ).toURI()); Desktop.getDesktop().browse( new File(
		 * "E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash_warning.html"
		 * ).toURI());
		 */
		
		Desktop.getDesktop().browse(
				new File("/OrangeHRM/captureScreenshot/subash_All.html").toURI());

		Desktop.getDesktop().browse(
				new File("/OrangeHRM/captureScreenshot/subash_failed.html").toURI());
		Desktop.getDesktop().browse(
				new File("/OrangeHRM/captureScreenshot/subash_passed.html").toURI());
		Desktop.getDesktop().browse(
				new File("/OrangeHRM/captureScreenshot/subash_skipped.html").toURI());
		Desktop.getDesktop().browse(
				new File("/OrangeHRM/captureScreenshot/subash_warning.html").toURI());

		;
	}
	
	
	/*
	 * public static String captureScreenshot() throws IOException { TakesScreenshot
	 * sc= (TakesScreenshot)driver; File src=sc.getScreenshotAs(OutputType.FILE);
	 * File dest=new File(
	 * "E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\captureScreenshot\\"+"sarru.
	 * png"); FileUtils.copyDirectory(src, dest);
	 * System.out.println("FIleSC saved succesfully"); return
	 * dest.getAbsolutePath(); }
	 */
	public static String CaptureScreenshotFromBase64()
	{
		TakesScreenshot sc1=(TakesScreenshot)driver;
		String BaseCODE64=sc1.getScreenshotAs(OutputType.BASE64);
		System.out.println("Base64SC saved succesfully");
		return BaseCODE64; 
	}

	
}
