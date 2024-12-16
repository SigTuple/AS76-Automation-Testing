package utilities;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListeners extends BrowserSetUp implements ITestListener{

	String filePath;
    private final Logger logger = LogManager.getLogger(ScreenshotListeners.class);
    public ScreenshotListeners() {
        filePath = new File("src/test/java/Screenshot")
                .getAbsolutePath();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        String methodname="";
        String[] listofname=iTestResult.getName().split("(?=[A-Z])");
        for(String temp:listofname)
     	   methodname=methodname+" "+temp;
        if(methodname.length()<1)
     	   methodname=iTestResult.getName();
        
    	logger.info("\n================: "+methodname+" :====================\n");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }


}
