package com.vtigercrm.test;

import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.genericLib.BaseClass;



public class EndToEndTesting extends BaseClass {
	@Test
	public void endToEndFlowTest() throws Throwable {
		
		/* TEST DATA */
		Random r = new Random();
		SoftAssert s = new SoftAssert();
		/* Other Data */
		String org = flib.getExcelFileData("Sheet1", 1, 1) + r.nextInt(100);
		String pdt = flib.getExcelFileData("Sheet1", 2, 1) + r.nextInt(100);
		String lastName = flib.getExcelFileData("Sheet1", 3, 1) + r.nextInt(100);
		String subject = flib.getExcelFileData("Sheet1", 4, 1) + r.nextInt(100);
		String billAdress = flib.getExcelFileData("Sheet1", 5, 1) + r.nextInt(100);
		String shippingAdress = flib.getExcelFileData("Sheet1", 6, 1) + r.nextInt(100);
		String orgExpResult = flib.getExcelFileData("Sheet1", 16, 18);
		String proExpResult = flib.getExcelFileData("Sheet1", 16, 19);
		String conExpResult = flib.getExcelFileData("Sheet1", 16, 20);
		String quoExpResult = flib.getExcelFileData("Sheet1", 16, 21);
		try {
			/* Create Organization */
			driver.findElement(By.xpath("//a[text()='Organizations']")).click();
			/*Validate Organization Page*/
			String orgActualResult = driver.getTitle();
			System.out.println(orgActualResult);
			s.assertEquals(orgExpResult, orgActualResult);
			System.out.println("Organization Page is displayed ==> PASS");
			s.assertAll();
			
			driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org);
			driver.findElement(By.xpath("//input[@class='crmbutton small save' and @name='button' ]")).click();
			
			/* Validate created Organization */
			WebElement msgWb = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
			String expMsg = msgWb.getText();
			s.assertTrue(expMsg.contains(org));
			Reporter.log("Organization \" + org + \" created successfully==>Pass");

			/* Create Product */
			driver.findElement(By.linkText("Products")).click();
			/*Validate Product Page*/
			String proActResult = driver.getTitle();
			System.out.println(orgActualResult);
			s.assertEquals(proActResult, proExpResult);
			System.out.println("Product Page is displayed ==> PASS");
			s.assertAll();
			
			driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
			driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(pdt);
			driver.findElement(By.xpath("//input[@class='crmbutton small save' and @name='button' ]")).click();

			WebElement pdMsgWb = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
			String PdtExpMsg = pdMsgWb.getText();
			s.assertTrue(PdtExpMsg.contains(pdt));
			Reporter.log("Product \" + pdt + \" created successfully==>Pass");
			

			/* Create contact for above organization */
			driver.findElement(By.linkText("Contacts")).click();
			// Create New Contact by clicking on "+" image
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			driver.findElement(By.name("lastname")).sendKeys(lastName);

			/* Select Organization */
			driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
			Set<String> set = driver.getWindowHandles();
			System.out.println(set.size());
			Iterator<String> itr = set.iterator();
			String parentWin = itr.next();
			String childWin = itr.next();
			driver.switchTo().window(childWin);

			/* Search for above created Organization */
			driver.findElement(By.id("search_txt")).sendKeys(org);

			/* Select organization name in drop-down */
			WebElement wb = driver.findElement(By.xpath("//select[@name='search_field']"));
			Select sel = new Select(wb);
			sel.selectByVisibleText("Organization Name");
			driver.findElement(By.xpath("//input[@name='search']")).click();
			driver.findElement(By.xpath("//a[text()='" + org + "']")).click();
			driver.switchTo().window(parentWin);
			driver.findElement(By.xpath("//input[@class='crmbutton small save' and @name='button' ]")).click();

			/* Create Quotes with above contact,organization name and product */
			Actions act = new Actions(driver);
			WebElement clickMore = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
			act.moveToElement(clickMore).perform();
			driver.findElement(By.linkText("Quotes")).click();
			System.out.println("Quotes Clicked");

			/*********************************/
			/* Create quote */
			/********************************/
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			/*Validation of Quotes Page*/
			String quoActualResult = driver.getTitle();
			System.out.println(quoActualResult);
			s.assertEquals(quoExpResult, quoActualResult);
			System.out.println("Quotes Page is displayed ==> PASS");
			s.assertAll();
			
			driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(subject);

			/* Select Above created contact */
			driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[2]")).click();
			Set<String> ssnId = driver.getWindowHandles();
			Iterator<String> itr1 = ssnId.iterator();
			String parentId = itr1.next();
			String childId = itr1.next();
			driver.switchTo().window(childId);
			System.out.println("===Switching to child window for adding contact===");
			driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(lastName);
			WebElement wb1 = driver.findElement(By.xpath("//select[@name='search_field']"));
			Select sel1 = new Select(wb1);
			sel1.selectByVisibleText("Last Name");
			driver.findElement(By.xpath("//input[@name='search']")).click();
			driver.findElement(By.xpath("//a[contains(text()='" + lastName + "']")).click();
			driver.switchTo().window(parentId);
			System.out.println("Above created contact is selected");

			/* Select above created Organization */
			driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[3]")).click();
			Set<String> ssnId1 = driver.getWindowHandles();
			Iterator<String> itrOrg = ssnId1.iterator();
			String parentId1 = itrOrg.next();
			String childId1 = itrOrg.next();
			driver.switchTo().window(childId1);
			driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(org);
			WebElement wb2 = driver.findElement(By.xpath("//select[@name='search_field']"));
			Select sel2 = new Select(wb2);
			sel1.selectByVisibleText("Organization Name");
			driver.findElement(By.xpath("//input[@name='search']")).click();
			driver.findElement(By.xpath("//a[text()='" + org + "']")).click();
			driver.switchTo().window(parentId1);
			System.out.println("Above created Organization is selected");

			/* Select above created Product */
			driver.findElement(By.xpath("//img[@id='searchIcon1']")).click();
			Set<String> ssnId2 = driver.getWindowHandles();
			Iterator<String> itrCon = ssnId2.iterator();
			String parentId2 = itrCon.next();
			String childId2 = itrCon.next();
			driver.switchTo().window(childId2);
			driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(pdt);
			WebElement wb3 = driver.findElement(By.xpath("//select[@name='search_field']"));
			Select sel3 = new Select(wb3);
			sel1.selectByVisibleText("Organization Name");
			driver.findElement(By.xpath("//input[@name='search']")).click();
			driver.findElement(By.xpath("//a[text()='" + pdt + "']")).click();
			driver.switchTo().window(parentId2);
			/* Entering Billing adress */
			driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(billAdress);
			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(shippingAdress);
			driver.findElement(By.xpath("(//input[@name='Button'])[1]")).click();
			System.out.println("Above created Organization is selected");

			/* Save */
			driver.findElement(By.xpath("//input[@class='crmbutton small save' and @name='button' ]")).click();

		} finally {
			driver.close();
		}

	}
}
