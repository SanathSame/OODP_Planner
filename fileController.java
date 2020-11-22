package P1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;



public class fileController {

	private static Scanner scanner = new Scanner(System.in);
	
//----------------Get info about user--------------------- 
	public static ArrayList<Object> getUsers() {
		ArrayList<Object> UsersDetails = binaryio.readSerializedObject("users.dat");
		return UsersDetails;
	}
	
	public static String[] getUser_Details(Object user) {
		User firstuser = (User) user;
		String un = firstuser.getUsername();
		String pw = firstuser.getPassword();
		String ad = (firstuser.getAdmin())? "true" : "false";
		
		String ret[]= {un,pw,ad};
		return ret;
	}

	public static String loginCheck(String un, String pw) {
		ArrayList<Object> UserDetails= getUsers();
		String hashpassword = hash(pw);
		
		for (int i = 0 ; i <UserDetails.size() ; i++) {
			User user  = (User) UserDetails.get(i);
			
			if(un.equals(user.getUsername()) && hashpassword.equals(user.getPassword())) 
			
				if(!user.getAdmin())
					return "student";
				
				else 					
					return "admin";
		}
		return "incorrect";
	}

//---------------User Function-----------------------
	public static String hash(String string_Encrypt) {
		/**
		* Hashes the input string using SHA-256 algorithm
		* @param string_Encrypt The string to encrypt (the password in this case)
		* @return the hashed string
		*/
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
	
	public static ArrayList<Object> getStudents() {
		ArrayList<Object> StudentsDetails = binaryio.readSerializedObject("students.dat");
		return StudentsDetails;
	}
	
	public static Student getStudent(String un) {
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
	public static void assignIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getIndices();
		update.add(ind);
		name.setIndices(update);
	}

	public static void unAssignIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getIndices();
		Iterator<Index> itr = update.iterator();		
		while (itr.hasNext()) {
			Index nxt = itr.next(); 
			if (ind.getIndexId().equals(nxt.getIndexId()))
				itr.remove();
			break;
		}
		name.setIndices(update);
	}

	public static void waitIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getWaitIndices();
		update.add(ind);
		name.setwaitIndices(update);
	}

	public static void removewait(Student name, Index ind) {
		ArrayList<Index> update = name.getWaitIndices();
		Iterator<Index> waititr = update.iterator();
		while (waititr.hasNext()) {
			Index nxt = waititr.next(); 
			if (ind.getIndexId().equals(nxt.getIndexId())) {
				waititr.remove();
				break;
			}
		}
		name.setwaitIndices(update);
	}
	
//------------Student functions-------------------------------
	public static int StudentAccessPeriod(String name) {
		Student stud = getStudent(name);
		LocalDate SD = stud.getstartDate();
		LocalDate ED = stud.getendDate();
		LocalDate today = LocalDate.now();
		
		if (today.isAfter(SD) && today.isBefore(ED))
			return 0;
		else if(today.isAfter(ED))
			return 1;
		else
			return -1;
	}

	public static int printStudentIndices(String username,String WaitReg) {
		Student obj=getStudent(username);
		ArrayList<Index> indices=null;
		if(WaitReg.equals("wait"))
			indices=obj.getWaitIndices();
		if(WaitReg.equals("reg"))
			indices=obj.getIndices();
		if(indices.size()==0) {
			return -1;
		}
		System.out.println("   Course Code\tIndex ID\t Waitlist\t Vacancy \tSchedule ");
		System.out.println("---------------------------------------------------------------------------");
		for (int i = 0 ; i <indices.size() ; i++) {
			Index ind  = indices.get(i);
			System.out.println(i+1+") "+ind.getCourseId()+"\t    \t"+ind.getIndexId()+
					"\t    \t"+ind.getNoWaitlist()+"\t    \t"+ind.getVacancy()+
					"\t"+printSchedule(ind.getSchedule()));
		}
		return 0;
	}

	public static void logout(String name) {
		Student user=getStudent(name);
		updateStudent(user);
		courseStudentUpdate(user);		
	}

