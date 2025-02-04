package com.itech;

import java.util.Arrays;
public class Student {
    private long rollNo;
    private String name;
    private String address;
    private String subject;
    private int marks[]= new int[6];
    public Student()
    {
        super();
    }
    public String toString()
    {
        return rollNo+"\t"+name+"\t"+address+"\t"+subject+"\t"+ Arrays.toString(marks);
    }
    public Student(long rollNo,String name,String address,String subject,int []marks)
    {
        this.rollNo=rollNo;
        this.name=name;
        this.address=address;
        this.subject=subject;
        this.marks=marks;
    }
    public long getRollNo() {
        return rollNo;
    }

    public void setRollNo(long rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }
}

//StudentMgmtInterface
interface StudentMgmtInterface {
    public  boolean addStudent(Student s);
    public  boolean deleteStudent(long rollNo) throws StudentDoesntExist;
    public  void listStudents();
    public  boolean updateStudent(long rollNo, Student std) throws StudentDoesntExist;
    public Student findTopper();
}
//StudentDoesntExist
class StudentDoesntExist extends Exception
{
    String msg;
    long rollNo;
    public StudentDoesntExist()
    {
        msg = "Student Doesn't Exist";
    }
    public StudentDoesntExist(long rollNo)
    {
        super(String.valueOf(rollNo));
        this.rollNo=rollNo;

    }
    public String toString()
    {
        return msg;
    }
}
//StudentManagementSystem
class StudentManagementSystem implements StudentMgmtInterface
{
    Student studentArray[] = new Student[5];
    static int i;
    @Override
    public boolean addStudent(Student s)
    {
        if(i>9)
            return false;
        else {
            studentArray[i++] = s;
            return true;
        }
    }

    @Override
    public  boolean deleteStudent(long rollNo) throws StudentDoesntExist
    {
        boolean flag = false;
        for(int j=0;j<i;j++)
            if(studentArray[j].getRollNo()==rollNo)
            {
                for(int k=j;k<i-1;k++)
                    studentArray[k]=studentArray[k+1];
                i--;
                flag=true;
                break;
            }
        return flag;
    }

    @Override
    public boolean updateStudent(long rollNo, Student std) throws StudentDoesntExist
    {
        boolean flag = false;
        for(int j=0;j<i;j++)
        {
            if(studentArray[j].getRollNo()==rollNo)
            {
                studentArray[j] = std;
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Student findTopper()
    {
        int[] total = new int[10];
        for(int j=0;j<i;j++)
            total[j]=0;
        int[] totalmark;
        for(int j=0;j<i;j++)
        {
            totalmark=studentArray[j].getMarks();
            for(int mark : totalmark)
                total[j]=total[j]+mark;
        }
        int maxtot = total[0];
        int k=0;
        for(int j=1;j<i;j++)
            if(total[j]>maxtot)
            {
                maxtot=total[j];
                k=j;
            }
        return studentArray[k];
    }

    @Override
    public  void listStudents()
    {
        for(int j=0;j<i;j++)
            System.out.println(studentArray[j]);
    }
}
//StudentDemo
class StudentDemo
{
    public static void main(String[] args) throws StudentDoesntExist {
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();
        int mark1[]={100,99,87,90,98,97};
        Student student1 = new Student(10,"Arjunan ","Ujianaak","Ruby",mark1);
        studentManagementSystem.addStudent(student1);
        int mark2[]={100,90,100,87,98,98};
        Student student2 = new Student(12,"Draupadi","Panchala","HTML",mark2);
        studentManagementSystem.addStudent(student2);
        int mark3[]={100,92,100,89,97,95};
        Student student3 = new Student(14,"Ambalika ","Kaasshya","JAVA",mark3);
        studentManagementSystem.addStudent(student3);
        int mark4[]={100,91,90,89,97,92};
        Student student4 = new Student(16,"Abimanyu","Dwarakai","CSS ",mark4);
        studentManagementSystem.addStudent(student4);
        System.out.println("List :");
        studentManagementSystem.listStudents();
        System.out.println("Topper: "+studentManagementSystem.findTopper());
        int mark5[]={88,97,99,84,88,94};
        Student student5 = new Student(18,"Krishnar","Gokhulam","Cpp",mark5);
        studentManagementSystem.addStudent(student5);
        System.out.println("Added a new object");
        int mark6[]={88,87,89,84,98,84};
        Student student6 = new Student(14,"Bheeman","Vaghella",".NET",mark6);
        studentManagementSystem.updateStudent(12,student6);
        System.out.println("Updated Rollno 14");
        studentManagementSystem.deleteStudent(14);
        System.out.println("Deleted Rollno. 10");
        System.out.println("List :");
        studentManagementSystem.listStudents();

    }
}