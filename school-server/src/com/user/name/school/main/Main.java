package com.user.name.school.main;
import com.user.name.school.network.*;
import com.user.name.school.network.exceptions.*; 
import com.user.name.school.network.interfaces.*;
import com.user.name.school.bl.pojo.interfaces.*;
import com.user.name.school.bl.pojo.*;
import com.user.name.school.bl.manager.interfaces.*;
import com.user.name.school.bl.manager.*;
import com.user.name.school.bl.exceptions.*;
import java.util.*;

class SchoolNetworkRequestProcessor implements RequestListener
{

	public Response processRequest(Request request)
	{
		Set<String> entities = new HashSet<String>();
		entities.add("student");
		Set<String> studentActions = new HashSet<String>();
		studentActions.add("add");
		studentActions.add("update");
		studentActions.add("remove");
		studentActions.add("getAll");
		studentActions.add("getByRollNumber");
		studentActions.add("getByGender");
		String entity = request.getEntity();
		String action = request.getAction();
		if(entities.contains(entity)==false)
		{
			Response response = new Response();
			response.setSuccess(false);
			response.setResult("");
			response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
			response.setExceptionMessage("Entity ("+entity+")does not exist");
			return response;
		}
		if(entity.equals("student"))
		{
			if(studentActions.contains(action)==false)
			{
			Response response = new Response();
			response.setSuccess(false);
			response.setResult("");
			response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
			response.setExceptionMessage("Action ("+action+") does not exist");
			return response;
			}

	                // code to call bl methods starts here
			Object[] arguments;
			arguments = request.getObjects();
			if(action.equals("add"))
			{
				if(arguments.length!=1)
				{
                			Response response = new Response();
		                	response.setSuccess(false);
					response.setResult("");
	       		response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
 			response.setExceptionMessage("Action ("+action+") takes 1 argument only");
	                  		return response;
				}
				StudentInterface studentInterface=null;
				try
				{
				studentInterface=(StudentInterface)arguments[0];
				}catch(Exception exception)
				{
			Response response = new Response();
			response.setSuccess(false);
			response.setResult("");
			response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
			response.setExceptionMessage("Action ("+action+") takes 1 argument of StudentInterface type only.");
			return response;	
				}
				try
				{
				StudentManagerInterface studentManagerInterface;
				studentManagerInterface = new StudentManager();
				studentManagerInterface.add(studentInterface);
				Response response;
				response = new Response();
				response.setSuccess(true);
				response.setResult("success!");
				response.setExceptionClassName("");
				response.setExceptionMessage("");
				return response;
				}catch(BLException blException)
 				{
				Response response = new Response();
			response.setSuccess(false);
			response.setResult("");
			response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
			response.setExceptionMessage(blException.getMessage());
			return response;
				}
			 }
			if(action.equals("getAll"))
			{
                		if(arguments.length!=0)
				{
                		Response response = new Response();
		               	response.setSuccess(false);
				response.setResult("");
	       	       	response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
			response.setExceptionMessage("Action ("+action+") takes no argument");
	                 	return response;
				}
				StudentManagerInterface studentManagerInterface;
			 	studentManagerInterface = new StudentManager();
				try
				{
					List<StudentInterface> students;
					students = studentManagerInterface.getAll();
				System.out.println("getAll of action studentManager got called in main");
					Response response = new Response();
					response.setSuccess(true);
					response.setResult(students);
					response.setExceptionClassName("");
					response.setExceptionMessage("");
					return response;  
				}catch(BLException blException)
				{
				Response response = new Response();
		               	response.setSuccess(false);
				response.setResult("");
	       	       	response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
			response.setExceptionMessage(blException.getMessage());
	                 	return response;
				}	
			}
			// code to call bl methods ends here
		}
		//this will never happen, still
		Response response = new Response();
		response.setSuccess(false);
		response.setResult("");
		response.setExceptionClassName("com.user.name.school.bl.exceptions.BLException");
		response.setExceptionMessage("Unknown reason");
		return response;
	}
}
public class Main
{
	public static void main(String gg[])
	{
	try
	{
		SchoolNetworkRequestProcessor schoolNetworkRequestProcessor;
		schoolNetworkRequestProcessor = new SchoolNetworkRequestProcessor();
		NetworkServer networkServer;
		networkServer = new NetworkServer();
		networkServer.onClientConnected(schoolNetworkRequestProcessor);
		networkServer.startServer(); // will start the server
	}catch(NetworkException networkException)
	{
		System.out.println(networkException);
	}
	}
}

