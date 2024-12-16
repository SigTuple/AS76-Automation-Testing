package RBC.VerifyTheRegradingOfRbcCellTypes;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.util.Properties;


public class VerifyTheRegradingOfRbcCellTypesTest extends BrowserSetUp {

    private final Logger logger = LogManager.getLogger(VerifyTheRegradingOfRbcCellTypesTest.class);


    public WebDriver driver;
    public WebDriverWait wait;
    int time = 30;
    Properties props;
    private VerifyTheRegradingOfRbcCellTypes verifyTheRegradingOfRbcCellTypes;
    private CommonMethods common;

    @BeforeSuite
    public void driver() throws Exception {
        BrowserSetUp browser = new BrowserSetUp();
        driver = browser.getDriver();
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readRBCProperties();
        verifyTheRegradingOfRbcCellTypes = new VerifyTheRegradingOfRbcCellTypes(driver);
        common = new CommonMethods(driver);
    }


    //_______________________________Verifying  The Regrading Of Rbc Cell Types Test Methods____________________________//



    /*

    // Verify the regrading functionality of all cell types for the Approved statuses of report.

    @Test(priority = 29,enabled = true)
    public void regradingFunctionalityOfApprovedReport() throws InterruptedException {
        driver.findElement(By.xpath(props.getProperty("backToListReport"))).click();
        boolean approvedReport=verifyTheRegradingOfRbcCellTypes.regradingFunctionalityOfDifferentStatuesReport("Approved");
        Assert.assertTrue(approvedReport);
        logger.info(" Verify the regrading functionality of all cell types for the Approved statuses of report.");
    }


    // Verify the regrading functionality of all cell types for the Rejected statuses of report.
    @Test(priority = 31,enabled = true)
    public void regradingFunctionalityOfRejectedReport() throws InterruptedException {
        boolean rejectedReport=verifyTheRegradingOfRbcCellTypes.regradingFunctionalityOfDifferentStatuesReport("Rejected");
        Assert.assertTrue(rejectedReport);
        logger.info(" Verify the regrading functionality of all cell types for the Rejected statuses of report.");
    }


    // Verify the regrading functionality of all cell types for the Under Reviewed statuses of report.
    @Test(priority = 33,enabled = true)
    public void regradingFunctionalityOfUnderReviewedReport() throws InterruptedException {
        boolean rejectedReport=verifyTheRegradingOfRbcCellTypes.regradingFunctionalityOfDifferentStatuesReport("Under Reviewed");
        Assert.assertTrue(rejectedReport);
        logger.info(" Verify the regrading functionality of all cell types for the Under review statuses of report.");
    }

     */


