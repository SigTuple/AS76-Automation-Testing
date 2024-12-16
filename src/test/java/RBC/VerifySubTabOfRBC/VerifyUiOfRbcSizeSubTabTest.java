package RBC.VerifySubTabOfRBC;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import utilities.BrowserSetUp;

public class VerifyUiOfRbcSizeSubTabTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(VerifyUiOfRbcSizeSubTabTest.class);

	public  WebDriver driver;
	public WebDriverWait wait;
	 int time=30;
	Properties props;
	private CommonMethods cms;
	 private VerifyUiOfRbcSizeSubTab verifySubtbOfRbc;
	 private String tabName="Size";
	 
	@BeforeSuite
	public void driver() throws Exception {
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		wait = new WebDriverWait(driver, 30);
		props = Property.prop;
		Property.readRBCProperties();
		verifySubtbOfRbc=new VerifyUiOfRbcSizeSubTab(driver);
		cms=new CommonMethods(driver);
	}


		//____________________ RBC Sub tab related scripts are written inside the class______________________//
	
	// click on RBC TAb 
	@Test(priority=1,enabled=true)
	public void openToBeReviewedStatusReport() throws InterruptedException {
		test=extent.createTest("RCB Size Tab Test");
		String status=verifySubtbOfRbc.selectToBeReviewedReport();
		Assert.assertEquals(status,"To be reviewed");
		logger.info("To be reviewed status report is selected from list report");
		
	}
	@Test(priority = 3,enabled = true)
	public void clickedOnRbcTab() throws InterruptedException {
		Assert.assertEquals(verifySubtbOfRbc.clickOnRBCTab(), "RBC");
	}

	@Test(priority =5,enabled = true)
	public void presenceOfSubTabNameOnRbc(){
		String subTabName=verifySubtbOfRbc.presenceOfSubTabNameOnRbc();
		Assert.assertNotEquals(subTabName,"Size\n" +
				"Shape\n" +
				"Color\n" +
				"Inclusions");
		logger.info("all sub tab names are present on RBC" + subTabName);
	}

	// verifying the header name on Size tab
	@Test(priority = 9,enabled = true)
	public void verifyHeaderNameOnSizeSubTab(){
		String HeaderName=verifySubtbOfRbc.presenceOfTabHeaderName();
		Assert.assertEquals(HeaderName,"Cell name\n" +
				"Grade\n" +
				"0\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"%");
		logger.info("header name is verified on Size sub tab :" + HeaderName);

	}
	// verify the cell name present on size sub tab
	@Test(priority = 11,enabled = true)
	public void verifyTheCellNameAndItsOrderOnSizeSubTab() throws InterruptedException {
		String cellName= String.valueOf(verifySubtbOfRbc.cellNamesOnSubTab());
		Assert.assertEquals(cellName,"[Microcytes, Macrocytes, Anisocytosis]");
		logger.info("cell name and its order is verified on Size sub tab");

	}
	// verify the note message on size tab
	@Test(priority = 13,enabled = true)
	public void noteTextMessageOnSizeTab(){
		boolean noteTextMessage= verifySubtbOfRbc.presenceOfNoteText("size");
		Assert.assertTrue(noteTextMessage);
		logger.info("Note Text Message is verified on Size Tab:" + noteTextMessage);
	}
	// verifying the presence of significant-info
	@Test(priority = 15,enabled = true)
	public void significant_info(){
		String significantInfo= verifySubtbOfRbc.significantInfo();
		Assert.assertEquals(significantInfo,"Significant\n" +
				"Non Significant");
		logger.info("significant and non significant text are verified on Rbc size tab");
	}

	// verifying default cell selection on RBC Size tab
	@Test(priority = 16, enabled = true)
	public void defaultCellSelection(){
		boolean status=verifySubtbOfRbc.defaultCellSelection("Anisocytosis");
		Assert.assertTrue(status);
		logger.info("Default cell selection on RBC TAB is verified");

	}
	@Test(priority = 18, enabled = false)
	public void clickOnImageSetting() throws InterruptedException {
		driver.findElement(By.xpath("//button[contains(text(),'WBC')]")).click();
		Thread.sleep(2000);
		cms.clickOnImageSetting("//tbody//tr//td[2]","//tbody//tr//td[1]","WBC","/html/body/div[2]/div[3]/div/div[3]/div[1]/div[2]/span/span[9]/input");

	}








	

}
