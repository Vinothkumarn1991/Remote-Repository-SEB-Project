//package src.test.java.com.seb.util;
package com.seb.util; 

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.kwutils.KWVariables;
import com.tavant.kwutils.PageObjectLoader;
import com.tavant.kwutils.TestDataHandler;
import com.tavant.utils.TwfException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import jxl.JXLException;
import jxl.read.biff.BiffException;

public class BorrowerFlow extends WebPage {

	WebDriver driver;

	public void scrollToElement(String ele) throws TwfException, BiffException, IOException, InvalidFormatException {
		System.out.println("********************** scrollToElement ********************");
		driver = DriverFactory.getDriver();
		// KWVariables.getVariables().get(ele);
		// getElementByUsing(ele);
		//String args [ ] = KWVariables.getVariables().get(ele).split(":");
		WebElement ele2 = getElementByUsing(ele);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(4000,0)", ele2);
		js.executeScript("window.scrollBy(4000,0)", ele2);
		js.executeScript("arguments[0].scrollIntoView(true);", ele2);
		System.out.println("getElementByUsing(ele) : " + getElementByUsing(ele).getText());
	}


	public void scrollToElementByPosition(String ele) throws TwfException, BiffException, IOException, InvalidFormatException {
		System.out.println("********************** scrollToElementByPosition ********************");
		driver = DriverFactory.getDriver();
		// KWVariables.getVariables().get(ele);
		// getElementByUsing(ele);
		String args [ ] = KWVariables.getVariables().get(ele).split(":");
		WebElement ele2 = getElementByUsing(args[0]);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+args [1]+",0)", ele2);
		js.executeScript("window.scrollBy("+args [1]+",0)", ele2);
		js.executeScript("arguments[0].scrollIntoView(true);", ele2);
		System.out.println("Element text : " + getElementByUsing(args[0]).getText());
	}

	public void typeSSN(String value)
			throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException {
		System.out.println("****************************** typeSSN *********************");
		String args[] = KWVariables.getVariables().get(value).split(":");
		driver=DriverFactory.getDriver();
		WebElement we = getElementByUsing(args[0]);
		Actions actions = new Actions(driver);
		actions.moveToElement(we);
		Thread.sleep(1000);
		actions.click();
		actions.sendKeys(args[1]);
		Thread.sleep(1000);
		actions.build().perform();
		Thread.sleep(1000);
		String valueEntered = getElementByUsing(args[0]).getAttribute("value");
		System.out.println("valueEntered :: " + valueEntered);

	}

	public void enterSSN(String input) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("****************************** typeSSN *********************");
		String[] inputs = input.split(",");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		Thread.sleep(5000);
		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing(inputs[0]).clear();
		Thread.sleep(5000);

		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		((JavascriptExecutor) getWebDriver()).executeScript(
				"window.scrollBy(0,document.body.scrollHeight/5)", "");
		// System.out.println("Reached here");
		actions.click();
		actions.sendKeys("" + inputs[1]);

		Thread.sleep(5000);

		System.out.println("Value Entered: "+inputs[1]);
		actions.build().perform();
	}

	public void enterSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException
	{
		System.out.println("****************************** enterSSNFromDP *********************"+dataPoolArgs.get("ssn_cust"));
		String[] inputs = dataPoolArgs.get("ssn_cust").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));
		((JavascriptExecutor) getWebDriver()).executeScript(
				"window.scrollBy(0,document.body.scrollHeight/5)", "");
		// System.out.println("Reached here");
		actions.click();
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();
	}

	public void enterHardPullSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("****************************** HARD PULL enterSSNFromDP *********************"+dataPoolArgs.get("ssn_Hardpull"));
		String[] inputs = dataPoolArgs.get("ssn_Hardpull").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterHardPullCoSignerSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("****************************** HARD PULL enterHardPullCoSignerSSNFromDP *********************"+dataPoolArgs.get("cosigner_ssn_textbox"));
		String[] inputs = dataPoolArgs.get("cosigner_ssn_textbox").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterSoftPullSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("****************************** SOFT PULL enterSSNFromDP *********************"+dataPoolArgs.get("ssn_txb"));
		String[] inputs = dataPoolArgs.get("ssn_txb").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterSpouseSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterSpouseSSNFromDP *********************"+dataPoolArgs.get("spouseSsn_INT"));
		String[] inputs = dataPoolArgs.get("spouseSsn_INT").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterCsrSpouseSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterSpouseSSNFromDP *********************"+dataPoolArgs.get("CsrspouseSsn_INT"));
		String[] inputs = dataPoolArgs.get("CsrspouseSsn_INT").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterEsignSpouseSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterEsignSpouseSSNFromDP *********************"+dataPoolArgs.get("enterEsignSpouseSSN"));
		String[] inputs = dataPoolArgs.get("enterEsignSpouseSSN").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterEsignCsrSpouseSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterEsignCsrSpouseSSNFromDP *********************"+dataPoolArgs.get("enterEsignCsrSpouseSSN"));
		String[] inputs = dataPoolArgs.get("enterEsignCsrSpouseSSN").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void enterEsignSpouseDOBFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterEsignSpouseDOBFromDP *********************"+dataPoolArgs.get("enterEsignSpouseDOB"));
		String[] inputs = dataPoolArgs.get("enterEsignSpouseDOB").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();
	}

	public void enterSpouseDOBFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterSpouseDOBFromDP *********************"+dataPoolArgs.get("Spouse_DOB"));
		String[] inputs = dataPoolArgs.get("Spouse_DOB").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();
	}

	public void enterCosignerSpouseDOBFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterCosignerSpouseDOBFromDP *********************"+dataPoolArgs.get("CsrSpouse_DOB"));
		String[] inputs = dataPoolArgs.get("CsrSpouse_DOB").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();
	}

	public void getData(String val) throws TwfException {

		driver = DriverFactory.getDriver();

	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}

	/*****************************************************
	 * * Method to scroll to the element and click
	 * 
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/
	public void scrollClick(String element) throws BiffException, IOException,
	TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement button = getElementByUsing(element);
		int x = button.getSize().getWidth();
		int y = button.getSize().getHeight();
		try {
			if (element.contains("submitionAck")
					|| element.contains("saveAndContinue")
					|| element.contains("authorize")
					|| element.contains("disclosureContinue")
					|| element.contains("selectProductAdjustment_CB")
					) {
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,document.body.scrollHeight)", "");
			} else if (element.contains("selectAdjustmentTable")
					|| element.contains("documentsPage_LBL")
					|| element.contains("RatesPageButton")
					|| element.contains("viewAndEsign_LNK")) {
				Thread.sleep(1000);
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x
						+ ",-document.body.scrollHeight)", "");
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x
						+ ",document.body.scrollHeight/20+" + y + 5
						+ ")", "");

				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,300)");

				//				getElementByUsing(element.split(",")[0]).click();
				Thread.sleep(1000);
			} else {
				Thread.sleep(1000);
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x
						+ ",document.body.scrollHeight/20+" + y + 5
						+ ")", "");
			}

			try {

				getElementByUsing(element.split(",")[0]).click();
			}
			catch(Exception e) {
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,100)");
				try {

					getElementByUsing(element.split(",")[0]).click();
				}
				catch(Exception e1) {
					((JavascriptExecutor) getWebDriver()).executeScript(
							"window.scrollBy(0,-100)");
				}
			}

		} catch (Exception e) {
			addExceptionToReport(e.getMessage() , "NULL", element);
		}
	}
	
	public void adoptandsign_Click(String element) throws BiffException, IOException,
	TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement button = getElementByUsing(element);
		int x = button.getSize().getWidth();
		int y = button.getSize().getHeight();
		int i=0;
		while(i<5) {
			try {
				getElementByUsing(element.split(",")[0]).click();
				break;
			}
			catch(Exception e) {
				new Actions(getWebDriver()).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
				try {
					getElementByUsing(element.split(",")[0]).click();
					break;
				}
				catch(Exception e1) {
					new Actions(getWebDriver()).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).build().perform();
				}
			}
			i++;
		}

	}

	/*****************************************************
	 * * Method to scroll to the element and click
	 * 
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/
	public void scrollClick_enter(String element) throws BiffException, IOException,
	TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement button = getElementByUsing(element);
		int x = button.getSize().getWidth();
		int y = button.getSize().getHeight();
		try {
			if (element.contains("submitionAck")
					|| element.contains("saveAndContinue")
					|| element.contains("authorize")
					|| element.contains("disclosureContinue")
					|| element.contains("selectProductAdjustment_CB")
					) {
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,document.body.scrollHeight)", "");
			} else if (element.contains("selectAdjustmentTable")
					|| element.contains("documentsPage_LBL")
					|| element.contains("RatesPageButton")
					|| element.contains("viewAndEsign_LNK")) {
				Thread.sleep(1000);
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x
						+ ",-document.body.scrollHeight)", "");
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x
						+ ",document.body.scrollHeight/20+" + y + 5
						+ ")", "");

				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,300)");
				Thread.sleep(1000);
			} else {
				Thread.sleep(1000);
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x
						+ ",document.body.scrollHeight/20+" + y + 5
						+ ")", "");
			}
			try {
				new Actions(getWebDriver()).sendKeys(Keys.ENTER).build().perform();
			}
			catch(Exception e) {
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,100)");
				try {
					new Actions(getWebDriver()).sendKeys(Keys.ENTER).build().perform();
				}
				catch(Exception e1) {
					((JavascriptExecutor) getWebDriver()).executeScript(
							"window.scrollBy(0,-100)");
				}
			}

		} catch (Exception e) {
			addExceptionToReport(e.getMessage() , "NULL", element);
		}
	}





	public void clickViewAndEsign(String s) throws TwfException, InterruptedException, BiffException, IOException
	{

		WebDriver driver = DriverFactory.getDriver();
		((JavascriptExecutor) getWebDriver()).executeScript(
				"window.scrollBy(" + 0+ ",document.body.scrollHeight/3+" + 0
				+ ")", "");
		Thread.sleep(1000);

		getElementByUsing(s).click();
		System.out.println("clicked");
		Thread.sleep(2000);
	}

	/*****************************************************
	 * * Method to Enter InputMask values
	 * 
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void enterInputMask(String index, String data) throws TwfException,
	BiffException, IOException {
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		// System.out.println("Reached here");
		actions.moveToElement(getElementByUsing(index));
		((JavascriptExecutor) getWebDriver()).executeScript(
				"window.scrollBy(0,document.body.scrollHeight/5)", "");
		// System.out.println("Reached here");
		actions.click();
		actions.sendKeys("" + data);
		actions.build().perform();
	}
	//TwfException, BiffException, IOException, InvalidFormatException
	public void enterSSN1(Map<String, String> data) throws BiffException, IOException, TwfException, InvalidFormatException,  InterruptedException {

		String SSN = data.get("borrower_ssn_textbox");
		enterInputMask("borrower_ssn_textbox", SSN);
	}

	/*****************************************************
	 * Method to select a loan number in UnderWriter section
	 * 

	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/

	public void selectLoanNumber(String s) throws TwfException, BiffException,
	InvalidFormatException, IOException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		String selectLoanNumber = PageObjectLoader.getObjectRepo()
				.get("selectLoanForApproval_BT").getTargetId();
		selectLoanNumber = selectLoanNumber.replace("xxxxxxx",
				TestDataHandler.getTestData(s));
		driver.findElement(By.xpath(selectLoanNumber)).click();
	}

	public void selectLoanNumber(Map<String, String> data) throws TwfException,
	BiffException, InvalidFormatException, IOException,
	InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String[] loanNumber = data.get("selectLoanNumber").split(",");
		String selectLoanNumber = PageObjectLoader.getObjectRepo()
				.get(loanNumber[0]).getTargetId();
		System.out.println("LN1 = " + selectLoanNumber);
		selectLoanNumber = selectLoanNumber.replace("xxxxxxx",
				data.get("searchLoanNumber_ET"));
		System.out.println("LN2 = " + selectLoanNumber);

		String records = PageObjectLoader.getObjectRepo()
				.get("noOfRecords_LBL").getTargetId();
		System.out.println("records = " + records);
		records = driver.findElement(By.xpath(records)).getText();
		System.out.println("records = " + records);
		records = records.substring(records.indexOf("of") + 3).replaceFirst(
				" records", "");
		System.out.println("Total number of records = " + records);
		double totalRecords = Math.ceil(Double.parseDouble(records) / 50);
		System.out.println("totalRecords = " + (totalRecords));

		for (int i = 1; i <= totalRecords; i++) {
			if (driver.findElements(By.xpath(selectLoanNumber)).size() < 1) {
				String index = PageObjectLoader.getObjectRepo()
						.get("recordsIndex_BT").getTargetId();
				System.out.println("Index = " + index);
				index = index.replace("index", "" + i);
				System.out.println("Index = " + index);
				driver.findElement(By.xpath(index)).click();

			}
		}

		driver.findElement(By.xpath(selectLoanNumber)).click();

	}

	public String popupHandler(String val) throws Exception {
		System.out.println("************** popupHandler ******************");
		WebDriver driver = DriverFactory.getDriver();
		String[] value = KWVariables.getVariables().get(val).split(":");
		String[] opt = value[0].split(",");
		//String alertOptions = value[1];
		//getElementByUsing(value[1]).click();

		Alert alert = null;

		try {
			if(opt[0].equalsIgnoreCase("select")){
				Select options = new Select(getElementByUsing(opt[1]));
				options.selectByVisibleText(opt[2]);
			}else{
				getElementByUsing(opt[1]).click();
			}
			System.out.println("Selection is done !!!!!!!!!!!");
			Thread.sleep(6000);
			alert = driver.switchTo().alert();
			Thread.sleep(2000);
			String alertMessage = alert.getText().trim();
			System.out.println("******************* alert message : " + alertMessage);
			if (value[1].equalsIgnoreCase("OK")) {
				alert.accept();
				System.out.println("OK!!!!!!!!!!!!!!!!!1");
			}else if (value[1].equalsIgnoreCase("CANCEL")) {
				alert.dismiss();
				System.out.println("CANCEL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

			}
			System.out.println("******************** alertMessage *************** :: "+alertMessage);
			return (alertMessage);
		} catch (Exception e) {
			return "No alert found";
		}
	} 

	public void enterHardPullSSNFromDPForStud(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("****************************** HARD PULL enterSSNFromDP *********************"+dataPoolArgs.get("students_ssn_txb"));
		String[] inputs = dataPoolArgs.get("students_ssn_txb").split(":");
		//String inputs[] = KWVariables.getVariables().get(input).split(":");
		System.out.println("======"+inputs[1]);
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(inputs[0]));

		System.out.println("Reached here"+inputs[1]);
		Thread.sleep(500);
		actions.click();

		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.sendKeys("" + inputs[1]);
		actions.build().perform();

	}

	public void switchToFrameByFrameName(String value)
			throws JXLException, InvalidFormatException, IOException, TwfException {
		try {
			Thread.sleep(30000);
			DriverFactory.getDriver().switchTo().frame(value);
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		//    	try {
		//    			DriverFactory.getDriver().switchTo().frame(Integer.parseInt(value));
		//    		} catch (Exception e) {
		//    	}

	}

	public void switchOutOfIFrame(String values) throws TwfException, InterruptedException {
		driver = DriverFactory.getDriver();
		driver.switchTo().defaultContent();
	}

	public void navigateToSpouseURLFromMail(String strURL)
			throws JXLException, InvalidFormatException, IOException, TwfException {
		try {
			Thread.sleep(30000);
			DriverFactory.getDriver().get(strURL);
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	public void closeOpenedBrowser(String s) throws TwfException,
	BiffException, IOException, InvalidFormatException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		Thread.sleep(6000);
		driver.close();
	}

	public void openNewTab(String s) throws TwfException, BiffException,
	InvalidFormatException, IOException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		try {
			((JavascriptExecutor) driver).executeScript("window.focus();");
			Thread.sleep(500);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			focusNewWindow(s);
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	public static String parentWindow;

	public void focusNewWindow(String string) throws TwfException,
	BiffException, IOException, InvalidFormatException,
	InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

	}

	public void closeWindow(String string) throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		getWebDriver().switchTo().window(getWebDriver().getWindowHandle())
		.close();
		driver.switchTo().window(parentWindow);
		driver.switchTo().defaultContent();
	}

	/******************************************************************************
	 * fileuploadUsingAutoITtool method performs file uploading using autoit tool
	 * @author Mitali Tarun
	 * @return None
	 * @throws IOException
	 * @throws BiffException
	 * @throws TwfException
	 * @throws InterruptedException 
	 * @throws  InvalidFormatException
	 ******************************************************************************/
	public void fileuploadUsingAutoITtool(String values)
			throws TwfException, BiffException, InvalidFormatException, IOException, InterruptedException {
		String parameters = KWVariables.getVariables().get(values);
		String element = parameters.split(",")[0];
		String file_path = parameters.split(",")[1];

		getElementByUsing(element).click();
		Thread.sleep(3000);
		System.out.println("open the file");
		Runtime.getRuntime().exec(file_path);
		System.out.println("upload the file");
		Thread.sleep(3000);

	}


	/*****************************************************
	 * Method to Upload a file
	 * 
	 * @param fileLocation
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 *****************************************************/

	public static void uploadFile(String fileLocation) {
		try {
			WebDriver driver = DriverFactory.getDriver();
			((JavascriptExecutor) driver).executeScript("window.focus();");
			Thread.sleep(1000);
			setClipboardData(TestDataHandler.getTestData(fileLocation));
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}



	}

	public static void setClipboardData(String data) {
		StringSelection stringSelection = new StringSelection(data);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	} 




	/*****************************************************
	 * * Method to Click View And Esign and upload documents
	 * 
	 * @Author Manjunath S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/

	public void clickViewAndEsignUpload(String s)
			throws TwfException, InterruptedException, BiffException, IOException {

		WebDriver driver = DriverFactory.getDriver();

		Thread.sleep(3000);

		// System.out.println("Reached here");
		((JavascriptExecutor) getWebDriver())
		.executeScript("window.scrollBy(" + 0 + ",document.body.scrollHeight/3+" + 0 + ")", "");
		Thread.sleep(3000);

		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", getElementByUsing(s));

		// getElementByUsing(s).click();
		// // System.out.println("clicked");
		Thread.sleep(2000);

		//

	}




	/************************************************************************************************************************
	 * The scrollElementIntoView method will perform window scroll operation for specific offset values 
	 * @Param  A String with offset values(x-offset and y-offset)
	 * @return none
	 * @throws IOException, TwfException
	 * @throws BiffException 
	 * @throws InterruptedException 
	 * @throws InvalidFormatException 
	 *************************************************************************************************************************/
	public void scrollElementIntoView(String values) throws TwfException, InterruptedException, BiffException, InvalidFormatException, IOException{
		String parameters= KWVariables.getVariables().get(values);
		String args[] = parameters.split("#");
		driver = DriverFactory.getDriver();
		((JavascriptExecutor) driver).executeScript("window.scrollBy("+Integer.parseInt(args[0])+","+Integer.parseInt(args[1])+")");


	}
	public void scrollUpToElement(String ele) throws TwfException, BiffException, IOException {
		driver = DriverFactory.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement button = getElementByUsing(ele);
		js.executeScript("arguments[0].scrollIntoView();", button);


	}

	public void fileuploadUsingRobot(String s) throws TwfException, BiffException,
	InvalidFormatException, IOException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		try {

			String value = KWVariables.getVariables().get(s);

			StringSelection ss = new StringSelection(value);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			//imitate mouse events like ENTER, CTRL+C, CTRL+V
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(500);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(500);
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}
	
	public void clearAndType(String value)
			throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException {
		driver=DriverFactory.getDriver();
		Actions actions = new Actions(driver);
		actions.moveToElement(getElementByUsing(value));
		Thread.sleep(1000);
		actions.click();
		actions.sendKeys("45000");
		Thread.sleep(1000);
		actions.build().perform();
		Thread.sleep(1000);

	}
	
}


