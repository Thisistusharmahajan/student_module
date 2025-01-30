package com.user.name.school.dao;
import com.user.name.school.dao.exceptions.*;
import com.user.name.school.dto.interfaces.*;
import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dto.*;
import java.util.*;
import java.sql.*;
public class StudentDAO implements StudentDAOInterface
{
	private static String dataFileName = "students.data";
	public void add(StudentDTOInterface student) throws DAOException
	{
		try
		{
			int rollNumber = student.getRollNumber();
			String name = student.getName();
			char gender = student.getGender();
			boolean isIndian = student.getIsIndian();
			java.util.Date dateOfBirth = student.getDateOfBirth();
			int dd,mm,yy;
			dd = dateOfBirth.getDate();
			mm = dateOfBirth.getMonth();
			yy = dateOfBirth.getYear();
			java.sql.Date sqlDateOfBirth = new java.sql.Date(yy,mm,dd);
			Connection connection = DAOConnection.getConnection();
if(connection!=null) System.out.println("Connection established");
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Select roll_number from student where roll_number=?");
			preparedStatement.setInt(1,rollNumber);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			boolean exists = resultSet.next();
			resultSet.close();
			preparedStatement.close();
			if(exists)
			{
				connection.close();
				throw new DAOException(rollNumber+" exists");
			}
			preparedStatement = connection.prepareStatement("insert into student values(?,?,?,?,?)");
			preparedStatement.setInt(1,rollNumber);
			preparedStatement.setString(2,name);
			preparedStatement.setString(3,String.valueOf(gender));
			preparedStatement.setBoolean(4,isIndian);
			preparedStatement.setDate(5,sqlDateOfBirth);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();

		}
		catch(SQLException sqlException)
		{
			throw new DAOException(sqlException.getMessage());
		}
	}
	public void update(StudentDTOInterface student) throws DAOException
	{
		try
		{
			int rollNumber = student.getRollNumber();
			String name = student.getName();
			char gender = student.getGender();
			boolean isIndian = student.getIsIndian();
			java.util.Date dateOfBirth = student.getDateOfBirth();
			int dd,mm,yy;
			dd = dateOfBirth.getDate();
			mm = dateOfBirth.getMonth();
			yy = dateOfBirth.getYear();
			java.sql.Date sqlDateOfBirth = new java.sql.Date(yy,mm,dd);
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Select roll_number from student where roll_number=?");
			preparedStatement.setInt(1,rollNumber);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			boolean exists = resultSet.next();
			resultSet.close();
			preparedStatement.close();
			if(!exists)
			{
				connection.close();
				throw new DAOException(rollNumber+" does not exists");
			}
			preparedStatement = connection.prepareStatement("update student set name=?,gender=?, is_indian=?,date_of_birth=? where roll_number=?");
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,String.valueOf(gender));
			preparedStatement.setBoolean(3,isIndian);
			preparedStatement.setDate(4,sqlDateOfBirth);
			preparedStatement.setInt(5,rollNumber);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		}catch(SQLException sqlException)
		{
			throw new DAOException(sqlException.getMessage());
		}
	}
        public void remove(int rollNumber) throws DAOException
	{
		try
		{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Select roll_number from student where roll_number=?");
			preparedStatement.setInt(1,rollNumber);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			boolean exists = resultSet.next();
			resultSet.close();
			preparedStatement.close();
			if(exists==false)
			{
				connection.close();
				throw new DAOException(rollNumber+"does not exist");
			}
		preparedStatement = connection.prepareStatement("delete from student where roll_number=?");
			preparedStatement.setInt(1,rollNumber);
			preparedStatement.close();
			connection.close();
		}catch(SQLException sqlException)
		{
			throw new DAOException(sqlException.getMessage());
		}
	}
        public StudentDTOInterface get(int rollNumber) throws DAOException
	{
		try
		{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Select * from student where roll_number=?");
			preparedStatement.setInt(1,rollNumber);
			ResultSet resultSet;
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()==false)
			{
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException(rollNumber+" does not exist");
			}
			String vName = resultSet.getString("name").trim();
			String vGender = resultSet.getString("gender").trim();
			boolean vIsIndian = resultSet.getBoolean("is_indian");
			java.sql.Date vDateOfBirth=resultSet.getDate("date_of_birth");
			resultSet.close();
			preparedStatement.close();
			connection.close();
			int yy = vDateOfBirth.getYear();
			int mm = vDateOfBirth.getMonth();
			int dd = vDateOfBirth.getDate();
			java.util.Date utilDateOfBirth = new java.util.Date(yy,mm,dd);
			StudentDTOInterface studentDTOInterface = new StudentDTO();
			studentDTOInterface.setRollNumber(rollNumber);
			studentDTOInterface.setName(vName);
			studentDTOInterface.setGender(vGender.charAt(0));
			studentDTOInterface.setIsIndian(vIsIndian);
			studentDTOInterface.setDateOfBirth(utilDateOfBirth); 
			return studentDTOInterface;
		}catch(SQLException sqlException)
		{
			throw new DAOException(sqlException.getMessage());
		}
	}
	public java.util.List<StudentDTOInterface> getAll() throws DAOException
	{
	
		List<StudentDTOInterface> students;
		students = new ArrayList<StudentDTOInterface>();	
		try
		{
         	//to perform the operations in loop following variables will be needed
		Connection connection = DAOConnection.getConnection();
if(connection!=null) System.out.println("Message from Data Layer: Connection established");
		Statement statement = connection.createStatement(); 
			ResultSet resultSet;
			resultSet = statement.executeQuery("select * from student");
			int vRollNumber; 
			String vName;
			String vGender;
			boolean vIsIndian;
			java.sql.Date sqlDateOfBirth;
			java.util.Date utilDateOfBirth;
			int dd,mm,yy;
			while(resultSet.next())
			{
			vRollNumber = resultSet.getInt("roll_number");
			vName = resultSet.getString("name").trim();
			vGender = resultSet.getString("gender").trim();
			vIsIndian = resultSet.getBoolean("is_indian");
			sqlDateOfBirth=resultSet.getDate("date_of_birth");
			yy = sqlDateOfBirth.getYear();
			mm = sqlDateOfBirth.getMonth();
			dd = sqlDateOfBirth.getDate();
			utilDateOfBirth = new java.util.Date(yy,mm,dd);
			StudentDTOInterface studentDTOInterface = new StudentDTO();
			studentDTOInterface.setRollNumber(vRollNumber);
			studentDTOInterface.setName(vName);
			studentDTOInterface.setGender(vGender.charAt(0));
			studentDTOInterface.setIsIndian(vIsIndian); 	
			studentDTOInterface.setDateOfBirth(utilDateOfBirth); 
			students.add(studentDTOInterface);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch(SQLException sqlException)
		{
			throw new DAOException(sqlException.getMessage());
		}
		return students;
	}
}
