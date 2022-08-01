package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationPage
{
	//Declaration
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement organisationLkpIcon;

	//Initialization
	public OrganisationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getOrganisationLkpIcon()
	{
		return organisationLkpIcon;
	}
	public void clickOrganisationLkpIcon()
	{
		organisationLkpIcon.click();
	}
}
