package com.user.name.utils;
import com.user.name.utils.exceptions.*;

public class ConversionUtility
{
	private ConversionUtility()
	{
	}
	public static java.util.Date toDate(String dateString)
	{
		if(!ValidationUtility.isDateValid(dateString))
		{
			throw new DateFormatException("Invalid Date: "+dateString);
		}
		String parts[] = dateString.split("/");
		int dd = Integer.parseInt(parts[0]);
		int mm = Integer.parseInt(parts[1]);
		int yy = Integer.parseInt(parts[2]);
		return new java.util.Date(yy-1900, mm -1, dd);
	}
}
