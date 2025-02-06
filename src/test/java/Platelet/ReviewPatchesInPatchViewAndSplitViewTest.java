package Platelet;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import RBC.VerifySplitViewOfRbcTab.VerifySplitViewOfRBCTab;
import RBC.VerifySubTabOfRBC.VerifyUiOfRbcSizeSubTab;
import ReportListing.VerifyTheListReportPage;
import ReportListing.VerifyTheListReportPageTest;
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

import java.util.Properties;

public class ReviewPatchesInPatchViewAndSplitViewTest extends BrowserSetUp {


    private final Logger logger = LogManager.getLogger(ReviewPatchesInPatchViewAndSplitViewTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public ReviewPatchesInPatchViewAndSplitView rppvs;
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
        rppvs = new ReviewPatchesInPatchViewAndSplitView(driver);
        cms=new CommonMethods(driver);

    }

    //_______________________________split view for rbc tab___________________//


    // verify the presence of sub tabs in the platelet tab

    @Test(priority = 2, enabled = true)
    public void presenceOfCountSubTabInPlatelet() throws InterruptedException {
        test=extent.createTest("Platelet");
        cms.clickOnTab("Platelet",props.getProperty("plaTeletTab"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("PatchView")))).click();
        String count = rppvs.presenceOfCountTab(props.getProperty("countTab"));
        Assert.assertEquals(count, "Count");
        logger.info("Count tab is present on platelet ");
    }

    @Test(priority = 4, enabled = true)
    public void presenceOfMorphology() {
        String morph = rppvs.presenceOfCountTab(props.getProperty("Morphology"));
        Assert.assertEquals(morph, "Morphology");
        logger.info("Morphology tab is present on platelet main tab");
    }

    // verifying the selection of fovs
    @Test(priority = 6, enabled = true)
    public void selectionOfFovs() throws InterruptedException {
        Assert.assertTrue(rppvs.selectionOfAnyFov());
        logger.info("Selection of any fovs are verified on platelet count tab");
    }


    // verifying the user is able to edit the N M G Values
    @Test(priority = 8,enabled = true)
    public void editTheNMGValue() throws InterruptedException {
        Assert.assertNull(rppvs.editTheNMGValue());
        logger.info("user is able to change the n m g value on platelet tab");
    }

    //Verify the default selection of split view in the platelet count
    @Test(priority = 9,enabled = true)
    public void testDefaultSelectionOfSplitView(){
        Assert.assertTrue(rppvs.defaultSelctionOfSplitViewInPlatelet());
        logger.info("Verify The Default selection of split view in the platelet count");

    }


    @Test(priority = 10,enabled = true)
    public void VerifyRadioBtnManualLevelPlateletCount(){
        Assert.assertTrue(rppvs.manualLevelPlateletCount());
        logger.info("Verify the selection of the manual platelet level radio button");
    }
    //Verify the selection of any one platelet count level from the manual platelet level dropdown
    @Test(priority = 11,enabled = true)
    public void VerifyTheSelectManualLevelPlateletCount(){
        Assert.assertTrue(rppvs.manualLevelPlateletCountSelectLevel("Increased"));
        logger.info("Verify the selection of the manual platelet level By clicking on the dropdown");
    }




    @Test(priority = 12,enabled = true)
    public void VerifyTheMessageBelowFov(){
        Assert.assertTrue(rppvs.verifyMessageBelowFov());
        logger.info("Verify the message below the FOV is as per Figma");
    }


    @Test(priority = 13,enabled = true)
    public void VerifyTheWarningMessageWhenPlateletCountDetected(){
        Assert.assertTrue(rppvs.warningMsgForPlateletClumpsDetected());
        logger.info("Verify the message below the FOV is as per Figma");
    }




}
