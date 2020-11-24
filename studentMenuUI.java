package P1;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class studentMenuUI implements menuUI {
	private static Scanner scanner = new Scanner(System.in);

	public void displayMenu(String username) {
		
		if(!studentController.isAccessPeriod(username))
			return;
		int choice=0;
		do {
			System.out.println("Please select one of the options below: ");
			System.out.println("--------------------------------------------");
			System.out.println("|1. Register Course                         |");
			System.out.println("|2. Drop Course                             |");
			System.out.println("|3. Check / Print Courses Registered        |");
			System.out.println("|4. Check / Print Waitlist Courses          |");
			System.out.println("|5. Check Vacancies Available               |");
			System.out.println("|6. Change Index Number of Course           |");
			System.out.println("|7. Swop Index Number with Another Student  |");
			System.out.println("|8. Change Email Address                    |");
			System.out.println("|9. Change Password                         |");
			System.out.println("|10. Log out                                |");
			System.out.println("--------------------------------------------");
			
			System.out.print("Please enter your choice: ");
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.println("Please enter valid option:");
			}
			
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				studentController.registerCourse(username);
				pause(2);
				break;
			case 2:
				studentController.dropCourse(username);
				pause(2);
				break;
			case 3:
				studentController.printRegistered(username);
				pause(5);
				break;
			case 4:
				studentController.printWaitlist(username);
				pause(5);
				break;
			case 5:
				studentController.printVacancy();
				pause(5);
				break;
			case 6:
				studentController.changeIndex(username);
				pause(5);
				break;
			case 7:
				studentController.swopIndex(username);
				pause(5);
				break;
			case 8:
				String newEmail;
				while (true)
				{
					System.out.print("Enter New Email: ");
					newEmail = scanner.nextLine();
					
					char [] emailArray = newEmail.toCharArray();
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
		        		        
		        studentController.updateEmail(username,newEmail);
		        System.out.println("Email has been updated!!");
		        pause(3);
		        break;
			
			case 9:
				System.out.print("Enter Current Password: ");
				Console console = System.console(); 
				String password; 
		        if (console == null) 
		            password = scanner.next();
		        else
		        	password=new String(console.readPassword());
				
		        if(!fileController.loginCheck(username, password).equals("student")) {
		        	System.out.println("Incorrect Password!!");
		        	break;
		        }
		        
		        System.out.print("Enter New Password: ");
		        String newPassword;
		        if (console == null) 
		            newPassword = scanner.next();
		        else
		        	newPassword=new String(console.readPassword());
		        
		        System.out.print("Confirm New Password: ");
		        String confirmPassword;
		        if (console == null) 
		        	confirmPassword = scanner.next();
		        else
		        	confirmPassword=new String(console.readPassword());
		        
		        if(!newPassword.equals(confirmPassword)){
		        	System.out.println("Password doesnt match!!");
		        	return;
		        }
		        
		        studentController.updatePassword(username,newPassword);
		        System.out.println("Password has been changed!!");
		        pause(3);
		        break;
		        
			case 10:
				System.out.println("Logging Out!");
				studentController.logout(username);
				pause(1);
				return;
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
