package com.experitest.auto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.list.TreeList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ContactListSort {

	///AppiumDriver driver;
	AndroidDriver driver;
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("reportDirectory", "reports");
        dc.setCapability("reportFormat", "xml");
        dc.setCapability("testName", "Untitled");
		dc.setCapability(MobileCapabilityType.UDID, "9886674e5258475a46");
        dc.setCapability("accessKey","eyJ4cC51Ijo2NDA4NTUzLCJ4cC5wIjo2NDA4NTUyLCJ4cC5tIjoiTVRVMU9EVXdNVFF4T1RVd013IiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4NzM4NjQ3NzYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.jv0x90YSjsw0YKlJouspM22bkvycFBKBixLo33U3f9s");
		///dc.setCapability(MobileCapabilityType.APP, <PATH TO APPLICATION> ); // If you wish to continue with the app running on the device, comment this line
        System.out.println("Before");
        driver = new AndroidDriver(new URL("https://cloud.seetest.io:443/wd/hub"), dc);
        System.out.println("After");
	}

	@Test
	public void testUntitled(){
        //Enter your test code here
		 System.out.println("Execution starts");
		
		 driver.findElement(By.xpath("//*[@text='Contacts']")).click();
		 System.out.println("Contacts object clicked");
		 driver.findElement(By.xpath("//*[@id='overflow_menu']")).click();
		 System.out.println("Overflow_Menu object clicked");
		 driver.findElement(By.xpath("//*[@text='Settings']")).click();
		 System.out.println("Settings object clicked");
		 driver.findElement(By.xpath("//*[@text='Sort by']")).click();
		 System.out.println("Sort by object clicked");
		 driver.findElement(By.xpath("//*[@text='First name']")).click();
		 System.out.println("First name object clicked");
		 driver.findElement(By.xpath("//*[@contentDescription='Navigate up']")).click();
		 System.out.println("Navigate to object clicked");
		 List elementList = driver.findElementsById("cliv_name_textview");
		 
		 StringBuffer[] contacts = new StringBuffer[3];
		 TreeList contactsTree = new TreeList();
		 
		 for (int i=0; i<=2; i++) {
			 contacts[i] = new StringBuffer(((WebElement) elementList.get(i+2)).getText());
			 contactsTree.add(((WebElement) elementList.get(i+2)).getText());
		 }
		 
		 Collections.sort(contactsTree);

		 
		 boolean match = true;
		 
		 for (int i=0; i<3; i++) {
			 System.out.println(contacts[i]);
			 System.out.println(contactsTree.get(i));
			 if (contacts[i].toString().equals(contactsTree.get(i))) {
				 System.out.println("Matching");
				 match = match & true;
			 } else {
				 System.out.println("Not Matching");
				 match = match & false;
			 }
		 }
		 
		 if (match == false) {
			 System.out.println("Sorting did not work");
		 } else {
			 System.out.println("Sorting worked");
		 }
	
		 System.out.println(contacts);
		 System.out.println(contactsTree);
		 
//		 driver.findElement(By.xpath("//*[@text='Last name']")).click();
//		 System.out.println("Last name object clicked");
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}