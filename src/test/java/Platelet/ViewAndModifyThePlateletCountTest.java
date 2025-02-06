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

public class ViewAndModifyThePlateletCountTest extends BrowserSetUp{

    private final Logger logger = LogManager.getLogger(ViewAndModifyThePlateletCountTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public ReviewPatchesInPatchViewAndSplitView rppvs;
    public ViewAndModifyThePlateletCount viewAndModifyThePlateletCount;
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
        viewAndModifyThePlateletCount=new ViewAndModifyThePlateletCount(driver);
        rppvs = new ReviewPatchesInPatchViewAndSplitView(driver);
        cms=new CommonMethods(driver);

    }

    //Verify the functionality of the default multiplication check boxes for the reports

    @Test(priority = 1, enabled = true)
    public void verifyTheFunctionalityOfMultiplicationFactorCancelBtn(){
        cms.clickOnSpecificTab("Platelet");
        Assert.assertTrue(viewAndModifyThePlateletCount.verifyMultiplicationFactorCancelFunctionality(5000,true));
        logger.info("multiplication factor is working Fine");
    }

    @Test(priority = 2, enabled = true)
    public void verifyTheFunctionalityOfMultiplicationWarningMsg(){
        Assert.assertTrue(viewAndModifyThePlateletCount.verifyMultiplicationFactorCancelFunctionalityWithWarning(2000,true));
        logger.info("multiplication factor is working Fine");
    }

    @Test(priority = 3, enabled = true)
    public void verifyTheFunctionalityOfMultiplicationFactor(){
        Assert.assertTrue(viewAndModifyThePlateletCount.verifyMultiplicationFactorSettings(5000,false));
        logger.info("multiplication factor is working Fine");

    }
}
