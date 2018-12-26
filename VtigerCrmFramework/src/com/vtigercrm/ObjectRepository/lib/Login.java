package com.vtigercrm.ObjectRepository.lib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.genericLib.BaseClass;



public class Login extends BaseClass{
	/* Initialization */
	/* We are identifying webElement here */
	@FindBy(name = "user_name")
	private WebElement userNameWbEle;

	@FindBy(name = "user_password")
	private WebElement passwordWbEle;

	@FindBy(id = "submitButton")
	private WebElement loginButton;

	/* Utilization Through Getters */
	public WebElement getUserName() {
		return userNameWbEle;
	}

	public WebElement getPassword() {
		return passwordWbEle;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	/* Utilization Through Business Methods */
	public void loginToApp(String userName, String password) {
		userNameWbEle.sendKeys(userName);
		passwordWbEle.sendKeys(password);
		loginButton.click();

	}

}
