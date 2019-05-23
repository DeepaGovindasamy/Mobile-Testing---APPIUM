package com.experitest.auto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WIFIConnectivity {

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
		 
		//*[@id='icon' and ./parent::*[@contentDescription='Wi-Fi,On.,PCLOUD-NV,Button']]
		List list = driver.findElementsById("icon");
		System.out.println(driver.getConnection().isWiFiEnabled());
		System.out.println(driver.getConnection().WIFI_MASK);
		//System.out.println(((WebElement)list.get(0)).getAttribute("selected"));
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}