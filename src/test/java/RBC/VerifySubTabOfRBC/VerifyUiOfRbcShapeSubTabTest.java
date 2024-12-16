package RBC.VerifySubTabOfRBC;

import Data.Property;
import GenericMethodForAllTab.CommonMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utilities.BrowserSetUp;

import java.util.Properties;

public class VerifyUiOfRbcShapeSubTabTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(VerifyUiOfRbcShapeSubTabTest.class);

	public  WebDriver driver;
	public WebDriverWait wait;
	 int time=30;
	Properties props;
	private CommonMethods cms;
	 private VerifyUiOfRbcSizeSubTab verifySubtbOfRbc;
	 private  VerifyUiOfRbcShapeSubTab verifyUiOfRbcShapeSubTab;
	 private String tabName="Size";
	 
	@BeforeSuite
	public void driver() throws Exception {
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		wait = new WebDriverWait(driver, 30);
		props = Property.prop;
		Property.readRBCProperties();
		verifySubtbOfRbc=new VerifyUiOfRbcSizeSubTab(driver);
		verifyUiOfRbcShapeSubTab=new VerifyUiOfRbcShapeSubTab(driver);
		cms=new CommonMethods(driver);
	}


		//____________________ RBC Sub tab related scripts are written inside the class______________________//

	// navigate to Shape tab of Rbc
	@Test(priority = 18,enabled = true)
	public void navigateToShapeTab() throws InterruptedException {
		test=extent.createTest("RCB Shape Tab Test");
		String shapeTab= this.verifyUiOfRbcShapeSubTab.clickedOnShapeTab();
		Assert.assertEquals(shapeTab,"Shape");
		logger.info("shape tab is clicked successfully");
	}
	// verifying column present on shape sub tab

	@Test(priority = 20,enabled = true)
	public void columnOnShapeSubTab(){
		String  shapeColumnName=verifySubtbOfRbc.presenceOfTabHeaderName();
		Assert.assertEquals(shapeColumnName,"Cell name\n" +
				"Grade\n" +
				"0\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"%");
		logger.info("column name is verified on Shape sub Tab"+shapeColumnName);
	}
	// verifying  all cell name present on Shape sub tab
	@Test(priority = 22,enabled = true)
	public void cellNameOnShapeSubTab(){
		String cellNameOnShapeTab= String.valueOf(verifySubtbOfRbc.cellNamesOnSubTab());
		Assert.assertEquals(cellNameOnShapeTab,"[Ovalocytes, Elliptocytes, Teardrop Cells, Fragmented Cells, Target Cells, Echinocytes, Acanthocytes*, Sickle Cells*, Poikilocytosis]");
		logger.info("All cell names are verified on Shape sub tab");
	}

	// verifying note text on Shape sub tab
	@Test(priority = 24,enabled = true)
	public void noteMessageOnShapeTab(){
		boolean noteMsgOnShapeTab= verifySubtbOfRbc.presenceOfNoteText("shape");
		Assert.assertTrue(noteMsgOnShapeTab);
		logger.info("Note message is verified on Shape tab:" +noteMsgOnShapeTab);
	}

	// verifying the significant and non-significant text on shape sub tab
	@Test(priority = 26,enabled = true)
	public void significatntNonSignificantTextOnShapeTab(){
		String  text= verifySubtbOfRbc.significantInfo();
		Assert.assertEquals(text,"Significant\n" +
				"Non Significant");
		logger.info("Significant and non significant text are present on shape sub tab");

	}

	// default cell selection on Shape sub tab
	@Test(priority = 28,enabled = true)
	public void defaultCellSelectionOnShapeTab(){
		boolean defaultCellSelection=verifySubtbOfRbc.defaultCellSelection("Poikilocytosis");
		Assert.assertTrue(defaultCellSelection);
		logger.info("default cell selections are verified on Shape Tab ");
	}

	// verifying the asteric mark on manual sub-classification cell
	@Test(priority = 30,enabled = true)
	public void astericMarkSOnManualSubClassification(){
		boolean status= verifyUiOfRbcShapeSubTab.astericMarkOnManualSubClassification();
		Assert.assertTrue(status);
		logger.info("manual sub-classification cells are verified on Shape tab");

	}

	// verifying Significant legend name with a red dot in front of cell name
	@Test(priority = 32,enabled = true)
	public void colourCodeOfSignificantCells() {
		boolean status=verifyUiOfRbcShapeSubTab.significantColourCode(props.getProperty("significant"));
		Assert.assertTrue(status);
		logger.info("red colours are verified for significant cells");
	}



	// verifying Non-Significant legend name with a green dot in front of cell name
	@Test(priority = 34,enabled = true)
	public void colourCodeOfNonSignificantCells() throws InterruptedException {
		boolean status=verifyUiOfRbcShapeSubTab.significantColourCode(props.getProperty("nonsignificant"));
		Assert.assertTrue(status);
		logger.info("red colours are verified for significant cells");
	}








	

}
