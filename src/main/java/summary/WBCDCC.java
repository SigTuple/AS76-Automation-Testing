package summary;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import Data.Property;

public class WBCDCC {

    public WebDriver driver;
    public WebDriverWait wait;
    public Properties props;

    public WBCDCC(WebDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        props = Property.prop;
        Property.readSummaryProperties();
    }
    //method will check the summary WBc RBC platelets tabs
    public String verifyThetabs() {
        WebElement report = driver.findElement(By.xpath(props.getProperty("firstReport")));
        report.click();
        String headersText ="";
        List<WebElement> tabHeaders = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("reportHeader"))));
        for (WebElement tabHeader : tabHeaders) {

            headersText=headersText+tabHeader.getText()+",";

        }
        System.out.println(headersText);
        return headersText;
    }

    public String verifyWBCDifferentialCountHeader() {
        String WBCdirrentialText = "";

        WebElement WBCdirrentialHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("WBCdifferentialHeader"))));
        if (WBCdirrentialHeader.isDisplayed()) {
            WBCdirrentialText = WBCdirrentialHeader.getText();
        }
        return WBCdirrentialText;
    }

    public String verifyWBCSubheaders() {

        String subheader="";
        List<WebElement> headers = wait
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("Subheaders"))));
        for (WebElement header : headers) {

            subheader=header.getText()+","+subheader;
        }
        System.out.println(subheader);
        return subheader;
    }

    public String verifyWBCAndNonWBCHeaders()
    {
        String WBCAndNonWBCHeaders="";
        List<WebElement> WBCandNOnWBCHeaders = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("WBCAndNonHeader"))));
        for (int i =0; i <WBCandNOnWBCHeaders.size(); i++) {

            WBCAndNonWBCHeaders=WBCAndNonWBCHeaders+WBCandNOnWBCHeaders.get(i).getText();
        }


        return WBCAndNonWBCHeaders;
    }

    public String verifyWBCCellNames()
    {
        String WBCcellNames="";
        List<WebElement> WBCcellNamesXapth = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("wbcCellXpath"))));
        for (int i =0; i <WBCcellNamesXapth.size(); i++) {

            WBCcellNames=WBCcellNames+(WBCcellNamesXapth.get(i).getText())+",";
        }
        return WBCcellNames;
    }

    //compareWBCcount in summary tab and WBC tab
    public boolean compareWBCcount()
    {
        boolean flag= true;
        ArrayList<String> counts=new ArrayList<String>();
        List<WebElement> WBCcellCountXapths = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("WBCCellCount"))));
        for (WebElement WBCcellCount : WBCcellCountXapths) {
            //int count= Integer.parseInt(WBCcellCount.getText());
            counts.add(WBCcellCount.getText());
        }
        System.out.println(counts);
        //click on the WBC tab
        WebElement WbcTab = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("WCBtabXpath"))));
        try {
            WbcTab.click();
            List<WebElement> WBCcellsCountInWBCTab = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("WBCCellsCountInWcbTab"))));

            for(int i=0;i<WBCcellsCountInWBCTab.size();i++)
            {
                String sub_classText = WBCcellsCountInWBCTab.get(i).getAttribute("class");
                String cellNames=WBCcellsCountInWBCTab.get(i).getText();
                if(!sub_classText.equals("sub-row"));
                {
                    System.out.println(sub_classText);
                    System.out.println(cellNames);
                }
            }


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        return flag;

    }

    public void click_summary_()
    {
        WebElement SummaryTab = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("WCBtabXpath"))));

        if(SummaryTab.isDisplayed());

    }



}
