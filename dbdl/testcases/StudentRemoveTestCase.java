import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dao.*;
import com.user.name.school.dto.interfaces.*;
import com.user.name.school.dto.*;
import com.user.name.school.dao.exceptions.*;

class StudentRemoveTestCase
{
	public static void main(String gg[])
	{
		try
		{
		int rollNumber = Integer.parseInt(gg[0]);
		StudentDAOInterface studentDAOInterface = new StudentDAO();
		studentDAOInterface.remove(rollNumber);
		System.out.println("Student Removed");
		}catch(DAOException daoException)
		{
			System.out.println(daoException);
		}
	}
}
