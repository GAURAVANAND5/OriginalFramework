package com.crm.GenericLibaray;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListImpListener implements ITestListener {
	
	 public void onTestFailure(ITestResult result)
	{
		String testName = result.getMethod().getMethodName();
		
		System.out.println(testName+"========Execute & i am listnening========");
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(BaseAnnotationClass.sDriver);
		File srcFile=edriver.getScreenshotAs(OutputType.FILE);
		try
		{
		File destfile=new File("./screenshot/"+testName+".png");
		FileUtils.copyFile(srcFile, destfile);
		}
		
		catch(IOException e) {
			e.printStackTrace();
			
		}
}
		

}
