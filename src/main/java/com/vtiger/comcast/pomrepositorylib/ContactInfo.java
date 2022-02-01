package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfo {

	public ContactInfo(WebDriver driver) {

			
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement succesfullMsg;
		
		@FindBy(xpath="//td[@id='mouseArea_Organization Name']")
		private WebElement OrgSuccMsg;

		public WebElement getSuccesfullMsg() {
			return succesfullMsg;
		}

		public WebElement getOrgSuccMsg() {
			return OrgSuccMsg;
		}
		
	}

