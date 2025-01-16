package summary;

import Data.Property;
import ReportListing.VerifyTheListReportPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.util.Properties;

public class SummaryTest {
    private final Logger logger = LogManager.getLogger(SummaryTest.class);
    public WebDriverWait wait;
    public Properties props;
    public Summary summary;
    public VerifyTheListReportPage VerifyTheListReportPage;
    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        WebDriver driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        summary =new Summary(driver);
        VerifyTheListReportPage =new VerifyTheListReportPage(driver);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();

    }
    // assign the report
    @Test(priority = 1 ,enabled = true)
    public void  assign() throws InterruptedException {
        Assert.assertEquals(VerifyTheListReportPage.assignreport(),props.getProperty("reviewer1"));
        logger.info("Assign the report");

    }
    //verify the presence of the tool tip in Summary tab.
    @Test(priority = 1 ,enabled = false)
    public void verifyTheWBCinfoIcon() throws InterruptedException {
        Assert.assertTrue(summary.toolTipverifier(props.getProperty("wbcinfoicon") , (props.getProperty("toolTip")) ,"Please check the WBC tab for complete evaluation"));
        logger.info("Verify The WBC Info Icon");
    }

    @Test(priority = 2 ,enabled = false)
    public void verifyTheRBCinfoIcon() throws InterruptedException {
        Thread.sleep(5000);
        Assert.assertTrue(summary.toolTipverifier(props.getProperty("rbcinfoicon") , (props.getProperty("toolTip")) ,"Please check the RBC tab for complete evaluation"));
        logger.info("Verify The RBC Info Icon");
    }

    @Test(priority = 3 ,enabled = false)
    public void verifyThePlateletinfoIcon() throws InterruptedException {
        Assert.assertTrue(summary.toolTipverifier(props.getProperty("plateletinfoicon") , (props.getProperty("toolTip")) ,"Please check the Platelet tab for complete evaluation"));
        logger.info("Verify The Platelet Info Icon");
    }

   @Test(priority = 4, enabled = true)
    public void psImpression()throws InterruptedException{
        Assert.assertTrue(summary.psImpressions());
        logger.info("Verify The PS impression text field");
    }

    @Test(priority = 5, enabled = true)
    public void verifyPsImpressionSubheader()throws InterruptedException {
        Assert.assertEquals(summary.pbsimpressionsubheader(), "RBC Morphology,WBC Morphology,Platelet Morphology,Hemoparasite,Impression,");
        logger.info("Verify The PS impression Sub headers");
    }
    @Test(priority = 6, enabled = true)
    public void verifyPsImpressionHeader(){
        Assert.assertEquals(summary.psImpressionHeading(),"Peripheral smear report");
        logger.info("Verify The PS impression Sub headers");
    }

    @Test(priority = 7, enabled = true)
    public void manualRadioBtnTest()throws InterruptedException{
        Assert.assertTrue(summary.manualRadioBtn());
        logger.info("Verify The functionality of radio button");
    }

    @Test(priority = 8, enabled = true)
    public void calculatedRadioBtnTest()throws InterruptedException{
        Assert.assertTrue(summary.calculatedRadioBtn());
        logger.info("Verify The functionality of radio button");
    }

    @Test(priority = 9, enabled = true)
    public void VerifyThePresenceOfManualTxtFieldValue()throws InterruptedException{
        Assert.assertTrue(summary.comparecalculatesandmanualValues());
        logger.info("Verify The Presence of values in manual text Field");
    }

    @Test(priority = 10 , enabled = true)
    public void verifyThePresenceOfDiffrencialCount(){
        Assert.assertEquals(summary.differentialcountmsg() ,"The differential count does not add up to 100%");
        logger.info("Verify the presence of the differential count note message");
    }


}
