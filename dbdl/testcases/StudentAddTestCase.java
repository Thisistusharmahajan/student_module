import com.user.name.school.dao.interfaces.*;
import com.user.name.school.dao.exceptions.*;
import com.user.name.school.dto.interfaces.*;
import com.user.name.school.dao.*;
import com.user.name.school.dto.*;
class StudentAddTestCase
{
	public static void main(String gg[])
	{
		int rollNumber = Integer.parseInt(gg[0]);
		String name = gg[1];
		char gender = gg[2].charAt(0);
		boolean isIndian;
		if(gg[3].equals("Y")) isIndian=true;
		else isIndian=false;
		int dd = Integer.parseInt(gg[4]);
		int mm = Integer.parseInt(gg[5])-1;
		int yy = Integer.parseInt(gg[6])-1900;
		java.util.Date dateOfBirth = new java.util.Date(yy,mm,dd);
		StudentDTOInterface studentDTOInterface = new StudentDTO();
		studentDTOInterface.setRollNumber(rollNumber);
		studentDTOInterface.setName(name);
		studentDTOInterface.setGender(gender);
		studentDTOInterface.setIsIndian(isIndian);
		studentDTOInterface.setDateOfBirth(dateOfBirth);
		try
		{
			StudentDAOInterface studentDAOInterface;
			studentDAOInterface = new StudentDAO();
			studentDAOInterface.add(studentDTOInterface);
			System.out.println("Student Added"); 
		}catch(DAOException daoException)
		{
			System.out.println(daoException);
		}
	}
}
