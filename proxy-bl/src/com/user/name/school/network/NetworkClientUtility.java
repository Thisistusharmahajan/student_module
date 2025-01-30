package com.user.name.school.network;
import com.user.name.school.network.exceptions.*;
import com.user.name.school.configuration.*;
import com.user.name.school.configuration.exceptions.*;
import com.user.name.school.configuration.pojo.*;
import java.net.*;
import java.io.*;
import java.nio.*;
public class NetworkClientUtility
{
	private NetworkClientUtility()
	{
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
	public static Response sendRequest(Request request) throws NetworkException
	{
		//serialize
		try
		{
		ByteArrayOutputStream byteArrayOutputStream;
		byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream;
		objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(request);
		objectOutputStream.flush();
		byte bytes[] = byteArrayOutputStream.toByteArray(); 
		// code to get Configuration
		Configuration configuration;
		configuration = ConfigurationFactory.getConfiguration();
		// code to establish connection
		Socket clientSocket;
		clientSocket = new Socket(configuration.getServer(), configuration.getPort()); 
		int bytesLength = bytes.length;
		byte headerBytes[];
		headerBytes = createHeader(bytesLength);
		OutputStream outputStream;
		outputStream = clientSocket.getOutputStream();
		outputStream.write(headerBytes);//header bytes bhej diye
		outputStream.flush();
		outputStream.write(bytes);//actual data bytes bhej diye
		outputStream.flush();
		Response response = null;
                //read response bytes
        InputStream inputStream = clientSocket.getInputStream();
		byte responseHeaderBytes[] = new byte[Integer.BYTES];
		int index = 0;
		int toRead = Integer.BYTES;
		int bytesReadCount;
		while(toRead>0)
		{
			bytesReadCount = inputStream.read(responseHeaderBytes,index,toRead);
			if(bytesReadCount==-1 || bytesReadCount==0) continue;
			index = index + bytesReadCount;
			toRead = toRead - bytesReadCount;
		}
		int responseBytesToRead = parseHeader(responseHeaderBytes);
		byte responseBytes[];
		responseBytes = new byte[responseBytesToRead];
		//read response as byte[]
		index = 0;
		while(responseBytesToRead>0)
		{	
			bytesReadCount = inputStream.read(responseBytes,index,responseBytesToRead);
			if(bytesReadCount==-1 || bytesReadCount==0) continue;
			index = index + bytesReadCount;
			responseBytesToRead = responseBytesToRead - bytesReadCount;
		}
		//deserialize response bytes
		ByteArrayInputStream byteArrayInputStream;
		byteArrayInputStream = new ByteArrayInputStream(responseBytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		response = (Response)objectInputStream.readObject();
		return response;
		}catch(Exception exception) 
		{
			System.out.println("Message from proxy-bl:Response bytes not deserialized");
			throw new NetworkException(exception.getMessage()); 
		}
	}
}
