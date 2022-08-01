package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericUtility.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility
{
	//Declaration
	@FindBy(name="accountname") private WebElement organizationNameTxtField;
	@FindBy(xpath="//select[contains(@name,'industry')]") private WebElement industryDrDwn;
	@FindBy(xpath="//select[@name='accounttype']") private WebElement typeDrDwn;
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")private WebElement saveBtn;
	
	//Initialization
		public CreateOrganizationPage(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}
	
		//Utilization	
		public WebElement getOrganizationNameTxtField() {
			return organizationNameTxtField;
		}
		public WebElement getSaveBtn() {
			return saveBtn;
		}
		public WebElement getIndustryDrDwn() {
			return industryDrDwn;
		}

		public WebElement getTypeDrDwn() {
			return typeDrDwn;
		}

		public void enterOrgname(String orgName)
		{
			organizationNameTxtField.sendKeys(orgName);
		}
		public void selectIndustry(String industry)
		{
			select(industryDrDwn,industry);
		}
		public void selectType(String type)
		{
			select(typeDrDwn,type);
		}
		public void clickSaveBtn()
		{
			saveBtn.click();
		}
}
