package com.crm.vtiger.objectRepository;

public class repo 
{
	[11:13 pm, 31/07/2022] Srimanth_Testyantra: public class ComposeMailPage extends WebDriverUtility {

		//intialization
		public ComposeMailPage(WebDriver driver)
		{
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(xpath = "//td[2]//input[@name='parent_name']") private WebElement ccMailTF;

		@FindBy(xpath = "//tr//td[2]//input[@name='bccmail']") private WebElement bccMailTF;

		@FindBy(xpath = "//tr//td[2]//input[@name='subject']") private WebElement subjectTF;

		@FindBy(name = "file_0") private WebElement fileUpLoad;

		@FindBy(name = "Send") private WebElement sendButton;

		@FindBy(name = "parent_type") private WebElement dropdown;

		@FindBy(xpath ="//img[@title='Select']") private WebElement selectLink;

		@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

		@FindBy(name="search") private WebElement searchNowButton;

		@FindBy(name ="search_field") private WebElement emailDropdown;


		@FindBy(xpath = "//a[.='Compose']") private WebElement composeLink;

		//utilization
		public WebElement getComposeLink() {
			return composeLink;
		}

		public WebElement getFileUpLoad() {
			return fileUpLoad;
		}

		public WebElement getEmailDropdown() {
			return emailDropdown;
		}


		public WebElement getCcMailTF() {
			return ccMailTF;
		}

		public WebElement getBccMailTF() {
			return bccMailTF;
		}

		public WebElement getSubjectTF() {
			return subjectTF;
		}

		public WebElement getSendButton() {
			return sendButton;
		}

		public WebElement getDropdown() {
			return dropdown;
		}

		public WebElement getSelectLink() {
			return selectLink;
		}

		public WebElement getSearchTF() {
			return searchTF;
		}

		public WebElement getSearchNowButton() {
			return searchNowButton;
		}

		public void composeAMail(String bccMail,String ccMail,String subject,String filePath,WebDriver driver) {
			composeLink.click();
			switchToWindow("Emails&action=Emails", driver);
			bccMailTF.sendKeys(bccMail);
			ccMailTF.sendKeys(ccMail);
			subjectTF.sendKeys(subject);
			fileUpLoad.sendKeys(filePath);
			switchToWindow("Emails&action=index", driver);
		}

		public void selectDropdown(String dropdownValue) {
			select(dropdown, dropdownValue);
		}

		public void selectMail(String emailID,String dropdownValue,WebDriver driver,String vendorName) {
			composeLink.click();
			switchToWindow("Emails&action=Emails", driver);
			selectLink.click();
			switchToWindow("Vendors&action", driver);
			searchTF.sendKeys(emailID);
			select(emailDropdown, dropdownValue);
			searchNowButton.click();
			driver.findElement(By.xpath("//a[.='"+vendorName+"']")).click();
			switchToWindow("Emails&action=index", driver);
		}

	}
	[11:14 pm, 31/07/2022] Srimanth_Testyantra: package com.crm.Vtiger.ObjectRepository;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class ContactInfoPage {
		//intialization
		public ContactInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		//declaretion
		@FindBy(xpath = "//*[@class='dvHeaderText']") private WebElement createdContactName;

		//utilization
		public WebElement getCreatedContactName() {
			return createdContactName;
		}
		
		
		
		
		public class ContactPage {
			//intialization
			public ContactPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}

			//declaretion
			@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

			//utilization
			public WebElement getCreateContactName() {
				return createContactName;
			}

			
			public class CreateContactPage extends WebDriverUtility{

				//intialization
				public CreateContactPage(WebDriver driver) {
					PageFactory.initElements(driver, this);
				}

				//declaretion
				@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactName;

				@FindBy(name="salutationtype") private WebElement firstNameDropdown;

				@FindBy(xpath = "//input[@name='account_name']/..//img[@title=\\\"Select\\\"]") private WebElement organizationLink;

				@FindBy(name="firstname") private WebElement firstNameEdt;

				@FindBy(name="lastname") private WebElement lastNameEdt;

				@FindBy(name="button") private WebElement saveButton;

				@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

				@FindBy(name="search") private WebElement searchNowButton;
				//utilization
				public WebElement getCreateContactName() {
					return createContactName;
				}

				public WebElement getFirstNameDropdown() {
					return firstNameDropdown;
				}

				public WebElement getFirstNameEdt() {
					return firstNameEdt;
				}

				public WebElement getLastNameEdt() {
					return lastNameEdt;
				}

				public WebElement getSaveButton() {
					return saveButton;
				}

				public WebElement getOrganizationLink() {
					return organizationLink;
				}

				public WebElement getSearchTF() {
					return searchTF;
				}

				public WebElement getSearchNowButton() {
					return searchNowButton;
				}

				public void createContacts(String firstName,String lastName) {
					firstNameEdt.sendKeys(firstName);
					lastNameEdt.sendKeys(lastName);
				}
				public void selectOrganization(WebDriver driver,String OrgName) {
					organizationLink.click();
					switchToWindow(driver, "Accounts&action");
					searchTF.sendKeys(OrgName);
					searchNowButton.click();
					driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
					switchToWindow("Contacts&action", driver);
				}
				
				public class CreateDocumentPage {
					//intialization
						public CreateDocumentPage(WebDriver driver)
						{
							PageFactory.initElements(driver, this);
						}

						//declaretion
						@FindBy(name = "notes_title") private WebElement titleEdt;
				 
						@FindBy(xpath = "//iframe[contains(@title,'Rich text editor')]") private WebElement frame;
						
						@FindBy(name = "filename") private WebElement fileUpload;
						
						@FindBy(name="button") private WebElement saveButton;

						//utilization
						public WebElement getTitleEdt() {
							return titleEdt;
						}

						public WebElement getFrame() {
							return frame;
						}

						public WebElement getFileUpload() {
							return fileUpload;
						}

						public WebElement getSaveButton() {
							return saveButton;
						}
						
						public void createDocument(String title,String filePath,String frameText) {
							titleEdt.sendKeys(title);
							frame.sendKeys(frameText);
							fileUpload.sendKeys(filePath);
						}

						
						[11:14 pm, 31/07/2022] Srimanth_Testyantra: public class CreateInvoicePage extends WebDriverUtility {

							//intialization
							public CreateInvoicePage(WebDriver driver)
							{
								PageFactory.initElements(driver, this);
							}

							//declaretion
							@FindBy(name = "subject") private WebElement subjectNameEdt;

							@FindBy(xpath = "//tr[5]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectContactLink;

							@FindBy(name = "invoicedate") private WebElement calender;

							@FindBy(name="button") private WebElement saveButton;

							@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

							@FindBy(name="search") private WebElement searchNowButton;

							@FindBy(xpath = "//tr[8]//td[2]//img[@src='themes/softed/images/select.gif']") private WebElement selectOrganizationLink;

							@FindBy(name="bill_street") private WebElement billingAddressEdt;

							@FindBy(name="ship_street") private WebElement shippingAddressEdt;

							@FindBy(name="searchIcon1") private WebElement selectProductLink;

							@FindBy(name="qty1") private WebElement qtyEdt;

							@FindBy(id ="listPrice1") private WebElement priceEdt;

							//utilization
							public WebElement getSubjectNameEdt() {
								return subjectNameEdt;
							}

							public WebElement getSelectContactLink() {
								return selectContactLink;
							}

							public WebElement getCalender() {
								return calender;
							}

							public WebElement getSaveButton() {
								return saveButton;
							}

							public WebElement getSearchTF() {
								return searchTF;
							}

							public WebElement getSearchNowButton() {
								return searchNowButton;
							}

							public WebElement getSelectOrganizationLink() {
								return selectOrganizationLink;
							}

							public WebElement getBillingAddressEdt() {
								return billingAddressEdt;
							}

							public WebElement getShippingAddressEdt() {
								return shippingAddressEdt;
							}

							public WebElement getSelectProductLink() {
								return selectProductLink;
							}

							public WebElement getQtyEdt() {
								return qtyEdt;
							}

							public WebElement getPriceEdt() {
								return priceEdt;
							}
							
							public void createInvoice(String subject,String billingAddress,String shippingAddress,String qty,String amount,String date) {
								subjectNameEdt.sendKeys(subject);
								billingAddressEdt.sendKeys(billingAddress);
								shippingAddressEdt.sendKeys(shippingAddress);
								calender.clear();
								calender.sendKeys(date);
								priceEdt.clear();;
								priceEdt.sendKeys(amount);
								qtyEdt.sendKeys(qty);		
							}
							
							public void selectOrganization(WebDriver driver,String OrgName) {
								selectOrganizationLink.click();
								switchToWindow(driver, "Accounts&action");
								searchTF.sendKeys(OrgName);
								searchNowButton.click();
								driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
								switchToWindow("Invoice&action", driver);
							}
							
							public void selectContact(WebDriver driver,String contactName) {
								selectContactLink.click();
								switchToWindow(driver, "Contacts&action");
								searchTF.sendKeys(contactName);
								searchNowButton.click();
								driver.findElement(By.xpath("//a[.='"+contactName+"']")).click();
								switchToWindow("Invoice&action", driver);
							}
							
							public void selectProduct(WebDriver driver,String productName) {
								selectProductLink.click();
								switchToWindow(driver, "Products&action");
								searchTF.sendKeys(productName);
								searchNowButton.click();
								driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
								switchToWindow("Invoice&action", driver);
							}

						}
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

							@FindBy(name="button") private WebElement saveButton;

							@FindBy(xpath="//input[@name='search_text']") private WebElement searchTF;

							@FindBy(name="search") private WebElement searchNowButton;

							@FindBy(name="amount") private WebElement amountTF;

							@FindBy(xpath="//input[@name='closingdate']") private WebElement calenderTF;

							@FindBy(name="//tr[8]//img[@src='themes/softed/images/select.gif']") private WebElement selectCampaignSource;

							//utilization
							public WebElement getOpportunityNameEdt() {
								return opportunityNameEdt;
							}

							public WebElement getReletedToDropdown() {
								return reletedToDropdown;
							}

							public WebElement getSelectOption() {
								return selectOption;
							}

							public WebElement getSaveButton() {
								return saveButton;
							}

							public WebElement getSearchTF() {
								return searchTF;
							}

							public WebElement getSearchNowButton() {
								return searchNowButton;
							}

							public WebElement getAmountTF() {
								return amountTF;
							}

							public WebElement getCalenderTF() {
								return calenderTF;
							}

							public WebElement getSelectCampaignSource() {
								return selectCampaignSource;
							}

							public void createOpportunity(String oppurtunityName,String amount,String date) {
								opportunityNameEdt.sendKeys(oppurtunityName);
								amountTF.sendKeys(amount);
								calenderTF.clear();
								calenderTF.sendKeys(date);
							}

							public void selectContacts(WebDriver driver,String contactsName) {
								selectOption.click();
								switchToWindow("Contacts&action", driver);
								searchTF.sendKeys(contactsName);
								searchNowButton.click();
								driver.findElement(By.xpath("//a[.='"+contactsName+"']")).click();
								switchToWindow("Potentials&action", driver);
							}

							public void selectOrganization(WebDriver driver,String OrgName) {
								selectOption.click();
								switchToWindow(driver, "Accounts&action");
								searchTF.sendKeys(OrgName);
								searchNowButton.click();
								driver.findElement(By.xpath("//a[.='"+OrgName+"']")).click();
								switchToWindow("Potentials&action", driver);
							}

							public void selectCampaignSource(WebDriver driver,String CampaignName) {
								selectCampaignSource.click();
								switchToWindow(driver, "Campaigns&action");
								searchTF.sendKeys(CampaignName);
								searchNowButton.click();
								driver.findElement(By.xpath("//a[.='"+CampaignName+"']")).click();
								switchToWindow("Potentials&action", driver);
							}

							
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

								public WebElement getIndustryDropdown() {
									return industryDropdown;
								}

								public WebElement getTypeDropdown() {
									return typeDropdown;
								}

								public WebElement getSaveButton() {
									return saveButton;
								}

								public void getOrganizationName(String organizationName) {
									organizationNameEdt.sendKeys(organizationName);
								}
}
							
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



								
								public class DocumentInfoPage {

									//intialization
									public DocumentInfoPage(WebDriver driver)
									{
										PageFactory.initElements(driver, this);
									}

									//declaretion
									@FindBy(xpath = "//span[@class='dvHeaderText']") private WebElement createdDocumentTitle;

									//utilization
									public WebElement getCreatedDocumentTitle() {
										return createdDocumentTitle;
									}

									
									public class DocumentInfoPage {

										//intialization
										public DocumentInfoPage(WebDriver driver)
										{
											PageFactory.initElements(driver, this);
										}

										//declaretion
										@FindBy(xpath = "//span[@class='dvHeaderText']") private WebElement createdDocumentTitle;

										//utilization
										public WebElement getCreatedDocumentTitle() {
											return createdDocumentTitle;
										}


										
										public class ProductPage {

											//intialization
											public ProductPage(WebDriver driver)
											{
												PageFactory.initElements(driver, this);
											}

											//declaretion
											@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement productLink;

											//utilization
											public WebElement getProductLink() {
												return productLink;
											}

											
											public class OpportunityPage {
												//intialization
												public OpportunityPage(WebDriver driver) {
													PageFactory.initElements(driver, this);
												}

												//declaretion
												@FindBy(xpath = "//img[@title='Create Opportunity...']") private WebElement createOpportunityLink;

												//utilization
												public WebElement getCreateOpportunityLink() {
													return createOpportunityLink;
												}
												
												public class InvoicePage {

													//intialization
													public InvoicePage(WebDriver driver)
													{
														PageFactory.initElements(driver, this);
													}


													//declaretion
													@FindBy(xpath = "//img[@title='Create Invoice...']") private WebElement createInvoiceLink;
													

													//utilization
													public WebElement getCreateInvoiceLink() {
														return createInvoiceLink;
													}
													
													
													import org.openqa.selenium.support.PageFactory;

													public class InvoicePage {

														//intialization
														public InvoicePage(WebDriver driver)
														{
															PageFactory.initElements(driver, this);
														}


														//declaretion
														@FindBy(xpath = "//img[@title='Create Invoice...']") private WebElement createInvoiceLink;
														

														//utilization
														public WebElement getCreateInvoiceLink() {
															return createInvoiceLink;
														}
