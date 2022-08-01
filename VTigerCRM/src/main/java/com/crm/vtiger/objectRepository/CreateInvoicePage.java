package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateInvoicePage
{
	//intialization
	public CreateInvoicePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//declaretion
	@FindBy(name = "subject") private WebElement subjectNameEdt;

	@FindBy(xpath = "//tr[5]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectContactLink;

	@FindBy(name = "invoicedate") private WebElement calender;

	@FindBy(name="button") private WebElement button;

	public WebElement getSubjectNameEdt() {
		return subjectNameEdt;
	}

	public WebElement getSelectContactLink() {
		return selectContactLink;
	}

	public WebElement getCalender() {
		return calender;
	}

	public WebElement getButton() {
		return button;
	}
	
}
