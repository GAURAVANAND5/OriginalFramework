package com.vtiger.comcast.organizationtest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibaray.BaseAnnotationClass;

import com.vtiger.comcast.pomrepositorylib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.OrganizationInfo;
import com.vtiger.comcast.pomrepositorylib.Organizations;

public class CreateOrganizationTest extends BaseAnnotationClass{
	
	
	@Test(groups="smoketest")
	public void createOrgTest() throws Throwable
	{

	
	/*read test data */
	String orgName=eLib.getDataFromExcel("Contact", 1, 3)+jLib.getRandomNumber();
	
	
	/*  navigate to org */
	HomePage hp=new HomePage(driver);
	hp.getOrgLnk().click();
	
	/* navigate to create Org Page*/
	Organizations op=new Organizations(driver);
	op.getCreateOrgImg().click();
	
	/*  create org */
	
	CreateNewOrganization cnop=new CreateNewOrganization(driver);
	cnop.createOrg(orgName);
	
	/*  verify */
	OrganizationInfo oinfop=new OrganizationInfo(driver);
	wLib.waitForElementVisibility(driver, oinfop.getSuccesfullMsg());
	String actSucMsg=oinfop.getSuccesfullMsg().getText();
	Assert.assertTrue(actSucMsg.contains(orgName),"==>Org is created and pass");
	System.out.println("Ogr is not created and fail");
	
	}
	
	@Test(groups="regressiontest")
	public void createOrgWithIndutriesTest() throws Throwable
	{
		 
		
		/*read test data */
		String orgName=eLib.getDataFromExcel("Contact", 4, 3)+jLib.getRandomNumber();
		String industries=eLib.getDataFromExcel("Contact",4, 4);
		
		/* navigate to org */
		HomePage hp=new HomePage(driver);
		hp.getOrgLnk().click();
		
		/*navigate to create Org Page*/
		Organizations op=new Organizations(driver);
		op.getCreateOrgImg().click();
		
		/*  create org */
		
		CreateNewOrganization cnop=new CreateNewOrganization(driver);
		cnop.createOrg(orgName,industries);
		
		/* verify */
		OrganizationInfo oinfop=new OrganizationInfo(driver);
		wLib.waitForElementVisibility(driver, oinfop.getSuccesfullMsg());
		String actSucMsg=oinfop.getSuccesfullMsg().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(actSucMsg.contains(orgName),"org created and pass");
		System.out.println("org is not created and fail");
		
		String actIndustryType = oinfop.getIntusrtyTypeinfo().getText();
        soft.assertTrue(actIndustryType.equals(industries),"==>ORg created with indusrties and pass"); 
        System.out.println("==>ORg  is  not created with indusrties and fail");
        
        soft.assertAll();
	}
	

	
	
	

}
