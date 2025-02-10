package SessionManagement;

import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Data.Property;

public class LoginExample {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public int time=30;
	private final static Logger logger = (Logger) LogManager.getLogger(Login.class);

	static Properties props;


	public LoginExample(WebDriver driver) throws Exception
	{
		this.driver=driver;
		wait = new WebDriverWait(driver, 50);
		props = Property.prop;
		Property.readSessionManagement();
	}
	
	//________________________________________________Login Functionality Related Test Cases_______________________________________//
	
	
	// entering username and password 
	
//	public String valid(String name,String PWD)
//	{
//		
//	    WebElement UserField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//	    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
//	    if(UserField.isDisplayed() && PWDField.isDisplayed())
//	    {
//	    UserField.sendKeys(name);
//	    PWDField.sendKeys(PWD);
//	    }
//		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
//		if(submitButton.isEnabled())
//		{
//			submitButton.click();
//		}
//
//		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("my_report")))).getText();
//
//		
//	}
	
	
	public String verifyReportingAppLoad()
	{
		props = Property.prop;
        String signin_page_title = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(props.getProperty("Title_Sign_in_page")))).getText();
        if (signin_page_title != null) {
            //logger.info("Signin_page is loaded");
        	System.out.println(signin_page_title);
            return signin_page_title;
            
        } else return "Not a sign in page";
	}
	
	public void enterUserName(String name)
	{
		
	    WebElement UserField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

		if(UserField.isDisplayed())
	    {		
			UserField.click();    
		    UserField.clear();
		    UserField.sendKeys(name);
		   logger.info("entered username: "+name);
		   
	    }
		
	}

	public void enterPassword(String PWD)
	{
	    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));

		if(PWDField.isDisplayed())
	    {
			PWDField.click();
			PWDField.clear();
			PWDField.sendKeys(PWD);
			logger.info("entered password: "+ PWD);
	    }
	}
	
	public String clickSubmitBtn() throws InterruptedException
	{
		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		if(submitButton.isEnabled())
		{
			submitButton.click();
			logger.info("clicked on submit button");
		}
		
//		try 
//		{
//			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//		}
//		//Thread.sleep(4000);

		String title=driver.getTitle();
		
		System.out.println(title);
		return title;
	}
	 
	public boolean verifyPresenceOfLogoOnLoginPage() 
	{
		boolean flag= false;
		WebElement  sigtuple_logo= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("Logo_login_page"))));
		if(sigtuple_logo.isDisplayed())
		{
			
			flag=true;
		}
		return flag;
	}

	public boolean verifyThePresenceOfUsernameField()
	{
		boolean flag= false;
	    WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		if(userField.isDisplayed())
{
			
			flag=true;
		}
		return flag;
	}
	
	public boolean verifyThePresenceOfPasswordField()
	{
		boolean flag= false;
	    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		if(PWDField.isDisplayed())
		{

			flag=true;
		}
		return flag;
	}
	
	public boolean verifyThePresenceOfSignInBtn()
	{
		boolean flag= false;
        WebElement sign_in_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		if(sign_in_btn.isDisplayed())
		{

			flag=true;
		}
		return flag;
	}
	
	public String LoginWithValidCred(String uname, String pwd) throws InterruptedException
	{
		System.out.println("Started valid case");
		enterUserName(uname);
		enterPassword(pwd);
		this.clickSubmitBtn();
		Thread.sleep(2000);
		return driver.findElement(By.xpath(props.getProperty("listReportPageTitle"))).getText();

	  //  WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//	    Actions act = new Actions(driver);
//	    act.keyDown(Keys.COMMAND).sendKeys("a").build().perform();
//	    act.keyDown(Keys.COMMAND).sendKeys("d").build().perform();

//	    userField.sendKeys(uname);
//		Thread.sleep(2000);

	    
//	    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
//
//	    PWDField.click();
//	    PWDField.clear();
//	    PWDField.sendKeys(pwd);
//		System.out.println("entered password");		

//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        
//	    //String username=userField.getAttribute("value");
//	    //System.out.print(username);
//	    
//		//Login.setUserName(props.getProperty("validName"));
////        Thread.sleep(2000);
////        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
////		if(submitButton.isEnabled())
////		{
////			submitButton.click();
////			System.out.println("clicked on signin button");
////			String title=driver.getTitle();
////			return title;
////		}
	

	    //WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
	    //PWDField.click();
	    //PWDField.clear();
		//Login.setPassword(props.getProperty("validPWD"));

		//String Title_listReport=Login.clickSubmitBtn();
		

	}
	
		public String LoginWithInValidCred(String uname, String pwd) throws InterruptedException
	{
		System.out.println("Started Invalid cases");
		enterUserName(uname);
		enterPassword(pwd);
		this.clickSubmitBtn();
		return driver.findElement(By.xpath(props.getProperty("Message_Incorrect_UN_PWD"))).getText();
		
		

//	    WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
//	    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
//
//	    userField.click();
//		Thread.sleep(2000);
//
//	    userField.clear();
//		Thread.sleep(2000);
//
//	    userField.sendKeys(uname);
//		Thread.sleep(2000);
//
//	    System.out.println("entered username");
//	    PWDField.click();
//	    PWDField.clear();
//	    PWDField.sendKeys(pwd);
//		System.out.println("entered password");		
//
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        
//	    //String username=userField.getAttribute("value");
//	    //System.out.print(username);
//	    
//		//Login.setUserName(props.getProperty("validName"));
//        Thread.sleep(2000);
//        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
//		if(submitButton.isEnabled())
//		{
//			submitButton.click();
//			System.out.println("clicked on signin button");
//			String Incorrect_UN_PWD_MSG= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("Message_Incorrect_UN_PWD")))).getText();
//
//			return Incorrect_UN_PWD_MSG;
//		}
//		else return "XYZ";
		
		
//		Login.setUserName(props.getProperty("InvalidName"));
//		Login.setPassword(props.getProperty("InvalidPWD"));
//		Login.clickSubmitBtn();
		//String Incorrect_UN_PWD_MSG= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("Message_Incorrect_UN_PWD")))).getText();
//
//		System.out.println(Incorrect_UN_PWD_MSG);
//		return Incorrect_UN_PWD_MSG;

	}
		
		public boolean verifyPresenceOfShowPasswordbutton()
		{
	        WebElement show_pwd_Btn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("show_pwd_btn"))));
	        if(show_pwd_Btn.isDisplayed()) 
	        {
	        	return true;
	        }

	        else {
	        	return false;
	        }
		}
	
		public boolean verifyUnMaskingThePassword()
		{ 
			boolean flag=false;
	        WebElement show_pwd_Btn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(props.getProperty("show_pwd_btn"))));
		    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		    PWDField.sendKeys("sigtuple");
	        if(show_pwd_Btn.isDisplayed()) 
	        {
	        	show_pwd_Btn.click();

	        	if(PWDField.getAttribute("type").equals("text")) 
	        	{
	        		flag= true;
	        	}
	        
		}
			return flag;
		}
	
}