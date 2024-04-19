package com.subhash.extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class FinalTestForSCUsingBase64AndFile {

	public static WebDriver driver;
	
	public static Properties prop;

	public static void main(String[] args) throws Exception {
		readCongin();
		driver = new ChromeDriver();
		// below snippet code is used to get the browser details-browesrName and browser
		// version
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/login/");
		driver.findElement(By.id("email")).sendKeys("subashchndrabose");
		driver.findElement(By.id("pass")).sendKeys("login123");
		driver.findElement(By.id("loginbutton")).click();
		String actualErrorMessage = driver
				.findElement(By.xpath("//div[contains(text(),'The email address or mobile number')]")).getText();
		String expectedError = "The email address or mobile number you entered isn't connected to an account. Find your account and 123log in.";
		// Assert.assertEquals(actualErrorMessage, expectedError ,"Both are not same");

		if (actualErrorMessage.equals(expectedError))
			System.out.println("both are equals");
		else
			System.out.println("both are not equals");

		// Here multiple reports are generating based on log levels

		ExtentReports reports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("/ExtentReportsSC/subhashchandraboseALL.html");

		ExtentSparkReporter spark_Failed = new ExtentSparkReporter("/ExtentReportsSC/subhashchandrabose_Failed.html");
		spark_Failed.filter().statusFilter().as(new Status[] { Status.FAIL }).apply();

		ExtentSparkReporter spark_Skipped = new ExtentSparkReporter("/ExtentReportsSC/subhashchandrabose_skipped.html");
		spark_Skipped.filter().statusFilter().as(new Status[] { Status.SKIP }).apply();

		ExtentSparkReporter spark_passed = new ExtentSparkReporter("/ExtentReportsSC/subhashchandrabose_passed.html");
		spark_passed.filter().statusFilter().as(new Status[] { Status.PASS }).apply();

		ExtentSparkReporter spark_warning = new ExtentSparkReporter("/ExtentReportsSC/subhashchandrabose_warning.html");
		spark_warning.filter().statusFilter().as(new Status[] { Status.WARNING }).apply();

		reports.attachReporter(spark, spark_Failed, spark_Skipped, spark_passed, spark_warning);

		// Here for faied test adding separate attaributes
		// This report level
		spark_Failed.config().setTheme(Theme.STANDARD);
		spark_Failed.config().setDocumentTitle("Failed Document");
		spark_Failed.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark_Failed.config().setReportName("subash");
		spark_Failed.config().setCss(".badge-primary{background-color:#df6565}");

		// Setting browser/system envoronment variables
		reports.setSystemInfo("OS", System.getProperty("os.name"));
		reports.setSystemInfo("JAVA Version", System.getProperty("java.version"));
		reports.setSystemInfo("USER NAME", System.getProperty("user.name"));
		reports.setSystemInfo("JAVA HOME", System.getProperty("java.home"));
		reports.setSystemInfo("BrowserNmae", cap.getBrowserName());
		reports.setSystemInfo("Browser Version", cap.getBrowserVersion());
		reports.setSystemInfo("URL", prop.getProperty("url"));
		reports.setSystemInfo("UN", prop.getProperty("userName"));
		reports.setSystemInfo("PW", prop.getProperty("password"));

		// captureBase64Code --storing to the string variable
		String base64 = captureBase64Code();

		// creating tests and attaching base64 screenshot
		reports.createTest("Mt test1", "facebook passed test").info("This is passed test1").pass("test1 passed")
				.addScreenCaptureFromBase64String(base64, "This is facebbok passed test");

		reports.createTest("Mt test2", "facebook failed test").info("This is failed test2").fail("Test failed")
				.addScreenCaptureFromBase64String(base64, "This is facebbok failed test");

		reports.createTest("Mt test3", "facebook skip test").info("This is passed test1").skip("Test skipped")
				.addScreenCaptureFromBase64String(base64, "This is facebbok skipped test");

		reports.createTest("Mt test4", "facebook warning test").info("This is failed test2").warning("test warnning")
				.addScreenCaptureFromBase64String(base64, "This is facebbok warning test");

		// creating tests and attaching file path screnshot
		reports.createTest("Mt test1", "facebook passed test").info("This is passed test1").pass("test1 passed")
				.addScreenCaptureFromBase64String(base64, "This is facebbok passed test");

		reports.createTest("Mt test2", "facebook failed test").info("This is failed test2").fail("Test failed")
				.addScreenCaptureFromBase64String(base64, "This is facebbok failed test");

		reports.createTest("Mt test3", "facebook skip test").info("This is passed test1").skip("Test skipped")
				.addScreenCaptureFromBase64String(base64, "This is facebbok skipped test");

		reports.createTest("Mt test4", "facebook warning test").info("This is failed test2").warning("test warnning")
				.addScreenCaptureFromBase64String(base64, "This is facebbok warning test");

		reports.flush();
		driver.close();

		// 1. Here we can pass direct file name , it will create under project
		// -----fileName.html
		// 2. Here if want to store all the files in side folder
		// ---/folderName/filename.html
		Desktop.getDesktop().browse(new File("/ExtentReportsSC/subhashchandraboseALL.html").toURI());
		Desktop.getDesktop().browse(new File("/ExtentReportsSC/subhashchandrabose_Failed.html").toURI());
		Desktop.getDesktop().browse(new File("/ExtentReportsSC/subhashchandrabose_skipped.html").toURI());
		Desktop.getDesktop().browse(new File("/ExtentReportsSC/subhashchandrabose_passed.html").toURI());
		Desktop.getDesktop().browse(new File("/ExtentReportsSC/subhashchandrabose_warning.html").toURI());
	}

	// This will take the screeshot but to attach file need to add this
	// captureBase64Code() method to each report.
	public static String captureBase64Code() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String base64Code = ts.getScreenshotAs(OutputType.BASE64);
		return base64Code;
	}

	public static String captureSCpathBased() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("sarru.png");
		FileUtils.copyFile(src, dest);

		return dest.getAbsolutePath();
	}
	
	public static void readCongin()
	{
		 try {
			prop=new Properties();
			 File f=new File(System.getProperty("user.dir")+"\\src\\test\\resource\\config.properties");
			 FileInputStream fis=new FileInputStream(f);
			 prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
