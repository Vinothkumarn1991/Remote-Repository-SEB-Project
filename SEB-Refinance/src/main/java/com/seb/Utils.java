package com.seb;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoAlertPresentException;
import javax.swing.text.TabExpander;
import org.openqa.selenium.Alert;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.mysql.jdbc.Driver;
import com.tavant.base.DriverFactory;
import com.tavant.base.WebPage;
import com.tavant.utils.TwfException;
import com.tavant.kwutils.*;

import junit.framework.Assert;
import jxl.read.biff.BiffException;

import org.openqa.selenium.JavascriptExecutor;

@SuppressWarnings("unused")
public class Utils extends WebPage {

	private static boolean DEBUG_MODE = true;
	public static String initialMonthlyLiab, finalMonthlyLiab;

	public static void debugStatement(Object s) {
		if (DEBUG_MODE) {
			try {
				// System.out.println(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*****************************************************
	 * Method to check the visibility of elements
	 * 
	 * @param elements
	 * @Author Bheesham S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void verifyVisibilityOfElements(String elements)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String z[] = TestDataHandler.getTestData(elements).split(",");
		for (int i = 0; i < z.length; i++) {
			Thread.sleep(500);
			if (!(getElementByUsing(z[i]).isDisplayed())) {
				addExceptionToReport("Unable to find Element " + z[i], null, "Element should be displayed");
			} else {
			}
		}
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
	 * * Method to scroll to the element and click
	 * 
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/
	public void scrollClick(String element) throws BiffException, IOException, TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement button = getElementByUsing(element);

		int x = button.getSize().getWidth();
		int y = button.getSize().getHeight();
		try {

			if (element.contains("submitionAck") || element.contains("saveAndContinue") || element.contains("authorize")
					|| element.contains("disclosureContinue") || element.contains("selectProductAdjustment_CB")
					|| element.contains("Additional point of contact")) {

				((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)",
						"");
			} else if (element.contains("selectAdjustenterCoSignerSpouseSSNmentTable")
					|| element.contains("documentsPage_LBL") || element.contains("RatesPageButton")) {
				Thread.sleep(1000);
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(" + x + ",-document.body.screnterCoSignerSpouseSSNollHeight)", "");
				((JavascriptExecutor) getWebDriver())
				.executeScript("window.scrollBy(" + x + ",document.body.scrollHeight/20+" + y + 5 + ")", "");

				getElementByUsing(element.split(",")[0]).click();
				Thread.sleep(500);
			} else if (element.contains("Underwriting_LBL")) {
				Thread.sleep(1000);
				((JavascriptExecutor) getWebDriver())
				.executeScript("window.scrollBy(" + x + ",-document.body.scrollHeight)", "");

				// getElementByUsing(element.split(",")[0]).click();
				// Thread.sleep(500);
			} else if (element.contains("Invite Co-Signer")) {

				Thread.sleep(500);
				((JavascriptExecutor) getWebDriver())
				.executeScript("window.scrollBy(" + x + ",document.body.scrollHeight/20+" + y + 2 + ")", "");
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

		}
		catch (Exception e) {
			addExceptionToReport("Problem in finding element ", "NULL", element);
		}
	}
	
	public void scrollToElement(String ele) throws TwfException, BiffException, IOException, InvalidFormatException {
		System.out.println("********************** scrollToElement ********************");
		WebDriver driver = DriverFactory.getDriver();
		//driver = DriverFactory.getDriver();
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

	public void add_Click(String element) throws BiffException, IOException,
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
	 * * Method to wait for element to appear
	 * 
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/
	public void waitForElement(String element) throws TwfException, BiffException, IOException {
		WebElement actual_element = getElementByUsing(element);
		(new WebDriverWait(getWebDriver(), 18000)) // timeout after 60
		.until(ExpectedConditions.visibilityOf(actual_element));
	}

	/***************** Methods to get and set the data ***********************/
	public void selectSchoolGraduated(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String schoolGraduated = data.get("schoolGraduated_ET");
		autoPopulate("schoolGraduated_ET", schoolGraduated);
	}

	public void selectUGSchoolGraduated(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String schoolGraduated = data.get("UGSchoolGraduated_ET");
		autoPopulate("UGSchoolGraduated_ET", schoolGraduated);
	}

	public void enterSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {

		WebDriver driver = DriverFactory.getDriver();
		// ((JavascriptExecutor)
		// getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight/-5)",
		// "");

		String SSN = data.get("ssn_INT");
		System.out.println("ssn is :"+SSN);
		enterInputMask("ssn_INT", SSN);
		System.out.println("enter ssn completed");

		// ((JavascriptExecutor)
		// getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight/5)",
		// "");

	}

	public void enterCoSignerSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		String SSN = data.get("Co_ssn_INT");
		enterInputMask("Co_ssn_INT", SSN);


	}

	public void enterCoSignerSpouseSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {

		WebDriver driver = DriverFactory.getDriver();

		String SSN = data.get("Co_spouseSsn_INT");
		enterInputMask("Co_spouseSsn_INT", SSN);


	}

	public void enterSpouseSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		
		WebDriver driver = DriverFactory.getDriver();
		String spouseSSN = data.get("spouseSsn_INT");
		
		enterInputMask("spouseSsn_INT", spouseSSN);
       		
	}
	
	public void eSignSpouseSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {

		WebDriver driver = DriverFactory.getDriver();

		String spouseSSN = data.get("spouseSsn_INT");

		enterInputMask("Co_ssn_INT1", spouseSSN);
		
		

	}

	public void enterSpouseSSNFromDP(Map<String, String> dataPoolArgs) throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException
	{
		System.out.println("******************************  enterSpouseSSNFromDP *********************"+dataPoolArgs.get("spouseSsn_INT"));
		String[] inputs = dataPoolArgs.get("Co_ssn_INT11").split(":");
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
	public void eSignCoSignerSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {

		WebDriver driver = DriverFactory.getDriver();

		String spouseSSN = data.get("Co_ssn_INT");

		enterInputMask("cosigne_ssn_INT1", spouseSSN);

	}

	public void eSignCoSignerSpouseSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {

		String SSN = data.get("Co_spouseSsn_INT");

		enterInputMask("Co_ssn_INT1", SSN);

	}

	public void esignBorrowerSSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {

		String SSN = data.get("ssn_INT");

		enterInputMask("cosigne_ssn_INT1", SSN);

	}

	public void eSignSpouseDOB(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String dob = data.get("spouseDob_ET");
		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing("dob_ET").sendKeys(dob);

	}

	public void enterReapplySSN(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String SSN = data.get("ssn_enternew");
		enterInputMask("reapplySsn_INT", SSN);
	}

	public void enterReapplyDOB(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String dob = data.get("dob_ET");
		enterInputMask("reapplyDob_ET", dob);
	}

	public void eSignCoSignerSpouseDOB(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String dob = data.get("Co_spouseDob_ET");
		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing("dob_ET").sendKeys(dob);
	}

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
				new Actions(getWebDriver()).moveToElement(button).sendKeys(Keys.ENTER).build().perform();
			}
			catch(Exception e) {
				((JavascriptExecutor) getWebDriver()).executeScript(
						"window.scrollBy(0,100)");
				try {
					new Actions(getWebDriver()).moveToElement(button).sendKeys(Keys.ENTER).build().perform();
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


	public void enterTotalgrossIncome(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String grossIncome = data.get("totalGrossIncome_ET");
		autoPopulate("totalGrossIncome_ET", grossIncome);
	}

	public void preTotalgrossIncome(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String grossIncome = data.get("preTotalGrossIncome_ET");
		autoPopulate("preTotalGrossIncome_ET", grossIncome);
	}

	public void enterAccountNumber(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String accNo = data.get("SLAccountNo_INT");
		enterInputMask("SLAccountNo_INT", accNo);
	}

	public void enterAutoPayAccNumber(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String accNo = data.get("autoPayAccountNo_ET");
		enterInputMask("autoPayAccountNo_ET", accNo);
	}

	public void enterConfAutoPayAccNumber(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String accNo = data.get("autoPayConfirmAccountNo_ET");
		enterInputMask("autoPayConfirmAccountNo_ET", accNo);
	}

	public void enterHouseExpense(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException {
		String expense = data.get("housingExpense_ET");
		autoPopulate("housingExpense_ET", expense);
	}

	public void enterLoanType(Map<String, String> data)
			throws BiffException, IOException, TwfException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		String xpth = PageObjectLoader.getObjectRepo().get("studentLoanType_DD").getTargetId();
		Select selectByValue = new Select(driver.findElement(By.xpath(xpth)));
		selectByValue.selectByVisibleText(data.get("studentLoanType_DD"));
		getElementByUsing("studentLoanType_DD").sendKeys(Keys.ENTER);
	}

	/*****************************************************
	 * * Method to AutoPopulate the fields after typing
	 * 
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/
	public void autoPopulate(String index, String data)
			throws BiffException, IOException, TwfException, InterruptedException {
		getElementByUsing(index).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Thread.sleep(200);
		getElementByUsing(index).sendKeys(data);
		Thread.sleep(1400);

	}

	/*****************************************************
	 * * Method to Enter InputMask values
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void enterInputMask(String index, String data)
			throws TwfException, BiffException, IOException, TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		Actions actions = new Actions(driver);

		actions.moveToElement(getElementByUsing(index));
		//((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight/5)", "");

		actions.click();
		Thread.sleep(2000);
		actions.sendKeys("" + data);
		Thread.sleep(1000);
		actions.build().perform();
		
	}

	/*****************************************************
	 * Method to click elements sequentially
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/

	public void loopClicks(String element)
			throws BiffException, InvalidFormatException, IOException, TwfException, InterruptedException {
		String[] params = TestDataHandler.getTestData(element).split(",");
		for (int i = 0; i < params.length; i++) {
			Thread.sleep(800);
			waitForElement(params[i]);
			scrollClick(params[i]);

		}
	}

	/*****************************************************
	 * Method to verify the Adverse Action report generated
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void checkSoftPoolReport(String data)
			throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		String[] report = TestDataHandler.getTestData(data).split(",");
		String xpth = PageObjectLoader.getObjectRepo().get(report[0]).getTargetId();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(xpth)).size() != 0) {
			Thread.sleep(3000);
			getElementByUsing(report[1]).click();
		} else {
			addExceptionToReport("Report Not generated . Loan Qualified ", "NULL", "Adverse Action Report");
		}

	}

	/*****************************************************
	 * Method to check the element doesnot exist
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void checkElementNotPresent(String element)
			throws TwfException, BiffException, InvalidFormatException, IOException {
		String[] data = element.split(",");
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String xpth = PageObjectLoader.getObjectRepo().get(data[0]).getTargetId();
		if (driver.findElements(By.xpath(xpth)).size() > 0) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			addExceptionToReport("Element " + data[0], "Exists", "Should not exist");
		} else {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	/*****************************************************
	 * Method to select a loan number in UnderWriter section
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/

	public void selectLoanNumber(String s)
			throws TwfException, BiffException, InvalidFormatException, IOException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		String selectLoanNumber = PageObjectLoader.getObjectRepo().get("selectLoanForApproval_BT").getTargetId();
		selectLoanNumber = selectLoanNumber.replace("xxxxxxx", TestDataHandler.getTestData(s));
		driver.findElement(By.xpath(selectLoanNumber)).click();
	}

	public void selectLoanNumber(Map<String, String> data)
			throws TwfException, BiffException, InvalidFormatException, IOException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		String[] loanNumber = data.get("selectLoanNumber").split(",");
		String selectLoanNumber = PageObjectLoader.getObjectRepo().get(loanNumber[0]).getTargetId();
		// System.out.println("LN1 = " + selectLoanNumber);
		selectLoanNumber = selectLoanNumber.replace("xxxxxxx", data.get("searchLoanNumber_ET"));
		// System.out.println("LN2 = " + selectLoanNumber);

		String records = PageObjectLoader.getObjectRepo().get("noOfRecords_LBL").getTargetId();
		// System.out.println("records = " + records);
		records = driver.findElement(By.xpath(records)).getText();
		// System.out.println("records = " + records);
		records = records.substring(records.indexOf("of") + 3).replaceFirst(" records", "");
		// System.out.println("Total number of records = " + records);
		double totalRecords = Math.ceil(Double.parseDouble(records) / 50);
		// System.out.println("totalRecords = " + (totalRecords));

		for (int i = 1; i <= totalRecords; i++) {
			if (driver.findElements(By.xpath(selectLoanNumber)).size() < 1) {
				String index = PageObjectLoader.getObjectRepo().get("recordsIndex_BT").getTargetId();
				// System.out.println("Index = " + index);
				index = index.replace("index", "" + i);
				// System.out.println("Index = " + index);
				driver.findElement(By.xpath(index)).click();

			}
		}

		driver.findElement(By.xpath(selectLoanNumber)).click();

	}



	/*****************************************************
	 * Method to validate the Rates and Liabilities
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void verifyRatesAndLiabilities(String data) throws Exception {
		// System.out.println("Inside the method");
		String[] z = TestDataHandler.getTestData(data).split(",");
		WebDriver driver = DriverFactory.getDriver();
		String tml = MyTestCaseExecuter.stepobject.getKwValueVariables().get(z[0]);
		String liabilities = MyTestCaseExecuter.stepobject.getKwValueVariables().get(z[1]);
		// System.out.println("tml = " + tml);
		if (tml.contains("$")) {
			tml = tml.replace("$", "").replace(",", "");
		} else if (tml.contains("%")) {
			tml = tml.replace("%", "");
		}
		if (liabilities.contains("$")) {
			liabilities = liabilities.replace("$", "").replace(",", "");
		}
		if (Double.parseDouble(liabilities) != Double.parseDouble(tml)) {
			throw new Exception("Mismatch in Monthly Liabilities");
		}
	}

	static double RoundTo2Decimals(double val) {
		DecimalFormat df2 = new DecimalFormat("###.##");
		return Double.valueOf(df2.format(val));
	}

	/*****************************
	 * Rates Maintenance
	 **********************************/

	public static List<String> ficoLimit = new ArrayList<String>();
	public List<String> dtiLimit = new ArrayList<String>();
	public static String rowValues = null;
	public static int index = 0;



	/*****************************************************
	 * Method to approve the loan application
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void appApprovalConfirmation(String s)
			throws BiffException, IOException, TwfException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(500);
		String xpth = PageObjectLoader.getObjectRepo().get(s).getTargetId();
		if ((driver.findElements(By.xpath(xpth)).size() > 0)) {
			if (s.contains("CB") && getElementByUsing(s).isSelected()) {
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

			} else {
				getElementByUsing(s).click();
				((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click();", getElementByUsing(s));
			}
		} else {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		}
	}


	/*****************************************************
	 * Method to get the captcha values
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void enterViewingCode(String element)
			throws TwfException, BiffException, InvalidFormatException, IOException {
		WebDriver driver = DriverFactory.getDriver();
		String[] params = TestDataHandler.getTestData(element).split(",");
		String captchaValue = getElementByUsing(params[0]).getAttribute("src");
		captchaValue = captchaValue.replaceAll("[^0-9]", "");
		// System.out.println("Captcha Value = " + captchaValue);

		getElementByUsing(params[1]).sendKeys(captchaValue);
	}

	/*****************************************************
	 * Method to focus to a new window
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public static String parentWindow;

	public void focusNewWindow(String string)
			throws TwfException, BiffException, IOException, InvalidFormatException, InterruptedException {
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

	/*****************************************************
	 * Method to close the current window
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void closeWindow(String string) throws TwfException {
		WebDriver driver = DriverFactory.getDriver();
		getWebDriver().switchTo().window(getWebDriver().getWindowHandle()).close();
		driver.switchTo().window(parentWindow);
		driver.switchTo().defaultContent();
	}

	public void openNewTab(String s)
			throws TwfException, BiffException, InvalidFormatException, IOException, InterruptedException {
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

	/*****************************************************
	 * Method to wait for an element to dissapear
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/

	public void waitForElementToDisappear(String element)
			throws TwfException, BiffException, IOException, InvalidFormatException {

		(new WebDriverWait(getWebDriver(), 100000)) // timeout after 200
		.until(ExpectedConditions.invisibilityOfElementLocated(
				org.openqa.selenium.By.xpath(PageObjectLoader.getObjectRepo().get(element).getTargetId())));
	}



	/*****************************************************
	 * Method to get the access code for Docmagic
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public static String accessCode;

	public void getAccessCode(String data) {

		accessCode = StringUtils.right(data, 4);
		// System.out.println("AccessCode = " + accessCode);

	}

	/*****************************************************
	 * Method to set the access code for Docmagic
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void setAccessCode(String data) throws TwfException, BiffException, IOException {

		WebDriver driver = DriverFactory.getDriver();
		getElementByUsing(data).sendKeys(accessCode);
	}

	/*****************************************************
	 * Method to click on an element if it is present
	 * 
	 * @author Bheesham S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void clickIfPresent(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {

		WebDriver driver = DriverFactory.getDriver();
		String xpth = PageObjectLoader.getObjectRepo().get(element).getTargetId();
		if (driver.findElements(By.xpath(xpth)).size() > 0) {
			waitForElement(element);
			scrollClick(element);
		} else {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
	}



	/*****************************************************
	 * Method to check the approval status
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void verifyApproval(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement button = getElementByUsing(element);

		String approveCheck = button.getText();

		// System.out.println("Approve status" + approveCheck);

		if (!approveCheck.equals("Approved")) {
			addExceptionToReport("Loan not approved", approveCheck, "Approved");
		}

	}

	/*****************************************************
	 * Method to check the Denial status
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void verifyDenial(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement button = getElementByUsing(element);

		String denialCheck = button.getText();

		// System.out.println("Deny status" + denialCheck);

		if (!denialCheck.equals("Denied")) {
			addExceptionToReport("Loan not approved", denialCheck, "Denied");
		}

	}

	/*****************************************************
	 * Method to check the Dismiss Popup
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void PopupDismiss(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			Thread.sleep(2000);
			// System.out.println("Reached pop up box code");
			if (driver.switchTo().alert() != null) {
				Alert alert = driver.switchTo().alert();
				String alertText = alert.getText();
				// System.out.println("Reached pop up box text" + alertText);
				alert.accept(); // alert.accept();

			}
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}

	}

	/*****************************************************
	 * Method to Scroll to End of page
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void scrollEndOfPage(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	/*****************************************************
	 * Method to Scroll to Top of page
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void scrollTopOfPage(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {
		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");

	}

	/*****************************************************
	 * Method to Retrieve credit
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void retrieveInitialCredit(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {

		Thread.sleep(2000);

		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		initialMonthlyLiab = getElementByUsing(element).getText();
		// System.out.println("Initial Monthly Liability" + initialMonthlyLiab);

	}

	/*****************************************************
	 * Method to Retrieve Final credit
	 * 
	 * @param elements
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws InvalidFormatException
	 *****************************************************/
	public void retrieveFinalCredit(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {

		Thread.sleep(2000);

		WebDriver driver = DriverFactory.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		finalMonthlyLiab = getElementByUsing(element).getText();
		// System.out.println("Final Monthly Liability" + finalMonthlyLiab);

		if (!initialMonthlyLiab.equals(finalMonthlyLiab)) {

			addExceptionToReport("Incorrect Credit pull", initialMonthlyLiab, finalMonthlyLiab);
		}
	}

	/*****************************************************
	 * * Method to scroll to the element and click
	 * 
	 * @Author Vikas S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/
	public void scrollClick2(String element) throws BiffException, IOException, TwfException, InterruptedException {
		WebDriver driver = DriverFactory.getDriver();
		WebElement button = getElementByUsing(element);

		int x = button.getSize().getWidth();
		int y = button.getSize().getHeight();
		try {

			if (element.contains("save")) {

				// System.out.println("We are here");
				// ((JavascriptExecutor)
				// getWebDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)",
				// "");
				getElementByUsing(element).click();

			}
		} catch (Exception e) {
			addExceptionToReport("Problem in finding element ", "NULL", element);
		}

	}

	/*****************************************************
	 * * Method to switch out of frame
	 * 
	 * @Author Manjunath S
	 * @throws TwfException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws IOException
	 *****************************************************/

	public void switchOutOfIFrame(String values) throws TwfException, InterruptedException {
		WebDriver driver;
		driver = DriverFactory.getDriver();
		driver.switchTo().defaultContent();
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

	/*****************************************************
	 * Method to click on an continue at underwriter screen
	 * 
	 * @author Vikas S
	 * @throws TwfException
	 * @throws IOException
	 * @throws BiffException
	 * @throws InvalidFormatException
	 * @throws InterruptedException
	 *****************************************************/
	public void clickContinue(String element)
			throws TwfException, BiffException, IOException, InterruptedException, InvalidFormatException {

		WebDriver driver = DriverFactory.getDriver();
		String xpth = PageObjectLoader.getObjectRepo().get(element).getTargetId();
		if (driver.findElements(By.xpath(xpth)).size() > 0) {
			waitForElement(element);
			scrollClick(element);
		} else {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
	}

	@Override
	public void checkPage() {
		// TODO Auto-generated method stub

	}
	public void verifyEmailSubjectLine(String values) throws TwfException, BiffException, InvalidFormatException, IOException {

        WebDriver driver=DriverFactory.getDriver();

            String errormsg="Mismatching Email subjectline";
            System.out.println("The expected email subject line is "+values);

            String actualemailsubjectline=driver.findElement(By.xpath("//div[@class='ellipsis nw b f18']")).getText();
            System.out.println("The actual email subject line is "+actualemailsubjectline);

            if(actualemailsubjectline.equals(values)) {

                System.out.println("Email subjectline Verified successfully");

            }
            else {
                System.out.println("Mismatching Email subjectline ");
                Assert.fail(errormsg);                
            }

        }
}