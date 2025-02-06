package CommonTools;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import ReportListing.VerifyTheListReportPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import summary.Summary;
import summary.SummaryTest;
import utilities.BrowserSetUp;

import java.util.Properties;

public class ViewOriginalModifiedreportsTest {
    private final Logger logger = LogManager.getLogger(ViewOriginalModifiedreportsTest.class);
    public WebDriverWait wait;
    public Properties props;
    public CommonMethods commonMethods;
    public ViewOriginalModifiedreports viewOriginalModifiedreports;
    public ReportListing.VerifyTheListReportPage VerifyTheListReportPage;


    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        WebDriver driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        viewOriginalModifiedreports =new ViewOriginalModifiedreports(driver);
        VerifyTheListReportPage =new VerifyTheListReportPage(driver);
        commonMethods=new CommonMethods(driver);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readCommonMethodProperties();
        Property.readCommonToolsProperties();

    }
    //Verification of the presence of View original/modified reports options in the kebab menu icon.
    @Test(priority = 1,enabled = true)
    public void presenceOfOriginalReport(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Summary']"))).click();
        Assert.assertTrue(viewOriginalModifiedreports.originalReport());
        logger.info("Verified The presence of original report in kebab menu");
    }

    @Test(priority = 2,enabled = true)
    public void presenceOfModifiedReport() throws InterruptedException {
        Assert.assertTrue(viewOriginalModifiedreports.modifiedReport());
        logger.info("Verified The presence of modified report in kebab menu");
    }
//Verification of switching between modified reports to original report
    @Test(priority = 3,enabled = true)
    public void verifyTheSwichingToOriginalReport() throws InterruptedException {
        Assert.assertTrue(viewOriginalModifiedreports.switchToOriginalReport());
        logger.info("Verified The Switching  of original report in kebab menu");
    }

    @Test(priority = 4,enabled = true)
    public void verifyTheSwichingToModifiedReport() throws InterruptedException {
        Assert.assertTrue(viewOriginalModifiedreports.switchToModifiedReport());
        logger.info("Verified The Switching  of modified report in kebab menu");
    }

}
