package ReportListing;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    //verify the functionality of apply button after selecting any option
    public boolean applybutton() throws InterruptedException {
        boolean flag = false;
        Thread.sleep(2000);
        WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
        actions = new Actions(driver);
        actions.moveToElement(filterbtn).click().perform();
        Thread.sleep(2000);
        WebElement scandate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandate"))));
        WebElement scndaterange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandaterange"))));
        scndaterange.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("startdatebtn")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("enddatebtn")))).click();
        Thread.sleep(2000);
        String[] datew = scndaterange.getText().split(" - ");
        String[] parts = datew[0].split("-");
        // Ensure the array has three parts (MM, dd, yyyy)
        String idealdate = null;
        if (parts.length == 3) {
            String month = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
            String day = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
            String year = parts[2];  // Year doesn't need formatting
            idealdate = month + "/" + day + "/" + year;
        }
        // scandate.click();
        WebElement applybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybtn"))));
        // Use JavaScript to click
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", applybtn);
        //actions.moveToElement(resetbtn).click().perform();
        Thread.sleep(5000);
        List<WebElement> dateinlist = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[contains(@class,'dateComponent_day')]")));
      for (WebElement dateinl:dateinlist) {

          if (idealdate.contains(dateinl.getText())) {
              flag = true;
              logger.info("Apply Button  is enable");
          }
      }
        return flag;

    }

    //verify the functionality of clear filter report
    public boolean clearfilteroption() throws InterruptedException {
        boolean flag = false;
        WebElement clearfilter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("clearfilter"))));
        if (!clearfilter.isEnabled()) {
            WebElement filterbtn = driver.findElement(By.xpath(props.getProperty("filterbtn")));
            actions = new Actions(driver);
            actions.moveToElement(filterbtn).click().perform();
            Thread.sleep(2000);
            WebElement scandate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandate"))));
            WebElement scndaterange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scandaterange"))));
            scndaterange.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("startdatebtn")))).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("enddatebtn")))).click();
            Thread.sleep(2000);
            String[] datew = scndaterange.getText().split(" - ");
            String[] parts = datew[0].split("-");
            // Ensure the array has three parts (MM, dd, yyyy)
            String idealdate = null;
            if (parts.length == 3) {
                String month = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
                String day = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
                String year = parts[2];  // Year doesn't need formatting
                idealdate = month + "/" + day + "/" + year;
            }
            WebElement applybtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("applybtn"))));
            // Use JavaScript to click
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", applybtn);
            //actions.moveToElement(resetbtn).click().perform();
            Thread.sleep(5000);
            WebElement dateinlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'dateComponent_day')])[1]")));
            System.out.println(dateinlist.getText());
            System.out.println(idealdate);
        }
        if (clearfilter.isEnabled()) {
            clearfilter.click();
            flag = true;
            logger.info("Clear Filter is enable");

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
            WebElement assignedTo = row.findElement(By.xpath(".//td//input[@value='rupa ']")); // Adjust XPath to match your table structure
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
        Thread.sleep(5000);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("filterbtn")))).click();
        //WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("fildercontainer"))));
        WebElement filteroption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("filteroption"))));
        //  driver.findElement(By.xpath(props.getProperty("xicon"))).click();
        Thread.sleep(5000);
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
    public boolean bookmarkSavedtatus() throws InterruptedException {
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
    // click on report assigned to manju
    public boolean clickOnAssignedReport(){
        boolean flag=false;
        WebElement clickOnSelectedReportr = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='assigned_to' and @value=''])[1]//ancestor::tr")));
        if (clickOnSelectedReportr.isDisplayed()){
            clickOnSelectedReportr.click();
            flag=true;
        }
        return flag;
    }


    //verify the assign and reassign of reviewer on list report page
    public String assignreport() throws InterruptedException {
        Thread.sleep(6000);
        this.clickOnAssignedReport();
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





