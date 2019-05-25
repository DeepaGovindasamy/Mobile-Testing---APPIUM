package com.experitest.auto;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.list.TreeList;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings({ "rawtypes", "unchecked" })

public class ContactsApp {
	
	// AppiumDriver
	AndroidDriver driver;
	
	 public ContactsApp(AndroidDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this); 
	 }
	 
	// Constants for mobile device chosen to validate first and last name sort
	final String XPATH_CONTACT_ICON		= "//*[@text='Contacts']";
	final String XPATH_MORE_ICON		= "//*[@id='overflow_menu']";
	final String XPATH_SETTING_ICON		= "//*[@text='Settings']";
	final String XPATH_SORT_ICON		= "//*[@text='Sort by']";
	final String XPATH_FIRST_NAME_ICON	= "//*[@text='First name']";
	final String XPATH_LAST_NAME_ICON	= "//*[@text='Last name']";
	final String XPATH_NAVIGATE_UP_ICON	= "//*[@contentDescription='Navigate up']";
	final String ELEMENT_CONTACT_LIST	= "cliv_name_textview";
	String LAST_NAME_DELIMITER			= "\\ ";
	int OFFSET_CONTACT_FROM_MENU		= 2;
	
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
								
	 @AndroidFindBy(xpath = XPATH_CONTACT_ICON)
	   private AndroidElement contactElement;
	 
	 @AndroidFindBy(xpath = XPATH_MORE_ICON)
	   private AndroidElement moreElement;
	 
	 @AndroidFindBy(xpath = XPATH_SETTING_ICON)
	   private AndroidElement settingsElement;
	 
	 @AndroidFindBy(xpath = XPATH_SORT_ICON)
	   private AndroidElement sortElement;
	 
	 @AndroidFindBy(xpath = XPATH_FIRST_NAME_ICON)
	   private AndroidElement firstNameElement;
	 
	 @AndroidFindBy(xpath = XPATH_LAST_NAME_ICON)
	   private AndroidElement lastNameElement;
	 
	 @AndroidFindBy(xpath = XPATH_NAVIGATE_UP_ICON)
	   private AndroidElement navigateElement;
	 
	 public void clickContacts() {
		 contactElement.click();
	    }
	 
	 public void clickMoreElement() {
		 moreElement.click();
	    }
	 
	 public void clickSettingsElement() {
		 settingsElement.click();
	    }
	 
	 public void clickSortElement() {
		 sortElement.click();
	    }
	 
	 public void clickFirstNameElement() {
		 firstNameElement.click();
	    }
	 
	 public void clickLastNameElement() {
		 lastNameElement.click();
	    }
	 
	 public void clickNavigateElement() {
		 navigateElement.click();
	    }
	 
	 public List getContactList() {
		// Retrieve all contact names from mobile screen 
		return driver.findElementsById(ELEMENT_CONTACT_LIST);
	 }
	 
	 public void checkSortByFirstName (List contactList) {
		 
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
	 }
	 
	 public void checkSortByLastName (List contactList) {
		 
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
	 }
	
	 public void ContactAppByFirstName() {
		
		 //Navigation to respective mobile elements
		
		clickContacts();
		
		clickMoreElement();
		
		clickSettingsElement();
		
		clickSortElement();
		
		clickFirstNameElement();
		
		clickNavigateElement();
		
		// Retrieve all contact names from mobile screen
		List contactList = getContactList();
		checkSortByFirstName(contactList);
	 }
	 
	 public void ContactAppByLastyName() {
		contactsTree = new TreeList();
		
		//Navigation to respective mobile elements
		
		clickMoreElement();
		
		clickSettingsElement();
		
		clickSortElement();
		
		clickLastNameElement();
		
		clickNavigateElement();
		
		// Retrieve all contact names from mobile screen
		List contactList = getContactList();
		checkSortByLastName(contactList);
	 }
}
