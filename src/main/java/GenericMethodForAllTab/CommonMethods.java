/**
 * 
 */
package GenericMethodForAllTab;


import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Data.Property;
import org.openqa.selenium.support.ui.WebDriverWait;




/**
 * @author rupakumari
 *
 */
public class CommonMethods {
	private final Logger logger = LogManager.getLogger(CommonMethods.class);

	private final WebDriver driver;
	public WebDriverWait wait;
	int time = 50;
	Properties props;


	public CommonMethods(WebDriver driver) throws Exception {
		this.driver = driver;
		wait = new WebDriverWait(driver, time);
		props = Property.prop;
		Property.readSessionManagement();
		Property.readCommonMethodProperties();
		Property.readRBCProperties();
	}
	//__________________ALL Tab Related common methods are written here________________________//

	// click on any tab

	public String clickOnTab(String tabName, String tabPath) throws InterruptedException {
		WebElement actualTabname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tabPath)));
		String  actualTabName=actualTabname.getText();
		if (actualTabname.isDisplayed() && actualTabName.contains(tabName) ){
			actualTabname.click();
			Thread.sleep(5000);
			logger.info("clicked on tab successfully");
		}

		return tabName;

	}

	// select any reports which are  'To be reviewed 'in status from list report

	public String openAnyReport(String reportStatus) throws InterruptedException {
		Thread.sleep(4000);
	boolean status=	openAReport(reportStatus);
	if(status){
		System.out.println("report is selected");

	}else {
		openAReport(reportStatus);
	}


		return reportStatus;
	}

	public boolean openAReport(String reportStatus) throws InterruptedException {
		boolean flag=false;
		List<WebElement> listOfRows = driver.findElements(By.xpath("//tbody//tr"));
		List<WebElement> listOfStatus = driver.findElements(By.xpath("//tbody//tr//td[6]//span"));
		System.out.println(listOfStatus.size());
		for (int i = 0; i < listOfStatus.size(); i++) {
			String reviewedStatus = listOfStatus.get(i).getText();
			System.out.println("Status found: " + reviewedStatus);
			// Scroll into view if needed
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", listOfStatus.get(i));
			Thread.sleep(2000); // Allow some time for scrolling
			if (reviewedStatus.contains(reportStatus)) {
				System.out.println("Matching status found: " + reviewedStatus);
				// Click the row
				listOfRows.get(i).click();
				flag=true;
				break;
			}
			else {

				System.out.println("report is not selected");
			}
		}

		return flag;
	}

