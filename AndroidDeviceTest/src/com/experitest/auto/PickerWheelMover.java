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

/* Class PickerWheelMover : Moves the picker wheel and changes the time
 * Version 1.0
 * Deepa Govindasamy : Assignment
 * 24.05.2019
 */

public class PickerWheelMover {

	// AppiumDriver
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;

	// Constants for driver initialization
	String REPORT_DIRECTORY	= "reports";
	String REPORT_FORMAT	= "xml";
	String TEST_NAME		= "PickerWheel";
	String UDID			= "9886674e5258475a46";
	String ACCESS_KEY		= "<Access_key>";
	String DRIVER_URL		= "https://cloud.seetest.io:443/wd/hub";

	// Driver initialization
	@SuppressWarnings("rawtypes")
	@BeforeMethod
	public void setUp() throws MalformedURLException {
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("reportDirectory", REPORT_DIRECTORY);
		capability.setCapability("reportFormat", REPORT_FORMAT);
		capability.setCapability("testName", TEST_NAME);
		capability.setCapability(MobileCapabilityType.UDID, UDID);
		capability.setCapability("accessKey", ACCESS_KEY);
		
        driver = new AndroidDriver(new URL(DRIVER_URL), capability);
	}

	@Test
	public void testPickerWheel() {

		/***** PICKER WHEEL MOVER : MODULE START    *****/
		
		// Constants for mobile device chosen for picker wheel mover
		String XPATH_CLOCK_ICON			= "//*[@text='Clock']";
		String XPATH_TIMER_ICON			= "//*[@text='TIMER']";
		String DIRECTION				= "up";
		String PROPERTY_DIRECTION		= "direction";
		String PROPERTY_ELEMENT			= "element";
		String PROPERTY_SCROLL			= "mobile: scroll";
		
		int HOUR_TO_BE_CHANGED			= 3;
		int MINUTE_TO_BE_CHANGED		= 2;
		int SECOND_TO_BE_CHANGED		= 4;
		
		String XPATH_TIME_PICKER_HOUR_ICON		= "//*[@id='numberpicker_input' and (./preceding-sibling::* | ./following-sibling::*)[@text='98, Hour, ']]";
		String XPATH_TIME_PICKER_MINUTE_ICON	= "//*[@id='numberpicker_input' and (./preceding-sibling::* | ./following-sibling::*)[@text='58, Minute, ']]";
		String XPATH_TIME_PICKER_SECOND_ICON	= "//*[@id='numberpicker_input' and (./preceding-sibling::* | ./following-sibling::*)[@text='58, Second, ']]";
		
		try {

			// Navigation to respective mobile elements
			driver.findElement(By.xpath(XPATH_CLOCK_ICON)).click();
			driver.findElement(By.xpath(XPATH_TIMER_ICON)).click();
		
			// Move the hour picker wheel by desired number
			WebElement timePickerWheelHour = driver.findElement(By.xpath(XPATH_TIME_PICKER_HOUR_ICON));
	
			JavascriptExecutor hourjs = (JavascriptExecutor) driver;
			HashMap<String, String> hourScrollObject = new HashMap<String, String>();
			hourScrollObject.put(PROPERTY_DIRECTION, DIRECTION);
			hourScrollObject.put(PROPERTY_ELEMENT, ((RemoteWebElement) timePickerWheelHour).getId());
			
			for (int count = 0; count < HOUR_TO_BE_CHANGED; count++) {
				hourjs.executeScript(PROPERTY_SCROLL, hourScrollObject);
			}
			
			// Move the minute picker wheel by desired number
			WebElement timePickerWheelMinute = driver.findElement(By.xpath(XPATH_TIME_PICKER_MINUTE_ICON));
	
			JavascriptExecutor minutejs = (JavascriptExecutor) driver;
			HashMap<String, String> minuteScrollObject = new HashMap<String, String>();
			minuteScrollObject.put(PROPERTY_DIRECTION, DIRECTION);
			minuteScrollObject.put(PROPERTY_ELEMENT, ((RemoteWebElement) timePickerWheelMinute).getId());
			
			for (int count = 0; count < MINUTE_TO_BE_CHANGED; count++) {
				minutejs.executeScript(PROPERTY_SCROLL, minuteScrollObject);
			}
			
			// Move the second picker wheel by desired number
			WebElement timePickerWheelSecond = driver.findElement(By.xpath(XPATH_TIME_PICKER_SECOND_ICON));
	
			JavascriptExecutor secondjs = (JavascriptExecutor) driver;
			HashMap<String, String> secondScrollObject = new HashMap<String, String>();
			secondScrollObject.put(PROPERTY_DIRECTION, DIRECTION);
			secondScrollObject.put(PROPERTY_ELEMENT, ((RemoteWebElement) timePickerWheelSecond).getId());
			
			for (int count = 0; count < SECOND_TO_BE_CHANGED; count++) {
				secondjs.executeScript(PROPERTY_SCROLL, secondScrollObject);
			}
		
		} catch (Exception exception) {
			
			System.out.println("LOGGER : ERROR : TEST FAILED : " + exception.getMessage());
			
		}
		
		/***** PICKER WHEEL MOVER : MODULE END    *****/
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}