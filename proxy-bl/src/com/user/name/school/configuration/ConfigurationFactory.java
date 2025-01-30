package com.user.name.school.configuration;
import com.user.name.school.configuration.exceptions.*;
import com.user.name.school.configuration.pojo.*;
import java.io.*;
public class ConfigurationFactory
{
	private static Configuration configuration = null;
        static
	{
		try
		{
			loadConfiguration();
		}catch(ConfigurationException configurationException)
		{ 
			System.out.println(configurationException.getMessage());
			System.exit(0); //Application ends system class ki static method exit ko call kiya
		}
	}
	private ConfigurationFactory()
	{
	}
	private static void loadConfiguration() throws ConfigurationException 
	{
		try
		{
			File file = new File("school.cfg");
			RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
			if(file.exists()==false)
			{
				System.out.println("School.cfg missing");
				throw new ConfigurationException("school.cfg missing, read documentation");
			}
			randomAccessFile = new RandomAccessFile(file,"rw");
			String server = null;
			int port = 0;
			String line;
			String pcs[];
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				line = randomAccessFile.readLine();
				pcs = line.split(":");
				if(pcs.length!=2) 
				{
					randomAccessFile.close();
throw new ConfigurationException("school.cfg contains invalid format : "+line+", read documentation");
				}
			 	pcs[0] = pcs[0].trim();
				pcs[1] = pcs[1].trim();
				if(pcs[0].equalsIgnoreCase("server"))
				{
					server = pcs[1];
				}	
				else if(pcs[0].equalsIgnoreCase("port"))
				{
					try
					{
					port = Integer.parseInt(pcs[1]);
					}catch(NumberFormatException nfe)
					{ 
						randomAccessFile.close();
						throw new ConfigurationException("Invalid port: "+pcs[1]);
					}
				}
				if(server!=null && port!=0) break;
 			} 
			randomAccessFile.close();
			if(server==null && port==0) 
			{ 
				throw new ConfigurationException("school.cfg not properly written, read documentation");
			}
			configuration  = new Configuration();
			configuration.setServer(server);
			configuration.setPort(port);
		}catch(IOException ioException)
		{
			throw new ConfigurationException(ioException.getMessage());
		}
	}
	public static Configuration getConfiguration()
	{
		//logic to load configuration, this is one way to do it
		//if(configuration!=null) return configuration;
		//other way to do it
		 return configuration;
	}
}
