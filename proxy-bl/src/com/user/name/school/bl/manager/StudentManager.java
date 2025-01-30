
// THIS IS PROXY_BL LAYER
package com.user.name.school.bl.manager;
import com.user.name.school.bl.manager.interfaces.*;
import com.user.name.school.bl.exceptions.*;
import com.user.name.school.bl.pojo.interfaces.*;
import com.user.name.school.bl.pojo.*;
import com.user.name.school.network.*;
import com.user.name.school.network.exceptions.*; 
import java.util.*;
public class StudentManager implements StudentManagerInterface
{
	public void add(StudentInterface studentInterface) throws BLException
	{
		Response response=null;
	  	Request request = new Request();
		request.setEntity("student");
		request.setAction("add");
		request.setObjects(studentInterface);
		try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Network Exception while sending request...in StudentManager");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in add method in proxy-bl StudentManager");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				//this will never happen unless there are programming logic mistake done
				//by the programmer of server side BL
				System.out.println("Logical Mistake, this should not happen");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
		}
	}
	public void update(StudentInterface studentInterface) throws BLException
	{
		Response response=null;
	  	Request request = new Request();
		request.setEntity("student");
		request.setAction("update");
		request.setObjects(studentInterface);
		try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Network Exception while sending request...in StudentManager");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in update method in proxy-bl StudentManager");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				//this will never happen unless there are programming logic mistake done
				//by the programmer of server side BL
				System.out.println("Logical Mistake, this should not happen");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
		}
	}
	public void remove(int rollNumber) throws BLException
	{	
		Response response=null;
	  	Request request = new Request();
		request.setEntity("student");
		request.setAction("remove");
		request.setObjects(rollNumber);
		try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Network Exception while sending request...in StudentManager");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in add method in proxy-bl StudentManager");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				//this will never happen unless there are programming logic mistake done
				//by the programmer of server side BL
				System.out.println("Logical Mistake, this should not happen");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
		}
	}
	public StudentInterface getByRollNumber(int rollNumber) throws BLException
	{
		Response response=null;
	  	Request request = new Request();
		request.setEntity("student");
		request.setAction("getByRollNumber");
		request.setObjects(rollNumber);
		try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Network Exception while sending request...in StudentManager");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in add method in proxy-bl StudentManager");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				//this will never happen unless there are programming logic mistake done
				//by the programmer of server side BL
				System.out.println("Logical Mistake, this should not happen");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
		}
		return (StudentInterface)response.getResult();
	}

	public List<StudentInterface> getAll() throws BLException
	{	
		Response response=null;
		Request request = new Request();
		request.setEntity("student");
		request.setAction("getAll");
		request.setObjects();
	 	try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Exception in proxy-bl getAll while sending request");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in proxy-bl getAll...(2)");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				System.out.println("RUNTIME EXCEPTION!!!");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
 		}
			return (List<StudentInterface>)response.getResult();
        }
	
	public List<StudentInterface> getByDateOfBirth(java.util.Date dateOfBirth) throws BLException
	{	
		Response response=null;
		Request request = new Request();
		request.setEntity("student");
		request.setAction("getByDateOfBirth");
		request.setObjects(dateOfBirth);
	 	try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Exception in proxy-bl getAll while sending request");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in proxy-bl getAll...(2)");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				System.out.println("RUNTIME EXCEPTION!!!");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
 		}
			return (List<StudentInterface>)response.getResult();
	}
	public List<StudentInterface> getByGender(char gender) throws BLException
	{
		Response response=null;
		Request request = new Request();
		request.setEntity("student");
		request.setAction("getByGender");
		request.setObjects(gender);
	 	try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Exception in proxy-bl getAll while sending request");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in proxy-bl getAll...(2)");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				System.out.println("RUNTIME EXCEPTION!!!");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
 		}
			return (List<StudentInterface>)response.getResult();
	}
    public List<StudentInterface> getIndians() throws BLException
    {
		Response response=null;
		Request request = new Request();
		request.setEntity("student");
		request.setAction("getIndians");
		request.setObjects();
	 	try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Exception in proxy-bl getAll while sending request");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in proxy-bl getAll...(2)");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				System.out.println("RUNTIME EXCEPTION!!!");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
 		}
			return (List<StudentInterface>)response.getResult();
	}
    public List<StudentInterface> getNonIndians() throws BLException
	{	
		Response response=null;
		Request request = new Request();
		request.setEntity("student");
		request.setAction("getNonIndians");
		request.setObjects();
	 	try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Exception in proxy-bl getAll while sending request");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in proxy-bl getAll...(2)");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				System.out.println("RUNTIME EXCEPTION!!!");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
 		}
			return (List<StudentInterface>)response.getResult();	
	}
	public List<StudentInterface> getByAge(int age) throws BLException
	{
		Response response=null;
		Request request = new Request();
		request.setEntity("student");
		request.setAction("getByAge");
		request.setObjects(age);
	 	try
		{
		response = NetworkClientUtility.sendRequest(request);
		}catch(NetworkException networkException)
		{
			System.out.println("Exception in proxy-bl getAll while sending request");
			throw new BLException(networkException.getMessage());
		}
		if(response.getSuccess()==false)
		{
	if(response.getExceptionClassName().equals("com.user.name.school.bl.exceptions.BLException"))
			{
				System.out.println("Exception in proxy-bl getAll...(2)");
				throw new BLException(response.getExceptionMessage());
			}
			else
			{
				System.out.println("RUNTIME EXCEPTION!!!");
				throw new RuntimeException(response.getExceptionMessage()); 
			}
 		}
			return (List<StudentInterface>)response.getResult();
	}
}
