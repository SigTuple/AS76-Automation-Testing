package ReportViewer;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class ReportViewer extends CommonMethods {

    private final Logger logger = LogManager.getLogger(ReportViewer.class);
    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;
    public Actions actions;

    public ReportViewer(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readReportViewerProperties();
        Property.readRBCProperties();
        actions = new Actions(driver);
    }

    //----------------------------------Report viewer script---------------------------------------------//



    // verify the presence of slide icon on the Assigned report
    public boolean presenceOfSlideIconOnAssignedReport(String tab) throws InterruptedException {
        boolean flag = false;
        WebElement assignedToInput = driver.findElement(By.id("assigned_to"));
        String assignedToValue = assignedToInput.getAttribute("value");
        if (!assignedToValue.isEmpty()) {
            System.out.println("Report is assigned to: " + assignedToValue);
        } else {
            System.out.println("Report is not assigned.");
        }
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tab))).click();
        String slideIdOnSummaryTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("SampleId")))).getText();
        System.out.println(slideIdOnSummaryTab);
        WebElement slideDetailsIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("slideDetailsIcon"))));
        if (slideDetailsIcon.isDisplayed() && slideDetailsIcon.isEnabled()) {
            slideDetailsIcon.click();
            logger.info("slide details icon is clicked successfully");
        }
        String slideIdOnSlideDetailsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("SlideIdOnSlideDetailTab")))).getText();
        System.out.println(slideIdOnSlideDetailsTab);
        if (slideIdOnSummaryTab.equals(slideIdOnSlideDetailsTab)) {
            logger.info("same slide id is present on slide details tab");
            flag = true;
        } else {
            logger.info("Matching slide id is not found on the tab" +tab);
        }

        WebElement statusOnPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("reportStatusOnSlideDetailsPopUp"))));
        String actualStatusOnPopUp = statusOnPopUp.getText();

        if ((assignedToValue.isEmpty()) && actualStatusOnPopUp.contains("To be reviewed")) {
            logger.info("same status found on Slide Details pop up for Unassigned report");
            flag = true;
        } else if ((!assignedToValue.isEmpty()) && actualStatusOnPopUp.contains("Under review")) {
            logger.info("Under review status found for assigned report on slide details pop up");
            flag=true;
        } else  {
            WebElement  approvedBy=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ApprovedBy"))));
            String approvedReport=approvedBy.getText();
            System.out.println(approvedReport);
            if (approvedBy.isDisplayed()&& approvedReport.contains("Approved By:")&& actualStatusOnPopUp.contains("Approved")){
                logger.info("Approve status found for Approved Report ");
                flag=true;
            }else if(approvedReport.contains("Rejected By:")&& actualStatusOnPopUp.contains("Rejected")) {
                logger.info("Reject status found for rejected report");
                flag=true;
            }

        }

        return flag;
    }




       // after opening any status of report verifying same status on slide pop up
    public boolean presenceOfSameReportStatus() {
        boolean flag=false;
        WebElement assignedToInput = driver.findElement(By.id("assigned_to"));
        String assignedToValue = assignedToInput.getAttribute("value");
        if (!assignedToValue.isEmpty()) {
            System.out.println("Report is assigned to: " + assignedToValue);
        } else {
            System.out.println("Report is not assigned.");
        }

        WebElement statusOnPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("reportStatusOnSlideDetailsPopUp"))));
        String actualStatusOnPopUp = statusOnPopUp.getText();

        if ((assignedToValue.isEmpty()) && actualStatusOnPopUp.contains("To be reviewed")) {
            logger.info("same status found on Slide Details pop up for Unassigned report");
            flag = true;
        } else if ((!assignedToValue.isEmpty()) && actualStatusOnPopUp.contains("Under review")) {
            logger.info("Under review status found for assigned report on slide details pop up");
        } else {
            WebElement  approvedBy=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("ApprovedBy"))));
            String approvedReport=approvedBy.getText();
            System.out.println(approvedReport);
            if (approvedBy.isDisplayed()&& approvedReport.contains("Approved By:")&& actualStatusOnPopUp.contains("Approved")){
                logger.info("Approve status found for Approved Report ");
                flag=true;
            }else if(approvedReport.contains("Rejected By:")&& actualStatusOnPopUp.contains("Rejected")) {
                logger.info("Reject status found for rejected report");
                flag=true;
            }

        }
        return flag;
    }

