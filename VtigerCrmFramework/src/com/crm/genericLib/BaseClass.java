package com.crm.genericLib;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.vtigercrm.ObjectRepository.lib.HomePage;
import com.vtigercrm.ObjectRepository.lib.Login;

import org.openqa.selenium.support.PageFactory;

public class BaseClass {

	public static WebDriver driver;
	public FileUtils flib = new FileUtils();
	WaitUtilityClass wUtil=new WaitUtilityClass();
	@BeforeClass
	public void configBC() throws Throwable {
		System.out.println("====Launching Browser====");
		Properties pObj = flib.getPropertyFileObject();
		String browserName = pObj.getProperty("browser");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chromedriver", "./resource/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void configBM() throws Throwable {
		Properties pObj = flib.getPropertyFileObject();
		wUtil.waitForPageToLoad(20, driver);
		pObj.getProperty("url");
		String url = pObj.getProperty("url");
		String id = pObj.getProperty("id");
		String pwd = pObj.getProperty("pwd");
		driver.get(url);

		/* Login by Calling method in Object Repository */
		Login l = PageFactory.initElements(driver, Login.class);
		l.loginToApp(id, pwd);
	}

	@AfterMethod
	public void configAM() throws Throwable {
		/* SignOut */
		HomePage h = PageFactory.initElements(driver, HomePage.class);
		h.doSignOut();

	}

	@AfterClass
	public void configAC() {
		driver.close();
	}

}
