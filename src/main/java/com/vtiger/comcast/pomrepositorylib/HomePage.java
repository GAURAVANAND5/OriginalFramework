package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibaray.WebDriverUtility;

public class HomePage extends WebDriverUtility {
	WebDriver driver=null;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement orgLnk;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	
	
	@FindBy(xpath="//img[contains(@src,'user')]")
	private WebElement adminstatorImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLnk;

	public WebElement getOrgLnk() {
		return orgLnk;
	}


	public WebElement getContactLnk() {
		return contactLnk;
	}


	public WebElement getAdminstatorImg() {
		return adminstatorImg;
	}

	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	public void logout() throws Throwable
	{
		mouseOver(driver,adminstatorImg);
		signOutLnk.click();
	}
	
	

}