// verify the presence of slide image area
    public boolean presenceOfSlideImageArea() {
        boolean flag = false;
        WebElement slideImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("slideImage"))));
        String actualText = slideImage.getText();
        WebElement imageArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("imageArea"))));
        if (imageArea.isDisplayed() && actualText.contains("Slide image")) {
            logger.info("slide image text and image is present on slide details pop up ");
            flag = true;
        } else {
            logger.info("not found slide image area and slide image text");
        }
        return flag;
    }
    // verify the presence of scan time text

    public String scanTime(){
      return   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("scantime")))).getText();
    }


    // verify the presence of scanned By text

    public String scannedBy(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("scannedBy")))).getText();
    }


    // verify the presence of scanned on text

    public String scannedOn(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("scannedOn")))).getText();
    }


     // close the pop up
    public Object closePopUp() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("close")))).click();

        return null;
    }


    // verifying the Approval and reject buttons are present when clicked on image setting icon

    public boolean imageSetting(String tab) throws InterruptedException {
        boolean flag=false;
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tab))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("imageSetting")))).click();
        WebElement approveButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("approveButton"))));
        WebElement rejectButton=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("rejectReport"))));
        String approve=approveButton.getText();
        String reject=rejectButton.getText();
        if(approveButton.isDisplayed() && approve.contains("Approve report")&& rejectButton.isDisplayed()&& reject.contains("Reject report")){
            logger.info("approve and reject buttons are displayed and its enabled");
            flag=true;
        }

        driver.findElement(By.xpath(props.getProperty("closeImgSetting"))).click();

        return flag;
    }




    // verifying the image size characteristic for any cell type
    public boolean verifyImageCharacteristicsParameters(String countXPath, String CellNameXpath, String tabName, String imageSettingToggle) throws InterruptedException {
        boolean flag = false;
       WebElement  count = driver.findElement(By.xpath(countXPath));
       String cellActualCount=count.getText();
       int actualCount=Integer.parseInt(cellActualCount);

        WebElement cellName = driver.findElement(By.xpath(CellNameXpath));
        String actualCellName=cellName.getText();

            if (!cellActualCount.isEmpty() && !count.equals("-") && (actualCount != 0) && actualCount != 1 && (!actualCellName.equals("Total"))) {
                Thread.sleep(5000);
                cellName.click();
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
                    }

                    driver.findElement(By.xpath("//button[@class='reset-btn']")).click();
                    Thread.sleep(4000);
                    driver.findElement(By.xpath("//html/body/div[2]/div[3]/div/div[2]/button")).click();


                }

            }



        return flag;
    }


















        // verify presence of reference tool on microscopic tab

    public boolean presenceOfReferenceTool(String tab) {
        boolean status = false;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tab))).click();
            Thread.sleep(4000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("Microscopic_view_Icon")))).click();
            WebElement line_tools=driver.findElement(By.xpath(props.getProperty("line_tool")));
            String line_tool=line_tools.getAttribute("alt");
            System.out.println(line_tool);
            WebElement circle_tools=driver.findElement(By.xpath(props.getProperty("circle_tool")));
            String circle_tool=circle_tools.getAttribute("alt");
            System.out.println(circle_tool);
            WebElement zoom_tools=driver.findElement(By.xpath(props.getProperty("zoom_tool")));
            String zoom_tool= zoom_tools.getAttribute("alt");
            System.out.println(zoom_tool);

                if (line_tool.contains("line-tool") && circle_tool.contains("circle-tool") && zoom_tool.contains("zoom-tool")) {
                    status = true;
                    logger.info("Linear, circle, and zoom icons are present on microscopic view.");

                }

        } catch (Exception e) {
            logger.error("Error in referenceTool method: ", e);
        }

        return status;
    }



    // clicking on any tool type(Linear circle or zoom)
    public boolean selectionOfReferenceTool(String toolType){
        boolean flag=false;
       WebElement tooltype= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(toolType)));
        Actions actions1=new Actions(driver);
        actions1.moveToElement(tooltype).click().perform();
        String msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("digitalZoom")))).getText();
        WebElement checkBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("checkBox"))));
           String byDefaultValue=checkBox.getAttribute("value");
        System.out.println(byDefaultValue);
        if(msg.contains("Digital zoom only")&& byDefaultValue.equals("7")){
            logger.info("Digital zoom only text is display on tab after selecting the linear line tool");
            logger.info("by default value (7) is appeared after clicking on linear/ circle line");
            flag=true;
        }else {
            System.out.println("zoom line is selected");
        }
        return flag;
    }




