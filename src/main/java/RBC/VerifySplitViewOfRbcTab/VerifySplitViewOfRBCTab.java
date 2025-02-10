package RBC.VerifySplitViewOfRbcTab;

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
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class VerifySplitViewOfRBCTab extends CommonMethods {
    private final Logger logger = LogManager.getLogger(VerifySplitViewOfRBCTab.class);

    public WebDriver driver;
    public WebDriverWait wait;
    Properties props;
    public CommonMethods cms;
    private final VerifyUiOfRbcSizeSubTab rbcSizeSubTab;

    public VerifySplitViewOfRBCTab(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readRBCProperties();
        cms = new CommonMethods(driver);
        rbcSizeSubTab = new VerifyUiOfRbcSizeSubTab(driver);
    }
    //_______________________________split view for rbc tab___________________//

    // verify the split view icon is available for RBC size, shape ,colour and Inclusion tab



    //  Verify the UI of the split view.
    public boolean uIOfSplitView() {
        boolean flag = false;
        WebElement canvas = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("canvas"))));
        WebElement zoom_in_icon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("zoom-in"))));
        WebElement zoom_out = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("zoom-out"))));
        WebElement overViewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("overview"))));
        if (canvas.isDisplayed() && (zoom_in_icon.isDisplayed() && zoom_out.isDisplayed()) && overViewButton.isDisplayed()) {
            flag = true;
            logger.info("split view screen with a microscopic view is displayed.");
            logger.info("zoom-in and zoom-out icon is available on microscopic view");
        }


        return flag;
    }


    private void clickOnCell(WebElement cell, String cellName) {
        cell.click();
        logger.info("Clicked on cell: " + cellName);
    }

    // verify the presence of split view for all the cells in RBC tab
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

    // verify the patches and available for all the cells in RBC tab

    private boolean checkPatchesAndPreview(String cellName) throws InterruptedException, IOException {
        boolean flag;
        if (Arrays.asList("Anisocytosis", "Poikilocytosis", "Howell-Jolly Bodies*", "Acanthocytes*", "Sickle cells*", "Hypochromic*", "Polychromtic*", "Pappenheimer Bodies*", "Basophilic Stippling*").contains(cellName)) {
            flag = driver.findElement(By.xpath(props.getProperty("nopatchavailable"))).getText().equals("No patches available")
                    && driver.findElement(By.xpath(props.getProperty("nopreview"))).getText().equals("No preview");
            System.out.println("Patches are not available for manual regraded cells: " + cellName);
        } else {
            System.out.println("Patches are available for cells: " + cellName);
            flag = true;

            // Verify the UI of the split view if patches are available
            if (uIOfSplitView()) {
                logger.info("UI of split view verified successfully for cell: " + cellName);
                verifyTheFunctionalityOfZoomIn_And_ZoomOut_Using_UI_icon("//div[@class='review-nav__selection__view']//img[2]", 7);

            } else {
                logger.warn("UI of split view verification failed for cell: " + cellName);
            }

        }
        return flag;
    }


    // availability of split view and zoom-in & zoom-out functionality if patches are available

    public boolean presenceOfSplitView(String tabPath, String tabName) throws InterruptedException {
        boolean flag = false;

        clickOnTab(tabPath, tabName);

        List<WebElement> cellNames = driver.findElements(By.xpath(props.getProperty("cellName")));
        List<WebElement> listOfGradePercent = driver.findElements(By.xpath(props.getProperty("gradePercent")));

        for (int i = 0; i < listOfGradePercent.size(); i++) {
            String actualGradePercent = listOfGradePercent.get(i).getText();

            try {
                double actualPercentageOfGrade = Double.parseDouble(actualGradePercent);
                System.out.println(actualPercentageOfGrade);
                if (actualPercentageOfGrade > 0.0 && !actualGradePercent.equals("")) {
                    String cellName = cellNames.get(i).getText();
                    cellNames.get(i).click();
                    flag = true;
                    logger.info("Clicked on cell: " + cellName);
                    // verify the Ui of split view , if patches are available on respective cell
                    if (splitViewIcon(cellName)) {
                        flag = checkPatchesAndPreview(cellName);
                    }
                } else {
                    System.out.println("Grade values of respective cell are 0.0 or empty, so we can't click on: " + cellNames.get(i).getText());
                    flag = true;
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Manually graded value for cell: " + cellNames.get(i).getText());
                flag = true;
            }
        }

        return flag;
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


    // verifying the manually graded cells are not shown on microscopic view tab

    public boolean manuallyGradedCellsNotPresentOnMicroscopicView(String tabPath,String btn_xpath) throws InterruptedException {
        boolean flag = false;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tabPath))).click();
        Map <Character, Integer> cellNameAndPosition = new HashMap<Character, Integer>();
        List<WebElement> cellNames = driver.findElements(By.xpath(props.getProperty("cellName")));
        String name = null;
        for (int i = 0; i < cellNames.size(); i++) {
            WebElement cellName = cellNames.get(i);
            name = cellName.getText();
            if (name != null && !name.isEmpty()) {
                Character firstChar = name.charAt(0);
                cellNameAndPosition.put(firstChar, i);
                System.out.println("CellName: " + name + "==" + i);
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath))).click();
        Thread.sleep(4000);
        List<WebElement> cellNameOnMicroscopicView = driver.findElements(By.xpath(props.getProperty("cellNameOnMicroscopicView")));
        for (WebElement cellsNameOnMicroscopicView : cellNameOnMicroscopicView) {
            String actualCell=cellsNameOnMicroscopicView.getText();
            System.out.println(actualCell);
        }
        if (!"Acanthocytes".equals(name)) {
            System.out.println("manually graded cells are not present on microscopic view");
            flag = true;

        } if (!"Anisocytosis".equals(name) && !"Sickle cells*".equals(name) && !"Howell-Jolly Bodies*".equals(name)) {
            System.out.println("Manually graded cells are not present on microscopic view Shape cell list");
            flag = true;
        }if(!"Hypochromic Cells".equals(name) && !"Polychromatic Cells".equals(name)){
            System.out.println(" manually graded cell are not present on  microscopic cell list on color tab");
            flag=true;
        }


        return flag;
    }




    // verifying the same patches are present on split view & patch view

        public boolean samePatchOnSplitAndPatchView(String tabPath) throws InterruptedException, IOException {

            boolean status = false;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tabPath))).click();
            List<WebElement> cellNames = driver.findElements(By.xpath(props.getProperty("cellName")));
            List<WebElement> listOfGradePercent = driver.findElements(By.xpath(props.getProperty("gradePercent")));

            for (int i = 0; i < listOfGradePercent.size(); i++) {
                String actualGradePercent = listOfGradePercent.get(i).getText();

                double actualPercentageOfGrade = 0;
                try {
                    actualPercentageOfGrade = Double.parseDouble(actualGradePercent);
                    System.out.println(actualPercentageOfGrade);
                } catch (Exception e) {
                }
                String cellName = null;
                if (actualPercentageOfGrade >=0.0 && !actualGradePercent.equals("")) {
                    cellName = cellNames.get(i).getText();
                    cellNames.get(i).click();
                    logger.info("Clicked on cell: " + cellName);

                        if (Arrays.asList("Anisocytosis", "Poikilocytosis", "Howell-Jolly Bodies*", "Acanthocytes*", "Sickle cells*", "Hypochromic Cells", "Polychromatic Cells", "Pappenheimer Bodies*", "Basophilic Stippling*").contains(cellName)) {
                            status = driver.findElement(By.xpath(props.getProperty("nopatchavailable"))).getText().equals("No patches available")
                                    && driver.findElement(By.xpath(props.getProperty("nopreview"))).getText().equals("No preview");
                        System.out.println("patches are not available so we can't verify the same patch view");
                    } else if (Arrays.asList("Anisocytosis", "Poikilocytosis", "Howell-Jolly Bodies*", "Acanthocytes*", "Sickle cells*", "Hypochromic Cells", "Polychromatic Cells", "Pappenheimer Bodies*", "Basophilic Stippling*").contains(cellName) && actualPercentageOfGrade > 0.0) {
                            driver.findElement(By.xpath(props.getProperty("patchView"))).click();
                            Thread.sleep(3000);

                            status = verifyPatchRanks();
                            // Click on the split view icon
                            driver.findElement(By.xpath(props.getProperty("splitview"))).click();
                            Thread.sleep(3000);
                            // Call the same method to verify the patch ranks again
                            boolean status1 = verifyPatchRanks();
                            if (status == status1) {
                                System.out.println("same patches are available on patch view and split view ");
                            }
                            status=true;
                        } else {


                       // click on patch view icon
                        driver.findElement(By.xpath(props.getProperty("patchView"))).click();
                        Thread.sleep(3000);

                        status = verifyPatchRanks();
                        // Click on the split view icon
                        driver.findElement(By.xpath(props.getProperty("splitview"))).click();
                        Thread.sleep(3000);
                        // Call the same method to verify the patch ranks again
                        boolean status1 = verifyPatchRanks();
                        if (status == status1) {
                            System.out.println("same patches are available on patch view and split view ");
                        }
                    }

                }else {
                    System.out.println("patches are not available so cant verify the same patch on split and patch");
                    status=true;
                }
            }
            return status;
        }




    private boolean verifyPatchRanks() throws InterruptedException {
        boolean status = false;
        String firstImage="/html/body/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[1]/div[1]/div";
        String secondImage="/html/body/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[1]/div[2]/div";
        String thirdImage="/html/body/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[1]/div[3]/div";
        String fourthImage="/html/body/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/div[1]/div/div/div/div[1]/div[4]/div";
        String firstPatch = "//img[@class='qa_patch_rank-1']";
        String secondPatch = "//img[@class='qa_patch_rank-2']";
        String thirdPatch = "//img[@class='qa_patch_rank-3']";
        String fourthPatch = "//img[@class='qa_patch_rank-4']";
        String[] patchesCount = {firstPatch, secondPatch, thirdPatch, fourthPatch};
        String [] imagePathForClick={firstImage,secondImage,thirdImage,fourthImage};
        int i = 0;
        List<WebElement> patches = driver.findElements(By.xpath(patchesCount[i]));

        for (i = 0; i < patches.size(); i++) {
            driver.findElement(By.xpath(imagePathForClick[i])).click();
            Thread.sleep(3000);
            String actualPatchRank = patches.get(i).getAttribute("class");
            System.out.println(actualPatchRank);
            if(actualPatchRank.equals("qa_patch_rank-1"))
            status = true;
        }


        return status;
    }



















































}

















