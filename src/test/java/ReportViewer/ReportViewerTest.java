package ReportViewer;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import WBC.VerifyWbcParameters.WBCParameters;
import WBC.VerifyWbcParametersTest.WBCParametersTest;
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

public class ReportViewerTest extends BrowserSetUp {
    private final Logger logger = LogManager.getLogger(WBCParametersTest.class);
    public WebDriverWait wait;
    public WBCParameters wbc;
    public CommonMethods cms;
    WebDriver driver;
    public Properties props;
    public ReportViewer reportViewer;


    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
       driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
         cms = new CommonMethods(driver);
       reportViewer=new ReportViewer(driver);
        props = Property.prop;
        Property.readReportViewerProperties();

    }

    //------------------------report viewer test script-------------------------------------------//




    // checking the presence of slide icon on summary tab
    @Test(priority = 2, enabled = true)
    public void verifyPresenceOfSlideDetailsOnSummaryTab() throws InterruptedException {
        test = extent.createTest("Report viewer");
        Assert.assertTrue(reportViewer.presenceOfSlideIconOnAssignedReport(props.getProperty("SummaryTab")));
        logger.info("slide details icon are present on tab and its clickable");
    }






    @Test(priority = 15,enabled = true)
    public void presenceOfScanImageArea(){
        Assert.assertTrue(reportViewer.presenceOfSlideImageArea());
        logger.info("slide image area present on slide details pop up is verified ");
    }

    @Test(priority = 19,enabled = true)
    public void presenceOfScanTimeText(){
        Assert.assertEquals(reportViewer.scanTime(),"Scan time");
        logger.info("'scan time' text is verified on scan details pop up");
    }


    @Test(priority = 21,enabled = true)
    public void presenceOfScannedByText(){
        Assert.assertEquals(reportViewer.scannedBy(),"Scanned by");
        logger.info(" presence of 'scanned by' text is verified on scan details pop up");
    }

    @Test(priority = 25,enabled = true)
    public void presenceOfScannedONText(){
        Assert.assertEquals(reportViewer.scannedOn(),"Scanned on");
        logger.info(" presence of 'scanned on' text is verified on scan details pop up");
    }

    @Test(priority = 27,enabled = true)
    public void popUpCloseOnSummaryTab(){
        Assert.assertNull(reportViewer.closePopUp(),"pop up closed successfully");
        logger.info("pop up closed successfully on summary tab");
    }




    @Test(priority = 34, enabled = true)
    public void verifyPresenceOfSlideDetailsOnWBCTab() throws InterruptedException {
        Assert.assertTrue(reportViewer.presenceOfSlideIconOnAssignedReport(props.getProperty("WBCTAB")));
        logger.info("slide details icon are present on tab and its clickable on WBC tab");
    }

    @Test(priority = 37,enabled = true)
    public void presenceOfScanImageAreaOnWbcTab(){
        Assert.assertTrue(reportViewer.presenceOfSlideImageArea());
        logger.info("slide image area present on slide details pop up is verified on WBC tab ");
    }

    @Test(priority = 39,enabled = true)
    public void presenceOfScanTimeTextOnWBCTab(){
        Assert.assertEquals(reportViewer.scanTime(),"Scan time");
        logger.info("'scan time' text is verified on scan details pop up on WBC Tab");
    }


    @Test(priority = 41,enabled = true)
    public void presenceOfScannedByTextOnWbcTab(){
        Assert.assertEquals(reportViewer.scannedBy(),"Scanned by");
        logger.info(" presence of 'scanned by' text is verified on scan details pop up on WBC tab");
    }

    @Test(priority = 45,enabled = true)
    public void presenceOfScannedONTextOnWbcTab(){
        Assert.assertEquals(reportViewer.scannedOn(),"Scanned on");
        logger.info(" presence of 'scanned on' text is verified on scan details pop up on WBC tab");
    }

    @Test(priority = 47,enabled = true)
    public void popUpCloseOnWBCTAb(){
        Assert.assertNull(reportViewer.closePopUp(),"pop up closed successfully");
        logger.info("pop uo closed successfully on summary tab on WBC Tab");
    }



    @Test(priority = 57, enabled = true)
    public void verifyPresenceOfSlideDetailsOnRBCTab() throws InterruptedException {
        Assert.assertTrue(reportViewer.presenceOfSlideIconOnAssignedReport(props.getProperty("RBCTAB")));
        logger.info("slide details icon are present on tab and its clickable");
    }


    @Test(priority = 67,enabled = true)
    public void presenceOfScanImageAreaOnRbcTab(){
        Assert.assertTrue(reportViewer.presenceOfSlideImageArea());
        logger.info("slide image area present on slide details pop up is verified on RBC tab ");
    }

    @Test(priority = 69,enabled = true)
    public void presenceOfScanTimeTextOnRBCTab(){
        Assert.assertEquals(reportViewer.scanTime(),"Scan time");
        logger.info("'scan time' text is verified on scan details pop up on RBC Tab");
    }


    @Test(priority = 71,enabled = true)
    public void presenceOfScannedByTextOnRbcTab(){
        Assert.assertEquals(reportViewer.scannedBy(),"Scanned by");
        logger.info(" presence of 'scanned by' text is verified on scan details pop up on RBC tab");
    }

    @Test(priority = 75,enabled = true)
    public void presenceOfScannedONTextOnRbcTab(){
        Assert.assertEquals(reportViewer.scannedOn(),"Scanned on");
        logger.info(" presence of 'scanned on' text is verified on scan details pop up on RBC tab");
    }

    @Test(priority = 77,enabled = true)
    public void popUpCloseOnRBCTAb(){
        Assert.assertNull(reportViewer.closePopUp(),"pop up closed successfully");
        logger.info("pop uo closed successfully on summary tab on RBC Tab");
    }


    @Test(priority = 79, enabled = true)
    public void verifyPresenceOfSlideDetailsOnPlateletTab() throws InterruptedException {
        Assert.assertTrue(reportViewer.presenceOfSlideIconOnAssignedReport(props.getProperty("PlateletTab")));
        logger.info("slide details icon are present on tab and its clickable");
    }


    @Test(priority = 87,enabled = true)
    public void presenceOfScanImageAreaOnPlateletTab(){
        Assert.assertTrue(reportViewer.presenceOfSlideImageArea());
        logger.info("slide image area present on slide details pop up is verified on Platelet tab ");
    }

    @Test(priority = 89,enabled = true)
    public void presenceOfScanTimeTextOnPlateletTab(){
        Assert.assertEquals(reportViewer.scanTime(),"Scan time");
        logger.info("'scan time' text is verified on scan details pop up on Platelet Tab");
    }


    @Test(priority = 91,enabled = true)
    public void presenceOfScannedByTextOnPlateletTab(){
        Assert.assertEquals(reportViewer.scannedBy(),"Scanned by");
        logger.info(" presence of 'scanned by' text is verified on scan details pop up on Platelet tab");
    }

    @Test(priority = 95,enabled = true)
    public void presenceOfScannedONTextOnPlateletTab(){
        Assert.assertEquals(reportViewer.scannedOn(),"Scanned on");
        logger.info(" presence of 'scanned on' text is verified on scan details pop up on Platelet tab");
    }

    @Test(priority = 97,enabled = true)
    public void popUpCloseOnPlateletTAb(){
        Assert.assertNull(reportViewer.closePopUp(),"pop up closed successfully");
        logger.info("pop uo closed successfully on summary tab on Platelet Tab");
    }


    // verifying the approve and reject buttons are enabled and its displayed on WBC tab

    @Test(priority = 99,enabled = true)
    public void approveAndRejectButtonDisplayedAndItsEnabled() throws InterruptedException {
        Assert.assertTrue(reportViewer.imageSetting(props.getProperty("WBCTAB")));
        logger.info("after clicked on image setting approve and reject buttons are displayed and its enabled on WBC tab");

    }

    // verifying the approve and reject buttons are enabled and its displayed on RBC tab

    @Test(priority = 109,enabled = true)
    public void approveAndRejectButtonDisplayedAndItsEnabledOnRbcTab() throws InterruptedException {
        Assert.assertTrue(reportViewer.imageSetting(props.getProperty("RBCTAB")));
        logger.info("after clicked on image setting approve and reject buttons are displayed and its enabled on RBC tab");

    }


    // verifying the approve and reject buttons are enabled and its displayed on platelet tab

    @Test(priority = 119,enabled = true)
    public void approveAndRejectButtonDisplayedAndItsEnabledOnPlateletTab() throws InterruptedException {
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("PlateletTab")))).click();
        String morphology=props.getProperty("morphology");
        Assert.assertTrue(reportViewer.imageSetting(morphology));
        logger.info("after clicked on image setting approve and reject buttons are displayed and its enabled on PlateletTab tab");

    }

    @Test(priority = 121,enabled = true)
    public void clickOnImageSettingIconAndVerifyingCharcteristicsOnWbcTab() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath(props.getProperty("WBCTAB"))).click();
        Thread.sleep(3000);
        Assert.assertTrue(reportViewer.verifyImageCharacteristicsParameters("//tbody//tr[5]//td[2]","//tbody//tr[5]//td[1]","WBC","/html/body/div[2]/div[3]/div/div[3]/div[1]/div[2]/span/span[9]/input"));
        logger.info("image size , saturation, brightness and contrast is verified on wbc tab for neutrophil cells ");

    }

    @Test(priority = 123,enabled = false)
    public void clickOnImageSettingIconAndVerifyingCharcteristicsOnRbcTab() throws InterruptedException {
        driver.findElement(By.xpath(props.getProperty("RBCTAB"))).click();
        Thread.sleep(2000);
        Assert.assertTrue(reportViewer.verifyImageCharacteristicsParameters("//tbody//tr[1]//td[2]","//tbody//tr[1]//td[1]","WBC","/html/body/div[2]/div[3]/div/div[3]/div[1]/div[2]/span/span[9]/input"));
        logger.info("image size , saturation, brightness and contrast is verified on wbc tab for neutrophil cells ");

    }


    @Test(priority = 125,enabled = true)
    public void clickOnImageSettingIconAndVerifyingCharcteristicsOnPLTTab() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(props.getProperty("PlateletTab"))).click();
        driver.findElement(By.xpath(props.getProperty("morphology"))).click();
        Thread.sleep(2000);
        Assert.assertTrue(reportViewer.verifyImageCharacteristicsParameters("//*[@id='root']/div/div[2]/div[2]/div/div[1]/div/div[2]/div[3]/div[2]","//div[contains(text(),'Platelet Clumps')]","Platelet","/html/body/div[2]/div[3]/div/div[3]/div[1]/div[2]/span/span[9]/input"));
        logger.info("image size , saturation, brightness and contrast is verified on wbc tab for neutrophil cells ");

    }


    @Test(priority = 127,enabled = true)
    public void referenceToolIconOnWbc() throws InterruptedException {
        Thread.sleep(2000);
       Assert.assertTrue(reportViewer.presenceOfReferenceTool(props.getProperty("WBCTAB")));
       logger.info("verified the presence of linear tool bar on left side of microscopic view on wbc tab ");

    }




    @Test(priority = 129,enabled = true)
    public void selectionOfLineTool() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("lineToolButton")));
        logger.info("line tool is selected and digital zoom message is verified on WBC tab");
    }


    @Test(priority = 130,enabled = true)
    public void alteringTheSizeOfLineOnWBC() throws InterruptedException {
        Assert.assertTrue(reportViewer.alteringTheSizeOfLineOrCircle(Integer.parseInt("7")));
        logger.info("alteration of line tool is verified on WBC tab");
    }


    @Test(priority = 131,enabled = true)
    public void deselectingTheLineTool(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("lineToolButton")));
        logger.info("Linear button is deselected successfully on Wbc tab");
    }





    @Test(priority = 133,enabled = true)
    public void selectionOCircleTool() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("circleToolButton")));
        logger.info("circle tool is selected and digital zoom message is verified on WBC tab");
    }

    @Test(priority = 135,enabled = true)
    public void alternationOFCircleTool() throws InterruptedException {
        Assert.assertTrue(reportViewer.alteringTheSizeOfLineOrCircle(Integer.parseInt("2")));
        logger.info("alternation Of circle tool is verified on WBC tab");
    }

    @Test(priority = 136,enabled = true)
    public void deselectingTheCircleTool(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("circleToolButton")));
        logger.info("Circle button is deselected successfully on Wbc tab");
    }




    @Test(priority = 137,enabled = true)
    public void selectionOZoomTool() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("zoomToolButton")));
        logger.info("circle tool is selected and digital zoom message is verified on WBC tab");
    }

    @Test(priority = 139,enabled = true)
    public void alternationOfZoomLevel(){
        Assert.assertTrue(reportViewer.alteringTheZoomLevel());
        logger.info("alternation Of zoom level is verified on WBC tab");}


    @Test(priority = 140,enabled = true)
    public void deselectingTheZoomLevel(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("zoomToolButton")));
        logger.info("Linear button is deselected successfully on Wbc tab");
    }




    @Test(priority = 142,enabled = true)
    public void selectAndDeselectTheCellNameCheckBox(){
        Assert.assertTrue(reportViewer.selectAndDeselectTheCellNameCheckBox("WBC"));
        logger.info("all the enabled checkbox is selected successfully on WBC TAb ");
    }





    @Test(priority = 143,enabled = true)
    public void referenceToolBarONRbcTab() throws InterruptedException {
        Assert.assertTrue(reportViewer.presenceOfReferenceTool(props.getProperty("RBCTAB")));
        logger.info("verified the presence of linear tool bar on left side of microscopic view on rbc tab ");

    }




    @Test(priority = 145,enabled = true)
    public void selectionOfLineToolOnRBC() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("lineToolButton")));
        logger.info("line tool is selected and digital zoom message is verified on RBC tab");
    }


    @Test(priority = 146,enabled = true)
    public void alteringTheSizeOfLineOnRBC() throws InterruptedException {
        Assert.assertTrue(reportViewer.alteringTheSizeOfLineOrCircle(Integer.parseInt("23")));
        logger.info("alteration of line tool is verified on RBC tab");
    }

    @Test(priority = 148,enabled = true)
    public void deselectingTheLineToolOnRBC(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("lineToolButton")));
        logger.info("Linear button is deselected successfully on Rbc tab");
    }


    @Test(priority = 150,enabled = true)
    public void selectionOCircleToolOnRBC() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("circleToolButton")));
        logger.info("circle tool is selected and digital zoom message is verified on RBC tab");
    }


    @Test(priority = 152,enabled = true)
    public void alternationOFCircleToolOnRbc() throws InterruptedException {
        Assert.assertTrue(reportViewer.alteringTheSizeOfLineOrCircle(Integer.parseInt("10")));
        logger.info("alternation Of circle tool is verified on RBC tab");
    }

    @Test(priority = 154,enabled = true)
    public void deselectingTheCircleToolOnRBC(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("circleToolButton")));
        logger.info("Circle tool is deselected successfully on Rbc tab");
    }

    @Test(priority = 156,enabled = true)
    public void selectionOZoomToolOnRBC() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("zoomToolButton")));
        logger.info("circle tool is selected and digital zoom message is visible on RBC tab");
    }


    @Test(priority = 158,enabled = true)
    public void alternationOfZoomLevelOnRbc(){
        Assert.assertTrue(reportViewer.alteringTheZoomLevel());
        logger.info("alternation Of zoom level is verified on RBC tab");
    }

    @Test(priority = 160,enabled = true)
    public void deselectingTheZoomLevelOnRBC(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("zoomToolButton")));
        logger.info("Zoom is deselected successfully on Rbc tab");
    }



    @Test(priority = 167,enabled = true)
    public void selectAndSelectTheCellNameCheckBoxONWBC(){
        Assert.assertTrue(reportViewer.selectAndDeselectTheCellNameCheckBox("RBC"));
        logger.info("all the enabled checkbox is selected successfully on RBC TAb ");
    }



    @Test(priority = 168,enabled = true)
    public void referenceToolBarOnPltTab() throws InterruptedException {
        driver.findElement(By.xpath(props.getProperty("morphology"))).click();
        Assert.assertTrue(reportViewer.presenceOfReferenceTool(props.getProperty("PlateletTab")));
        logger.info("verified the presence of linear tool bar on left side of microscopic view on plt tab ");

    }


    @Test(priority = 170,enabled = true)
    public void selectionOfLineToolOnPlatelet() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("lineToolButton")));
        logger.info("line tool is selected and digital zoom message is verified on Platelet tab");
    }



    @Test(priority = 180,enabled = true)
    public void alteringTheSizeOfLineOnPLT() throws InterruptedException {
        Assert.assertTrue(reportViewer.alteringTheSizeOfLineOrCircle(Integer.parseInt("23")));
        logger.info("alteration of line tool is verified on Plt tab");
    }

    @Test(priority = 182,enabled = true)
    public void deselectingTheLinearToolOnPLT(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("lineToolButton")));
        logger.info("Linear button is deselected successfully on PLT tab");
    }




    @Test(priority = 184,enabled = true)
    public void selectionOCircleToolOnPlatelet() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("circleToolButton")));
        logger.info("circle tool is selected and digital zoom message is verified on Platelet tab");
    }



    @Test(priority = 186,enabled = true)
    public void alternationOFCircleToolOnPLT() throws InterruptedException {
        Assert.assertTrue(reportViewer.alteringTheSizeOfLineOrCircle(Integer.parseInt("32")));
        logger.info("alternation Of circle tool is verified on plt tab");
    }

    @Test(priority = 188,enabled = true)
    public void deselectingTheCircleToolOnPLT(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("circleToolButton")));
        logger.info("Linear button is deselected successfully on PLT tab");
    }


    @Test(priority = 190,enabled = true)
    public void selectionOZoomToolOnPlatelet() throws InterruptedException {
        Assert.assertTrue(reportViewer.selectionOfReferenceTool(props.getProperty("zoomToolButton")));
        logger.info("circle tool is selected and digital zoom message is verified on Platelet tab");
    }

    @Test(priority = 192,enabled = true)
    public void alternationOfZoomLevelInPlt(){
        Assert.assertTrue(reportViewer.alteringTheZoomLevel());
        logger.info("alternation Of zoom level is verified on plt tab");
    }

    @Test(priority = 194,enabled = true)
    public void deselectingTheZoomLevelOnPLT(){
        Assert.assertTrue(reportViewer.deselectTheReferenceTool(props.getProperty("zoomToolButton")));
        logger.info("Linear button is deselected successfully on PLT tab");
    }




    @Test(priority = 195,enabled = true)
    public void selectAndSelectTheCellNameCheckBoxOnPltC(){
        Assert.assertTrue(reportViewer.selectAndDeselectTheCellNameCheckBox("Platelet"));
        logger.info("all the enabled checkbox is selected successfully on Morphology TAb ");
    }












































}
