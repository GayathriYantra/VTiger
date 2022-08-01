package com.crm.vtiger.objectRepository;

public class InvoicePage
{
	 //intialization
		public ComposeMailPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(xpath = "//td[2]//input[@name='parent_name']") private WebElement ccMailTF;

		@FindBy(xpath = "//tr//td[2]//input[@name='bccmail']") private WebElement bccMailTF;

		@FindBy(xpath = "//tr//td[2]//input[@name='subject']") private WebElement subjectTF;

		@FindBy(name = "file_0") priv…
	
	
	


	}
	

	public class ContactPage {
		


	}
	[11:14 pm, 31/07/2022] Srimanth_Testyantra: package com.crm.Vtiger.ObjectRepository;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import com.crm.Vtiger.GenericUtility.WebDriverUtility;

	public class CreateContactPage extends WebDriverUtility{

		//intialization
		public CreateContactPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

		@FindBy(name="salutationtype") private WebElement firstNameDropdown;

		@FindBy(xpath = "//input[@name='account_name']/..//img[@title=\\\"Select\\\"]") private WebElement organizationLink;

		@Fin…
	[11:14 pm, 31/07/2022] Srimanth_Testyantra: package com.crm.Vtiger.ObjectRepository;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import com.crm.Vtiger.GenericUtility.WebDriverUtility;

	public class CreateInvoicePage extends WebDriverUtility {

	
	[11:14 pm, 31/07/2022] Srimanth_Testyantra: package com.crm.Vtiger.ObjectRepository;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import com.crm.Vtiger.GenericUtility.WebDriverUtility;

	public class CreateOpportunityPage extends WebDriverUtility{
		//intialization
		public CreateOpportunityPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(name = "potentialname") private WebElement opportunityNameEdt;

		@FindBy(name="related_to_type") private WebElement reletedToDropdown;

		@FindBy(xpath="//tr[4]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectOption;

		@FindBy(name="button") …
	[11:14 pm, 31/07/2022] Srimanth_Testyantra: package com.crm.Vtiger.ObjectRepository;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class CreateOrganizationPage {
		//intialization
		public CreateOrganizationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(name = "accountname") private WebElement organizationNameEdt ;

		@FindBy(name = "industry") private WebElement industryDropdown ;

		@FindBy(name = "accounttype") private WebElement typeDropdown ;

		@FindBy(name = "button") private WebElement saveButton ;

		//utilization
		public WebElement getOrganizationNameEdt() {
			return organizationNameEdt;
		}

		public WebElement getIndustry…
	[11:14 pm, 31/07/2022] Srimanth_Testyantra: package com.crm.Vtiger.ObjectRepository;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class CreateProductPage {
		//intialization
		public CreateProductPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(name = "productname") private WebElement productNameEdt;

		//utilization
		public WebElement getProductNameEdt() {
			return productNameEdt;
		}




	}
	
	public class DocumentInfoPage {

		

}
