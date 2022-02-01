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
import com.vtiger.comcast.pomrepositorylib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.LoginPage;
import com.vtiger.comcast.pomrepositorylib.OrganizationInfo;
import com.vtiger.comcast.pomrepositorylib.Organizations;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws Throwable {
		
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
		String orgName=eLib.getDataFromExcel("Contact", 1, 3)+jLib.getRandomNumber();
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
		
		wLib.waitUntilPageLoad(driver);
		/*step 2: login to app */
		driver.get(URL);;
		
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		/* step 3: navigate to org */
		HomePage hp=new HomePage(driver);
		hp.getOrgLnk().click();
		
		/*step 4: navigate to create Org Page*/
		Organizations op=new Organizations(driver);
		op.getCreateOrgImg().click();
		
		/* : create org */
		
		CreateNewOrganization cnop=new CreateNewOrganization(driver);
		cnop.createOrg(orgName);
		
		/* wiat for header element*/
		OrganizationInfo oi=new OrganizationInfo(driver);
		wLib.waitForElementVisibility(driver, oi.getSuccesfullMsg());
		
		/* navigate to contact */
      	hp.getContactLnk().click();
		
		/*step 4: navigate to create Cantact Page*/
		Contact cp=new Contact(driver);
		cp.getCreateContactImg().click();
		
		
		/* : create Contact */
		CreateNewContact cncp=new  CreateNewContact(driver);
		cncp.createContact(lastName, orgName);
		/*  verify */
		ContactInfo cinfop=new ContactInfo(driver);
		wLib.waitForElementVisibility(driver, cinfop.getSuccesfullMsg());
		String actSucMsg=cinfop.getSuccesfullMsg().getText();
		if(actSucMsg.contains(lastName))
		{
			System.out.println(lastName+"==>Contact created successfull ==>pass");
		}
		else
		{
			System.out.println(lastName+"==>Contact not created ==>fail");
		}
		
		String actorgsucMsg=cinfop.getOrgSuccMsg().getText();
		if(actorgsucMsg.contains(orgName))
		{
			System.out.println(orgName+"==>Contact created with Org successfull ==>pass");
		}
		else
		{
			System.out.println(orgName+"==>Contact not created ==>fail");
		}
		
		
		/* logout */
		hp.logout();
		
		/*  close the Browser */
		driver.close();
		

	}

}
