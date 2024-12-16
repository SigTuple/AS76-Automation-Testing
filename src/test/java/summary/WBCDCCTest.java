package summary;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import Data.Property;
import utilities.BrowserSetUp;

public class WBCDCCTest extends BrowserSetUp {
    private final Logger logger = LogManager.getLogger(WBCDCCTest.class);
    public WebDriverWait wait;
    public Properties props;
    public WBCDCC wbcdcc;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        WebDriver driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        // WBCDCC === WBC Differential calculated count
        wbcdcc = new WBCDCC(driver);
        props = Property.prop;
        Property.readSummaryProperties();

    }
    //verify the presences of the summary WBC RBC and platelet tabs
    @Test(priority = 1, enabled = true)
    public void verifyMainTabs() throws Exception {
        // Thread.sleep(2000);
        String actualHeader = wbcdcc.verifyThetabs();
        Assert.assertEquals(actualHeader, "Summary,WBC,RBC,Platelets,");
        logger.info(" expected header is present on report page");
    }

    // WBC differential count (%) header verification
    @Test(priority = 2, enabled = true)
    public void verifyWBCDiffentialCountHeader() {

        test = extent.createTest("WBCDCC Test");
        String mainHeader = wbcdcc.verifyWBCDifferentialCountHeader();
        Assert.assertEquals(mainHeader, "WBC differential count (%)");
        logger.info("Header is present and main header is " + mainHeader);
    }

    // Verify WBC differential Sub headers
    @Test(priority = 3, enabled = true)
    public void verifySubHeaders() {
        String subheaders = wbcdcc.verifyWBCSubheaders();
        Assert.assertEquals(subheaders, "Manual,Calculated,Cell Name,");
        logger.info("Sub Header is present and sub headers are  " + subheaders);
    }
    //verify the WBC and Non WBC headers
    @Test(priority = 4, enabled = true)
    public void verifyWBCAndNonWBCHeaders() {

        String WBCandNonWCB = wbcdcc.verifyWBCAndNonWBCHeaders();
        Assert.assertEquals(WBCandNonWCB, "Cell NameWBCNon-WBC");
        logger.info("WBC and NonWBC headers are present  " + WBCandNonWCB);
    }

    //Verify the WBC cells name present in the summary tab
    @Test(priority = 5, enabled = true)
    public void verifyWBCCellNames() {
        String WBCCellNames = wbcdcc.verifyWBCCellNames();
        Assert.assertEquals(WBCCellNames, props.getProperty("WBCandNonWBCNames"));
        logger.info("WBC and NonWBC cells name are present  " + WBCCellNames);
    }

    //comparing the WBC cell count present in the summary and WBC tab
    @Test(priority = 6, enabled = true)
    public void compareWBCcount()
    {
        boolean flag= wbcdcc.compareWBCcount();
        Assert.assertTrue(flag);
        logger.info("WBC values are same in Summary tab and WBC tab ");
    }

}
