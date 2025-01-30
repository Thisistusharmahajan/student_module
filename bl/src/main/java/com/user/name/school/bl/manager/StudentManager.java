package com.user.name.school.bl.manager;
import com.user.name.school.bl.manager.interfaces.*;
import com.user.name.school.bl.exceptions.*;
import com.user.name.school.bl.pojo.interfaces.*;
import com.user.name.school.bl.pojo.*;
import java.util.*;
import com.user.name.school.dto.interfaces.*;  
import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dto.*;
import com.user.name.school.dao.*;
import com.user.name.school.dao.exceptions.*;
public class StudentManager implements StudentManagerInterface
{
	public void add(StudentInterface studentInterface) throws BLException
	{
		int rollNumber = studentInterface.getRollNumber();
		String name = studentInterface.getName();
		char gender = studentInterface.getGender();
		boolean isIndian = studentInterface.getIsIndian();
		java.util.Date dateOfBirth = studentInterface.getDateOfBirth();
		int age = studentInterface.getAge();
		if(age<15) throw new BLException("Student should be atleast 15 years old");
		StudentDTOInterface studentDTOInterface = new StudentDTO();
		studentDTOInterface.setRollNumber(rollNumber);
		studentDTOInterface.setName(name);
		studentDTOInterface.setGender(gender);
		studentDTOInterface.setIsIndian(isIndian);
		studentDTOInterface.setDateOfBirth(dateOfBirth);
		StudentDAOInterface studentDAOInterface = new StudentDAO();
		try
		{
			studentDAOInterface.add(studentDTOInterface);
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public void update(StudentInterface studentInterface) throws BLException
	{
		int rollNumber = studentInterface.getRollNumber();
		String name = studentInterface.getName();
		char gender = studentInterface.getGender();
		boolean isIndian = studentInterface.getIsIndian();
		java.util.Date dateOfBirth = studentInterface.getDateOfBirth();
		int age = studentInterface.getAge();
		if(age<15) 
		{
			System.out.println(age);
			throw new BLException("Student should be atleast 15 years old");
		}
		StudentDTOInterface studentDTOInterface = new StudentDTO();
		studentDTOInterface.setRollNumber(rollNumber);
		studentDTOInterface.setName(name);
		studentDTOInterface.setGender(gender);
		studentDTOInterface.setIsIndian(isIndian);
		studentDTOInterface.setDateOfBirth(dateOfBirth);
		StudentDAOInterface studentDAOInterface = new StudentDAO();
		try
		{
			studentDAOInterface.update(studentDTOInterface);
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}

	}
	public void remove(int rollNumber) throws BLException
	{
		StudentDAOInterface studentDAOInterface = new StudentDAO();
		try
		{
			studentDAOInterface.remove(rollNumber);
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public StudentInterface getByRollNumber(int rollNumber) throws BLException
	{
		StudentDAOInterface studentDAOInterface = new StudentDAO();
		StudentDTOInterface studentDTOInterface;
		try
		{
			studentDTOInterface = studentDAOInterface.get(rollNumber);
			StudentInterface studentInterface = new Student();
			studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			studentInterface.setName(studentDTOInterface.getName());
			studentInterface.setGender(studentDTOInterface.getGender());
			studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
			studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
			return studentInterface;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentInterface> getAll() throws BLException
	{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO(); 
		List<StudentDTOInterface> dtoStudents;
		try
		{
			dtoStudents = studentDAOInterface.getAll();
			List<StudentInterface> blStudents;
			blStudents = new LinkedList<StudentInterface>();
			StudentInterface studentInterface;
			for(StudentDTOInterface studentDTOInterface:dtoStudents) 
			{
				studentInterface = new Student();
				studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
			        studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			        studentInterface.setName(studentDTOInterface.getName());
			        studentInterface.setGender(studentDTOInterface.getGender());
			        studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
			        studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
	      			blStudents.add(studentInterface);
			}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentInterface> getByDateOfBirth(java.util.Date dateOfBirth) throws BLException
	{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO(); 
		List<StudentDTOInterface> dtoStudents;
		try
		{
			dtoStudents = studentDAOInterface.getAll();
			List<StudentInterface> blStudents;
			blStudents = new LinkedList<StudentInterface>();
			StudentInterface studentInterface;
			for(StudentDTOInterface studentDTOInterface:dtoStudents) 
			{
				if(studentDTOInterface.getDateOfBirth().equals(dateOfBirth))
				{
				studentInterface = new Student();
			        studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			        studentInterface.setName(studentDTOInterface.getName());
			        studentInterface.setGender(studentDTOInterface.getGender());
			        studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
			        studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				blStudents.add(studentInterface);
			        }
			}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentInterface> getByGender(char gender) throws BLException
	{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO(); 
		List<StudentDTOInterface> dtoStudents;
		try
		{
			dtoStudents = studentDAOInterface.getAll();
			List<StudentInterface> blStudents;
			blStudents = new LinkedList<StudentInterface>();
			StudentInterface studentInterface;
			for(StudentDTOInterface studentDTOInterface:dtoStudents) 
			{
				if(studentDTOInterface.getGender()==gender)
				{
				studentInterface = new Student();
			        studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			        studentInterface.setName(studentDTOInterface.getName());
			        studentInterface.setGender(studentDTOInterface.getGender());
			        studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
			        studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				blStudents.add(studentInterface);
			        }
			}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
        public List<StudentInterface> getIndians() throws BLException
	{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO(); 
		List<StudentDTOInterface> dtoStudents;
		try
		{
			dtoStudents = studentDAOInterface.getAll();
			List<StudentInterface> blStudents;
			blStudents = new LinkedList<StudentInterface>();
			StudentInterface studentInterface;
			for(StudentDTOInterface studentDTOInterface:dtoStudents) 
			{
				if(studentDTOInterface.getIsIndian()==true)
				{
				studentInterface = new Student();
			        studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			        studentInterface.setName(studentDTOInterface.getName());
			        studentInterface.setGender(studentDTOInterface.getGender());
			        studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
			        studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				blStudents.add(studentInterface);
			        }
			}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
        public List<StudentInterface> getNonIndians() throws BLException
	{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO(); 
		List<StudentDTOInterface> dtoStudents;
		try
		{
			dtoStudents = studentDAOInterface.getAll();
			List<StudentInterface> blStudents;
			blStudents = new LinkedList<StudentInterface>();
			StudentInterface studentInterface;
			for(StudentDTOInterface studentDTOInterface:dtoStudents) 
			{
				if(studentDTOInterface.getIsIndian()==false)
				{
				studentInterface = new Student();
			        studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			        studentInterface.setName(studentDTOInterface.getName());
			        studentInterface.setGender(studentDTOInterface.getGender());
			        studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
			        studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				blStudents.add(studentInterface);
			        }
			}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
	public List<StudentInterface> getByAge(int age) throws BLException
	{
		StudentDAOInterface studentDAOInterface;
		studentDAOInterface = new StudentDAO(); 
		List<StudentDTOInterface> dtoStudents;
		try
		{
			dtoStudents = studentDAOInterface.getAll();
			List<StudentInterface> blStudents;
			blStudents = new LinkedList<StudentInterface>();
			StudentInterface studentInterface;
			for(StudentDTOInterface studentDTOInterface:dtoStudents) 
			{
				studentInterface = new Student();
			        studentInterface.setDateOfBirth(studentDTOInterface.getDateOfBirth());
				if(studentInterface.getAge()==age)
				{
			        studentInterface.setRollNumber(studentDTOInterface.getRollNumber());
			        studentInterface.setName(studentDTOInterface.getName());
			        studentInterface.setGender(studentDTOInterface.getGender());
			        studentInterface.setIsIndian(studentDTOInterface.getIsIndian());
				blStudents.add(studentInterface);
			        }    
			}
			return blStudents;
		}catch(DAOException daoException)
		{
			throw new BLException(daoException.getMessage());
		}
	}
}