//	else {
//				//click on ready for review button
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Ready for review')]"))).click();
//				List<WebElement>listOfStatusReports=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='report-type-popover']/div[3]/div/div/div/span")));
//				for (WebElement listOfAllStatus:listOfStatusReports) {
//					if (listOfAllStatus.getText().equals(reportStatus)) {
//						//	if(listOfAllStatus.getText().equals("Approve")|| listOfAllStatus.getText().equals("Rejected")){
//						// click on reviewed in drop-down if we need to select the approved and rejected report
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='report-type-popover']/div[3]/div/div[3]/div/span"))).click();
//						 List<WebElement>listOfApprovedReports = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='reportListingTable']/div/div/div/tbody/tr/td[6]/div/span")));
//						for (WebElement listOfApprovedReport : listOfApprovedReports) {
//							if (listOfApprovedReport.getText().equals(reportStatus)) {
//								listOfApprovedReports.get(i).click();
//								clickOnTab("RBC","//button[contains(text(),'RBC')]");
//								WebElement grade=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='root']/div/div[2]/div[2]/div/div/div[1]/div/div[1]/div[3]/div[1]/div[2]/div/span[1]/input")));
//								if(!grade.isEnabled()){
//									logger.info("grade fields are disabled , so we cannot regrade cell type");
//								}
//							}
//						}
//
//						// if we want to select the preparing status reports
//					} else if (listOfAllStatus.getText().equals("Preparing")) {
//						// click on preparing from drop-down list
//						wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='report-type-popover']/div[3]/div/div[2]/div/span"))).click();
//						List<WebElement>listOfPrepairingReports = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='reportListingTable']/div/div/div/tbody/tr/td[6]/div/span")));
//						for (WebElement listOfPreparingReport : listOfPrepairingReports) {
//							if (listOfPreparingReport.getText().equals("Processing")) {
//								System.out.println("processing is in progress, so we cannot open a report");
//
//							}
//						}
//					}
//				}


	// clicking on image setting icon and comparing the image size, brightness and contrast, saturation and hue
	public boolean clickOnImageSetting(String countXPath, String CellNameXpath, String tabName, String imageSettingToggle) throws InterruptedException {
		boolean flag = false;
		List<WebElement> count = driver.findElements(By.xpath(countXPath));
		List<WebElement> cellName = driver.findElements(By.xpath(CellNameXpath));

		for (int i = 0; i < count.size(); i++) {
			String actualCellName = cellName.get(i).getText();
				int cellActualCount = 0;
				try {
					cellActualCount = Integer.parseInt(count.get(i).getText());
				} catch (NumberFormatException e) {}

				if (!count.isEmpty() && !count.equals("-") && (cellActualCount != 0) && cellActualCount != 1 && (!actualCellName.equals("Total"))) {
					Thread.sleep(5000);
					cellName.get(i).click();
					Thread.sleep(5000);
					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class='image-settings']"))).click();
					Thread.sleep(2500);
					logger.info("Image setting is clicked on specific tab:" + tabName + "-" + actualCellName);
					WebElement listOfImageContainers = driver.findElement(By.xpath("//div[@class='img-utils-container']"));
					String imageTabDetails = listOfImageContainers.getText();
					if (imageTabDetails.equals("Image settings\n" +
							"Image Size\n" +
							"Small\n" +
							"Medium\n" +
							"Large\n" +
							"Brightness\n" +
							"-100\n" +
							"0\n" +
							"100\n" +
							"0\n" +
							"Contrast\n" +
							"-100\n" +
							"0\n" +
							"100\n" +
							"0\n" +
							"Hue\n" +
							"-100\n" +
							"0\n" +
							"100\n" +
							"0\n" +
							"Saturation\n" +
							"-100\n" +
							"0\n" +
							"100\n" +
							"0\n" +
							"Reset")) {
						logger.info("all the image setting content verified on Image setting tabs");
						WebElement image = driver.findElement(By.xpath("//img[@class='qa_patch_rank-1']"));
						WebElement imgSizeToggleBar = driver.findElement(By.xpath(imageSettingToggle));
						// Get the image size before clicking the toggle bar
						Dimension beforeSize = image.getSize();
						System.out.println("Before Size: " + beforeSize);
						Actions actions = new Actions(driver);
						actions.dragAndDropBy(imgSizeToggleBar, -80, 0).perform();
						Thread.sleep(3000);
						driver.findElement(By.xpath("//button[@class='reset-btn']")).click();
						Thread.sleep(3000);
						actions.dragAndDropBy(imgSizeToggleBar, 80, 0).perform();
						Thread.sleep(3000);
						// Get the image size after clicking the toggle bar
						Dimension afterSize = image.getSize();
						System.out.println("After Size: " + afterSize);

						// Verify that the image size has increased
						if (afterSize.height < beforeSize.height && afterSize.width < beforeSize.width) {
							System.out.println("Image size increased");
							flag = true;
						} else if (afterSize.height == beforeSize.height && afterSize.width == beforeSize.width) {
							System.out.println("images are in actual size");
							flag = false;
						}

						driver.findElement(By.xpath("//button[@class='reset-btn']")).click();
						Thread.sleep(4000);
						driver.findElement(By.xpath("//html/body/div[2]/div[3]/div/div[2]/button")).click();

					}

				}


			}


		return flag;
	}





		// verify sigtuple logo with AS76 text
		public boolean verifySigtupleLogo(String tabName) {
			WebElement sigtupleLogo = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("sigtupleLogoXpath"))));
			if (sigtupleLogo.isDisplayed()) {
				logger.info("Sigtuple logo is present in the " + tabName);

				return true;
			}
			return false;
		}

		// verify the presence of the approve and rejected report buttons
		public String verifyPresenceOfTheApproveAndRejectedButtons(String tabName) {
			String buttons_placeholder = "";
			WebElement rejectButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("rejectButtonXpath"))));
			WebElement approveButton = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approveButtonXpath"))));
			if (rejectButton.isDisplayed() && approveButton.isDisplayed()) {
				buttons_placeholder = rejectButton.getText() + "," + approveButton.getText();
				System.out.println(buttons_placeholder);
				logger.info("approve and rejected report buttons are present in " + tabName);
			}
			return buttons_placeholder;

		}

		// verify the containt present in the slide details page
		public String verifySlideDetailsInInfoPage(String tabName) throws InterruptedException {
			Thread.sleep(300);
			WebElement InfoIcon = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("infoIconXpath"))));
			String headers = "";
			if (InfoIcon.isDisplayed()) {
				InfoIcon.click();
				List<WebElement> details = wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("slideDetailsXpath"))));
				for (WebElement detail : details) {
					headers = headers + detail.getText() + ",";
				}
				System.out.println(headers);
				WebElement Xbutton = wait
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("XbuttonXpath"))));
				Xbutton.click();
				logger.info("slide details are verified in the " + tabName);
			}
			return headers;
		}

		// verify the presence of the disclaimer
		public boolean verifyThePresenceOfTheDisclaimer(String tabName) {
			String disclaimer = "";
			List<WebElement> disclaimerXpaths = wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("disclaimerXpath"))));
			for (WebElement disclaimerXpath : disclaimerXpaths) {
				disclaimer = disclaimer + disclaimerXpath.getText();
			}
			if (disclaimer.equals(props.getProperty("DisclaimerText"))) {
				System.out.println(disclaimer);
				logger.info("Disclaimer is verified in the " + tabName);
				return true;
			}
			System.out.println(disclaimer);
			return false;

		}

		// verify the presence of the CBC report text and icon
		public boolean verifyPresenceOfCBCReportText(String tabName) {

			String CBCIconText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("cbcIconXpath"))))
					.getAttribute("alt");
			String CBCReportText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CBCReportTextXpath"))))
					.getText();
			System.out.println(CBCIconText);
			System.out.println(CBCReportText);
			if (CBCIconText.equals("icon-cbc-report") && CBCReportText.equals("CBC report")) {
				logger.info("references text and icon is present " + tabName);
				return true;
			}
			return false;

		}

		// click on the CBC report option
		public boolean clickOnCBCReportOption(String tabName) {

			WebElement CBCReportClick = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CBCReportTextXpath"))));
			if (CBCReportClick.isDisplayed()) {
				CBCReportClick.click();
				logger.info("CBC icon is selected " + tabName);
				return true;
			}
			return false;
		}

		// verify click on the back/ cross button in the CBC report
		public boolean clickOnCrossXIcon(String tabName) {

			WebElement clickOnXIcon = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CBCXButtonXpath"))));
			if (clickOnXIcon.isDisplayed()) {
				clickOnXIcon.click();
				logger.info("Cross icon is selected in " + tabName);
				return true;
			}
			return false;

		}

		// verify the presence of the reference text and icon
		public boolean verifyPresenceOfReferencesIcon(String tabName) {
			this.clickOnSpecificTab("WBC");
			String ReferenceIconText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReferenceIconXpath"))))
					.getAttribute("alt");
			String ReferenceText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReferenceTextXpath"))))
					.getText();
			System.out.println(ReferenceIconText);
			System.out.println(ReferenceText);
			if (ReferenceIconText.equals("icon-reference-image") && ReferenceText.equals("References")) {
				logger.info("refrence text and  icons are present in " + tabName);
				return true;
			}
			return false;

		}

		// click on the refrence option
		public boolean clickOnReferenceOption(String tabName) {

			WebElement ReferenceClick = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReferenceTextXpath"))));
			if (ReferenceClick.isDisplayed()) {
				ReferenceClick.click();
				ReferenceClick.click();
				logger.info("ReferenceOption option is selected in " + tabName);
				return true;
			}
			return false;
		}

		// verify click on the back/cross button in the reference
		public boolean clickOnXIconInReferesence(String tabName) {

			WebElement clickOnXIcon = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("XiconInRefrence"))));
			if (clickOnXIcon.isDisplayed()) {
				clickOnXIcon.click();
				logger.info("Cross icon is selected in " + tabName);
				this.clickOnSpecificTab("Summary");
				return true;
			}
			return false;

		}

		// verify presence of comment icon
		public boolean verifyPresenceOfaCommentIcon(String tabName) {
			String ReferenceIconText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CommentIconXpath"))))
					.getAttribute("alt");
			String ReferenceText = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CommentTextXpath"))))
					.getText();
			System.out.println(ReferenceIconText);
			System.out.println(ReferenceText);
			if (ReferenceIconText.equals("icon-comment-btn") && ReferenceText.equals("Comments")) {
				logger.info("comment text and  icons are present in " + tabName);
				return true;
			}
			return false;

		}

		// click on the comment option
		public boolean clickOnCommentOption(String tabName) {

			WebElement CommentClick = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CommentTextXpath"))));
			if (CommentClick.isDisplayed()) {
				CommentClick.click();
				logger.info("comment option is selected in " + tabName);
				return true;
			}
			return false;
		}

		// verify click on the back/cross button in the comment
		public boolean clickOnXIconInComment(String tabName) {

			WebElement clickOnXIcon = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("CBCXButtonXpath"))));
			if (clickOnXIcon.isDisplayed()) {
				clickOnXIcon.click();
				logger.info("Cross icon is selected in " + tabName);
				return true;
			}
			return false;

		}

		// click operation for tab main and sub tab
		public void clickOnSpecificTab(String tabName) {
			WebElement respectiveTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("commonTabXpath1") + tabName + props.getProperty("commonTabXpath2"))));
			if (respectiveTab.isDisplayed()) {
				respectiveTab.click();
				logger.info(tabName + " tab is selected");
			}

		}

		// click on the view tabs
		public void clickOnSpecificViewtab(String viewTabName) {
			viewTabName = viewTabName.toLowerCase();
			WebElement respectiveViewTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					props.getProperty("commonViewTabXpath1") + viewTabName + props.getProperty("commonViewTabXpath2"))));
			if (respectiveViewTab.isDisplayed()) {
				respectiveViewTab.click();
				System.out.println(viewTabName + " tab is selected");
			}

		}

		// back to list report
		public void clickOnBackTOListReportButton() {
			WebElement BackButton = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("BackToListReportButton"))));
			if (BackButton.isDisplayed()) {
				BackButton.click();
				logger.info("Back button is selected");
			}
		}

		// verify presence and fuctionaly of the kebab menu icon
		public boolean verifyPresenceOfKebabMenuIcon(String TabName) {
			WebElement KebabMenu = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("KebabMenuXpath"))));

			if (KebabMenu.isDisplayed()) {
				logger.info("Kebab menu present in the " + TabName);
				return true;
			}
			return false;

		}

		// verify selection and detail present in the kebab menu icon
		public boolean clickOnKebabMenu(String TabName) {
			boolean flag = false;

			WebElement KebabMenu = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("KebabMenuXpath"))));
			if (KebabMenu.isDisplayed()) {
				KebabMenu.click();

				WebElement KebabMenuDetails = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("KebabMenuDetails"))));
				System.out.println(KebabMenuDetails.getText());
				if (KebabMenuDetails.getText().equals("Switch to original report\n" + "Logout")) {

					flag = true;
					logger.info("Kebab Menu Details are verified in the " + TabName + " tab");

				}
			}
			return flag;

		}

		//serach report and open
		public void reportOpten(String reportID) {
			WebElement SearchField = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("searchField"))));
			if (SearchField.isDisplayed()) {
				SearchField.click();
				SearchField.sendKeys(reportID);
				WebElement selectReportFromSearchList = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(props.getProperty("selectReportFromSearchList"))));
				selectReportFromSearchList.click();
				WebElement report = driver.findElement(By.xpath(props.getProperty("firstReport")));
				report.click();
			}
		}

		//assign reviewer
		public boolean assignReviewer(String reviewerName) throws InterruptedException {

			WebElement AssignReviewerDropDown = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReviewDropDown"))));
			if (AssignReviewerDropDown.isDisplayed()) {
				AssignReviewerDropDown.click();
				List<WebElement> pageDetails = wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='root']/div/div[1]/div[1]/div[5]/div/ul/li")));
				String text = "";
				for (int i = 0; i < pageDetails.size(); i++) {
					text = text + "," + pageDetails.get(i).getText();
					if (pageDetails.get(i).getText().equals(reviewerName)) {
						pageDetails.get(i).click();
						try {
							WebElement reAssignButton = wait.until(ExpectedConditions
									.visibilityOfElementLocated(By.xpath(props.getProperty("reassignButton"))));
							reAssignButton.click();
						} catch (Exception e) {
							System.out.println("expection is " + e);
						}
						break;
					}
				}
			}
			WebElement reviewerList = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ReviewList"))));
			String reviwerName=reviewerList.getAttribute("value");
			if(reviwerName.equals(reviewerName))
			{
				System.out.println("assigned reviwer is:"+reviwerName);
				logger.info("assigned reviwer is:"+reviwerName);
				return true;
			}
			System.out.println("assigned reviwer is:"+reviwerName);
			logger.info("assigned reviwer is:"+reviwerName);
			return false;
		}

	public void selectSpecificStatus(String Status) throws InterruptedException {
		Thread.sleep(3000);
		WebElement statusDropdowm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("statusdropdowm"))));
		statusDropdowm.click();
		List<WebElement> listOfDD = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("statusDDList"))));
		for ( WebElement option:listOfDD){
			if(option.getText().equals(Status))
			{
				option.click();
			}
		}

	}







	// verify the image zoom-in and zoom-out and home zoom functionality
	public String verifyTheFunctionalityOfZoomIn_And_ZoomOut_Using_UI_icon(String btn_xpath, int steps) throws InterruptedException, IOException {
		int count = 0;
		WebElement zoom_in = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(btn_xpath)));
		String zoom_level = null;

		for (int i = 1; i <= steps; i++) {
			count += 1;
			zoom_in.click();
			Thread.sleep(1000);
			zoom_level = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scale_box")))).getText();
			System.out.println("Zoom level at step " + i + " is: " + zoom_level);

			if (count == 2) {
				// Call verifyTheFunctionalityOf_Home_zoom() when count == 2
				String homeZoomLevel = verifyTheFunctionalityOf_Home_zoom();
				logger.info("Home zoom level after count == 2: " + homeZoomLevel);
			}

			if (count == steps) {
				logger.info("Zoom in count is equal to steps");
			}
		}

		return zoom_level;
	}


	// verify the home-zoom /reset functionality if patches are available


	public String verifyTheFunctionalityOf_Home_zoom() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("home_zoom")))).click();
		Thread.sleep(2000);
		String zoom_lvl = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scale_box")))).getText();
		System.out.println("home zoom level is:" + zoom_lvl);

		return zoom_lvl;
	}


	public boolean splitViewIcon(String cellName) throws InterruptedException {
		WebElement splitView = driver.findElement(By.xpath(props.getProperty("splitview")));
		if (splitView.isDisplayed() && "split view".equals(splitView.getAttribute("alt"))) {
			splitView.click();
			logger.info("Split view icon clicked on: " + cellName);
			Thread.sleep(5000);
			return true;
		}
		return false;
	}


}



















