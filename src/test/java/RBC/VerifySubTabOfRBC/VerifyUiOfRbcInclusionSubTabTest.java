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

public class VerifyUiOfRbcInclusionSubTabTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(VerifyUiOfRbcInclusionSubTabTest.class);

	public  WebDriver driver;
	public WebDriverWait wait;
	 int time=30;
	Properties props;
	private CommonMethods cms;
	 private VerifyUiOfRbcSizeSubTab verifySubtbOfRbc;
	 private  VerifyUiOfRbcInclusionSubTab verifyUiOfRbcInclusionSubTab;
	 private  VerifyUiOfRbcShapeSubTab verifyUiOfRbcShapeSubTab ;
	 private String tabName="Inclusion";
	 
	@BeforeSuite
	public void driver() throws Exception {
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		wait = new WebDriverWait(driver, 30);
		props = Property.prop;
		Property.readRBCProperties();
		verifySubtbOfRbc=new VerifyUiOfRbcSizeSubTab(driver);
		verifyUiOfRbcInclusionSubTab=new VerifyUiOfRbcInclusionSubTab(driver);
		verifyUiOfRbcShapeSubTab=new VerifyUiOfRbcShapeSubTab(driver);
		cms=new CommonMethods(driver);
	}


		//____________________ RBC Sub tab related scripts are written inside the class______________________//

	// navigate to Shape tab of Rbc
	@Test(priority = 18,enabled = true)
	public void navigateToInclusionTab() throws InterruptedException {
		test=extent.createTest("RCB Inclusion Tab Test");
		String shapeTab= this.verifyUiOfRbcInclusionSubTab.clickedOnInclusionsTab();
		Assert.assertEquals(shapeTab,"Inclusions");
		logger.info("Inclusions tab is clicked successfully");
	}
	// verifying column present on shape sub tab

	@Test(priority = 20,enabled = true)
	public void columnOnInclusionSubTab(){
		String  shapeColumnName=verifySubtbOfRbc.presenceOfTabHeaderName();
		Assert.assertEquals(shapeColumnName,"Cell name\n" +
				"Grade\n" +
				"0\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"%");
		logger.info("column name is verified on Inclusion sub Tab"+shapeColumnName);
	}
	// verifying  all cell name present on Shape sub tab
	@Test(priority = 22,enabled = true)
	public void cellNameOnInclusionSubTab(){
		String cellNameOnShapeTab= String.valueOf(verifySubtbOfRbc.cellNamesOnSubTab());
		Assert.assertEquals(cellNameOnShapeTab,"[Howell-Jolly Bodies*, Pappenheimer Bodies*, Basophilic Stippling*]");
		logger.info("All cell names are verified on Inclusion sub tab");
	}

	// verifying note text on Inclusion sub tab
	@Test(priority = 24,enabled = false)
	public void noteMessageOnInclusionTab(){
		boolean noteMsgOnShapeTab= verifySubtbOfRbc.presenceOfNoteText("inclusion");
		Assert.assertTrue(noteMsgOnShapeTab,"Total number of RBCs counted for shape based classification is 4458");
		logger.info("Note message is verified on Inclusion tab");
	}

	// verifying the significant and non-significant text on Inclusion sub tab
	@Test(priority = 26,enabled = true)
	public void significatntNonSignificantTextOnInclusionTab(){
		String  text= verifySubtbOfRbc.significantInfo();
		Assert.assertEquals(text,"Significant\n" +
				"Non Significant");
		logger.info("Significant and non significant text are present on Inclusion sub tab");

	}

	// default cell selection on Shape sub tab
	@Test(priority = 28,enabled = true)
	public void defaultCellSelectionOnInclusionTab(){
		boolean defaultCellSelection=verifySubtbOfRbc.defaultCellSelection("Howell-Jolly Bodies");
		Assert.assertTrue(defaultCellSelection);
		logger.info("default cell selections are verified on Inclusion Tab ");
	}

	// verifying the asteric mark on manual sub-classification cell
	@Test(priority = 30,enabled = true)
	public void astericMarkSOnManualSubClassification(){
		boolean status= verifyUiOfRbcInclusionSubTab.astericMarkOnManualSubClassification();
		Assert.assertTrue(status);
		logger.info("manual sub-classification cells are verified on Inclusion tab");

	}

	// verifying Significant legend name with a red dot in front of cell name in Inclusion Tab
	@Test(priority = 32,enabled = true)
	public void colourCodeOfSignificantCells() {
		boolean status=verifyUiOfRbcShapeSubTab.significantColourCode(props.getProperty("significant"));
		Assert.assertTrue(status);
		logger.info("red colours are verified for significant cells: " +tabName);
	}



	// verifying Non-Significant legend name with a green dot in front of cell name in Inclusion Tab
	@Test(priority = 34,enabled = true)
	public void colourCodeOfNonSignificantCells() throws InterruptedException {
		boolean status=verifyUiOfRbcShapeSubTab.significantColourCode(props.getProperty("nonsignificant"));
		Assert.assertTrue(status);
		logger.info("red colours are verified for significant cells on Inclusion Tab: " +tabName);
	}








	

}
