package com.subhash.extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.subhash.Basic.GetSysytemANDBrowserDetails;

public class ExtentReport2 {
       
	public static void main(String[] args) throws IOException {
		
		// Get The Browser Details 
		WebDriver driver=new ChromeDriver();
		Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
		System.out.println(cap.getBrowserName()+" "+cap.getBrowserVersion());
		
		// get the system details 
		
		//System.getProperties().list(System.out);
		
		
		ExtentReports report=new ExtentReports();
		String path=System.getProperty("user.dir");
		File fileName=new File("E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash.html");
		
		ExtentSparkReporter spark=new ExtentSparkReporter(fileName);
		
		spark.config().setTheme(Theme.STANDARD);
		spark.config().setDocumentTitle("My First --- Documant");
		spark.config().setTimeStampFormat("yyyy-mm-dd ss:mm:hh");
		spark.config().setReportName("subash chandra bose");
		
		report.createTest("Test1" ,"My First test")
		      .assignAuthor("subhash","sarru")
		      .assignCategory("smoke","regression")
		      .assignDevice("chrome")
		      .log(Status.PASS, "My First test is pass");
		
		report.createTest("Test2" ,"My second test")
	      .assignAuthor("subhash","sarru")
	      .assignCategory("smoke","regression")
	      .assignDevice("chrome")
	      .log(Status.FAIL, "My second test is fail");
		
		report.createTest("Test3" ,"My thrid test")
	      .assignAuthor("subhash","sarru")
	      .assignCategory("smoke","regression")
	      .assignDevice("chrome")
	      .log(Status.SKIP, "My thrid test is skip");
		
		report.createTest("Test4" ,"My fourth test")
	      .assignAuthor("subhash","sarru")
	      .assignCategory("smoke","regression")
	      .assignDevice("chrome")
	      .log(Status.WARNING, "My fourth test is warnning");
		
		report.createTest("Test5" ,"My fifth test")
	      .assignAuthor("subhash","sarru")
	      .assignCategory("smoke","regression")
	      .assignDevice("chrome")
	      .log(Status.PASS, "My fifth test is passed");
		
		/**
		 * os.name=Windows 10
		 * java.home=C:\Program Files\Java\jdk-17.0.5
		 * java.version=17.0.5
		 * user.name=user
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
		
		
		report.attachReporter(spark);
		
		report.flush();
		Desktop.getDesktop().browse(new File("E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\subash.html").toURI());
		
		
;
	}

}
