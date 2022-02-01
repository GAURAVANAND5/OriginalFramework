package com.crm.GenericLibaray;

import java.sql.Date;
import java.util.Random;
/**
 * This class will contains java specific generic libraries
 * @author GAURAV
 *
 */

public class JavaUtility {
	/**
	 * This method is used to generate the random number
	 * @return
	 */
	
	public int getRandomNumber()
	{
		Random ran=new Random();
		int random = ran.nextInt(1000);
		return random;
	}
	/**
	 * This method is used to get the current system date
	 * @return
	 */
	
	public  String getCurrentDate()
	{
		Date date=new Date(0);
		String currentDate=date.toString();
		return currentDate;
	}
	/**
	 * This method is used to get the date in specific format yyyy/mm/dd
	 * @return
	 */
	public String getFinalDateFromat()
	{
	
		Date date=new Date(0);
		String d = date.toString();
		String[] dte = d.split(" ");
		String YYYY = dte[5];
		String DD = dte[2];
		String MM = dte[1];
		String today = YYYY+"-"+MM+"-"+DD;
		return today;
	}
	


}
