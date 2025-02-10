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

import java.time.Duration;
import java.util.Properties;

public class VerifyUiOfRbcColorSubTabTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(VerifyUiOfRbcColorSubTabTest.class);

	public  WebDriver driver;
	public WebDriverWait wait;
	 int time=30;
	Properties props;
	private CommonMethods cms;
	 private VerifyUiOfRbcSizeSubTab verifySubtbOfRbc;
	 private  VerifyUiOfRbcColorSubTab verifyUiOfRbcColorSubTab;
	 private VerifyUiOfRbcShapeSubTab verifyUiOfRbcShapeSubTab;
	 private String tabName="Color";
	 
	@BeforeSuite
	public void driver() throws Exception {
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		wait = new WebDriverWait(driver, 50);
		props = Property.prop;
		Property.readRBCProperties();
		verifySubtbOfRbc=new VerifyUiOfRbcSizeSubTab(driver);
		verifyUiOfRbcColorSubTab=new VerifyUiOfRbcColorSubTab(driver);
		verifyUiOfRbcShapeSubTab=new VerifyUiOfRbcShapeSubTab(driver);
		cms=new CommonMethods(driver);
	}


		//____________________ RBC Sub tab related scripts are written inside the class______________________//

	// navigate to Color tab of Rbc
	@Test(priority = 18,enabled = true)
	public void navigateToColorTab() throws InterruptedException {
		test=extent.createTest("RCB Color Tab Test");
		String shapeTab= this.verifyUiOfRbcColorSubTab.clickedOnColorTab();
		Assert.assertEquals(shapeTab,"Color");
		logger.info("Color tab is clicked successfully");
	}
	// verifying column present on Color sub tab

	@Test(priority = 20,enabled = true)
	public void columnOnColorSubTab(){
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
	// verifying  all cell name present on Color sub tab
	@Test(priority = 22,enabled = true)
	public void cellNameOnColorSubTab(){
		String cellNameOnShapeTab= String.valueOf(verifySubtbOfRbc.cellNamesOnSubTab());
		Assert.assertEquals(cellNameOnShapeTab,"[Hypochromic Cells, Polychromatic Cells]");
		logger.info("All cell names are verified on Color sub tab");
	}

	// verifying note text on Color sub tab
	@Test(priority = 24,enabled = true)
	public void noteMessageOnColorTab(){
		boolean noteMsgOnShapeTab= verifySubtbOfRbc.presenceOfNoteText("color");
		Assert.assertTrue(noteMsgOnShapeTab);
		logger.info("Note message is verified on Color tab");
	}

	// verifying the significant and non-significant text on Color sub tab
	@Test(priority = 26,enabled = true)
	public void significatntNonSignificantTextOnColorTab(){
		String  text= verifySubtbOfRbc.significantInfo();
		Assert.assertEquals(text,"Significant\n" +
				"Non Significant");
		logger.info("Significant and non significant text are present on Color sub tab");

	}

	// default cell selection on Color sub tab
	@Test(priority = 28,enabled = true)
	public void defaultCellSelectionOnColorTab(){
		boolean defaultCellSelection=verifySubtbOfRbc.defaultCellSelection("Hypochromic Cell");
		Assert.assertTrue(defaultCellSelection);
		logger.info("default cell selections are verified on Color Tab ");
	}

	// verifying the asteric mark on manual sub-classification cell
	@Test(priority = 30,enabled = true)
	public void astericMarkSOnManualSubClassification(){
		boolean status= verifyUiOfRbcColorSubTab.astericMarkOnManualSubClassification();
		Assert.assertTrue(status);
		logger.info("manual sub-classification cells are verified on Color tab");

	}

	// verifying Significant legend name with a red dot in front of cell name in Color Tab
	@Test(priority = 32,enabled = true)
	public void colourCodeOfSignificantCells() {
		boolean status=verifyUiOfRbcShapeSubTab.significantColourCode(props.getProperty("significant"));
		Assert.assertTrue(status);
		logger.info("red colours are verified for significant cells: " +tabName);
	}



	// verifying Non-Significant legend name with a green dot in front of cell name in Color Tab
	@Test(priority = 34,enabled = true)
	public void colourCodeOfNonSignificantCells() throws InterruptedException {
		boolean status=verifyUiOfRbcShapeSubTab.significantColourCode(props.getProperty("nonsignificant"));
		Assert.assertTrue(status);
		logger.info("red colours are verified for significant cells on Color Tab: " +tabName);
	}








	

}
