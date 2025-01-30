package com.user.name.school.pl;
import com.user.name.school.bl.manager.interfaces.*;
import com.user.name.school.bl.exceptions.*;
import com.user.name.school.bl.manager.*;
import com.user.name.school.bl.pojo.*;
import com.user.name.school.bl.pojo.interfaces.*;
import java.util.*;
import com.user.name.utils.*;
import com.user.name.utils.exceptions.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
class StudentModel extends AbstractTableModel
{
private ArrayList<StudentInterface> students;
private StudentManagerInterface studentManagerInterface;
private java.util.List<StudentInterface> list;
StudentModel()
{
populateDataStructure();
}
private void populateDataStructure()
{
studentManagerInterface = new StudentManager();
try
{
list= studentManagerInterface.getAll();
}catch(BLException blException)
{
System.out.println(blException);
}
Collections.sort(list);
students = new ArrayList<StudentInterface>();
int index = 0;
for(StudentInterface studentInterface:list)
{
students.add(index,studentInterface);
index++;
}
}
public int getRowCount()
{
return students.size();
}
public int getColumnCount()
{
return 3;
}
public String getColumnName(int columnIndex)
{
if(columnIndex==0) return new String("S.No.");
if(columnIndex==1) return new String("Roll Number");
return new String("Name");
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)
{
return new Integer(rowIndex+1);
}
if(columnIndex == 1)
{
return students.get(rowIndex).getRollNumber();
}
return students.get(rowIndex).getName();
}
public boolean isCellEditable(int rowIndex, int columnIndex)
{
return false;
}
public Class getColumnClass(int columnIndex)
{
Class c = null;
try
{
if(columnIndex == 0 || columnIndex == 1)
{
c = Class.forName("java.lang.Integer");
}
if(columnIndex == 2)
{
c = Class.forName("java.lang.String");
}
}catch(Exception e)
{
System.out.println(e); //this should never happen
}
return c;
}

//methods to perform studentGUI actions
public void add(StudentInterface student) throws BLException
{
studentManagerInterface.add(student);
this.students.add(student);
Collections.sort(list);
fireTableDataChanged(); // for immediate update JTable 
}

public void update(StudentInterface student) throws BLException
{
studentManagerInterface.update(student);
this.students.remove(indexOfStudent(student));
this.students.add(student);
Collections.sort(list);
fireTableDataChanged(); // for immediate update JTable 
}

public void remove(int rollNumber) throws BLException
{
studentManagerInterface.remove(rollNumber);
Iterator<StudentInterface> iterator = this.students.iterator();
int index=0;
while(iterator.hasNext())
{
if(iterator.next().getRollNumber()==rollNumber)  break;
index++;
}
if(index==this.students.size())
{
BLException blException = new BLException("Invalid Roll Number: "+rollNumber);
throw blException;
}
this.students.remove(index);
fireTableDataChanged(); // for immediate update JTable 
}

public int indexOfStudent(StudentInterface student) throws BLException
{
int index = 0;
for(StudentInterface s : students)
{
if(s.equals(student)) return index;
index++;
}
BLException blException = new BLException("Invalid Student: "+student.getName());
throw blException;
}
public int indexOfName(String name, boolean partialLeftSearch) throws BLException
{
int index = 0;
for(StudentInterface s : students)
{
if(partialLeftSearch)
{
if(s.getName().toUpperCase().startsWith(name.toUpperCase())) 
{
return index;  
}
}
else
{
if(s.getName().equalsIgnoreCase(name)) return index;  
}
index++;
}
BLException blException = new BLException("Invalid Name: "+name);
throw blException;
}
public StudentInterface getStudentAt(int index) throws BLException
}
public class StudentPL extends JFrame
{
private JTable table;
private JScrollPane jsp;
private StudentModel studentModel;
private Container container;
public StudentPL()
{
studentModel = new StudentModel();
table = new JTable(studentModel);
table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
{
public void valueChanged(ListSelectionEvent lse)
{
if (!lse.getValueIsAdjusting() && table.getSelectedRow() != -1) 
{
System.out.println(table.getValueAt(table.getSelectedRow(),0).toString());
System.out.println(table.getValueAt(table.getSelectedRow(),1).toString());
System.out.println(table.getValueAt(table.getSelectedRow(),2).toString());
}
}
});
Font font = new Font("Times New Roman",Font.PLAIN,24);
table.setFont(font);
table.setRowHeight(30);
jsp = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container  = getContentPane();
container.setLayout(new BorderLayout());
container.add(jsp);
Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
int width = 600;
int height = 480;
int x = d.width/2 - width/2;
int y = d.height/2 - height/2;
setLocation(x,y);
setSize(width,height);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public static void main(String gg[])
{
StudentPL s = new StudentPL();
}
}