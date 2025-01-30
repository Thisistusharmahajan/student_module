package com.user.name.school.dao.interfaces;
import com.user.name.school.dao.exceptions.*;
import com.user.name.school.dto.interfaces.*;
public interface StudentDAOInterface
{
	public void add(StudentDTOInterface student) throws DAOException;
	public void update(StudentDTOInterface student) throws DAOException;
        public void remove(int rollNumber) throws DAOException;
	public StudentDTOInterface  get(int rollNumber) throws DAOException;
	public java.util.List<StudentDTOInterface> getAll() throws DAOException;
}
