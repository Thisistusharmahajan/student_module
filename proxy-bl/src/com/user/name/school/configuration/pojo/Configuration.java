package com.user.name.school.configuration.pojo;
public class Configuration implements java.io.Serializable
{
private String server;
private int port;
public Configuration()
{
this.server=null;
this.port=0;
}
public void setServer(String server)
{
this.server=server;
}
public String getServer()
{
return this.server;
}
public void setPort(int port)
{
this.port=port;
}
public int getPort()
{
return this.port;
}
}
