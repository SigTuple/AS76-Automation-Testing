package RBC.VerifySubTabOfRBC;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;


public class VerifyUiOfRbcSizeSubTab extends CommonMethods{
	public WebDriver driver;
	public WebDriverWait wait;
	Properties props;
   public CommonMethods cms;
	private String tabName;


	public VerifyUiOfRbcSizeSubTab(WebDriver driver) throws Exception

	{
		super(driver);
		this.driver=driver;
		   int time = 30;
		   wait=new WebDriverWait(driver,time);
			props = Property.prop;
			Property.readRBCProperties();
		   cms= new CommonMethods(driver);
		}


	//____________________ RBC "Sub tab" related scripts are written inside the class______________________//
	
	
	// select any report which is 'To be reviewed in status' from list report page
	public String selectToBeReviewedReport() throws InterruptedException {
		 return  cms.openAnyReport("To be reviewed");}

	// click on RBC TAb 
	public String clickOnRBCTab() throws InterruptedException {
		Thread.sleep(4000);
		return super.clickOnTab("RBC", props.getProperty("rbctab"));

		
	}
	// verify the sub tab names on RBC
	public String presenceOfSubTabNameOnRbc() {
		List<WebElement> subCellName = driver.findElements(By.xpath(props.getProperty("subtabname")));
		String actualCellName = null;
		for (WebElement subCellNames : subCellName) {
			actualCellName = subCellNames.getText();
			System.out.println(actualCellName);
		}
		return actualCellName;
	}


	// verifying the header name on rbc size tab
	public String presenceOfTabHeaderName() {
     return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("rbcSizeTabHeader")))).getText();
	}

	// verify the list and order of all the cells name
	public List<String> cellNamesOnSubTab() {
		List<String> significantNames = new ArrayList<>();
		List<WebElement> cellNames = driver.findElements(By.xpath(props.getProperty("cellName")));
		for (WebElement cellName : cellNames) {
			String name = cellName.getText();
			significantNames.add(name);
			System.out.println(name);
		}
		return significantNames;
	}

	//verify the list and order of all the cells name

	public Map<String, Integer> cellsNameOnSubTab() {
		List<WebElement> cellNames = driver.findElements(By.xpath(props.getProperty("cellName")));
		Map<String, Integer> cellNamePositionMap = new LinkedHashMap<>();

		int position = 1;
		for (WebElement cellName : cellNames) {
			String name = cellName.getText();
			cellNamePositionMap.put(name, position);
			position++;
		}

		for (Map.Entry<String, Integer> entry : cellNamePositionMap.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}

		return cellNamePositionMap;
	}




	// verify the presence of Note message
	public boolean  presenceOfNoteText(String tabName){
		boolean flag=false;
		String fullText= driver.findElement(By.xpath("//div[@class='text']")).getText();
		//System.out.println(fullText);
		String textWithoutDigits = fullText.replaceAll("[0-9]", "");
		System.out.println(textWithoutDigits);
		if(textWithoutDigits.startsWith("Total number of RBCs counted for "+tabName+" based classification is")){
			System.out.println("note message is verified on RBC tab");
			flag=true;
		}

		return flag;
	}

	// verify the presence of significant-info
	public String significantInfo(){
		return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("significant-info")))).getText();
	}

	// verify the default cell selection in RBC Size Tab
	public boolean defaultCellSelection(String firstCell){
		boolean flag=false;
		WebElement firstCellName=driver.findElement(By.xpath(props.getProperty("firstCellName") +firstCell+ props.getProperty("remainingPathOfFirstCellName")));
		String actualCellName=firstCellName.getText();
		System.out.println(actualCellName);
		if (actualCellName.contains(firstCell)) {
			System.out.println("default cell is selected : " +actualCellName);
			flag=true;
		} else {
			System.out.println("The default cell is not selected.");
		}

		return flag;
	}










}



