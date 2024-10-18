package com.seb.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tavant.base.DriverFactory;
import com.tavant.kwutils.CustomStep;
import com.tavant.utils.TwfException;

public class DriverUtils extends CustomStep {

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
	
	
	public void switchToiFrameByXpath() throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		driver.switchTo().frame(driver.findElement(By.xpath("/iframe")));
	}

}
