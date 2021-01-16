package P1;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/** 
 * A control class that implements all the necessary logical functions of our application
  */
public class fileController {
        /**
	 * Scanner object used to get input from user.
	 * 
	 */
	private static Scanner scanner = new Scanner(System.in);
	
        /**
         * Static private method that is used to obtain the credentials of users from the binary file.
         * @return UserDetails that are stored in an array-list
         */
	private static ArrayList<Object> getUsers() {
		ArrayList<Object> UsersDetails = binaryio.readSerializedObject("users.dat");
		return UsersDetails;
	}
/**
 * 
 * @param un stores the username of the user
 * @param pw stores the password of the user
 * @return in string whether the logged in user is a student or admin
 */
	public static String loginCheck(String un, String pw) {
		ArrayList<Object> UserDetails= getUsers();
		String hashpassword = hash(pw);
		
		for (int i = 0 ; i <UserDetails.size() ; i++) {
			User user  = (User) UserDetails.get(i);
			
			if(un.equals(user.getUsername()) && hashpassword.equals(user.getPassword())) 
			
				if(!user.isAdmin())
					return "student";
				
				else 					
					return "admin";
		}
		return "incorrect";
	}

//---------------User Function-----------------------
	/**
		* Hashes the input string using SHA-256 algorithm
		* @param string_Encrypt The string to encrypt (the password in this case)
		* @return the hashed string
		*/
        static String hash(String string_Encrypt) {
		
		String algorithm = "SHA-256";
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(string_Encrypt.getBytes());
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			string_Encrypt = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return string_Encrypt;
	}

//----------------Get info about student------------------- 
	/**
         * Obtains information about the students from binary file.
         * @return the student details in array-list form.
         */
	private static ArrayList<Object> getStudents() {
		ArrayList<Object> StudentsDetails = binaryio.readSerializedObject("students.dat");
		return StudentsDetails;
	}
	/**
         * 
         * @param un stores the username of the student
         * @return an object of the Student class with relevant details.
         */
	private static Student getStudent(String un) {
		ArrayList<Object> StudentDetails = getStudents();
		
		if(StudentDetails.size()==0) {
			System.out.println("Error reading the file!!");
			return null;
		}
		for (int i = 0 ; i <StudentDetails.size() ; i++) {
			Student stud  = (Student)StudentDetails.get(i);
			if(stud.getUsername().equals(un)) {
				return stud;
			}
			else if(i+1==StudentDetails.size())
				System.out.print("Incorect username");
		}	
		return null;
	}

//--------------------Changing attributes of students-----------------
	/**
         * Assigns a student to a particular index
         * @param name stores the name of the student
         * @param ind stores the index to be assigned to the student.
         */
        public static void assignIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getRegistered_index();
		update.add(ind);
		name.setRegistered_index(update);
		}
/**
 * Unassigns a student from a particular index.
 * @param name stores the name of the student.
 * @param ind  stores the name of the student
 */
	public static void unAssignIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getRegistered_index();
		Iterator<Index> itr = update.iterator();		
		while (itr.hasNext()) {
			Index nxt = itr.next(); 
			if (ind.getIndex_id().equals(nxt.getIndex_id()))
				itr.remove();
			break;
		}
		name.setRegistered_index(update);
	}
/**
 * Waitlists a student on a specific index.
 * @param name stores the name of the student
 * @param ind stores the index of the student
 */
	public static void waitIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getWaitlisted_index();
		update.add(ind);
		name.setWaitlisted_index(update);
	}
/**
 * Removes student from waitlisted index and places him on registered index
 * @param name stores the name of the student
 * @param ind stores the index of the student
 */
	public static void removewait(Student name, Index ind) {
		ArrayList<Index> update = name.getRegistered_index();
		Iterator<Index> waititr = update.iterator();
		while (waititr.hasNext()) {
			Index nxt = waititr.next(); 
			if (ind.getIndex_id().equals(nxt.getIndex_id())) {
				waititr.remove();
				break;
			}
		}
		name.setRegistered_index(update);
	}
/**
 * Updates access period for a specific student
 * @param username stores the username of the student 
 * @param Sdate stores the start date of the access period
 * @param Edate stores the end date of the access period
 * @return boolean value that indicates if student access is specifically updated.
 */	
	public static boolean updateStudentAccess(String username,LocalDate Sdate,LocalDate Edate) {
		if(!username.isEmpty()) {
			Student student = getStudent(username);
			if(student==null)
				return false;
			student.setAccessPeriod_Start(Sdate);
			student.setAccessPeriod_End(Edate);
			updateStudent(student);
			courseStudentUpdate(student);
		}
		else {
			ArrayList<Object> studentList = getStudents();
			for (int i = 0 ; i <studentList.size() ; i++) {
				Student student  = (Student)studentList.get(i);
				student.setAccessPeriod_Start(Sdate);
				student.setAccessPeriod_End(Edate);
				updateStudent(student);
				courseStudentUpdate(student);
			}	
			return true;
		}
		return true;
	}
/**
 * Updates specific student's password
 * @param username stores the username of the student.
 * @param newPassword stores the newly updated password of the student.
 */
	public static void updateStudentPassword(String username, String newPassword) {
		
		Student student = getStudent(username);
		String hashedPassword = hash(newPassword);
		student.setPassword(hashedPassword);
		courseStudentUpdate(student);
		updateStudent(student);
		
	}
/**
 * Updates student's email address
 * @param username stores the username of the student.
 * @param newEmail stores the newly updated email of the student.
 */
	public static void updateStudentEmail(String username, String newEmail) {
		
		Student student = getStudent(username);
		student.setEmail(newEmail);
		courseStudentUpdate(student);
		updateStudent(student);
	
	}

	
//------------Student functions-------------------------------
	/**
         * Checks the student access period 
         * @param name stores the name of the student.
         * @return an integer value that indicates if the access period is valid
         */
        public static int StudentAccessPeriod(String name) {
		Student stud = getStudent(name);
		LocalDate SD = stud.getAccessPeriod_Start();
		LocalDate ED = stud.getAccessPeriod_End();
		LocalDate today = LocalDate.now();
		
		if ((today.isAfter(SD) && today.isBefore(ED)) || today.isEqual(ED)||today.isEqual(SD) )
			return 0;
		else if(today.isAfter(ED))
			return -1;
		else
			return 1;
	}
