package CommonTools;

import Data.Property;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class ViewOriginalModifiedreports {
    private final Logger logger = LogManager.getLogger(ViewOriginalModifiedreports.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;

    public ViewOriginalModifiedreports(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readCommonMethodProperties();
        Property.readSummaryProperties();
        Property.readCommonToolsProperties();
    }

    //Verification of the presence of View original/modified reports options in the kebab menu icon.
    public boolean originalReport(){
        boolean flag=false;
        WebElement kebabmenuicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("kebabmenuicon"))));
        kebabmenuicon.click();
        List<WebElement> listOfKebabMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("listOfKebabMenu"))));
        for (WebElement element:listOfKebabMenu){
            if (element.getText().equals("Switch to original report")){
                flag=true;
                element.click();
                logger.info("Switch to original report is displayed in kebab menu ");
                break;
            }
        }
        return flag;
    }

    public boolean modifiedReport() throws InterruptedException {
        boolean flag=false;
        Thread.sleep(4000);
        WebElement kebabmenuicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("kebabmenuicon"))));
        kebabmenuicon.click();
        List<WebElement> listOfKebabMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("listOfKebabMenu"))));
        for (WebElement element:listOfKebabMenu){
            if (element.getText().equals("Switch to modified report")){
                flag=true;
                element.click();
                logger.info("Switch to original report is displayed in kebab menu ");
                break;
            }
        }
        return flag;
    }

    //Verification of switching between modified reports to original report
    public boolean switchToOriginalReport() throws InterruptedException {
        boolean flag=false;
        Thread.sleep(4000);
        WebElement kebabmenuicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("kebabmenuicon"))));
        kebabmenuicon.click();
        List<WebElement> listOfKebabMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("listOfKebabMenu"))));
        for (WebElement element:listOfKebabMenu){
            if (element.getText().equals("Switch to original report")){
                element.click();
                break;
            }
        }
        Thread.sleep(4000);
        WebElement manualTxtField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='wbc-correction-input-neutrophil']")));
        String status= manualTxtField.getAttribute("class");
        if (status.contains("Mui-disabled")){
            flag=true;
            logger.info("Original report is present");
        }
        else {
            System.out.println("Original report is not present");
        }
        return flag;

    }
    //Verification of switching between modified reports to original report
    public boolean switchToModifiedReport() throws InterruptedException {
        boolean flag=false;
        Thread.sleep(4000);
        WebElement kebabmenuicon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("kebabmenuicon"))));
        kebabmenuicon.click();
        List<WebElement> listOfKebabMenu = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("listOfKebabMenu"))));
        for (WebElement element:listOfKebabMenu){
            if (element.getText().equals("Switch to modified report")){
                element.click();
                break;
            }
        }
        Thread.sleep(6000);
        WebElement manualTxtField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='wbc-correction-input-neutrophil']")));
        String status= manualTxtField.getAttribute("class");
        if (!status.contains("Mui-disabled")){
            logger.info("Modified report is not present");
        }
        else {
            flag=true;
            logger.info("modified  report is present");

        }
        return flag;

    }

}
