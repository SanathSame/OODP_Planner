package P1;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Implementation of adminController class which calls methods from the filecontroller class
 *
 */
public class adminController 
{
	/**
	 * Scanner used to input the admin's choice
	 */
	private static Scanner scanner = new Scanner (System.in);
	
	/**
	 * Method for admin to change the access period to the starsplanner system
	 */
	public static void setAccessPeriod() {
		System.out.println("Please select one of the options below:");
		System.out.println("1. Change access period for all Students");
		System.out.println("2. Change access period for one students");
		System.out.println("3. Back to Menu");
		
		int choice = fileController.checkValidInt();
		
		LocalDate startDate=LocalDate.of(2021, 1, 30); 
		LocalDate endDate = LocalDate.of(2021, 1, 30);
		if (choice>2)
			return;
		
		boolean done=false;
		while (!done) {
			System.out.print("Enter start of access period (YYYY-MM-DD): ");
			try {
				done=true;
				startDate = LocalDate.parse(scanner.nextLine()); 
			}
			catch(DateTimeException e) {
				done=false;
			}
			if(startDate.isBefore(LocalDate.now())) {
				System.out.println("Invalid entry!! Entered start date is before today.");
				done =false;
			}
		}
		
		done=false;
		while (!done) {
			System.out.print("Enter end of access period (YYYY-MM-DD): ");
			try {
				done=true;
				endDate = LocalDate.parse(scanner.nextLine()); 
			}
			catch(DateTimeException e) {
				done=false;
			}
			if(endDate.isBefore(startDate)) {
				System.out.println("Invalid entry!! Entered end date is before start date.");
				done =false;
			}
		}
		
		switch (choice) {
			case 1:
				fileController.updateStudentAccess("",startDate,endDate);
				System.out.println("Access period of all the Students has been updated");
				break;
			case 2:
				System.out.print("Enter student username to change access period:");
				String username = scanner.next();
				if(fileController.updateStudentAccess(username, startDate, endDate))
					System.out.println("Access period of Student "+ username +" has been updated");
				else
					System.out.println("Incorrect username!!!");
				break;
			default: 
				return;
		}
	}
	
	/**
	 * Method used to add a new student user into the system memory with the students particulars
	 */
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
			System.out.print("Enter Student Gender (M/F): ");
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
	
	/**
	 * Method to add a course into the starsplanner system
	 */
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
			return;
		}
		else {
			System.out.println("Course already exists!");
			return;
		}		
		
	}
	
	/**
	 * Method to print the students registered in an index
	 */
	public static void printIndexStudents()
	{
		int print = fileController.showIndexStudents();
		if(print == -1)
			System.out.println("No students Registered to this course yet");
	}
	
	/**
	 * Method to print the students registered in a course
	 */
	public static void printCourseStudents()
	{
		int print = fileController.showCourseStudents();
		if(print == -1)
			System.out.println("No students Registered to this course yet");
	}

	/**
	 * Method to update information on an existing course
	 */
	public static void updateCourse() {
		fileController.updateExistingCourse();
	}

	/**
	 * Method to print the number of vacancies for a selected course
	 */
	public static void printVacancy() 
	{
		fileController.printVacancy();
	}

	/**
	 * Method to drop a course from the system
	 */
	public static void dropCourse() {
		
		fileController.dropCourse();
	
	}

	/**
	 * Method to update the index of a course
	 */
	public static void updateIndex() {
		fileController.updateIndex();
	}

	/**
	 * Method to print the list of students
	 */
	public static void printAllStudents() {
		fileController.printAllStudents();
	}

	
}