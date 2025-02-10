package WBC.VerifyWbcParametersTest;

import java.time.Duration;
import java.util.Properties;

import GenericMethodForAllTab.CommonMethods;
import WBC.VerifyWbcParameters.WBCParameters;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Data.Property;
import utilities.BrowserSetUp;

public class WBCParametersTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(WBCParametersTest.class);
	public WebDriverWait wait;
	public WBCParameters wbc;
	private CommonMethods cms;
	WebDriver driver;
	public Properties props;

	@BeforeSuite
	public void driver() throws Exception {
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		wait = new WebDriverWait(driver, 50);
		wbc = new WBCParameters(driver);
		 cms=new CommonMethods(driver);
		props = Property.prop;
		Property.readSessionManagement();

	}
	@Test(priority = 2, enabled = true)
	public void verifySelctionOfWBCTab() throws InterruptedException {
		test = extent.createTest("WBCParametersTest");
		cms.clickOnTab("WBC", props.getProperty("WBCTab"));
		String header = wbc.verifyPresenceAndSelectionOfWBCTab();
		Assert.assertEquals(header, "WBC");
		logger.info("WBC tab is present and selected");
	}
	
	@Test(priority = 4, enabled = true)
	public void verifyTheColumnPresentInTheWBCTab() {
		String header = wbc.verifyTheColumnPresentInTheWBCTab();
		Assert.assertEquals(header, ",Cell name,Count,%");
		logger.info("WBC tab is present and selected");
	}
	
	@Test(priority = 6, enabled = true)
	public void verifyWBCCellsNames() {
		String header = wbc.verifyWBCCells();
		Assert.assertEquals(header, ",Neutrophils,Band Forms,Hypersegmented*,Neutrophils with Toxic Granules*,Lymphocytes,Reactive,Large Granular Lymphocytes*,Eosinophils,Monocytes,Basophils,Atypical Cells/Blasts,Atypical Cells*,Lymphoid Blasts*,Myeloid Blasts*,Immature Granulocytes,Promyelocytes,Myelocytes,Metamyelocytes,Immature Eosinophils*,Immature Basophils*,Promonocytes*,Prolymphocytes*,Hairy Cells*,Sezary Cells*,Plasma Cells*,Others*,Total,Non-WBC,NRBC,Smudge Cells,Degenerate Cells,Stain Artefacts,Unclassified,Rejected");
		logger.info("WBC tab is present and selected");
	}
	
	
	@Test(priority = 8, enabled = true)
	public void verifyTheWBCCellCountIsEqualToTotalCount() {
		boolean totalCount = wbc.verifyTheWBCCellCountIsEqualToTotalCount();
		Assert.assertTrue(totalCount);
		logger.info("sum of WBC coutns are eqaul to the total count");
	}
	
	@Test(priority = 10, enabled = true)
	public void verifyManualSubClassificationMessage() {
		String message = wbc.verifyManualSubClassificationMessage();
		Assert.assertEquals(message, "* Manual sub-classification");
		logger.info(message+" message is present");
	}
	
	
	@Test(priority = 12, enabled = false)
	public void VerifyTheFunctionalityOfNLRValueCalculation() throws InterruptedException {
		boolean NLRvalue = wbc.VerifyTheFunctionalityOfNLRValueCalculation();
		Assert.assertTrue(NLRvalue);
		logger.info("NLRvalue is verified");
	}

	@Test(priority = 24, enabled = true)
	public void VerifyTheReclassificationOfNeutrophilWithAllOtherCells() throws InterruptedException {
		boolean reclassify=wbc.verifyCountAfterReclassification("//tbody//tr//td[2]","//table/tbody/tr/td[1]");
		Assert.assertTrue(reclassify);
		logger.info("after reclassification count of neutrophils is verified for all the other cells");
	}

	@Test(priority = 26, enabled = true)
	public void VerifyTheReclassificationOfWBCToLargePlatelet() throws InterruptedException {
		boolean reclassify=wbc.wbcPatchToPlateletClump();
		Assert.assertTrue(reclassify);
		logger.info("after reclassification , count of neutrophils is verified for all the other cells");
	}


	@Test(priority = 34, enabled = true)
	public void VerifyTheReclassificationOfWBCToPlateletClumps() throws InterruptedException {
		boolean reclassify=wbc.wbcPatchToLargePlatelet();
		Assert.assertTrue(reclassify);
		logger.info("after reclassification , count of neutrophils is verified for all the other cells");
	}








}
