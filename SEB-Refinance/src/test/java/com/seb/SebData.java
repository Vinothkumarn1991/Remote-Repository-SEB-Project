package com.seb;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Driver;
import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.CustomStep;
import com.tavant.kwutils.FireKeyWord;
import com.tavant.kwutils.KWVariables;
import com.tavant.kwutils.MyTestCaseExecuter;
import com.tavant.kwutils.PageObjectLoader;
import com.tavant.kwutils.TestDataHandler;
import com.tavant.utils.TwfException;
import com.tavant.kwutils.Step;

import jxl.read.biff.BiffException;

import org.openqa.selenium.JavascriptExecutor;
public class SebData extends CustomStep {

	public void enterSSN(Map<String, String> data) throws BiffException,
	IOException, TwfException, InterruptedException {
		
		System.out.println("DATAVALUE == " +step.getDataValue("ssn_INT")+ "\nSTEP VALUE == "+step.getValue() );
		String SSN = step.getDataValue("ssn_INT");
		enterInputMask("ssn_INT", SSN);
	}

	public void enterInputMask(String index, String data) throws TwfException,
	BiffException, IOException {
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
	//	System.out.println("Reached here");
		actions.moveToElement(getElementByUsing(index));
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight/5)", "");	
		//System.out.println("Reached here");
		actions.click();
		actions.sendKeys("" + data);
		actions.build().perform();
	}
	@Override
	public void checkPage() {
		// TODO Auto-generated method stub
		
	}
	
}
