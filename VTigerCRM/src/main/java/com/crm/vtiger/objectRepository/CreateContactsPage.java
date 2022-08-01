package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateContactsPage 
{
	//Initialization
		public LoginPage(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}
}
