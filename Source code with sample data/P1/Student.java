package P1;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Student entity subclass which extends the User parent class.
 */
public class Student extends User{

	/**
	 * Maximum number of AUs student can take, set as a constant static int of 22.
	 */
	static final int maxAU=22;
	/**
	 * Private unique ID used to identify each student
	 */
	private String student_id;
	/**
	 * Starting date where students can access the starPlanner system
	 */
	private LocalDate accessPeriod_Start;
	/**
	 * Ending date where students can access the starPlanner system
	 */
	private LocalDate accessPeriod_End;
	/**
	 * List of indexes registered by this student
	 */
	private ArrayList<Index> registered_index;
	/**
	 * List of indexes waitlisted by this student
	 */
	private ArrayList<Index> waitlisted_index;
	/**
	 * Nationality of this student
	 */
	private String nationality;
	/**
	 * Gender of this student
	 */
	private String gender;
	/**
	 * Email of this student
	 */
	private String email;
	/**
	 * Number of AUs registered by this student
	 */
	private int registeredAU;
	
	/**
	 * Default constructor class used to create a Student object.
	 */
	public Student() {
		super(null,null,null,false);
		student_id = null;
		accessPeriod_Start=null;
		accessPeriod_End=null;
		registered_index= new ArrayList<Index>();
		waitlisted_index= new ArrayList<Index>();
		nationality=null;
		gender=null;
		email = null;
		registeredAU=0;
	}
	
	/**
	 * Parameterized constructor to specify the name, username, password, start and end accessperiod,
	 * registered indexes, waitlisted indexes, nationality, gender, email and registered AU of this student
	 * 
	 * @param name : this student's name
	 * @param un : this student's login username
	 * @param pw : this student's login password
	 * @param id : this student's unique ID
	 * @param ld : starting date of this student's access period
	 * @param ld1 : ending date of this student's access period
	 * @param Email : this student's email
	 * @param nation : this student's nationality
	 * @param gen : this student's gender
	 */
	public Student(String name,String un,String pw, String id, LocalDate ld, LocalDate ld1,
			String Email,String nation,String gen) {
		super(name,un,pw,false);
		student_id = id;
		accessPeriod_Start=ld;
		accessPeriod_End=ld1;
		registered_index= new ArrayList<Index>();
		waitlisted_index= new ArrayList<Index>();
		nationality = nation;
		gender=gen;
		email=Email;
		registeredAU=0;
	}
	
	/**
	 * Method returns this student's ID
	 * @return this student's ID
	 */
	public String getStudent_id() {
		return student_id;
	}
	
	/**
	 * Method to change this student's ID
	 * @param student_id : specified student ID to set for this student
	 */
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	/**
	 * Method returns starting date of this student's access period to starPlanner system
	 * @return date of the starting period of this student's access period
	 */
	public LocalDate getAccessPeriod_Start() {
		return accessPeriod_Start;
	}

	/**
	 * Method to change  this student's starting access period 
	 * @param accessPeriod_Start :new starting date of this student's access period
	 */
	public void setAccessPeriod_Start(LocalDate accessPeriod_Start) {
		this.accessPeriod_Start = accessPeriod_Start;
	}

	/**
	 * Method returns ending date of this student's access period to starPlanner system
	 * @return date of the ending period of this student's access period
	 */
	public LocalDate getAccessPeriod_End() {
		return accessPeriod_End;
	}

	/**
	 * Method to change this student's ending access period 
	 * @param accessPeriod_End :new Ending date of this student's access period
	 */
	public void setAccessPeriod_End(LocalDate accessPeriod_End) {
		this.accessPeriod_End = accessPeriod_End;
	}

	/** 
	 * Method returns this student's list of registered indexes
	 * @return list of this student's registered indexes
	 */
	
	public ArrayList<Index> getRegistered_index() {
		return registered_index;
	}

	/**
	 * Method to edit this student's list of registered indexes
	 * @param registered_index : new specified list of student's registered indexes
	 */
	public void setRegistered_index(ArrayList<Index> registered_index) {
		this.registered_index = registered_index;
	}

	/** 
	 * Method returns this student's list of waitlisted indexes
	 * @return list of this student's waitlisted indexes
	 */
	public ArrayList<Index> getWaitlisted_index() {
		return waitlisted_index;
	}

	/**
	 * Method to edit this student's list of waitlisted indexes
	 * @param waitlisted_index : new specified list of student's waitlisted indexes
	 */
	public void setWaitlisted_index(ArrayList<Index> waitlisted_index) {
		this.waitlisted_index = waitlisted_index;
	}

	/**
	 * Method returns this student's nationality
	 * @return This student's nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Method to change this student's nationality
	 * @param nationality : new nationality of this student
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Method to get this student's gender
	 * @return this student's gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Method to set this student's gender 
	 * @param gender : specified gender of this student
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Method returns this student's email
	 * @return the email of this student
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Method to change this student's email
	 * @param email : This student's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method returns maximum AU this student can register
	 * @return Maximum number of AU for this student
	 */
	public static int getMaxau() {
		return maxAU;
	}

	/** 
	 * Method returns this student's current number of registered AU
	 * @return this student's current registered AUs
	 */
	public int getRegisteredAU() {
		return registeredAU;
	}

	/**
	 * Method changes this student's number of current registered AUs
	 * @param registeredAU : new number of registered AUs of this student
	 */
	public void setRegisteredAU(int registeredAU) {
		this.registeredAU = registeredAU;
	}
	
}
