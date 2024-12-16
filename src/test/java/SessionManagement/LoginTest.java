package SessionManagement;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Data.Property;
import utilities.BrowserSetUp;

public class LoginTest extends BrowserSetUp {
	private final Logger logger = LogManager.getLogger(LoginTest.class);
	public  WebDriver driver;
	public WebDriverWait wait;
	private Login login;
	Properties props;
	
	
	@BeforeSuite
	public void driver() throws Exception
	{
		BrowserSetUp browser = new BrowserSetUp();
		driver = browser.getDriver();
		System.out.println("before lauch");
		wait = new WebDriverWait(driver,10);
		System.out.println("after lauch");
		login=new Login(driver);
		props = Property.prop;
		Property.readSessionManagement();
		
		
	}
	
	//________________________________________Login main method calling in test classes______________________________//

	//valid login
	@Test (priority=1,enabled=true)
	public void validLogin() throws InterruptedException {
		test=extent.createTest("login Test");
		String My_report=login.Valid(props.getProperty("userName"), props.getProperty("passWord"));
	  // String My_report=login.Valid(props.getProperty("validName"), props.getProperty("validPWD"));

		Assert.assertEquals(My_report, "My reports");
		logger.info("user Logged in successfully");
		
	}

}
