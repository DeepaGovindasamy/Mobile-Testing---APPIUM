package com.experitest.auto;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/* Class ContactListSorterWithPOM : Performs the test of first name and last name sort.
 * Implements Page Object Model
 * Version 1.0
 * Deepa Govindasamy : Assignment
 * 24.05.2019
 */

public class ContactListSorterWithPOM {
	
	// AppiumDriver
	@SuppressWarnings("rawtypes")
	AndroidDriver driver;
	
	// Constants for driver initialization
	String REPORT_DIRECTORY	= "reports";
	String REPORT_FORMAT	= "xml";
	String TEST_NAME		= "ContactListSort";
	String UDID				= "9886674e5258475a46";
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

	//@SuppressWarnings({ "unchecked" })
	@Test
	public void testContactListSort() {
	
		try {

			/***** FIRST NAME SORT CHECKER : MODULE START    *****/

			// Navigation to respective mobile elements
			ContactsApp ContactsApp = new ContactsApp(driver);
			System.out.println("LOGGER : INFO : FIRST NAME SORT CHECK STARTED");

			ContactsApp.ContactAppByFirstName();	
			System.out.println("LOGGER : INFO : FIRST NAME SORT CHECK ENDED");
			
			/***** FIRST NAME SORT CHECKER : MODULE END    *****/
			
			
			/***** LAST NAME SORT CHECKER : MODULE START    *****/
			
			System.out.println("LOGGER : INFO : LAST NAME SORT CHECK STARTED");
			ContactsApp.ContactAppByLastyName();			
			System.out.println("LOGGER : INFO : LAST NAME SORT CHECK ENDED");
			
			/***** LAST NAME SORT CHECKER : MODULE END    *****/
		
		} catch (Exception exception) {
			
			System.out.println("LOGGER : ERROR : TEST FAILED : " + exception.getMessage());
		}
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
