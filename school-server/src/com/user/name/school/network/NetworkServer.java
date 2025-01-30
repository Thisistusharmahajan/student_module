package com.user.name.school.network;
import com.user.name.school.network.exceptions.*;
import com.user.name.school.network.interfaces.*;
import com.user.name.school.configuration.*;
import com.user.name.school.configuration.exceptions.*;
import com.user.name.school.configuration.pojo.*;
import java.net.*;
import java.io.*;
import java.nio.*;
class RequestProcessingThread extends Thread
{
	private Socket clientSocket;
	private RequestListener requestListener;
	RequestProcessingThread(Socket clientSocket, RequestListener requestListener)
	{
		this.clientSocket=clientSocket;
		this.requestListener=requestListener;
		this.start();
	}
	private static byte[] createHeader(int value)
	{
	ByteBuffer byteBuffer;
	byteBuffer = ByteBuffer.allocate(Integer.BYTES);
	byteBuffer.order(ByteOrder.BIG_ENDIAN);
	byteBuffer.putInt(value);
	return byteBuffer.array();
	}
	private static int parseHeader(byte bytes[])
	{
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.order(ByteOrder.BIG_ENDIAN);
		byteBuffer.put(bytes);
		byteBuffer.rewind();
		return byteBuffer.getInt();
	}
        public void run()
  	{
		try
		{
		InputStream inputStream = this.clientSocket.getInputStream();
		byte requestHeaderBytes[] = new byte[Integer.BYTES];
		int index = 0; 
		int toRead = Integer.BYTES;
		int bytesReadCount;
		//read request Header
		while(toRead>0)
		{
			bytesReadCount = inputStream.read(requestHeaderBytes,index,toRead);
			if(bytesReadCount==-1 || bytesReadCount==0) continue;
			index = index + bytesReadCount;
			toRead = toRead - bytesReadCount;
		}
		int requestBytesToRead = parseHeader(requestHeaderBytes);
		byte requestBytes[];
		requestBytes = new byte[requestBytesToRead];
		//read request as byte[]
		index = 0;
		while(requestBytesToRead>0)
		{	
			bytesReadCount = inputStream.read(requestBytes,index,requestBytesToRead);
			if(bytesReadCount==-1 || bytesReadCount==0) continue;
			index = index + bytesReadCount;
			requestBytesToRead = requestBytesToRead - bytesReadCount;
		}
		//deserialize request bytes[]
		ByteArrayInputStream byteArrayInputStream;
		byteArrayInputStream = new ByteArrayInputStream(requestBytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Request request;
  		request = (Request)objectInputStream.readObject();
		Response response;
		response = this.requestListener.processRequest(request);
		// code to serialize response starts here
		ByteArrayOutputStream byteArrayOutputStream;
		byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream;
		objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(response);
		objectOutputStream.flush();
		byte responseBytes[] = byteArrayOutputStream.toByteArray(); 
		int responseBytesLength = responseBytes.length;
		byte responseHeaderBytes[];
		responseHeaderBytes = createHeader(responseBytesLength);
		OutputStream outputStream;
		outputStream = this.clientSocket.getOutputStream();
		outputStream.write(responseHeaderBytes);
		outputStream.flush(); //this is added
		outputStream.write(responseBytes);
		outputStream.flush();
		}catch(Exception exception) 
		{
			System.out.println("Request bytes failed to deserialize");
			System.out.println(exception);
		}
	}
}
public class NetworkServer
{
	private ServerSocket serverSocket;
	private int port;
	private RequestListener requestListener;
	public NetworkServer()  
	{
		try
		{
			Configuration configuration;
			configuration = ConfigurationFactory.getConfiguration();
 			this.port = configuration.getPort();
			this.serverSocket = new ServerSocket(this.port);
		}catch(Exception exception)
		{
			System.out.println(exception.getMessage());
                        System.exit(0);
		}
	}
        public void onClientConnected(RequestListener requestListener)
	{
		this.requestListener = requestListener;
	}	
	public void startServer() throws NetworkException
	{
		if(this.requestListener==null)
		{
			throw new NetworkException("onClientConnected not called, read documentation");
		}
		Socket clientSocket;
		try
		{
			RequestProcessingThread requestProcessingThread;
	 	while(true)
 		{
			clientSocket=this.serverSocket.accept();//backlog queue me se accept karega, naya socket banayega aur uska address return kiya jayega
	requestProcessingThread = new RequestProcessingThread(clientSocket,this.requestListener);//yaha clientSocket, this.requestListener de rahe hai matlab school app ne jo address pass kiya vo de rahe hai
		}
		}catch(Throwable t)//extreme cases me bhi kaam ho jayega isse
		{
			System.out.println(t.getMessage());
			System.exit(0);
		}
	}
	}
