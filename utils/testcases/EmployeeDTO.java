package com.user.name.hr.dl;
public class EmployeeDTO implements Comparable<EmployeeDTO>
{
private String gender;
private java.util.Date dateOfBirth;
private String panNumber;
private String title;
private String aadhaarCardNumber;
private BigDecimal basicSalary;
private boolean isIndian;
private String name;
private int designationCode;
private String id;
public EmployeeDTO()
{
this.gender=null;
this.dateOfBirth=null;
this.panNumber=null;
this.title=null;
this.aadhaarCardNumber=null;
this.basicSalary=null;
this.isIndian=false;
this.name=null;
this.designationCode=0;
this.id=null;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return this.gender;
}
public void setDateOfBirth(java.util.Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public java.util.Date getDateOfBirth()
{
return this.dateOfBirth;
}
public void setPanNumber(String panNumber)
{
this.panNumber=panNumber;
}
public String getPanNumber()
{
return this.panNumber;
}
public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}
public void setAadhaarCardNumber(String aadhaarCardNumber)
{
this.aadhaarCardNumber=aadhaarCardNumber;
}
public String getAadhaarCardNumber()
{
return this.aadhaarCardNumber;
}
public void setBasicSalary(BigDecimal basicSalary)
{
this.basicSalary=basicSalary;
}
public BigDecimal getBasicSalary()
{
return this.basicSalary;
}
public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}
public boolean getIsIndian()
{
return this.isIndian;
}
public void setName(String name)
{
this.name=name;
}
public String getName()
{
return this.name;
}
public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}
public void setId(String id)
{
this.id=id;
}
public String getId()
{
return this.id;
}
}
