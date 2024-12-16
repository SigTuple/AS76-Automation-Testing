package RBC.VerifyThePatchViewOfRBC;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class VerifyThePatchViewOfRBC extends CommonMethods {


    private final Logger logger = LogManager.getLogger(VerifyThePatchViewOfRBC.class);

    public static WebDriver driver;
    public static WebDriverWait wait;
    static Properties props;
    public CommonMethods cms;

    public VerifyThePatchViewOfRBC(WebDriver driver) throws Exception {
        super(driver);
        VerifyThePatchViewOfRBC.driver = driver;
        int time = 30;
        wait = new WebDriverWait(driver, time);
        props = Property.prop;
        Property.readRBCProperties();
        cms = new CommonMethods(driver);

    }
    //_______________________________Verifying The Patch View Of Rbc Cell ___________________________________________//


    public boolean verifyThePatchDisplayingCorrectlyForAllCellType(String cellName) throws InterruptedException {
        boolean flag = false;
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cellName))).click();
        Thread.sleep(5000);
        List<WebElement> listOfPatches = driver.findElements(By.xpath(props.getProperty("patch")));
        int countOfPatches = listOfPatches.size();
        System.out.println(countOfPatches);

        for (int i = 0; i < listOfPatches.size(); i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 500);");

//            js.executeScript("arguments[0].scrollIntoView(true);", listOfPatches.get(i));
        }
        Thread.sleep(2000);
        return flag;
    }



}
