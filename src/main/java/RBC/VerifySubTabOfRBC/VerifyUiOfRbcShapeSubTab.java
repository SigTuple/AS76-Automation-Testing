package RBC.VerifySubTabOfRBC;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;


public class VerifyUiOfRbcShapeSubTab extends CommonMethods{
	public WebDriver driver;
	public WebDriverWait wait;
	Properties props;
   public CommonMethods cms;
   private  VerifyUiOfRbcSizeSubTab verifyUiOfRbcSizeSubTab;



	public VerifyUiOfRbcShapeSubTab(WebDriver driver) throws Exception

	{
		super(driver);
		this.driver=driver;
		   int time = 30;
		   wait=new WebDriverWait(driver,time);
			props = Property.prop;
			Property.readRBCProperties();
		   cms= new CommonMethods(driver);
		   verifyUiOfRbcSizeSubTab=new VerifyUiOfRbcSizeSubTab(driver);
		}


	//  _____________________Shape Sub Tab Of RBC__________________________________________________________//


	// navigate to shape tab
	public String clickedOnShapeTab() throws InterruptedException {
		return super.clickOnTab("Shape", props.getProperty("TabXpath") +"'Shape'"+ props.getProperty("remainingXpath"));
	}

	// verify the asteric mark on Manual sub-classification cell of shape tab
	public boolean astericMarkOnManualSubClassification(){
		boolean flag=false;
		String cellName= String.valueOf(verifyUiOfRbcSizeSubTab.cellNamesOnSubTab());
		if(cellName.contains("Acanthocytes*") && cellName.contains("Sickle Cells*")){
			 flag=true;
			System.out.println("manual sub-classification cells are verified on Shape Tab");
		}

		return flag;
	}

	//  verify the Significant and non-significant legend name with a red dot in front of cell name
	public boolean significantColourCode(String significant_Nonsignificant_Path) {
		boolean flag = false;
		List<WebElement> colour = driver.findElements(By.xpath(significant_Nonsignificant_Path));
		for (WebElement colors : colour) {
			String actualColor = colors.getCssValue("background-color");
			System.out.println("Color value: " + actualColor);

			String[] rgbValues = actualColor.replace("rgba(", "").replace("rgb(", "").replace(")", "").split(",");

			int red = Integer.parseInt(rgbValues[0].trim());
			int green = Integer.parseInt(rgbValues[1].trim());
			int blue = Integer.parseInt(rgbValues[2].trim());

			System.out.println("Red: " + red + " Green: " + green + " Blue: " + blue);

			String actualColors = String.format("#%02x%02x%02x", red, green, blue);
			System.out.println(actualColors);
			if (actualColors.equalsIgnoreCase("#c61b1c")) {
				System.out.println("Significant legend name with a red dot in front of cell name is verified");
				flag = true;
			} else if (actualColors.equalsIgnoreCase("#32985d")){
				System.out.println(" Non-Significant legend name with a green dot in front of cell name is verified");
				flag=true;
			}
			else {
				System.out.println("colour is not found ");
			}
		}
		return flag;
	}







}



