package SessionManagement;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Data.Property;
import utilities.BrowserSetUp;

public class LoginExampleTest extends BrowserSetUp {
	public static final String ValidAuthentication = null;
	private final static Logger logger = LogManager.getLogger(LoginExampleTest.class);
	public WebDriver driver;
	public WebDriverWait wait;
	private LoginExample login;
	 public static Properties props;
	@BeforeSuite
	public void setUP() throws Exception
	{
		BrowserSetUp browser = new BrowserSetUp();
		WebDriver driver = browser.getDriver();
		System.out.println("before lauch");
		wait = new WebDriverWait(driver,30);
		System.out.println("after lauch");
		login=new LoginExample(driver);
		props = Property.prop;
		Property.readSessionManagement();
		
		
	}
	
	@Test(priority=1, enabled=false)
	public void testVerifyReportingAppLoad() 
	{
		
		String Title=login.verifyReportingAppLoad();
		Assert.assertEquals(Title,"Sign in to dcm4che");
		
	}
	
	@Test(priority=2, enabled=true)
	public void testVerifyPresenceOfLogoInLoginPage()
	{
		test=extent.createTest("login Test");
		System.out.println("1st case ");
		Assert.assertTrue(login.verifyPresenceOfLogoOnLoginPage());
	}
	
	@Test(priority=3, enabled=true)
	public void testVerifyThePresenceOfUsernameField()
	{
		Assert.assertTrue(login.verifyThePresenceOfUsernameField());
	}
		
	@Test(priority=4, enabled=true)
	public void testVerifyThePresenceOfPasswordField()
	{
		Assert.assertTrue(login.verifyThePresenceOfPasswordField());
	}
	
	@Test(priority=5, enabled=true)
	public void testVerifyThePresenceOfSignInBtn()
	{
		Assert.assertTrue(login.verifyThePresenceOfSignInBtn());
	}
	
	
	@Test(priority=6, enabled=true)
	public void testVerifyPresenceOfShowPasswordbutton()
	{
		Assert.assertTrue(login.verifyPresenceOfShowPasswordbutton());
		
	}
	
	@Test(priority=7, enabled=true)
	public void testverifyUnMaskingThePassword()
	{
		Assert.assertTrue(login.verifyUnMaskingThePassword());
		
	}
	
	 //Send Invalid login Credentials
    @DataProvider(name = "InvalidAuthentication")
    public static Object[][] Invalidcredentials()  {
        // The number of times data is repeated, test will be executed the same no. of times
        // Here it will execute 4 times

    	//1st set of invalid cred
        String user1 = props.getProperty("InvalidName1");
       String pwd1 = props.getProperty("InvalidPWD1");
       logger.info("1st set of invalid cred"+user1);
       
       	//2nd set of invalid cred
        String user2 = props.getProperty("InvalidName2");
        String pwd2 = props.getProperty("InvalidPWD2");
        logger.info("2st set of invalid cred"+user2);

        
        //empty set of cred
        String empty_un = props.getProperty("Empty_un");
        String empty_pwd = props.getProperty("Empty_pwd");
        
        //valid set of cred
        String user3 = props.getProperty("validName");
        String pwd3 = props.getProperty("validPWD");
        
        return new Object[][]{{user1, pwd1},{user2, pwd2},{user3,empty_pwd},{empty_un,pwd3}};
    }

    //Send Valid Credentials
    @DataProvider(name = "ValidAuthentication")
    public static Object[][] Validcredentials() {
        props = Property.prop;
        // The number of times data is repeated, test will be executed the same no. of times
        // Here it will execute 1 times
        String user3 = props.getProperty("validName");
        String pwd3 = props.getProperty("validPWD");

        return new Object[][]{{user3, pwd3}};
    }
	
	//valid login
	@Test(dataProvider="ValidAuthentication", priority=9, enabled=true)
	public void testLoginWithValidCred(String uname, String pwd) throws InterruptedException
	{
		//test=extent.createTest("login Test");
		System.out.println("validName");
		//String My_report=login.valid(props.getProperty("validName"), props.getProperty("validPWD"));
		 
		//login.setUserName(props.getProperty("validName"));
     	//login.setPassword(props.getProperty("validPWD"));

		String Title_listRpt=login.LoginWithValidCred(uname, pwd);
		Assert.assertEquals(Title_listRpt, "My reports");
		logger.info("user Logged in successfully");
		
	}
	
	@Test(dataProvider="InvalidAuthentication",priority=8,enabled=true )
	public void testLoginWithInValidCred(String uname, String pwd) throws InterruptedException
	{
		
		System.out.println("InvalidName");
		//String My_report=login.valid(props.getProperty("validName"), props.getProperty("validPWD"));
		 
//		login.setUserName(props.getProperty("validName"));
//		login.setPassword(props.getProperty("validPWD"));
//		String Title_listReport=login.clickSubmitBtn();

		String Incorrect_UN_PW_MSGG=login.LoginWithInValidCred(uname,pwd);
		Assert.assertEquals(Incorrect_UN_PW_MSGG, "Invalid username or password.");
		logger.info("user Logged in successfully");
		
	}
}
