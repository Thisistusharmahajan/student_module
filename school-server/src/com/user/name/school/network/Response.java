package com.user.name.school.network;
public class Response implements java.io.Serializable
{
private Object result;
private boolean success;
private long identificationNumber;
private String exceptionClassName;
private String exceptionMessage;
public Response()
{
this.result=null;
this.success=false;
this.identificationNumber=0;
this.exceptionClassName=null;
this.exceptionMessage=null;
}
public void setResult(Object result)
{
this.result=result;
}
public Object getResult()
{
return this.result;
}
public void setSuccess(boolean success)
{
this.success=success;
}
public boolean getSuccess()
{
return this.success;
}
public void setIdentificationNumber(long identificationNumber)
{
this.identificationNumber=identificationNumber;
}
public long getIdentificationNumber()
{
return this.identificationNumber;
}
public void setExceptionClassName(String exceptionClassName)
{
this.exceptionClassName=exceptionClassName;
}
public String getExceptionClassName()
{
return this.exceptionClassName;
}
public void setExceptionMessage(String exceptionMessage)
{
this.exceptionMessage=exceptionMessage;
}
public String getExceptionMessage()
{
return this.exceptionMessage;
}
}
