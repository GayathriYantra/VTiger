package com.crm.GenericUtility;

public interface IConstants 
{
	String filePath="./src/test/resources/commondata.properties"; 
	String excelPath="./src/test/resources/sdet37TestData.xlsx";
	String chromeKey="webdriver.chrome.driver";
	String chromeValue="./src/main/resources/chromedriver.exe";
	String firefoxKey="webdriver.firefox.driver";
	String firefoxValue="./src/main/resources/geckodriver.exe";
	String dbName="Employees";
	String dbURL="jdbc:mysql://localhost:3306/";
	String dbUsername="root";
	String dbPassword="root";
	int implicitWaitDuration=10;
	int explicitWaitDuration=10;	
}
