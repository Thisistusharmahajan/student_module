package com.user.name.school.main; 
import com.user.name.utils.*;
import com.user.name.utils.exceptions.*;
import com.user.name.school.pl.*;
public class Main
{
  public static void main(String gg[])
  {
    int choice;
    StudentUI studentUI = new StudentUI();
    while(true)
    {
      System.out.println("Student Module");
      System.out.println("1. Add");
      System.out.println("2. Edit");
      System.out.println("3. Delete");
      System.out.println("4. List");
      System.out.println("5. Search");
      System.out.println("6. Filter by Date");
      System.out.println("7. List of Indians");
      System.out.println("8. List of Non Indians");
      System.out.println("9. List by Age");
      System.out.println("10. Exit");
      try
      {
      choice = Keyboard.readInt("Enter your Choice: ");
      }catch(NumberFormatException numberFormatException)
      {
        choice = 0;
      }
      if(choice<1 || choice>10)
      {
        System.out.println("Invalid Choice"); 
        continue;
      }
      if(choice == 1)        studentUI.addStudent();
      else if(choice == 2) studentUI.updateStudent();
      else if(choice == 3) studentUI.removeStudent();
      else if(choice == 4) studentUI.displayListOfStudents();
      else if(choice == 5) studentUI.searchStudent();
      else if(choice == 6) studentUI.listByDateOfBirth();
      else if(choice == 7) studentUI.listByAge();
      //else if(choice == 8) studentUI.listByGender();
      //else if(choice == 9) studentUI.getIndians();
      //else if(choice == 10) studentUI.getNonIndians();
      else if(choice == 11) break;
    }  
  }
}