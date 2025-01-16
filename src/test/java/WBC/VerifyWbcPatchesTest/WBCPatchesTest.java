package WBC.VerifyWbcPatchesTest;

import java.util.Properties;

import WBC.VerifyWbcPatches.WBCPatches;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Data.Property;
import utilities.BrowserSetUp;

public class WBCPatchesTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(WBCPatchesTest.class);
	public WebDriverWait wait;
	public WBCPatches wbc;
	WebDriver driver;
	public Properties props;

	@BeforeSuite
	public void driver() throws Exception {
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		wait = new WebDriverWait(driver, 30);
		wbc = new WBCPatches(driver);
		props = Property.prop;


	}
	@Test(priority = 2, enabled = true)
	public void verifyPresenceOfWBCPatches() {
		test = extent.createTest("WBCPatchesTest");
		boolean patch = wbc.verifyPresenceOfWBCPatches();
		Assert.assertTrue(patch);
		logger.info("WBC patches verified");
		
	}
	
	@Test(priority = 3, enabled = true)
	public void verifyPresenceOfNONWBCPatches() {
		boolean patch = wbc.verifyPresenceOfNonWBCPatches();
		Assert.assertTrue(patch);
		logger.info("Non WBC patches verified");
		
	}
	
}
