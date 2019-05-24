package com.experitest.auto;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/* Class WIFIConnectivityChecker : Performs the test of WIFI connectivity.
 * Version 1.0
 * Deepa Govindasamy : Assignment
 * 24.05.2019
 */

public class WIFIConnectivityChecker {

	// AppiumDriver
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	
	// Constants for driver initialization
	String REPORT_DIRECTORY	= "reports";
	String REPORT_FORMAT	= "xml";
	String TEST_NAME		= "wifiConnectivity";
	String UDID				= "9886674e5258475a46";
	String ACCESS_KEY		= "eyJ4cC51Ijo2NDM1NjY3LCJ4cC5wIjo2NDM1NjY2LCJ4cC5tIjoiTVRVMU9EWTVPVFkzTXpZd01RIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE4NzQwNTk2NzYsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.0BWBBGMoEkentk-q-b2Q9pKABRYOPaGeGK4xcM0uTIU";
	String DRIVER_URL		= "https://cloud.seetest.io:443/wd/hub";
	
	// Driver initialization
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		initializeDriver();
	}

	@Test
	public void wifiConnectivity() throws Exception {
		
		/***** WIFI CONNECTIVITY CHECKER : MODULE START    *****/
		
		// Constants for mobile device chosen to validate wifi
		String XPATH_WIFI_ICON		= "//*[@id='icon' and ./parent::*[@contentDescription='Wi-Fi,On.,PCLOUD-NV,Button']]";
		String DIRECTION			= "down";
		boolean TRUE 				= true;
		boolean FALSE 				= false;
		
		// Variables for wifi check
		boolean wifiState;
		
		try {
			
			// Checks the initial state of WIFI icon in device home
			wifiState = driver.getConnection().isWiFiEnabled();
			
			// Assert the wifi status in ON state
			assertEquals(wifiState, TRUE);
			System.out.println("LOGGER : INFO : WIFI TURNED ON");
						
			// Javascript executor to scroll the device home
			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", DIRECTION);
			js.executeScript("mobile: scroll", scrollObject);
			
			// Find WIFI icon on the device and toggle the state
			WebElement wifiElement = driver.findElement(By.xpath(XPATH_WIFI_ICON));
			wifiElement.click();
			
			// Reload the device information after click event
			initializeDriver();
	        
			// Checks the state of WIFI icon in device home after toggle
			wifiState = driver.getConnection().isWiFiEnabled();
			
			// Assert the wifi status after turning off
			assertEquals(wifiState, FALSE);
			System.out.println("LOGGER : INFO : WIFI TURNED OFF SUCCESSFULLY");
			
			System.out.println("LOGGER : INFO : WIFI CORRESPONDS TO UDID : > " + driver.getCapabilities().getCapability(MobileCapabilityType.UDID));
			
			/***** WIFI CONNECTIVITY CHECKER : MODULE END    *****/
		
		} catch (Exception exception) {
			
			System.out.println("LOGGER : ERROR : TEST FAILED : " + exception.getMessage());
			
		}
	}

	@SuppressWarnings("rawtypes")
	public void initializeDriver() throws MalformedURLException {
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("reportDirectory", REPORT_DIRECTORY);
		capability.setCapability("reportFormat", REPORT_FORMAT);
		capability.setCapability("testName", TEST_NAME);
		capability.setCapability(MobileCapabilityType.UDID, UDID);
		capability.setCapability("accessKey",ACCESS_KEY);
		
        driver = new AndroidDriver(new URL(DRIVER_URL), capability);

	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}