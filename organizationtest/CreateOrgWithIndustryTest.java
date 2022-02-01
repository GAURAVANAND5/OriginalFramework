package com.vtiger.comcast.organizationtest;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibaray.ExcelUtility;
import com.crm.GenericLibaray.FileUtility;
import com.crm.GenericLibaray.JavaUtility;
import com.crm.GenericLibaray.WebDriverUtility;
import com.vtiger.comcast.pomrepositorylib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.LoginPage;
import com.vtiger.comcast.pomrepositorylib.OrganizationInfo;
import com.vtiger.comcast.pomrepositorylib.Organizations;

public class CreateOrgWithIndustryTest {

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
		String orgName=eLib.getDataFromExcel("Contact", 4, 3)+jLib.getRandomNumber();
		String industries=eLib.getDataFromExcel("Contact",4, 4);
		
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
		cnop.createOrg(orgName,industries);
		
		/* step 6: verify */
		OrganizationInfo oinfop=new OrganizationInfo(driver);
		wLib.waitForElementVisibility(driver, oinfop.getSuccesfullMsg());
		String actSucMsg=oinfop.getSuccesfullMsg().getText();
		if(actSucMsg.contains(orgName))
		{
			System.out.println(orgName+"==>org is created successfull ==>pass");
		}
		else
		{
			System.out.println(orgName+"==>org is not created ==>fail");
		}
		
		String actIndustryType = oinfop.getIntusrtyTypeinfo().getText();
        if(actIndustryType.equals(industries)) {
        	System.out.println(industries + "==>industry is verified successfully");
        }else {
        	System.out.println(industries + "==>industry is not verified successfully");

        }

		
		/* step 7: logout */
		hp.logout();
		
		/* Step 8: close the Browser */
		driver.close();
		


	}

}
