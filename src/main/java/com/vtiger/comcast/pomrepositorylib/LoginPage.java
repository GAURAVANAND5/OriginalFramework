package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement userNameId;
	
	@FindBy(name="user_password")
	private WebElement userPasswordId;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@id='submitButton']")})
	private WebElement loginBtn;

	public WebElement getUserNameId() {
		return userNameId;
	}

	public WebElement getUserPasswordId() {
		return userPasswordId;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	 public void loginToApp(String userName,String password)
	 {
		 userNameId.sendKeys(userName);
		 userPasswordId.sendKeys(password);
		 loginBtn.click();
	 }

}
