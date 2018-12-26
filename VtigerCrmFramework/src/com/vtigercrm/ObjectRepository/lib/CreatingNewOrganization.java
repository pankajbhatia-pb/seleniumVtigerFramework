package com.vtigercrm.ObjectRepository.lib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.genericLib.BaseClass;



public class CreatingNewOrganization extends BaseClass {
	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement imgOrgWbEle;

	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameWbEle;

	@FindBy(xpath = "//input[@class='crmbutton small save' and @name='button' ]")
	private WebElement saveOrgWbEle;

	public WebElement getImgOrgWbEle() {
		return imgOrgWbEle;
	}

	public WebElement getOrgNameWbEle() {
		return orgNameWbEle;
	}

	public WebElement getSaveOrgWbEle() {
		return saveOrgWbEle;
	}

	public void createOrganizationWithOrgName(String orgName) {
		imgOrgWbEle.click();
		orgNameWbEle.sendKeys(orgName);
		saveOrgWbEle.click();

	}
}
