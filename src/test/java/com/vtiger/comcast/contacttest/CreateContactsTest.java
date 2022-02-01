package com.vtiger.comcast.contacttest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibaray.BaseAnnotationClass;

import com.vtiger.comcast.pomrepositorylib.Contact;
import com.vtiger.comcast.pomrepositorylib.ContactInfo;
import com.vtiger.comcast.pomrepositorylib.CreateNewContact;
import com.vtiger.comcast.pomrepositorylib.CreateNewOrganization;
import com.vtiger.comcast.pomrepositorylib.HomePage;

import com.vtiger.comcast.pomrepositorylib.OrganizationInfo;
import com.vtiger.comcast.pomrepositorylib.Organizations;

public class CreateContactsTest extends BaseAnnotationClass {
	
	
	@Test(groups="smoketest")
	public void createContact() throws Throwable
	{
	
	/*read test data */
	String lastName=eLib.getDataFromExcel("Contact", 1, 2)+jLib.getRandomNumber();
	
	
	
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
	System.out.println(actSucMsg);
	Assert.assertTrue(actSucMsg.contains(lastName),"==>Contact is not created ==>fail");
	Reporter.log(lastName+"==>Contact is created successfull ==>pass",true);
	
	}
	
	
	
	@Test(groups="regressiontest")
	public void createContactwithorgTest() throws Throwable
	{
	
	/*read test data */
	String orgName=eLib.getDataFromExcel("Contact", 1, 3)+jLib.getRandomNumber();
	String lastName=eLib.getDataFromExcel("Contact", 1, 2)+jLib.getRandomNumber();
	
	
	
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
	SoftAssert soft=new SoftAssert();
	soft.assertTrue(actSucMsg.contains(lastName),"==>Contact is not  created ==>fail");
	
	Reporter.log(lastName+"==>Contact created successfull ==>pass",true);
	
	String actorgsucMsg=cinfop.getOrgSuccMsg().getText();
	soft.assertTrue(actorgsucMsg.contains(orgName),"==>Contact not created ==>fail");
	
	Reporter.log(orgName+"==>Contact created with Org successfull ==>pass",true);
	
	
	soft.assertAll();
	
	}
	

}
