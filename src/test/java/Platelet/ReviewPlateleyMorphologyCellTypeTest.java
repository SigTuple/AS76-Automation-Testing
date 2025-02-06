package Platelet;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.util.Properties;

public class ReviewPlateleyMorphologyCellTypeTest {


    private final Logger logger = LogManager.getLogger(ReviewPlateleyMorphologyCellTypeTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public ReviewPatchesInPatchViewAndSplitView rppvs;
    public ViewAndModifyThePlateletCount viewAndModifyThePlateletCount;
    public ReviewPlateletMorphologyCellType reviewPlateletMorphologyCellType;
    Properties props;
    public CommonMethods cms;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readReportListingProperties();
        Property.readPlateletProperties();
        Property.readCommonMethodProperties();
        viewAndModifyThePlateletCount=new ViewAndModifyThePlateletCount(driver);
        reviewPlateletMorphologyCellType=new ReviewPlateletMorphologyCellType(driver);
        rppvs = new ReviewPatchesInPatchViewAndSplitView(driver);
        cms=new CommonMethods(driver);

    }
    //Verify the presence of cell types in morphology
    @Test(priority = 1,enabled = true)
    public void verifyPresenceOfCellType(){
        String CellName = reviewPlateletMorphologyCellType.PresenceOfCellInMorphology();
        Assert.assertEquals(CellName, ",Large Platelets,Platelet Clumps");
        logger.info("Morphology tab Cell Types is present on platelet main tab");
    }

    //verify the cell count and patches are same or not
    @Test(priority = 2,enabled = true)
    public void VerifyCellCountOfLargePlateletIsEqualToPatch(){
        Assert.assertTrue(reviewPlateletMorphologyCellType.isCellCountEqualToPatchCount("Large Platelets"));
        logger.info("Verify the Large platelet cell count is equal patch count");
    }
    //verify the cell count and patches are same or not
    @Test(priority = 3,enabled = true)
    public void VerifyCellCountOfPlateletClumpsIsEqualToPatch(){
        Assert.assertTrue(reviewPlateletMorphologyCellType.isCellCountEqualToPatchCount("Platelet Clumps"));
        logger.info("Verify the  Platelet clumps cell count is equal patch count");
    }

    //Verify the functionality if the user select any patch in the platelet morphology tab
    @Test(priority = 4,enabled = true)
    public void FunctionalityOfPatchWhenSelectThePatchInLargePlatelets(){
        Assert.assertTrue(reviewPlateletMorphologyCellType.verifyPatchSelection("Large Platelets"));
        logger.info("Verify the Large platelet cell  patches are selecting");
    }
    //verify the cell count and patches are same or not
    @Test(priority = 5,enabled = true)
    public void FunctionalityOfPatchWhenSelectThePatchInPlateletClumps(){
        Assert.assertTrue(reviewPlateletMorphologyCellType.verifyPatchSelection("Platelet Clumps"));
        logger.info("Verify the  Platelet clumps cell patches are selecting");
    }

    //
    @Test(priority = 6,enabled = true)
    public void verifyTheSizeOfPatchesInLargePlatelet(){
        Assert.assertTrue(reviewPlateletMorphologyCellType.verifyPatchSizes("Large Platelets"));
        logger.info("Verify the Size of patch");
    }

    @Test(priority = 7,enabled = true)
    public void verifyTheSizeOfPatchesInPlateletClumps(){
        Assert.assertTrue(reviewPlateletMorphologyCellType.verifyPatchSizes("Platelet Clumps"));
        logger.info("Verify the Size of patch");
    }

    //
    @Test(priority = 8 ,enabled = true)
    public void testZoomFunctionality() {
        cms.clickOnSpecificViewtab("Split view");
        String zoomOutXpath = props.getProperty("Zoom_out_btn");
        String zoomInXpath = props.getProperty("Zoom_in_btn");

        int steps = 3; // Define the number of zoom steps

        // Perform Zoom-Out
        String finalZoomOutLevel = cms.zoomOut(zoomOutXpath, steps);
        Assert.assertEquals(finalZoomOutLevel,"100 μm");
        logger.info("Final zoom level after zoom-out: " + finalZoomOutLevel);

        // Perform Zoom-In
        String finalZoomInLevel = cms.zoomIn(zoomInXpath, steps);
        Assert.assertEquals(finalZoomInLevel,"10 μm");

        logger.info("Final zoom level after zoom-in: " + finalZoomInLevel);



        // Perform Home Zoom
        String homeZoomLevel = cms.homeZoom();
        Assert.assertEquals(homeZoomLevel,"1000 μm");
        logger.info("Final zoom level after resetting to home: " + homeZoomLevel);
    }



}
