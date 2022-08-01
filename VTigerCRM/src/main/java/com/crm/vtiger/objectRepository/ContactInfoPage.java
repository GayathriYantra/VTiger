package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage 
{

	//declaration
	@FindBy(xpath = "//*[@class='dvHeaderText']") private WebElement createdContactName;

	//intialization
	public ContactInfoPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getCreatedContactName() {
		return createdContactName;
	}
}
