package RBC.VerifyThePatchSizeOfRBC;
import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.time.Duration;
import java.util.Properties;

public class VerifyThePatchSizeOfRBCTest  extends BrowserSetUp {
    private final Logger logger = LogManager.getLogger(VerifyThePatchSizeOfRBCTest.class);


    public WebDriver driver;
    public WebDriverWait wait;
    int time = 30;
    Properties props;
    private VerifyThePatchSizeOfRBC verifyThePatchSizeOfRBC ;
    private CommonMethods common;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readRBCProperties();
        verifyThePatchSizeOfRBC = new VerifyThePatchSizeOfRBC(driver);
        common = new CommonMethods(driver);
    }


    //_______________________________Verifying  The Regrading Of Rbc Cell Types Test Methods____________________________//

     @Test(priority = 1,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForMacrocytesInSizeTab() throws InterruptedException {
         test=extent.createTest("Verify The Patch Size Of RBC");
         String tab = props.getProperty("TabXpath") + "'Size'" + props.getProperty("remainingXpath");
         common.clickOnTab("Size", tab);
         String macrocytes=props.getProperty("Macrocytes");
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("patchView")))).click();
         boolean status= verifyThePatchSizeOfRBC.verifyTheSizeOfPatchesInPixelForRBC(macrocytes,macrocytes+"/following::div[3]");
         Assert.assertTrue(status);
         logger.info("The rendered patch size in pixels for the RBC cells in size tab is verified successfully");
}
    @Test(priority = 3,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForMicrocytes() throws InterruptedException {
        String microcytes=props.getProperty("Microcytes");
        boolean status= verifyThePatchSizeOfRBC.verifyTheSizeOfPatchesInPixelForRBC(microcytes,microcytes+"/following::div[3]");
        Assert.assertTrue(status);
        logger.info("The rendered patch size in pixels for the RBC cells in size tab is verified successfully");
    }

    @Test(priority = 9,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForMacrocytesInShapeTab() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Shape'" + props.getProperty("remainingXpath");
        common.clickOnTab("Shape", tab);
        String ovalocytes=props.getProperty("Ovalocytes");
        boolean status= verifyThePatchSizeOfRBC.verifyTheSizeOfPatchesInPixelForRBC(ovalocytes,ovalocytes+"/following::div[3]");
        Assert.assertTrue(status);
        logger.info("The rendered patch size in pixels for the RBC ovalocytes cells is verified successfully");
    }

    @Test(priority = 17,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForHypochromicCellsInColorTab() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Color'" + props.getProperty("remainingXpath");
        common.clickOnTab("Color", tab);
        String  hypochromicCells=props.getProperty("HypochromicCell");
        boolean status= verifyThePatchSizeOfRBC.verifyTheSizeOfPatchesInPixelForRBC(hypochromicCells,hypochromicCells+"/following::div[3]");
        Assert.assertTrue(status);
        logger.info("The rendered patch size in pixels for the hypochromic cells in RBC size tab is verified successfully");
    }
    @Test(priority = 19,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForPolychromaticCellsInColorTab() throws InterruptedException {
        String polychromaticCells=props.getProperty("Polychromatic");
        boolean status= verifyThePatchSizeOfRBC.verifyTheSizeOfPatchesInPixelForRBC(polychromaticCells,polychromaticCells+"/following::div[3]");
        Assert.assertTrue(status);
        logger.info("The rendered patch size in pixels for the Polychromatic cells in RBC color tab is verified successfully");
    }




    @Test(priority = 29,enabled = true)
    public void verifyTheSizeOfPatchesInPixelForWBCTab() throws InterruptedException {
        String tabName="WBC";
        common.clickOnTab(tabName, props.getProperty("WBCTab"));
        Thread.sleep(4000);
        String lymphocytes=props.getProperty("lymphocytes");
        boolean status= verifyThePatchSizeOfRBC.verifyTheSizeOfPatchesInPixelForRBC(lymphocytes,lymphocytes+"/following::td[2]");
        Assert.assertTrue(status);
        logger.info("The rendered patch size in pixels for the WBC neutrophills cell type is verified successfully");
    }















}
