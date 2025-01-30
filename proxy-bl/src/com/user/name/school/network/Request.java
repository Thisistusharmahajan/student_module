package com.user.name.school.network;

public class Request implements java.io.Serializable 
{
private Object objects[];
private long identificationNumber;
private String action;
private String entity;
public Request()
{
this.objects=null;
this.identificationNumber=0;
this.action=null;
this.entity=null;
}
public void setObjects(Object ...objects)
{
this.objects=objects;
}
public Object[] getObjects()
{ 
return this.objects;
}
public void setIdentificationNumber(long identificationNumber)
{
this.identificationNumber=identificationNumber;
}
public long getIdentificationNumber()
{
return this.identificationNumber;
}
public void setAction(String action)
{
this.action=action;
}
public String getAction()
{
return this.action;
}
public void setEntity(String entity)
{
this.entity=entity;
}
public String getEntity()
{
return this.entity;
}
}
