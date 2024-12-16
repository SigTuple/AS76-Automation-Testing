package SessionManagement;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Data.Property;

public class Login {

	public WebDriver driver;
	private final WebDriverWait wait;
	public int time=10;
	Properties props;

	public Login(WebDriver driver) throws Exception
	{
		  this.driver=driver;
		wait=new WebDriverWait(driver,time);
		props = Property.prop;
		Property.readSessionManagement();
	}

	//________________________________________________Login Functionality Related Test Cases_______________________________________//
	
	
	// entering username and password after launching the application
	
	public String Valid(String name,String PWD) throws InterruptedException {
		
	    WebElement UserField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginId")));
	    WebElement PWDField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginPassword")));
	    if(UserField.isDisplayed() && PWDField.isDisplayed())
	    {
	    UserField.sendKeys(name);
	    PWDField.sendKeys(PWD);
	    }
		WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button']")));
		if(submitButton.isDisplayed())
		{
			submitButton.click();
		}
        Thread.sleep(5000);
		String myReportText= driver.findElement(By.xpath(props.getProperty("my_report"))).getText();
		System.out.println(myReportText);
		return myReportText;

		
	}


	

}
