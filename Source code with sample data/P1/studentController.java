package P1;

import java.util.Scanner;

/**
 * Implementation of the studentcontroller class which calls methods from the filecontroller clas
 *
 */
public class studentController {
	/**
	 * Method which determines whether student has access to the starsplanner system according to
	 * their access period
	 * @param name : name of the student
	 * @return :boolean value, where true represents student has access to the starsplanner system
	 * and false represents student does not have access to the system at this time
	 */
	public static boolean isAccessPeriod(String name) {
		int out = fileController.StudentAccessPeriod(name);
		if(out==0)
			return true;
		else if(out==1) {
			System.out.println("Your STARS access has not started yet!!!");
			return false;
		}
		else if(out==-1) {
			System.out.println("Your STARS access has been closed!!!");
			return false;
		}
		else
			return false;
	}	

	/**
	 * Method to handle the different situations when a student try to register himself to a course
	 * There can be successful registration, registered for the same course again, 
	 * added to waitlist, reached the AU limits and possible clashes in schedule
	 * 
	 * @param name : name of the particular student
	 */
	public static void registerCourse(String name) {
		String[] course=fileController.getRegisterIndex();
		if(course[0].equals(""))
			return;
		int success=fileController.assignStudent(name,course);
		
		if(success==-1)
			System.out.println("Sorry!! You have already been registered/waitlisted for course: " 
					+course[1]);
		else if(success==0)
			System.out.println("You have been registered to course: " 
					+course[1]+ ", Index Id: "+course[0]);
		else if(success==1)
			System.out.println("You have been added to the waitinglist for course: " 
					+course[1]+ ", Index Id: "+course[0]);
		else if(success==2) {
			System.out.println("Sorry, you have reached your max AU limit!!!");
		}
		else if(success==3)
			System.out.println("Sorry!! There is a clash in your timetable for course: " 
					+course[1]+ ", Index Id: "+course[0]);
		
			
	}

	/**
	 * Method which allows student to drop the index of a particular course
	 * @param name : name of this student
	 */
	public static void dropCourse(String name) {
		System.out.println("Choose course to drop: ");
		String[] course=fileController.getDropIndex(name);
		if(course[1].isEmpty())
			return;
		System.out.println("Are you sure want to drop index "+ course[0] +
				" of Course "+ course[1]+ " (yes/no)?");
		Scanner scanner = new Scanner(System.in);
		String cnfm = scanner.next();
			
		if(cnfm.toLowerCase().equals("yes")) {
			fileController.unAssignStudent(name,course,"drop");
		}
		else
			dropCourse(name);
	}
	
	/**
	 * Method to print the indexes registered by this student
	 * @param name : name of this student
	 */
	public static void printRegistered(String name) {
		int printed = fileController.printStudentIndices(name,"reg");
		if(printed==-1)
			System.out.println("You have not been registered to any courses yet!!!");
	}

	/**
	 * Method to print the waitlisted indexes of a particular student
	 * @param name: name of this student
	 */
	public static void printWaitlist(String name) {
		int printed = fileController.printStudentIndices(name,"wait");
		if(printed==-1)
			System.out.println("There are no waitlisted courses to display.");
	}

	/**
	 * Print the vacancies of a particular index
	 */
	public static void printVacancy() {
		System.out.println("Choose a course to check vacancy: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		System.out.print("====>Enter choice: ");
		Scanner scanner = new Scanner(System.in);
		int cur = scanner.nextInt();

		if(cur-1<max) 
			fileController.printIndices(null,cur-1);
		else
			System.out.println("Back to Menu");
	}
	
	/**
	 * Method which allows the students to change the index of a registered course
	 * @param name : name of this student
	 */
	public static void changeIndex(String name) {
		System.out.println("Choose course to change Index: ");
		String[] course=fileController.getDropIndex(name);
		if(course[0].equals(""))							//if nothing is choosen
			return;
		String[] index = fileController.getChangeIndex(course[1]);
		
		
		if(index[1].equals("")) {
			System.out.println("Sorry!! The requested index "+index[0]+" has no vacany!!");
		}
		else {
			//if no clash
			if(!fileController.clash(name,course)) {
				String[] drop= course;
				String[] add= {index[0],course[1]};
				fileController.unAssignStudent(name,drop,"drop");
				fileController.assignStudent(name,add);
			}
			else
				System.out.println("Sorry!! The requested index "+index[0]+" clashes with your timetable!!");
		}
	}
	
	/**
	 * Method which allows the student to swop indexes with another student
	 * @param name : name of this student
	 */
	public static void swopIndex(String name) {
		System.out.println("Choose course to swop Index: ");
		String[] course=fileController.getDropIndex(name);
		if(course[0].equals(""))
			return;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Student Username to swop Index : ");
		String user2 = scanner.next();
		
		System.out.print("Enter Student Password to swop Index : ");
		String pw2 = scanner.next();
		
		String authourize=fileController.loginCheck(user2, pw2);
		if(!authourize.equals("student")) {
			System.out.println("Swop is not athourized!!");
			swopIndex(name);
		}
		
		String course2[] = {"",course[1]};
		course2[0] = fileController.getIndexReg_Course(user2,course[1]);
		
		if(course2[0]==null) {
			System.out.println("Student "+user2+" is not registered for the course "+course[1]);
			return;
		}
		
		
		fileController.unAssignStudent(name, course,"swop");
		fileController.unAssignStudent(user2, course2,"swop");
		int done = fileController.assignStudent(name, course2);
		if(done==2) {
			System.out.println("Swop unsuccessful!! There was a clash in your timetable");
			fileController.assignStudent(name, course);
			fileController.assignStudent(user2, course2);
			return;
		}
		done = fileController.assignStudent(user2, course);
		if(done==2) {
			System.out.println("Swop unsuccessful!! There was a clash in student "+user2+"'s timetable");
			fileController.assignStudent(name, course);
			fileController.assignStudent(user2, course2);
			return;
		}
		fileController.logout(user2);
		
	}
	
	/**
	 * Method which allows student to change the login password
	 * @param username : login username of this student
	 * @param newPassword : new password of this student
	 */
	public static void updatePassword(String username, String newPassword) {
		fileController.updateStudentPassword(username,newPassword);
	}

	/**
	 * Method which allows student to update/change his email
	 * @param username : username of this student
	 * @param newEmail : new email of this student
	 */
	public static void updateEmail(String username, String newEmail) {
		fileController.updateStudentEmail(username,newEmail);
	}	
	
	/**
	 * Method which allows the student to log out from the starsplanner system
	 * @param name : name of the user logging out
	 */
	public static void logout(String name) {
		fileController.logout(name);
	}


		
	
}
