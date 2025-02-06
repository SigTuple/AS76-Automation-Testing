package ReportListing;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
        wait = new WebDriverWait(driver, time);
        props = Property.prop;
        Property.readRBCProperties();
        Property.readReportListingProperties();
        cms = new CommonMethods(driver);
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
            for (WebElement ops :filteroption) {
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
                    js.executeScript("window.scrollBy(0, 500);");
                    wait.until(ExpectedConditions.jsReturnsValue("return document.body.scrollHeight > " + lastHeight)); // Wait for new content to load
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
        this.clickOnUnassignedReport();
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



}






