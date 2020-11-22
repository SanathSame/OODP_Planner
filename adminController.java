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
		
		System.out.print("Enter Username: ");
		String userName = scanner.next();		
		
		System.out.print("Enter Password: ");
		String password = scanner.next();
		
		System.out.print("Enter Student ID: ");
		String studentID = scanner.next();
		
		System.out.print("Enter Student Email: ");
		String Email = scanner.next();
		
		boolean sucess = fileController.makeStudent(name, userName, password, studentID,sdate,edate,Email);
		
		if(sucess) {
			System.out.printf("Student %s has been added",userName);
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
					break;
					
				case 2: 
					System.out.println("Enter New Course Name:");
					String nameNew = scanner.nextLine();
					courseSelected.setCourseName(nameNew);
					System.out.println ("Course Name Changed To: " + courseSelected.getCourseName());
					break;
				
				default:
					return;
			}
			
			fileController.updateCoursefile_course(courseSelected);
		}
	}

	public static void printVacancy() {
		
		System.out.println("Choose a course to check vaccancy: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		
		int cur = scanner.nextInt();

		if(cur-1<max) 
			fileController.printIndices(null,cur-1);
		else
			System.out.println("Back to Menu");
		
	}
}
