package com.user.name.school.pl;
import com.user.name.school.pl.*; 
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
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.table.*;
public class StudentGUI extends JFrame implements DocumentListener
{
private JLabel nameLabel;
private JLabel searchLabel;
private JLabel titleLabel;
private JTextField searchTextField;
private JButton clearSearchFieldButton;
private JLabel searchErrorLabel;
private JTable studentTable;
private JScrollPane scrollPane;
private StudentModel studentModel;
private Container container;
private StudentPanel studentPanel;
public StudentGUI()
{
initComponents(); //object creation will be done in init components
setAppearance();  //Font settings arrangements frame components
addListeners();  //to add listeners, event handlers for different components
}
private void initComponents()
{
studentModel = new StudentModel();
titleLabel = new JLabel("Students");
searchLabel = new JLabel("Search");
searchTextField = new JTextField();
clearSearchFieldButton = new JButton("x");
searchErrorLabel = new JLabel("");
studentTable = new JTable(studentModel);
scrollPane= new JScrollPane(studentTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
container=getContentPane();
}
private void setAppearance()
{
Font titleFont = new Font("Verdana",Font.BOLD,18);
Font captionFont = new Font("Verdana",Font.BOLD,16);
Font dataFont = new Font("Verdana",Font.PLAIN,16);
Font searchErrorFont = new Font("Verdana",Font.BOLD,12);
Font columnHeaderFont = new Font("Verdana",Font.BOLD,16);

titleLabel.setFont(titleFont);
searchLabel.setFont(captionFont);
searchTextField.setFont(dataFont);
searchErrorLabel.setFont(searchErrorFont);
searchErrorLabel.setForeground(Color.red);
studentTable.setFont(dataFont);
studentTable.setRowHeight(30);
studentTable.getColumnModel().getColumn(0).setPreferredWidth(20);
studentTable.getColumnModel().getColumn(1).setPreferredWidth(100);
studentTable.getColumnModel().getColumn(2).setPreferredWidth(300);
studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
JTableHeader header=studentTable.getTableHeader();
header.setFont(columnHeaderFont);
header.setReorderingAllowed(false);
header.setResizingAllowed(false);

studentPanel = new StudentPanel();
container.setLayout(null);
int lm,tm; //left-margin,top-margin
lm = 0;
tm = 0;
titleLabel.setBounds(lm+10,tm+10,200,40);
searchLabel.setBounds(lm+10,tm+10+40,100,30); 
searchTextField.setBounds(lm+10+100+5,tm+10+40,400,30);
clearSearchFieldButton.setBounds(lm+10+100+400+10,tm+10+40,30,30);
searchErrorLabel.setBounds(lm+10+100+400+10-75,tm+10+20,100,20); 
scrollPane.setBounds(lm+10,tm+10+40+10+30+10,565,300);
studentPanel.setBounds(lm+10,tm+10+40+10+30+10+300+10,565, 200);
container.add(titleLabel);
container.add(searchErrorLabel);
container.add(searchLabel);
container.add(searchTextField);
container.add(clearSearchFieldButton); 
container.add(scrollPane);
container.add(studentPanel);
int w,h;
w = 600;
h = 700;
setSize(w,h);
Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2));
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}

