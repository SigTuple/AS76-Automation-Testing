package summary;

import Data.Property;
import ReportListing.VerifyTheListReportPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class Summary {
    private final Logger logger = LogManager.getLogger(Summary.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;

    public Summary(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
    }

    //verify the presence of the tool tip in Summary tab.
    public String wbcInfoIcon() throws InterruptedException {
        Thread.sleep(6000);
        String wbcinfo = "";
        WebElement wbcinfoicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("wbcinfoicon"))));
        actions = new Actions(driver);
        actions.moveToElement(wbcinfoicon).perform();
        WebElement wbctoolmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tooltip']")));
        wbcinfo = wbctoolmsg.getText();
        System.out.println(wbcinfo);
        return wbcinfo;

    }

    public String rbcInfoIcon() throws InterruptedException {
        Thread.sleep(6000);
        String rbcinfo = "";
        WebElement rbcinfoicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("rbcinfoicon"))));
        actions = new Actions(driver);
        actions.moveToElement(rbcinfoicon).perform();
        WebElement rbctoolmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tooltip']")));
        rbcinfo = rbctoolmsg.getText();
        System.out.println(rbcinfo);
        return rbcinfo;

    }

    public String platelesInfoIcon() throws InterruptedException {
        Thread.sleep(6000);
        String plateletinfo = "";
        WebElement plateletinfoicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("plateletinfoicon"))));
        actions = new Actions(driver);
        actions.moveToElement(plateletinfoicon).perform();
        WebElement platelettoolmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='tooltip']")));
        plateletinfo = platelettoolmsg.getText();
        System.out.println(plateletinfo);
        return plateletinfo;

    }


    public boolean toolTipverifier(String iconxpath , String tooltipxpath , String exptMsg) throws InterruptedException {
        boolean flag = false;
        WebElement   iconx = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(iconxpath)));
            actions = new Actions(driver);
            actions.moveToElement(iconx).perform();
            WebElement tooltipMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tooltipxpath)));
            String actualMessage = tooltipMessage.getText();
            System.out.println(actualMessage);
            if (exptMsg.equals(actualMessage)) {
                flag = true;
                logger.info("Tool tip is verified"+actualMessage);

            }
        actions.sendKeys(Keys.ESCAPE).perform();
        Thread.sleep(3000);
        return flag;
    }

    //Verification of the adding and updating of the PS impressions in the add details field.
    public boolean psImpressions() throws InterruptedException {
        boolean flag = false;
        List<WebElement> psimpressiontxtfields = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("psimpressiontxtfield"))));
        if (!psimpressiontxtfields.isEmpty()){
            for (WebElement field:psimpressiontxtfields) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", field);
                wait.until(ExpectedConditions.elementToBeClickable(field));
                Actions actions = new Actions(driver);
                actions.moveToElement(field).click().sendKeys("drtfyguhijo").perform();
            }
            flag=true;

        }
        return flag;
        }

        //Verify the ps impression Heading
    public String psImpressionHeading(){
        String actualPsImpressionHeading="";
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("psimpressionHeader"))));
        if (header.isDisplayed()){
            actualPsImpressionHeading=header.getText();
        }
        return actualPsImpressionHeading;
    }
        // verify the pbs impression sub headings
    public String pbsimpressionsubheader(){
        String actualdsubheader = "";
        List<WebElement> pbsimpressionsubheader = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("pbsimpressionsubheader"))));
        if (!pbsimpressionsubheader.isEmpty()){
            for (int i=pbsimpressionsubheader.size()-1 ; i>=0; i--){
                actualdsubheader=pbsimpressionsubheader.get(i).getText()+","+actualdsubheader;
            }

        }
        return actualdsubheader;
    }



        //Verify the selection of the manual radio button for assigned report
    public boolean manualRadioBtn() throws InterruptedException {
        boolean flag = false;
        WebElement manualradiobtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("manualradiobtn"))));
        manualradiobtn.click();
        WebElement neutrophilmanualtxtfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='wbc-correction-input-neutrophil']")));
        neutrophilmanualtxtfield.sendKeys(Keys.BACK_SPACE);
        neutrophilmanualtxtfield.sendKeys(Keys.BACK_SPACE);
        neutrophilmanualtxtfield.sendKeys(Keys.BACK_SPACE);
        neutrophilmanualtxtfield.sendKeys(Keys.BACK_SPACE);
        neutrophilmanualtxtfield.sendKeys("45");
        if(neutrophilmanualtxtfield.isEnabled() && !manualradiobtn.isSelected()){
            flag=true;
            logger.info("Manual Radio button is visible");
        }
        return flag;
    }
    //Verify the appearance of Switch to calculated value popup when user select calculated radio button from the manual radio button
    public boolean calculatedRadioBtn(){
        boolean flag =false;
        WebElement calculatedradiobtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("calculatedradiobtn"))));
        calculatedradiobtn.click();
        WebElement switchmsgforcalculated = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("switchmsgforcalculated"))));
        String switchmsg = switchmsgforcalculated.getText();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("yesbtn")))).click();
        if (switchmsg.equals("All values entered in the manual column will be lost")){
            flag=true;
            logger.info(" Calculated Radio button is enable");
        }
        return flag;

    }
    //Verify the presence of the calculated values in the manual value fields
    public boolean comparecalculatesandmanualValues(){
        boolean flag=false;
        List<WebElement> calculatedwbcvalues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("calculatedwbcvalues"))));
        WebElement manualradiobtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("manualradiobtn"))));
        manualradiobtn.click();
        try{
        List<WebElement> manualwbcvalues = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("manualwbcvalues"))));
        for (int i = 0; i < manualwbcvalues.size(); i++) {
            String manualValue = manualwbcvalues.get(i).getAttribute("value").trim().replace("-", "0"); // Get value of manual field
            String calculatedValue = calculatedwbcvalues.get(i).getText().trim().replace("-", "0"); // Get value of calculated field

            // Parse to double if numeric comparison is required
            double manualNum = Double.parseDouble(manualValue);
            double calculatedNum = Double.parseDouble(calculatedValue);

            if (manualNum == calculatedNum) {
                logger.info("Value Match at Index " + i + ": Manual = " + manualNum + ", Calculated = " + calculatedNum);
                flag = true;
            } else {
                logger.info("Value Mismatch at Index " + i + ": Manual = " + manualNum + ", Calculated = " + calculatedNum);
            }
        }} catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return flag;

    }
    //Verify the presence of the differential count note message on
    public String differentialcountmsg(){
        String actualmsg="";
        WebElement manualradiobtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("manualradiobtn"))));
        manualradiobtn.click();
        WebElement warnngtotal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("warnngtotal"))));
        if (!warnngtotal.getText().equals("100.0")) {
            WebElement diffrencialcountmsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("diffrencialcountmsg"))));
            if (diffrencialcountmsg.isDisplayed()) {
                actualmsg = diffrencialcountmsg.getText();
                logger.info("The differential count note message is visible");
            }
        }
        else {
            logger.info("The differential count note message is Not Visible if Total is equal to 100.0");
        }

        return actualmsg;

        }

    }

