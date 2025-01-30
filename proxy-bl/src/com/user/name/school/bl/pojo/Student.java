package com.user.name.school.bl.pojo;
import com.user.name.school.bl.pojo.interfaces.*;
public class Student implements StudentInterface
{
	private int rollNumber;
	private String name;
	private char gender;
	private boolean isIndian;
	private java.util.Date dateOfBirth;
	public Student()
	{
		this.rollNumber=0;
		this.name=null;
		this.gender=' ';
		this.isIndian = false;
		this.dateOfBirth=null;
	}
	public void setRollNumber(int rollNumber)
	{
		this.rollNumber = rollNumber;
	}
	public int getRollNumber()
	{
		return this.rollNumber;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	public void setGender(char gender)
	{
		this.gender=gender;
	}
	public char getGender()
	{
		return this.gender;
	}

	public void setIsIndian(boolean isIndian)
	{
		this.isIndian=isIndian;
	}
	public boolean getIsIndian()
	{
		return this.isIndian;
	}
	public void setDateOfBirth(java.util.Date dateOfBirth)
	{
		this.dateOfBirth=dateOfBirth;
	}
	public java.util.Date getDateOfBirth()
	{
		return this.dateOfBirth;
	}
	public int getAge()
	{
		int dd=this.dateOfBirth.getYear();
		int mm=this.dateOfBirth.getMonth();
		int yy=this.dateOfBirth.getDay();
		java.util.Date today = new java.util.Date();
		int todayyy = today.getYear();
		int age = todayyy-yy;
		java.util.Date t = new java.util.Date(todayyy,mm,dd);
		if(today.compareTo(t)<0)
		{
			age--;
		}
		return age;
	}
	public boolean equals(Object object)
	{
		if(!(object instanceof StudentInterface)) return false;
		StudentInterface studentInterface;
		studentInterface = (StudentInterface)object;
		return this.rollNumber==studentInterface.getRollNumber();
	}
	public int compareTo(StudentInterface studentInterface)
	{
		return this.rollNumber-studentInterface.getRollNumber();
	}
}

