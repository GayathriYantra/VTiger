package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage
{


	//Declaration
	@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

	//Initialization
	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//utilization
	public WebElement getCreateContactName() {
		return createContactName;
	}
	public void clickCreateContactLkpIcon()
	{
		createContactName.click();
	}
}
