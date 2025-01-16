package ReportSignOff;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import ReportListing.VerifyTheListReportPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import summary.Summary;
import summary.SummaryTest;
import utilities.BrowserSetUp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static utilities.BrowserSetUp.driver;

public class verifyReportSignoffTest {
    private final Logger logger = LogManager.getLogger(verifyReportSignoffTest.class);
    public WebDriverWait wait;
    public Properties props;
    public verifyReportSignoff verifyReportSignoff;
    public Summary summary;
    public ReportListing.VerifyTheListReportPage VerifyTheListReportPage;
    public CommonMethods commonMethods;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        WebDriver driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        summary =new Summary(driver);
        commonMethods=new CommonMethods(driver);
        VerifyTheListReportPage =new VerifyTheListReportPage(driver);
        verifyReportSignoff=new verifyReportSignoff(driver);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readReportSignOffProperties();

    }
    //Verify the availability of Approve report button
  /* @Test(priority = 1 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInSummary() throws InterruptedException {
        Thread.sleep(2000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("Summary");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }


    @Test(priority = 2 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInWBCInPatch() throws InterruptedException {
        commonMethods.clickOnSpecificTab("WBC");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("WBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }

    @Test(priority = 3 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCSizeInPatch() throws InterruptedException {
        commonMethods.clickOnSpecificTab("RBC");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 4 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCShapeInPatch() throws InterruptedException {

        commonMethods.clickOnSpecificSubTab("Shape");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 5 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCColorInPatch() throws InterruptedException {

        commonMethods.clickOnSpecificSubTab("Color");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 6 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCInclustionsInPatch() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 7 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCSizeInSplit() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Size");
        commonMethods.clickOnSpecificViewtab("Split view");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 8 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCShapeinSplit() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Shape");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 9 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCColorInSplit() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Color");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 10,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCInclustionsinSplit() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }

    @Test(priority = 11 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCSizeInMicroscopic() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Size");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 12 ,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCShapeInMicroscopic() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Shape");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 13,enabled = true)
    public void VerifyTheApproveAndRejectBtnInRBCColorInMicroscopic() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Color");
        Thread.sleep(5000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("RBC");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }


    @Test(priority = 14,enabled = true)
    public void VerifyTheApproveAndRejectBtnInPlateletsCountSplit() throws InterruptedException {
        commonMethods.clickOnSpecificTab("Platelets");
        Thread.sleep(2000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("Platelets");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 15,enabled = true)
    public void VerifyTheApproveAndRejectBtnInPlateletsCountMicroscopic() throws InterruptedException {
        Thread.sleep(3000);
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("Platelets");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 16,enabled = false)
    public void VerifyTheApproveAndRejectBtnInPlatelets() throws InterruptedException {
        commonMethods.clickOnSpecificSubTab("Morphology");
        Thread.sleep(2000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("Platelets");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }

    @Test(priority = 17,enabled = true)
    public void VerifyTheApproveAndRejectBtnInPlateletsMorphologySplit() throws InterruptedException {
        commonMethods.clickOnSpecificViewtab("Split view");
        Thread.sleep(2000);
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("Platelets");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }
    @Test(priority = 18,enabled = true)
    public void VerifyTheApproveAndRejectBtnInPlateletsMorphologyMicroscopic() throws InterruptedException {
        Thread.sleep(3000);
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        String actualPlaceholder = commonMethods.verifyPresenceOfTheApproveAndRejectedButtons("Platelets");
        Assert.assertEquals(actualPlaceholder,"Reject report,Approve report");
        logger.info("Approve and Reject Button are available");
    }

    @Test(priority = 19,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInSummary() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
       boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 20,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInWBC() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 21,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInWBCSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 22,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInWBCMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }
    @Test(priority = 23,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCSize() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 24,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCSizeSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 25,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCSizeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 26,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 27,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCShapeSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 28,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCShapeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 29,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCColor() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 30,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCColorSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 31,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCColorMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 32,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCInclusions() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 33,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInRBCInclusionsSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 34,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInPlateletsInCount() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 35,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInPlateletInSplt() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 36,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInPlateletsInMorphology() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 37,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInPlateletInMorphologySplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

    @Test(priority = 38,enabled = true)
    public void VerifyTheApproveButtonFunctionalityInPlateletInMorphologyMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyApproveReportFunctionality());
        logger.info("Approve Button Functionality is working fine");
        boolean backToSummaryPage = verifyReportSignoff.backToReport();
        Assert.assertTrue(backToSummaryPage);
        logger.info("Successfully back to summary page");
    }

//Verify the approve popup text in Summary page
    @Test(priority = 39,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInSummary() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }
    //Verify the approve popup text in WBC Tab

    @Test(priority = 40,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInWBC() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("WBC");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 41,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInWBCSplit() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }
    @Test(priority = 42,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInWBCMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    //Verify the approve popup text in RBC Tab

    @Test(priority = 43,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCSize() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }
    @Test(priority = 44,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCSizeSplit() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }
    @Test(priority = 45,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCSizeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 46,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCShape() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }
    @Test(priority = 47,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCShapeSPlit() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }
    @Test(priority = 48,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCShapeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 49,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCColor() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }


    @Test(priority = 50,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCColorSplit() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }


    @Test(priority = 51,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCColorMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 52,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCInclusions() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 53,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInRBCInclusionsSplit() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

// Verify the Approve pop up in platelets
    @Test(priority = 54,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInPlateletsCount() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("Platelets");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 54,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInPlateletsCountMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 55,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInPlateletsMorphology() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 56,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInPlateletsMorphologySplit() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }

    @Test(priority = 57,enabled = true)
    public void VerifyTheApproveButtonPopUpTextInPlateletsMorphologyMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        String wbcTabUnclassifiedValue = verifyReportSignoff.getValueOfUnclassifiedValueOfWBC();
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.verifyPopupContent(wbcTabUnclassifiedValue));
        logger.info("Approve Button Pop-Up is as per Design ");
    }


    //verify the Supporting image availability in all tabs

    @Test(priority = 58,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInSummary() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }


    @Test(priority = 59,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInWBC() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 60,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInWBCPatch() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 61,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInWBCMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 62,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubSize() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 63,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubSizeSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 62,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubSizeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 62,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 63,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubShapeSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 64,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubShapeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 65,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInColorSubShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 66,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubColorSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 67,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubColorMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 68,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInInclusionsSubInclusions() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 69,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInRBCSubInclusionsSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 70,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInPlateletsSubCountSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 71,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInPlateletsSubCountMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 72,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInPlateletsSubMorphology() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 73,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInPlateletsSubMorphologySplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 74,enabled = true)
    public void VerifyTheSupportingImageAvailabilityInPlateletsSubMorphologyMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfSupportingImage());
        logger.info("Supporting image Button is as per Design ");
        verifyReportSignoff.backToReport();
    }

    //verify the Supporting image Functionality in all tabs

    @Test(priority = 75,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInSummary() throws InterruptedException {
        Thread.sleep(2000);
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }


    @Test(priority = 76,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInWBC() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 77,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInWBCPatch() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 78,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInWBCMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 79,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubSize() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button Working is as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 80,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubSizeSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 81,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubSizeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 82,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 83,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubShapeSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 84,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubShapeMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 85,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInColorSubShape() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 86,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubColorSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 87,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubColorMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 88,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInInclusionsSubInclusions() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 89,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInRBCSubInclusionsSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 90,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInPlateletsSubCountSplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 91,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInPlateletsSubCountMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 92,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInPlateletsSubMorphology() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 93,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInPlateletsSubMorphologySplit() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button is Working as per Design ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 94,enabled = true)
    public void VerifyTheSupportingImageFunctionalityInPlateletsSubMorphologyMicroscopic() throws InterruptedException {
        Thread.sleep(2000);
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.functionalityOfSupportingImage());
        logger.info("Supporting image Button Working is as per Design ");
        verifyReportSignoff.backToReport();
    }

    //Verify the availability of cell names present in the left pane of select supporting images page
    @Test(priority = 95 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageSummary(){
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 96 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageWBC(){
        commonMethods.clickOnSpecificTab("WBC");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 97 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageWBCSplit(){
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 98 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageWBCMicroscopic(){
        commonMethods.clickOnSpecificTab("WBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 99 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubSize(){
        commonMethods.clickOnSpecificTab("RBC");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 97 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubSizeSplit(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 98 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubSizeMicroscopic(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 99 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubShape(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 100 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubShapeSplit(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 101 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubShapeMicroscopic(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Shape");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 102,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageColorSubColor(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 103 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubColorSplit(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 104 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubColorMicroscopic(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Color");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 105,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageColorSubInclusions(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 106 ,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePageRBCSubInclusionsSplit(){
        commonMethods.clickOnSpecificTab("RBC");
        commonMethods.clickOnSpecificSubTab("Inclusions");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 107,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePagePlateletsSubCountSplit(){
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Count");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 107,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePagePlateletsSubCountMicroscopic(){
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 108,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePagePlateletsSubMorphology(){
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 107,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePagePlateletsSubMorphologySplit(){
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }
    @Test(priority = 107,enabled = true)
    public void verifyTheAvailabilityOfCellNameInSupportingImagePagePlateletsSubMorphologyMicroscopic(){
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificSubTab("Morphology");
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.availabilityOfCellName());
        logger.info("Supporting image Page Eligible cell name are available ");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 108 ,enabled = true)
    public void verifyTheCheckBoxSelectAndDeselect(){
        Assert.assertTrue(verifyReportSignoff.verifyTheCheckBoxOfEligibleCell());
        logger.info("Check box Functionality is Working");
         verifyReportSignoff.backToReport();
    }

    @Test(priority = 109 ,enabled = true)
    public void verifyTheModifyButtonAvailability(){
        Assert.assertTrue(verifyReportSignoff.verifyTheCheckBoxOfEligibleCellModifyBtn());
        logger.info("Check box Functionality is Working");
        verifyReportSignoff.backToReport();
    }

    @Test(priority = 110 ,enabled = true)
    public void verifyTextOnPsImpressionInPreviewReport() throws InterruptedException {
        Assert.assertTrue(verifyReportSignoff.psImpressioninPreviewReport());
        logger.info("PS impression is same as in Summary page");
        verifyReportSignoff.backToReport();
    }*/


    @Test(priority = 111, enabled = true)
    public void verifyTheReportSignOffAndVerifyPDF() throws InterruptedException, IOException {
        Assert.assertTrue(verifyReportSignoff.approveButtonFromApprovePopup());
        logger.info("Report Approved Successfully");
            // Define locators for expected data from the web app
            Map<String, String> locators = new HashMap<>();
            locators.put("Slide Id:", "//div[contains(@class,'appBar_slide-details__slide-id')]/span");
            locators.put("WBC","//th[text()='WBC']");
            locators.put("Approved by:", "//span[contains(@class,'appBar_name')]");
            locators.put("Peripheral Smear Report", "//span[text()='Peripheral Smear Report']");
            locators.put("Platelet count (x 10^9/L)","//span[contains(@class,'bII reportSummary_ml-factor')]");

            // Fetch expected values from the web app
            Map<String, String> expectedValues = verifyReportSignoff.fetchExpectedValues(locators);

            // Path to the downloaded PDF
            String oldPDFPath = "/Users/manjunathcg/Downloads/AS76-REPORTING/download/pdfReport.pdf";

            // Extract Slide ID to rename the PDF
            String slideID = expectedValues.get("Slide Id:");
            String newPDFName = slideID + ".pdf";
            verifyReportSignoff.renamePDFFile(oldPDFPath, newPDFName);

            // Extract content from the renamed PDF
            String pdfFilePath = oldPDFPath.replace("pdfReport.pdf", newPDFName);
            String pdfContent =verifyReportSignoff.extractPDFContent(pdfFilePath);

            // Validate PDF content against the expected values
            for (Map.Entry<String, String> entry : expectedValues.entrySet()) {
                String key = entry.getKey();
                String expectedValue = entry.getValue();

                Assert.assertTrue(pdfContent.contains(expectedValue),
                        "Mismatch for " + key + "! Expected: " + expectedValue);
            }

            System.out.println("PDF content verification passed!");
        driver.navigate().refresh();
        WebElement backToReportArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'appBar_back-button')]")));
        backToReportArrow.click();
        driver.navigate().refresh();
        }


        @Test(priority = 112, enabled = true)
        public void reopenReport(){

            Assert.assertTrue(VerifyTheListReportPage.clickOnAssignedReport());
            logger.info("Summary page is Displayed successfully");

        }










    @Test(priority = 116, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInSummary(){
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority = 117, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInWBC(){
        commonMethods.clickOnSpecificTab("WBC");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority = 118, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCSize(){
        commonMethods.clickOnSpecificTab("RBC");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority = 119, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCSizeSplit(){
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority = 120, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCSizeMicroscopic(){
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority =121, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCShape(){
        commonMethods.clickOnSpecificTab("Shape");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority = 122, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCShapeSplit(){
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority = 123, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCShapeMicroscopic(){
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority =124, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCColor(){
        commonMethods.clickOnSpecificTab("Color");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority =125, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCColorSplit(){
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority =126, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCColorMicroscopic(){
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
        commonMethods.clickOnSpecificViewtab("Split view");
    }

    @Test(priority =127, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCInclusions(){
        commonMethods.clickOnSpecificTab("Inclusions");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }
    @Test(priority = 128, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInRBCInclusionsSplit(){
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority = 129, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInPlateletsCountInSplit(){
        commonMethods.clickOnSpecificTab("Platelets");
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority =130, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInPlateletsCountInMicroscopic(){
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority = 131, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInPlateletsMorphologyInSplit(){
        commonMethods.clickOnSpecificViewtab("Split view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }

    @Test(priority = 132, enabled = true)
    public void verifyTheContentsOfRejectBtnPopupInPlateletsMorphologyInMicroscopic(){
        commonMethods.clickOnSpecificViewtab("Microscopic view");
        Assert.assertTrue(verifyReportSignoff.rejectReportPopup());
        logger.info("All the Contents are available in Reject report Pop-Up");
    }


    @Test(priority = 133 ,enabled = true)
    public void verifyTheRejectFromReportPreviewPage() throws InterruptedException {
        Assert.assertTrue(verifyReportSignoff.rejectReportFunctionality());
        logger.info("Report Reject Successfully");
    }











}
