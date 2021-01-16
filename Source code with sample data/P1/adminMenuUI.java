package P1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * A boundary class used to display menu to the administrator user.
 * Implements menuUI interface
 * 
 * @author anon
 */
public class adminMenuUI implements menuUI{
	/**
	 * Scanner object used to get input from user.
	 * 
	 */
	private static Scanner scanner = new Scanner(System.in);

	/**
	 * Public method called from starsPlannerUI to display administrator options.
	 * This function is dynamically called if an administrator logs in, as it overrides 
	 * the displayMenu function in menuUI.
	 * 
	 * @param username : username of the administrator who logged in to the application
	 */
	public void displayMenu(String username){
		int choice = 0;
		do{
			System.out.println("Please select one of the options below: ");

			System.out.println("--------------------------------------------");
			System.out.println("|1. Edit Access Period                      |");
			System.out.println("|2. Add Student                             |");
			System.out.println("|3. Add Course                              |");
			System.out.println("|4. Update Existing Course                  |");
			System.out.println("|5. Drop Course                             |");
			System.out.println("|6. Update Existing Index                   |");
			System.out.println("|7. Check Vacancies Available               |");
			System.out.println("|8: Print Students In Index                 |");
			System.out.println("|9: Print Students In Course                |");
			System.out.println("|10: Print All Students                     |");
			System.out.println("|11. Log Out                                |");
			System.out.println("--------------------------------------------");
			
			System.out.print("Please enter your choice: ");
			while (!scanner.hasNextInt()) 
			{
				scanner.next();
				System.out.println("Please enter valid option:");
			}
			
			
			choice = scanner.nextInt();
			
			switch (choice) 
			{
			case 1:
				adminController.setAccessPeriod();
				break;
				
			case 2:
				adminController.addStudent();
				pause(5);
				break;
				
			case 3:
				adminController.addCourse();
				pause(5);
				break;
				
			case 4:
				adminController.updateCourse();
				pause(5);
				break;
				
			case 5:
				adminController.dropCourse();
				pause(5);
				break;
				
			case 6:
				adminController.updateIndex();
				pause(5);
				break;
				
			case 7:
				adminController.printVacancy(); //printVacancy method copied from student Controller.
				pause(5);
				break;
				
			case 8:
				adminController.printIndexStudents();
				pause(5);
				break;
				
			case 9:
				adminController.printCourseStudents();
				pause(5);
				break;
				
			case 10: 
				adminController.printAllStudents();
				pause(5);
				break;
				
			case 11:
				System.out.println("Logging Out!");
				pause(1);
				return;
				
			default:
				System.out.println("Please enter valid option:");
				pause(1);
				break;
				
			}
		} while (true);
	}
	
	/**
	 * private static method used inside this class to pause the program for a few seconds before next display. 
	 * 
	 * @param durationInSeconds : the number of seconds the program pauses
	 */
	private static void pause (long durationInSeconds){
		try 
		{
			TimeUnit.SECONDS.sleep(durationInSeconds);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}
