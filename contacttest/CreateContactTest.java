package com.vtiger.comcast.contacttest;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibaray.ExcelUtility;
import com.crm.GenericLibaray.FileUtility;
import com.crm.GenericLibaray.JavaUtility;
import com.crm.GenericLibaray.WebDriverUtility;
import com.vtiger.comcast.pomrepositorylib.Contact;
import com.vtiger.comcast.pomrepositorylib.ContactInfo;
import com.vtiger.comcast.pomrepositorylib.CreateNewContact;

import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.LoginPage;



public class CreateContactTest {

	public static void main(String[] args) throws Throwable  {
		
		 /*create Objects */
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		WebDriver driver=null;
		
		/*read common data*/
		String BROWSER = fLib.getFileProperty("browser");
		String URL = fLib.getFileProperty("url");
		String USERNAME = fLib.getFileProperty("username");
		String PASSWORD = fLib.getFileProperty("password");
		
		/*read test data */
		String lastName=eLib.getDataFromExcel("Contact", 1, 2)+jLib.getRandomNumber();
		
		/*step 1:launch the Browser */
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
		/*step 2: login to app */
		driver.get(URL);
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		/* step 3: navigate to Contact */
		HomePage hp=new HomePage(driver);
		hp.getContactLnk().click();
		
		/*step 4: navigate to create Cantact Page*/
		Contact cp=new Contact(driver);
		cp.getCreateContactImg().click();
		
		
		/* : create Contact */
		CreateNewContact cncp=new  CreateNewContact(driver);
		cncp.createContact(lastName);
		
		
		/* step 6: verify */
		ContactInfo cinfop=new ContactInfo(driver);
		wLib.waitForElementVisibility(driver, cinfop.getSuccesfullMsg());
		String actSucMsg=cinfop.getSuccesfullMsg().getText();
		if(actSucMsg.contains(lastName))
		{
			System.out.println(lastName+"==>Contact is created successfull ==>pass");
		}
		else
		{
			System.out.println(lastName+"==>Contact is not created ==>fail");
		}
		
		/* step 7: logout */
		hp.logout();
		
		/* Step 8: close the Browser */
		driver.close();


	}

}
