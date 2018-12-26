package com.vtigercrm.ObjectRepository.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.crm.genericLib.BaseClass;

public class ContactDetails extends BaseClass {

	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement searchBoxWbEle;
	
	@FindBy(xpath = "(//select[@id='bas_searchfield'])[1]")
	private WebElement searchByWbEle;
	
	@FindBy(xpath = "(//input[@class='crmbutton small create'])[1]")
	private WebElement SearchButtonWbEle;
	
	@FindBy(xpath = "(//input[@class='crmbutton small delete']")
	private WebElement DeleteButtonWbEle;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement conMgsWbEle;

	public WebElement getConMgsWbEle() {
		return conMgsWbEle;
	}

	public WebElement getSearchBoxWbEle() {
		return searchBoxWbEle;
	}

	public WebElement getSearchByWbEle() {
		return searchByWbEle;
	}

	public WebElement getSearchButtonWbEle() {
		return SearchButtonWbEle;
	}

	public WebElement getDeleteButtonWbEle() {
		return DeleteButtonWbEle;
	}
	
	public String getConMsg()
	{
		String actMsg=conMgsWbEle.getText();
		return actMsg;
	}
	public void deleteContact(String lastName)
	{
		searchBoxWbEle.sendKeys(lastName);
		Select sel=new Select(searchByWbEle);
		sel.selectByVisibleText("Last Name");
		SearchButtonWbEle.click();
		driver.findElement(By.xpath("//a[text()='" + lastName + "']/../../td[1]")).click();
		DeleteButtonWbEle.click();
	}
	public void searchContact(String lastName)
	{
		searchBoxWbEle.sendKeys(lastName);
		Select sel=new Select(searchByWbEle);
		sel.selectByVisibleText("Last Name");
		SearchButtonWbEle.click();
	}

	
	
}
