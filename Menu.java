package P1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Menu {
	private static Scanner scanner = new Scanner(System.in);
	
public static void adminmenu (String name){
		
		int choice = 0;
		do{
			System.out.println("Please select one of the options below: ");
			System.out.println("1. Edit Access Period");
			System.out.println("2. Add Student");
			System.out.println("3. Add Course");
			System.out.println("4. Update Existing Course");
			System.out.println("5. Drop Course");
			System.out.println("6. Update Existing Index");
			System.out.println("7. Check Vacancies Available");
			System.out.println("8: Print Students In Index");
			System.out.println("9: Print Students In Course");
			System.out.println("10: Print All Students");
			System.out.println("11. Log Out");
			while (!scanner.hasNextInt()) 
			{
				scanner.next();
				System.out.println("Please enter valid option:");
			}
			choice = scanner.nextInt();
			
			switch (choice) 
			{
			case 1:
				//adminController.setAccessPeriod();
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
				appclass1.login();
				return;
				
			default:
				System.out.println("Please enter valid option:");
				pause(1);
				break;
				
			}
		} while (true);
	}
	
	public static void studentmenu(String name) {
		if(!studentControler.isAccessPeriod(name))
			return;
		int choice=0;
		do {
			System.out.println("Please select one of the options below: ");
			System.out.println("1. Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check / Print Courses Registered");
			System.out.println("4. Check / Print Waitlist Courses");
			System.out.println("5. Check Vacancies Available");
			System.out.println("6. Change Index Number of Course");
			System.out.println("7. Swop Index Number with Another Student");
			System.out.println("8. Change notification mode");
			System.out.println("9. Change Number or Email");
			System.out.println("10. Log out");
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.println("Please enter valid option:");
			}
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				studentControler.registerCourse(name);
				pause(2);
				break;
			case 2:
				studentControler.dropCourse(name);
				pause(2);
				break;
			case 3:
				studentControler.printRegistered(name);
				pause(5);
				break;
			case 4:
				studentControler.printWaitlist(name);
				pause(5);
				break;
			case 5:
				studentControler.printVacancy();
				pause(5);
				break;
			case 6:
				studentControler.changeIndex(name);
				pause(5);
				break;
			case 7:
				studentControler.swopIndex(name);
				pause(5);
				break;
			case 8:
				//StudentController.changeNotification(student.getMatricNo());
				break;
			case 9:
				//StudentController.changeNumOrEmail(student.getMatricNo());
				break;
			case 10:
				System.out.println("Logging Out!");
				pause(1);
				studentControler.logout(name);
				appclass1.login();
				break;
			default:
				System.out.println("Please enter valid option:");
				pause(1);
				break;
			}
		} while (true);
	}

	private static void pause (long durationInSeconds)
	{
		try 
		{
			TimeUnit.SECONDS.sleep(durationInSeconds);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}