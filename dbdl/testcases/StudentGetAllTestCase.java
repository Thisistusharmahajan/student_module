import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dao.*;
import com.user.name.school.dto.interfaces.*;
import com.user.name.school.dto.*;
import com.user.name.school.dao.exceptions.*;
import java.util.*;

class StudentGetAllTestCase
{
	public static void main(String gg[])
	{
		try
		{
			List<StudentDTOInterface> students;
			StudentDAOInterface studentDAOInterface;
			studentDAOInterface = new StudentDAO();
			students = studentDAOInterface.getAll();
			for(StudentDTOInterface studentDTOInterface:students)
			{
				System.out.println("Roll Number: "+studentDTOInterface.getRollNumber());
				System.out.println("Name: "+studentDTOInterface.getName());
				System.out.println("Gender: "+studentDTOInterface.getGender());
				System.out.println("Indian: "+studentDTOInterface.getIsIndian());
				System.out.println("Date of Birth: "+studentDTOInterface.getDateOfBirth());
			}
		}catch(DAOException daoException)
		{
			System.out.println(daoException);
		}
	}

}
