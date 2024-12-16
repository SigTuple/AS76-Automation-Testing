package RBC.VerifySplitViewOfRbcTab;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import RBC.VerifySubTabOfRBC.VerifyUiOfRbcSizeSubTabTest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.io.IOException;
import java.util.Properties;

public class VerifySplitViewOfRBCTabTest extends BrowserSetUp {


    private final Logger logger = LogManager.getLogger(VerifyUiOfRbcSizeSubTabTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    int time=30;
    Properties props;
    private CommonMethods cms;
    private VerifySplitViewOfRBCTab verifySplitViewOfRBCTab;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readRBCProperties();
        verifySplitViewOfRBCTab=new VerifySplitViewOfRBCTab(driver);
    }

    //_________________________________________RBC SPLiT View Test Method_______________________________________//
    // verifying  the availability of split view in size tab
    @Test(priority = 1,enabled = true)
    public void presenceOfSplitViewOnSizeSubTab() throws InterruptedException {
        test=extent.createTest("RCB Split View");
       boolean status=verifySplitViewOfRBCTab.presenceOfSplitView("Size", props.getProperty("TabXpath") +"'Size'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("split view icon is present & clickable on size tab for all the cells ");
    }

    // verifying  the availability of split view in Shape tab

    @Test(priority = 3,enabled = true)
    public void presenceOfSplitViewOnShapeSubTab() throws InterruptedException {
        boolean status=verifySplitViewOfRBCTab.presenceOfSplitView("Shape", props.getProperty("TabXpath") +"'Shape'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("split view icon is present & clickable on shape tab for all the cells");
    }

    // verifying  the availability of split view in color tab

    @Test(priority = 5,enabled = true)
    public void presenceOfSplitViewOnColorSubTab() throws InterruptedException {
        boolean status=verifySplitViewOfRBCTab.presenceOfSplitView("Color", props.getProperty("TabXpath") +"'Color'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("split view icon is present & clickable on Color tab for all the cells ");
    }



    // verifying  the availability of split view in Inclusion tab
    @Test(priority = 7,enabled = true)
    public void presenceOfSplitViewOnInclusionSubTab() throws InterruptedException {
        Thread.sleep(2000);
        boolean status=verifySplitViewOfRBCTab.presenceOfSplitView("Inclusion", props.getProperty("TabXpath") +"'Inclusion'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("split view icon is present & clickable on Inclusion tab for all the cells ");
    }


    // verifying  the same patches are available on patch view and split view in size tab
    @Test(priority =13,enabled = true)
    public void samePatchesArePresentOnSplitAndPatchViewOnSizeTab() throws InterruptedException, IOException {
        boolean status= verifySplitViewOfRBCTab.samePatchOnSplitAndPatchView(props.getProperty("TabXpath") +"'Size'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("same patches are available on split view and patch view is verified on size tab ");
    }

    // verifying the same patch on split view and patch view on shape tab

    @Test(priority =15,enabled = true)
    public void samePatchesArePresentOnSplitAndPatchViewOnShapeTab() throws InterruptedException, IOException {
        boolean status= verifySplitViewOfRBCTab.samePatchOnSplitAndPatchView(props.getProperty("TabXpath") +"'Shape'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("same patches are available on split view and patch view is verified on size tab ");
    }



    // verifying the same patch on split view and patch view on color tab

    @Test(priority =17,enabled = true)
    public void samePatchesArePresentOnSplitAndPatchViewOnColorTab() throws InterruptedException, IOException {
        boolean status= verifySplitViewOfRBCTab.samePatchOnSplitAndPatchView(props.getProperty("TabXpath") +"'Color'"+ props.getProperty("remainingXpath"));
        Assert.assertTrue(status);
        logger.info("same patches are available on split view and patch view is verified on size tab ");
    }


    // verifying the manually graded cells are not present on microscopic view cell list in size tab
    @Test(priority =19,enabled = true)
    public void manuallyGradedCellsNotPresentOnMicroscopicViewInSizeTab() throws InterruptedException {
        boolean status= verifySplitViewOfRBCTab.manuallyGradedCellsNotPresentOnMicroscopicView(props.getProperty("TabXpath") +"'Size'"+ props.getProperty("remainingXpath"),"//div[@class='review-nav__selection__view']//img[3]");
        Assert.assertTrue(status);
        logger.info(" Acanthocytes manually graded cells are not present on microscopic view cell list in size tab ");
    }

    // verifying the manually graded cells are not present on microscopic view cell list in shape tab

    @Test(priority =21,enabled = true)
    public void manuallyGradedCellsNotPresentOnMicroscopicViewInShape() throws InterruptedException {
        boolean status= verifySplitViewOfRBCTab.manuallyGradedCellsNotPresentOnMicroscopicView(props.getProperty("TabXpath") +"'Shape'"+ props.getProperty("remainingXpath"),"//div[@class='review-nav__selection__view']//img[3]");
        Assert.assertTrue(status);
        logger.info("manually graded cells are not present on microscopic view in shape tab ");
    }


















}