    //Verify the calculation of % value for Macrocytes cell types present in the RBC size tab after regrading.
    @Test(priority = 35,enabled = true)
    public void percentageValueOfMacrocytesOnRbcSizeTabAfterRegrading() throws InterruptedException {
        test = extent.createTest("RCB Regrading");
        String tab = props.getProperty("TabXpath") + "'Size'" + props.getProperty("remainingXpath");
        common.clickOnTab("Size", tab);
        common.assignReviewer("rupa");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("Macrocytes"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Macrocytes cell types present in the RBC size tab after regrading");
    }


    //Verify the calculation of % value for Microcytes cell types present in the RBC size tab after regrading.
    @Test(priority = 37,enabled = true)
    public void percentageValueOfMicrocytesOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("Microcytes"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Microcytes cell types present in the RBC size tab after regrading");
    }

    //Verify the calculation of % value for Anisocytosis cell types present in the RBC size tab after regrading.
    @Test(priority = 39,enabled = false)
    public void percentageValueOfAnisocytosisOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("AnisocytosisCell"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Anisocytosis cell types present in the RBC size tab after regrading");
    }



    //Verify the calculation of % value for Ovalocytes cell types present in the RBC size tab after regrading.
    @Test(priority = 41,enabled = true)
    public void percentageValueOfOvalocytesOnRbcShapeTabAfterRegrading() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Shape'" + props.getProperty("remainingXpath");
        common.clickOnTab("Shape", tab);
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("Ovalocytes"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Ovalocytes cell types present in the RBC size tab after regrading");
    }

    //Verify the calculation of % value for Anisocytosis cell types present in the RBC size tab after regrading.
    @Test(priority = 43,enabled = true)
    public void percentageValueOfElliptocytesOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("Elliptocytes"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Elliptocytes cell types present in the RBC size tab after regrading");
    }



    //Verify the calculation of % value for Teardrop Cells cell types present in the RBC size tab after regrading.
    @Test(priority = 45,enabled = true)
    public void percentageValueOfTeardropCellsOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("TeardropCells"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Teardrop Cells  types present in the RBC size tab after regrading");
    }

    //Verify the calculation of % value for Fragmented Cells types present in the RBC size tab after regrading.
    @Test(priority = 47,enabled = true)
    public void percentageValueOfFragmentedCellsOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("FragmentedCells"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Fragmented Cells types present in the RBC size tab after regrading");
    }


    //Verify the calculation of % value for Target Cells types present in the RBC size tab after regrading.
    @Test(priority = 49,enabled = true)
    public void percentageValueOfTargetCellsOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("TargetCells"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Target Cells types present in the RBC size tab after regrading");
    }


    //Verify the calculation of % value for Fragmented Cells types present in the RBC size tab after regrading.
    @Test(priority = 51,enabled = false)
    public void percentageValueOfPoikilocytosisOnRbcSizeTabAfterRegrading() throws InterruptedException {
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(props.getProperty("Poikilocytosis"));
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Poikilocytosis Cells types present in the RBC size tab after regrading");
    }


    //Verify the calculation of % value for HypochromicCell cell types present in the RBC Color tab after regrading.
    @Test(priority = 54,enabled = true)
    public void percentageValueRbcColorTabAfterRegrading() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Color'" + props.getProperty("remainingXpath");
        common.clickOnTab("Color", tab);
        String cellname = props.getProperty("HypochromicCell");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for HypochromicCell cell types present in the RBC size tab after regrading");
    }

