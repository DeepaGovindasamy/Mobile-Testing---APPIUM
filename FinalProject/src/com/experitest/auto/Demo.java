package com.experitest.auto;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.JavascriptExecutor;
import java.util.HashMap;

public class Demo {

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
		 System.out.println(driver);
		 driver.findElement(By.xpath("//*[@text='Phone']")).click();
		 System.out.println("Phone object clicked");
		 driver.findElement(By.xpath("//*[@text='CONTACTS']")).click();
		 System.out.println("Contacts object clicked");
		 driver.findElement(By.xpath("//*[@text='Create contact']")).click();
		 System.out.println("Create Contacts object clicked");
		 driver.findElement(By.xpath("//*[@class='android.widget.EditText' and ./parent::*[@id='editors']]")).sendKeys("US");
		 System.out.println("Name input");
		 driver.hideKeyboard();
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		 //HashMap<String, String> scrollObject = new HashMap<String, String>();
		 //scrollObject.put("direction", "down");
		 //js.executeScript("mobile: scroll", scrollObject);
		 //js.executeScript("mobile: scroll", scrollObject);
		 //js.executeScript("mobile: scroll", scrollObject);
		 
		 driver.findElement(By.xpath("(//*[@id='sect_fields']/*/*/*[@id='kind_title_edit'])[1]")).sendKeys("1564364");
		 System.out.println("phone number input");
	     driver.hideKeyboard();
	     driver.findElement(By.xpath(" //*[@text='SAVE']")).click();
	     System.out.println("save");
	   //*[@id='iconview_imageView' and ./parent::*[@contentDescription='Phone']]
	     
	   
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}