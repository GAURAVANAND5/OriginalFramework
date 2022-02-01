package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganization {

	public CreateNewOrganization(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(name="industry")
	private WebElement indNameEdt;
	
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	public void createOrg(String orgName ) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();
	}
	public void createOrg(String orgName ,String industries) {
		orgNameEdt.sendKeys(orgName);
		indNameEdt.sendKeys(industries);
		saveBtn.click();
	}

}
