package Platelet;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import RBC.VerifySubTabOfRBC.VerifyUiOfRbcSizeSubTab;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReviewPlateletMorphologyCellType extends CommonMethods {

    private final Logger logger = LogManager.getLogger(ReviewPlateletMorphologyCellType.class);

    public WebDriver driver;
    public WebDriverWait wait;
    Properties props;
    public CommonMethods cms;

    public ReviewPlateletMorphologyCellType(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, time);
        props = Property.prop;
        Property.readRBCProperties();
        cms = new CommonMethods(driver);
    }

    //Verify the presence of cell types in morphology
    public String PresenceOfCellInMorphology() {
        String actualCellName = "";
        cms.clickOnSpecificSubTab("Morphology");
        List<WebElement> CellsName = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("morphologyCellTypes"))));
        for (WebElement cell : CellsName) {
            actualCellName = actualCellName + "," + cell.getText();
        }
        return actualCellName;

    }

    public int getAllPatches() {
        int totalPatches = 0;

        try {
            // Wait for the container holding patches to be visible
            WebElement patchContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='List']")));

            // Retrieve all patches within the container
            List<WebElement> patchItems = patchContainer.findElements(By.xpath(".//div[contains(@class, 'Item')]"));
            for (WebElement patchItem : patchItems) {
                // Retrieve all rows within each patch item
                List<WebElement> rows = patchItem.findElements(By.xpath(".//div[@class='Card patches-container']"));
                totalPatches += rows.size(); // Add the number of rows to the total

                System.out.println(totalPatches);
            }

            // Log the total number of patches
            logger.info("Total patches found: " + totalPatches);
        } catch (Exception e) {
            logger.error("Failed to retrieve patches: ", e);
        }
        return totalPatches;
    }


    public boolean isCellCountEqualToPatchCount(String cellType) {
        boolean flag = false;
        try {
            // Get the element for the specified cell type
            WebElement cellTypeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + cellType + "')]")));
            if (cellTypeElement.isEnabled()) {
                cellTypeElement.click();
                Thread.sleep(2000);
            }

            // Get all visible patches
            // Check if patches are available
            int actualPatchCount = getAllPatches();
            if (actualPatchCount == 0) {
                logger.warn("No patches available for " + cellType);
                return false; // Return false as there are no patches
            }

            logger.info("Total patches found for " + cellType + ": " + actualPatchCount);

            // Get the expected count
            String expectedCountStr = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'" + cellType + "')]/following-sibling::div[1]"))).getText();

            logger.info("Expected count for " + cellType + ": " + expectedCountStr);

            // Compare actual and expected counts
            if (Integer.toString(actualPatchCount).equals(expectedCountStr)) {
                logger.info(cellType + " count matches the patch count. Count: " + actualPatchCount);
                flag = true;
            } else {
                logger.warn("Mismatch in " + cellType + " count. Expected: " + expectedCountStr + ", Actual: " + actualPatchCount);
            }
        } catch (Exception e) {
            logger.error("Error verifying " + cellType + " count: ", e);
        }
        return flag;
    }



    //Verify the functionality if the user select any patch in the platelet morphology tab
    public boolean verifyPatchSelection(String cellType) {
        boolean flag = false;
        try {
            // Click on the specified cell type
            WebElement cellTypeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + cellType + "')]")));
            if (cellTypeElement.isEnabled()) {
                cellTypeElement.click();
            }

            // Get all visible patches
            // Check if patches are available
            int actualPatchCount = getAllPatches();
            if (actualPatchCount == 0) {
                logger.warn("No patches available for " + cellType);
                return false; // Return false as there are no patches
            }

            logger.info("Total patches found for " + cellType + ": " + actualPatchCount);
            List<WebElement> patches = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("patchCount"))));


           for (WebElement patch : patches) {
                patch.click(); // Click the patch

                // Verify if the patch is selected
                if (patch.findElement(By.xpath("./div/div")).getAttribute("class").contains("selected-patch")) {
                    flag = true;
                    logger.info("Patch selected successfully in " + cellType + ". Patch details: " + patch.getText());
                    break; // Exit after verifying the first patch
                } else {
                    logger.warn("Patch not selected properly in " + cellType + ". Patch details: " + patch.getText());
                }
            }
        } catch (Exception e) {
            logger.error("Error verifying patch selection in " + cellType, e);
        }
        return flag;
    }

    public boolean verifyPatchSizes(String cellType) {
        boolean flag=false;
        try {
            // Click on the specified cell type
            WebElement cellTypeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'" + cellType + "')]")));
            if (cellTypeElement.isEnabled()) {
                cellTypeElement.click();
            }
            // Wait for the patch container to be visible
            WebElement patchContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='List']")));

            // Retrieve all patch elements
            List<WebElement> patches = patchContainer.findElements(By.xpath(".//div[contains(@class, 'patch-focus-mode')]"));

            for (WebElement patch : patches) {
                // Get the width and height of the patch
                double width = Double.parseDouble(patch.getCssValue("width").replace("px", "").trim());
                double height = Double.parseDouble(patch.getCssValue("height").replace("px", "").trim());

                // Print the dimensions of the patch
                System.out.println("Patch Dimensions: " + Math.round(width) + "x" + Math.round(height) + " pixels");

                //  Verify the size against expected dimensions
                if (Math.round(width) >=160||Math.round(width) <=162  && Math.round(height) >160||Math.round(height) <=162) {
                    flag=true;
                    System.out.println("Patch size matches expected dimensions.");
                } else {
                    System.out.println("Patch size does NOT match expected dimensions.");
                }
            }
        } catch (Exception e) {
            logger.error("Error while verifying patch sizes: ", e);
        }
        return flag;
    }







}
