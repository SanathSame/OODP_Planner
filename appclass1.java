package P1;

import java.util.Scanner;


public class appclass1 {
	static Scanner input = new Scanner(System.in);
	
	public static void login() {
		System.out.print("Enter Username: ");
		String username= input.next();
		
		System.out.print("Enter password: ");
		String password=input.next();
		
		String acc = fileController.loginCheck(username, password);
		
		if(acc.equals("student")) 
			Menu.studentmenu(username);
		
		else if(acc.equals("admin"))
			Menu.adminmenu(username);
		
		else if(acc.equals("incorrect"))
			System.out.println("Incorect username or password");
				
	}
	
	public static void main(String[] args) {
		System.out.println("------------------------------------------------------------");
		//System.out.println("                                                           |");
		System.out.println("Welcome to MySTARS:My STudent Automated Registration System|");
		System.out.println("A project by OODP Lab Group SE3 Group 7                    |");
		System.out.println("Members: Agnesh,Sanath, Okka, Lulu, Wilbert                |");
		System.out.println("------------------------------------------------------------");
		while(true)
			login();		

	}
}
