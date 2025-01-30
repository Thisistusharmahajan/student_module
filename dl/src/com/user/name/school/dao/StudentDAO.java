package com.user.name.school.dao;
import com.user.name.school.dao.exceptions.*;
import com.user.name.school.dto.interfaces.*;
import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dto.*;
import java.io.*;
import java.util.*;
public class StudentDAO implements StudentDAOInterface
{
	private static String dataFileName = "students.data";
	public void add(StudentDTOInterface studentDTOInterface) throws DAOException
	{
		try
		{
			String dateOfBirthString;
			int rollNumber = studentDTOInterface.getRollNumber();
			String name = studentDTOInterface.getName();
			char gender = studentDTOInterface.getGender();
			boolean isIndian = studentDTOInterface.getIsIndian();
			java.util.Date dateOfBirth = studentDTOInterface.getDateOfBirth();
			dateOfBirthString = dateOfBirth.getDate()+","+dateOfBirth.getMonth()+","+dateOfBirth.getYear();
			int vRollNumber=0;
			File file;
			file = new File(dataFileName);
			RandomAccessFile randomAccessFile;
			randomAccessFile = new RandomAccessFile(file, "rw");
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber = Integer.parseInt(randomAccessFile.readLine());
				if(vRollNumber == rollNumber)
				{
					randomAccessFile.close();
					throw new DAOException(rollNumber+" exists");
				}
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				randomAccessFile.readLine();
				randomAccessFile.readLine();
			}
			randomAccessFile.writeBytes(rollNumber+"\n");
			randomAccessFile.writeBytes(name+"\n");
			randomAccessFile.writeBytes(gender+"\n");
			randomAccessFile.writeBytes(((isIndian==true)?'Y':'N')+"\n");
			randomAccessFile.writeBytes(dateOfBirthString+"\n");
			randomAccessFile.close();
		}
		catch(IOException ioException)
		{
			throw new DAOException(ioException.getMessage());
		}
	}
	public void update(StudentDTOInterface studentDTOInterface) throws DAOException
	{
		try
		{
			int rollNumber = studentDTOInterface.getRollNumber();
			File file;
			file = new File(dataFileName);
			if(file.exists()==false)
			{
				throw new DAOException(rollNumber+" does not exist");
			}
			RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
			if(randomAccessFile.length()==0)
			{
				randomAccessFile.close();
				throw new DAOException(rollNumber+" does not exist");
			}
			String name = studentDTOInterface.getName();
			char gender = studentDTOInterface.getGender();
			boolean isIndian = studentDTOInterface.getIsIndian();
			java.util.Date dateOfBirth = studentDTOInterface.getDateOfBirth();
			int dd,mm,yy;
			dd = dateOfBirth.getDate();
			mm = dateOfBirth.getMonth()+1;
			yy = dateOfBirth.getYear()+1900;
			String dateOfBirthString = dd+","+mm+","+yy;
			int vRollNumber;
			String vName;
			char vGender;
			char vIsIndian;
			String vDateOfBirth;
			File tmpFile = new File("tmp.tmp");
			if(tmpFile.exists()) tmpFile.delete();
			RandomAccessFile tmpRandomAccessFile;
			tmpRandomAccessFile = new RandomAccessFile(tmpFile, "rw");
			boolean found = false;
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber = Integer.parseInt(randomAccessFile.readLine());
				vName = randomAccessFile.readLine();
                                vGender = randomAccessFile.readLine().charAt(0);
                                vIsIndian = randomAccessFile.readLine().charAt(0);
                                vDateOfBirth = randomAccessFile.readLine();
				if(vRollNumber!=rollNumber)
				{
					tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
                                        tmpRandomAccessFile.writeBytes(vName+"\n");
                                        tmpRandomAccessFile.writeBytes(vGender+"\n");
                                        tmpRandomAccessFile.writeBytes(vIsIndian+"\n");
                                        tmpRandomAccessFile.writeBytes(vDateOfBirth+"\n");
				}
				else
				{
					found = true;
					tmpRandomAccessFile.writeBytes(rollNumber+"\n");
                                        tmpRandomAccessFile.writeBytes(name+"\n");
                                        tmpRandomAccessFile.writeBytes(gender+"\n");
                                        tmpRandomAccessFile.writeBytes((isIndian)?"Y\n":"N\n");
                                        tmpRandomAccessFile.writeBytes(dateOfBirthString+"\n");
				}
			}
			if(found == false)
			{
				randomAccessFile.close();
				tmpRandomAccessFile.setLength(0);
				tmpRandomAccessFile.close(); //never delete the file was opened
				throw new DAOException(rollNumber+" does not exist");
			}
			randomAccessFile.seek(0);
			tmpRandomAccessFile.seek(0);
			while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
			{
				randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n"); 
			}
			randomAccessFile.setLength(tmpRandomAccessFile.length());
			tmpRandomAccessFile.setLength(0);
			randomAccessFile.close();
		}catch(IOException ioException)
		{
			throw new DAOException(ioException.getMessage());
		}
	}
        public void remove(int rollNumber) throws DAOException
	{
		try
		{
			File file;
			file = new File(dataFileName);
			if(file.exists()==false)
			{
				throw new DAOException(rollNumber+" does not exist");
			}
			RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
			if(randomAccessFile.length()==0)
			{
				randomAccessFile.close();
				throw new DAOException(rollNumber+" does not exist");
			}
			int vRollNumber;
			String vName;
			char vGender;
			char vIsIndian;
			String vDateOfBirth;
			File tmpFile = new File("tmp.tmp");
			if(tmpFile.exists()) tmpFile.delete();
			RandomAccessFile tmpRandomAccessFile;
			tmpRandomAccessFile = new RandomAccessFile(tmpFile, "rw");
			boolean found = false;
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber = Integer.parseInt(randomAccessFile.readLine());
				vName = randomAccessFile.readLine();
                                vGender = randomAccessFile.readLine().charAt(0);
                                vIsIndian = randomAccessFile.readLine().charAt(0);
                                vDateOfBirth = randomAccessFile.readLine();
				if(vRollNumber!=rollNumber)
				{
					tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
                                        tmpRandomAccessFile.writeBytes(vName+"\n");
                                        tmpRandomAccessFile.writeBytes(vGender+"\n");
                                        tmpRandomAccessFile.writeBytes(vIsIndian+"\n");
                                        tmpRandomAccessFile.writeBytes(vDateOfBirth+"\n");
				}
				else
				{
					found = true;
				}
			}
			if(found == false)
			{
				randomAccessFile.close();
				tmpRandomAccessFile.setLength(0);
				tmpRandomAccessFile.close(); //never delete the file was opened
				throw new DAOException(rollNumber+" does not exist");
			}
			randomAccessFile.seek(0);
			tmpRandomAccessFile.seek(0);
			while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
			{
				randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n"); 
			}
			randomAccessFile.setLength(tmpRandomAccessFile.length());
			tmpRandomAccessFile.setLength(0);
			randomAccessFile.close();
		}catch(IOException ioException)
		{
			throw new DAOException(ioException.getMessage());
		}
	}
        public StudentDTOInterface get(int rollNumber) throws DAOException
	{
		int vRollNumber;
		String vName;
		char vGender;
		boolean vIsIndian;
		java.util.Date vDateOfBirth; 
		String dateOfBirthString;
		String dateOfBirthStringParts[];
		StudentDTOInterface studentDTOInterface;
		int yy,mm,dd;
		try
		{
			File file;
			file = new File(dataFileName);
			if(file.exists()==false) throw new DAOException(rollNumber+" does not exist");
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				vRollNumber = Integer.parseInt(randomAccessFile.readLine());
				if(vRollNumber == rollNumber)
				{
				vName = randomAccessFile.readLine();
				vGender = randomAccessFile.readLine().charAt(0);
				vIsIndian = randomAccessFile.readLine().equals("Y");
				dateOfBirthString = randomAccessFile.readLine();
				dateOfBirthStringParts = dateOfBirthString.split(",");
				yy = Integer.parseInt(dateOfBirthStringParts[0]);
				mm = Integer.parseInt(dateOfBirthStringParts[1]);
				dd = Integer.parseInt(dateOfBirthStringParts[2]);
				vDateOfBirth = new java.util.Date(yy,mm,dd);
				studentDTOInterface = new StudentDTO();
				studentDTOInterface.setRollNumber(vRollNumber);
				studentDTOInterface.setName(vName);
				studentDTOInterface.setGender(vGender);
				studentDTOInterface.setIsIndian(vIsIndian);
				studentDTOInterface.setDateOfBirth(vDateOfBirth); 
				randomAccessFile.close();
				return studentDTOInterface;
				}
				else
				{
					randomAccessFile.readLine();
					randomAccessFile.readLine();
					randomAccessFile.readLine();
					randomAccessFile.readLine();
				}
			}
			randomAccessFile.close();
		}catch(IOException ioException)
		{
			throw new DAOException(ioException.getMessage());
		}
		throw new DAOException(rollNumber+" does not exist");
	}
	public java.util.List<StudentDTOInterface> getAll() throws DAOException
	{
		List<StudentDTOInterface> students;
		//to perform the operations in loop following variables will be needed
		int rollNumber;
		String name;
		char gender;
		boolean isIndian;
		java.util.Date dateOfBirth; 
		String dateOfBirthString;
		String dateOfBirthStringParts[];
		StudentDTOInterface studentDTOInterface;
		int yy,mm,dd;
		students = new ArrayList<StudentDTOInterface>();
		try
		{
			File file;
			file = new File(dataFileName);
			if(file.exists()==false) return students;
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			while(randomAccessFile.getFilePointer()<randomAccessFile.length())
			{
				rollNumber = Integer.parseInt(randomAccessFile.readLine());
				name = randomAccessFile.readLine();
				gender = randomAccessFile.readLine().charAt(0);
				isIndian = randomAccessFile.readLine().equals("Y");
				dateOfBirthString = randomAccessFile.readLine();
				dateOfBirthStringParts = dateOfBirthString.split(",");
				yy = Integer.parseInt(dateOfBirthStringParts[0]);
				mm = Integer.parseInt(dateOfBirthStringParts[1]);
				dd = Integer.parseInt(dateOfBirthStringParts[2]);
				dateOfBirth = new java.util.Date(yy,mm,dd);
				studentDTOInterface = new StudentDTO();
				studentDTOInterface.setRollNumber(rollNumber);
				studentDTOInterface.setName(name);
				studentDTOInterface.setGender(gender);
				studentDTOInterface.setIsIndian(isIndian);
				studentDTOInterface.setDateOfBirth(dateOfBirth); 
				students.add(studentDTOInterface);//yeh add array list ki method hai usme add karne k liye
			}
			randomAccessFile.close();
		}catch(IOException ioException)
		{
			throw new DAOException(ioException.getMessage());
		}
		return students;
	}
}

