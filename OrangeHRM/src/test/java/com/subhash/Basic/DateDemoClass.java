package com.subhash.Basic;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateDemoClass {

	public static void main(String[] args) {
		/*
		 * LocalDate myObj = LocalDate.now();
		 * System.out.println(myObj.toString().replace("-", "_"));
		 * 
		 * LocalDateTime ldateTime = LocalDateTime.now();
		 * System.out.println(ldateTime.toString().replace("-","-"));
		 */
	    LocalDateTime myDateObj = LocalDateTime.now();
	    //DateTimeFormatter myFormatObj_Date_Time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    DateTimeFormatter myFormatObj1_Date = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
System.out.println(myDateObj.format(myFormatObj1_Date).toString().replace("-", "_").replace(":", "_"));

	}

}
