package com.seb.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tavant.base.DriverFactory;
import com.tavant.utils.TwfException;

public class Utils {
	WebDriver driver;

	
	/**
	 * Add Failure Reason to Report
	 * 
	 * @param description
	 * @param actualValue
	 * @param expectedValue
	 * @return
	 * @throws TwfException
	 */
	public static void addExceptionToReport(String description,
			String actualValue, String expectedValue) throws TwfException {
		throw new TwfException(description
				+ " :<font color=\"solid orange\">  Actual :[" + actualValue
				+ "]</font><font color=\"EE7600\"> Expected :[" + expectedValue
				+ "]</font><br> <b>Step Details:</b> " + "<br>");
	}

	
	/**
	 * Gets the window handles and switches between the windows
	 * 
	 * @throws TwfException
	 * 
	 * @throws InterruptedException
	 */
	public static void switchToWindow(WebDriver driver) throws TwfException,
			InterruptedException {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			System.out.println("winHandle ==> "+winHandle);
		}
	}
	
	
	
	
	
	
}