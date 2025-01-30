import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dao.*;
import com.user.name.school.dto.interfaces.*;
import com.user.name.school.dto.*;
import com.user.name.school.dao.exceptions.*;

class StudentGetTestCase
{
	public static void main(String gg[])
	{
		try
		{
		int rollNumber = Integer.parseInt(gg[0]);
		StudentDAOInterface studentDAOInterface = new StudentDAO();
		StudentDTOInterface studentDTOInterface = studentDAOInterface.get(rollNumber);
		System.out.println(studentDTOInterface.getRollNumber());
		System.out.println(studentDTOInterface.getName());
		System.out.println(studentDTOInterface.getGender());
		System.out.println(studentDTOInterface.getIsIndian());
		System.out.println(studentDTOInterface.getDateOfBirth());
		}catch(DAOException daoException)
		{
			System.out.println(daoException);
		}
	}
}
