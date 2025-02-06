package CommonTools;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import ReportListing.VerifyTheListReportPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.util.Properties;

public class DownloadPatchesTest extends BrowserSetUp{
    private final Logger logger = LogManager.getLogger(DownloadPatchesTest.class);
    public WebDriverWait wait;
    public WebDriver driver;
    public Properties props;
    public DownloadPatches downloadPatches;
    public ViewReferenceLibrary viewReferenceLibrary;
    public ViewOriginalModifiedreports viewOriginalModifiedreports;
    public ReportListing.VerifyTheListReportPage VerifyTheListReportPage;
    public CommonMethods commonMethods;



    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        viewOriginalModifiedreports =new ViewOriginalModifiedreports(driver);
        VerifyTheListReportPage =new VerifyTheListReportPage(driver);
        viewReferenceLibrary=new ViewReferenceLibrary(driver);
        downloadPatches=new DownloadPatches(driver);
        commonMethods=new CommonMethods(driver);

        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readCommonToolsProperties();
        Property.readCommonMethodProperties();


    }
    @Test(priority = 1, enabled = true)
    public void testDownloadPatches() {
        commonMethods.clickOnSpecificTab("WBC");
        String tableXPath = props.getProperty("wbccCellRows"); // XPath for table rows
        String cellColumnXPath = ".//td[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for patches

        downloadPatches.downloadPatchesForAllCells(tableXPath, cellColumnXPath, patchXPath);
    }
    //Verify download of all patches in patch view
    @Test(priority = 2, enabled = true)
    public void testDownloadAllPatches() {
        String tableXPath = props.getProperty("wbccCellRows"); // XPath for table rows
        String cellColumnXPath = "./td[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCell(tableXPath, cellColumnXPath,patchXPath);
    }

    @Test(priority = 3, enabled = true)
    public void testDownloadPatchesSplitView() {
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        // Define XPaths
        String tableXPath = props.getProperty("wbccCellRows"); // XPath for table rows
        String cellColumnXPath = ".//td[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for patches

        downloadPatches.downloadPatchesForAllCells(tableXPath, cellColumnXPath, patchXPath);
    }
    //Verify download of all patches in split view
    @Test(priority = 4, enabled = true)
    public void testDownloadAllPatchesInSplitView() {
        String tableXPath = props.getProperty("wbccCellRows"); // XPath for table rows
        String cellColumnXPath = "./td[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCell(tableXPath, cellColumnXPath,patchXPath);
    }

    //Verify download of all patches in split view in RBC
    @Test(priority = 4, enabled = true)
    public void testDownloadAllPatchesInRbcPatchViewSize() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }

    //Verify download of all patches in split view in RBC
    @Test(priority = 5, enabled = true)
    public void testDownloadAllPatchesInRbcPatchViewShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Shape");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }

    //Verify download of all patches in split view in RBC
    @Test(priority = 6, enabled = true )
    public void testDownloadAllPatchesInRbcPatchViewColor() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Color");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 7, enabled = true )
    public void testDownloadAllPatchesInRbcPatchViewInclusion() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Inclusions");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 8, enabled = true)
    public void testDownloadAllPatchesInRbcSplitViewSize() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Size");
        commonMethods.clickOnSpecificViewtab("Split view");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 9, enabled = true )
    public void testDownloadAllPatchesInRbcSplitViewShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Shape");

        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 10, enabled = true)
    public void testDownloadAllPatchesInRbcSplitViewColour() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Color");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }
    @Test(priority = 11, enabled = true )
    public void testDownloadAllPatchesInRbcSplitViewInclusion() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Inclusions");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }


    @Test(priority = 12, enabled = true )
    public void testDownloadAllPatchesInRbcPatchViewSizeMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Size");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }

    @Test(priority = 13, enabled = true )
    public void testDownloadAllPatchesInRbcPatchViewShapeMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Shape");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches
        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }

    //Verify download of all patches in split view in RBC
    @Test(priority = 14, enabled = true)
    public void testDownloadAllPatchesInRbcPatchViewColorMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Color");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 15, enabled = true)
    public void testDownloadAllPatchesInRbcPatchViewInclusionMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Inclusions");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 16, enabled = true)
    public void testDownloadAllPatchesInRbcSplitViewSizeMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Size");
        commonMethods.clickOnSpecificViewtab("Split view");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 17, enabled = true)
    public void testDownloadAllPatchesInRbcSplitViewShapeMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Shape");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }
    //Verify download of all patches in split view in RBC
    @Test(priority = 18, enabled = true)
    public void testDownloadAllPatchesInRbcSplitViewColourMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Color");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }
    @Test(priority = 19, enabled = true)
    public void testDownloadAllPatchesInRbcSplitViewInclusionMultiple() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificSubTab("Inclusions");
        String tableXPath = props.getProperty("rbcCellRows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }

    //Verify download of all patches in split view in Platelet
    @Test(priority = 19, enabled = true )
    public void testDownloadAllPatchesInPlateletMorpology() {
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        String tableXPath = props.getProperty("plateletsubrows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }


    //Verify download of all patches in split view in Platelet
    @Test(priority = 20, enabled = true, dependsOnMethods = "testDownloadAllPatchesInPlateletMorpology")
    public void testDownloadAllPatchesInPlateletMorpologyMultiple() {
        String tableXPath = props.getProperty("plateletsubrows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }


    @Test(priority = 21, enabled = true , dependsOnMethods = "testDownloadAllPatchesInPlateletMorpology")
    public void testDownloadAllPatchesInPlateletMorpologySplitview() {
        commonMethods.clickOnSpecificViewtab("Split view");
        String tableXPath = props.getProperty("plateletsubrows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadPatchesForAllCellsRbc(tableXPath, cellColumnXPath,patchXPath);
    }

    @Test(priority = 22, enabled = true , dependsOnMethods = "testDownloadAllPatchesInPlateletMorpologySplitview")
    public void testDownloadAllPatchesInPlateletMorpologySplitviewMultiple() {
        String tableXPath = props.getProperty("plateletsubrows"); // XPath for table rows
        String cellColumnXPath = "./div[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for all patches

        downloadPatches.downloadAllPatchesForEachCellRBC(tableXPath, cellColumnXPath,patchXPath);
    }

    @Test(priority = 23, enabled = true)
    public void testAdditionalInformation() {
        commonMethods.clickOnSpecificTab("WBC");
        String tableXPath = props.getProperty("wbccCellRows"); // XPath for table rows
        String cellColumnXPath = ".//td[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for patches

        boolean actualrank = downloadPatches.additionalInformation(tableXPath, cellColumnXPath, patchXPath);
        Assert.assertTrue(actualrank);
        logger.info("Verify The Additional Information in WBC tab");
    }

    @Test(priority = 22, enabled = true)
    public void testAdditionalInformationSplit() {
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");

        // Define XPaths
        String tableXPath = props.getProperty("wbccCellRows"); // XPath for table rows
        String cellColumnXPath = ".//td[1]"; // XPath for the cell name column
        String patchXPath = props.getProperty("patches"); // XPath for patches

        boolean actualrank = downloadPatches.additionalInformation(tableXPath, cellColumnXPath, patchXPath);
        Assert.assertTrue(actualrank);
        logger.info("Verify The Additional Information in WBC tab");
    }





}
