package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibaray.WebDriverUtility;

public class CreateNewContact  extends WebDriverUtility{

     WebDriver driver=null;	
	public CreateNewContact(WebDriver driver) {
		  this.driver=driver;
			PageFactory.initElements(driver, this);
			
			
		}
		@FindBy(name="lastname")
		private WebElement lastNameEdt;
		
		@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
		private WebElement orgLookUpImg;
		
		
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveBtn;
		
		public void createContact(String lastName ) {
			lastNameEdt.sendKeys(lastName);
			saveBtn.click();
		}
		
		public WebElement getLastNameEdt() {
			return lastNameEdt;
		}

		public WebElement getOrgLookUpImg() {
			return orgLookUpImg;
		}

		public WebElement getSaveBtn() {
			return saveBtn;
		}
		

		public void createContact(String lastName,String orgName) throws Throwable
		{
			lastNameEdt.sendKeys(lastName);
			orgLookUpImg.click();
			switchToWindow(driver,"Accounts&action");
			Organizations op=new Organizations(driver);
			op.getSerachEdt().sendKeys(orgName);
			op.getSearchBtn().click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			switchToWindow(driver,"Contacts&action");
			saveBtn.click();
			
			
		}

	
	

	}

