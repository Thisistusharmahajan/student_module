package com.user.name.utils;

public class ValidationUtility
{
	private ValidationUtility()
	{
	}
	public static boolean isDateValid(String dateOfBirthString)
	{
		String pcs[];
		pcs = dateOfBirthString.split("/");
		if(pcs.length!=3) return false;
		try
		{
			int dd = Integer.parseInt(pcs[0]);
			int mm = Integer.parseInt(pcs[1]);
			int yy = Integer.parseInt(pcs[2]);
			if(mm<1 || mm>12) return false;
			if(dd<1) return false;
			java.util.Date d = new java.util.Date(yy-1900,mm-1,dd);
			if(d.getDate()==dd && d.getMonth()+1==mm && d.getYear()+1900==yy)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(NumberFormatException nfe)
		{
			return false;
		}
	}
}
