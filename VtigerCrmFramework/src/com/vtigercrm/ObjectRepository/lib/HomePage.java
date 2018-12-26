package com.vtigercrm.ObjectRepository.lib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.crm.genericLib.BaseClass;



public class HomePage extends BaseClass{
	/**/
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement signOutImgWbEle;

	@FindBy(xpath = "//a[text()='Sign Out']")
	private WebElement signOutButtonWbEle;
	
	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement contactWbEle;
	
	@FindBy(xpath = "//a[text()='Organizations']")
	private WebElement orgWbEle;

	public WebElement getOrganisationWbEle() {
		return orgWbEle;
	}

	public WebElement getContactWbEle() {
		return contactWbEle;
	}
	
	public WebElement getSignOutImgWbEle() {
		return signOutImgWbEle;
	}
	
	public WebElement getSignOutButtonWbEle() {
		return signOutButtonWbEle;
	}

	public void navigateToOrganisationPage() {
		orgWbEle.click();
	}
	public void navigateToContactPage() {
		contactWbEle.click();
	}
	public void doSignOut() throws Throwable
	{
		Actions act = new Actions(driver);
		act.moveToElement(signOutImgWbEle).perform();
		Thread.sleep(2000);
		signOutButtonWbEle.click();
		
	}
	
}
