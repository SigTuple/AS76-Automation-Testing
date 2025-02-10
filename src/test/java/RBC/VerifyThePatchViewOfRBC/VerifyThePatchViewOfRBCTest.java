package RBC.VerifyThePatchViewOfRBC;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import RBC.VerifyThePatchSizeOfRBC.VerifyThePatchSizeOfRBC;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.time.Duration;
import java.util.Properties;

public class VerifyThePatchViewOfRBCTest extends BrowserSetUp {
    private final Logger logger = LogManager.getLogger(VerifyThePatchViewOfRBCTest.class);


    public WebDriver driver;
    public WebDriverWait wait;
    int time = 30;
    Properties props;
    private VerifyThePatchViewOfRBC verifyThePatchViewOfRBC ;
    private CommonMethods common;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readRBCProperties();
        verifyThePatchViewOfRBC = new VerifyThePatchViewOfRBC(driver);
        common = new CommonMethods(driver);
    }


    //_______________________________Verifying The Patch View Of Rbc Cell Types Test Methods____________________________//

     @Test(priority = 1,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForMacrocytesInSizeTab() throws InterruptedException {
         test = extent.createTest("Verify The Patch Size Of RBC");
         common.clickOnTab("RBC","//*[contains(text(),'RBC')]");
         String tab = props.getProperty("TabXpath") + "'Size'" + props.getProperty("remainingXpath");
         common.clickOnTab("Size", tab);
         String microcytes=props.getProperty("Microcytes");
         verifyThePatchViewOfRBC.verifyThePatchDisplayingCorrectlyForAllCellType(microcytes);
         logger.info("Patches are displayed correctly on macrocytes cell in rbc size tab");


     }
}
