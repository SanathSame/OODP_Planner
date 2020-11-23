package P1;

import java.time.LocalDate;
import java.util.Scanner;

public class adminController 
{
	private static Scanner scanner = new Scanner (System.in);

	public static void addStudent(){
		
		LocalDate edate =LocalDate.of(2021, 1, 30); 
		LocalDate sdate =LocalDate.of(2020, 11, 01); 
		
		System.out.print("Enter Student Name: ");
		String name = scanner.nextLine();
		char [] nameCheckArray = name.trim().toLowerCase().toCharArray();
		
		for (int i = 0; i<nameCheckArray.length; i++)
		{
			char ch = nameCheckArray[i];
			if ((ch < 'a' || ch > 'z') && ch != ' ')
			{
				System.out.println("Name can only consist of characters A to Z!");
				addStudent();
				return;
			}
		}
		
		System.out.print("Enter Username: ");
		String userName = scanner.nextLine();		
		
		System.out.print("Enter Password: ");
		String password = scanner.nextLine();
		
		System.out.print("Enter Student ID: ");
		String studentID = scanner.nextLine();
		
		System.out.print("Enter Student Nationality: ");
		String nationality = scanner.nextLine();
		
		String gender;
		while (true)
		{
			System.out.print("Enter Student Gender: ");
			gender = scanner.nextLine();
			if (gender.equals("M") || gender.equals("F"))
			{
				break;
			}
			else
			{
				System.out.println("Invalid! Gender Must Be Either M or F");
				continue;
			}
		}
		
		String Email;
		while (true)
		{
			System.out.print("Enter Student Email: ");
			Email = scanner.nextLine();
			
			char [] emailArray = Email.toCharArray();
			boolean containsAT = false;
			boolean containsDot = false;
			int indexAt = 0;
			int indexDot = 0;
			for (int i = 0; i < emailArray.length; i++)
			{
				char currentChar = emailArray[i];
				if (currentChar == '@')
				{
					 containsAT = true;
					 indexAt = i;
				}
				
				if (currentChar == '.')
				{
					containsDot = true;
					indexDot = i;
				}
			}
			if (containsAT && containsDot && indexAt < indexDot)
			{
				break;
			}
			System.out.println("Invalid Email!");
		}
		
		boolean sucess = fileController.makeStudent(name, userName, password, studentID,sdate,edate,
				nationality,gender,Email);
		
		if(sucess) {
			System.out.printf("Student %s has been added.\n",userName);
			fileController.printAllStudents();
			return;
		}
		else {
			System.out.println("StudentID / Username is already registered!");
			return;
		}
	}
	
	public static void addCourse()
	{
		System.out.print("Enter Course Code: ");
		String code = scanner.nextLine();
		
		System.out.print("Enter Course Name: ");
		String name = scanner.nextLine();
		
		boolean sucess = fileController.makeCourse(code, name);
		
		if(sucess) {
			System.out.printf("Course %s has been added.\n",code);
			fileController.printAllCourses();
			fileController.pause(5);
			return;
		}
		else {
			System.out.println("Course already exists!");
			return;
		}		
		
	}
	
	public static void printIndexStudents()
	{
		int print = fileController.showIndexStudents();
		if(print == -1)
			System.out.println("No students Registered to this course yet");
	}
	
	public static void printCourseStudents()
	{
		int print = fileController.showCourseStudents();
		if(print == -1)
			System.out.println("No students Registered to this course yet");
	}

	public static void updateCourse() {
		fileController.updateExistingCourse();
	}

	public static void printVacancy() 
	{
		fileController.printVacancy();
	}

	public static void dropCourse() {
		
		fileController.dropCourse();
	
	}

	public static void updateIndex() {
		fileController.updateIndex();
	}

	public static void printAllStudents() {
		fileController.printAllStudents();
	}
}