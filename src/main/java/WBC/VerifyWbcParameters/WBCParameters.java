package WBC.VerifyWbcParameters;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	// verify the count value of cells after reclassification of any cells

	public boolean verifyCountAfterReclassification(String countXPath, String CellNameXpath) throws InterruptedException {
		boolean flag = false;
		List<WebElement> count = driver.findElements(By.xpath(countXPath));
		List<WebElement> cellName = driver.findElements(By.xpath(CellNameXpath));
		for (int i = 0; i < count.size(); i++) {
			int cellActualCount = 0;
			try {
				String countText = count.get(i).getText();
				if (!countText.isEmpty() && !countText.equals("-")) {
					cellActualCount = Integer.parseInt(countText);
				}
			} catch (NumberFormatException e) {
				String actualCellName = "";
				System.out.println("Error parsing count for cell: " + actualCellName);
			}
			String actualCellName = cellName.get(i).getText();


			if (!count.isEmpty() && !count.equals("-") && (cellActualCount != 0)  && (cellActualCount != 1) && (!actualCellName.equals("Total"))) {
				Thread.sleep(5000);
				count.get(i).click();
				String[] cellTypes = {"Neutrophils", "Lymphocytes", "Monocytes", "Basophils", "Eosinophils",
						"Atypical Cells/Blasts", "Immature Granulocytes", "Immature Eosinophils",
						"Immature Basophils", "Promonocytes", "Prolymphocytes", "Hairy Cells",
						"Sezary Cells", "Plasma Cells"};

				List<WebElement> countElements = null;
				for (String cellType : cellTypes) {
					this.classification(cellType);
				}

					// Refresh count element reference to get the updated count after classification
					countElements = driver.findElements(By.xpath(countXPath));
					try {
						String updatedCountText = countElements.get(i).getText();
						int updatedCount=Integer.parseInt(updatedCountText);
						System.out.println(updatedCount);
						String cellType = null;
						if (updatedCountText.equals("-")&& (updatedCount!=1)) {
							System.out.println("Cell count has become '-' after classifying: " + cellType + " for cell: " + actualCellName);
							//break;
						}
						else if (updatedCountText.equals(String.valueOf(cellActualCount))) {
						System.out.println("reclassified with same cell so updated and actual count will be the same");
						flag=true;

					}
						// Verify count has decreased by 1
						else if (updatedCount<cellActualCount) {
							System.out.println("Count is verified for cell: " + actualCellName + " after classifying: " + cellType);
							 flag=true;

						}else {
							System.out.println("count mismatch");
							flag=false;
						}
					} catch (NumberFormatException ignored) {
					}



			} else {
				System.out.println("Cell count is zero or hyphen, so we cannot classify: " + actualCellName);
				flag = true;
			}
		}
		return flag;
	}










	public boolean classification(String cellType) throws InterruptedException {
			boolean flag = false;
			 Thread.sleep(5000);
				actions.moveToElement(driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/div[1]/div[1]/div/img"))).build().perform();
				Thread.sleep(1500);
				actions.contextClick(driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/div[1]/div[1]/div/img"))).perform();
				Thread.sleep(1500);
				WebElement classify = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Classify')]")));
				actions.moveToElement(classify).click().perform();
				Thread.sleep(3000);

		    WebElement cell = driver.findElement(By.xpath("//ul[contains(@class,'MuiMenu-list')]//li//div[contains(text(),'" + cellType + "')]"));

				//List<WebElement> popUpCells = driver.findElements(By.xpath("/html/body/div[3]/div[3]/ul/li/div"));
				//for (WebElement popUpCell : popUpCells) {
					String popUpCellName = cell.getText();
					System.out.println(popUpCellName);
					if (popUpCellName.contains("Neutrophils") && popUpCellName.contains("Lymphocytes")&& popUpCellName.contains(" Atypical Cells/Blasts")&& popUpCellName.contains("Immature Granulocytes")) {
						List<WebElement> subCells = driver.findElements(By.xpath("/html/body/div[4]/div[2]/ul/li"));
						if (subCells.size()>0) {
							actions.moveToElement(cell).build().perform();
							Thread.sleep(1000);
							actions.moveToElement(subCells.get(0)).click().perform();
							Thread.sleep(4000);
							flag=true;
						}

					} else {
						//WebElement cell = driver.findElement(By.xpath("//ul[contains(@class,'MuiMenu-list')]//li//div[contains(text(),'" + cellType + "')]"));
						//System.out.println(cell.getText());
						actions.moveToElement(cell).click().perform();
						Thread.sleep(3000);
						flag=true;
					}
					WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='body']")));
					String actualText = text.getText();
					System.out.println("Classification Text: " + actualText);

					WebElement msgText = driver.findElement(By.xpath("//div[@class='header']"));
					String actualMsgText = msgText.getText();
					System.out.println("Classification Message: " + actualMsgText);

					//flag = true;
					//break;
				//}

			return flag;
		}






		//classifying the wbc cell type to platelet clump and giant platelet

	public boolean wbcPatchToPlateletClump() throws InterruptedException {
		super.clickOnTab("Platelet", props.getProperty("platelet"));
		super.clickOnTab("Morphology",props.getProperty("Morpholog"));
		return verifyCountAfterReclassification("//div[@class='split-view-count-section']//following::button/following::div[contains(text(),'Count')]/following::div[4]","/html/body/div/div/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div[contains(text(),'Large Platelets')]");


	}


	public  boolean wbcPatchToLargePlatelet() throws InterruptedException {
		return verifyCountAfterReclassification("//div[@class='split-view-count-section']//following::button/following::div[contains(text(),'Count')]/following::div[10]","//*[@id='root']/div/div[2]/div[2]/div/div[1]/div/div[2]/div[3]/div[1]");
	}

























	}












