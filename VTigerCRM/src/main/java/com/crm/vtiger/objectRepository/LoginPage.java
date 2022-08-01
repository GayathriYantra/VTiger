package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{	
	//Declaration
	@FindBy(name="user_name") private WebElement userNameTxtField;
	@FindBy(name="user_password") private WebElement passwordTxtField;
	@FindBy(id="submitButton") private WebElement loginBtn;
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//Implementation
	
	public WebElement getUserNameTxtField() {
		return userNameTxtField;
	}

	public WebElement getPasswordTxtField() {
		return passwordTxtField;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	public void loginToApp(String userName,String password)
	{
		userNameTxtField.sendKeys(userName);
		passwordTxtField.sendKeys(password);
		loginBtn.click();
	}
	
}
