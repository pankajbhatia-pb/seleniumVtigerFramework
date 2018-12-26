package com.crm.genericLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilityClass {

		/*Implicitly wait*/
		public void waitForPageToLoad(long timeInSeconds,WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
			
		}
		
		/*	Explicitly wait	*/
		public void waitForElementToBePresent(WebDriver driver,long timeInSec,WebElement wb)
		{
			WebDriverWait wait=new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(wb));
			
		}
		
		public void waitForElementCompleteLoad(String linkNAme ,WebDriver driver) throws InterruptedException
		{
			int count=0;
					while (count<20)
					{
						try
						{
						   driver.findElement(By.linkText(linkNAme)).click();
							break;
						}
						catch(Throwable t)
						{
							System.out.println("handled");
							
							Thread.sleep(1000);
							count++;
						}
					}
		}
	

}
