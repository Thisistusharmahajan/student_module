package com.user.name.school.bl.manager.interfaces;
import com.user.name.school.bl.exceptions.*;
import com.user.name.school.bl.pojo.interfaces.*;
import java.util.*;
public interface StudentManagerInterface
{
	public void add(StudentInterface studentInterface) throws BLException;
	public void update(StudentInterface studentInterface) throws BLException;
	public void remove(int rollNumber) throws BLException;
	public StudentInterface getByRollNumber(int rollNumber) throws BLException;
	public List<StudentInterface> getAll() throws BLException;
	public List<StudentInterface> getByDateOfBirth(java.util.Date dateOfBirth) throws BLException;
	public List<StudentInterface> getByGender(char gender) throws BLException;
       	public List<StudentInterface> getIndians() throws BLException;
        public List<StudentInterface> getNonIndians() throws BLException;
	public List<StudentInterface> getByAge(int age) throws BLException;
}
