package com.vtigercrm.test;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import com.vtigercrm.ObjectRepository.lib.ContactDetails;
import com.vtigercrm.ObjectRepository.lib.CreatingNewContact;
import com.vtigercrm.ObjectRepository.lib.HomePage;
@Listeners(com.crm.genericLib.ListnerImplementClass.class)
public class ContactTest extends com.crm.genericLib.BaseClass {
	@Test
	public void createContactTest() throws Throwable {
		/* TEST DATA */
		Random r = new Random();
		SoftAssert s = new SoftAssert();
		
		/* Other Data */
		String org = flib.getExcelFileData("Sheet1", 1, 1) + r.nextInt(100);
		String pdt = flib.getExcelFileData("Sheet1", 2, 1) + r.nextInt(100);
		String lastName = flib.getExcelFileData("Sheet1", 3, 1) + r.nextInt(100);
		String conExpResult = flib.getExcelFileData("Sheet1", 7, 1);

		/* Navigate to contact page */
		HomePage conPage = PageFactory.initElements(driver, HomePage.class);
		conPage.navigateToContactPage();

		/* Validation of contact Page */
		String conActualResult = driver.getTitle();
		System.out.println(conActualResult);
		s.assertEquals(conExpResult, conActualResult);
		s.assertAll();
		Reporter.log("Contact page is displayed", true);

		/* Create New Contact with mandotoryFields by clicking on "+" image */
		CreatingNewContact newCon = PageFactory.initElements(driver, CreatingNewContact.class);
		newCon.createContactWithLastName(lastName);

		/* Validating created Contact */
		ContactDetails vCon = PageFactory.initElements(driver, ContactDetails.class);
		String actMsg = vCon.getConMsg();
		s.assertTrue(actMsg.contains(lastName));
		s.assertAll();
		Reporter.log("Contact \" + lastname + \" created successfully==>Pass");

	}

}
