package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizations {
	
	public Organizations(WebDriver driver)
	{
		PageFactory.initElements(driver ,this);
	}
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrgImg;
	
	@FindBy(name="search_text")
	private WebElement serachEdt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	public WebElement getCreateOrgImg() {
		return createOrgImg;
	}
	

	public WebElement getSerachEdt() {
		return serachEdt;
	}


	public WebElement getSearchBtn() {
		return searchBtn;
	}


}
