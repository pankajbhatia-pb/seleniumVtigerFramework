package com.vtigercrm.ObjectRepository.lib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.genericLib.BaseClass;





public class CreatingNewContact extends BaseClass{

	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement imgWbEle;

	@FindBy(name = "lastname")
	private WebElement lastNameWbEle;

	@FindBy(xpath = "//input[@class='crmButton small save']")
	private WebElement saveWbEle;

	public WebElement getImgWbEle() {
		return imgWbEle;
	}

	public WebElement getLastNameWbEle() {
		return lastNameWbEle;
	}

	public WebElement getSaveWbEle() {
		return saveWbEle;
	}

	public void createContactWithLastName(String lastName) {
		imgWbEle.click();
		lastNameWbEle.sendKeys(lastName);
		saveWbEle.click();

	}

}
