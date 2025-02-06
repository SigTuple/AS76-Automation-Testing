package WBC.VerifyWbcParameters;

import java.text.DecimalFormat;
import java.util.*;

import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Data.Property;
import summary.WBCDCC;

public class WBCParameters extends CommonMethods {
	private final Logger logger = LogManager.getLogger(WBCParameters.class);
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties props;
	public WBCDCC wbcdcc;
	public Actions actions;

	public WBCParameters(WebDriver driver) throws Exception {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		props = Property.prop;
		Property.readWBCProperties();
		actions = new Actions(driver);
	}

	// verify the presence and selection of the WBC tab
	public String verifyPresenceAndSelectionOfWBCTab() {
		String WBCHeader = "";
		WebElement WBCHeaderText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("WBCHeader"))));
		if (WBCHeaderText.isDisplayed()) {
			WBCHeader = WBCHeaderText.getText();
		}
		logger.info(WBCHeader);
		return WBCHeader;

	}

	// verify the column present in the WBC tab
	public String verifyTheColumnPresentInTheWBCTab() {
		String ColumnHeader = "";
		List<WebElement> columnheaderXpath = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("columns"))));
		for (WebElement webElement : columnheaderXpath) {
			ColumnHeader = ColumnHeader + "," + webElement.getText();
		}
		logger.info(ColumnHeader);
		return ColumnHeader;
	}

	// verify the WBC cells names
	public String verifyWBCCells() {
		String cellNames = "";
		List<WebElement> columnheaderXpath = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("WBCCells"))));
		for (WebElement webElement : columnheaderXpath) {
			cellNames = cellNames + "," + webElement.getText();
		}
		logger.info(cellNames);
		return cellNames;
	}

	// verify the WBC cell counts is equal to the total count
	public boolean verifyTheWBCCellCountIsEqualToTotalCount() {
		int WBCCellCount = 0;
		List<WebElement> WBCcellsNameXpath = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("WBCcellsNameXpath"))));
		for (int i = 0; i < 25; i++) {
			String sub_classText = WBCcellsNameXpath.get(i).getAttribute("class");

			if (!sub_classText.contains("sub-row")) {
				WebElement WBCCountXpath = wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath(props.getProperty("WBCCounts") + (i + 1) + props.getProperty("WBCCounts2"))));
				String wbcCount = WBCCountXpath.getText();

				if (wbcCount.contains("-")) {
				} else {
					WBCCellCount = WBCCellCount + Integer.parseInt(wbcCount);
				}
			}
		}
		String Total = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("WBCTotalCount"))))
				.getText();
		int WBCTotalCount = Integer.parseInt(Total);
		if (WBCCellCount == WBCTotalCount) {

			logger.info("WBC cell counts are equals to the total count " + "Actual count: " + WBCCellCount
					+ " Expected total count: " + WBCTotalCount);
			return true;
		}
		logger.info("WBC cell counts are not equals to the total count " + "Actual count: " + WBCCellCount
				+ " Expected total count: " + WBCTotalCount);

		return false;

	}

	// verify * Manual sub-classification text message in the WBC tab
	public String verifyManualSubClassificationMessage() {
		String manualClassificationText = "";
		WebElement manualClassificationXpath = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(props.getProperty("manualClassificationXpath"))));
		if (manualClassificationXpath.isDisplayed()) {
			manualClassificationText = manualClassificationXpath.getText();
		}
		return manualClassificationText;

	}

	// Verify the functionality of NLR value calculation
	public boolean VerifyTheFunctionalityOfNLRValueCalculation() {

		WebElement neutrophilCountXpath = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("neutrophilCount"))));
		WebElement LymphocytesCountXpath = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("LymphocytesCount"))));
		WebElement NLRMouseHover = driver.findElement(By.xpath("//div[@class='nlr-tag ']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(NLRMouseHover).perform();
		WebElement actualNRLValueXpath = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("actualNRLValueXpath"))));

		String actualNRLValue = actualNRLValueXpath.getText();
		System.out.println("actualNRLValue value is " + actualNRLValue);

		if (neutrophilCountXpath.isDisplayed() && LymphocytesCountXpath.isDisplayed()) {

			int neutrophilCount = Integer.parseInt(neutrophilCountXpath.getText());
			int LymphocytesCount = Integer.parseInt(LymphocytesCountXpath.getText());
			if (LymphocytesCount > 0) {
				System.out.println("neutrophilCount value is " + neutrophilCount);
				System.out.println("LymphocytesCount value is " + LymphocytesCount);
				float value = (float) neutrophilCount / LymphocytesCount;
				System.out.println("Expected nlr value is " + value);
				DecimalFormat df = new DecimalFormat("#.#");
				String trimmedNumber = df.format(value);
				System.out.println("trimmedNumber value is " + trimmedNumber);
				String expected = "NLR = " + trimmedNumber;
				if (expected.equals(actualNRLValue)) {
					logger.info("NRL value is correct");
					return true;
				}

			} else {
				if (actualNRLValue.equals("NA")) {
					logger.info("NRL value is correct");
					return true;
				}
			}
		}
		return false;
	}
	public boolean verifyCountAfterReclassification(String countXPath, String cellNameXPath) throws InterruptedException {
		boolean flag = true;

		// Fetch count and cell name elements
		List<WebElement> countElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(countXPath))));
		List<WebElement> cellNameElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(cellNameXPath))));

		// Define cell types for classification
		String[] cellTypes = {"Neutrophils", "Lymphocytes", "Monocytes", "Basophils", "Eosinophils",
				"Atypical Cells/Blasts", "Immature Granulocytes", "Immature Eosinophils",
				"Immature Basophils", "Promonocytes", "Prolymphocytes", "Hairy Cells",
				"Sezary Cells", "Plasma Cells"};

		Map<String, List<String>> cellToSubCells = new HashMap<>();
		cellToSubCells.put("Neutrophils", Arrays.asList("Band Forms", "Hypersegmented","Neutrophils with Toxic Granules"));
		cellToSubCells.put("Lymphocytes", Arrays.asList("Reactive", "Large Granular Lymphocytes"));
		cellToSubCells.put("Atypical Cells/Blasts", Arrays.asList("Atypical Cells","Lymphoid Blasts","Myeloid Blasts"));
		cellToSubCells.put("Immature Granulocytes", Arrays.asList("Promyelocytes","Myelocytes","Metamyelocytes"));



		// Iterate through each cell
		for (int i = 0; i < countElements.size(); i++) {
			String actualCellName = cellNameElements.get(i).getText();
			String countText = countElements.get(i).getText();
			int initialCount = 0;

			// Parse the count
			try {
				if (!countText.isEmpty() && !countText.equals("-")) {
					initialCount = Integer.parseInt(countText);
				}
			} catch (NumberFormatException e) {
				logger.error("Error parsing count for cell: " + actualCellName);
				continue;
			}

			// Skip cells with invalid counts
			if (initialCount == 0 || initialCount == 1 || actualCellName.equals("Total")) {
				logger.info("Skipping cell: " + actualCellName + " with count: " + countText);
				continue;
			}

			// Perform classification for each cell type
			countElements.get(i).click();
			for (String cellType : cellTypes) {
				List<String> subCells = cellToSubCells.get(cellType); // Get sub-cells if present

				if (subCells != null) {
					for (String subCell : subCells) {
						boolean classified = retryClassification(cellType, subCell, 3); // Retry with sub-cell
						if (!classified) {
							logger.warn("Skipping further actions for cell type: " + cellType + " -> " + subCell + " due to repeated failures.");
							break; // Exit loop for this cell type
						}
					}
				} else {
					boolean classified = retryClassification(cellType, 3); // Retry without sub-cell
					if (!classified) {
						logger.warn("Skipping further actions for cell type: " + cellType + " due to repeated failures.");
						break; // Exit loop for this cell type
					}
				}
			}

			// Refresh count elements to get updated counts
			countElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(countXPath))));
			String updatedCountText = countElements.get(i).getText();
			int updatedCount;

			try {
				updatedCount = Integer.parseInt(updatedCountText);
			} catch (NumberFormatException e) {
				logger.error("Error parsing updated count for cell: " + actualCellName);
				continue;
			}

			// Validate the count
			if (updatedCountText.equals("-") && updatedCount != 1) {
				logger.info("Cell count has become '-' after reclassification for: " + actualCellName);
			} else if (updatedCount == initialCount) {
				logger.info("Reclassified with the same cell, count remains unchanged for: " + actualCellName);
			} else if (updatedCount < initialCount) {
				logger.info("Count successfully decreased for cell: " + actualCellName + ". Initial: " + initialCount + ", Updated: " + updatedCount);
			} else {
				logger.error("Count mismatch for cell: " + actualCellName + ". Initial: " + initialCount + ", Updated: " + updatedCount);
				flag = false;
			}
		}
		return flag;
	}

	public boolean classification(String cellType, String subCellType) {
		boolean flag = false;

		try {
			// Locate and right-click on the image
			WebElement imageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='patches-section ']//img")));
			actions.moveToElement(imageElement).contextClick(imageElement).perform();
			logger.info("Hovered and right-clicked on the image.");

			// Click the 'Classify' option using JavaScript
			WebElement classifyButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Classify')]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", classifyButton);
			logger.info("Clicked on 'Classify' option.");

			// Wait for the classification menu and select the cell type
			WebElement cellElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class,'MuiMenu-list')]//li//div[contains(text(),'" + cellType + "')]")));
			actions.moveToElement(cellElement).perform();

			// Handle sub-cells if present
			if (subCellType != null && !subCellType.isEmpty()) {
				WebElement subCellElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[contains(@class,'MuiMenu-list')])[3]//li[contains(text(),'" + subCellType + "')]")));
				actions.moveToElement(subCellElement).click().perform();
				logger.info("Selected sub-cell type: " + subCellType);
			} else {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", cellElement);
				logger.info("Selected cell type directly: " + cellType);
			}

			// Wait for the classification confirmation
			WebElement classificationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='body']")));
			logger.info("Classification completed: " + classificationMessage.getText());

			flag = true;
		} catch (Exception e) {
			logger.error("Error during classification for cell type: " + cellType + (subCellType != null ? " -> " + subCellType : ""));
		}

		return flag;
	}

	public boolean retryClassification(String cellType, int maxRetries) {
		return retryClassification(cellType, null, maxRetries);
	}

	public boolean retryClassification(String cellType, String subCellType, int maxRetries) {
		boolean success = false;
		int attempts = 0;

		while (attempts < maxRetries) {
			try {
				logger.info("Attempting classification for cell type: " + cellType + (subCellType != null ? " -> " + subCellType : "") + ". Attempt: " + (attempts + 1));
				success = classification(cellType, subCellType);
				if (success) {
					logger.info("Classification succeeded for cell type: " + cellType + (subCellType != null ? " -> " + subCellType : ""));
					break;
				}
			} catch (Exception e) {
				logger.error("Retry attempt failed for classification: " + e.getMessage());
			}
			attempts++;
			// Small delay before retrying
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		if (!success) {
			logger.error("Classification failed for cell type: " + cellType + (subCellType != null ? " -> " + subCellType : "") + " after " + maxRetries + " attempts.");
		}
		return success;
	}






	//classifying the wbc cell type to platelet clump and giant platelet

	public boolean wbcPatchToPlateletClump() throws InterruptedException {
		super.clickOnTab("Platelet", props.getProperty("platelet"));
		super.clickOnTab("Morphology",props.getProperty("Morpholog"));
		return verifyCountAfterReclassificationPlatelet("//div[contains(@class,'platelet-morph-row')]/div[2]","//div[contains(@class,'platelet-morph-row')]/div[1]");


	}


	public  boolean wbcPatchToLargePlatelet() throws InterruptedException {
		return verifyCountAfterReclassification("//div[@class='split-view-count-section']//following::button/following::div[contains(text(),'Count')]/following::div[10]","//*[@id='root']/div/div[2]/div[2]/div/div[1]/div/div[2]/div[3]/div[1]");
	}




	public boolean verifyCountAfterReclassificationPlatelet(String countXPath, String cellNameXPath) throws InterruptedException {
		boolean flag = true;

		// Fetch count and cell name elements
		List<WebElement> countElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(countXPath))));
		List<WebElement> cellNameElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(cellNameXPath))));

		// Define cell types for classification
		String[] cellTypes = {"Large Platelets","Platelet Clumps"};

		Map<String, List<String>> cellToSubCells = new HashMap<>();
		cellToSubCells.put("Neutrophils", Arrays.asList("Band Forms", "Hypersegmented","Neutrophils with Toxic Granules"));
		cellToSubCells.put("Lymphocytes", Arrays.asList("Reactive", "Large Granular Lymphocytes"));
		cellToSubCells.put("Atypical Cells/Blasts", Arrays.asList("Atypical Cells","Lymphoid Blasts","Myeloid Blasts"));
		cellToSubCells.put("Immature Granulocytes", Arrays.asList("Promyelocytes","Myelocytes","Metamyelocytes"));



		// Iterate through each cell
		for (int i = 0; i < countElements.size(); i++) {
			String actualCellName = cellNameElements.get(i).getText();
			String countText = countElements.get(i).getText();
			int initialCount = 0;

			// Parse the count
			try {
				if (!countText.isEmpty() && !countText.equals("-")) {
					initialCount = Integer.parseInt(countText);
				}
			} catch (NumberFormatException e) {
				logger.error("Error parsing count for cell: " + actualCellName);
				continue;
			}

			// Skip cells with invalid counts
			if (initialCount == 0 || initialCount == 1 || actualCellName.equals("Total")) {
				logger.info("Skipping cell: " + actualCellName + " with count: " + countText);
				continue;
			}

			// Perform classification for each cell type
			countElements.get(i).click();
			for (String cellType : cellTypes) {
				List<String> subCells = cellToSubCells.get(cellType); // Get sub-cells if present

				if (subCells != null) {
					for (String subCell : subCells) {
						boolean classified = retryClassification(cellType, subCell, 3); // Retry with sub-cell
						if (!classified) {
							logger.warn("Skipping further actions for cell type: " + cellType + " -> " + subCell + " due to repeated failures.");
							break; // Exit loop for this cell type
						}
					}
				} else {
					boolean classified = retryClassification(cellType, 3); // Retry without sub-cell
					if (!classified) {
						logger.warn("Skipping further actions for cell type: " + cellType + " due to repeated failures.");
						break; // Exit loop for this cell type
					}
				}
			}

			// Refresh count elements to get updated counts
			countElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath(countXPath))));
			String updatedCountText = countElements.get(i).getText();
			int updatedCount;

			try {
				updatedCount = Integer.parseInt(updatedCountText);
			} catch (NumberFormatException e) {
				logger.error("Error parsing updated count for cell: " + actualCellName);
				continue;
			}

			// Validate the count
			if (updatedCountText.equals("-") && updatedCount != 1) {
				logger.info("Cell count has become '-' after reclassification for: " + actualCellName);
			} else if (updatedCount == initialCount) {
				logger.info("Reclassified with the same cell, count remains unchanged for: " + actualCellName);
			} else if (updatedCount < initialCount) {
				logger.info("Count successfully decreased for cell: " + actualCellName + ". Initial: " + initialCount + ", Updated: " + updatedCount);
			} else {
				logger.error("Count mismatch for cell: " + actualCellName + ". Initial: " + initialCount + ", Updated: " + updatedCount);
				flag = false;
			}
		}
		return flag;
	}



























	}












