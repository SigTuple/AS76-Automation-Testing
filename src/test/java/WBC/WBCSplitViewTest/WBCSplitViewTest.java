package WBC.WbcSplitViewTest;

import Data.Property;
import WBC.VerifyWbcPatches.WBCPatches;
import WBC.WbcSplitView.WbcSplitView;
import com.aventstack.extentreports.ExtentTest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class WbcSplitViewTest {
    private final Logger logger = LogManager.getLogger(WbcSplitViewTest.class);
    public WebDriverWait wait;
    public WBCPatches wbc;
    WebDriver driver;
    public Properties props;
    public WbcSplitView wbcSplitView;
    public ExtentTest test;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 50);
        wbc = new WBCPatches(driver);
        props = Property.prop;
        wbcSplitView=new WbcSplitView(driver);



    }
    // _________________________________________________ WBC Split view icon______________________----------------//

    // clicked on split view icon on wbc tab

    @Test(priority = 2,enabled = true)
    public void clickedOnSplitViewOnWBCTab() throws InterruptedException {

        boolean status=wbcSplitView.clickedOnSplitView();
        Assert.assertTrue(status);
        logger.info("split view icon clicked successfully");
    }


    // verifying the zoom -in zoom-out functionality on wbc split view tab


    @Test(priority = 4,enabled = true)
    public void verifyZoomInZoomOutFunctionality() throws IOException, InterruptedException {
        String output=wbcSplitView.zoom_in_zoom_out_functionality();
        Assert.assertEquals(output,"5 μm");
        logger.info("zoom in and zoom out functionality is verified on wbc split view tab");
    }



    @Test(priority = 6,enabled = true)
    public void verifying_Home_Zoom_Functionality() throws InterruptedException {
        String otp= wbcSplitView.home_Zoom_functionality();
        Assert.assertEquals(otp,"1000 μm");
        logger.info("home zoom functionality is verified on wbc split view");
    }








}
