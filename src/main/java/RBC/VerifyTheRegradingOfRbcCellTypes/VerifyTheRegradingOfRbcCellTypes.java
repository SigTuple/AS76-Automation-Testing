package RBC.VerifyTheRegradingOfRbcCellTypes;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class VerifyTheRegradingOfRbcCellTypes extends CommonMethods {

    private final Logger logger = LogManager.getLogger(VerifyTheRegradingOfRbcCellTypes.class);

    public static WebDriver driver;
    public static WebDriverWait wait;
    static Properties props;
    public CommonMethods cms;

    public VerifyTheRegradingOfRbcCellTypes(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, time);
        props = Property.prop;
        Property.readRBCProperties();
        Property.readCommonMethodProperties();
        cms = new CommonMethods(driver);

    }
    //_______________________________Verifying The Regrading Of Rbc Cell Types____________________________//


    // Verify that the user is able to regrade cell types present in the RBC size, shape and color tab.

    public boolean rbcRegrading(String cellPath, String gradePath) throws InterruptedException {
        boolean flag = false;
        boolean flag1=false;

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("patchView")))).click();
        Thread.sleep(4000);

        // Locate the cell name element
        WebElement cellNames = driver.findElement(By.xpath(cellPath));
        cellNames.click();
        String actualCellName = cellNames.getText().trim();

        // Locate all grade elements
        List<WebElement> grades = driver.findElements(By.xpath(gradePath));

        // Iterate through the list of radio buttons and check which one is selected
        String selectedValue = null;
        for (WebElement grade : grades) {
            if (grade.isSelected()) {
                selectedValue = grade.getAttribute("value").trim();  // Trim the selected value
                System.out.println("Selected grade value for cell " + actualCellName + " is " + selectedValue);
                break; // Exit loop once the selected radio button is found
            }
        }

        // Click on all other grade values one by one and verify the pop-up
        if (selectedValue != null) {
            for (WebElement grade : grades) {
                String currentGradeValue = grade.getAttribute("value").trim();  // Trim the current grade value

                if (!currentGradeValue.equals(selectedValue)) {
                    grade.click();
                    System.out.println("Clicked on grade value: " + currentGradeValue + " for cell " + actualCellName);

                    // Wait for the pop-up to appear
                    WebElement popupHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("popupMessage"))));

                    // Extract the text from the pop-up header
                    String popupText = popupHeader.getText().trim();  // Trim the pop-up text to remove extra spaces
                    System.out.println("Pop-up text: " + popupText);

                    // Correct the expected popup text with proper spacing
                    String expectedPopupText = actualCellName + " regraded from " + selectedValue + " to " + currentGradeValue;

                    // Verify that the pop-up shows the correct information
                    if (popupText.equals(expectedPopupText)) {
                        flag = true;
                        System.out.println("Pop-up verification passed for grade value: " + currentGradeValue);
                    } else {
                        System.out.println("Pop-up verification failed for grade value: " + currentGradeValue);
                        System.out.println("Expected: " + expectedPopupText);
                        System.out.println("Actual: " + popupText);
                    }

                    Thread.sleep(1000);
                }
            }
        } else {
             flag1 = manualGradingOFCell(cellPath);
            if (flag1) {
                System.out.println("Manual graded cells are regraded successfully.");
                flag = true;
                this.verifyUndoRegrading(cellPath);

            }
        }

        return flag;
    }


    //  Verify the manual grading of cells present in the RBC inclusions

    //  method to handle the manual grading condition where no grade is selected
    public  boolean manualGradingOFCell(String cellPath) {
        boolean flag=false;
        WebElement cellNames = driver.findElement(By.xpath(cellPath));
        String actualCellsName = cellNames.getText().replace("*", "");
        System.out.println(actualCellsName);
        List<WebElement> grades = driver.findElements(By.xpath(cellPath+"/following::div[1]//span/input"));
        for (WebElement grade : grades) {
            if (!grade.isSelected()) {
                grade.click();
                String currentGradeValue = grade.getAttribute("value").trim();

                System.out.println("Clicked on grade value: " + grade.getAttribute("value") + " for cell " + actualCellsName);
                // Wait for the pop-up to appear
                WebElement popupHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("popupMessage"))));

                // Extract the text from the pop-up header
                String popupText = popupHeader.getText().trim();
                System.out.println(popupText);

                String expectedPopupText = actualCellsName + " regraded from - to " + grade.getAttribute("value");
                String expectedPopUp= actualCellsName + " regraded from " + grade.getAttribute("value") + " to " + currentGradeValue;

                if (popupText.equals(expectedPopupText)) {
                    System.out.println("Pop-up verification passed for grade value 0: " + grade.getAttribute("value"));
                  flag=true;
                } else if (popupText.equals(expectedPopUp)) {
                    System.out.println("Pop-up verification passed for grade value 1 to 3: " + grade.getAttribute("value"));
                    flag=true;

                } else  {
                    System.out.println("Pop-up verification failed for grade value: " + grade.getAttribute("value"));

                }

            }
        }

        return flag;
    }

    public boolean verifyUndoRegrading(String cellPath){
        boolean status=false;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='reset-icon']"))).click();
        WebElement cellNames = driver.findElement(By.xpath(cellPath));
        String actualCellsName = cellNames.getText().replace("*", "");
        List<WebElement> grades = driver.findElements(By.xpath(cellPath+"/following::div[1]//span/input"));
        for (WebElement grade : grades) {
            if (!grade.isSelected()) {
                status=true;
                logger.info(actualCellsName + "is successfully reset the regarding values");
            }
        }
        return status;
    }


    //Verify the regrading functionality of all cell types for the Approved statuses of report.
    public boolean regradingFunctionalityOfDifferentStatuesReport(String statusOfReport) throws InterruptedException {
        return cms.openAReport(statusOfReport);

    }

    //Verify the calculation of % value of all cells present in the RBC tab.
    public boolean percentageValuesOfAllCellsInRbcTab(String cellPath) throws InterruptedException {
        boolean flag = false;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("patchView")))).click();
        // Locate all cell name elements
        WebElement cellNames = driver.findElement(By.xpath(cellPath));
        cellNames.click();
        String actualCellName = cellNames.getText();
        logger.info("clicked on cell " + actualCellName);
        double percentageOnPatchView = Double.parseDouble(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cellPath + "//following::div[3]"))).getText());
        System.out.println(percentageOnPatchView);

        // clicked om microscopic view icon
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("Microscopic_view_Icon")))).click();
        // click on same cell
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cellPath)));
        //   tr//*[contains(text(),'Macrocytes')]/following::td[1]
        String countOfCellOnMicroscopicViewtab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cellPath + "/following::td[1]"))).getText();
        int actualCount = Integer.parseInt(countOfCellOnMicroscopicViewtab);
        System.out.println("count of respective cell is = " + actualCount);
        // finding total count of RBC on note message
        WebElement noteMessage = driver.findElement(By.xpath("//div[@class='text']"));
        String fullText = noteMessage.getText();
        String totalCountOfRBC = fullText.replaceAll("[^0-9]", "");
        int totalCounts = Integer.parseInt(totalCountOfRBC);
        System.out.println("Total number of RBCs count is = " + totalCountOfRBC);

        // Calculate the percentage
        double percentage = ((double) actualCount / totalCounts) * 100; // Round the percentage to one decimal place
        // Format the percentage to one decimal place
        String formattedPercentage = String.format("%.1f", percentage);
        double roundedPercentage = Double.parseDouble(formattedPercentage);
        System.out.println("percentage found after number " + totalCounts + " is  divisible by " + actualCount + " = " + roundedPercentage);
        String percentageValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cellPath + "/following::td[1]/following::td[1]"))).getText();
        double actualPercentageValueOnMicroscopicVoew = Double.parseDouble(percentageValue);
        System.out.println(actualPercentageValueOnMicroscopicVoew);
        if (roundedPercentage == actualPercentageValueOnMicroscopicVoew && percentageOnPatchView == actualPercentageValueOnMicroscopicVoew && roundedPercentage == actualPercentageValueOnMicroscopicVoew) {
            System.out.println("percentage value is verified on cell " + actualCellName);
            flag = true;
        }
        return flag;
    }


    // Verify the correctness of the grade value according to the % present for all cell types present in the RBC tab.
    public boolean gradeValueAccordingToPercentage(String cellPath) {
        boolean flag = false;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(props.getProperty("patchView")))).click();
        WebElement cellNames = driver.findElement(By.xpath(cellPath));
        cellNames.click();
        String actualCellName = cellNames.getText();
        logger.info("clicked on cell " + actualCellName);

       // Fetch the grade percentage for the current cell
        double gradePercentage = Double.parseDouble(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cellPath + "//following::div[3]"))).getText());
        System.out.println("Grade percentage for cell " + actualCellName + ": " + gradePercentage);
        WebElement colorCodeForSignificantOrNOnSigni=driver.findElement(By.xpath(cellPath+"//preceding-sibling::div"));
        // Check for green color (non-significant) indication in the cell
        String actualColor = colorCodeForSignificantOrNOnSigni.getCssValue("background-color");

        String[] rgbValues = actualColor.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");
        int red = Integer.parseInt(rgbValues[0].trim());
        int green = Integer.parseInt(rgbValues[1].trim());
        int blue = Integer.parseInt(rgbValues[2].trim());
        System.out.println("Red: " + red + " Green: " + green + " Blue: " + blue);
        String actualColors = String.format("#%02x%02x%02x", red, green, blue);
        System.out.println("Color code for cell " + actualCellName + " is: " + actualColors);


        // Initialize selectedGrade
        int selectedGrade = -1;

         // Fetch the list of grade radio buttons
        List<WebElement> listOfGrades = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(cellPath + "//following::div[2]//span/input")));

        // Iterate over the list to find the selected radio button and retrieve the grade value
        for (WebElement grade : listOfGrades) {
            if (grade.isSelected()) {
                String selectedValue = grade.getAttribute("value");
                selectedGrade = Integer.parseInt(selectedValue);
                System.out.println("Selected grade value for cell " + actualCellName + " is " + selectedGrade);
                break;  // Exit the loop once the selected radio button is found
            }
        }


        // Check for Microcytes, Macrocytes, Poikilocytosis, Hypochromic Cells
        if (Arrays.asList("Microcytes", "Macrocytes", "Poikilocytosis", "Hypochromic Cells").contains(actualCellName)) {

            // Now compare the grade Percentage and selectedGrade
            if (gradePercentage == 0) {
                // Check if the selected grade is also 0
                if (selectedGrade == 0) {
                    logger.info("Grade value is correctly set to 0 when the percentage is 0.0 for cell " + actualCellName);
                    if (actualColors.equals("#32985d")) {
                        logger.info("Non-significant grade: Correct green color code is present for cell " + actualCellName);
                        flag = true;
                    } else {
                        logger.error("Expected green color for non-significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for non-significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 0 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }else if (gradePercentage >= 0.1 && gradePercentage <= 10.0) {
                if (selectedGrade == 1 && actualColors.equals("#32985d")) {
                    logger.info("Non-significant grade: Correct green color code is present for cell " + actualCellName);
                    flag = true;
                } else if (selectedGrade == 1) {
                    logger.error("Expected green color but found " + actualColors + " for cell " + actualCellName);
                    throw new AssertionError("Color mismatch for non-significant grade.");
                }
            } else if (gradePercentage > 10.0 && gradePercentage <= 20.0) {
                if (selectedGrade == 2 && actualColors.equals("#c61b1c")) {
                    logger.info("Significant grade: Correct red color code is present for cell " + actualCellName);
                    flag = true;
                } else if (selectedGrade == 2) {
                    logger.error("Expected red color but found " + actualColors + " for cell " + actualCellName);
                    throw new AssertionError("Color mismatch for significant grade.");
                }
            }  else if (gradePercentage > 20.0) {
                if (selectedGrade == 3) {
                    logger.info("Grade value is correctly set to 3 when the percentage is greater than 20 for cell " + actualCellName);
                    if ( actualColors.equals("#c61b1c")) {
                        logger.info("Significant grade: Correct red color code is present for cell " + actualCellName);
                        flag=true;
                    } else {
                        logger.error("Expected red color for significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 3 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }
        }

        // Check for Elliptocytes, Teardrop cells, etc.
        else if (Arrays.asList("Elliptocytes", "Teardrop Cells", "Target Cells", "Echinocytes", "Acanthocytes*", "Spherocytes*", "Stomatocytes*", "Anisocytosis", "Polychromatic cells", "Basophilic stippling").contains(actualCellName)) {
            // Now compare the grade Percentage and selectedGrade
            if (gradePercentage == 0.0) {
                // Check if the selected grade is also 0
                if (selectedGrade == 0) {
                    logger.info("Grade value is correctly set to 0 when the percentage is 0.0 for cell " + actualCellName);
                    if (actualColors.equals("#32985d")) {
                        logger.info("Non-significant grade: Correct green color code is present for cell " + actualCellName);
                        flag = true;
                    } else {
                        logger.error("Expected green color for non-significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for non-significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 0 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }else if (gradePercentage > 0.1 && gradePercentage <= 5.0) {
                if (selectedGrade == 1 && actualColors.equals("#32985d")) {
                    logger.info("Non-significant grade: Correct green color code is present for cell " + actualCellName);
                    flag = true;
                } else if (selectedGrade == 1) {
                    logger.error("Expected green color but found " + actualColors + " for cell " + actualCellName);
                    throw new AssertionError("Color mismatch for non-significant grade.");
                }
            } else if (gradePercentage > 5.0 && gradePercentage <= 20.0) {
                if (selectedGrade == 2 && actualColors.equals("#c61b1c")) {
                    logger.info("Significant grade: Correct red color code is present for cell " + actualCellName);
                    flag = true;
                } else if (selectedGrade == 2) {
                    logger.error("Expected red color but found " + actualColors + " for cell " + actualCellName);
                    throw new AssertionError("Color mismatch for significant grade.");
                }
            }
            else if (gradePercentage > 20.0) {
                if (selectedGrade == 3) {
                    logger.info("Grade value is correctly set to 3 when the percentage is greater than 20 for cell " + actualCellName);
                    if ( actualColors.equals("#c61b1c")) {
                        logger.info("Significant grade: Correct red color code is present for cell " + actualCellName);
                        flag=true;
                    } else {
                        logger.error("Expected red color for significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 3 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }
        }

        // Check for Ovalocytes
        else if (actualCellName.equals("Ovalocytes")) {
            // Now compare the grade Percentage and selectedGrade
            if (gradePercentage == 0) {
                // Check if the selected grade is also 0
                if (selectedGrade == 0) {
                    logger.info("Grade value is correctly set to 0 when the percentage is 0.0 for cell " + actualCellName);
                    if (actualColors.equals("#32985d")) {
                        logger.info("Non-significant grade: Correct green color code is present for cell " + actualCellName);
                        flag = true;
                    } else {
                        logger.error("Expected green color for non-significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for non-significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 0 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }else if (gradePercentage >= 0.1 && gradePercentage <= 6.0) {
                if (selectedGrade == 1 && actualColors.equals("#32985d")) {
                    logger.info("Non-significant grade: Correct green color code is present for Ovalocytes.");
                    flag = true;
                } else if (selectedGrade == 1) {
                    logger.error("Expected green color but found " + actualColors + " for Ovalocytes.");
                    throw new AssertionError("Color mismatch for non-significant grade.");
                }
            } else if (gradePercentage > 6.0 && gradePercentage <= 20.0) {
                if (selectedGrade == 2 && actualColors.equals("#c61b1c")) {
                    logger.info("Significant grade: Correct red color code is present for Ovalocytes.");
                    flag = true;
                } else if (selectedGrade == 2) {
                    logger.error("Expected red color but found " + actualColors + " for Ovalocytes.");
                    throw new AssertionError("Color mismatch for significant grade.");
                }

            }  else if (gradePercentage > 20.0) {
                if (selectedGrade == 3) {
                    logger.info("Grade value is correctly set to 3 when the percentage is greater than 20 for cell " + actualCellName);
                    if ( actualColors.equals("#c61b1c")) {
                        logger.info("Significant grade: Correct red color code is present for cell " + actualCellName);
                        flag=true;
                    } else {
                        logger.error("Expected red color for significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 3 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }
        } else if (actualCellName.equals("Fragmented Cells")) {
            // Now compare the grade Percentage and selectedGrade
            if (gradePercentage == 0) {
                // Check if the selected grade is also 0
                if (selectedGrade == 0) {
                    logger.info("Grade value is correctly set to 0 when the percentage is 0.0 for cell " + actualCellName);
                    if (actualColors.equals("#32985d")) {
                        logger.info("Non-significant grade: Correct green color code is present for cell " + actualCellName);
                        flag = true;
                    } else {
                        logger.error("Expected green color for non-significant grade but found: " + actualColors + " for cell " + actualCellName);
                        throw new AssertionError("Color mismatch for non-significant grade in cell: " + actualCellName);
                    }
                } else {
                    logger.error("Grade value should be 0 but found " + selectedGrade + " for cell " + actualCellName);
                    throw new AssertionError("Grade value mismatch for cell: " + actualCellName);
                }
            }else if (gradePercentage >= 0.1 && gradePercentage <= 1.0) {
                    if (selectedGrade == 1 && actualColors.equals("#c61b1c")) {
                        logger.info("Significant grade: Correct red color code is present for Fragmented Cells with grade 1.");
                        flag = true;
                    } else if (selectedGrade == 1) {
                        logger.error("Expected red color but found " + actualColors + " for Fragmented Cells with grade 1.");
                        throw new AssertionError("Color mismatch for significant grade in Fragmented Cells.");
                    }
                } else if (gradePercentage > 1.0 && gradePercentage <= 2.0) {
                    if (selectedGrade == 2 && actualColors.equals("#c61b1c")) {
                        logger.info("Significant grade: Correct red color code is present for Fragmented Cells with grade 2.");
                        flag = true;
                    } else if (selectedGrade == 2) {
                        logger.error("Expected red color but found " + actualColors + " for Fragmented Cells with grade 2.");
                        throw new AssertionError("Color mismatch for significant grade in Fragmented Cells.");
                    }
                } else if (gradePercentage > 2.0) {
                    if (selectedGrade == 3 && actualColors.equals("#c61b1c")) {
                        logger.info("Significant grade: Correct red color code is present for Fragmented Cells with grade 3.");
                        flag = true;
                    } else if (selectedGrade == 3) {
                        logger.error("Expected red color but found " + actualColors + " for Fragmented Cells with grade 3.");
                        throw new AssertionError("Color mismatch for significant grade in Fragmented Cells.");
                    }
                }
        }



        return flag;
    }






    // Verify that the percentage value is getting struck off when the cells are regraded.
    public boolean percentageValueStruckOffAfterRegrading(String  cellPath,String gradePercentagePath) throws InterruptedException {
        boolean flag=false;
        this.rbcRegrading(cellPath,gradePercentagePath);
            WebElement struckOffElement=driver.findElement(By.xpath(cellPath + "//following::div[3]/del"));
            String textDecoration = struckOffElement.getCssValue("text-decoration");
            if (textDecoration.contains("line-through")) {
                System.out.println("Text is properly displayed with a strikethrough.");
                flag=true;
            } else {
                System.out.println("Strikethrough is not applied correctly.");
            }


        return flag;
    }
















}














