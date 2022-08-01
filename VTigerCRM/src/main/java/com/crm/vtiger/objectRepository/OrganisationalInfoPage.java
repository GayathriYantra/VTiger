package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationalInfoPage
{

	//Declaration
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement organisationInfoText;	

	//Initialization
	public OrganisationalInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Utilization
	public WebElement getOrganisationInfoText() {
		return organisationInfoText;


	}
}
