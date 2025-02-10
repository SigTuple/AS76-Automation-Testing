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

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class DownloadPatches {
    private final Logger logger = LogManager.getLogger(DownloadPatches.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;

    public DownloadPatches(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readSummaryProperties();
        Property.readReportListingProperties();
        Property.readCommonToolsProperties();
    }

    public void downloadPatchesForAllCells(String tableXPath, String cellColumnXPath, String patchXPath) {
        try {
            // Locate all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath(tableXPath));

            for (WebElement row : rows) {

                try {
                    actions =new Actions(driver);
                    actions.moveToElement(row).click().perform();
                    Thread.sleep(5000);
                    WebElement cellNameElement = row.findElement(By.xpath(cellColumnXPath));
                    String cellValue = row.findElement(By.xpath("./td[3]")).getText();
                    String cellName = cellNameElement.getText();
                    if (cellValue.equals("-")) {
                        logger.info("Skipping cell with no value: " + cellName);
                        continue; // Skip this cell
                    }
                    logger.info("Processing cell: " + cellName);

                    // Locate the patches associated with this row
                    List<WebElement> patches = driver.findElements(By.xpath(patchXPath));
                    if (patches.isEmpty()) {
                        logger.info("No patches found for cell: " + cellName);
                        continue; // Skip to the next row if no patches are found
                    }

                    for (int i = 0; i < patches.size(); i++) {
                        if (i >= 3) { // Limit to 5 patches
                            break; // Exit the loop
                        }
                        try {
                            WebElement patch = patches.get(i);
                            Thread.sleep(2000); // Pause to ensure the download starts
                            actions = new Actions(driver);
                            actions.contextClick(patch).perform();
                            Thread.sleep(500); // Wait for menu to load

                            // Locate and click the "Download" option
                            WebElement downloadOption = driver.findElement(By.xpath(props.getProperty("download")));
                            downloadOption.click();

                            Thread.sleep(2000); // Wait for the download to complete
                            logger.info("Downloaded patch for cell: " + cellName);
                        } catch (Exception e) {
                            logger.info("Error downloading patch for cell: " + cellName + ". Error: " + e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    logger.info("Error processing row: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.info("Error in download process: " + e.getMessage());
        }
    }

    public void downloadAllPatchesForEachCell(String tableXPath, String cellColumnXPath, String patchXPath) {
        try {
            // Locate all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath(tableXPath));
            actions = new Actions(driver); // Initialize Actions

            for (WebElement row : rows) {
                try {
                    // Select the row
                    row.click();
                    Thread.sleep(3000); // Adjust delay if needed

                    // Get cell name and value
                    WebElement cellNameElement = row.findElement(By.xpath(cellColumnXPath));
                    String cellName = cellNameElement.getText();
                    String cellValue = row.findElement(By.xpath("./td[3]")).getText();

                    // Skip cells with no value ("-")
                    if (cellValue.equals("-")) {
                        logger.info("Skipping cell with no value: " + cellName);
                        continue;
                    }

                    System.out.println("Processing cell: " + cellName);

                    // Locate all patches
                    List<WebElement> patches = driver.findElements(By.xpath(patchXPath));
                    if (patches.isEmpty()) {
                        logger.info("No patches found for cell: " + cellName);
                        continue;
                    }

                    // Limit selection to the first 10 patches
                    int patchLimit = Math.min(5, patches.size());
                    for (int i = 0; i < patchLimit; i++) {
                        try {
                            WebElement patch = patches.get(i);
                            patch.click();
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            logger.info("Error selecting patch: " + e.getMessage());
                        }
                    }

                    // Right-click to open the context menu
                    try {
                        actions.contextClick(patches.get(0)).perform(); // Right-click on the first patch
                        Thread.sleep(1000); // Wait for context menu to appear

                        // Locate and click the "Download" option
                        WebElement downloadOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("download"))));
                        downloadOption.click();

                        logger.info("Downloaded first 10 patches for cell: " + cellName);
                    } catch (Exception e) {
                        logger.info("Error downloading patches for cell: " + cellName + ". Error: " + e.getMessage());
                    }

                    // Optional: Delay after processing a cell
                    Thread.sleep(2000);
                } catch (Exception e) {
                    logger.info("Error processing cell: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.info("Error in download process: " + e.getMessage());
        }
    }

    public void downloadPatchesForAllCellsRbc(String tableXPath, String cellColumnXPath, String patchXPath) {
        try {
            // Locate all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath(tableXPath));

            for (WebElement row : rows) {

                try {
                    // Get the cell name (e.g., Neutrophils, Lymphocytes)
                    actions =new Actions(driver);
                    actions.moveToElement(row).click().perform();
                   // row.click();
                    Thread.sleep(2000);
                    WebElement cellNameElement = row.findElement(By.xpath(cellColumnXPath));
                    String cellName = cellNameElement.getText();

                    // Locate the patches associated with this row
                    List<WebElement> patches = driver.findElements(By.xpath(patchXPath));
                    if (patches.isEmpty()) {
                        logger.info("No patches found for cell: " + cellName);
                        continue; // Skip to the next row if no patches are found
                    }


                    for (int i = 0; i < patches.size(); i++) {
                        if (i >= 3) { // Limit to 5 patches
                            break; // Exit the loop
                        }
                        try {
                            WebElement patch = patches.get(i);
                            Thread.sleep(2000); // Pause to ensure the download starts
                            actions =new Actions(driver);
                            actions.contextClick(patch).perform();
                            WebElement downloadOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("download"))));
                            downloadOption.click();
                            logger.info("downloading patch "+(i + 1));
                            Thread.sleep(2000); // Pause to ensure the download starts
                        } catch (Exception e) {
                            logger.info("Error downloading patch " + (i + 1) + ". Error: " + e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    logger.info("Error processing row: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.info("Error in download process: " + e.getMessage());
        }
    }


    public void downloadAllPatchesForEachCellRBC(String tableXPath, String cellColumnXPath, String patchXPath) {
        try {
            // Locate all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath(tableXPath));
            actions = new Actions(driver); // Initialize Actions

            for (WebElement row : rows) {
                try {
                    // Select the row
                    row.click();
                    Thread.sleep(2000); // Adjust delay if needed

                    // Get cell name and value
                    WebElement cellNameElement = row.findElement(By.xpath(cellColumnXPath));
                    String cellName = cellNameElement.getText();
                    logger.info("Processing cell: " + cellName);

                    // Locate all patches
                    List<WebElement> patches = driver.findElements(By.xpath(patchXPath));
                    if (patches.isEmpty()) {
                        logger.info("No patches found for cell: " + cellName);
                        continue;
                    }

                    // Limit selection to the first 10 patches
                    int patchLimit = Math.min(3, patches.size());
                    for (int i = 0; i < patchLimit; i++) {
                        try {
                            WebElement patch = patches.get(i);
                            patch.click();
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            logger.info("Error selecting patch: " + e.getMessage());
                        }
                    }

                    // Right-click to open the context menu
                    try {
                        Thread.sleep(2000); // Wait for context menu to appear
                        actions.contextClick(patches.get(0)).perform(); // Right-click on the first patch

                        // Locate and click the "Download" option
                        WebElement downloadOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("download"))));
                        downloadOption.click();
                        logger.info("Downloaded first 10 patches for cell: " + cellName);
                    } catch (Exception e) {
                        logger.info("Error downloading patches for cell: " + cellName + ". Error: " + e.getMessage());
                    }

                    // Optional: Delay after processing a cell
                    Thread.sleep(2000);
                } catch (Exception e) {
                    logger.info("Error processing cell: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.info("Error in download process: " + e.getMessage());
        }
    }

    //verify the additional information
    public boolean additionalInformation(String tableXPath, String cellColumnXPath, String patchXPath) {
       boolean flag=false;
        try {
            // Locate all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath(tableXPath));

            for (WebElement row : rows) {

                try {
                    row.click();
                    Thread.sleep(5000);
                    WebElement cellNameElement = row.findElement(By.xpath(cellColumnXPath));
                    String cellValue = row.findElement(By.xpath("./td[3]")).getText();
                    String cellName = cellNameElement.getText();
                    if (cellValue.equals("-")) {
                        logger.info("Skipping cell with no value: " + cellName);
                        continue; // Skip this cell
                    }
                    logger.info("Processing cell: " + cellName);

                    // Locate the patches associated with this row
                    List<WebElement> patches = driver.findElements(By.xpath(patchXPath));
                    if (patches.isEmpty()) {
                        logger.info("No patches found for cell: " + cellName);
                        continue; // Skip to the next row if no patches are found
                    }

                    for (int i = 0; i < patches.size(); i++) {
                        if (i >= 3) { // Limit to 5 patches
                            break; // Exit the loop
                        }
                        try {
                            WebElement patch = patches.get(i);
                            Thread.sleep(2000);
                            actions = new Actions(driver);
                            actions.contextClick(patch).perform();
                            Thread.sleep(500); // Wait for menu to load

                            // Locate and click the "Download" option
                            WebElement additionalInfoBtn = driver.findElement(By.xpath(props.getProperty("additionalInfo")));
                            additionalInfoBtn.click();
                            WebElement rankname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("rank"))));
                            Thread.sleep(2000);
                            System.out.println(rankname.getText());
                            if (rankname.getText().contains(cellName)){
                                flag=true;
                                logger.info("Additional information Tab is available");
                            }
                            actions = new Actions(driver);
                            actions.sendKeys(Keys.ESCAPE).perform();
                            actions.sendKeys(Keys.ESCAPE).perform();
                            Thread.sleep(2000); // Wait for the download to complete
                        } catch (Exception e) {
                            logger.info("Error in additional information  patch for cell: " + cellName + ". Error: " + e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    logger.info("Error processing row ");
                }
            }
        } catch (Exception e) {
            logger.info("Error in additional information");
        }
        return flag;
    }















    /* public void downloadAllPatchesForEachCell(String tableXPath, String cellColumnXPath, String patchXPath, String patchContainerXPath, String downloadOptionXPath) {
        try {
            // Locate all rows in the table
            List<WebElement> rows = driver.findElements(By.xpath(tableXPath));
            Actions actions = new Actions(driver); // Initialize Actions

            for (WebElement row : rows) {
                try {
                    // Select the row
                    row.click();
                    Thread.sleep(2000); // Allow UI to update

                    // Get cell name and value
                    WebElement cellNameElement = row.findElement(By.xpath(cellColumnXPath));
                    String cellName = cellNameElement.getText();
                    String cellValue = row.findElement(By.xpath("./td[3]")).getText();

                    // Skip cells with no value ("-")
                    if (cellValue.equals("-")) {
                        System.out.println("Skipping cell with no value: " + cellName);
                        continue;
                    }
                    System.out.println("Processing cell: " + cellName);

                    // Scroll through patches container and load all patches
                    WebElement patchContainer = driver.findElement(By.xpath(patchContainerXPath));
                    String patchRowXPath="//div[@class='patches-section ']//div[@class='Item']";
                    loadAndSelectAllPatchesRowByRow(patchContainer,patchRowXPath, patchXPath);

                    // Right-click to open context menu
                    try {
                        WebElement firstPatch = driver.findElement(By.xpath("(" + patchXPath + ")[1]"));
                        actions.contextClick(firstPatch).perform(); // Right-click on the first patch
                        Thread.sleep(1000); // Wait for context menu to appear

                        // Locate and click the "Download" option
                        WebElement downloadOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(downloadOptionXPath)));
                        downloadOption.click();

                        System.out.println("Downloaded all patches for cell: " + cellName);

                    } catch (Exception e) {
                        System.out.println("Error downloading patches for cell: " + cellName + ". Error: " + e.getMessage());
                    }

                    // Optional: Delay after processing a cell
                    Thread.sleep(2000);

                } catch (Exception e) {
                    System.out.println("Error processing cell: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error in download process: " + e.getMessage());
        }
    }
    /**
     * Helper method to scroll through the container and load all patches.
     */
   /* private void loadAndSelectAllPatchesRowByRow(WebElement patchContainer, String patchRowXPath, String patchXPath) {
        try {
            int previousRowCount = 0;

            while (true) {
                // Locate all rows in the patch container
                List<WebElement> rows = driver.findElements(By.xpath(patchRowXPath));
                System.out.println("Total rows loaded: " + rows.size());

                // Break if no new rows are loaded
                if (rows.size() == previousRowCount) {
                    break;
                }

                // Process each row and select patches
                for (WebElement row : rows) {
                    try {
                        // Locate all patches in the current row
                        List<WebElement> patches = row.findElements(By.xpath(patchXPath));

                        for (WebElement patch : patches) {
                            try {
                                // Highlight the patch for debugging (optional)
                                ((JavascriptExecutor) driver).executeScript(
                                        "arguments[0].style.border='3px solid red'", patch);

                                // Click the patch
                                patch.click();
                                Thread.sleep(2000); // Small delay to avoid rapid clicks
                            } catch (Exception e) {
                                System.out.println("Error selecting patch: " + e.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error processing row: " + e.getMessage());
                    }
                }

                // Scroll the container incrementally to load the next set of rows
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += 200;", patchContainer);
                Thread.sleep(2000); // Wait for rows to load

                // Update row count
                previousRowCount = rows.size();
            }
        } catch (Exception e) {
            System.out.println("Error loading and selecting patches row by row: " + e.getMessage());
        }
    }*/
}
