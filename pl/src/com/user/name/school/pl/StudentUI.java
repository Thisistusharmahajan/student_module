package com.user.name.school.pl;
import com.user.name.school.bl.manager.interfaces.*;
import com.user.name.school.bl.exceptions.*;
import com.user.name.school.bl.manager.*;
import com.user.name.school.bl.pojo.*;
import com.user.name.school.bl.pojo.interfaces.*;
import java.util.*;
import com.user.name.utils.*;
import com.user.name.utils.exceptions.*;
public class StudentUI
{
	private List<StudentInterface> students;
	private HashMap<Integer, StudentInterface> studentsHashMap;
	private StudentManagerInterface studentManagerInterface;
	public StudentUI()
	{
		populateDataStructures();
	}
	private void populateDataStructures()
	{
		this.studentManagerInterface = new StudentManager();
		try
		{
			this.students = studentManagerInterface.getAll();
			Collections.sort(students);
			this.studentsHashMap = new HashMap<Integer, StudentInterface>();
			for(StudentInterface studentInterface:this.students)
			{
				this.studentsHashMap.put(studentInterface.getRollNumber(),studentInterface);
			}
		}catch(BLException blException)
		{
			System.out.println(blException);
		}
	}
	public void addStudent()
	{
		int rollNumber;
		System.out.println("Student Add (Module)");
		System.out.println("--------------------");
		try
		{
		rollNumber = Keyboard.readInt("Enter roll Number: ");
		}catch(NumberFormatException numberFormatException)
		{
			System.out.println("Roll number should be an integer");
			return;
		}
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll Number");
			return;
		}
		StudentInterface studentInterface;
		studentInterface = this.studentsHashMap.get(rollNumber);
		if(studentInterface!=null)
		{
			System.out.println("That roll number is alloted to "+studentInterface.getName());
			return;
		}
		String name = Keyboard.readString("Enter Name: ");
		if(name == null ||name.length()==0)
		{
			System.out.println("Name required");
			return;
		}
		char gender = Keyboard.readChar("Enter Gender (M/F/T): ");
		if(gender == 'm') gender = 'M';
		else if(gender == 'f') gender = 'F';
		else if(gender == 't') gender = 'T';
		if(gender!='M' && gender!='F' && gender!='T')
		{
			System.out.println("Invalid Gender");
			return;
		}
		char isIndian = Keyboard.readChar("Is Indian (Y/N): ");
		if(isIndian == 'y') isIndian = 'Y';
		else if(isIndian == 'n') isIndian = 'N';	
		if(isIndian!='Y' && isIndian!='N')
		{
			System.out.println("Invalid Input");
			return;
		}
		java.util.Date dateOfBirth=null;
		try
		{
		dateOfBirth = Keyboard.readDate("Enter date Of Birth in dd/mm/yyyy format: ");
		}catch(DateFormatException dateFormatException)
		{
			System.out.println("Invalid date of Birth");
			return;
		}
		char yesNo = Keyboard.readChar("Add(Y/N): ");
		if(yesNo=='y') yesNo = 'Y';
		if(yesNo=='n') yesNo = 'N';
		if(yesNo=='Y') 
		{
			try
			{
			studentInterface = new Student();
			studentInterface.setRollNumber(rollNumber);
			studentInterface.setName(name);
			studentInterface.setGender(gender);
			studentInterface.setIsIndian(isIndian=='Y');
			studentInterface.setDateOfBirth(dateOfBirth);
				studentManagerInterface.add(studentInterface);
				//next 3 lines are very very important
				this.students.add(studentInterface);// to add student 
		Collections.sort(this.students);// to sort the collection for the newly added student
		this.studentsHashMap.put(rollNumber,studentInterface);//to add new student to data structure
		System.out.println("Student Added");
			}catch(BLException blException)
			{
				blException.printStackTrace();
				System.out.println("reason:"+blException+", Student not added");
			}
	         }
		else
		{
			System.out.println("Student Not added");
		}
	}
	public void updateStudent()
        {
	        int rollNumber;
		System.out.println("Student Edit (Module)");
		System.out.println("--------------------");
		try
		{
		rollNumber = Keyboard.readInt("Enter roll Number: ");
		}catch(NumberFormatException numberFormatException)
		{
			System.out.println("Roll number should be an integer");
			return;
		}
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll Number");
			return;
		}
		StudentInterface studentInterface;
		studentInterface = studentsHashMap.get(rollNumber);
                String name;
		name = Keyboard.readString("Enter Name to edit: ");
		if(name == null ||name.length()==0)
		{
			System.out.println("Name required");
			return;
		}
		char gender;
		gender = Keyboard.readChar("Enter Gender to edit (M/F/T): ");
		if(gender == 'm') gender = 'M';
		else if(gender == 'f') gender = 'F';
		else if(gender == 't') gender = 'T';
		if(gender!='M' && gender!='F' && gender!='T')
		{
			System.out.println("Invalid Gender");
			return;
		}
		char isIndian;
		isIndian = Keyboard.readChar("Is Indian (Y/N): ");
		if(isIndian == 'y') isIndian = 'Y';
		else if(isIndian == 'n') isIndian = 'N';	
		if(isIndian!='Y' && isIndian!='N')
		{
			System.out.println("Invalid Input");
			return;
		}
		java.util.Date dateOfBirth=null;
		try
		{
		dateOfBirth = Keyboard.readDate("Enter date Of Birth in dd/mm/yyyy format: ");
		}catch(DateFormatException dateFormatException)
		{
			System.out.println("Invalid date of Birth");
			return;
		}
		char yesNo;
		yesNo = Keyboard.readChar("Update(Y/N): ");
		if(yesNo=='y') yesNo = 'Y';
		if(yesNo=='n') yesNo = 'N';
		if(yesNo=='Y') 
		{
			//studentInterface = new Student();
			studentInterface.setRollNumber(rollNumber);
			studentInterface.setName(name);
			studentInterface.setGender(gender);
			studentInterface.setIsIndian(isIndian=='Y');
			studentInterface.setDateOfBirth(dateOfBirth);
			try{
				studentManagerInterface.update(studentInterface);
				//next 3 lines are very very important
				//this.students.add(studentInterface);// to add student 
		Collections.sort(this.students);// to sort the collection for the newly added student
	        this.studentsHashMap.replace(rollNumber,studentInterface);//to add new student to data structure
				System.out.println("Student Updated");
			}catch(BLException blException)
			{
			System.out.println("reason:"+blException.getMessage()+", Student not Updated");
			}
		}
		else
		{
			System.out.println("Student Not Updated");
		}

        }
	public void removeStudent()
        {
	   	int rollNumber;
		System.out.println("Student Delete (Module)");
		System.out.println("--------------------");
		try
		{
		rollNumber = Keyboard.readInt("Enter roll Number: ");
		}catch(NumberFormatException numberFormatException)
		{
			System.out.println("Roll number should be an integer");
			return;
		}
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll Number");
			return;
		}
		char yesNo;
		yesNo = Keyboard.readChar("Delete (Y/N): ");
		if(yesNo=='y') yesNo = 'Y';
		if(yesNo=='n') yesNo = 'N';
		if(yesNo=='Y') 
		{
			try
			{
			studentManagerInterface.remove(rollNumber);
				//next 3 lines are very very important
		this.students.remove(rollNumber);
		Collections.sort(this.students);// to sort the collection for the recently removed student
	        this.studentsHashMap.remove(rollNumber);//to remove existing student from data structure
				System.out.println("Student Deleted");
			}catch(BLException blException)
			{
			System.out.println("reason:"+blException.getMessage()+", Student not Deleted");
			}

		}
		else
		{ 
			System.out.println("Student Not Deleted");
		}
        }
	public void displayListOfStudents()
        {
 		System.out.println("Student (List Module)");
		for(StudentInterface studentInterface:students)
		{
	System.out.printf("RollNumber: %d, Name: %s\n",studentInterface.getRollNumber(),studentInterface.getName());
		}
       }
	public void searchStudent()
        {	
		int rollNumber;
		System.out.println("Student Search (Module)");
		System.out.println("--------------------");
		try
		{
		rollNumber = Keyboard.readInt("Enter roll Number: ");
		}catch(NumberFormatException numberFormatException)
		{
			System.out.println("Roll number should be an integer");
			return;
		}
		if(rollNumber<=0)
		{
			System.out.println("Invalid roll Number");
			return;
		}
		StudentInterface studentInterface;
		studentInterface = studentsHashMap.get(rollNumber);
		if(studentInterface!=null)
		{
			System.out.println("Name: "+studentInterface.getName());
		}
	
        }
    public void listByDateOfBirth()
	{
		java.util.Date dob;
		System.out.println("List By Date Of Birth (Module)");
		System.out.println("------------------------------");
		try
		{
		String dateOfBirth = Keyboard.readString("Enter date of birth dd/mm/yyyy format: "); 
		String dateOfBirthString[]=dateOfBirth.split("/");
		int dd,mm,yy;
		dd = Integer.parseInt(dateOfBirthString[0]);
		mm = Integer.parseInt(dateOfBirthString[1]);
		yy = Integer.parseInt(dateOfBirthString[2]);
		dob = new java.util.Date(yy,mm,dd);
	for(StudentInterface studentInterface:students)
		{
			if(studentInterface.getDateOfBirth().equals(dob))
	System.out.printf("RollNumber: %d, Name: %s\n",studentInterface.getRollNumber(),studentInterface.getName());
		}

		}catch(DateFormatException dfe)
		{
			System.out.println("Invalid Date");
			dfe.printStackTrace();
		}
	}

	public void listByAge()
	{
		int age;
		System.out.println("List By Date Of Birth (Module)");
		System.out.println("------------------------------");
		try
		{
		age = Keyboard.readInt("Enter Age: ");
	for(StudentInterface studentInterface:students)
		{
			if(studentInterface.getAge()==age)
	System.out.printf("RollNumber: %d, Name: %s\n",studentInterface.getRollNumber(),studentInterface.getName());
		}

		}catch(DateFormatException dfe)
		{
			System.out.println("Invalid Date");
			dfe.printStackTrace();
		}
	}
}
