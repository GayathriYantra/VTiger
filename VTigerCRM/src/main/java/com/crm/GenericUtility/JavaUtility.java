package com.crm.GenericUtility;


import java.util.Date;
import java.util.Random;
/**
 * 
 * @author admin
 *
 */
public class JavaUtility 
{
	/**
	 * To get the random number	
	 * @return
	 */
	public int getRandomNum()
	{
		Random random=new Random();
		int randNum=random.nextInt(1000);
		return randNum;
	}
	/**
	 * to get the system date and time
	 * @return
	 */
	public String getSystemDate()
	{
		Date date=new Date();
		String datetime=date.toString();
		return datetime;
	}
	/**
	 * 
	 * @return
	 */
	public String getSystemDateFormat()
	{
		Date date1=new Date();
		String dateTime=date1.toString();
		String[] dateArr = dateTime.split(" ");

		int month = date1.getMonth()+1;
		String date=dateArr[2];
		String year=dateArr[5];
		int day=date1.getDay();
		String time=dateArr[3];

		String finalFormat= month+" "+date+" "+year+" "+day+" "+time;
		return finalFormat;
	}
}
