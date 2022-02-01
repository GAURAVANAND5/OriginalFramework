package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfo {

	public OrganizationInfo(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement succesfullMsg;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement IntusrtyTypeinfo;

	public WebElement getSuccesfullMsg() {
		return succesfullMsg;
	}

	public WebElement getIntusrtyTypeinfo() {
		return IntusrtyTypeinfo;
	}

}
