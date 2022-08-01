package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage
{
	//declaretion 
	@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

	//intialization of webelements
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getCreateContactName() {
		return createContactName;
	}
}
