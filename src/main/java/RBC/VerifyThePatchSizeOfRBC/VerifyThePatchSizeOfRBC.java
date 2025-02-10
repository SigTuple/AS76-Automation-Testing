package RBC.VerifyThePatchSizeOfRBC;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import RBC.VerifyTheRegradingOfRbcCellTypes.VerifyTheRegradingOfRbcCellTypes;
import org.apache.hc.core5.util.Timeout;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class VerifyThePatchSizeOfRBC extends CommonMethods {


    private final Logger logger = LogManager.getLogger(VerifyThePatchSizeOfRBC.class);

    public static WebDriver driver;
    public static WebDriverWait wait;
    static Properties props;
    public CommonMethods cms;

    public VerifyThePatchSizeOfRBC(WebDriver driver) throws Exception {
        super(driver);
        this.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, 50);
        props = Property.prop;
        Property.readRBCProperties();
        cms = new CommonMethods(driver);

    }
    //_______________________________Verifying The Patch Size Of Rbc Cell ___________________________________________//


    public boolean verifyTheSizeOfPatchesInPixelForRBC(String cellName,String gradePercentage) throws InterruptedException {
        boolean status = false;
        WebElement cellname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cellName)));
        cellname.click();
        Thread.sleep(2000);
        WebElement gradePercentages = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(gradePercentage)));
        String actualPercentage = gradePercentages.getText();
        double actualGradePercentage = Double.parseDouble(actualPercentage);
        System.out.println(actualGradePercentage);

        if (actualGradePercentage > 0.0 && !gradePercentage.equals("-") && !gradePercentage.equals(" ")) {
            // Locate the patch element
            WebElement patchElement = driver.findElement(By.xpath("//div[@class='Item'][1]/div[1]"));

            // Retrieve the exact CSS value for width and height
            String widthStyle = patchElement.getCssValue("width");
            String heightStyle = patchElement.getCssValue("height");

            // Print the exact width and height as specified in the CSS
            System.out.println("CSS Width: " + widthStyle);
            System.out.println("CSS Height: " + heightStyle);

            // Remove 'px' and convert to double for comparison
            double actualWidth = Double.parseDouble(widthStyle.replace("px", "").trim());
            double actualHeight = Double.parseDouble(heightStyle.replace("px", "").trim());

            // Expected values
            double expectedWidth = 85.8438;
            double expectedHeight = 85.8438;

            // Compare the exact values
            if (actualWidth == expectedWidth && actualHeight == expectedHeight) {
                System.out.println("Pixel size of patches are verified on Rbc tab.");
                status = true;
            } else if (actualWidth==161.594 && actualHeight==161.594) {
                System.out.println("pixel size of patches are verified on WBC tab");
                status=true;
            }else
            {
                System.out.println("Element size does not match the expected values.");
            }
        }else {
            System.out.println("patches are not found so we can't verify the pixel of patches ");
            status=true;
        }

        return status;
    }











}
