package com.subhash.extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static void main(String[] args) throws IOException {
		
		ExtentReports extentReport=new ExtentReports();
		
		File f=new File("Report1.html");
		
		
		ExtentSparkReporter sr=new ExtentSparkReporter(f);
		extentReport.attachReporter(sr);
		
		
	ExtentTest et	=extentReport.createTest("Test4");
	et.pass("Test4 passed");
	
	ExtentTest et1= extentReport.createTest("Test5");
	et1.log(Status.FAIL, "Test5 failed");
	
	extentReport.createTest("Test6").log(Status.SKIP,"Test6 skipped");
		
		
		extentReport.flush();
		Desktop.getDesktop().browse(f.toURI());
		
	}

}
