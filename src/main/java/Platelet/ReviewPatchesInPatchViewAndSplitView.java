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


    public  String presenceOfCountTab(String tabPath){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tabPath))).click();
        String countTab=driver.findElement(By.xpath(tabPath)).getText();
        if(countTab.equals("Count")){
            logger.info("count sub tab is present on platelet ");
        }

        return countTab;
    }

    public boolean selectionOfAnyFov() throws InterruptedException {
        boolean flag = true; // Start as true; will set to false if any verification fails.

        // Click on the count tab to ensure the FOVs are loaded
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("countTab")))).click();
        WebElement fov1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("firstFov"))));
        fov1.click();
        String actualTextForFirstFov = driver.findElement(By.xpath(props.getProperty("fovsNavigationMsg"))).getText();
        System.out.println(actualTextForFirstFov);
//        String expectedTextForFov1 = "FOV " + (1) + " / ";
//        if(actualTextForFirstFov.equals(expectedTextForFov1)){
//            logger.info("FOV navigation is correct for FOV " + (1) + ": " + actualTextForFirstFov);
//
//        }
          Thread.sleep(3000);
        // Get the list of all FOVs
        List<WebElement> allFov = driver.findElements(By.xpath(props.getProperty("fovs")));
        int totalFovs = allFov.size()+1;
        System.out.println(totalFovs+1);

        // Loop through all FOVs
        for (int i = 0; i < totalFovs; i++) {
            // Click on the current FOV
            allFov.get(i).click();
            Thread.sleep(3000);

            // Get the FOV navigation message text
            String actualText = driver.findElement(By.xpath(props.getProperty("fovsNavigationMsg"))).getText();
            System.out.println(actualText);

            // Construct the expected text for verification
            String expectedText = "FOV " + (i + 2) + " / " + totalFovs;

            // Verify the actual text matches the expected text
            if (actualText.equals(expectedText)) {
                logger.info("FOV navigation is correct for FOV " + (i + 2) + ": " + actualText);
            } else {
                logger.error("Mismatch in FOV navigation for FOV " + (i + 2) +
                        ". Expected: " + expectedText + ", but found: " + actualText);
                flag = false; // Set to false if verification fails
                break; // Exit loop early if a mismatch is found
            }
        }

        return flag;
    }






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
                int randomValue = random.nextInt(100) + 1;

                // Update the NMG value
                nmgValues.get(j).click();
                nmgValues.get(j).clear();
                nmgValues.get(j).sendKeys(String.valueOf(randomValue));
                System.out.println("Updated NMG Value: " + randomValue);
            }
        }

        return null;
    }



    public boolean multiplicationFactor(){
        boolean status=false;
        return status;
    }











}
