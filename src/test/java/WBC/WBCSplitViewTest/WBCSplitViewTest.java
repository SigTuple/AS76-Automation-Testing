package WBC.WBCSplitViewTest;

import Data.Property;
import WBC.VerifyWbcPatches.WBCPatches;
import WBC.WBCSplitView.WBCSplitView;
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
import java.util.Properties;

import static utilities.BrowserSetUp.extent;

public class WBCSplitViewTest {
        private final Logger logger = LogManager.getLogger(WBC.WBCSplitViewTest.WBCSplitViewTest.class);
        public WebDriverWait wait;
        public WBCPatches wbc;
        WebDriver driver;
        public Properties props;
        public WBCSplitView wbcSplitView;
        public   ExtentTest test;

        @BeforeSuite
        public void driver() throws Exception {
            BrowserSetUp browser = new BrowserSetUp();
            driver = browser.getDriver();
            wait = new WebDriverWait(driver, 30);
            wbc = new WBCPatches(driver);
            props = Property.prop;
             wbcSplitView=new WBCSplitView(driver);



        }
       // _________________________________________________ WBC Split view icon______________________----------------//

        // clicked on split view icon on wbc tab

        @Test(priority = 1,enabled = true)
    public void clickedOnSplitViewOnWBCTab() throws InterruptedException {
       test = extent.createTest("WBCPatchesTest");
            boolean status=wbcSplitView.clickedOnSplitView();
        Assert.assertTrue(status);
        logger.info("split view icon clicked successfully");
    }


    // verifying the zoom -in zoom-out functionality on wbc split view tab


    @Test(priority = 4,enabled = true)
    public void verifyZoomInZoomOutFunctionality() throws IOException, InterruptedException {
            String output=wbcSplitView.zoom_in_zoom_out_functionality();
            Assert.assertEquals(output,"");
            logger.info("zoom in and zoom out functionality is verified on wbc split view tab");
    }



    @Test(priority = 6,enabled = true)
    public void verifying_Home_Zoom_Functionality() throws InterruptedException {
            String otp= wbcSplitView.home_Zoom_functionality();
            Assert.assertEquals(otp,"");
            logger.info("home zoom functionality is verified on wbc split view");
    }








}
