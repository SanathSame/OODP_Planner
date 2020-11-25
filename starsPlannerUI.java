package P1;

import java.io.Console;
import java.util.Scanner;

/**
 * 
 * Represents a main boundary class, starsPlannerUI for users to interact with the application.
 *
 */

public class starsPlannerUI{
	/**
	 * Scanner object to read inputs from user.
	 */
	private static Scanner input = new Scanner(System.in);
	
	/**
	 * For user login. Asks user for username and password, check if they are authorized to use the system.
	 * If authorized, need them to respective menus.
	*/
	public static void login() {
		System.out.print("Enter Username: ");
		String username= input.next();
		
		System.out.print("Enter password: ");
		String password;
		Console console = System.console(); 
        	if (console == null) 
            		password = input.next();
        	else
        		password=new String(console.readPassword());
		
		String acc = fileController.loginCheck(username, password);
		
		if(acc.equals("student")) {
			menuUI studentmenu=new studentMenuUI();
			studentmenu.displayMenu(username);
		}
		
		else if(acc.equals("admin")) {
			menuUI adminmenu=new adminMenuUI();
			adminmenu.displayMenu(username);
		}
		
		else if(acc.equals("incorrect"))
			System.out.println("Incorect username or password");
				
	}
	
	/**
	 * Main program method.Displays the welcome message and calls the login function.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("------------------------------------------------------------");
		System.out.println("|                                                           |");
		System.out.println("|Welcome to MySTARS:My STudent Automated Registration System|");
		System.out.println("|        A project by OODP Lab Group SE3 Group 7            |");
		System.out.println("|      Members: Agnesh,Sanath, Okka, Lulu, Wilbert          |");
		System.out.println("|                                                           |");
		System.out.println("------------------------------------------------------------");
		while(true)
			login();		

	}
}