// altering the size of reference tool on microscopic view tab
    public boolean alteringTheSizeOfLineOrCircle(int value){
        boolean flag=false;
        WebElement checkBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("checkBox"))));
        checkBox.click();
        checkBox.clear();
        checkBox.sendKeys(String.valueOf(value));
        if(value<5 || value >20){
            WebElement colourOfCheckBox=driver.findElement(By.xpath(props.getProperty("checkBoxColor")));
            String color=colourOfCheckBox.getCssValue("back-ground");
            System.out.println(color);
            if(color.equals("")){
                logger.info("check box outline is highlighted with red color");
                flag=true;
            }else {
                logger.info("check box outline is highlighted with blue color");
                flag=true;
            }


        }


        return flag;
    }

// zoom level is verifying with 40x and 100x
    public boolean alteringTheZoomLevel() {
        boolean flag = false;

        List<WebElement> zoomFovsOption = driver.findElements(By.xpath(props.getProperty("ZoomOptions")));

        for (WebElement zoomOption : zoomFovsOption) {
            zoomOption.click();
            String presenceOfZoom = driver.findElement(By.xpath(props.getProperty("40xZoom"))).getText();
            if (presenceOfZoom.equals("40x")) {
                logger.info("After selection of 40x zoom, FOV is displaying below the zoom icon.");
                flag = true;

                WebElement zoomLineTool = driver.findElement(By.xpath(props.getProperty("circleToolButton")));
                zoomLineTool.click();

            } else if (presenceOfZoom.equals("100x")) {
                logger.info("After selection of 100x zoom, FOV is displaying below the zoom icon.");
                flag = true;

            } else {
                logger.info("Zoom value not found: " + presenceOfZoom);
            }
            if (flag) {
                break;
            }
        }

        return flag;
    }






       // after alteration de select the reference tool
    public boolean deselectTheReferenceTool(String toolName){
        boolean flag=false;
        WebElement checkBox=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("checkBox"))));
        String byDefaultValue=checkBox.getAttribute("value");
        System.out.println(byDefaultValue);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(toolName))).click();
        if(!checkBox.isDisplayed()){
            logger.info("line/circle is deselected successfully"+toolName);
            flag=true;
        }else {
            System.out.println("zoom line is deselected successfully");
        }
        return flag ;
    }


      // if any cell check box is selected then deselecting and selecting all the un selected check box
    public boolean selectAndDeselectTheCellNameCheckBox(String tab){
        boolean flag=false;
        List<WebElement> listOfCellName=driver.findElements(By.xpath(props.getProperty("cellNameList")));
        List<WebElement> allCheckBoxes= driver.findElements(By.xpath(props.getProperty("cellNameCheckBox")));
        List<WebElement> listOfCount=driver.findElements(By.xpath(props.getProperty("ListOfCount")));
        for(int i=0;i<=allCheckBoxes.size()-1;i++){
            String  count=listOfCount.get(i).getText();
            int actualCount=Integer.parseInt(count);
            if(actualCount>=1) {
                allCheckBoxes.get(i).click();
                flag= true;
            }
            String statusOfCheckBox=allCheckBoxes.get(i).getAttribute("value");
            if(statusOfCheckBox.equals("true")){
                logger.info("after clicking on checkbox status as 'true' found in html code");
                flag=true;

            }else  if(statusOfCheckBox.equals("false")){
               logger.info("check box is by default selected for cell name on " +tab  +listOfCellName.get(0).getText());
               flag=true;

               }else {
                logger.info("not found any status value of check box");
            }


            }



        return flag;
    }





}