private void addListeners()
{

/*Hum chahte the ki vo jo textbox hai vo functional ho jaye, to humne is textfield k liye 
getDocument() method call kari vo jo return karegi uske upar upar addDocumentListener() call
kari aur this likhke humne StudentGUI k object ka address pass kardiya vo karne ki vajah se 
code compile nahi hua kuki receiving end pe jo Pointer hai vo DocumentListener type ka hai
isliye humne StudentGUI ko DocumentListener se implement kiya fir compile kiya compile nahi
hua kuki humne changeUpdate(), insertUpdate() aur removeUpdate() methods define nahi kari thi. 
teeno method se ek hi common function searchStudent ko call kiya. Search me kya karna tha
title nikalna tha vo nikal liya getText() karke usko trim kiya. uske baad vo jo title hai vo humne model
ki indexOfName method ko bhej diya aur bola ki haa bhai leftPartialSearch karna
vo ya to Exception fekega ya to exception nahi fekega. exception fekega to upar not found likhwa
diya. Exception nahi fekega to apne paas rowIndex aagaya to humne setRowSelectionInterval() 
call karke vo row select karwa liya par itne se kaam nahi banega usko visibility area me
lana hai to uske liye JTable ki getCellRect() method hoti hai usko rowIndex aur columnIndex 
pass karo to vo cell ke rectangular portion ki info ek Rectangle type ke object me dalke
humko Rectangle type ke object ka address dedega jo humne ek Rectangle k pointer me pakad liya
fir JTable ki scrollRectToVisible() method hai usko ek argument pass karna padta hai rectangle*/

searchTextField.getDocument().addDocumentListener(this);
clearSearchFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
}
private void searchStudent()
{
searchErrorLabel.setText("");
String name = searchTextField.getText().trim();
if(name.length()==0) return;
int rowIndex;
try
{
rowIndex = studentModel.indexOfName(name,true);
}catch(BLException blException)
{
searchErrorLabel.setText("Not Found");
return;
}
studentTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle = studentTable.getCellRect(rowIndex,0,true);
studentTable.scrollRectToVisible(rectangle);
}
public void changedUpdate(DocumentEvent de)
{
searchStudent();
}
public void removeUpdate(DocumentEvent de)
{
searchStudent();
}
public void insertUpdate(DocumentEvent de)
{
searchStudent();
}

//inner class starts here
class StudentPanel extends JPanel
{
private JLabel titleCaptionLabel;
private JLabel titleLabel;
private JTextField titleTextField;
private JButton clearTitleTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton deleteButton;
private JButton cancelButton;
private JButton exportToPDFButton;
private JPanel buttonsPanel;
private StudentInterface studentInterface;
StudentPanel()
{
studentInterface=null;
setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
initComponents();
setAppearance();
addListeners();
}
public void setStudent(StudentInterface student)
{

}
private void initComponents()
{
titleCaptionLabel = new JLabel("Student Operation");
titleLabel=new JLabel("");
titleTextField = new JTextField();
clearTitleTextFieldButton = new JButton("X");
buttonsPanel = new JPanel();
addButton = new JButton("A");
editButton = new JButton("E");
cancelButton = new JButton("C");
deleteButton = new JButton("D");
exportToPDFButton = new JButton("Export");
}
private void setAppearance()
{
Font captionFont = new Font("Verdana",Font.BOLD,16);
Font dataFont = new Font("Verdana",Font.PLAIN,16);
titleCaptionLabel.setFont(captionFont);
titleLabel.setFont(dataFont);
titleTextField.setFont(dataFont);
setLayout(null);
int lm,tm;
lm = 0;
tm = 0;
titleCaptionLabel.setBounds(lm+10,tm+20,110,30);
titleLabel.setBounds(lm+110+5,tm+20,400,30);
titleTextField.setBounds(lm+110+5+10,tm+20,350,30);
clearTitleTextFieldButton.setBounds(lm+10+110+5+350+5,tm+20,30,30);
buttonsPanel.setBounds(50,tm+20+30+30,465,75);
buttonsPanel.setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
addButton.setBounds(70,12,50,50);
editButton.setBounds(70+50+20,12,50,50);
cancelButton.setBounds(70+50+20+50+20,12,50,50);
deleteButton.setBounds(70+50+20+50+20+50+20,12,50,50);
exportToPDFButton.setBounds(70+50+20+50+20+50+20+50+20,12,50,50);
buttonsPanel.setLayout(null);
buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(exportToPDFButton);
add(titleCaptionLabel);
//add(titleTextFiled);
add(titleLabel);
add(clearTitleTextFieldButton);
add(buttonsPanel);
}
private void addListeners()
{
}
}//inner class ends here
}