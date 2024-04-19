package com.subhash.Basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GetSysytemANDBrowserDetails {

	public static void main(String[] args) {

		// Get The Browser Details 
		WebDriver driver=new ChromeDriver();
		Capabilities cap=((RemoteWebDriver)driver).getCapabilities();
		System.out.println(cap.getBrowserName()+" "+cap.getBrowserVersion());
		
		// get the system details 
		
		/**
		 * os.name=Windows 10
		 * java.home=C:\Program Files\Java\jdk-17.0.5
		 * java.version=17.0.5
		 * user.name=user
		 * 
		 */
		
		
//		
		
		System.getProperties().list(System.out);
	}
	
	public  static void initpropertyFile() throws Exception
	{
		Properties prop=new Properties();
		File f=new File("E:\\PRACTIES\\NewSseion_Pracrices\\OrangeHRM\\src\\test\\resource\\config.properties");
		FileInputStream fis=new FileInputStream(f);
		prop.load(fis);
		
	}
}
