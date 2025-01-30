package com.user.name.school.bl.pojo.interfaces;

public interface StudentInterface extends java.io.Serializable, Comparable<StudentInterface> 
{
	public void setRollNumber(int rollNumber);
	public int getRollNumber();

	public void setName(String name);
	public String getName();

	public void setGender(char gender);
	public char getGender();

	public void setIsIndian(boolean isIndian);
	public boolean getIsIndian();

	public void setDateOfBirth(java.util.Date dateOfBirth);
	public java.util.Date getDateOfBirth();

	public int getAge();
}
