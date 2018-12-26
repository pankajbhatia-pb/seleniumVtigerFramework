package com.vtigercrm.test;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.genericLib.BaseClass;
import com.vtigercrm.ObjectRepository.lib.CreatingNewContact;
import com.vtigercrm.ObjectRepository.lib.CreatingNewOrganization;
import com.vtigercrm.ObjectRepository.lib.HomePage;
import com.vtigercrm.ObjectRepository.lib.OrganisationDetails;

@Listeners(com.crm.genericLib.ListnerImplementClass.class)
public class OrganizationTest extends BaseClass {
	@Test
	public void createOrganizationTest() throws Throwable {
		
		/* TEST DATA */
		Random r = new Random();
		SoftAssert s =new SoftAssert();
		/* Other Data */
		String org = flib.getExcelFileData("Sheet1", 1, 1) + r.nextInt(100);
		String pdt = flib.getExcelFileData("Sheet1", 2, 1) + r.nextInt(100);
		String lastName = flib.getExcelFileData("Sheet1", 3, 1) + r.nextInt(100);
		String orgExpResult = flib.getExcelFileData("Sheet1", 8, 1);
		
		/* Navigate to Organization page */
		PageFactory.initElements(driver, HomePage.class);
		
		/*Validate Organization Page*/
		String orgActualResult = driver.getTitle();
		System.out.println(orgActualResult);
		s.assertEquals(orgExpResult, orgActualResult);
		System.out.println("Organization Page is displayed ==> PASS");
		s.assertAll();
		
		/*Create New Organization with mandotoryFields by clicking on "+" image*/
		CreatingNewOrganization newOrg=PageFactory.initElements(driver, CreatingNewOrganization.class);
		newOrg.createOrganizationWithOrgName(org);

		/* Validate created Organization */
		WebElement msgWb = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String expMsg = msgWb.getText();
		
		OrganisationDetails vOrg = PageFactory.initElements(driver, OrganisationDetails.class);
		String actMsg = vOrg.getOrgMsg();
		s.assertTrue(actMsg.contains(org));
		Reporter.log("Contact \" + org + \" created successfully==>Pass");
	}
}
