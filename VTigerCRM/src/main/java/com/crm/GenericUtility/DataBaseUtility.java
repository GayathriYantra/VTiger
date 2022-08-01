package com.crm.GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;
/**
 * 
 * @author admin
 *
 */
public class DataBaseUtility
{
	static Driver driverRef;
	static Connection connection;
	static ResultSet result;
	/**
	 * this method is used to connect to DB
	 */
	public void connectToDB()
	{

		try {
			driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			connection=DriverManager.getConnection(IConstants.dbURL+IConstants.dbName,IConstants.dbUsername,IConstants.dbPassword);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * this method is used to close the DB
	 */
	public void closeDB()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	/**
	 * this method is used to perform execute query
	 * @param query
	 * @param colNum
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public  boolean executeQuery(String query,int colNum, String expectedData) throws Throwable
	{
		result=connection.createStatement().executeQuery(query);
		boolean flag=false;
		while(result.next())
		{

			{
				if(result.getString(colNum).equals(expectedData))
					flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println("data is presnt");
			return flag;
		}else
		{
			System.out.println("data is not present");
			return flag;
		}

	}
	/**
	 * this method is used to perform execute update
	 * @param query
	 * @throws Throwable
	 */
	public void executeUpdateQuery(String query) throws Throwable
	{
		int res=connection.createStatement().executeUpdate(query);
		if(res==1)
		{
			System.out.println("data is updated");
		}else {
			System.out.println("data is not updated");
		}
	}

}

