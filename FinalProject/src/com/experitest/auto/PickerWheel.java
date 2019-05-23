package com.experitest.auto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class PickerWheel {

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
		// driver.findElement(By.xpath("//*[@text='Apps']")).click();
		// System.out.println("App object clicked");
		 driver.findElement(By.xpath("//*[@text='Clock']")).click();
		 System.out.println("Clock object clicked");
		 driver.findElement(By.xpath("//*[@text='TIMER']")).click();
		 System.out.println("Timer object clicked");
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		 //HashMap<String, String> scrollObject = new HashMap<String, String>();
		 //scrollObject.put("direction", "down");
		 //js.executeScript("mobile: scroll", scrollObject);
		 //js.executeScript("mobile: scroll", scrollObject);
		 //js.executeScript("mobile: scroll", scrollObject);
		 int strHour = 4;	
		 WebElement DatePickerListItem = driver.findElement(By.xpath("//*[@id='timer_picker_view']"));
		// WebElement DatePickerListItem = driver.findElement(By.xpath("//*[@id='numberpicker_input' and (./preceding-sibling::* | ./following-sibling::*)[@text='98, Hour, ']]"));
			//DatePickerListItem.click();	
			 System.out.println("picker object clicked");
			
			 WebElement DatePickerWheel1 = 
						driver.findElement(By.xpath("//*[@id='numberpicker_input' and (./preceding-sibling::* | ./following-sibling::*)[@text='98, Hour, ']]"));

			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 HashMap<String, String> scrollObject = new HashMap<String, String>();
			 scrollObject.put("direction", "up");
			 scrollObject.put("offset", "23");
			 scrollObject.put("element", ((RemoteWebElement) DatePickerWheel1).getId());
			 for (int i = 0; i < 24; i++) {
				 js.executeScript("mobile: scroll", scrollObject);
			 }
			 
			 /*
			 
			WebElement DatePickerWheel1 = 
					driver.findElement(By.xpath("//*[@id='numberpicker_input' and (./preceding-sibling::* | ./following-sibling::*)[@text='98, Hour, ']]"));
			DatePickerWheel1.click();
			
			driver.findElement(By.xpath("//*[@id='numberpicker_input' and ./parent::*[@id='timer_timepicker_hours_picker']]")).sendKeys("12");
			driver.findElement(By.xpath("//*[@id='numberpicker_input' and ./parent::*[@id='timer_timepicker_minutes_picker']]")).sendKeys("45");
			driver.findElement(By.xpath("//*[@id='numberpicker_input' and ./parent::*[@id='timer_timepicker_seconds_picker']]")).sendKeys("15");
			
		    */
	     
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}