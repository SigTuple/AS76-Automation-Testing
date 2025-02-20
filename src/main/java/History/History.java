package History;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import ReportListing.VerifyTheListReportPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import summary.Summary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class History {

    private final Logger logger = LogManager.getLogger(History.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;
    public VerifyTheListReportPage verifyTheListReportPage;
    public CommonMethods commonMethods;


    public History(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        commonMethods =new CommonMethods(driver);
        verifyTheListReportPage=new VerifyTheListReportPage(driver);
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readHistoryProperties();
        Property.readCommonMethodProperties();
    }

    //Verify that the History CTA displays the list of events when clicked.
    public boolean presenceOfHistoryCtc() throws InterruptedException {
        boolean flag=false;
        WebElement KebabMenu = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("KebabMenuXpath"))));
        if (KebabMenu.isDisplayed()) {
            KebabMenu.click();

            List<WebElement> KebabMenuDetails = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("kebabOptions"))));
            for (WebElement option:KebabMenuDetails) {
                if (option.getText().equals("History") && option.isEnabled()) {
                    flag = true;
                    logger.info("Kebab Menu Details are verified");
                }
            }
        }
        Thread.sleep(4000);
        return flag;
    }

    //Verify the details present in the history page of a newly generated report.
    public boolean historyOfNewlyGeneratedReport(){
        boolean flag=false;
        commonMethods.clickOnHistory();
        WebElement noEventToShowMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("noEventToShowMsg"))));
        System.out.println(noEventToShowMsg.getText());
        if (noEventToShowMsg.getText().equals("No events to show")){
            flag=true;
            logger.info("No events to show message is Displayed");

        }
        return flag;
    }

    //Verify the first-time assignment of the report and the message displayed in the history
    public void historyOfFirstTimeAssignReviewer() throws InterruptedException {
        String assignedReviewer = verifyTheListReportPage.assignreport();
        commonMethods.clickOnHistory();

        Date now = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy, hh:mm a"); // Corrected format
        String formattedDateAndTime = dateFormatter.format(now);
        String expectedDateAndTime = formattedDateAndTime + " (IST)";

        // Step 2: Define the dynamic expected values
        String expectedHeaderPrefix = "Report assignment "; // Static part of the header
        String expectedHeader = expectedHeaderPrefix + expectedDateAndTime; // Combine with dynamic date and time
        String expectedDescription = assignedReviewer+" assigned the report"; // Static description
        String expectedTransaction = "- ➝ "+assignedReviewer; // Static transaction details

        //Retrieve actual values from the webpage
        List<WebElement> eventLogs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("eventLogs"))));
        String actualHeader=eventLogs.get(0).getText();
        String actualDescription=eventLogs.get(1).getText();
        String actualTransaction=eventLogs.get(2).getText();


        // Step 4: Validate header, description, and transaction
        Assert.assertTrue(actualHeader.startsWith(expectedHeaderPrefix), "Header prefix does not match!");
        Assert.assertTrue(actualHeader.contains(expectedDateAndTime), "Date and time do not match dynamically!");
        Assert.assertEquals(actualDescription, expectedDescription, "Description does not match!");
        Assert.assertEquals(actualTransaction, expectedTransaction, "Transaction details do not match!");

    }






}
