package com.subhash.extentReports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport3 {

	public static void main(String[] args) throws IOException {

		ExtentReports extentReport = new ExtentReports();

		File f = new File("E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\Report1.html");

		ExtentSparkReporter sr = new ExtentSparkReporter(f);
		extentReport.attachReporter(sr);

		ExtentTest et = extentReport.createTest("Test6");
		et.pass("Test6 passed");

		ExtentTest et1 = extentReport.createTest("Test7");
		et1.log(Status.FAIL, "Test7 failed");

		extentReport.createTest("Test8").log(Status.SKIP, "Test8 skipped");

		extentReport.flush();
		Desktop.getDesktop().browse(new File("E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\extentReports\\Report1.html").toURI());

	}

}
