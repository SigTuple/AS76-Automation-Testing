package RBC.VerifySubTabOfRBC;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Properties;


public class VerifyUiOfRbcInclusionSubTab extends CommonMethods{
	public WebDriver driver;
	public WebDriverWait wait;
	Properties props;
   public CommonMethods cms;
   private  VerifyUiOfRbcSizeSubTab verifyUiOfRbcSizeSubTab;



	public VerifyUiOfRbcInclusionSubTab(WebDriver driver) throws Exception

	{
		super(driver);
		this.driver=driver;
		   int time = 30;
		wait = new WebDriverWait(driver, 50);
			props = Property.prop;
			Property.readRBCProperties();
		   cms= new CommonMethods(driver);
		   verifyUiOfRbcSizeSubTab=new VerifyUiOfRbcSizeSubTab(driver);
		}


	//  _____________________Inclusion Sub Tab Of RBC__________________________________________________________//


	// navigate ti inclusion tab
	public String clickedOnInclusionsTab() throws InterruptedException {
		return super.clickOnTab("Inclusions", props.getProperty("TabXpath") +"'Inclusions'"+ props.getProperty("remainingXpath"));
	}

	// verify the asteric mark on Manual sub_classification cell of Inclusions tab
	public boolean astericMarkOnManualSubClassification(){
		boolean flag=false;
		String cellName= String.valueOf(verifyUiOfRbcSizeSubTab.cellNamesOnSubTab());
		if(cellName.contains("Howell-Jolly Bodies*") && cellName.contains("Pappenheimer Bodies*")&& cellName.contains("Basophilic Stippling*")){
			 flag=true;
			System.out.println("manual sub-classification cells are verified on Inclusion Tab");
		}

		return flag;
	}









}



