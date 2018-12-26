package com.crm.genericLib;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ListnerImplementClass implements ITestListener {
	@Override
	public void onFinish(ITestContext arg0) {

	}

	@Override
	public void onStart(ITestContext arg0) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	@Override
	public void onTestFailure(ITestResult r) {
		String failedTestName = r.getMethod().getMethodName();
		EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.driver);
		File src = eDriver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenshot/" + failedTestName + ".png");

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {

	}

	@Override
	public void onTestStart(ITestResult arg0) {

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {

	}

}
