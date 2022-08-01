package com.crm.vtiger.objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericUtility.WebDriverUtility;

//Declaration
public class HomePage extends WebDriverUtility
{
	@FindBy(xpath="//a[@href='index.php?module=Accounts&action=index']")
	private WebElement organisationLink;

	@FindBy(xpath="//a[normalize-space()='Contacts']")
	private WebElement contactsLink;

	@FindBy(xpath="//a[normalize-space()='Opportunities']")
	private WebElement opportunitiesLink;

	@FindBy(xpath="//a[normalize-space()='Products']")
	private WebElement productsLink;

	@FindBy(xpath="//img[@src='themes/softed/images/menuDnArrow.gif']")
	private WebElement moreLink;

	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorLink;

	@FindBy(xpath="//a[normalize-space()='Sign Out']") 
	private WebElement signOut;

	//Initialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);		
	}

	//Utilization
	public WebElement getOranisationLink() {
		return organisationLink;
	}
	public WebElement getContactLink()
	{
		return contactsLink;
	}
	public WebElement getProductLink()
	{
		return productsLink;
	}
	public WebElement getMoreLink()
	{
		return moreLink;
	}
	public WebElement getAdministratorLink() {
		return administratorLink;
	}

	public WebElement getSignOut() {
		return signOut;
	}
	public void clickOrganisationLink()
	{
		organisationLink.click();
	}
	public void Logout(WebDriver driver)
	{
		mouseOveronElement(driver,administratorLink);
		signOut.click();
	}
	
} 

