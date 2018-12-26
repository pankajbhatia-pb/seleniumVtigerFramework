package com.vtigercrm.ObjectRepository.lib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrganisationDetails {
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement orgMgsWbEle;

	public WebElement getOrgMgsWbEle() {
		return orgMgsWbEle;
	}
	
	public String getOrgMsg()
	{
		String actMsg=orgMgsWbEle.getText();
		return actMsg;
	}
}
