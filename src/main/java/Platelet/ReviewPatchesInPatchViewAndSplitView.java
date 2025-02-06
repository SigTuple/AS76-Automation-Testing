package Platelet;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import RBC.VerifySubTabOfRBC.VerifyUiOfRbcSizeSubTab;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ReviewPatchesInPatchViewAndSplitView extends CommonMethods {


    private final Logger logger = LogManager.getLogger(ReviewPatchesInPatchViewAndSplitView.class);

    public WebDriver driver;
    public WebDriverWait wait;
    Properties props;
    public CommonMethods cms;

    public ReviewPatchesInPatchViewAndSplitView(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, time);
        props = Property.prop;
        Property.readRBCProperties();
        cms = new CommonMethods(driver);
        VerifyUiOfRbcSizeSubTab rbcSizeSubTab = new VerifyUiOfRbcSizeSubTab(driver);
    }
    //_______________________________split view for rbc tab___________________//


    // verify the presence of sub tabs in the platelet tab


    public String presenceOfCountTab(String tabPath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tabPath))).click();
        String countTab = driver.findElement(By.xpath(tabPath)).getText();
        if (countTab.equals("Count")) {
            logger.info("count sub tab is present on platelet ");
        }

        return countTab;
    }

    public boolean selectionOfAnyFov() throws InterruptedException {
        boolean flag = true; // Start as true; will set to false if any verification fails.

        // Click on the count tab to ensure the FOVs are loaded
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("countTab")))).click();
        Thread.sleep(3000);
        // Get the list of all FOVs
        List<WebElement> allFov = driver.findElements(By.xpath(props.getProperty("fovs")));
        int totalFovs = allFov.size();
        System.out.println(totalFovs);

        // Loop through all FOVs
        for (int i = 1; i <=totalFovs; i++) {
            // Click on the current FOV
            allFov.get(i-1).click();
            Thread.sleep(3000);

            // Get the FOV navigation message text
            String actualText = driver.findElement(By.xpath(props.getProperty("fovsNavigationMsg"))).getText();
            System.out.println(actualText);

            // Construct the expected text for verification
            String expectedText = "FOV " + (i) + " / " + totalFovs;

            // Verify the actual text matches the expected text
            if (actualText.equals(expectedText)) {
                logger.info("FOV navigation is correct for FOV " + (i) + ": " + actualText);
            } else {
                logger.error("Mismatch in FOV navigation for FOV " + (i) + ". Expected: " + expectedText + ", but found: " + actualText);
                flag = false; // Set to false if verification fails
                break; // Exit loop early if a mismatch is found
            }
        }

        return flag;
    }


    //Verify the user able to change the N,M,G values
    public Object editTheNMGValue() throws InterruptedException {
        // Fetch all FOV elements
        List<WebElement> allFov = driver.findElements(By.xpath(props.getProperty("fovs")));

        for (int i = 0; i < allFov.size(); i++) {
            // Click on the current FOV
            allFov.get(i).click();
            Thread.sleep(3000);

            // Fetch NMG values for the current FOV
            List<WebElement> nmgValues = driver.findElements(By.xpath(props.getProperty("NMGValue")));
            for (int j = 0; j < nmgValues.size(); j++) {
                // Get the original NMG value
                String value = nmgValues.get(j).getAttribute("value");
                int originalValue = 0;
                try {
                    originalValue = Integer.parseInt(value);
                } catch (NumberFormatException ignored) {
                }
                System.out.println("Original NMG Value: " + originalValue);

                // Generate a random integer value between 1 and 100
                Random random = new Random();
                int randomValue = random.nextInt(100);

                // Update the NMG value
                nmgValues.get(j).click();
                nmgValues.get(j).clear();
                nmgValues.get(j).sendKeys(String.valueOf(randomValue));
                System.out.println("Updated NMG Value: " + randomValue);
            }
        }

        return null;
    }

    //Verify the default selection of split view in the platelet count
    public boolean defaultSelctionOfSplitViewInPlatelet() {
        boolean flag = false;
        try {
            // Locate the Split View element
            WebElement splitViewElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@id='split-view']")));

            // Verify Split View is selected by default
            String splitViewClass = splitViewElement.getAttribute("style");
            if (splitViewClass.contains("cursor: pointer;")) {
                logger.info("Split View is selected by default.");
                flag = true;
            } else {
                logger.info("Split View is NOT selected by default.");
            }

            // Locate the Patch View element
            WebElement patchViewElement = driver.findElement(By.xpath("//img[@id='patch-view']"));

            // Verify Patch View is disabled
            String patchViewStyle = patchViewElement.getAttribute("style");
            if (patchViewStyle.contains("cursor: not-allowed")) {
                logger.info("Patch View is disabled.");
                flag = true;
            } else {
                logger.info("Patch View is NOT disabled.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    //Verify the selection of the manual platelet level radio buttons
    public boolean manualLevelPlateletCount() {
        boolean flag = false;
        try {
        // Locate and select the "Manual Level" radio button
        WebElement manualLevelRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("manualRadioButtonInPlatelet"))));

        // Click on the "Manual Level" radio button if not already selected
        if (!manualLevelRadioButton.isSelected()) {
            flag=true;
            manualLevelRadioButton.click();
        }

        // Verify if the "Platelet Count Level" field is enabled
        WebElement plateletCountField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("plateletCountLevel")))); // Replace with the actual field's locator));

        boolean isFieldEnabled = plateletCountField.isEnabled();
        if (isFieldEnabled) {
            flag=true;
            logger.info("Platelet Count Level field is enabled after selecting Manual Level.");
        } else {
            logger.info("Platelet Count Level field is NOT enabled after selecting Manual Level.");
        }

        } catch (Exception e) {
            logger.info(" Manual radio button is not selected");
        }
        return flag;
    }
