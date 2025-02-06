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

import java.util.Properties;

public class ViewAndModifyThePlateletCount{

    private final Logger logger = LogManager.getLogger(ViewAndModifyThePlateletCount.class);

    public WebDriver driver;
    public WebDriverWait wait;
    Properties props;
    public CommonMethods cms;

    public ViewAndModifyThePlateletCount(WebDriver driver) throws Exception {
        int time = 30;
        wait = new WebDriverWait(driver, time);
        props = Property.prop;
        Property.readPlateletProperties();
        cms = new CommonMethods(driver);

    }
    //
    public boolean verifyMultiplicationFactorCancelFunctionality(int inputValue, boolean initialDefaultState) {
        boolean flag = false;
        try {

            // Store the initial value of the multiplication factor
            WebElement multiplicationFactorValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("multiplicationFactorValue"))));
            String initialValue = multiplicationFactorValue.getText();

            WebElement multiplicationFactorEditButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("multiplicationFactorEditBtn"))));
            multiplicationFactorEditButton.click();

            // Locate and click on the multiplication factor field
            WebElement multiplicationFactorField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("multiplicationFactorFieldXpath"))));
            multiplicationFactorField.click();



            // Input a value between 5000 and 50000
            if (inputValue >= 5000 && inputValue <= 50000) {
                multiplicationFactorField.clear();
                multiplicationFactorField.sendKeys(String.valueOf(inputValue));
                logger.info("Entered valid multiplication factor: " + inputValue);
            } else {
                logger.info("Invalid input value: " + inputValue + ". Please enter a value between 5000 and 50000.");
                return false; // Exit if the value is not in the valid range
            }

            // Locate and handle the "Default for all your future reports" checkbox
            WebElement defaultCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("defaultCheckboxXpath"))));
            boolean currentDefaultState = defaultCheckbox.isSelected();
            if (initialDefaultState && !currentDefaultState) {
                defaultCheckbox.click();
                logger.info("Set as default for future reports.");
            } else if (!initialDefaultState && currentDefaultState) {
                defaultCheckbox.click();
                logger.info("Unset as default for future reports.");
            }

            // Locate and click the "Cancel" button
            WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelButtonXpath"))));
            cancelButton.click();
            logger.info("Clicked the Cancel button.");

            // Verify if the changes were reverted
            WebElement multiplicationFactorValues = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("multiplicationFactorValue"))));
            String revertedValue = multiplicationFactorValues.getText();

            if (revertedValue.equals(initialValue)) {
                logger.info("Changes successfully reverted after clicking Cancel.");
                flag = true;
            } else {
                logger.error("Changes were not reverted after clicking Cancel.");
            }

        } catch (Exception e) {
            logger.error("Error while verifying Cancel functionality: " + e.getMessage());
        }
        return flag;
    }


    //
    public boolean verifyMultiplicationFactorCancelFunctionalityWithWarning(int inputValue, boolean initialDefaultState) {
        boolean flag = false;
        try {
            // Store the initial value of the multiplication factor
            WebElement multiplicationFactorValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("multiplicationFactorValue"))));
            String initialValue = multiplicationFactorValue.getText();

            WebElement multiplicationFactorEditButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("multiplicationFactorEditBtn"))));
            multiplicationFactorEditButton.click();

            // Locate and click on the multiplication factor field
            WebElement multiplicationFactorField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("multiplicationFactorFieldXpath"))));
            multiplicationFactorField.click();

            // Input the value into the multiplication factor field
            multiplicationFactorField.clear();
            multiplicationFactorField.sendKeys(String.valueOf(inputValue));
            logger.info("Entered multiplication factor: " + inputValue);

            // Handle invalid input and verify the warning message
            if (inputValue < 5000 || inputValue > 50000) {
                WebElement warningMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("warningMessageXpath"))));
                String actualWarningMessage = warningMessageElement.getText();
                String expectedWarningMessage = "Please enter a value between 5000-50000";

                if (actualWarningMessage.equals(expectedWarningMessage)) {
                    logger.info("Warning message displayed correctly: " + actualWarningMessage);
                    flag = true; // Warning message verified
                } else {
                    logger.error("Warning message is incorrect. Expected: " + expectedWarningMessage + ", but found: " + actualWarningMessage);
                    return false; // Exit if the warning message is incorrect
                }
            }

            // Locate and click the "Cancel" button
            WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelButtonXpath"))));
            cancelButton.click();
            logger.info("Clicked the Cancel button.");

            // Verify if the changes were reverted
            WebElement multiplicationFactorValues = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("multiplicationFactorValue"))));
            String revertedValue = multiplicationFactorValues.getText();

            if (revertedValue.equals(initialValue) ) {
                logger.info("Changes successfully reverted after clicking Cancel.");
                flag = true;
            } else {
                logger.error("Changes were not reverted after clicking Cancel.");
            }

        } catch (Exception e) {
            logger.error("Error while verifying Cancel functionality: " + e.getMessage());
        }
        return flag;
    }










    //Verify the functionality of the default multiplication check boxes for the reports
    public boolean verifyMultiplicationFactorSettings(int inputValue, boolean setAsDefault) {
        boolean flag = false;
        try {

            WebElement multiplicationFactorEditButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("multiplicationFactorEditBtn"))));
            multiplicationFactorEditButton.click();

            // Locate and click on the multiplication factor field
            WebElement multiplicationFactorField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("multiplicationFactorFieldXpath"))));
            multiplicationFactorField.click();

            // Input a value between 5000 and 50000
            if (inputValue >= 5000 && inputValue <= 50000) {
                multiplicationFactorField.clear();
                multiplicationFactorField.sendKeys(String.valueOf(inputValue));
                logger.info("Entered valid multiplication factor: " + inputValue);
            } else {
                logger.info("Invalid input value: " + inputValue + ". Please enter a value between 5000 and 50000.");
                return false; // Exit if the value is not in the valid range
            }

            // Locate and check the "Default for all your future reports" checkbox
            WebElement defaultCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("defaultCheckboxXpath"))));
            if (setAsDefault && !defaultCheckbox.isSelected()) {
                defaultCheckbox.click();
                logger.info("Set as default for future reports.");
            } else if (!setAsDefault && defaultCheckbox.isSelected()) {
                defaultCheckbox.click();
                logger.info("Unset as default for future reports.");
            }

            // Locate and click the "Save" button
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("saveButtonXpath"))));
            saveButton.click();
            logger.info("Clicked the Save button.");
            WebElement multiplicationFactorValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("multiplicationFactorValue"))));
            // Verify if the changes are saved successfully
            String savedValue = multiplicationFactorValue.getText();
            if (savedValue.equals(String.valueOf(inputValue))) {
                logger.info("Multiplication factor successfully saved: " + savedValue);
                flag = true;
            } else {
                logger.error("Failed to save the multiplication factor.");
            }

        } catch (Exception e) {
            logger.error("Error while verifying multiplication factor functionality: " + e.getMessage());
        }
        return flag;
    }

}