/**
 * Prints student indices 
 * @param username stores the username of the student.
 * @param WaitReg indicates if the student is on waitlist or registered.
 * @return : returns the size of the array
 */
	public static int printStudentIndices(String username,String WaitReg) {
		Student obj=getStudent(username);
		ArrayList<Index> indices=null;
		if(WaitReg.equals("wait"))
			indices=obj.getWaitlisted_index();
		if(WaitReg.equals("reg"))
			indices=obj.getRegistered_index();
		if(indices.size()==0) {
			return -1;
		}
		System.out.println("+---------------------------------------------------------------------------+");
		System.out.printf("|%-14s %-16s %-12s %-12s %-16s | %n", "Course Code", "Index ID", "Waitlist", "Vacancy", "Schedule");
		System.out.println("+---------------------------------------------------------------------------+");
		for (int i = 0 ; i <indices.size() ; i++) {
			Index ind  = indices.get(i);
			System.out.printf(" %-15s %-18s %-12s %-9s %-11s %n", (i+1) + ") " + ind.getCourse_id(), ind.getIndex_id(), ind.getNo_waitlist(), ind.getVacancies(), printSchedule(ind.getTimings()));
			//System.out.println(i+1+") "+ind.getCourse_id()+"\t    \t"+ind.getIndex_id()+ "\t    \t" +ind.getNo_waitlist()+"\t    \t"+ind.getVacancies() + "\t"+printSchedule(ind.getTimings()));
		}
		return 0;
	}
/**
 * Logs out the student from the application
 * @param name stores the name of the student.
 */
	public static void logout(String name) {
		Student user=getStudent(name);
		updateStudent(user);
		courseStudentUpdate(user);		
	}

//-------------------Updating Student file to save changes-------------
	/**
         * Updates the student credentials and saves changes.
         * @param name stores the name of the student.
         */
        public static void updateStudent(Student name) {
		ArrayList<Object> StudentsDetails = getStudents();
		Iterator<Object> itr = StudentsDetails.iterator();
		
		while (itr.hasNext()) {
			Student nxt = (Student)itr.next(); 
			if (name.getUsername().equals(nxt.getUsername())) {
				itr.remove();
				break;
			}
		}
	
		StudentsDetails.add(name);
		binaryio.clearwriteSerializedObject("students.dat", StudentsDetails);
	}
	/**
         * Updates student index 
         * @param ind stores the ind object of Index class 
         * @param oldId stores the old index of the student that has to be updated.
         */
	public static void updateStudentIndex(Index ind,String oldId) {
		Student cur=null;
		ArrayList<Object> studentDetails = getStudents();
		Iterator<Object> studitr = studentDetails.iterator();
	
		ArrayList<Student> updated = new ArrayList<Student>();
		while (studitr.hasNext()){
			cur  = (Student)studitr.next();
			
			ArrayList<Index> indices = cur.getRegistered_index();
			Iterator<Index> itr = indices.iterator();
			int reg=0;
			while (itr.hasNext()) {
				Index nxt = itr.next(); 
				if (ind.getIndex_id().equals(nxt.getIndex_id()) || nxt.getIndex_id().equals(oldId)) {
					itr.remove();
					indices.add(ind);
					reg=1;
					cur.setRegistered_index(indices);
					break;
				}
			}
			if(reg==0) {
				ArrayList<Index> wait = cur.getWaitlisted_index();
				Iterator<Index> waititr = wait.iterator();
				while (waititr.hasNext()) {
					Index nxt = waititr.next(); 
					if (ind.getIndex_id().equals(nxt.getIndex_id()) || ind.getIndex_id().equals(oldId)) {
						waititr.remove();
						wait.add(ind);
						cur.setWaitlisted_index(wait);
						break;
					}
				}
			}
			studitr.remove();
			updated.add(cur);
		}	
		for(int i=0;i<updated.size();i++) {
			studentDetails.add(updated.get(i));
		}
		binaryio.clearwriteSerializedObject("students.dat", studentDetails);
			
	}

//=================================================================================================================================
	
//----------------Get info about courses----------------------
/**
 * Obtains information about courses by reading binary file.
 * @return array-list containing information about courses.
 */
	private static ArrayList<Object> getCourses() {
		ArrayList<Object> courses=binaryio.readSerializedObject("courses.dat");
		return courses;
	}
	/**
         * Gets details about course
         * @param courseId stores the unique course ID for a course
         * @return array-list with information about the course
         */
	private static Course getCourse(String courseId) {
		ArrayList<Object> courseDetails = getCourses();
		
		if(courseDetails.size()==0) {
			System.out.println("Error reading the file!!");
			return null;
		}
		for (int i = 0 ; i <courseDetails.size() ; i++) {
			Course cur  = (Course)courseDetails.get(i);
			if(cur.getCourseCode().equals(courseId)) {
				return cur;
			}
			else if(i+1==courseDetails.size())
				System.out.print("No course");
		}	
		return null;
	}

//--------------------Change Course Attributes---------------
	/**
         * Adds student to course that he/she is registered in
         * @param name stores the student name
         * @param ind  stores ind object of Index class with specific index 
         */
        private static void addStudenttoCourse(Student name,Index ind) {
		Course cour = getCourse(ind.getCourse_id());
		Hashtable<String, String> update = cour.getRegistered();
		update.put(name.getUsername(), ind.getIndex_id());
		cour.setRegistered(update);
		updateCoursefile_course(cour);
	}
	/**
         * Adds student to the waitlist of a specific course
         * @param name stores the name of the student
         * @param ind stores ind object of Index class with specific index
         */
	private static void addStudenttoWaitlist(Student name, Index ind) {
		Course cour = getCourse(ind.getCourse_id());
		Hashtable<String, String> update = cour.getWaitlist();
		update.put(name.getUsername(), ind.getIndex_id());
		cour.setWaitlist(update);
		updateCoursefile_course(cour);
	}
/**
 * Removes the student from specific course
 * @param name stores name of student
 * @param ind stores ind object of Index class with specific index
 */
	private static void courseRemoveStudent(Student name, Index ind) {
		Course cour = getCourse(ind.getCourse_id());
		Hashtable<String, String> update = cour.getRegistered();
		update.remove(name.getUsername());
		cour.setRegistered(update);
		updateCoursefile_course(cour);
	}