//Verify the selection of any one platelet count level from the manual platelet level dropdown
    public boolean manualLevelPlateletCountSelectLevel(String plateletLevel) {
        boolean flag = false;
        try {
            // Locate the "Manual Level" radio button
            WebElement manualLevelRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("manualRadioButtonInPlatelet"))));

            // Click on the "Manual Level" radio button if not already selected
            if (!manualLevelRadioButton.isSelected()) {
                manualLevelRadioButton.click();
                logger.info("Manual radio button is now selected.");
            } else {
                logger.info("Manual radio button is already selected.");
            }

            // Verify if the "Platelet Count Level" dropdown is enabled
            WebElement plateletCountField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("plateletCountLevel"))));
            if (plateletCountField.isEnabled()) {
                logger.info("Platelet Count Level field is enabled after selecting Manual Level.");
                plateletCountField.click();

                // Get all available options in the dropdown
                List<WebElement> plateletCountLevelOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("plateletCountLevelOption"))));
                for (WebElement option : plateletCountLevelOptions) {
                    if (option.getText().equalsIgnoreCase(plateletLevel)) {
                        option.click();
                        logger.info("Platelet Count Level '" + plateletLevel + "' has been selected.");
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    logger.info("The specified Platelet Count Level '" + plateletLevel + "' was not found.");
                }
            } else {
                logger.info("Platelet Count Level field is NOT enabled after selecting Manual Level.");
            }

        } catch (Exception e) {
            logger.error("An error occurred while selecting the Manual Platelet Count Level: " + e.getMessage());
        }

        return flag;
    }


    // Verification of the message present below the FOV list in the reports
    public boolean verifyMessageBelowFov() {
        boolean flag = false;

        try {
            // Wait for the element to be visible and retrieve the message
            String infoMsgBelowFov = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(props.getProperty("infoMsgBelowFov")))).getText();

            // Expected message (retrieved from a properties/config file for better maintainability)
            String expectedMessage = "Area of the image is equivalent to the area of a 100x FOV for optical number 22";

            // Validate the message
            if (infoMsgBelowFov.equals(expectedMessage)) {
                logger.info("Message is displayed correctly: " + infoMsgBelowFov);
                flag = true; // Update flag if the message matches
            } else {
                logger.warn("Message does not match the expected value. Actual: " + infoMsgBelowFov);
            }
        } catch (Exception e) {
            logger.error("An error occurred while verifying the message below FOV.", e);
        }

        return flag; // Return the updated flag
    }

    // Verification of the presence of the warning message for reports with platelet clumps detected
    public boolean warningMsgForPlateletClumpsDetected() {
        boolean flag = false;

        try {
            // Navigate to "Morphology" Tab
            cms.clickOnSpecificSubTab("Morphology");

            // Check if Platelet Clumps are Detected
            String plateletClumpsDetected = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(props.getProperty("plateletClumpsDetectedXpath")))).getText();

            if ("Detected".equals(plateletClumpsDetected)) {
                // Navigate to "Count" Tab
                cms.clickOnSpecificSubTab("Count");

                // Retrieve Warning Message
                String plateletClumpsDetectedWarningMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(props.getProperty("plateletClumpsDetectedWarningMsg")))).getText();

                // Validate Warning Message
                if ("Platelet clumps are detected. Platelet count might be underestimated.".equals(plateletClumpsDetectedWarningMsg)) {
                    logger.info("Message is Displayed: " + plateletClumpsDetectedWarningMsg);
                    flag = true;
                } else {
                    logger.warn("Unexpected Warning Message: " + plateletClumpsDetectedWarningMsg);
                }
            } else {
                logger.info("Platelet clumps are not detected.");
            }
        } catch (Exception e) {
            logger.error("An error occurred while verifying the warning message for platelet clumps detected.", e);
        }

        return flag;
    }


}














