package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage 
{
	//Declaration
	@FindBy(xpath="//img[@title='Create Contact...']") private WebElement firstNameTxtFld;
	@FindBy(name="firstname")private WebElement fistNameTxtField;
	@FindBy(name="lastname")private WebElement lastNameTxtField;
	@FindBy(xpath="//input[contains(@class,'crmButton small save')]")private WebElement saveBtn;
	
	//Initialization
	public CreateContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public WebElement getFirstNameTxtFld() {
		return fistNameTxtField;
	}

	public WebElement getLastNameTxtFld() {
		return lastNameTxtField;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//Utilization
	public void createContact(String firstName, String lastName)
	{
		firstNameTxtFld.sendKeys(firstName);
		lastNameTxtField.sendKeys(lastName);
		saveBtn.click();
	}
	
}
