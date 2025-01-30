package com.user.name.utils;
import java.io.*;
import com.user.name.utils.exceptions.*;
public class Keyboard
{
	private void Keybaord()
	{
	}
	public static String readString()
	{
		String input = null;
		try{
			BufferedReader bufferedReader;
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			input = bufferedReader.readLine();
		}catch(IOException ioException)
		{
		}
		return input;
	}
	public static String readString(String caption)
	{
		System.out.print(caption);
		return readString();
	}
	public static char readChar()
	{
		String str = readString();
		if(str!=null && str.length()>0) return str.charAt(0);
		return (char)0;
	}
	public static char readChar(String caption)
	{
		System.out.print(caption);
		return readChar();
	}
	public static long readLong()
	{
		return Long.parseLong(readString());
	}
	public static long readLong(String caption)
	{
		System.out.print(caption);
		return readLong();
	}
	public static int readInt()
	{
		return Integer.parseInt(readString());
	}
	public static int readInt(String caption)
	{
		System.out.print(caption);
		return readInt();
	}
	public static short readShort()
	{
		return Short.parseShort(readString());
	}
	public static short readShort(String caption)
	{
		System.out.print(caption);
		return readShort();
	}
	public static byte readByte()
	{
		return Byte.parseByte(readString());
	}
	public static byte readByte(String caption)
	{
		System.out.print(caption);
		return readByte();
	}
	public static double readDouble()
	{
		return Double.parseDouble(readString());
	}
	public static double readDouble(String caption)
	{
		System.out.print(caption);
		return readDouble();
	}
	public static float readFloat()
	{
		return Float.parseFloat(readString());
	}
	public static float readFloat(String caption)
	{
		System.out.print(caption);
		return readFloat();
	}
	public static boolean readBoolean()
	{
		String k = readString();
		String i = k.toUpperCase();
		boolean b = Boolean.parseBoolean(i);
		String sb = String.valueOf(b).toUpperCase();
		boolean valid = i.equals(sb);
		if(!valid) 
		{
			throw new BooleanFormatException("Invalid Boolean: "+k);
		}
		else return b;
	}
	public static boolean readBoolean(String caption)
	{
		System.out.println(caption);
		return readBoolean();
	}
	public static java.util.Date readDate()
	{
		//for dd/mm/yyyy format only
		String dateString = readString();
		if(dateString==null || dateString.length()==0)
		{
			throw new DateFormatException("Invalid Date: "+dateString);
		}
		if(ValidationUtility.isDateValid(dateString))
		{
			return ConversionUtility.toDate(dateString);
		}
		else
		{
			throw new DateFormatException("Invalid Date: "+dateString);
		}
	}
	public static java.util.Date readDate(String caption)
	{
		System.out.print(caption);
		return readDate();
	}

}
