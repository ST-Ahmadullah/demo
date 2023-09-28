package com.Tcarts_FPO;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.baseClass.Base;
import com.helper.FileReaderManager;
import com.helper.PageObjectManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Tcarts_FPO_Demo extends Base {

	public static WebDriver driver = Base.initializeWebDriver("chrome");
	public static PageObjectManager pom = new PageObjectManager(driver);
	public static Logger log = Logger.getLogger(Tcarts_FPO_Demo.class);

	static ExtentTest test;
	static ExtentReports report;

	@BeforeClass
	public static void startTest() {

		report = new ExtentReports("//home//st//eclipse-workspace//Tcarts_Login//Report//ExtentReport//Report.html");
		test = report.startTest("ExtentReport");
	}

	@Test(priority = 0)
	public void setUp() throws Throwable {

		PropertyConfigurator.configure("log4j.properties");

		log.info("URL");

		getUrl("https://tcfoodapp.sumanas.xyz/login");

		if (driver.getTitle().equals("TCARTS Food PreOrdering")) {
			test.log(LogStatus.PASS, "Navigated to the specified URL");
		} else {
			test.log(LogStatus.FAIL, "Test Failed");
		}

		waitForElement("implicit", 30, null);
	}

	@Test(dependsOnMethods = { "setUp" })
	public void login() throws Throwable {

		log.info("Login");

		String email = FileReaderManager.getInstanceFRM().getInstanceCR().getEmail();
		sendKeys(pom.getInstanceli().getUsername(), email);

		String password = FileReaderManager.getInstanceFRM().getInstanceCR().getPassword();
		sendKeys(pom.getInstanceli().getPassword(), password);

		waitForElement("static", 1000, null);

		performAction("click", pom.getInstanceli().getSignin(), 0);

	}

	@Test(priority = 1)
	public void dashboard() throws Throwable {

		log.info("Dashboard");

		performAction("click", pom.getInstancedb().getFullscreen(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getMenu(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancedb().getSvg(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getMenu(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancedb().getPng(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getMenu(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancedb().getCsv(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getFullscreen(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getIconview(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancedb().getDashboard(), 0);
		waitForElement("static", 1000, null);
	}

	@Test(priority = 2)
	public void sales() {

		log.info("Sales");

		performAction("click", pom.getInstancedb().getSales(), 0);
		waitForElement("static", 1500, null);

	}

	@Test(dependsOnMethods = { "sales" })
	public void receipts() throws Throwable {

		log.info("Receipt List");

		performAction("click", pom.getInstancedb().getReceipts(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getFullscreen(), 0);
		
		performAction("click", pom.getInstancerl().getAdvanceSearch(), 0);
		waitForElement("static", 1500, null);

		log.info("Payment Pending");

		performAction("click", driver.findElement(By.xpath("//label[@for='payment_status_P']")), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getSearchButton(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getReset(), 0);

		log.info("In Progress");

		performAction("click", driver.findElement(By.xpath("//label[@for='payment_status_W']")), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getSearchButton(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getReset(), 0);

		log.info("Failed");

		performAction("click", driver.findElement(By.xpath("//label[@for='payment_status_F']")), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getSearchButton(), 0);
		waitForElement("static", 1000, null);

		String f = FileReaderManager.getInstanceFRM().getInstanceCR().getReceiptFailed(); // Search
		sendKeys(pom.getInstanceco().getSearch(), f);
		waitForElement("static", 1500, null);

		performAction("click", pom.getInstancerl().getViewitem(), 0); // Item Status
		waitForElement("static", 2000, null);
		performAction("click", pom.getInstancerl().getItemstatus(), 0);
		waitForElement("static", 2000, null);

		performAction("click", pom.getInstanceco().getView(), 0); // View
		waitForElement("static", 2000, null);
		performAction("click", pom.getInstanceco().getCloseview(), 0);
		waitForElement("static", 2000, null);

		performAction("click", driver.findElement(By.xpath("//button[@title='Check Billdesk']")), 0); // Check Billdesk
		waitForElement("static", 2000, null);
		performAction("click", driver.findElement(By.xpath("(//button[@class='btn-close'])[2]")), 0);
		waitForElement("static", 3000, null);

		WebElement v1 = driver.findElement(By.xpath("//div[@class='badge badge-danger']")); // Validation
		String actual1 = v1.getText();
		System.out.println(actual1);
		String expected1 = "Failed";
		assertEquals(expected1, actual1);

		performAction("click", pom.getInstanceco().getRefresh(), 0); // Refresh
		waitForElement("static", 2000, null);

		performAction("scrollintoview", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getNext(), 0); // Page - Next & Previous
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getPrevious(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollby", null, -500);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getReset(), 0); // Reset
		waitForElement("static", 1000, null);

		log.info("Cancelled");

		performAction("click", driver.findElement(By.xpath("//label[@for='payment_status_C']")), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getSearchButton(), 0);
		waitForElement("static", 1000, null);

		String c = FileReaderManager.getInstanceFRM().getInstanceCR().getReceiptCancelled();
		sendKeys(pom.getInstanceco().getSearch(), c);
		waitForElement("static", 1500, null);

		performAction("click", pom.getInstancerl().getViewitem(), 0);
		waitForElement("static", 2000, null);
		performAction("click", pom.getInstancerl().getItemstatus(), 0);
		waitForElement("static", 2000, null);

		performAction("click", pom.getInstanceco().getView(), 0);
		waitForElement("static", 2000, null);
		performAction("click", pom.getInstanceco().getCloseview(), 0);
		waitForElement("static", 2000, null);

		performAction("click", driver.findElement(By.xpath("//button[@title='Check Billdesk']")), 0);
		waitForElement("static", 2000, null);
		performAction("click", driver.findElement(By.xpath("(//button[@class='btn-close'])[2]")), 0);
		waitForElement("static", 3000, null);

		WebElement v2 = driver.findElement(By.xpath("//div[@class='badge badge-dark']")); // Validation
		String actual2 = v2.getText();
		System.out.println(actual2);
		String expected2 = "Cancelled";
		assertEquals(expected2, actual2);

		performAction("click", pom.getInstanceco().getRefresh(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollintoview", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 2500, null);
		performAction("click", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getPrevious(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollby", null, -300);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getReset(), 0);
		waitForElement("static", 1000, null);

		log.info("Paid");

		performAction("click", driver.findElement(By.xpath("//label[@for='payment_status_S']")), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getSearchButton(), 0);
		waitForElement("static", 1000, null);

		String p = FileReaderManager.getInstanceFRM().getInstanceCR().getReceiptPaid();
		sendKeys(pom.getInstanceco().getSearch(), p);
		waitForElement("static", 1500, null);

		performAction("click", pom.getInstancerl().getViewitem(), 0);
		waitForElement("static", 2000, null);
		performAction("click", pom.getInstancerl().getItemstatus(), 0);
		waitForElement("static", 2000, null);

		performAction("click", pom.getInstanceco().getView(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstanceco().getCloseview(), 0);
		waitForElement("static", 2000, null);

		performAction("click", driver.findElement(By.xpath("//button[@title='Check Billdesk']")), 0);
		waitForElement("static", 2000, null);
		performAction("click", driver.findElement(By.xpath("(//button[@class='btn-close'])[2]")), 0);
		waitForElement("static", 3000, null);

		WebElement v3 = driver.findElement(By.xpath("//div[@class='badge badge-success']")); // Validation
		String actual3 = v3.getText();
		System.out.println(actual3);
		String expected3 = "Paid";
		assertEquals(expected3, actual3);

		performAction("click", pom.getInstanceco().getRefresh(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollintoview", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 2500, null);
		performAction("click", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getPrevious(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollby", null, -300);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getReset(), 0);
		waitForElement("static", 1000, null);

		log.info("All");

		performAction("click", driver.findElement(By.xpath("//label[@for='selectAllStatuses']")), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancerl().getSearchButton(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollintoview", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 2500, null);
		performAction("click", pom.getInstancerl().getNext(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getPrevious(), 0);
		waitForElement("static", 1000, null);

		performAction("scrollby", null, -900);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancerl().getReset(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancerl().getAdvanceSearch(), 0);
		waitForElement("static", 1500, null);
		performAction("click", pom.getInstancedb().getFullscreen(), 0);
		waitForElement("static", 1500, null);

	}

	@Test(priority = 3)
	public void appFeedback() {

		log.info("App Feedback");

		performAction("click", pom.getInstancedb().getAppfeedback(), 0);
		waitForElement("static", 2000, null);

		performAction("click", pom.getInstancedb().getFullscreen(), 0);

		sendKeys(pom.getInstancefb().getSearch(), "Tester"); // 1
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancefb().getView(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstanceco().getCloseview(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancefb().getRefresh(), 0);
		waitForElement("static", 1000, null);

		sendKeys(pom.getInstancefb().getSearch(), "Developer"); // 2
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancefb().getView(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstanceco().getCloseview(), 0);
		waitForElement("static", 1000, null);
		performAction("click", pom.getInstancefb().getRefresh(), 0);
		waitForElement("static", 1000, null);

		performAction("click", pom.getInstancedb().getFullscreen(), 0);
		waitForElement("static", 1000, null);

	}

	@Test(priority = 4)
	public void tearDown() throws Throwable {

		log.info("Logout");

		performAction("click", pom.getInstancelo().getAdmin(), 0);
		waitForElement("static", 2000, null);
		performAction("click", pom.getInstancelo().getLogout(), 0);
		waitForElement("static", 1500, null);
		quit();
	}

	@AfterClass
	public static void endTest() {
		report.endTest(test);
		report.flush();
	}

}
