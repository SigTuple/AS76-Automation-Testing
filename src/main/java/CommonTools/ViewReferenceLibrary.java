package CommonTools;

import Data.Property;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Properties;

public class ViewReferenceLibrary {
    private final Logger logger = LogManager.getLogger(ViewReferenceLibrary.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;

    public ViewReferenceLibrary(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readCommonMethodProperties();
    }
    public boolean verifyReferences(String referencesiconXpath, String tabName) throws InterruptedException {
        // Verify if the References tab exists
        boolean flag=true;
        WebElement referenceElement = driver.findElement(By.xpath(referencesiconXpath));
        if (referenceElement.isEnabled()) {
            Thread.sleep(3000);
            //WebElement referenceElement = driver.findElement(By.xpath(referencesiconXpath));
            actions=new Actions(driver);
            actions.moveToElement(referenceElement).click().perform();
          // referenceElement.click();
            Thread.sleep(3000);
            // Close the References dialog/overlay
           WebElement closeButton = driver.findElement(By.xpath(props.getProperty("referencesCloseButton")));
           actions.moveToElement(closeButton).click().perform();
           flag=true;
            //wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
            logger.info("References tab closed successfully in " + tabName);
        } else {
            logger.info("References tab is NOT present in"+ tabName);
        }
        return flag;
    }

    public boolean verifyTheCBCReport(String CBCXpath, String tabName) throws InterruptedException {
        // Verify if the CBC report tab exists
        boolean flag=true;
        // boolean isReferencesPresent = !driver.findElements(By.xpath(referencesiconXpath)).isEmpty();
        WebElement referenceElement = driver.findElement(By.xpath(CBCXpath));
        if (referenceElement.isEnabled()) {
            Thread.sleep(3000);
            //WebElement referenceElement = driver.findElement(By.xpath(referencesiconXpath));
            actions=new Actions(driver);
            actions.moveToElement(referenceElement).click().perform();
            // referenceElement.click();
            Thread.sleep(3000);
            // Close the References dialog/overlay
            WebElement closeButton = driver.findElement(By.xpath(props.getProperty("cbcCloseButton")));
            actions.moveToElement(closeButton).click().perform();
            flag=true;
            //wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
            logger.info("CBC Report tab closed successfully in " + tabName);
        } else {
            logger.info("CBC Report tab is NOT present in"+ tabName);
        }
        return flag;
    }

    public String verifyReferencesDefault(String referencesiconXpath, String tabName) throws InterruptedException {
        // Verify if the References tab exists
        String refrencesFirstoption="";
        WebElement referenceElement = driver.findElement(By.xpath(referencesiconXpath));
        System.out.println(referenceElement.getText());
        referenceElement.click();
        if (referenceElement.isEnabled()) {
            //referenceElement.click();
            Thread.sleep(3000);
            //WebElement referenceElement = driver.findElement(By.xpath(referencesiconXpath));
            actions=new Actions(driver);
            actions.moveToElement(referenceElement).click().perform();
            Thread.sleep(3000);
            WebElement dropDownButton = driver.findElement(By.xpath(props.getProperty("cellsdropdown")));
            dropDownButton.click();
            try {
                WebElement Firstoption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("refrencesFirstoption"))));
                refrencesFirstoption = Firstoption.getText();
                Firstoption.click();
            } catch (Exception e) {
                actions.sendKeys(Keys.ESCAPE).perform();
                logger.info("Options are not Available");
            }

            Thread.sleep(3000);
            WebElement closeButton = driver.findElement(By.xpath(props.getProperty("referencesCloseButton")));
            closeButton.click();
            //actions.moveToElement(closeButton).click().perform();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(props.getProperty("referencesCloseButton"))));


            logger.info("References tab closed successfully in " + tabName);
        } else {
            logger.info("References tab is NOT present in"+ tabName);
        }
        return refrencesFirstoption;
    }





    // Helper method to verify the Disclaimer message
    public boolean verifyDisclaimer(String disclaimerXpath, String tabName) {
        boolean flag=false;
        try {
            boolean isDisclaimerPresent = !driver.findElements(By.xpath(disclaimerXpath)).isEmpty();

            if (isDisclaimerPresent) {
                WebElement disclaimer = driver.findElement(By.xpath(disclaimerXpath));
                String disclaimerText = disclaimer.getText();
                flag=true;
                Assert.assertNotNull(disclaimerText, "Disclaimer text is null in " + tabName);
                logger.info("Disclaimer is present in " + tabName + ": " + disclaimerText);
            } else {
                logger.warn("Disclaimer is NOT present in " + tabName);
            }
        } catch (Exception e) {
            logger.error("Error while verifying Disclaimer in " + tabName, e);
        }
        return flag;
    }


}