//-------------------Updating Student file to save changes-------------
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
	
	public static void updateStudentIndex(Index ind) {
		Student cur=null;
		ArrayList<Object> studentDetails = getStudents();
		Iterator<Object> studitr = studentDetails.iterator();
	
		ArrayList<Student> updated = new ArrayList<Student>();
		while (studitr.hasNext()){
			cur  = (Student)studitr.next();
			
			ArrayList<Index> indices = cur.getIndices();
			Iterator<Index> itr = indices.iterator();
			int reg=0;
			while (itr.hasNext()) {
				Index nxt = itr.next(); 
				if (ind.getIndexId().equals(nxt.getIndexId())) {
					itr.remove();
					indices.add(ind);
					reg=1;
					cur.setIndices(indices);
					break;
				}
			}
			if(reg==0) {
				ArrayList<Index> wait = cur.getWaitIndices();
				Iterator<Index> waititr = wait.iterator();
				while (waititr.hasNext()) {
					Index nxt = waititr.next(); 
					if (ind.getIndexId().equals(nxt.getIndexId())) {
						waititr.remove();
						wait.add(ind);
						cur.setwaitIndices(wait);
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

	public static ArrayList<Object> getCourses() {
		ArrayList<Object> courses=binaryio.readSerializedObject("courses.dat");
		return courses;
	}
	
	public static Course getCourse(String courseId) {
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
	public static void addStudenttoCourse(Student name,Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getRegistered();
		update.put(name.getUsername(), ind.getIndexId());
		cour.setRegistered(update);
		updateCoursefile_course(cour);
	}
	
	public static void addStudenttoWaitlist(Student name, Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getWaitlist();
		update.put(name.getUsername(), ind.getIndexId());
		cour.setWaitlist(update);
		updateCoursefile_course(cour);
	}

	public static void courseRemoveStudent(Student name, Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getRegistered();
		update.remove(name.getUsername());
		cour.setRegistered(update);
		updateCoursefile_course(cour);
	}

	public static void removeCourseWait(Student wait, Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getWaitlist();
		update.remove(wait.getUsername());
		cour.setWaitlist(update);	
		updateCoursefile_course(cour);
	}
	
//--------------------Course/Index functions--------------
	public static String[] getRegisterIndex() {
		System.out.println("Choose a course to register: ");
		ArrayList<Object> courses=getCourses();
		int max=printAllCourses();
		System.out.println(max+1+") To go back.");
		Index ind=null;
		String[] course= {"",""};
		int choice = scanner.nextInt();
		
		if(choice-1<max) {
			System.out.println("Choose an Index to register: ");
			Course cour  = (Course)courses.get(choice-1);
			printIndices(cour,0); 
			System.out.println(cour.getIndices().size()+1+") To go back.");
			
			choice = scanner.nextInt();
			
			if(choice-1<cour.getIndices().size()) {
				ind = cour.getIndices().get(choice-1);
				course[0]= ind.getIndexId();
				course[1]=ind.getCourseId();
			}
			else
				getRegisterIndex();
		}
		else
			System.out.println("Back to Menu");
		return course;
	}

	public static int printAllCourses() {
		ArrayList<Object> courses=fileController.getCourses();
		
		System.out.println("   Course Code\t Course Name ");

		System.out.println("-------------------------------------");
		for (int i = 0 ; i <courses.size() ; i++) {
			Course cour  = (Course)courses.get(i);
			System.out.println(i+1+") "+cour.getCourseCode()+"\t    "+cour.getCourseName());
		}	
		
		return courses.size();
	}
	
	public static void printIndices(Course cour, int i) {
		if(cour==null) {
			ArrayList<Object> courses=fileController.getCourses();
			cour=(Course)courses.get(i);
		}
		System.out.println("   Course Code\tIndex ID\t Waitlist\t Vacancy \tSchedule ");
		System.out.println("---------------------------------------------------------------------------");
		ArrayList<Index> indices=cour.getIndices();
		for (int j = 0 ; j <indices.size() ; j++) {
			Index ind  = indices.get(j);
			System.out.println(j+1+") "+ind.getCourseId()+"\t    \t"+ind.getIndexId()+
					"\t    \t"+ind.getNoWaitlist()+"\t    \t"+ind.getVacancy()+
					"\t"+printSchedule(ind.getSchedule()));
		}	
	}
	
	public static String[] getChangeIndex(String courseId) {
		System.out.println("Choose Index to change: ");
		Course cour=getCourse(courseId);
		System.out.println("   Course Code\tIndex ID\t Waitlist\t Vacancy \tSchedule ");
		System.out.println("---------------------------------------------------------------------------");
		ArrayList<Index> indices=cour.getIndices();
		for (int j = 0 ; j <indices.size() ; j++) {
			Index ind  = indices.get(j);
			System.out.println(j+1+") "+ind.getCourseId()+"\t    \t"+ind.getIndexId()+
					"\t    \t"+ind.getNoWaitlist()+"\t    \t"+ind.getVacancy()+
					"\t"+printSchedule(ind.getSchedule()));
		}
		System.out.println(indices.size()+1+") To go back.");
		
		String[] result= {"",""};
		int choice = scanner.nextInt();
		
		if(choice-1<indices.size())  {
			Index ind=indices.get(choice-1);
			result[0]=ind.getIndexId();
			if(ind.getVacancy()>0) 
				result[1]="true";
		}
		return result;
	}

	public static int assignStudent(String username, String course[]) {
		Course cour = getCourse(course[1]);
		Index ind= findIndex(cour,course[0]);
		Student name=getStudent(username);
		
		Hashtable<String,String> reg=cour.getRegistered();
		Hashtable<String,String> wait=cour.getWaitlist();
		
		if((wait.get(username)!=null)
				||(reg.get(username)!=null)) {
			return -1;
		}

		//if no clash
		if(!clash(username,course)) {
			if (ind.getVacancy() > 0) {
				ArrayList<Student> update = ind.getRegistered();
				update.add(name);
				ind.setRegistered(update);
				ind.setVacancy(ind.getVacancy()-1);
				assignIndex(name,ind);
				addStudenttoCourse(name,ind);
				
				//save the change to file
				courseUpdate(ind);
				updateStudentIndex(ind);
				
				courseStudentUpdate(name);
				updateStudent(name);
				
				return 0;
			}
			else {
				ind.setNoWaitlist(ind.getNoWaitlist()+1);
				ArrayList<Student> update = ind.getWaitlist();
				update.add(name);
				ind.setWaitlist(update);
				addStudenttoWaitlist(name,ind);
				waitIndex(name,ind);

				//save the change to file
				courseUpdate(ind);
				updateStudentIndex(ind);
				
				courseStudentUpdate(name);
				updateStudent(name);
				
				return 1;
			}
		}
		//if clash
		return 2;
	
	}
	
	public static String[] getDropIndex(String user) {
		Student obj=getStudent(user);
		ArrayList<Index> indices= obj.getIndices();
		int printed = printStudentIndices(user,"reg");
		
		if(printed==-1) {
			System.out.println("You have not been registered to any courses yet!!!");
			return null;
		}
			
		System.out.println(indices.size()+1+") To go back.");
		Index ind=null;
		int choice = scanner.nextInt();
		String[] course= {"",""};
		if(choice-1<indices.size()) {
			ind  = indices.get(choice-1);
			course[0]= ind.getIndexId();
			course[1]= ind.getCourseId();
		}
		else
			System.out.println("Back to Menu");
		
		return course;
	
	}

	public static void unAssignStudent(String username, String course[], String swop) {
		Student name=getStudent(username);
		Course cour=getCourse(course[1]);
		Index ind=findIndex(cour,course[0]);
		
		ArrayList<Student> update = ind.getRegistered();
		Iterator<Student> itr = update.iterator();		
		while (itr.hasNext()) {
			Student nxt = itr.next(); 
			if (name.getUsername().equals(nxt.getUsername()))
				itr.remove();
			break;
		}
		ind.setRegistered(update);
		courseRemoveStudent(name,ind);
		unAssignIndex(name,ind);
		ind.setVacancy(ind.getVacancy()+1);
		if(ind.getNoWaitlist()>0 && swop.equals("drop")) {
			System.out.println("The index has been removed from your timetable");
			update = ind.getWaitlist();
			int success;
			do{
				Student wait=update.get(0);

				fileController.removewait(wait,ind);
				removeCourseWait(wait,ind);
				itr = update.iterator();		
				while (itr.hasNext()) {
					Student nxt = itr.next(); 
					if (wait.getUsername().equals(nxt.getUsername()))
						itr.remove();
					break;
				} 
				ind.setWaitlist(update);
				ind.setNoWaitlist(ind.getNoWaitlist()-1);
				
				String[] newCourse= {ind.getIndexId(),ind.getCourseId()};
				success=assignStudent(wait.getUsername(),newCourse);
				
				String[] message = {"",""};
				message[0] = wait.getEmail();
				if(success==-1) {
					message[1]="Sorry!! You have already registered to course: "  +ind.getCourseId() ;
				}
				else if(success==0) {
					message[1] = "You have been registered to course: " +ind.getCourseId()+ ", Index Id: "+ind.getIndexId();
				}
				else if(success==2) {
					message[1] = "Sorry!! There is a clash in your timetable for course: " 
							+ind.getCourseId()+ ", Index Id: "+ind.getIndexId()+"\n You have been removed from the waitlist";
				}
				NotificationController.sendEmail(message);

			}while(success!=0 && update.size()!=0);
		}
	
		//save the change to file
		courseUpdate(ind);
		updateStudentIndex(ind);
		
		courseStudentUpdate(name);
		updateStudent(name);
	}

	public static boolean clash(String name, String[] course) {
		Student stud = getStudent(name);
		Course cour = getCourse(course[1]);
		Index newInd = findIndex(cour,course[0]);
		
		ArrayList<Index> registered = stud.getIndices();
		ArrayList<Schedule> newclass = newInd.getSchedule();
		
		for(Index ind : registered) {
			ArrayList<Schedule> classes = ind.getSchedule();
				for(Schedule sch : classes) {
					LocalTime start = sch.getStartTime();
					LocalTime end = sch.getEndTime();
					
					for(Schedule newsch : newclass) {
						if( (sch.getDayofWeek() == newsch.getDayofWeek()) && 
								(getScheduleWeek(sch).equals(getScheduleWeek(sch))) ) {
							LocalTime newstart = newsch.getStartTime();
							LocalTime newend = newsch.getEndTime();
							
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
	
//-------------------Update Course file to save changes------------
	public static void courseUpdate(Index ind) {
		Course cur=null;
		ArrayList<Object> courseDetails = getCourses();
		Iterator<Object> courseitr = courseDetails.iterator();
		int coursepos=0,indpos=0;
		
		while (courseitr.hasNext()){
			cur  = (Course)courseitr.next();
			if(cur.getCourseCode().equals(ind.getCourseId())) {
				courseitr.remove();
				ArrayList<Index> indices = cur.getIndices();
				Iterator<Index> itr = indices.iterator();
				while (itr.hasNext()) {
					Index nxt = itr.next(); 
					if (ind.getIndexId().equals(nxt.getIndexId())) {
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
	
	public static void courseStudentUpdate(Student name) {
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
				
					if(indId == ind.getIndexId()) {
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
				
					if(indId == ind.getIndexId()) {
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
	
	public static void updateCoursefile_course(Course cour) {
			ArrayList<Object> courseDetails = getCourses();
			Iterator<Object> courseitr = courseDetails.iterator();
			int coursepos=0;
			
			while(courseitr.hasNext()){
				Course cur  = (Course)courseitr.next();
				if((cour.getCourseCode()).equals(cur.getCourseCode())) {
					courseitr.remove();
					ArrayList<Index> indices = cur.getIndices();
					
					Iterator<Index> indexitr = indices.iterator();
					int indpos=0;
					
					while(indexitr.hasNext()){
						Index curInd  = (Index)indexitr.next();
						indexitr.remove();
						curInd.setCourseId(cour.getCourseCode());
						updateStudentIndex(curInd);
						indices.add(indpos,curInd);
						indpos++;
					}
					courseDetails.add(coursepos,(Object)cour);
					break;
				}
				coursepos++;
			}
			binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
	}

//====================================================================================================================
//-----------------Index info-----------------------------------------
	public static Index findIndex(Course cur, String indId) {
		ArrayList<Index> indices = cur.getIndices();
		for(Index nxt : indices)
			if(nxt.getIndexId().equals(indId))
				return nxt;
		
		return null;
	}
	
	public static String getIndexReg_Course(String name, String courseId) {
		Course cour = getCourse(courseId);
		Hashtable <String,String> reg = cour.getRegistered();
		return reg.get(name);
	}

//------------------Schedule info------------------------------------------
	public static String getScheduleWeek(Schedule sch) {
		if(sch.getisEvenWeek() && sch.getisOddWeek())
			return "both";
		else if (sch.getisEvenWeek())
			return "even";
		else if (sch.getisOddWeek())
			return "odd";
		
		return "both";
	}

//-----------------Schedule Function--------------------------------------
	public static String printSchedule(ArrayList<Schedule> classes) {
		String daysWeek[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		String details="";
		for (Schedule sch : classes) {
			String week = getScheduleWeek(sch)!="both" ? "("+getScheduleWeek(sch)+" week)" : " ";
			if(details.equals(""))
				details = sch.getType()+": "+sch.getStartTime()+"-"+sch.getEndTime()+","+ daysWeek[sch.getDayofWeek()] +week ;
			else
				details = details +"|\t"+ sch.getType()+": "+sch.getStartTime()+"-"+sch.getEndTime()+","+ daysWeek[sch.getDayofWeek()] +week ;
		}
				
		return details;
	}

//---------------------Admin Function-------------------------------------------------------
	public static boolean makeStudent(String name, String userName, String password, String studentID, 
			LocalDate sdate,LocalDate edate, String Email ) {
		
		String hashpassword = hash(password);
		ArrayList <Object> check = getStudents();
		
		for (int j = 0; j<check.size(); j++)
		{
			Student stud = (Student) check.get(j);
			if (studentID.equals(stud.getStudentId()) || userName.equals(stud.getUsername()))
				return false;
		}
		
		User Stud = new Student(name, userName, hashpassword, studentID,sdate,edate, Email);
		
		binaryio.writeSerializedObject("students.dat", Stud);
		binaryio.writeSerializedObject("users.dat", (User) Stud);
		
		return true;
	}

	public static boolean makeCourse(String code, String name) {
		
		
		ArrayList <Object> check = getCourses();
		
		for (int j = 0; j<check.size(); j++)
		{
			Course cour = (Course) check.get(j);
			if (code.equals(cour.getCourseCode()))
				return false;
		}
		
		System.out.printf("Enter type for course %s (lec/lec&tut/lec,tut&lab):",code);
		String type = scanner.next();
		
		System.out.print("Enter day number of week for lecture of " + code+": ");
		int lecday = scanner.nextInt();
		
		System.out.printf("Enter start time for lecture (HH:MM 24Hrs):");		
		LocalTime lecst = LocalTime.parse(scanner.next());
		
		System.out.printf("Enter end time for lecture (HH:MM 24Hrs):");		
		LocalTime lecet = LocalTime.parse(scanner.next());
		
		ArrayList<Index> indices = makeIndex(code,type,lecday,lecst,lecet);
		
		Course newcourse = new Course (code, name, indices);
		binaryio.writeSerializedObject("courses.dat", newcourse);
		return true;
	}
	
	public static ArrayList<Index> makeIndex(String code, String type, int lecday, LocalTime lecst, LocalTime lecet) {
		
		ArrayList<Index> indices = new ArrayList<Index>();
		if(!type.equals("lec")) {
			System.out.printf("Enter number of indices for %s :",code);
			int num = scanner.nextInt();
			
			for(int i=1;i<=num;i++){
				System.out.printf("Enter Index id for index %d",i);		
				String index_id = scanner.next();
									
				System.out.printf("Enter number of vacancies for index %d",i);		
				int slot = scanner.nextInt();
				
				ArrayList<Schedule> sch= makeSchedule(index_id,type,lecday,lecst,lecet);	
				indices.add(new Index(code,index_id,type, slot, sch));
			}
			
		}
		else {
			
			System.out.printf("Enter number of vacancies for the lecture");		
			int slot = scanner.nextInt();
			ArrayList<Schedule> sch= makeSchedule(code+"_01",type,lecday,lecst,lecet);
			indices.add(new Index(code,code+"_01",type,slot,sch));
		
		}
		return indices;
	}

	public static ArrayList<Schedule> makeSchedule(String IndID,String type,int lecday, LocalTime lecst, LocalTime lecet) {
		
		final String[] classtype = {"Lecture","Tutorial","Lab"};
		ArrayList<Schedule> sch = new ArrayList<Schedule>();
		for(int j=0;j<3;j++) {
			boolean e=true,o=true;
			int day;
			LocalTime st,et;
			
			if(j==0) {
				day=lecday;
				st=lecst;
				et=lecet;
			}
			
			else {
				System.out.print("Enter day number of week for "+ classtype[j]+" of " + IndID+": ");
				day = scanner.nextInt();
				
				System.out.printf("Enter start time for %s (HH:MM 24Hrs):",classtype[j]);		
				st = LocalTime.parse(scanner.next());
				
				System.out.printf("Enter end time for %s (HH:MM 24Hrs):",classtype[j]);		
				et = LocalTime.parse(scanner.next());
				
				if(j>0) {
					System.out.printf("Enter week for %s (even/odd/both):",classtype[j]);		
					String evenodd = scanner.next();
					if(evenodd.toLowerCase().equals("even"))
						o=false;
					if(evenodd.toLowerCase().equals("odd"))
						e=false;
				}
			}
			sch.add(new Schedule(classtype[j],day,st,et,e,o));
			if((type.equals("lec") && j==0) || (type.equals("lec&tut") && j==1))
				break;
		}
		return sch;
	}

	public static int showIndexStudents(){
		
		ArrayList <Object> courseList = getCourses();
		
		System.out.print("Select Course: ");
		printAllCourses();
		System.out.println(courseList.size()+1+") To go back.");
		int choice = scanner.nextInt();
		
		if(choice>courseList.size())
			return 0;
		
		System.out.print("Select Index: ");
		Course courseSelected = (Course) courseList.get(choice-1);
		ArrayList <Index> indices = courseSelected.getIndices();
		
		printIndices(courseSelected, 0);
		System.out.println(indices.size()+1+") To go back.");
		int indexSelected = scanner.nextInt();
		
		if(indexSelected>indices.size())
			showIndexStudents();
		
		else {
			Index chosenIndex = indices.get(indexSelected-1);
			ArrayList <Student> studentInIndex = chosenIndex.getRegistered();
			
			if(studentInIndex.size()==0)
				return -1;
			
			int i = 1;
			System.out.println("No \t Name\n");
			for (Student student : studentInIndex)
			{
				System.out.println(i + "\t" + student.getName());
				i++;
			}
		}
		return 0;
	}
	
	public static int showCourseStudents(){
		
		ArrayList <Object> courseList = getCourses();
		
		System.out.print("Select Course: ");
		printAllCourses();
		System.out.println(courseList.size()+1+") To go back.");
		int choice = scanner.nextInt();
		
		if(choice>courseList.size())
			return 0;
		
		Course courseSelected = (Course) courseList.get(choice-1);
		
		Hashtable<String,String> registered = courseSelected.getRegistered();
		if(registered.size()==0)
			return -1;
		
		System.out.println("Student Name \t Registered Index");
		System.out.println("--------------------------------------------------");
		Set<String> students = registered.keySet();
		for(String stud : students){
			System.out.println(stud+"\t"+registered.get(stud));
		}
		return 0;
	}
	
}