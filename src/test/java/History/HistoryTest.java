package History;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import Platelet.ReviewPatchesInPatchViewAndSplitView;
import Platelet.ReviewPlateletMorphologyCellType;

import Platelet.ViewAndModifyThePlateletCount;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.util.Properties;

public class HistoryTest {

    private final Logger logger = LogManager.getLogger(HistoryTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public History history;
    public ReviewPatchesInPatchViewAndSplitView rppvs;
    public ViewAndModifyThePlateletCount viewAndModifyThePlateletCount;
    public ReviewPlateletMorphologyCellType reviewPlateletMorphologyCellType;
    public Properties props;
    public CommonMethods commonMethods;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readReportListingProperties();
        Property.readPlateletProperties();
        Property.readCommonMethodProperties();
        Property.readHistoryProperties();
        history=new History(driver);
        viewAndModifyThePlateletCount = new ViewAndModifyThePlateletCount(driver);
        reviewPlateletMorphologyCellType = new ReviewPlateletMorphologyCellType(driver);
        rppvs = new ReviewPatchesInPatchViewAndSplitView(driver);
        commonMethods = new CommonMethods(driver);

    }

    //Verify that the History CTA displays the list of events when clicked.
    @Test(priority = 1,enabled = true)
    public void verifyThePresenceOfHistoryCta() throws InterruptedException {
        commonMethods.openAReport("To be reviewed");
        Assert.assertTrue(history.presenceOfHistoryCtc());
        logger.info("Verified that History option is available in Kebab List");
    }

    //Verify the details present in the history page of a newly generated report.
    @Test(priority = 2,enabled = true)
    public void verifyHistoryOfNewlyGeneratedReport() throws InterruptedException {
        commonMethods.openAReport("To be reviewed");
        Assert.assertTrue(history.historyOfNewlyGeneratedReport());
        logger.info("Verified the History of new generated report");
    }


    @Test(priority = 2,enabled = true)
    public void verifyHistoryOfFirstTimeAssignReviewer() throws InterruptedException {
        commonMethods.openAReport("To be reviewed");
        history.historyOfFirstTimeAssignReviewer();
        logger.info("Verified the History of new generated report");
    }







}
