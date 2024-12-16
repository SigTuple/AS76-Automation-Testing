package WBC.VerifyWbcPatches;

import java.util.List;
import java.util.Properties;

import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Data.Property;
import summary.WBCDCC;

public class WBCPatches extends CommonMethods {
	private final Logger logger = LogManager.getLogger(WBCPatches.class);
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties props;
	public WBCDCC wbcdcc;

//Author: Santosh Budni
	public WBCPatches(WebDriver driver) throws Exception {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		props = Property.prop;
		Property.readWBCProperties();
	}

	// Verify the user is able to view the WBC patches
	public boolean verifyPresenceOfWBCPatches() {
		boolean flag = false;
		List<WebElement> WBCCountXpath = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr/td[2]")));
		List<WebElement> WBCNameXpath = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(props.getProperty("WBCCells"))));
		for (int i = 0; i < 18; i++) {
			
			if (!WBCCountXpath.get(i).getText().contains("-")) {
				WBCCountXpath.get(i).click();
				WebElement patchXpath = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("patchXpath"))));

				if (patchXpath.isDisplayed()) {
					System.out.println("count present for a cell: " + WBCNameXpath.get(i).getText());
					flag = true;
				} else {
					flag = true;
					break;
				}
			} else {
				System.out.println("Patches are not present");
			}

		}
		return flag;
	}
	
	
	
	//verify the user is able to see the Non WBC patches
	public boolean verifyPresenceOfNonWBCPatches() {
		boolean flag = false;
		for (int i = 27; i <=31; i++) {
			WebElement WBCCountXpath = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+i+"]/td[2]")));
			WebElement WBCNameXpath = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+i+"]/td[1]")));

			if (!WBCCountXpath.getText().contains("-")) {
				WBCCountXpath.click();
				WebElement patchXpath = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("patchXpath"))));

				if (patchXpath.isDisplayed()) {
					System.out.println("count present for a cell: " + WBCNameXpath.getText());
					flag = true;
				} else {
					flag = true;
					break;
				}
		} else {
			System.out.println("count not present for a cell: "+WBCNameXpath.getText());
			
			}
		}
		return flag;
	}
	
	
	

}
