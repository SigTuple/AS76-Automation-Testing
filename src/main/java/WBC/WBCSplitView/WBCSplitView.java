package WBC.WbcSplitView;


import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Properties;

public class WbcSplitView extends CommonMethods {


    //private final Logger logger = LogManager.getLogger(WBCSplitView.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    // public WBCDCC wbcdcc;

    //Author: Santosh Budni
    public WbcSplitView(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readWBCProperties();
        Property.readRBCProperties();
    }


    // clicking to split view

    public  boolean clickedOnSplitView() throws InterruptedException {
        //this.clickOnSpecificTab("WBC");
        this.clickOnSpecificViewtab("Split view");
        return true;

    }


    // verifying the zoom-in and zoom-out functionality

    public String zoom_in_zoom_out_functionality() throws IOException, InterruptedException, IOException {
        return  super.verifyTheFunctionalityOfZoomIn_And_ZoomOut_Using_UI_icon("//*[@id='pbs-volumeViewport']/div/div[3]/div[3]/button[1]",7);
    }



    // verify the functionality of home zoom

    public String home_Zoom_functionality() throws InterruptedException {
        return  super.verifyTheFunctionalityOf_Home_zoom();
    }

}
