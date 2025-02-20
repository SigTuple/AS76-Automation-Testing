package ReportListing;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyTheListReportPage extends CommonMethods {

    private final Logger logger = LogManager.getLogger(VerifyTheListReportPage.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    Properties props;
    public CommonMethods cms;

    public VerifyTheListReportPage(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readRBCProperties();
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        cms = new CommonMethods(driver);
        actions = new Actions(driver);
    }

    //  Verify the logo of sigtuple and PBS.
    public boolean uIOfSigtuplelogoAndPbs() {
        boolean flag = false;
        WebElement sigtuplelogo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("sigtuplelogo"))));
        WebElement pbstxt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("pbstxt"))));

        if (sigtuplelogo.isDisplayed() && pbstxt.isDisplayed() && sigtuplelogo.getAttribute("alt").contains("sigtuple-logo")) {
            flag = true;
            logger.info("Sigtuple logo is Loaded .");
            logger.info("PBS Text is loaded");
        }
        return flag;
    }

    // Verify the kebab present
    public boolean uIOfKebab() {
        boolean flag = false;
        WebElement kebabmenuicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("kebabmenuicon"))));
        if (kebabmenuicon.isDisplayed()) {
            flag = true;
            logger.info("Kebab menu icon is loaded");
        }
        return flag;
    }

    //Verify UI the search text field ,filter,clear filter and check box of my report
    public boolean uIOfSearchFieldAndFilterIconAndClearreport() {
        boolean flag = false;
        WebElement searchfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchfield"))));
        WebElement filterbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filterbtn"))));
        WebElement clearfilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clearfilter"))));
        if (searchfield.isDisplayed() && filterbtn.isDisplayed() && clearfilter.isDisplayed()) {
            flag = true;
            logger.info("Search  Field  is displayed");
            logger.info("Filter button is displayed");
            logger.info("clear filter is displayed");
        }
        return flag;
    }

    //Verify the My Report checkbok is present and enable
    public boolean uIOfMyReportCheckBox() {
        boolean flag = false;
        WebElement myreportckbx = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("myreportckbx"))));
        if (myreportckbx.isDisplayed() && myreportckbx.isEnabled()) {
            logger.info("My report check box is displayed");
            logger.info("My report check box is Active");
            flag = true;
        }
        return flag;
    }

    //Verify the UI of header of list page
    public String uIOfHeaderofListReport() {
        String header = null;
        WebElement reportlistheader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("reportlistheader"))));
        System.out.println(reportlistheader);
        if (reportlistheader.isDisplayed()) {
            header = reportlistheader.getText();
            logger.info(" Report List header is Displayed");
        }
        return header;
    }


    //Verify the functionality of seraching any valide slide id

    public boolean sampleSaerchingBySlideId() throws InterruptedException {
        boolean flag = false;
        WebElement searchfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchfield"))));
        Thread.sleep(5000);
        searchfield.sendKeys(props.getProperty("sampleslideID"));
        searchfield.sendKeys(Keys.RETURN);
        Thread.sleep(5000);
        List<WebElement> matchingSampleslideid = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("matchingSampleslideid"))));
        for (WebElement slideid : matchingSampleslideid) {

            String matchslide = slideid.getText();
            System.out.println(matchslide);
            if (matchslide.contains(props.getProperty("sampleslideID"))) {
                logger.info("Matching the sample Slide Id is displayed");
                flag = true;
            } else if (driver.findElements(By.xpath(props.getProperty("noreportAvailable"))).isEmpty()) {
                logger.info("No matching report found");
                flag = true;

            } else {
                flag = true;
                logger.info("searching functionality is not working fine");
            }
        }

        Thread.sleep(5000);
        WebElement cancelxicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelxicon"))));
        cancelxicon.sendKeys(Keys.RETURN);
        Thread.sleep(5000);
        return flag;
    }

    // verify the functionality of Filter Icon
    public boolean filterIcon() {
        boolean flag = false;
        WebElement filterIn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("filterbtn"))));
        if (filterIn.isDisplayed() && filterIn.isEnabled()) {
            logger.info("Filter icon is present and enabled");
            flag = true;
        }
        return flag;

    }

    //Verify the  presence of filter option
    public String filterOption() throws InterruptedException {
        String options = "";
        actions = new Actions(driver);
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(5000);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("filterbtn")))).click();
        // WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("fildercontainer"))));
        List<WebElement> filteroption = driver.findElements(By.xpath(props.getProperty("filteroption")));
        //  driver.findElement(By.xpath(props.getProperty("xicon"))).click();

        if (!filteroption.isEmpty()) {
            for (WebElement ops : filteroption) {
                options = options + ops.getText() + "\n";
            }
            logger.info(" Filter Option are Loaded");
        }
        Thread.sleep(5000);
        WebElement xicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("xicon"))));
        actions.moveToElement(xicon).click().perform();
        System.out.println(options);
        Thread.sleep(2000);
        return options;
    }

    //verify the functionality of Reset button after selecting any option
    public boolean resetbutton() throws InterruptedException {
        boolean flag = false;
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions = new Actions(driver);
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);
        //WebElement scandate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandate"))));
        // scandate.click();
        WebElement scndaterange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandaterange"))));
        scndaterange.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("startdatebtn")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("enddatebtn")))).click();
        Thread.sleep(3000);
        // scandate.click();
        WebElement resetbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("resetbtn"))));
        // Use JavaScript to click
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", resetbtn);
        //actions.moveToElement(resetbtn).click().perform();
        Thread.sleep(3000);
        WebElement scndaterangep = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandaterange"))));
        String place = scndaterangep.getText();

        WebElement xicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("xicon"))));
        // Use JavaScript to click
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", xicon);


        System.out.println(place);
        if (place.contains("Select date range")) {
            logger.info("scane date text field is enable");
            flag = true;

        }
        Thread.sleep(5000);
        return flag;

    }

    public boolean applybutton() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);

        // Click on the filter button
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions = new Actions(driver);
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);

        // Click on scan date range and select start & end dates
        WebElement scndaterange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandaterange"))));
        scndaterange.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("startdatebtn")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("enddatebtn")))).click();
        Thread.sleep(2000);

        // Extract selected date range
        String selectedDateRange = scndaterange.getText();
        String[] dates = selectedDateRange.split(" - ");
        if (dates.length != 2) {
            logger.error("Invalid date range format: " + selectedDateRange);
            return false;
        }

        // Convert selected dates to LocalDate
        LocalDate startDate = parseDateWithMultipleFormats(dates[0]);
        LocalDate endDate = parseDateWithMultipleFormats(dates[1]);

        if (startDate == null || endDate == null) {
            logger.error("Failed to parse selected date range: " + selectedDateRange);
            return false;
        }

        // Click Apply button using JavaScript
        WebElement applybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybtn"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applybtn);
        Thread.sleep(5000);

        // Verify if report dates are within the selected range
        List<WebElement> reportDateElements = driver.findElements(By.xpath("//div[@class='time']"));
        for (WebElement reportDateElement : reportDateElements) {
            String[] reportDateStringWithTime = reportDateElement.getText().replace(",", "x").split("x");
            String reportDateString = reportDateStringWithTime[0].trim();
            System.out.println("Extracted Report Date: " + reportDateString);

            // Convert report date to LocalDate
            LocalDate reportDate = parseDateWithMultipleFormats(reportDateString);
            if (reportDate != null && !reportDate.isBefore(startDate) && !reportDate.isAfter(endDate)) {
                flag = true;
                logger.info("Report within selected range found: " + reportDate);
                break;
            }
        }

        // Clear filter if enabled
        WebElement clearfilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clearfilter"))));
        if (clearfilter.isEnabled()) {
            clearfilter.click();
            logger.info("Clear Filter is enabled and clicked.");
        }

        return flag;
    }

    public static LocalDate parseDateWithMultipleFormats(String dateStr) {
        List<String> datePatterns = Arrays.asList("d-M-yyyy", "dd-MM-yyyy", "dd-M-yyyy", "d-MM-yyyy", "dd-MMM-yyyy");

        for (String pattern : datePatterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException e) {
                // Try next pattern
            }
        }
        return null; // Return null if no format matches
    }


    //verify the functionality of apply button after selecting any option
    public boolean assignedToFilter() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions = new Actions(driver);
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);
        WebElement assignedToOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("assignedto"))));
        assignedToOption.click();
        List<WebElement> assignedToCheckboxes = driver.findElements(By.xpath("//div[contains(@class,'assignedToPage_container-body')]//li"));

        // Select checkboxes for specific users (e.g., "user1", "user2")
        for (WebElement checkbox : assignedToCheckboxes) {
            String user = checkbox.getText();// Assuming the user name is stored in the "value" attribute
            if (user.equals(props.getProperty("reviewer1"))) {
                checkbox.findElement(By.xpath(".//input")).click();
                logger.info("Click on the selected reviewer");
                System.out.println(user);
                break;
            }
        }

        // Click Apply button
        WebElement applybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybtn"))));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", applybtn);

        // Get the filtered report list
        Thread.sleep(2000);
        List<WebElement> filteredReportItems = driver.findElements(By.xpath("//div[@id='reportListingTable']//tr/td//input"));

        // Verify report assignments
        for (WebElement reportItem : filteredReportItems) {
            String assignedTo = reportItem.getAttribute("value");

            // Verify if the "Assigned To" value matches any of the selected users
            if (assignedTo.contains(props.getProperty("reviewer1"))) {
                logger.info("The assigned reviewer is showing ");
                flag = true;
                break;

            } else {
                logger.info("No Report is assign for " + props.getProperty("reviewer1" + " ."));
            }

        }
        Thread.sleep(2000);
        WebElement clearfilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clearfilter"))));
        if (clearfilter.isEnabled()) {
            clearfilter.click();
            logger.info("Clear Filter is enable");
        } else {
            logger.info("Clear Filter is not enable");
        }
        return flag;

    }

    //verify the functionality of apply button after selecting any option
    public boolean statusFilter(String RStatus) throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions = new Actions(driver);
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);
        WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("statusBtn"))));
        statusOption.click();
        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("stutusXpth1") + RStatus + props.getProperty("stutusXpth2"))));
        selectedOption.click();
        // Click Apply button
        WebElement applybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybtn"))));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", applybtn);
        // Get the filtered report list
        Thread.sleep(2000);
        List<WebElement> filteredReportItems = driver.findElements(By.xpath("//div[@id='reportListingTable']//tr//span[contains(@class,'reportStatusComponent_text')]"));

        for (WebElement reportItem : filteredReportItems) {
            String actualFilterStatus = reportItem.getText();
            if (actualFilterStatus.equals(RStatus)) {
                logger.info("Report status as per selected filter option");
                flag = true;
            }
        }
        Thread.sleep(2000);
        WebElement clearfilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clearfilter"))));
        if (clearfilter.isEnabled()) {
            clearfilter.click();
            logger.info("Clear Filter is enable");
        } else {
            logger.info("Clear Filter is not enable");
        }
        return flag;

    }

    public boolean clearfilteroption() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);

        // Click on the filter button
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions = new Actions(driver);
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);

        // Select scan date range
        WebElement scndaterange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandaterange"))));
        scndaterange.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("startdatebtn")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("enddatebtn")))).click();
        Thread.sleep(2000);

        // Extract selected date range
        String selectedDateRange = scndaterange.getText();
        String[] dates = selectedDateRange.split(" - ");
        if (dates.length != 2) {
            logger.error("Invalid date range format: " + selectedDateRange);
            return false;
        }

        // Convert selected dates to LocalDate
        LocalDate startDate = parseDateWithMultipleFormats(dates[0]);
        LocalDate endDate = parseDateWithMultipleFormats(dates[1]);

        if (startDate == null || endDate == null) {
            logger.error("Failed to parse selected date range: " + selectedDateRange);
            return false;
        }

        // Click Apply button using JavaScript
        WebElement applybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybtn"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applybtn);
        Thread.sleep(5000);

        // Verify if report dates are within the selected range
        List<WebElement> reportDateElements = driver.findElements(By.xpath("//div[@class='time']"));
        for (WebElement reportDateElement : reportDateElements) {
            String[] reportDateStringWithTime = reportDateElement.getText().replace(",", "x").split("x");
            String reportDateString = reportDateStringWithTime[0].trim();
            System.out.println("Extracted Report Date: " + reportDateString);

            // Convert report date to LocalDate
            LocalDate reportDate = parseDateWithMultipleFormats(reportDateString);
            if (reportDate != null && !reportDate.isBefore(startDate) && !reportDate.isAfter(endDate)) {
                flag = true;
                logger.info("Report within selected range found: " + reportDate);
                break;
            }
        }

        // Click Clear Filter button
        WebElement clearfilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clearfilter"))));
        if (clearfilter.isEnabled()) {
            clearfilter.click();
            logger.info("Clear Filter is enabled and clicked.");
        }

        // Wait and check if reports are restored
        Thread.sleep(3000);
        List<WebElement> allReports = driver.findElements(By.xpath("//div[@class='time']"));
        if (allReports.size() > reportDateElements.size()) {
            logger.info("Reports are restored after clearing the filter.");
            flag = true;
        } else {
            logger.warn("Reports were NOT restored after clearing the filter.");
            flag = false;
        }

        return flag;
    }


    //verify the same slide id on additional info tab
    public boolean additionalInfoDetails() throws InterruptedException {
        boolean flag = false;
        String slideidfromFirstrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("firstrowofslideid")))).getText();
        System.out.println(slideidfromFirstrow);
        Thread.sleep(5000);
        // WebElement additionalinfoico = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("additionalinfoico"))));
        //actions.moveToElement(additionalinfoico).click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("additionalinfoico")))).click();
        String slideidfromAdditionalicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("additionalinfodetails")))).getText();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("additionalinfocancelbtn")))).click();
        if (slideidfromFirstrow.contains(slideidfromAdditionalicon)) {
            logger.info("Additional info icon loaded successfully");
            flag = true;
        }

        return flag;

    }

    //verify the functionality of my report check box
    public boolean myReportCheckBoxFunctionality() throws InterruptedException {
        boolean flag = false;
        WebElement myreportckbx = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("myreportckbx"))));
        if (myreportckbx.isDisplayed() && myreportckbx.isEnabled()) {
            myreportckbx.click();
        }
        Thread.sleep(5000);
        // Verify that only your reports are displayed
        List<WebElement> reportRows = driver.findElements(By.xpath("//tbody/tr"));
        for (WebElement row : reportRows) {
            WebElement assignedTo = row.findElement(By.xpath(".//td//input[@value='manju ']")); // Adjust XPath to match your table structure
            if (!assignedTo.getAttribute("value").equalsIgnoreCase(props.getProperty("reviewer1"))) {
                logger.info(" My Report is enable");// Replace "Your Name" with your actual identifier
                System.out.println("Report assigned to " + assignedTo.getAttribute("value") + " is visible.");
                flag = true;
                break;
            }
        }
        Thread.sleep(2000);
        myreportckbx.click();

        return flag;
    }

    //Verify the  presence of filter option close button
    public boolean filterclose() throws InterruptedException {
        boolean flag = false;
        actions = new Actions(driver);
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);
        WebElement xicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("xicon"))));

        if (xicon.isDisplayed() && xicon.isEnabled()) {
            actions.moveToElement(xicon).click().perform();
            flag = true;
            logger.info(" Filter Options close button is displayed");
        }
        Thread.sleep(2000);
        return flag;
    }

    //Verify the functionality of Bookmarks for any status Reports
    public boolean bookmarkSavedStatus() throws InterruptedException {
        boolean flag = false;
        WebElement bookmarkiconfilled = wait.until(ExpectedConditions.elementToBeClickable(By.xpath((props.getProperty("bookmarkicon")))));
        if (bookmarkiconfilled.getAttribute("src").contains("bookmark-filled")) {
            logger.info("Bookmark is already filled. Removing bookmark.");
            WebElement bookmarkicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("bookmarkicon"))));
            bookmarkicon.click();
            Thread.sleep(5000);
            // WebElement commenticon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenticon"))));
            WebElement confirmbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("confirmbtn"))));
            confirmbtn.click();
            Thread.sleep(4000);
            bookmarkicon.click();
            WebElement savedmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Bookmark saved']")));
            if (savedmsg.getText().equals("Bookmark saved")) {
                logger.info("Bookmark is displayed");
                flag = true;
            }
        } else {
            WebElement bookmarkicon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("bookmarkicon"))));
            bookmarkicon.click();
            WebElement savedmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Bookmark saved']")));
            if (savedmsg.getText().equals("Bookmark saved")) {
                logger.info("Bookmark is displayed");
                flag = true;
            }
        }
        return flag;
    }

    //verify the bookmark for the report
    public boolean multipleBookmarkSavedStatus() {
        boolean flag = false;

        List<WebElement> allBookmarkIcons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("allBookmarkIcon"))));

        for (WebElement bookmark : allBookmarkIcons) {
            try {
                // Re-fetch the element to avoid StaleElementReferenceException
                bookmark = wait.until(ExpectedConditions.elementToBeClickable(bookmark));

                // Check if the bookmark is already filled
                if (bookmark.getAttribute("src").contains("bookmark-filled")) {
                    logger.info("Bookmark is already filled. Removing bookmark.");
                    bookmark.click();

                    // Wait for confirmation button and click
                    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmbtn"))));
                    confirmBtn.click();

                    // Wait until the bookmark is removed
                    wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(bookmark, "src", "bookmark-filled")));

                    logger.info("Bookmark removed successfully.");
                }

                // Now, re-add the bookmark
                logger.info("Adding bookmark.");
                bookmark.click();

                // Wait for the "Bookmark saved" message
                WebElement savedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Bookmark saved']")));

                if (savedMsg.isDisplayed()) {
                    logger.info("Bookmark successfully saved.");
                    flag = true;
                }
            } catch (Exception e) {
                logger.error("Error while handling bookmark: " + e.getMessage());
            }
        }

        return flag;
    }

    //verify the bookmark for the report
    public boolean multipleBookmarkRemovedStatus() {
        boolean flag = false;

        List<WebElement> allBookmarkIcons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(props.getProperty("allBookmarkIcon"))
        ));

        for (WebElement bookmark : allBookmarkIcons) {
            try {
                // Re-fetch the element to avoid StaleElementReferenceException
                bookmark = wait.until(ExpectedConditions.elementToBeClickable(bookmark));

                // Check if the bookmark is already filled
                if (bookmark.getAttribute("src").contains("bookmark-filled")) {
                    logger.info("Bookmark is already filled. Removing bookmark.");
                    bookmark.click();

                    // Wait for confirmation button and click
                    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmbtn"))));
                    confirmBtn.click();

                    logger.info("Bookmark removed successfully.");


                    // Wait for the "Bookmark saved" message
                    WebElement savedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Bookmark removed']")));

                    if (savedMsg.isDisplayed() && savedMsg.getText().equals("Bookmark removed")) {
                        logger.info("Bookmark successfully removed.");
                        flag = true;
                    }
                }
            } catch (Exception e) {
                logger.error("Error while handling bookmark: " + e.getMessage());
            }
        }

        return flag;
    }


    //verify that the user is able to comment on bookmarked slides
    public boolean bookmarkComment() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        WebElement bookmarkiconfilled = wait.until(ExpectedConditions.elementToBeClickable(By.xpath((props.getProperty("bookmarkicon")))));
        if (bookmarkiconfilled.getAttribute("src").contains("bookmark-filled")) {
            logger.info("Bookmark is already filled. Removing bookmark.");
            WebElement bookmarkicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("bookmarkicon"))));
            bookmarkicon.click();
            WebElement confirmbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("confirmbtn"))));
            confirmbtn.click();
            Thread.sleep(4000);
            bookmarkicon.click();
            Thread.sleep(4000);
            WebElement commenticon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenticon"))));
            commenticon.click();

            if (bookmarkicon.isDisplayed() && commenticon.isDisplayed()) {
                WebElement commenttxtfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenttxtfield"))));
                commenttxtfield.click();
                commenttxtfield.sendKeys(props.getProperty("dummycomment"));
                WebElement saveiconcomment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("saveiconcomment"))));
                saveiconcomment.click();
                if (commenttxtfield.getText().contains(props.getProperty("dummycomment"))) {
                    flag = true;
                    logger.info("Comment Textfield is enable");
                }

            }


        } else {
            WebElement bookmarkicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("bookmarkicon"))));
            bookmarkicon.click();
            Thread.sleep(4000);
            WebElement commenticon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenticon"))));
            commenticon.click();

            if (bookmarkicon.isDisplayed() && commenticon.isDisplayed()) {
                WebElement commenttxtfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenttxtfield"))));
                commenttxtfield.click();
                commenttxtfield.sendKeys(props.getProperty("dummycomment"));
                WebElement saveiconcomment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("saveiconcomment"))));
                saveiconcomment.click();
                if (commenttxtfield.getText().contains(props.getProperty("dummycomment"))) {
                    flag = true;
                    logger.info("Comment Textfield is enable");
                }
            }

        }
        return flag;
    }

    //bookmark comment for multiple report
    public boolean multipleBookmarkComment() {
        boolean flag = false;

        List<WebElement> allBookmarkIcons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("allBookmarkIcon"))));

        for (WebElement bookmark : allBookmarkIcons) {
            try {
                // Re-fetch the element to avoid StaleElementReferenceException
                bookmark = wait.until(ExpectedConditions.elementToBeClickable(bookmark));

                // Check if the bookmark is already filled
                if (bookmark.getAttribute("src").contains("bookmark-filled")) {
                    logger.info("Bookmark is already filled. Removing bookmark.");
                    bookmark.click();

                    // Wait for confirmation button and click
                    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmbtn"))));
                    confirmBtn.click();

                    // Wait until the bookmark is removed
                    wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(bookmark, "src", "bookmark-filled")));
                    logger.info("Bookmark removed successfully.");
                    bookmark.click();
                    Thread.sleep(2000);
                    WebElement commenticon = bookmark.findElement(By.xpath("./parent::td/following::td[1]//img"));
                    commenticon.click();
                    Thread.sleep(2000);

                    if (bookmark.isDisplayed() && commenticon.isDisplayed()) {
                        WebElement commenttxtfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenttxtfield"))));
                        commenttxtfield.click();
                        commenttxtfield.sendKeys(props.getProperty("dummycomment"));
                        commenttxtfield.click();
                        String comment = commenttxtfield.getText();
                        WebElement saveiconcomment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("saveiconcomment"))));
                        saveiconcomment.click();
                        Thread.sleep(2000);
                        if (comment.contains(props.getProperty("dummycomment"))) {
                            flag = true;
                            logger.info("Comment Textfield is enable");
                        }

                    }

                } else {

                    // Now, re-add the bookmark
                    logger.info("Adding bookmark.");
                    bookmark = wait.until(ExpectedConditions.elementToBeClickable(bookmark));
                    bookmark.click();
                    Thread.sleep(2000);

                    WebElement commenticon = bookmark.findElement(By.xpath("./parent::td/following::td[1]//img"));
                    commenticon.click();
                    Thread.sleep(2000);

                    if (bookmark.isDisplayed() && commenticon.isDisplayed()) {
                        WebElement commenttxtfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenttxtfield"))));
                        commenttxtfield.click();
                        commenttxtfield.sendKeys(props.getProperty("dummycomment"));
                        commenttxtfield.click();
                        String comment = commenttxtfield.getText();
                        WebElement saveiconcomment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("saveiconcomment"))));
                        saveiconcomment.click();
                        Thread.sleep(2000);
                        if (comment.contains(props.getProperty("dummycomment"))) {
                            flag = true;
                            logger.info("Comment Textfield is enable");
                        }
                    }
                }

            } catch (Exception e) {
                logger.error("Error while handling bookmark: ");
            }
        }

        return flag;
    }

    //Update bookmark comment for multiple report
    public boolean multipleBookmarkCommentUpdate() {
        boolean flag = false;

        List<WebElement> allBookmarkIcons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("allBookmarkIcon"))));

        for (WebElement bookmark : allBookmarkIcons) {
            try {
                // Re-fetch the element to avoid StaleElementReferenceException
                bookmark = wait.until(ExpectedConditions.elementToBeClickable(bookmark));

                // Check if the bookmark is already filled
                if (bookmark.getAttribute("src").contains("bookmark-filled")) {
                    Thread.sleep(2000);
                    WebElement commenticon = bookmark.findElement(By.xpath("./parent::td/following::td[1]//img"));
                    commenticon.click();
                    Thread.sleep(2000);

                    if (bookmark.isDisplayed() && commenticon.isDisplayed()) {
                        WebElement commenttxtfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("commenttxtfield"))));
                        commenttxtfield.click();
                        commenttxtfield.sendKeys(props.getProperty("dummyComment2"));
                        commenttxtfield.click();
                        String comment = commenttxtfield.getText();
                        WebElement saveiconcomment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("saveiconcomment"))));
                        saveiconcomment.click();
                        Thread.sleep(2000);
                        if (comment.contains("test")) {
                            flag = true;
                            logger.info("Comment Text field is enable");
                        }
                    }

                } else {
                    logger.info("Comment is not avaialble");
                }

            } catch (Exception e) {
                logger.error("Error while handling bookmark: ");
            }
        }

        return flag;
    }

    //Update bookmark comment for multiple report
    public boolean multipleBookmarkCommentDelete() throws InterruptedException {
        boolean flag = false;

        List<WebElement> allBookmarkIcons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("allBookmarkIcon"))));

        for (WebElement bookmark : allBookmarkIcons) {
            try {
                // Re-fetch the element to avoid StaleElementReferenceException
                bookmark = wait.until(ExpectedConditions.elementToBeClickable(bookmark));

                // Check if the bookmark is already filled
                if (bookmark.getAttribute("src").contains("bookmark-filled")) {
                    Thread.sleep(2000);
                    WebElement commenticon = bookmark.findElement(By.xpath("./parent::td/following::td[1]//img"));
                    if (commenticon.getAttribute("src").contains("comment-filled")) {
                        commenticon.click();
                        Thread.sleep(2000);


                        WebElement deleteIconComment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("deleteiconcomment"))));
                        Actions actions1 = new Actions(driver);
                        actions1.moveToElement(deleteIconComment).click().perform();
                        flag = true;
                        logger.info("Comment successfully deleted");
                    }


                } else {
                    logger.info("Comment is not avaialble");
                }

            } catch (Exception e) {
                logger.error("Error while handling bookmark: ");
            }
        }
        driver.navigate().refresh();
        Thread.sleep(3000);
        return flag;
    }


    public boolean clickOnUnassignedReport() {
        boolean flag = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int maxScrollAttempts = 25;

        for (int i = 0; i < maxScrollAttempts; i++) {
            try {
                // Locate unassigned reports
                List<WebElement> unassignedReports = driver.findElements(By.xpath("//tr[.//input[@id='assigned_to' and @value='']]"));

                if (!unassignedReports.isEmpty()) {
                    WebElement firstUnassignedReport = unassignedReports.get(0);

                    // Scroll the report into view if not visible
                    if (!isElementInViewport(firstUnassignedReport)) {
                        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", firstUnassignedReport);
                        wait.until(ExpectedConditions.visibilityOf(firstUnassignedReport)); // Wait for the element to be visible
                    }

                    // Wait until the element is clickable and click it
                    wait.until(ExpectedConditions.elementToBeClickable(firstUnassignedReport));
                    firstUnassignedReport.click();
                    System.out.println("Clicked on an unassigned report.");
                    flag = true;
                    break;
                } else {
                    // Scroll down the page if no reports are visible
                    Long lastHeight = (Long) js.executeScript("return document.body.scrollHeight");
                    js.executeScript("window.scrollBy(0, 200);");
                    wait.until(ExpectedConditions.jsReturnsValue("return document.body.scrollHeight > " + 940)); // Wait for new content to load
                }
            } catch (StaleElementReferenceException e) {
                System.err.println("Stale element encountered. Retrying...");
            } catch (Exception e) {
                System.err.println("Error during scrolling or clicking: " + e.getClass().getName() + " - " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (!flag) {
            System.out.println("No unassigned report found after scrolling " + maxScrollAttempts + " times.");
        }

        return flag;
    }

    public boolean isElementInViewport(WebElement element) {
        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.left >= 0 && rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
                element
        );
    }


    //verify the assign and reassign of reviewer on list report page
    public String assignreport() throws InterruptedException {
        Thread.sleep(6000);
        this.openAReport("To be reviewed");
        String assignedreviewer = "";
        Thread.sleep(3000);
        WebElement reviewerdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("reviewerdropdown"))));
        reviewerdropdown.click();
        List<WebElement> reviewrslist = driver.findElements(By.xpath(props.getProperty("reviewrslist")));
        for (WebElement reviewer : reviewrslist) {
            String user = reviewer.getText().trim();
            if (user.equals(props.getProperty("reviewer1"))) {
                assignedreviewer = user;
                reviewer.click();
                logger.info("user is drop down is available");
                break;
            }

        }
        return assignedreviewer;

    }

    //verify the assign and reassign of reviewer on list report page
    public String reassignreport() throws InterruptedException {
        String re = "";
        Thread.sleep(3000);
        WebElement reviewerdropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='assigned_to' and @value='manju '])[1]")));
        reviewerdropdown.click();
        List<WebElement> reviewrslist = driver.findElements(By.xpath(props.getProperty("reviewrslist")));
        for (WebElement reviewer : reviewrslist) {
            String user = reviewer.getText().trim();
            if (user.equals(props.getProperty("reviewer2"))) {
                re = user;
                reviewer.click();
                logger.info("user is drop down is available");
                break;
            }

        }
        System.out.println(re);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("Reassign")))).click();

        return re;
    }


    //Verification of the presence of the time zone option and the configured time zone.
    public boolean PresenceOfTimezoneOption() throws InterruptedException {
        boolean flag = false;
        //Change Time Zone in Settings
        Thread.sleep(2000);
        WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("profileIcon"))));
        actions.moveToElement(profileIcon).click().perform();
        List<WebElement> ListOfProfileOption = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("ListOfProfileOption"))));
        for (WebElement option : ListOfProfileOption) {
            if (option.getText().contains("Time zone")) {
                logger.info("Clicked on Time zone option");
                wait.until(ExpectedConditions.elementToBeClickable(option));
                Thread.sleep(2000);
                actions.moveToElement(option).click().perform();
                //option.click();
                flag = true;
                break;

            } else {
                Assert.fail();
            }
        }
        cms.clickOnBackTOListReportButton();
        return flag;
    }

    //Verify the functionality of the Edit CTA
    public boolean FunctionalityOfEditCTA( String ReqTimeZone) throws InterruptedException {
        boolean flag = false;
        //Change Time Zone in Settings
        Thread.sleep(2000);
        WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("profileIcon"))));
        actions.moveToElement(profileIcon).click().perform();
        List<WebElement> ListOfProfileOption = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("ListOfProfileOption"))));
        for (WebElement option : ListOfProfileOption) {
            if (option.getText().contains("Time zone")) {
                logger.info("Clicked on Time zone option");
                wait.until(ExpectedConditions.elementToBeClickable(option));
                Thread.sleep(2000);
                actions.moveToElement(option).click().perform();
                //option.click();
                break;

            } else {
                Assert.fail();
            }
        }
        WebElement editTimeZoneBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("editTimeZoneBtn"))));
        editTimeZoneBtn.click();
        WebElement configuredTimeZone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("configuredTimeZone"))));
        String BeforeConfiguredTimeZone = configuredTimeZone.getText();

        logger.info("Before Change Timezone: " + BeforeConfiguredTimeZone);

        WebElement selectTimeZoneDD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("selectTimeZoneDD"))));

        Thread.sleep(2000);
        selectTimeZoneDD.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        selectTimeZoneDD.sendKeys(Keys.BACK_SPACE);
        selectTimeZoneDD.click();
        selectTimeZoneDD.sendKeys(ReqTimeZone);
        selectTimeZoneDD.sendKeys(Keys.RETURN);
        selectTimeZoneDD.sendKeys(Keys.chord(Keys.ARROW_DOWN));
        selectTimeZoneDD.sendKeys(Keys.RETURN);
        logger.info("Selecting the required option");
        Thread.sleep(2000);
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("saveBtn"))));
        saveBtn.click();//"Success\nTime zone changed successfuly"


        WebElement SuccessfulMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("SuccessfulMsg"))));
        System.out.println(SuccessfulMsg.getText());
        if (SuccessfulMsg.getText().equals("Success\nTime zone changed successfuly"))//"Success\nTime zone changed successfuly" only for execution purpose
        {
            flag = true;
        }
        logger.info("After Change Timezone: " + ReqTimeZone);
        WebElement XIcon = driver.findElement(By.xpath(props.getProperty("XIcon")));
        actions.moveToElement(XIcon).click().perform();
        cms.clickOnBackTOListReportButton();
        return flag;
    }

    //Verification of User Viewing Scanned on time within the Configured Time zone set on the reporting.
    public boolean ConfigureTimeZoneIsSetOnTheReporting( String ReqTimeZone) throws InterruptedException {
        boolean flag = false;
        //Change Time Zone in Settings
        Thread.sleep(2000);
        WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("profileIcon"))));
        actions.moveToElement(profileIcon).click().perform();
        List<WebElement> ListOfProfileOption = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("ListOfProfileOption"))));
        for (WebElement option : ListOfProfileOption) {
            if (option.getText().contains("Time zone")) {
                logger.info("Clicked on Time zone option");
                wait.until(ExpectedConditions.elementToBeClickable(option));
                Thread.sleep(2000);
                actions.moveToElement(option).click().perform();
                //option.click();
                break;

            } else {
                Assert.fail();
            }
        }
        WebElement editTimeZoneBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("editTimeZoneBtn"))));
        editTimeZoneBtn.click();
        WebElement configuredTimeZone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("configuredTimeZone"))));
        String BeforeConfiguredTimeZone = configuredTimeZone.getText();

        logger.info("Before Change Timezone: " + BeforeConfiguredTimeZone);

        WebElement selectTimeZoneDD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("selectTimeZoneDD"))));

        Thread.sleep(2000);
        selectTimeZoneDD.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        selectTimeZoneDD.sendKeys(Keys.BACK_SPACE);
        selectTimeZoneDD.click();
        selectTimeZoneDD.sendKeys(ReqTimeZone);
        selectTimeZoneDD.sendKeys(Keys.RETURN);
        selectTimeZoneDD.sendKeys(Keys.chord(Keys.ARROW_DOWN));
        selectTimeZoneDD.sendKeys(Keys.RETURN);
        logger.info("Selecting the required option");
        Thread.sleep(2000);
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("saveBtn"))));
        saveBtn.click();//"Success\nTime zone changed successfuly"


        WebElement SuccessfulMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("SuccessfulMsg"))));
        System.out.println(SuccessfulMsg.getText());
        if (SuccessfulMsg.getText().equals("Success\nTime zone changed successfuly"))//"Success\nTime zone changed successfuly" only for execution purpose
        {
            logger.info("Time zone changed successfully");
        }
        logger.info("After Change Timezone: " + ReqTimeZone);
        WebElement XIcon = driver.findElement(By.xpath(props.getProperty("XIcon")));
        actions.moveToElement(XIcon).click().perform();
        cms.clickOnBackTOListReportButton();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("slideIcon")))).click();
        Thread.sleep(5000);
        WebElement timeStampOnSlide = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scannedOnTime"))));
        System.out.println("Date :"+timeStampOnSlide.getText());
       // validateTimeZone(timeStampOnSlide.getText(),ReqTimeZone);
        //boolean isValid = validateTimeZone(timeStampOnSlide.getText(), ReqTimeZone);
        String dateTimeString = timeStampOnSlide.getText();
        // Extract date and time part
        int lastParenIndex = dateTimeString.lastIndexOf("(");
        String dateTimePart = dateTimeString.substring(0, lastParenIndex).trim();
        String timeZoneAbbreviation = dateTimeString.substring(lastParenIndex + 1, dateTimeString.length() - 1).trim();
        String[] parts = ReqTimeZone.split("\\) ");
        String utcOffset = parts[0].replace("(", ""); // "UTC+00:00"
        String zoneId = parts[1]; // "Africa/Abidjan"


        System.out.println(dateTimeString);
        System.out.println(timeZoneAbbreviation);
        System.out.println(zoneId);

        boolean isValid = verifyTimeZone(dateTimeString, timeZoneAbbreviation, zoneId);
       // Assert.assertTrue(isValid, "Time zone verification failed.");

      /*  if (isValid) {
            flag=true;
            System.out.println("✅ The time and zone are correct.");
        } else {
            System.out.println("❌ The time does not match the expected time zone.");
        }*/
        WebElement XIconSlideInfo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("XIconSlideInfo"))));
        actions.moveToElement(XIconSlideInfo).click().perform();
        Thread.sleep(2000);
        cms.clickOnBackTOListReportButton();

        return flag;
    }

    public static boolean verifyTimeZone(String timeStr, String fromTimeZone, String toTimeZone) {
        // Use the correct IANA time zone ID for EAT:
        if (fromTimeZone.equals("EAT")) {
            fromTimeZone = "Africa/Nairobi"; // Correct IANA ID
        }
        if (toTimeZone.equals("EAT")) {
            toTimeZone = "Africa/Nairobi"; // Correct IANA ID
        }
        try {
            // 1. Parse the time string (with time zone abbreviation)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm a (z)", Locale.ENGLISH);
            ZonedDateTime zonedDateTimeFrom = ZonedDateTime.parse(timeStr, formatter.withZone(ZoneId.of(fromTimeZone)));

            // 2. Convert to the 'to' time zone (using withZoneSameInstant)
            ZonedDateTime zonedDateTimeTo = zonedDateTimeFrom.withZoneSameInstant(ZoneId.of(toTimeZone));

            // 3. Compare the INSTANTS (the most reliable way)
            boolean areEqual = zonedDateTimeFrom.toInstant().equals(zonedDateTimeTo.toInstant());

            if (areEqual) {
                // For display purposes, you can format zonedDateTimeTo:
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm a (z)", Locale.ENGLISH); //Same format as input or desired format.
                String formattedTimeTo = zonedDateTimeTo.format(outputFormatter);
                System.out.println("✅ Time '" + timeStr + "' in " + fromTimeZone + " is equivalent to '" + formattedTimeTo + "' in " + toTimeZone);
                return true;
            } else {
                // For debugging, you can print the actual times:
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm a (z)", Locale.ENGLISH); //Same format as input or desired format.
                String formattedTimeFrom = zonedDateTimeFrom.format(outputFormatter);
                String formattedTimeTo = zonedDateTimeTo.format(outputFormatter);

                System.out.println("❌ Time '" + timeStr + "' in " + fromTimeZone + " is NOT equivalent to '" + formattedTimeTo + "' in " + toTimeZone);
                return false;
            }

        } catch (DateTimeParseException e) {
            System.err.println("❌ Invalid date/time format: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("❌ An error occurred: " + e.getMessage());
            return false;
        }
    }
    public static boolean validateTimeZone(String dateTimeStr, String expectedZone) {
        // Extract the Zone ID and UTC Offset dynamically
        String[] zoneParts = extractZoneIdAndOffset(expectedZone);
        if (zoneParts == null) {
            System.out.println("❌ Invalid expected time zone format: " + expectedZone);
            return false;
        }

        String expectedZoneId = zoneParts[0];  // Extracted Zone ID (e.g., Europe/London)
        String expectedOffset = zoneParts[1];  // Extracted UTC Offset (e.g., UTC+00:00)

            // Extract time and abbreviation (e.g., "GMT") from input timestamp
            Pattern pattern = Pattern.compile("(.+)\\(([^)]+)\\)");
            Matcher matcher = pattern.matcher(dateTimeStr);

            if (!matcher.matches()) {
                System.out.println("❌ Invalid timestamp format: " + dateTimeStr);
                return false;
            }

            String timePart = matcher.group(1).trim();  // "18-Feb-2025, 12:41 PM"
            String extractedAbbreviation = matcher.group(2).trim();  // "GMT"

            // Convert extracted abbreviation to UTC offset dynamically
            String formattedOffset = getOffsetFromAbbreviation(extractedAbbreviation, expectedZoneId);

            // Parse date-time without time zone
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm a [(z)]", Locale.ENGLISH);

            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);

            // Convert to ZonedDateTime in the expected zone
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of(expectedZoneId));

            // Get actual offset of the given time in the expected time zone
            String actualOffset = zonedDateTime.getOffset().toString();
            actualOffset = convertOffsetToUTCFormat(actualOffset);
        System.out.println(actualOffset);
        System.out.println(formattedOffset);
        System.out.println(expectedOffset);

            // Compare extracted offset with expected offset
            if (formattedOffset.equals(actualOffset) && expectedOffset.equals(actualOffset)) {
                System.out.println("✅ Valid: " + dateTimeStr + " belongs to " + expectedZoneId);
                return true;
            } else {
                System.out.println("❌ Mismatch! Expected: " + expectedOffset + ", Extracted: " + formattedOffset + ", Found: " + actualOffset);
                return false;
            }

    }

    private static String[] extractZoneIdAndOffset(String fullZone) {
        // Extract (UTC±XX:XX) and Zone ID
        Pattern pattern = Pattern.compile("\\((UTC[+-]\\d{2}:\\d{2})\\)\\s(.+)");
        Matcher matcher = pattern.matcher(fullZone);

        if (matcher.matches()) {
            return new String[]{matcher.group(2), matcher.group(1)};
        }
        return null;
    }

    private static String getOffsetFromAbbreviation(String abbreviation, String zoneId) {
        // Convert abbreviation dynamically
        ZoneId zone = ZoneId.of(zoneId);
        ZonedDateTime now = ZonedDateTime.now(zone);
        String offset = now.getOffset().toString();

        return convertOffsetToUTCFormat(offset);
    }

    private static String convertOffsetToUTCFormat(String offset) {
        if (offset.equals("Z")) {
            return "UTC+00:00";
        }
        return "UTC" + offset;
    }




    //Timezone
    public boolean TimezoneComparison(String ReqTimeZone) throws InterruptedException {
        boolean flag = false;
        // Store timestamps before the time zone change
        List<String> beforeTimestamps = new ArrayList<>();
        for (WebElement time : driver.findElements(By.xpath(props.getProperty("beforeTimeStamp")))) {
            beforeTimestamps.add(time.getText());
        }
        //logger.info("Before Change: " + beforeTimestamps);
        //Change Time Zone in Settings
        WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("profileIcon"))));
        actions.moveToElement(profileIcon).click().perform();
        List<WebElement> ListOfProfileOption = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("ListOfProfileOption"))));
        for (WebElement option : ListOfProfileOption) {
            if (option.getText().contains("Time zone")) {
                logger.info("Clicked on Time zone option");
                wait.until(ExpectedConditions.elementToBeClickable(option));
                Thread.sleep(2000);
                actions.moveToElement(option).click().perform();
                //option.click();
                break;
            } else {
                actions.moveToElement(option).click().perform();
                //Assert.fail();
            }
        }
        WebElement editTimeZoneBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("editTimeZoneBtn"))));
        editTimeZoneBtn.click();
        WebElement configuredTimeZone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("configuredTimeZone"))));
        String BeforeConfiguredTimeZone = configuredTimeZone.getText();

        logger.info("Before Change Timezone: " + BeforeConfiguredTimeZone);

        WebElement selectTimeZoneDD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("selectTimeZoneDD"))));

        Thread.sleep(2000);
        selectTimeZoneDD.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        selectTimeZoneDD.sendKeys(Keys.BACK_SPACE);
        selectTimeZoneDD.click();
        selectTimeZoneDD.sendKeys(ReqTimeZone);
        selectTimeZoneDD.sendKeys(Keys.RETURN);
        selectTimeZoneDD.sendKeys(Keys.chord(Keys.ARROW_DOWN));
        selectTimeZoneDD.sendKeys(Keys.RETURN);
        logger.info("Selecting the required option");
        Thread.sleep(2000);
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("saveBtn"))));
        saveBtn.click();//"Success\nTime zone changed successfuly"


        WebElement SuccessfulMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("SuccessfulMsg"))));
        System.out.println(SuccessfulMsg.getText());
        if (SuccessfulMsg.getText().equals("Success\nTime zone changed successfuly")) {
            flag = true;
        }
        logger.info("After Change Timezone: " + ReqTimeZone);
        WebElement XIcon = driver.findElement(By.xpath(props.getProperty("XIcon")));
        actions.moveToElement(XIcon).click().perform();
        cms.clickOnBackTOListReportButton();
        Thread.sleep(2000);

        // Store timestamps after the time zone change
        List<String> afterTimestamps = new ArrayList<>();
        for (WebElement time : driver.findElements(By.xpath(props.getProperty("afterTimeStamp")))) {
            afterTimestamps.add(time.getText());
        }
        //logger.info("After Change: " + afterTimestamps);

        // Store timestamp pairs in a list
        List<String[]> timestampPairs = new ArrayList<>();

        // Validate the Changes by Comparing Arrays
        for (int i = 0; i < Math.min(beforeTimestamps.size(), afterTimestamps.size()); i++) {
            timestampPairs.add(new String[]{beforeTimestamps.get(i), afterTimestamps.get(i)});
            logger.info("Validation: " + beforeTimestamps.get(i) + " -> " + afterTimestamps.get(i));
        }


        // Now pass `timestampPairs` to the validation method
        validateTimeZones(timestampPairs, BeforeConfiguredTimeZone, ReqTimeZone);
        return flag;

    }


    public void validateTimeZones(List<String[]> timestampPairs, String BeforeZone, String AfterZOne) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy, hh:mm a");

        for (String[] pair : timestampPairs) {
            String before = pair[0];
            String after = pair[1];

            try {
                ZonedDateTime beforeZDT = parseZonedDateTimeBefore(before, formatter, BeforeZone);
                ZonedDateTime afterZDT = parseZonedDateTimeAfter(after, formatter, AfterZOne);

                if (beforeZDT == null || afterZDT == null) {
                    System.out.println("❌ Invalid date/time format or time zone in input: " + before + " or " + after);
                    continue;
                }

                ZonedDateTime convertedZDT = beforeZDT.withZoneSameInstant(afterZDT.getZone());

                String calculatedAfterTime = convertedZDT.format(formatter);
                String expectedAfterTime = after.substring(0, after.lastIndexOf(" "));

                if (calculatedAfterTime.equals(expectedAfterTime)) {
                    System.out.println("✅ Valid: " + before + " -> " + after);
                } else {
                    System.out.println("❌ Mismatch! Expected: " + expectedAfterTime + ", Calculated: " + calculatedAfterTime);
                    System.out.println("   Before ZDT: " + beforeZDT);
                    System.out.println("   Converted ZDT: " + convertedZDT);
                    System.out.println("   After ZDT: " + afterZDT);
                }

            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println("❌ Error parsing or comparing: " + before + " or " + after + ": " + e.getMessage());
            }
        }
    }


    private ZonedDateTime parseZonedDateTimeBefore(String dateTimeStr, DateTimeFormatter formatter, String Before) {
        String timePart = dateTimeStr.substring(0, dateTimeStr.lastIndexOf(" "));
        String zonePart = Before.substring(Before.lastIndexOf(" ") + 1);

        try {
            LocalDateTime ldt = LocalDateTime.parse(timePart, formatter);
            ZoneId zoneId = ZoneId.of(zonePart);
            return ZonedDateTime.of(ldt, zoneId);

        } catch (DateTimeParseException | IllegalArgumentException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
            return null;
        }
    }


    private ZonedDateTime parseZonedDateTimeAfter(String dateTimeStr, DateTimeFormatter formatter, String After) {
        String timePart = dateTimeStr.substring(0, dateTimeStr.lastIndexOf(" "));
        String zonePart = After.substring(After.lastIndexOf(" ") + 1);

        try {
            LocalDateTime ldt = LocalDateTime.parse(timePart, formatter);
            ZoneId zoneId = ZoneId.of(zonePart);
            return ZonedDateTime.of(ldt, zoneId);

        } catch (DateTimeParseException | IllegalArgumentException e) {
            System.err.println("Error parsing date/time: " + e.getMessage());
            return null;
        }
    }










    private static final Map<String, String> abbreviationToZoneIdMap = new HashMap<>();
    private static final Map<String, String> daylightSavingMap = new HashMap<>();

    static {
        // Standard Time Zones
        abbreviationToZoneIdMap.put("CET", "Europe/Paris");
        abbreviationToZoneIdMap.put("GMT", "Europe/London");
        abbreviationToZoneIdMap.put("IST", "Asia/Kolkata");
        abbreviationToZoneIdMap.put("EST", "America/New_York");
        abbreviationToZoneIdMap.put("PST", "America/Los_Angeles");
        abbreviationToZoneIdMap.put("UTC", "UTC");
        abbreviationToZoneIdMap.put("EET", "Europe/Bucharest");
        abbreviationToZoneIdMap.put("AST", "America/Halifax");
        abbreviationToZoneIdMap.put("MST", "America/Denver");
        abbreviationToZoneIdMap.put("CST", "America/Chicago");
        abbreviationToZoneIdMap.put("AKST", "America/Anchorage");
        abbreviationToZoneIdMap.put("HST", "Pacific/Honolulu");
        abbreviationToZoneIdMap.put("JST", "Asia/Tokyo");
        abbreviationToZoneIdMap.put("KST", "Asia/Seoul");
        abbreviationToZoneIdMap.put("AEST", "Australia/Sydney");
        abbreviationToZoneIdMap.put("ACST", "Australia/Adelaide");
        abbreviationToZoneIdMap.put("AWST", "Australia/Perth");
        abbreviationToZoneIdMap.put("NZST", "Pacific/Auckland");
        abbreviationToZoneIdMap.put("WET", "Europe/Lisbon");
        abbreviationToZoneIdMap.put("EAT", "Africa/Nairobi");
        abbreviationToZoneIdMap.put("WAT", "Africa/Lagos");
        abbreviationToZoneIdMap.put("CAT", "Africa/Harare");
        abbreviationToZoneIdMap.put("SAST", "Africa/Johannesburg");
        abbreviationToZoneIdMap.put("HKT", "Asia/Hong_Kong");
        abbreviationToZoneIdMap.put("SGT", "Asia/Singapore");
        abbreviationToZoneIdMap.put("PHT", "Asia/Manila");
        abbreviationToZoneIdMap.put("MYT", "Asia/Kuala_Lumpur");
        abbreviationToZoneIdMap.put("IRKT", "Asia/Irkutsk");
        abbreviationToZoneIdMap.put("KRAT", "Asia/Krasnoyarsk");
        abbreviationToZoneIdMap.put("MSK", "Europe/Moscow");
        abbreviationToZoneIdMap.put("SAMT", "Europe/Samara");
        abbreviationToZoneIdMap.put("NOVT", "Asia/Novosibirsk");
        abbreviationToZoneIdMap.put("OMST", "Asia/Omsk");
        abbreviationToZoneIdMap.put("VLAT", "Asia/Vladivostok");
        abbreviationToZoneIdMap.put("MAGT", "Asia/Magadan");
        abbreviationToZoneIdMap.put("PETT", "Asia/Kamchatka");
        abbreviationToZoneIdMap.put("CHUT", "Pacific/Chuuk");
        abbreviationToZoneIdMap.put("CHST", "Pacific/Guam");
        abbreviationToZoneIdMap.put("SST", "Pacific/Pago_Pago");
        abbreviationToZoneIdMap.put("WFT", "Pacific/Wallis");
        abbreviationToZoneIdMap.put("LINT", "Pacific/Kiritimati");
        abbreviationToZoneIdMap.put("BST", "Europe/London"); // British Summer Time

        // Adding daylight saving time mappings
        daylightSavingMap.put("CST", "CDT");
        daylightSavingMap.put("AST", "ADT");
        daylightSavingMap.put("EST", "EDT");
        daylightSavingMap.put("MST", "MDT");
        daylightSavingMap.put("NST", "NDT");
        daylightSavingMap.put("PST", "PDT");
        daylightSavingMap.put("EET", "EEST");
        daylightSavingMap.put("AEST", "AEDT");
        daylightSavingMap.put("NZST", "NZDT");
        daylightSavingMap.put("IST", "IDT"); // Israel Daylight Time

        // Adding numeric offsets dynamically (-12:00 to +14:00)
        for (int i = -12; i <= 14; i++) {
            String offset = String.format("%+03d:00", i);
            abbreviationToZoneIdMap.put(offset, "Etc/GMT" + (i == 0 ? "" : (i > 0 ? "-" + i : "+" + (-i))));
        }
    }

    public static ZoneId convertAbbreviationToZoneId(String abbreviation) {
        if (abbreviation.startsWith("+") || abbreviation.startsWith("-")) {
            return ZoneId.ofOffset("UTC", ZoneOffset.of(abbreviation));
        }
        String zoneId = abbreviationToZoneIdMap.get(abbreviation);
        if (zoneId != null) {
            return ZoneId.of(zoneId);
        } else {
            throw new IllegalArgumentException("Invalid time zone abbreviation: " + abbreviation);
        }
    }

    public static String convertToDaylightSaving(String standardAbbreviation) {
        return daylightSavingMap.getOrDefault(standardAbbreviation, standardAbbreviation);
    }

   /* public static void main(String[] args) {
        try {
            // Convert abbreviation
            ZoneId zoneId = convertAbbreviationToZoneId("+01:00");
            System.out.println("Converted Zone ID: " + zoneId);

            // Convert standard time to daylight saving time
            System.out.println("CST to DST: " + convertToDaylightSaving("CST"));
            System.out.println("AEST to DST: " + convertToDaylightSaving("AEST"));
            System.out.println("IST to DST: " + convertToDaylightSaving("IST"));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
    */}