/**
 * Removes course from waitlist for specific student.
 * @param wait indicates the course for student on waitlist
 * @param ind stores ind object of Index class with specific index
 */
	private static void removeCourseWait(Student wait, Index ind) {
		Course cour = getCourse(ind.getCourse_id());
		Hashtable<String, String> update = cour.getWaitlist();
		update.remove(wait.getUsername());
		cour.setWaitlist(update);	
		updateCoursefile_course(cour);
	}
	
//--------------------Course/Index functions--------------
	/**
         * Allows for student to get registered in a course
         * @return string array with the registered course
         */
        public static String[] getRegisterIndex() {
		System.out.println("Choose a course to register: ");
		ArrayList<Object> courses=getCourses();
		int max=printAllCourses();
		System.out.println(max+1+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		Index ind=null;
		String[] course= {"",""};
		int choice = checkValidInt();

		
		if(choice-1<max) {
			System.out.println("Choose an Index to register: ");
			Course cour  = (Course)courses.get(choice-1);
			printIndices(cour,0); 
			System.out.println(" " + (cour.getIndices().size()+1) +") To go back.");
			System.out.println(" ");
			System.out.print("====>Enter choice: ");
			choice = checkValidInt();
			
			if(choice-1<cour.getIndices().size()) {
				ind = cour.getIndices().get(choice-1);
				course[0]= ind.getIndex_id();
				course[1]=ind.getCourse_id();
			}
			else {
				System.out.println("Back to previous menu");
				getRegisterIndex();
			}
		}
		else
			System.out.println("Back to Menu");
		
		return course;
	}
/**
 * Prints the vacancy in the course selected
 */
	public static void printVacancy() 
	{
		
		System.out.println("Choose a course to check vaccancy: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		
		int cur = fileController.checkValidInt();

		if(cur-1<max) 
			fileController.printIndices(null,cur-1);
		else
			System.out.println("Back to Menu");
	}
	/**
         * Prints all courses existing in the application
         * @return the size of the courses arraylist indicating number of courses
         */
	public static int printAllCourses() {
		ArrayList<Object> courses=fileController.getCourses();

		System.out.println("+-------------------------------------+");
		
		System.out.println("| Course Code \t \t Course Name  |");

		System.out.println("+-------------------------------------+");
		for (int i = 0 ; i <courses.size() ; i++) {
			Course cour  = (Course)courses.get(i);
			System.out.println(i+1+") "+cour.getCourseCode()+"\t      \t "+cour.getCourseName());
		}	
		
		return courses.size();
	}
	/**
         * Prints indices of the course that is selected
         * @param cour stores the course for which indexes are needed in obj format
         * @param i stores the position of the course in the course list.
         */
	public static void printIndices(Course cour, int i) {
		if(cour==null) {
			ArrayList<Object> courses=fileController.getCourses();
			cour=(Course)courses.get(i);
		}
		System.out.println("+---------------------------------------------------------------------------+");
		System.out.printf("|%-14s %-16s %-12s %-12s %-16s | %n", "Course Code", "Index ID", "Waitlist", "Vacancy", "Schedule");
		System.out.println("+---------------------------------------------------------------------------+");
		ArrayList<Index> indices=cour.getIndices();
		for (int j = 0 ; j <indices.size() ; j++) {
			Index ind  = indices.get(j);
			//System.out.println(j+1+") "+ind.getCourse_id()+"\t    \t"+ind.getIndex_id()+ "\t    \t"+ind.getNo_waitlist()+"\t    \t"+ind.getVacancies()+"\t"+printSchedule(ind.getTimings()));
			System.out.printf(" %-15s %-18s %-12s %-9s %-11s %n", (j+1) + ") " + ind.getCourse_id(), ind.getIndex_id(), 
					ind.getNo_waitlist(), ind.getVacancies(), printSchedule(ind.getTimings()));
		}	
	}
	/**
         * Changes index number for course specified
         * @param courseId stores the unique courseID 
         * @return string array with the changed index for course
         */
	public static String[] getChangeIndex(String courseId) {
		System.out.println("Choose Index to change: ");
		Course cour=getCourse(courseId);
		System.out.println("+---------------------------------------------------------------------------+");
		System.out.printf("|%-14s %-16s %-12s %-12s %-16s | %n", "Course Code", "Index ID", "Waitlist", "Vacancy", "Schedule");
		System.out.println("+---------------------------------------------------------------------------+");
		ArrayList<Index> indices=cour.getIndices();
		for (int j = 0 ; j <indices.size() ; j++) {
			Index ind  = indices.get(j);
			System.out.printf(" %-15s %-18s %-12s %-9s %-11s %n", (j+1) + ") " + ind.getCourse_id(), 
					ind.getIndex_id(), ind.getNo_waitlist(), ind.getVacancies(), printSchedule(ind.getTimings()));
		}
		System.out.println(" " + (indices.size()+1) +") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		
		String[] result= {"",""};
		int choice = checkValidInt();
		
		if(choice-1<indices.size())  {
			Index ind=indices.get(choice-1);
			result[0]=ind.getIndex_id();
			if(ind.getVacancies()>0) 
				result[1]="true";
		}
		return result;
	}
/**
 * Assigns student to either waitlist of registered based on vacancies
 * @param username stores the unique username of the student
 * @param course is a string array that stores list of courses
 * @return : returns integer that flags successfull assignment of the index
 */
	public static int assignStudent(String username, String course[]) {
		Course cour = getCourse(course[1]);
		Index ind= findIndex(cour,course[0]);
		Student name=getStudent(username);
		int addAU = cour.getAu();
		int studentAU =name.getRegisteredAU();
		
		Hashtable<String,String> reg=cour.getRegistered();
		Hashtable<String,String> wait=cour.getWaitlist();
		
		if((wait.get(username)!=null)
				||(reg.get(username)!=null)) {
			return -1;
		}

		//if no clash
		if(!clash(username,course)) {
			if(!maxedAu(username,course)){
				if (ind.getVacancies() > 0) {
					ArrayList<Student> update = ind.getRegistered();
					update.add(name);
					ind.setRegistered(update);
					ind.setVacancies(ind.getVacancies()-1);
					name.setRegisteredAU(studentAU + addAU);
					assignIndex(name,ind);
					addStudenttoCourse(name,ind);
					
					//save the change to file
					courseUpdate(ind,null);
					updateStudentIndex(ind,null);
					
					courseStudentUpdate(name);
					updateStudent(name);
					
					return 0;
				}
				else {
					ind.setNo_waitlist(ind.getNo_waitlist()+1);
					ArrayList<Student> update = ind.getWaitlist();
					update.add(name);
					ind.setWaitlist(update);
					addStudenttoWaitlist(name,ind);
					waitIndex(name,ind);
	
					//save the change to file
					courseUpdate(ind,null);
					updateStudentIndex(ind,null);
					
					courseStudentUpdate(name);
					updateStudent(name);
					
					return 1;
				}
			}
			else
				return 2;
		}
		//if clash
		return 3;
	
	}
	/**
         *  method that prompts user to choose the index to be dropped
         * @param user : username of student calling this function
         * @return : returns string array of course code and index id
         */
	public static String[] getDropIndex(String user) {
		Student obj=getStudent(user);
		ArrayList<Index> indices= obj.getRegistered_index();
		int printed = printStudentIndices(user,"reg");
		String[] course= {"",""};
		if(printed==-1) {
			System.out.println("You have not been registered to any courses yet!!!");
			return course;
		}
			
		System.out.println(" " + (indices.size()+1) +") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		
		Index ind=null;
		int choice = checkValidInt();
		
		if(choice-1<indices.size()) {
			ind  = indices.get(choice-1);
			course[0]= ind.getIndex_id();
			course[1]= ind.getCourse_id();
		}
		else
			System.out.println("Back to Menu");
		
		return course;
	
	}
/**
 * Unassigns a student from a specific course
 * @param username stores unique username of student.
 * @param course stores the course details
 * @param swop stores the variable to enable swapping
 */
	public static void unAssignStudent(String username, String course[], String swop) {
		Student student=getStudent(username);
		Course cour=getCourse(course[1]);
		Index ind=findIndex(cour,course[0]);
		
		ArrayList<Student> update = ind.getRegistered();
		Iterator<Student> itr = update.iterator();		
		while (itr.hasNext()) {
			Student nxt = itr.next(); 
			if (student.getUsername().equals(nxt.getUsername()))
				itr.remove();
			break;
		}
		ind.setRegistered(update);
		courseRemoveStudent(student,ind);
		unAssignIndex(student,ind);
		ind.setVacancies(ind.getVacancies()+1);
		
		student.setRegisteredAU(student.getRegisteredAU() - cour.getAu());
		System.out.println("The index has been removed from your timetable");
		
		if(ind.getNo_waitlist()>0 && swop.equals("drop")) {
			assignWaitlistStudent(ind);
		}
	
		//save the change to file
		courseUpdate(ind,null);
		updateStudentIndex(ind,null);
		
		courseStudentUpdate(student);
		updateStudent(student);
	}
/**
 * Assigns student to waitlist
 * @param ind stores ind object of Index class to assign student to specific index
 */
	private static void assignWaitlistStudent(Index ind) {
		ArrayList<Student> waitlist = ind.getWaitlist();
		int success=1;
		while((success!=0 && waitlist.size()!=0) ||( ind.getVacancies()!=0 && waitlist.size()!=0)){
			Student wait=waitlist.get(0);

			removewait(wait,ind);
			removeCourseWait(wait,ind);	
			for(int i=0; i<waitlist.size();i++) {
				Student nxt = waitlist.get(i); 
				if (wait.getUsername().equals(nxt.getUsername())) {
					waitlist.remove(i);
					break;
				}
			} 
			ind.setWaitlist(waitlist);
			ind.setNo_waitlist(ind.getNo_waitlist()-1);
			
			String[] newCourse= {ind.getIndex_id(),ind.getCourse_id()};
			success=assignStudent(wait.getUsername(),newCourse);
			
			String[] message = {"",""};
			message[0] = wait.getEmail();
			
			if(success==-1) {
				message[1]="Sorry!! You have already registered to course: "  +ind.getCourse_id() ;
			}
			
			else if(success==0) {
				message[1] = "You have been registered to course: " +ind.getCourse_id()+ ", Index Id: "+ind.getIndex_id();
			}

			else if(success==2) {
				message[1] = "Sorry, you have reached your max AU limit!!!\n" +ind.getCourse_id()+ ", Index Id: "
						+ind.getIndex_id()+",has been removed from your waitlist";
			}

			else if(success==3) {
				message[1] = "Sorry!! There is a clash in your timetable for course: " 
						+ind.getCourse_id()+ ", Index Id: "+ind.getIndex_id()+"\n You have been removed from the waitlist";
			}
			NotificationController.sendEmail(message);

		}
		
	}
/**
 * Checks for a clash between registered modules
 * @param name stores the name of student with clash
 * @param course stores the courses that are having clashes
 * @return a boolean value determining if there is a clash
 */
	public static boolean clash(String name, String[] course) {
		Student stud = getStudent(name);
		Course cour = getCourse(course[1]);
		Index newInd = findIndex(cour,course[0]);
		
		ArrayList<Index> registered = stud.getRegistered_index();
		ArrayList<Schedule> newclass = newInd.getTimings();
		
		for(Index ind : registered) {
			ArrayList<Schedule> classes = ind.getTimings();
				for(Schedule sch : classes) {
					LocalTime start = sch.getStart();
					LocalTime end = sch.getEnd();
					
					for(Schedule newsch : newclass) {
						if( (sch.getDayofWeek() == newsch.getDayofWeek()) && 
								(getScheduleWeek(sch).equals(getScheduleWeek(sch))) ) {
							LocalTime newstart = newsch.getStart();
							LocalTime newend = newsch.getEnd();
							
							if( Math.abs(start.until(end,ChronoUnit.HOURS)) >
								Math.abs(start.until(newstart,ChronoUnit.HOURS))) 
								return true;
							
							if( Math.abs(newstart.until(newend,ChronoUnit.HOURS)) >
								Math.abs(start.until(newstart,ChronoUnit.HOURS))) 
								return true;
							
						}
					}
				}
		}
		return false;
	}
	/**
         * checks if maximum AU's of the student have been met
         * @param username stores username of student
         * @param course stores courses where student is registered
         * @return 
         */
	private static boolean maxedAu(String username, String[] course) {
		Student student = getStudent(username);
		Course newCourse = getCourse(course[1]);
		
		if(newCourse.getAu() + student.getRegisteredAU() > Student.getMaxau()) 
			return true;
		
		return false;
	}
	
//-------------------Update Course file to save changes------------
	/**
         * Used to update the course based on index numbers
         * @param ind refers to the ind object of the Index class 
         * @param oldId refers to the old index of the class that needs to be updated
         */
        public static void courseUpdate(Index ind,String oldId) {
		Course cur=null;
		ArrayList<Object> courseDetails = getCourses();
		Iterator<Object> courseitr = courseDetails.iterator();
		int coursepos=0,indpos=0;
		
		while (courseitr.hasNext()){
			cur  = (Course)courseitr.next();
			if(cur.getCourseCode().equals(ind.getCourse_id())) {
				courseitr.remove();
				ArrayList<Index> indices = cur.getIndices();
				Iterator<Index> itr = indices.iterator();
				while (itr.hasNext()) {
					Index nxt = itr.next(); 
					if (ind.getIndex_id().equals(nxt.getIndex_id()) || nxt.getIndex_id().equals(oldId) ) {
						itr.remove();
						indices.add(indpos,ind);
						cur.setIndices(indices);
						break;
					}
					indpos++;
				}
				
				courseDetails.add(coursepos,cur);
				break;
			}	
			coursepos++;
		}
		
		
		binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
	}
	/**
         * Updates courses for the student specified
         * @param name stores student's name 
         */
	private static void courseStudentUpdate(Student name) {
		Course cur=null;
		ArrayList<Object> courseDetails = getCourses();		
		String Username =name.getUsername();		
		
		for(int i=0;i<courseDetails.size();i++){
			cur  = (Course)courseDetails.get(i);
			
			Hashtable<String, String> regs = cur.getRegistered();
			String indId= regs.get(Username);

			Hashtable<String, String> wait = cur.getWaitlist();
			String indwaitId= wait.get(Username);
			int indpos=0;
			
			if(indId !=null) {
				courseDetails.remove(i);
				ArrayList<Index> indices = cur.getIndices();
			
				for(int j=0;j<indices.size();j++){
					Index ind = indices.get(j);
				
					if(indId == ind.getIndex_id()) {
						indices.remove(j);
						ArrayList<Student> students = ind.getRegistered();
						Iterator<Student> studitr = students.iterator();
					
						while(studitr.hasNext()) {
							Student stud=studitr.next();
						
							if(Username == stud.getUsername()) {
								studitr.remove();
								students.add(name);
								break;
								}
						}
						ind.setRegistered(students);
						indices.add(indpos,ind);
						break;
					}
					indpos++;
				}
				cur.setIndices(indices);
				courseDetails.add(i,(Object)cur);
				
			}
			else if(indwaitId!=null) {	
				courseDetails.remove(i);
				ArrayList<Index> indices = cur.getIndices();
			
				for(int j=0;j<indices.size();j++){
					Index ind = indices.get(j);
				
					if(indId == ind.getIndex_id()) {
						indices.remove(j);
						ArrayList<Student> students = ind.getWaitlist();
						Iterator<Student> studitr = students.iterator();
					
						while(studitr.hasNext()) {
							Student stud=studitr.next();
						
							if(Username == stud.getUsername()) {
								studitr.remove();
								students.add(name);
								break;
								}
						}
						ind.setRegistered(students);
						indices.add(indpos,ind);
						break;
					}
					indpos++;
				}
				cur.setIndices(indices);
				courseDetails.add(i,(Object)cur);
			}
		}
		 
		binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
	}
	/**
         * Updates the course specified as parameter.
         * @param cour stores the object of Course class that has to be updated
         */
	private static void updateCoursefile_course(Course cour) {
		ArrayList<Object> courseDetails = getCourses();
		Iterator<Object> courseitr = courseDetails.iterator();
		int coursepos=0;
		
		while(courseitr.hasNext()){
			Course cur  = (Course)courseitr.next();
			if((cour.getCourseCode()).equals(cur.getCourseCode()) || (cour.getCourseName()).equals(cur.getCourseName()) ) {
				courseitr.remove();
				
				ArrayList<Index> indices = cur.getIndices();
													
				for (int i = 0; i<indices.size(); i++){
					Index curInd  = (Index)indices.get(i);
					indices.remove(i);
					curInd.setCourse_id(cour.getCourseCode());
					updateStudentIndex(curInd,null);
					indices.add(i,curInd);
				}
				cour.setIndices(indices);
				courseDetails.add(coursepos,(Object)cour);
				break;
			}
			coursepos++;
		}
			binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
	}

//====================================================================================================================
//-----------------Index info-----------------------------------------
	/**
         * Finds the index for the course specified
         * @param cur refers to the object of Course class for which index info is needed
         * @param indId refers to the index ID useful for finding the index
         * @return the index obtained
         */
        private static Index findIndex(Course cur, String indId) {
		ArrayList<Index> indices = cur.getIndices();
		for(Index nxt : indices)
			if(nxt.getIndex_id().equals(indId))
				return nxt;
		
		return null;
	}
	/**
         * Finds the index for a course that has been registered
         * @param name refers to the name of the course
         * @param courseId refers to the index ID useful for finding the index
         * @return string value storing the course that has been registered
         */
	public static String getIndexReg_Course(String name, String courseId) {
		Course cour = getCourse(courseId);
		Hashtable <String,String> reg = cour.getRegistered();
		return reg.get(name);
	}

//------------------Change index attributes--------------------------------
	/**
         * Updates index number for course that is chosen
         */
        public static void updateIndex() {
		System.out.println("Choose a course whose index to update:");
		int max = printAllCourses();
		System.out.println(max+1+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		int cur = checkValidInt();
		

		if (cur-1<max) 
		{
			Course course = (Course) getCourses().get(cur-1);
			
			System.out.println("Choose index to update:");
			fileController.printIndices(course,0);
			System.out.println((" " + (course.getIndices().size() + 1)) + ") To go back");
			
			int indexToUpdate = checkValidInt();
			if (indexToUpdate >= course.getIndices().size())
			{
				System.out.println("Back To Menu");
				return;
			}
			Index indexChosen = course.getIndices().get(indexToUpdate - 1);
			System.out.println("Please select one of the options below:");
			System.out.println("1. Change Index ID");
			System.out.println("2. Change Vacancy");
			
			int choice = checkValidInt();
			scanner.nextLine();
			
			switch (choice)
			{
				case 1:
					String oldID = indexChosen.getIndex_id();
					System.out.println("Enter new Index ID");
					String newID =  scanner.nextLine();
					if(findIndex(course,indexChosen.getIndex_id())==null)
						System.out.println("The entered Index ID already exists");
					else {
						indexChosen.setIndex_id(newID);
						updateStudentIndex(indexChosen,oldID);
						courseUpdate(indexChosen,oldID);

						System.out.println("The index ID has been updated");
					}
					break;
					
				case 2: 
					int newVacancy;
					do{
						System.out.println("Enter new vacancy");
						newVacancy = scanner.nextInt();
					}while(newVacancy<=0);
					
					indexChosen.setVacancies(newVacancy);
					assignWaitlistStudent(indexChosen);
					updateStudentIndex(indexChosen,null);
					courseUpdate(indexChosen,null);
					System.out.println("The index vancancy has been updated");
					break;
			}
			
		}
		else
		{
			System.out.println("Back to Menu");
			return;
		}
	}
	
//------------------Schedule info------------------------------------------
	/**
         * Checks the schedule per week, if it is even week, odd week or both
         * @param sch is an object of Schedule class that determines weekly schedule
         * @return whether course schedule is in even or odd weeks or both
         */
        public static String getScheduleWeek(Schedule sch) {
		if(sch.isEven() && sch.isOdd())
			return "both";
		else if (sch.isEven())
			return "even";
		else if (sch.isOdd())
			return "odd";
		
		return "both";
	}

//-----------------Schedule Function--------------------------------------
	/**
         * Prints the schedule for classes in a course
         * @param classes refers to the schedule for the class
         * @return details which contains all details for class schedule
         */
        public static String printSchedule(ArrayList<Schedule> classes) {
		String daysWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		String details="";
		for (Schedule sch : classes) {
			String week = getScheduleWeek(sch)!="both" ? "("+getScheduleWeek(sch)+" week)" : " ";
			if(details.equals(""))
				details = sch.getType()+": "+sch.getStart()+"-"+sch.getEnd()+","+ daysWeek[sch.getDayofWeek() - 1] +week ;
			else
				details = details +"|\t"+ sch.getType()+": "+sch.getStart()+"-"+sch.getEnd()+","+ daysWeek[sch.getDayofWeek() - 1] +week ;
		}
				
		return details;
	}

//---------------------Admin Function-------------------------------------------------------
	/**
         * Used to enter student details and add student
         * @param name stores name of student
         * @param userName stores unique username of student
         * @param password stores encrypted password for the student
         * @param studentID stores unique Student ID for student
         * @param sdate stores start date of student
         * @param edate stores end date of student
         * @param nationality stores nationality of student
         * @param gender stores gender orientation that the student accepts
         * @param Email stores the email address of the student
         * @return boolean value indicating if student is created
         */
        public static boolean makeStudent(String name, String userName, String password, String studentID, 
			LocalDate sdate,LocalDate edate,String nationality,String gender, String Email ) {
		
		String hashpassword = hash(password);
		ArrayList <Object> check = getStudents();
		
		for (int j = 0; j<check.size(); j++)
		{
			Student stud = (Student) check.get(j);
			if (studentID.equals(stud.getStudent_id()) || userName.equals(stud.getUsername()))
				return false;
		}
		
		User Stud = new Student(name, userName, hashpassword, studentID,sdate,edate,Email,nationality, gender);
		
		binaryio.writeSerializedObject("students.dat", Stud);
		binaryio.writeSerializedObject("users.dat", (User) Stud);
		
		return true;
	}
/**
 * Defines a course for the application
 * @param code refers to the course code
 * @param name refers to the name of the course
 * @return boolean value indicating if course is created
 */
	public static boolean makeCourse(String code, String name) {
		
		ArrayList <Object> check = getCourses();
		
		for (int j = 0; j<check.size(); j++){
			Course cour = (Course) check.get(j);
			if (code.equals(cour.getCourseCode()))
				return false;
		}
		
		System.out.print("Enter number of AUs for " + code+": ");
		int au = checkValidInt();
		
		System.out.printf("Enter type for course %s (lec/lec&tut/lec,tut&lab):",code);
		String type = scanner.next();
		while (!type.equals("lec") && !type.equals("lec&tut") && !type.equals("lec,tut&lab")) 
		{
			System.out.printf("Please enter valid type for course %s (lec/lec&tut/lec,tut&lab):",code);
			type = scanner.next();
		}
		
		System.out.print("Enter day number of week for lecture of " + code+": ");
		int lecday = checkValidDayOfWeek();
		
		System.out.printf("Enter start time for lecture (HH:MM 24Hrs):");	
		LocalTime lecst = checkValidTime();

		System.out.printf("Enter end time for lecture (HH:MM 24Hrs):");
		LocalTime lecet = checkAfterStart(lecst);
		
		System.out.printf("Enter venue for lecture:");		
		String lecvenue = scanner.next();
		
		ArrayList<Index> indices = makeIndex(code,type,lecday,lecst,lecet,lecvenue);
		
		Course newcourse = new Course (code, name, indices,au);
		binaryio.writeSerializedObject("courses.dat", newcourse);
		return true;
	}
/**
 * Used to create index for a course
 * @param code refers to 
 * @param type stores what type of course is to be created
 * @param lecday stores the day of the lecture
 * @param lecst stores the start time of the lecture
 * @param lecet stores the end time of the lecture
 * @param lecvenue stores the venue of the lecture
 * @return an array-list with the created index
 */
	public static ArrayList<Index> makeIndex(String code, String type, int lecday, LocalTime lecst, 
			LocalTime lecet, String lecvenue) {
		
		ArrayList<Index> indices = new ArrayList<Index>();
		if(!type.equals("lec")) 
		{	
			System.out.printf("Enter number of indices for %s :",code);
			int num = checkValidInt();
			
			for(int i=1;i<=num;i++)
			{
				System.out.printf("Enter Index id for index %d",i);		
				String index_id = scanner.next();
									

				System.out.printf("Enter number of vacancies for index %d",i);
				int slot = checkValidInt();
		
				ArrayList<Schedule> sch= makeSchedule(index_id,type,lecday,lecst,lecet,lecvenue);	
				indices.add(new Index(code,index_id,type, slot, sch));
			}
			
		}
		else {
			
			System.out.printf("Enter number of vacancies for the lecture");		
			int slot = checkValidInt();
			ArrayList<Schedule> sch= makeSchedule(code+"_01",type,lecday,lecst,lecet ,lecvenue);
			indices.add(new Index(code,code+"_01",type,slot,sch));
		
		}
		return indices;
	}
/**
 * Used to make schedule for course
 * @param IndID stores the index for the student
 * @param type stores the type of course 
 * @param lecday stores the day the lecture happens
 * @param lecst stores the starting time of lecture
 * @param lecet stores the ending time of lecture
 * @param ven stores the venue of the lecture
 * @return array-list with all credentials for course
*/
	public static ArrayList<Schedule> makeSchedule(String IndID,String type,int lecday,
			LocalTime lecst, LocalTime lecet, String ven) {
		
		final String[] classtype = {"Lecture","Tutorial","Lab"};
		ArrayList<Schedule> sch = new ArrayList<Schedule>();
		for(int j=0;j<3;j++) {
			boolean e=true,o=true;
			int day;
			LocalTime st,et;
			String venue;
			
			if(j==0) {
				day=lecday;
				st=lecst;
				et=lecet;
				venue=ven;
			}
			
			else {
				System.out.print("Enter day number of week for "+ classtype[j]+" of " + IndID+": ");
				day = checkValidDayOfWeek();
				
				System.out.printf("Enter start time for %s (HH:MM 24Hrs):",classtype[j]);		
				st = checkValidTime();
				
				System.out.printf("Enter end time for %s (HH:MM 24Hrs):",classtype[j]);		
				et = checkAfterStart(st);
				
				System.out.printf("Enter venue for %s:",classtype[j]);		
				venue = scanner.next();
				
				if(j>0) {
					System.out.printf("Enter week for %s (even/odd/both):",classtype[j]);
					String evenodd = scanner.next();
					while (!evenodd.equals("even") && !evenodd.equals("odd") && !evenodd.equals("both")) 
					{
						System.out.printf("Please enter valid week for %s (even/odd/both):", classtype[j]);
						evenodd = scanner.next();
					}
					if(evenodd.toLowerCase().equals("even"))
						o=false;
					if(evenodd.toLowerCase().equals("odd"))
						e=false;
				}
			}
			sch.add(new Schedule(classtype[j],day,st,et,e,o,venue));
			if((type.equals("lec") && j==0) || (type.equals("lec&tut") && j==1))
				break;
		}
		return sch;
	}
/**
 * Displays the list of students by index
 * @return integer value to indicate if list of students has been displayed
 */
	public static int showIndexStudents(){
		
		ArrayList <Object> courseList = getCourses();
		
		System.out.println("Select Course: ");
		printAllCourses();
		System.out.println(courseList.size()+1+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		
		int choice = checkValidInt();
		
		if(choice>courseList.size())
		{
			System.out.println("Back To Menu");
			return 0;
		}
		System.out.println("Select Index: ");
		Course courseSelected = (Course) courseList.get(choice-1);
		ArrayList <Index> indices = courseSelected.getIndices();
		
		printIndices(courseSelected, 0);
		System.out.println(" " + (indices.size()+1)+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		
		int indexSelected = checkValidInt();
		
		if(indexSelected>indices.size())
		{	
			System.out.println("Back to previous menu");
			showIndexStudents();
		}
		
		else {
			Index chosenIndex = indices.get(indexSelected-1);
			ArrayList <Student> studentInIndex = chosenIndex.getRegistered();
			
			if(studentInIndex.size()==0)
				return -1;
			
			System.out.println("+-------------------------------------------------------------------------------------------------+");
			System.out.printf("| %-23s %-23s %-23s %-23s |%n", "Student Name","Student ID", "Gender", "Nationality");
			System.out.println("+-------------------------------------------------------------------------------------------------+");
			
			for (Student student : studentInIndex)
			{
				System.out.printf("| %-23s %-26s %-20s %-23s |%n" , student.getName(),student.getStudent_id()
						, student.getGender(), student.getNationality());
			}
		}
		System.out.println("+-------------------------------------------------------------------------------------------------+");

		return 0;
	}
	/**
         * Displays the students enrolled in a course
         * @return integer value indicating if the students by course have been printed
         */
	public static int showCourseStudents(){
		
		ArrayList <Object> courseList = getCourses();
		
		System.out.println("Select Course: ");
		printAllCourses();
		System.out.println(courseList.size()+1+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		
		int choice = checkValidInt();
		
		if(choice>courseList.size())
		{
			System.out.println("Back to Menu");
			return 0;
		}
		
		Course courseSelected = (Course) courseList.get(choice-1);
		
		Hashtable<String,String> registered = courseSelected.getRegistered();
		if(registered.size()==0)
			return -1;
		System.out.println("+-------------------------------------------------------------------------------------------------+");
		System.out.printf("| %-23s %-23s %-23s %-23s |%n", "Student Name", "Gender", "Nationality", "Registered Index");
		System.out.println("+-------------------------------------------------------------------------------------------------+");
		Set<String> students = registered.keySet();
		for(String stud : students){
			Student student = fileController.getStudent(stud);
			System.out.printf("| %-26s %-20s %-26s %-20s |%n" , student.getName(), student.getGender(), student.getNationality(), registered.get(stud));
		}
		System.out.println("+-------------------------------------------------------------------------------------------------+");

		return 0;
	}
/**
 * Used to update an existing course by updating either its code or name
 */
	public static void updateExistingCourse() {
		System.out.println("Choose a course to update: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		System.out.println(" ");
		System.out.print("====>Enter choice: ");
		int cur = checkValidInt();
		if (cur-1 < max){
			
			Course courseSelected = (Course) fileController.getCourses().get(cur-1);
			System.out.println("Please select one of the options below:");
			System.out.println("1) Edit Course Code");
			System.out.println("2) Edit Course Name");
			System.out.println("3) To go back");
			int choice = checkValidInt();
			scanner.nextLine();
			switch (choice)
			{
				case 1:
					System.out.println("----------Making Changes to " + courseSelected.getCourseCode() + " " + courseSelected.getCourseName() + "----------");
					System.out.println("Enter New Course Code:");
					String codeNew = scanner.nextLine();
					courseSelected.setCourseCode(codeNew);
					System.out.println ("Course Code Changed To: " + courseSelected.getCourseCode());
					break;
					
				case 2: 
					System.out.println("----------Making Changes to " + courseSelected.getCourseCode() + " " + courseSelected.getCourseName() + "----------");
					System.out.println("Enter New Course Name:");
					String nameNew = scanner.nextLine();
					courseSelected.setCourseName(nameNew);
					System.out.println ("Course Name Changed To: " + courseSelected.getCourseName());
					break;
				
				default:
					System.out.println("Back To Menu");
					return;
					
			}
			fileController.updateCoursefile_course(courseSelected);

		}

		else 
		{
			System.out.println("Back To Menu");
		}
	}
	/**
         * Used to drop a specific course from the application
         */
	public static void dropCourse(){
		
		System.out.println("Select course to drop");
		printAllCourses();
		System.out.println((getCourses().size()+1) + ") To go back");
		int courseSelected = checkValidInt();
		ArrayList<Object> courselist = getCourses();
		if (courseSelected >= courselist.size())
		{
			System.out.println("Back to Menu");
			return;
		}
		Course courseToDrop = (Course) courselist.get(courseSelected-1);
		
		Hashtable<String,String> registered = courseToDrop.getRegistered();
		Set<String> students = registered.keySet();
		String[] course = {"",courseToDrop.getCourseCode()};
		for (String stud : students)
		{
			course[0]=registered.get(stud);
			unAssignStudent(stud, course,"swop");
		}
		
		Hashtable <String, String> waitlisted = courseToDrop.getWaitlist();
		Set<String> studentsInWaitList = waitlisted.keySet();
		//ArrayList <Index> indicesInCourse = courseToDrop.indices;
		for (String stud : studentsInWaitList)
		{
			Student studentToRemove = getStudent(stud);
			Index indexToRemove = findIndex(courseToDrop, waitlisted.get(stud));
			removewait(studentToRemove, indexToRemove);
		}
		
		courselist.remove(courseSelected-1);
		binaryio.clearwriteSerializedObject("courses.dat", courselist);
		
		System.out.println("Course " + courseToDrop.getCourseCode() + ", " + courseToDrop.getCourseName() + " dropped!");
	}
	/**
         * Prints the list of all students with name, username, student ID, Nationality and Gender
         */
	public static void printAllStudents(){
		
		ArrayList <Object> studentList = getStudents();
		System.out.println("+--------------------------------------------------------------------------------------------------------+");
		System.out.println("|Student Name \t \t Username \t \t Student ID \t \t Nationality \t \t Gender  |");
		System.out.println("+--------------------------------------------------------------------------------------------------------+");
		for (Object student : studentList)
		{
			Student stud = (Student) student;
			System.out.printf("| %-23s %-23s %-23s %-23s %-7s|%n", stud.getName(), stud.getUsername(), 
					stud.getStudent_id(), stud.getNationality(),stud.getGender());
		}
		System.out.println("+--------------------------------------------------------------------------------------------------------+");
	}
	
//=========================================================================================================
//------------------------------Error Handling Methods-----------------------------------------------
	/**
         * checks if the integer entered is a valid integer for the expression
         * @return the variable slot after checking vacancies
         */
        public static int checkValidInt ()
	{
		int slot;
		while (true)
		{
			try 
			{
				slot = scanner.nextInt();
				if (slot < 1)
				{
					throw new ArithmeticException();
				}
				else
				{
					break;
				}
			}
			catch (Exception e)
			{
				System.out.println("Please Enter A Valid Number!");
				scanner.nextLine();
			}
		}
		
		return slot;
	}
	/**
         * checks if the day of week entered is valid
         * @return the variable slot after checking its validity for the expression
         */
	private static int checkValidDayOfWeek ()
	{
		int slot;
		while (true)
		{
			try 
			{
				slot = scanner.nextInt();
				if (slot < 1 || slot > 5)
				{
					throw new ArithmeticException();
				}
				else
				{
					break;
				}
			}
			catch (Exception e)
			{
				System.out.println("Please Enter A Valid Number Between 1 (Monday) and 5 (Friday)!");
				scanner.nextLine();
			}
		}
		
		return slot;
	}
/**
 * Checks if the time entered for the course details is a valid expression
 * @param time stores the time that has been entered by the user
 * @return boolean value that indicates if time entered is valid
 */
	private static boolean isValidTime(String time) { 
		  
        // Regex to check valid time in 24-hour format. 
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]"; 
  
      
        Pattern p = Pattern.compile(regex); 
  
    
        if (time == null) { 
            return false; 
        } 
  
        // Pattern class contains matcher() method 
        // to find matching between given time 
        // and regular expression. 
        Matcher m = p.matcher(time); 
        return m.matches(); 
    } 
	/**
         * checks if valid time has been entered in a 24hr format
         * @return time expression after checking its validity
         */
	private static LocalTime checkValidTime()
	{
		String timeInput =scanner.next();
		LocalTime time; 
		while (!isValidTime(timeInput))
		{
				System.out.printf("Entry Invalid! \n Enter valid time (HH:MM 24Hrs):");	
				timeInput =scanner.next();
		}
		time=LocalTime.parse(timeInput);
		return time;
	}
	/**
         * Checks if time entered is after the start time declared
         * @param start refers to the start time for access period
         * @return end time expression after checking if it is after start time
         */
	private static LocalTime checkAfterStart(LocalTime start)
	{
		LocalTime end = checkValidTime();
		while (end.isBefore(start))
		{
			System.out.println("End Time Cannot Be Earlier Than Start Time!");
			System.out.print("Enter valid end time (HH:MM 24Hrs): ");
			end = checkValidTime();
		}
		return end;
	}

}


