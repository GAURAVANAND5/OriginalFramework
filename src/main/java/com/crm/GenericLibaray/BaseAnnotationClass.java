package com.crm.GenericLibaray;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.LoginPage;

public class BaseAnnotationClass {
	
	 /*create Objects */
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public WebDriver driver=null;
	public static WebDriver sDriver=null;
	
	@BeforeSuite(groups= {"smoketest","regressiontest"})
	public void ConfigBS()
	{
		System.out.println("===============Connect to DataBase================");
	}
	
	//@Parameters("browser")
	@BeforeClass(groups= {"smoketest","regressiontest"})
	public void ConfigBC(/*String BROWSER*/) throws Throwable
	{
		System.out.println("=========================Launch the Browser====================");
		/*reading common data*/
		String BROWSER = fLib.getFileProperty("browser");
		
		/*launching the Browser */
		
		if(BROWSER.equalsIgnoreCase("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid Browser");
		}
		wLib.windowMaximize(driver);
		wLib.waitUntilPageLoad(driver);
		sDriver=driver;
	
	}
	@BeforeMethod(groups= {"smoketest","regressiontest"})
	public void ConfigBM() throws Throwable
	{
		
		/*reading common data*/
		System.out.println("================= Login to app==========");
		String URL = fLib.getFileProperty("url");
		String USERNAME = fLib.getFileProperty("username");
		String PASSWORD = fLib.getFileProperty("password");
		/*step 2: login to app */
		driver.get(URL);
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}


	@AfterMethod(groups= {"smoketest","regressiontest"})
	public void ConfigAM() throws Throwable
	{
		System.out.println("========================Logout to App=======================");
		/* step 7: logout */
		HomePage hp=new HomePage(driver);
		hp.logout();}
	@AfterClass(groups= {"smoketest","regressiontest"})
	public void ConfigAC()
	{
		System.out.println("========================Close the Browser=======================");
		/* Step 8: close the Browser */
		driver.close();
	}
	
	@AfterSuite(groups= {"smoketest","regressiontest"})
	public void ConfigAS()
	{
		System.out.println("===============Closing the DataBase Connection================");
	}
	
	

}
