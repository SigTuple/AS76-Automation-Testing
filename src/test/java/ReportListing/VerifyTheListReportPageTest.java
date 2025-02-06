package ReportListing;

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

public class VerifyTheListReportPageTest extends BrowserSetUp {

    private final Logger logger = LogManager.getLogger(VerifyTheListReportPageTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    int time=30;
    Properties props;
    private CommonMethods cms;
    private VerifyTheListReportPage verifyTheListReportPage;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readReportListingProperties();
        verifyTheListReportPage = new VerifyTheListReportPage(driver);
    }

    //Verify the presence of Sigtuple logo snd PBS
    @Test(priority = 1 ,enabled = true)
    public void verifySigLogoAndPBS() {
        Assert.assertTrue(verifyTheListReportPage.uIOfSigtuplelogoAndPbs());
        logger.info(" Sigtuple logo and PBS is Present on the page");
    }

    //Verify the kebaba menu icon
    @Test(priority = 2 ,enabled = true)
    public void verifyTheKebabMenuIcon(){
        Assert.assertTrue(verifyTheListReportPage.uIOfKebab());
        logger.info("Kebab menu is Displayed on the app ");
    }

    //verify the Search ,filter and check box of my report
    @Test(priority = 3 ,enabled = true)
    public void verifytheSearchAndFilterAndCheckbox(){
        Assert.assertTrue(verifyTheListReportPage.uIOfSearchFieldAndFilterIconAndClearreport());
        logger.info(" Search textbox and Filter is Present on the page");
    }

    //verify The Functionality of My report CheckBox
    @Test(priority = 4 ,enabled = true)
    public void verifyTheFunctionalityOfTheMyReportCheckBox(){
        Assert.assertTrue(verifyTheListReportPage.uIOfMyReportCheckBox());
        logger.info("My report Check box  is Present on the page");
    }
    //verify the functionality of my report check box
    @Test(priority = 5 , enabled = true)
    public void verifyfunctionalityofmyreportcheckbox() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.myReportCheckBoxFunctionality());
        logger.info(" Verify the functionality of the my report check box");
    }


    //Verify the content of header
    @Test(priority = 6 ,enabled = true)
    public void verityHeader(){
       String listReportHeader= verifyTheListReportPage.uIOfHeaderofListReport();
        Assert.assertEquals(listReportHeader,"Slide Id Scan date Status Assigned to Additional info");
        logger.info("Header of the Report listing is displayed and verified");

    }

    // verify the search text field
    @Test(priority = 7 ,enabled = true)
    public void verifyTheFunctionalityOfSearchField() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.sampleSaerchingBySlideId());
        logger.info("Functionality of search text field is working fine");

    }

    // verify the Functionality of filter icon
    @Test(priority = 8 ,enabled = true)
    public void verifyTheFunctionalityOfFilterIcon(){
        Assert.assertTrue(verifyTheListReportPage.filterIcon());
        logger.info("Functionality of Filter icon is working Fine");

    }

    //verify the same slide id on additional info tab
    @Test(priority = 9 ,enabled = true)
    public void verifyTheSameSlideIdonAdditionalInfo() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.additionalInfoDetails());
        logger.info("Functionality Additional info icon is enable and same slide id is Displyed");
    }

    //Verify the  presence of filter option
    @Test(priority = 14 ,enabled = true)
    public void verifyThePresenceOfFilterOption() throws InterruptedException {
        Assert.assertEquals(verifyTheListReportPage.filterOption(),"Scan date\nAssigned to\nStatus\nDevice ID\nProduct Version\n");
        logger.info("Verify the Filter Options");
    }

    //verify the functionality of Reset button after selecting any option
    @Test(priority = 12 ,enabled = true)
    public  void verifyTheResetButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.resetbutton());
        logger.info("Verify the functionality of the Rest Button");
    }

    //verify the functionality of Apply button after selecting any option
    @Test(priority = 11 ,enabled = true)
    public  void verifyTheApplytButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.applybutton());
        logger.info("Verify the functionality of the apply Button");
    }
    //verify the functionality of clear filter report
    @Test(priority = 12 ,enabled = true)
    public  void verifyTheClearFilterButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.clearfilteroption());
        logger.info("Verify the functionality of the apply Button");
    }

    //verify the functionality of clear filter report
    @Test(priority = 13 ,enabled = true)
    public  void verifyTheFilterCloseButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.filterclose());
        logger.info("Verify the functionality of the Close Button");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 15 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkButton() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.bookmarkSavedStatus());
        logger.info("Verify the functionality of the Bookmark Button");
    }

    //Verify the functionality of Bookmarks for any status Reports
    @Test(priority = 16 ,enabled = true)
    public  void verifyTheFunctionalityOfBookmarkTextfield() throws InterruptedException {
        Assert.assertTrue(verifyTheListReportPage.bookmarkComment());
        logger.info("Verify the functionality of the Bookmark textfield ");
    }

    //verify the assign and reassign of reviewer on list report page
    @Test(priority = 17 , enabled = false)
    public void verifyTheAssignReviewer() throws InterruptedException {
        Assert.assertEquals(verifyTheListReportPage.assignreport(),props.getProperty("reviewer1"));
        logger.info("Functionality of assign is working ");
    }

    //verify the assign and reassign of reviewer on list report page
    @Test(priority = 18 , enabled = true)
    public void verifyTheReassignReviewer() throws InterruptedException {
        Assert.assertEquals(verifyTheListReportPage.reassignreport(),props.getProperty("reviewer2"));
        logger.info("Functionality of assign is working ");
    }







}
