package com.experitest.auto;

import static org.testng.Assert.assertEquals;

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

/* Class ContactListSorter : Performs the test of first name and last name sort.
 * Version 1.0
 * Deepa Govindasamy : Assignment
 * 24.05.2019
 */

public class ContactListSorter {

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testContactListSort() {
        
		/***** FIRST NAME SORT CHECKER : MODULE START    *****/
		
		// Constants for mobile device chosen to validate first and last name sort
		String XPATH_CONTACT_ICON		= "//*[@text='Contacts']";
		String XPATH_MORE_ICON			= "//*[@id='overflow_menu']";
		String XPATH_SETTING_ICON		= "//*[@text='Settings']";
		String XPATH_SORT_ICON			= "//*[@text='Sort by']";
		String XPATH_FIRST_NAME_ICON	= "//*[@text='First name']";
		String XPATH_LAST_NAME_ICON		= "//*[@text='Last name']";
		String XPATH_NAVIGATE_UP_ICON	= "//*[@contentDescription='Navigate up']";
		String ELEMENT_CONTACT_LIST		= "cliv_name_textview";
		String LAST_NAME_DELIMITER		= "\\ ";
		int OFFSET_CONTACT_FROM_MENU	= 2;
		
		// Variables for sort check
		int ZERO = 0;
		int ONE = 1;
		int contactListCount = 0;
		boolean MATCH = true;
		boolean TRUE = true;
		boolean FALSE = false;
		StringBuffer[] contacts;
		String individualContactName = null;
		TreeList contactsTree = new TreeList();
		
		try {
			
			// Navigation to respective mobile elements
			driver.findElement(By.xpath(XPATH_CONTACT_ICON)).click();
			driver.findElement(By.xpath(XPATH_MORE_ICON)).click();
			driver.findElement(By.xpath(XPATH_SETTING_ICON)).click();
			driver.findElement(By.xpath(XPATH_SORT_ICON)).click();
			driver.findElement(By.xpath(XPATH_FIRST_NAME_ICON)).click();
			driver.findElement(By.xpath(XPATH_NAVIGATE_UP_ICON)).click();
	
			// Retrieve all contact names from mobile screen
			List contactList = driver.findElementsById(ELEMENT_CONTACT_LIST);
	
			System.out.println("LOGGER : INFO : FIRST NAME SORT CHECK STARTED");
			
			if (contactList != null) {
				
				contactListCount = contactList.size();
				contacts = new StringBuffer[contactListCount - OFFSET_CONTACT_FROM_MENU];
				
				// Loops through all contacts retrieved from device and loads into 2 collections
				for (int count = ZERO; count < contactListCount - OFFSET_CONTACT_FROM_MENU; count++) {
					
					 contacts[count] = new StringBuffer(((WebElement) contactList.get(count + OFFSET_CONTACT_FROM_MENU)).getText());
					 contactsTree.add(((WebElement) contactList.get(count + OFFSET_CONTACT_FROM_MENU)).getText());
					 
				}
				
				// Sorts one collection
				Collections.sort(contactsTree);
				
				// Verifies the sort order from the device versus the sort through functionality
				for (int count = ZERO; count < contactListCount - OFFSET_CONTACT_FROM_MENU; count++) {
						
					if (contacts[count].toString().equals(contactsTree.get(count))) {
						 
						MATCH = MATCH & TRUE;
					
					} else {
						
						MATCH = MATCH & FALSE;
					 }
					
				}
					
				// Asserts if the sort order matches
				assertEquals(MATCH, TRUE);
				System.out.println("LOGGER : INFO : FIRST NAME SORT CHECK SUCCESSFUL");

			} else {
				
				System.out.println("LOGGER : ERROR : Test cannot be performed, as there is no contact information in device");
			
			}
			
			System.out.println("LOGGER : INFO : FIRST NAME SORT CHECK ENDED");
			
			/***** FIRST NAME SORT CHECKER : MODULE END    *****/
			
			
			/***** LAST NAME SORT CHECKER : MODULE START    *****/
			
			contactsTree = new TreeList();
			
			// Navigation to respective mobile elements
			driver.findElement(By.xpath(XPATH_MORE_ICON)).click();
			driver.findElement(By.xpath(XPATH_SETTING_ICON)).click();
			driver.findElement(By.xpath(XPATH_SORT_ICON)).click();
			driver.findElement(By.xpath(XPATH_LAST_NAME_ICON)).click();
			driver.findElement(By.xpath(XPATH_NAVIGATE_UP_ICON)).click();
			
			// Retrieve all contact names from mobile screen
			contactList = driver.findElementsById(ELEMENT_CONTACT_LIST);
			
			System.out.println("LOGGER : INFO : LAST NAME SORT CHECK STARTED");
			
			if (contactList != null) {
				
				contactListCount = contactList.size();
				contacts = new StringBuffer[contactListCount - OFFSET_CONTACT_FROM_MENU];
				
				// Loops through all contacts retrieved from device and loads into 2 collections
				for (int count = ZERO; count < contactListCount - OFFSET_CONTACT_FROM_MENU; count++) {
					
					individualContactName = ((WebElement) contactList.get(count + OFFSET_CONTACT_FROM_MENU)).getText();
					
					String[] nameSplit = individualContactName.split(LAST_NAME_DELIMITER);
					
					contacts[count] = new StringBuffer(nameSplit[nameSplit.length - ONE]);
					contactsTree.add(nameSplit[nameSplit.length - ONE]);
					
				}
				
				// Sorts one collection
				Collections.sort(contactsTree);
				
				// Match is reset
				MATCH = TRUE;
				
				// Verifies the sort order from the device versus the sort through functionality
				for (int count = ZERO; count < contactListCount - OFFSET_CONTACT_FROM_MENU; count++) {
	
					if (contacts[count].toString().equals(contactsTree.get(count))) {
						 
						MATCH = MATCH & TRUE;
					
					} else {
						
						MATCH = MATCH & FALSE;
					 }
					
				}
				
				// Asserts if the sort order matches
				assertEquals(MATCH, TRUE);
				System.out.println("LOGGER : INFO : LAST NAME SORT CHECK SUCCESSFUL");
			}
			
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