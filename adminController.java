package P1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class adminController 
{
	private static Scanner scanner = new Scanner (System.in);

	public static void addStudent(){
		
		//LocalDate edate =LocalDate.of(2021, 1, 30); 
		//LocalDate sdate =LocalDate.of(2020, 11, 01); 
		
		System.out.print("Enter Student Name: ");
		String name = scanner.nextLine();
		char [] nameCheckArray = name.trim().toLowerCase().toCharArray();
		
		for (int i = 0; i<nameCheckArray.length; i++)
		{
			char ch = nameCheckArray[i];
			if ((ch < 'a' || ch > 'z') && ch != ' ')
			{
				System.out.println("Name can only consist of characters A to Z!");
				return;
			}
		}
		
		System.out.print("Enter Username: ");
		String userName = scanner.next();		
		
		System.out.print("Enter Password: ");
		String password = scanner.next();
		
		System.out.print("Enter Student ID: ");
		String studentID = scanner.next();
		
		System.out.print("Enter Student Email: ");
		String Email = scanner.next();
		
		boolean sucess = fileController.makeStudent(name, userName, password, studentID, Email);
		
		if(sucess) {
			System.out.printf("Student %s has been added\n",userName);
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
		System.out.println("Enter Course Code: ");
		String code = scanner.nextLine();
		
		System.out.println("Enter Course Name: ");
		String name = scanner.nextLine();
		
		boolean sucess = fileController.makeCourse(code, name);
		
		if(sucess) {
			System.out.printf("Course %s has been added",code);
			return;
		}
		else {
			System.out.println("Course already exist!");
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
	
	public static void printAllStudents()
	{
		fileController.printAllStudents();
	}

	public static void updateCourse() {
		System.out.println("Choose a course to update: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		int cur = scanner.nextInt();
		if (cur-1 < max){
			
			Course courseSelected = (Course) fileController.getCourses().get(cur-1);
			System.out.println("Please select one of the options below:");
			System.out.println("1. Edit Course Code");
			System.out.println("2. Edit Course Name");
			int choice = scanner.nextInt();
			scanner.nextLine();
			System.out.println("----------Making Changes to " + courseSelected.getCourseCode() + " " + courseSelected.getCourseName() + "----------");
			switch (choice)
			{
				case 1:
					System.out.println("Enter New Course Code:");
					String codeNew = scanner.nextLine();
					courseSelected.setCourseCode(codeNew);
					System.out.println ("Course Code Changed To: " + courseSelected.getCourseCode());
					fileController.updateCoursefile_course(courseSelected);
					break;
					
				case 2: 
					System.out.println("Enter New Course Name:");
					String nameNew = scanner.nextLine();
					courseSelected.setCourseName(nameNew);
					System.out.println ("Course Name Changed To: " + courseSelected.getCourseName());
					fileController.updateCoursefile_course(courseSelected);
					break;
				
				default:
					return;
			}
			
			
		}
	}
	
	public static void dropCourse()
	{
		fileController.dropCourse();
	}

	public static void updateIndex()
	{
		
		fileController.updateIndex();
		/*
		System.out.prinln("Choose a course whose index to update:");
		int max = fileController.printAllCourses()
		*/
	}
	public static void printVacancy() {
		
		System.out.println("Choose a course to check vacancy: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		
		int cur = scanner.nextInt();

		if(cur-1<max) 
			fileController.printIndices(null,cur-1);
		else
			System.out.println("Back to Menu");
		
	}
}
