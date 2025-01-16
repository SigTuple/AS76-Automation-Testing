package ReportSignOff;

import CommonTools.DownloadPatches;
import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import summary.Summary;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class verifyReportSignoff {
    private final Logger logger = LogManager.getLogger(verifyReportSignoff.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;
    private String WBCdifferentialHeader;
    public CommonMethods commonMethods;
    public Summary summary;



    public verifyReportSignoff(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        commonMethods=new CommonMethods(driver);
        summary=new Summary(driver);
        actions=new Actions(driver);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readCommonMethodProperties();
        Property.readReportSignOffProperties();
    }

    // Verify the functionality of the Approve Report button
    public boolean verifyApproveReportFunctionality() {
        boolean flag = false;
        try {
            // Locate and click the "Approve report" button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on Approve Report button");

            // Wait for the confirmation popup to appear
            WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approveWarningMsg"))));
            logger.info("Confirmation popup displayed: " + popupMessage.getText());

            // Verify the popup contains the expected message
            if (popupMessage.getText().contains("Are you sure you want to approve?")) {
                // Click on the Confirm button
                WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
                confirmButton.click();
                logger.info("Clicked on Confirm button in the popup");

                // Wait for redirection to the next page
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("noChangeMsg"))));
                logger.info("Successfully redirected to the next page after approval");

                // Verify successful redirection
                WebElement noChangesMessage = driver.findElement(By.xpath(props.getProperty("noChangeMsg")));
                if (noChangesMessage.isDisplayed()) {
                    flag = true;
                    logger.info("Approval process initiated successfully");
                }
            } else {
                logger.error("Confirmation popup message did not match the expected text");
            }
        } catch (Exception e) {
            logger.error("Error while verifying the Approve Report functionality: " + e.getMessage());
        }
        return flag;
    }

    public String getValueOfUnclassifiedValueOfWBC() throws InterruptedException {
        commonMethods.clickOnSpecificTab("WBC");
        String unclassifiedValue = "";
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");
        WebElement unclassifiedcell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Unclassified']/following-sibling::td[1]")));
        if (unclassifiedcell.isDisplayed()) {
            unclassifiedValue = unclassifiedcell.getText();
            System.out.println(unclassifiedValue);
            logger.info("Successfully get the value of unclassified cell");
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Summary']"))).click();
        return unclassifiedValue;
    }
//Verify the popup content in approve button

    public boolean verifyPopupContent(String wbcTabUnclassifiedValue ) {
        boolean flag = false;
        try {
            // Click the Approve Report button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on the Approve Report button");

            // Wait for the popup to appear
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("popupText"))));
            String popupText = popup.getText().trim().replaceAll("\\s+", " ");
            logger.info("Pop-up content retrieved: \n" + popupText);

            // Fetch the dynamic value for unclassified patches from the WBC tab
            //String wbcTabUnclassifiedValue = getValueOfUnclassifiedValueOfWBC();
            logger.info("Unclassified patches count retrieved: " + wbcTabUnclassifiedValue);

            // Build the expected content dynamically
            String expectedContent = "Are you sure you want to approve? "
                    + "All WBC patches, including unclassified and rejected, have been reviewed. "
                    + "The scanned area for RBC morphology has been reviewed. "
                    + "Required patches and FOVs for platelet count and morphology have been reviewed. "
                    + "Note: There are " + wbcTabUnclassifiedValue + " patches in the unclassified bucket of WBC. "
                    + "Cancel "
                    + "Confirm";

            // Normalize the expected content for comparison
            expectedContent = expectedContent.trim().replaceAll("\\s+", " ");

            // Compare the extracted popup text with the dynamically constructed expected content
            if (popupText.equals(expectedContent)) {
                logger.info("Popup content matches the expected content.");
                flag = true;
            } else {
                logger.error("Popup content mismatch!\nExtracted: " + popupText + "\nExpected: " + expectedContent);
            }
        } catch (Exception e) {
            logger.error("Error while verifying popup content: " + e.getMessage());
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelBtn")))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Summary']"))).click();

        return flag;
    }

    //verify the availability of support image in approve report flow
    public boolean availabilityOfSupportingImage( ) {
        boolean flag = false;
        try {
            // Click the Approve Report button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on the Approve Report button");

            // Click on the Confirm button
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
            confirmButton.click();
            logger.info("Clicked on Confirm button in the popup");

            //locate the supporting image button
            WebElement supportingImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("supportingImage"))));
            if (supportingImage.isDisplayed() && supportingImage.isEnabled() && supportingImage.getText().equals("Add supporting images")) {
                flag=true;
                logger.info("supporting image button is available");
            }

        } catch (Exception e) {
            logger.error("Error verify the supporting image: " + e.getMessage());
        }
       return flag ;
    }

    //verify the availability of support image in approve report flow
    public boolean functionalityOfSupportingImage( ) {
        boolean flag = false;
        try {
            // Click the Approve Report button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on the Approve Report button");

            // Click on the Confirm button
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
            confirmButton.click();
            logger.info("Clicked on Confirm button in the popup");

            //locate the supporting image button
            WebElement supportingImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("supportingImage"))));
            if (supportingImage.isDisplayed() && supportingImage.isEnabled() && supportingImage.getText().equals("Add supporting images")) {
                supportingImage.click();
                String warningMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("noChangeMsg")))).getText();

                String supportingImagePageTittle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("supportingImagePageTittle")))).getText();
                if (supportingImagePageTittle.equals("Select supporting images") && warningMsg.equals("No changes can be made once report is approved!")) {
                    flag = true;
                    logger.info("supporting image button is available And Waring massage also available");

                }
            }

        } catch (Exception e) {
            logger.error("Error verify the supporting image Functionality: " + e.getMessage());
        }
        return flag ;
    }
    public boolean availabilityOfCellName() {
        boolean flag = false;

        // Click the Approve Report button
        WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
        approveButton.click();
        logger.info("Clicked on the Approve Report button");

        // Click on the Confirm button
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
        confirmButton.click();
        logger.info("Clicked on Confirm button in the popup");

        //  Expected eligible cells
        String expectedEligibleCells = "WBC(%)\nWBC\nNeutrophils\nLymphocytes\nEosinophils\nMonocytes\nBasophils\n"
                + "Atypical Cells/Blasts\nImmature Granulocytes\nImmature Eosinophils\nImmature Basophils\nPromonocytes\n"
                + "Prolymphocytes\nHairy Cells\nSezary Cells\nPlasma Cells\nOthers\nNon-WBC\nNRBC\nSmudge Cells\n"
                + "RBC (Grade)\nMicrocytes\nMacrocytes\nOvalocytes\nElliptocytes\nTeardrop Cells\nFragmented Cells\n"
                + "Target Cells\nEchinocytes\nHypochromic Cells\nPolychromatic Cells\nPlatelet (Count)\nLarge Platelets\n"
                + "Platelet Clumps";

        // Locate and click the Supporting Image button
        WebElement supportingImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("supportingImage"))));
        if (supportingImage.isDisplayed() && supportingImage.isEnabled() && supportingImage.getText().equals("Add supporting images")) {
            supportingImage.click();
            logger.info("Clicked on Add Supporting Images button");

            // Get the eligible cells from the page
            String actualEligibleCells = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("eligibleCells")))).getText();
            logger.info("Eligible cells retrieved from the page:\n" + actualEligibleCells);

            // Normalize both the expected and actual cell lists
            List<String> expectedCellList = Arrays.asList(expectedEligibleCells.split("\\n"))
                    .stream()
                    .map(String::trim)
                    .collect(Collectors.toList());

            List<String> actualCellList = Arrays.asList(actualEligibleCells.split("\\n"))
                    .stream()
                    .map(cell -> cell.replaceAll("\\(.*\\)", "").trim()) // Remove text in parentheses and trim
                    .collect(Collectors.toList());

            // Add specific handling for special cases
            expectedCellList = expectedCellList.stream()
                    .map(cell -> cell.replace("WBC(%)", "WBC").replace("RBC (Grade)", "RBC").replace("Platelet (Count)", "Platelet"))
                    .collect(Collectors.toList());

            if (actualCellList.containsAll(expectedCellList)) {
                flag = true; // Set the flag to true when all cells are present
                logger.info("All expected eligible cells are present in the Supporting Images page.");
            } else {
                // Identify and log missing cells
                List<String> missingCells = expectedCellList.stream()
                        .filter(cell -> !actualCellList.contains(cell))
                        .collect(Collectors.toList());
                logger.warn("The following expected cells are missing from the Supporting Images page:\n" + String.join(", ", missingCells));
            }
        }
        return flag;
    }
    public boolean verifyTheCheckBoxOfEligibleCell() {
        boolean flag = false;

        try {
            // Click the Approve Report button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on the Approve Report button");

            // Click the Confirm button
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
            confirmButton.click();
            logger.info("Clicked on Confirm button in the popup");

            // Click on Add Supporting Images button
            WebElement supportingImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("supportingImage"))));
            if (supportingImage.isDisplayed() && supportingImage.isEnabled() && supportingImage.getText().equals("Add supporting images")) {
                supportingImage.click();
                logger.info("Clicked on Add Supporting Images button");
            } else {
                backToReport();
                return false;
            }

            // Wait for the left panel to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'celle-selection-list')])[1]")));

            // Locate all enabled checkboxes in the left panel
            List<WebElement> enabledCheckboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(props.getProperty("enableCheckBox"))));

            for (WebElement checkbox : enabledCheckboxes) {
                // Get the label of the checkbox
                String cellName = checkbox.getAttribute("name").trim();
                System.out.println("Processing checkbox for cell: " + cellName);


                // Verify checkbox deselection
                checkbox.click(); // Deselect the checkbox
                System.out.println("Checkbox deselected for: " + cellName);

                // Verify the corresponding cell is hidden
                List<WebElement> cellElements = driver.findElements(By.xpath(
                        props.getProperty("selectedCellNameXpath1") + cellName + props.getProperty("selectedCellNameXpath2")
                ));
                if (cellElements.isEmpty()) {
                    flag = true;
                    System.out.println("Verified: Corresponding cell is hidden for " + cellName);
                } else {
                    System.out.println("Error: Corresponding cell is still displayed for " + cellName);
                }
                // Verify checkbox selection
                if (!checkbox.isSelected()) {
                    checkbox.click(); // Select the checkbox
                    System.out.println("Checkbox selected for: " + cellName);

                    // Verify the corresponding cell is displayed
                    WebElement cellElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(props.getProperty("selectedCellNameXpath1") + cellName + props.getProperty("selectedCellNameXpath2"))));

                    if (cellElement != null && cellElement.isDisplayed()) {
                        flag = true;
                        System.out.println("Verified: Corresponding cell is displayed for " + cellName);
                    } else {
                        System.out.println("Error: Corresponding cell is not displayed for " + cellName);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error occurred while verifying checkboxes: " + e.getMessage());
        }

        return flag;
    }

    public boolean verifyTheCheckBoxOfEligibleCellModifyBtn() {
        boolean flag = false;

        try {
            // Click the Approve Report button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on the Approve Report button");

            // Click the Confirm button
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
            confirmButton.click();
            logger.info("Clicked on Confirm button in the popup");

            // Click on Add Supporting Images button
            WebElement supportingImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("supportingImage"))));
            if (supportingImage.isDisplayed() && supportingImage.isEnabled() && supportingImage.getText().equals("Add supporting images")) {
                supportingImage.click();
                logger.info("Clicked on Add Supporting Images button");
            } else {
                backToReport();
                return false;
            }

            // Wait for the left panel to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'celle-selection-list')])[1]")));

            // Locate all enabled checkboxes in the left panel
            List<WebElement> enabledCheckboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(props.getProperty("enableCheckBox"))));

            for (WebElement checkbox : enabledCheckboxes) {
                // Get the label of the checkbox
                String cellName = checkbox.getAttribute("name").trim();
                System.out.println("Processing checkbox for cell: " + cellName);

                // Verify checkbox deselection
                checkbox.click(); // Deselect the checkbox
                System.out.println("Checkbox deselected for: " + cellName);

                // Verify the corresponding cell is hidden
                List<WebElement> cellElements = driver.findElements(By.xpath(
                        props.getProperty("selectedCellNameXpath1") + cellName + props.getProperty("selectedCellNameXpath2")
                ));
                if (cellElements.isEmpty()) {
                    flag = true;
                    System.out.println("Verified: Corresponding cell is hidden for " + cellName);
                } else {
                    System.out.println("Error: Corresponding cell is still displayed for " + cellName);
                }
                // Verify checkbox selection
                if (!checkbox.isSelected()) {
                    checkbox.click(); // Select the checkbox
                    System.out.println("Checkbox selected for: " + cellName);

                    // Verify the corresponding cell is displayed
                    WebElement cellElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(props.getProperty("selectedCellModifyBtnXpath1")+ cellName +props.getProperty("selectedCellModifyBtnXpath2"))
                    ));

                    if (cellElement != null && cellElement.isDisplayed()) {
                        System.out.println("Verified: Corresponding cell is displayed for " + cellName);

                        // Locate the Modify button within the cell container
                        WebElement modifyButton = cellElement.findElement(By.xpath(".//span[contains(text(), 'Modify')]"));
                        if (modifyButton != null && modifyButton.isDisplayed()) {
                            verifyModifyButtonFunctionality(modifyButton,cellName);
                            flag=true;
                            System.out.println("Verified: Modify button is displayed for " + cellName);
                        } else {
                            System.out.println("Error: Modify button is not displayed for " + cellName);
                        }
                    } else {
                        System.out.println("Error: Corresponding cell is not displayed for " + cellName);
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Error occurred while verifying checkboxes: " + e.getMessage());
        }

        return flag;
    }

    public void verifyModifyButtonFunctionality(WebElement modifyButton, String cellName) {
        try {
            // Click the Modify button
            modifyButton.click();
            Thread.sleep(10000); // Keeping the wait as per your original logic
            logger.info("Clicked Modify button for cell type: " + cellName);

            // Verify the modify patch window is displayed
            WebElement modifyPatchWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("modifyPopup"))));
            if (modifyPatchWindow.isDisplayed()) {
                logger.info("Modify patch window displayed successfully for: " + cellName);
            } else {
                logger.error("Error: Modify patch window not displayed for: " + cellName);
                return;
            }

            // Get all the patch elements
            List<WebElement> patches = modifyPatchWindow.findElements(By.xpath(props.getProperty("AllPatch")));
            int availablePatches = patches.size();
            logger.info("Total patches available for selection: " + availablePatches);

            // Handle case with no patches
            if (availablePatches == 0) {
                logger.warn("No patches available for cell: " + cellName);
                return;
            }

            //  Select up to 5 patches or as many as available
            List<WebElement> preSelectedPatches = modifyPatchWindow.findElements(By.xpath(props.getProperty("selectedPatches")));
            for (WebElement patch : preSelectedPatches) {
                patch.click(); // Unselect pre-selected patches
            }
            logger.info("Cleared pre-selected patches for cell: " + cellName);

            int patchesToSelect = Math.min(5, availablePatches);
            selectPatches(modifyPatchWindow, patchesToSelect);
            validateSelectedPatchesNumbering(modifyPatchWindow, patchesToSelect);
            WebElement addToReportCTA = modifyPatchWindow.findElement(By.xpath(props.getProperty("addToReportBtn")));
            addToReportCTA.click();
            Thread.sleep(10000); // Keeping the wait
            logger.info("Added " + patchesToSelect + " selected patches to the report.");

            //  Select more than 12 patches if possible
            if (availablePatches > 12) {
                wait.until(ExpectedConditions.elementToBeClickable(modifyButton)).click(); // Reopen Modify window
                WebElement modifyPatchWindow2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("modifyPopup"))));

                List<WebElement> preSelectPatchs = modifyPatchWindow2.findElements(By.xpath(props.getProperty("selectedPatches")));
                for (WebElement patch : preSelectPatchs) {
                    patch.click(); // Unselect pre-selected patches
                }
                logger.info("Cleared pre-selected patches for 'more than 12 patches' scenario.");

                selectPatches(modifyPatchWindow2, 13); // Attempt to select 13 patches
                WebElement maxPatchAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("warningMsg"))));
                if (maxPatchAlert.isDisplayed()) {
                    logger.info("Verified alert for selecting more than 12 patches: " + maxPatchAlert.getText());


                    // Cancel the operation
                    WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelBtn"))));
                    cancelButton.click();
                    Thread.sleep(5000); // Keeping the wait
                    logger.info("Cancelled operation, resetting to default system-selected patches.");
                } else {
                    logger.error("Error: No alert displayed when selecting more than 12 patches.");
                }

            }

            else {
                 logger.info("Skipping 'more than 12 patches' test as only " + availablePatches + " patches are available.");
            }

        } catch (Exception e) {
            logger.error("Error occurred while testing Modify button functionality for " + cellName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clearAllPatches(WebElement modifyPatchWindow) {
        List<WebElement> selectedPatches = modifyPatchWindow.findElements(By.xpath(props.getProperty("selectedPatches")));
        for (WebElement patch : selectedPatches) {
            patch.click(); // Unselect the patch
        }
        logger.info("Cleared all selected patches.");
    }

    public void selectPatches(WebElement modifyPatchWindow, int count) {
        List<WebElement> patches = modifyPatchWindow.findElements(By.xpath(props.getProperty("AllPatch")));
        int selectedCount = 0;
        for (WebElement patch : patches) {
            if (!patch.getAttribute("class").contains("modification") && selectedCount < count) {
                patch.click(); // Select the patch
                selectedCount++;
            }
        }
        logger.info("Selected " + selectedCount + " patches.");
    }

    public void validateSelectedPatchesNumbering(WebElement modifyPatchWindow, int expectedCount) {
        List<WebElement> selectedPatches = modifyPatchWindow.findElements(By.xpath(props.getProperty("selectedPatches")));
        if (selectedPatches.size() == expectedCount) {
            logger.info("Verified: " + expectedCount + " patches selected with correct numbering.");
            for (int i = 0; i < selectedPatches.size(); i++) {
                String numbering = selectedPatches.get(i).findElement(By.xpath(".//div[@class='patch-count-icon']")).getText();
                if (!numbering.equals(String.valueOf(i + 1))) {
                    logger.error("Error: Incorrect numbering for patch at position " + (i + 1));
                }
            }
        } else {
            logger.error("Error: Selected patches count mismatch. Expected: " + expectedCount + ", Actual: " + selectedPatches.size());
        }
    }









    //Verify the values present in the PS impression section when the PS impressions are added in the Summary tab of the report
    public boolean psImpressioninPreviewReport() throws InterruptedException {
        boolean flag = true;
        boolean textOnPsImpression = summary.psImpressions();
        {
            // Locate and click the "Approve report" button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on Approve Report button");

            // Wait for the confirmation popup to appear
            WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approveWarningMsg"))));
            logger.info("Confirmation popup displayed: " + popupMessage.getText());

            // Verify the popup contains the expected message
            if (popupMessage.getText().contains("Are you sure you want to approve?")) {
                // Click on the Confirm button
                WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
                confirmButton.click();
                logger.info("Clicked on Confirm button in the popup");

                // Wait for redirection to the next page
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("noChangeMsg"))));
                logger.info("Successfully redirected to the next page after approval");

                // Verify successful redirection
                String psImpressionText = driver.findElement(By.xpath(props.getProperty("psimpressionText"))).getText();
                if (psImpressionText.contains("Test123")) {
                    flag = true;
                    System.out.println(psImpressionText);
                    logger.info("PS Impression is presented in  preview report");
                }
            } else {
                logger.error("PS Impression is not presented in  preview report");
            }
        }
        return flag;
    }


    // Verify the Report approval functionality when the user clicks on the approve button from the approval pop-up
    public boolean approveButtonFromApprovePopup() {
        boolean flag = false;

        try {
            //store the slide id
            Thread.sleep(2000);
           // driver.navigate().refresh();
            WebElement slideID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("slideID"))));
            String exptedSlideID = slideID.getText();
            // Locate and click the "Approve Report" button
            WebElement approveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
            approveButton.click();
            logger.info("Clicked on Approve Report button");

            // Wait for the confirmation popup to appear
            WebElement popupMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approveWarningMsg"))));
            logger.info("Confirmation popup displayed: " + popupMessage.getText());

            // Verify the popup contains the expected message
            if (popupMessage.getText().contains("Are you sure you want to approve?")) {
                // Click on the Confirm button
                WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("confirmBtn"))));
                confirmButton.click();
                logger.info("Clicked on Confirm button in the popup");

                // Wait for redirection to the next page
                WebElement noChangeMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("noChangeMsg"))));
                logger.info("Successfully redirected to the next page: " + noChangeMsg.getText());

                // Click the "Approve Report" button again in the preview page
                WebElement approveButtonInPreview = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveButtonXpath"))));
                approveButtonInPreview.click();
                logger.info("Clicked on Approve Report button on the Preview Page");

                // Wait for and verify the popup in the preview page
                WebElement popupMessageFromPreview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("popupText"))));
                logger.info("Confirmation popup displayed on the Preview Page: " + popupMessageFromPreview.getText());

                // Click the Approve button in the preview report popup
                WebElement approveBtnFromPreviewReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("approveBtnFromPreviewReport"))));
                approveBtnFromPreviewReport.click();
                logger.info("Clicked on Approve button in the Preview Report popup");

                // Check the status of the report
                Thread.sleep(3000);
                WebElement approvedReportStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReportStatus"))));
                String reportStatus = approvedReportStatus.getText().replace("\n", "").trim();
                String expectedStatus = "Approved By:" + props.getProperty("reviewer1");

                if (reportStatus.equals(expectedStatus)) {
                    //flag = true;
                    logger.info("Report approved successfully with status: " + reportStatus);
                } else {
                    logger.error("Report status mismatch. Expected: " + expectedStatus + ", Found: " + reportStatus);
                }
                Thread.sleep(5000);
                commonMethods.clickOnBackTOListReportButton();
                Thread.sleep(3000);
                commonMethods.selectSpecificStatus("Reviewed");
                Thread.sleep(3000);
                WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchfield"))));
                searchField.click();
                searchField.sendKeys(exptedSlideID);
                actions=new Actions(driver);
                actions.sendKeys(Keys.RETURN).perform();
                Thread.sleep(3000);
                // Locate the element
                WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approveReportStatusXpath1")+exptedSlideID+props.getProperty("approveReportStatusXpath2"))));
                // Verify if the element is displayed
                if (statusElement.isDisplayed()) {
                    logger.info("The report with Slide ID " + exptedSlideID + " is approved.");
                    flag = true;
                }
                Thread.sleep(3000);
                actions=new Actions(driver);
                actions.moveToElement(statusElement).click().perform();
                downloadReport();

                 } else {
                logger.error("Confirmation popup message is incorrect or not displayed.");
            }
        } catch (Exception e) {
            logger.error("Error during the report approval process: " + e.getMessage());
        }

        return flag;
    }

    //Verify the Functionality of the back to report
    public boolean backToReport(){
        boolean flag=false;
        WebElement backToReportArrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("backToReportArrow"))));
        backToReportArrow.click();
        WebElement backToReportBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("backToReportBtn"))));
        if (backToReportBtn.isDisplayed()){
            backToReportBtn.click();
            logger.info("Click on back to report button");
            WBCdifferentialHeader=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("WBCdifferentialHeader")))).getText();
            if (WBCdifferentialHeader.contains("WBC differential count")){
                System.out.println(WBCdifferentialHeader);
                flag=true;
                logger.info("Back to report to summary page");
            }
        }
        return flag;
    }


    //Verify the Reject report pop-up UI elements
    public boolean rejectReportPopup(){
        boolean flag=false;
        WebElement rejectBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("rejectButtonXpath"))));
        rejectBtn.click();
        logger.info("Clicked on the Reject Report button");

        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("popupText"))));
        String actualPopupContent = popup.getText().trim();
        logger.info("Pop-up content retrieved: \n" + actualPopupContent);

        String expectedPopupText="Are you sure you want to reject this report?\n"+
                "No changes can be made once report is rejected!\n"+
                "Cancel\n"+
                "Reject report";
        if (actualPopupContent.equals(expectedPopupText)){
            WebElement popupRejectReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Reject report')])[2]")));
            WebElement popupCancel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelBtn"))));
            if (popupRejectReport.isDisplayed() && popupCancel.isDisplayed()){
                flag=true;
                popupCancel.click();
                logger.info("Verify the content of the pop up");
            }
        }
        return flag;

    }


    // Verify the Reject Report functionality
    public boolean rejectReportFunctionality() {
        boolean flag = false;

        try {
            //store the slide id
            WebElement slideID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("slideID"))));
            String exptedSlideID = slideID.getText();
            // Locate and click the "Reject Report" button
            WebElement rejectBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("rejectButtonXpath"))));
            rejectBtn.click();
            logger.info("Clicked on the Reject Report button");

            // Wait for the confirmation popup to appear
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("popupText"))));
            String actualPopupContent = popup.getText().trim();
            logger.info("Pop-up content retrieved: \n" + actualPopupContent);

            // Define the expected popup content
            String expectedPopupText = "Are you sure you want to reject this report?\n" +
                    "No changes can be made once report is rejected!\n" +
                    "Cancel\n" +
                    "Reject report";

            // Verify the popup content
            if (actualPopupContent.equals(expectedPopupText)) {
                logger.info("Popup content matches the expected content.");

                // Locate Reject Report and Cancel buttons on the popup
                WebElement popupRejectReport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[contains(text(),'Reject report')])[2]")));
                WebElement popupCancel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("cancelBtn"))));

                // Verify the buttons are displayed
                if (popupRejectReport.isDisplayed() && popupCancel.isDisplayed()) {
                    logger.info("Reject and Cancel buttons are displayed in the popup.");
                    popupRejectReport.click();
                    logger.info("Clicked on Reject Report button in the popup.");

                    // Wait and verify the report status
                    WebElement rejectedReportStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReportStatus"))));
                    String reportStatus = rejectedReportStatus.getText().replace("\n", "").trim();
                    String expectedStatus = "Rejected By:" + props.getProperty("reviewer1");

                    if (reportStatus.equals(expectedStatus)) {
                       // flag = true;
                        logger.info("Report rejected successfully with status: " + reportStatus);
                    } else {
                        logger.error("Report status mismatch. Expected: " + expectedStatus + ", Found: " + reportStatus);
                    }
                    commonMethods.clickOnBackTOListReportButton();
                    commonMethods.selectSpecificStatus("Reviewed");
                    Thread.sleep(3000);
                    WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchfield"))));
                    searchField.click();
                    searchField.sendKeys(exptedSlideID);
                    actions=new Actions(driver);
                    actions.sendKeys(Keys.RETURN).perform();
                    Thread.sleep(3000);
                    // Locate the element
                    WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("rejectedReportStatusXpath1")+exptedSlideID+props.getProperty("rejectedReportStatusXpath2"))));

                    // Verify if the element is displayed
                    if (statusElement.isDisplayed()) {
                        logger.info("The report with Slide ID " + exptedSlideID + " is Rejected.");
                        flag = true;
                    }

                } else {
                    logger.error("Reject or Cancel button is not displayed in the popup.");
                }
            } else {
                logger.error("Popup content mismatch. \nExpected: \n" + expectedPopupText + "\nFound: \n" + actualPopupContent);
            }
        } catch (Exception e) {
            logger.error("Error during the Reject Report functionality verification: " + e.getMessage());
        }

        return flag;
    }

    //Download the approved report
    public void downloadReport(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("KebabMenuXpath")))).click();
        WebElement downloadOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/span[text()='Download PDF report']")));
        if (downloadOption.isDisplayed()){
            downloadOption.click();
            logger.info("Download option is visible for approved report");
        }
    }



    // Method to fetch dynamic data from the web app
    public Map<String, String> fetchExpectedValues(Map<String, String> locators) {
        Map<String, String> expectedValues = new HashMap<>();

        for (Map.Entry<String, String> entry : locators.entrySet()) {
            try {
                WebElement element = driver.findElement(By.xpath(entry.getValue()));
                String value = element.getText().trim();
                expectedValues.put(entry.getKey(), value);

                // Log fetched values
                System.out.println("Fetched Value - Key: " + entry.getKey() + ", Value: " + value);
            } catch (NoSuchElementException e) {
                System.err.println("Element not found for key: " + entry.getKey());
                expectedValues.put(entry.getKey(), ""); // Handle missing elements
            }
        }

        return expectedValues;
    }


    // Method to extract text content from the PDF
    public String extractPDFContent(String filePath) throws IOException {
        File file = new File(filePath);

        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String pdfContent = pdfStripper.getText(document);

            // Normalize the content (e.g., remove extra spaces and line breaks)
            pdfContent = pdfContent.replaceAll("\\s+", " ").trim();

            // Log extracted PDF content
            System.out.println("Extracted PDF Content: \n" + pdfContent);

            return pdfContent;
        } catch (IOException e) {
            System.err.println("Error while reading PDF: " + e.getMessage());
            return "";
        }
    }

    // Method to rename the PDF file based on the Slide ID
    public void renamePDFFile(String oldFilePath, String newFileName) {
        File oldFile = new File(oldFilePath);
        File newFile = new File(oldFile.getParent(), newFileName);

        if (oldFile.renameTo(newFile)) {
            System.out.println("PDF renamed successfully to: " + newFileName);
        } else {
            System.err.println("Failed to rename PDF file.");
        }
    }






}