    //Verifying the correctness of the grade value according to the % present for Macrocytes cell types present in the RBC Size tab
    @Test(priority = 56,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnAnisocytosisCell () throws InterruptedException {
        driver.navigate().refresh();
        common.clickOnTab("RBC","//*[contains(text(),'RBC')]");
        String tab = props.getProperty("TabXpath") + "'Size'" + props.getProperty("remainingXpath");
        common.clickOnTab("Size", tab);
        String cellname = props.getProperty("AnisocytosisCell");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Macrocytes cell types present in the RBC Size tab ");
    }
    //Verifying the correctness of the grade value according to the % present for Microcytes cell types present in the RBC Size tab
    @Test(priority = 58,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnMacrocytes () throws InterruptedException {
        String cellname = props.getProperty("Macrocytes");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Microcytes cell types present in the RBC Size tab ");
    }


    //Verifying the correctness of the grade value according to the % present for Microcytes cell types present in the RBC Size tab
    @Test(priority = 60,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnMicrocytes () throws InterruptedException {
        String cellname = props.getProperty("Microcytes");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Microcytes cell types present in the RBC Size tab ");
    }

    //Verifying the correctness of the grade value according to the % present for Microcytes cell types present in the RBC Size tab
    @Test(priority = 62,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnPokilocytes() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Shape'" + props.getProperty("remainingXpath");
        common.clickOnTab("Shape", tab);
        String cellname = props.getProperty("Poikilocytosis");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Poikilocytosis cell types present in the RBC Size tab ");
    }

    //Verifying the correctness of the grade value according to the % present for Ovalocytes cell types present in the RBC Size tab
    @Test(priority = 64,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnOvalocytes () throws InterruptedException {
        String cellname = props.getProperty("Ovalocytes");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Ovalocytes cell types present in the RBC Size tab ");
    }


    //Verifying the correctness of the grade value according to the % present for Elliptocytes cell types present in the RBC Size tab
    @Test(priority = 66,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnElliptocytes() throws InterruptedException {
        String cellname = props.getProperty("Elliptocytes");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Elliptocytes cell types present in the RBC Size tab ");
    }



    //Verifying the correctness of the grade value according to the % present for Teardrop cells types present in the RBC Size tab
    @Test(priority = 68,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnTeardropCells() throws InterruptedException {
        String cellname = props.getProperty("TeardropCells");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Teardrop cells cell types present in the RBC Size tab ");
    }

    //Verifying the correctness of the grade value according to the % present for Fragmented cells  types present in the RBC Size tab
    @Test(priority = 70,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnFragmentedCells() throws InterruptedException {
        String cellname = props.getProperty("FragmentedCells");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Fragmented cells cell types present in the RBC Size tab ");
    }

    //Verifying the correctness of the grade value according to the % present for Target cells  types present in the RBC Size tab
    @Test(priority = 72,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnTargetCells() throws InterruptedException {
        String cellname = props.getProperty("TargetCells");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Target cells cell types present in the RBC Size tab ");
    }



    //Verifying the correctness of the grade value according to the % present for Echinocytes cell types present in the RBC Size tab
    @Test(priority = 74,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnEchinocytes() throws InterruptedException {
        String cellname = props.getProperty("Echinocytes");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Echinocytes cell types present in the RBC Size tab ");
    }


    //Verifying the correctness of the grade value according to the % present for HypochromicCell cell types present in the RBC Size tab
    @Test(priority = 76,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnHypochromicCell() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Color'" + props.getProperty("remainingXpath");
        common.clickOnTab("Color", tab);
        String cellname = props.getProperty("HypochromicCell");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValuesOfAllCellsInRbcTab(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the calculation of % value for Hypochromic cell types present in the RBC size tab after regrading");
    }
    //Verifying the correctness of the grade value according to the % present for Polychromatic cell types present in the RBC Size tab
    @Test(priority = 78,enabled = true)
    public void verifyingGradeValueAccordingToPercentageOnPolychromatic() throws InterruptedException {
        String cellname = props.getProperty("Polychromatic");
        boolean status=verifyTheRegradingOfRbcCellTypes.gradeValueAccordingToPercentage(cellname);
        Assert.assertTrue(status);
        logger.info("Verified the correctness of the grade value according to the % present for Polychromatic cell types present in the RBC Size tab ");
    }


    // Verify that the user is able to regrade cell types present in the RBC size, shape and color tab.
    @Test(priority = 91, enabled = true)
    public void verifyRegradingInAnisocytosisCellTypes() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Size'" + props.getProperty("remainingXpath");
        common.clickOnTab("Size", tab);
        String cellname = props.getProperty("AnisocytosisCell");
        String listOfGradeOnAnisocytosis = props.getProperty("AnisocytosisCellGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnAnisocytosis);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Anisocytosis cell types in the RBC size tab is verified.");


    }
    // Verify that the user is able to regrade Microcytes cell types present in the RBC size

    @Test(priority = 93, enabled = true)
    public void verifyRegradingInMicrocytesCellType() throws InterruptedException {
        String cellname = props.getProperty("Microcytes");
        String listOfGradeOnMicrocytes = props.getProperty("MicrocytesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnMicrocytes);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Microcytes cell types in the RBC size tab is verified.");


    }

    // Verify that the user is able to regrade Macrocytes cell types present in the RBC size
    @Test(priority = 95, enabled = true)
    public void verifyRegradingInMacrocytesCellType() throws InterruptedException {
        String cellname = props.getProperty("Macrocytes");
        String listOfGradeOnMacrocytes = props.getProperty("MacrocytesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnMacrocytes);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Macrocytes cell types in the RBC size tab is verified.");


    }


    // Verify that the user is able to regrade Ovalocytes cell types present in the RBC Shape

    @Test(priority = 97, enabled = true)
    public void verifyRegradingInOvalocytesCellType() throws InterruptedException {
        String shapeTab = props.getProperty("TabXpath") + "'Shape'" + props.getProperty("remainingXpath");
        common.clickOnTab("Shape", shapeTab);
        String cellname = props.getProperty("Ovalocytes");
        String listOfGradeOnOvalocytes = props.getProperty("OvalocytesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnOvalocytes);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Ovalocytes cell types in the RBC Shape tab is verified.");


    }


    // Verify that the user is able to regrade Poikilocytosis cell types present in the RBC Shape
    @Test(priority =98, enabled = true)
    public void verifyRegradingInAcanthocytesCellType() throws InterruptedException {
        String cellname = props.getProperty("Poikilocytosis");
        String listOfGradeOnPoikilocytosis = props.getProperty("PoikilocytosisGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnPoikilocytosis);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Poikilocytosis cell types in the RBC Shape tab is verified.");


    }

    // Verify that the user is able to regrade Elliptocytes cell types present in the RBC Shape

    @Test(priority = 99, enabled = true)
    public void verifyRegradingInElliptocytesCellType() throws InterruptedException {
        String cellname = props.getProperty("Elliptocytes");
        String listOfGradeOnElliptocytes = props.getProperty("ElliptocytesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnElliptocytes);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Elliptocytes cell types in the RBC Shape tab is verified.");


    }
    // Verify that the user is able to regrade TeardropCells cell types present in the RBC Shape

    @Test(priority = 111, enabled = true)
    public void verifyRegradingInTeardropCellType() throws InterruptedException {
        String cellname = props.getProperty("TeardropCells");
        String listOfGradeOnTeardropCells = props.getProperty("TeardropCellsGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnTeardropCells);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Teardrop cell types in the Shape size tab is verified.");


    }
    // Verify that the user is able to regrade FragmentedCells cell types present in the RBC Shape

    @Test(priority = 113, enabled = true)
    public void verifyRegradingInFragmentedCellType() throws InterruptedException {
        String cellname = props.getProperty("FragmentedCells");
        String listOfGradeOnFragmentedCells = props.getProperty("FragmentedCellsGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnFragmentedCells);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Fragmented cell types in the RBC Shape tab is verified.");

    }
    // Verify that the user is able to regrade TargetCells cell types present in the RBC Shape
    @Test(priority = 115, enabled = true)
    public void verifyRegradingInTargetCellType() throws InterruptedException {
        String cellname = props.getProperty("TargetCells");
        String listOfGradeOnTargetCells = props.getProperty("TargetCellsGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnTargetCells);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the Target cell types in the RBC Shape tab is verified.");


    }

    // Verify that the user is able to regrade Echinocytes cell types present in the RBC Shape

    @Test(priority = 117, enabled = true)
    public void verifyRegradingInEchinocytesCellType() throws InterruptedException {
        String cellname = props.getProperty("Echinocytes");
        String listOfGradeOnEchinocytes = props.getProperty("EchinocytesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnEchinocytes);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Echinocytes cell types in the RBC Shape tab is verified.");


    }

    // Verify that the user is able to regrade HypochromicCell cell types present in the RBC color

    @Test(priority = 119, enabled = true)
    public void verifyRegradingInHypochromicCellType() throws InterruptedException {
        String colorTab = props.getProperty("TabXpath") + "'Color'" + props.getProperty("remainingXpath");
        common.clickOnTab("Color", colorTab);
        String cellname = props.getProperty("HypochromicCell");
        String listOfGradeOnHypochromicCell = props.getProperty("HypochromicGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnHypochromicCell);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the Hypochromic cell types in the RBC Color tab is verified.");

    }

    // Verify that the user is able to regrade Polychromatic cell types present in the RBC color

    @Test(priority = 121, enabled = true)
    public void verifyRegradingInPolychromaticellType() throws InterruptedException {
        String cellname = props.getProperty("Polychromatic");
        String listOfGradeOnPolychromatic = props.getProperty("PolychromaticGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnPolychromatic);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the  Echinocytes cell types in the RBC Shape tab is verified.");

    }
    // Verify that the user is able to regrade Howell-JollyBodies cell types present in the RBC Inclusions

    @Test(priority = 123, enabled = true)
    public void verifyRegradingInInclusionCellType() throws InterruptedException {
        String inclusionTab = props.getProperty("TabXpath") + "'Inclusions'" + props.getProperty("remainingXpath");
        common.clickOnTab("Inclusions", inclusionTab);
        String cellname = props.getProperty("Howell-JollyBodies");
        String listOfGradeOnHowellJollyBodies = props.getProperty("Howell-JollyBodiesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnHowellJollyBodies);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the Howell-JollyBodies Grade cell types in the RBC Inclusion tab is verified.");

    }


    // Verify that the user is able to regrade Pappenheimer Bodies cell types present in the RBC Inclusions

    @Test(priority = 125, enabled = true)
    public void verifyRegradingInPappenheimerBodiescellType() throws InterruptedException {
        String cellname = props.getProperty("PappenheimerBodies*");
        String listOfGradeOnPolychromatic = props.getProperty("PappenheimerBodiesGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnPolychromatic);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the Pappenheimer Bodies cell types in the RBC Inclusions tab is verified.");

    }


    // Verify that the user is able to regrade Pappenheimer Bodies cell types present in the RBC Inclusions

    @Test(priority = 127, enabled = true)
    public void verifyRegradingInBasophilicStipplingcellType() throws InterruptedException {
        String cellname = props.getProperty("BasophilicStippling");
        String listOfGradeOnPolychromatic = props.getProperty("BasophilicStipplingGrade");
        boolean grades = verifyTheRegradingOfRbcCellTypes.rbcRegrading(cellname, listOfGradeOnPolychromatic);
        Assert.assertTrue(grades);
        logger.info("the user is able to regrade the Basophilic Stippling cell types in the RBC Inclusions tab is verified.");

    }





    //Verifying the percentage value is getting struck off when the Macrocytes cell types present in the RBC Size tab
    @Test(priority = 184,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInMacrocytesCell() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Size'" + props.getProperty("remainingXpath");
        common.clickOnTab("Size", tab);
        String cellname = props.getProperty("Macrocytes");
        String listOfGradeOnMicrocytes = props.getProperty("MacrocytesGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Macrocytes Cell are regraded.");
    }

    //Verifying the percentage value is getting struck off when the Microcytes cell types present in the RBC Size tab
    @Test(priority = 186,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInMicrocytesCell() throws InterruptedException {
        String cellname = props.getProperty("Microcytes");
        String listOfGradeOnMicrocytes = props.getProperty("MicrocytesGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Microcytes Cell are regraded.");
    }

    //Verifying the percentage value is getting struck off when the Elliptocytes cell types present in the RBC Size tab
    @Test(priority = 208,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInElliptocytes() throws InterruptedException {
        String tab = props.getProperty("TabXpath") + "'Shape'" + props.getProperty("remainingXpath");
        common.clickOnTab("Shape", tab);
        String cellname = props.getProperty("Elliptocytes");
        String listOfGradeOnMicrocytes = props.getProperty("ElliptocytesGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Elliptocytes Cell are regraded.");
    }


    //Verifying the percentage value is getting struck off when the Teardrop cell types present in the RBC Size tab
    @Test(priority = 218,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInTeardropCells() throws InterruptedException {
        String cellname = props.getProperty("TeardropCells");
        String listOfGradeOnMicrocytes = props.getProperty("TeardropCellsGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Teardrop cells are regraded.");
    }


    //Verifying the percentage value is getting struck off when the Fragmented cell types present in the RBC Size tab
    @Test(priority = 220,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInFragmentedCells() throws InterruptedException {
        String cellname = props.getProperty("FragmentedCells");
        String listOfGradeOnMicrocytes = props.getProperty("FragmentedCellsGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Fragmented cells are regraded.");
    }


    //Verifying the percentage value is getting struck off when the Target Cells types present in the RBC Size tab
    @Test(priority = 222,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInTargetCells() throws InterruptedException {
        String cellname = props.getProperty("TargetCells");
        String listOfGradeOnMicrocytes = props.getProperty("TargetCellsGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Target cells are regraded.");
    }



    //Verifying the percentage value is getting struck off when the Echinocytes Cells types present in the RBC Size tab
    @Test(priority = 224,enabled = true)
    public void verifyingPercentageValueStruckOffAfterRegradingInEchinocytes() throws InterruptedException {
        String cellname = props.getProperty("Echinocytes");
        String listOfGradeOnMicrocytes = props.getProperty("EchinocytesGrade");
        boolean status=verifyTheRegradingOfRbcCellTypes.percentageValueStruckOffAfterRegrading(cellname,listOfGradeOnMicrocytes);
        Assert.assertTrue(status);
        logger.info(" Verify that the percentage value is getting struck off when the Echinocytes cells are regraded.");
    }











































}
