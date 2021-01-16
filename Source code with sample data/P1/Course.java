package P1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Implementation of the course class
 * Implements Serializable interface to enable serialized storing of the class objects.
 *
 */
public class Course implements Serializable{

	/**
	 * Unique code of this course
	 */
	private String courseCode;
	/**
	 * Name of this course
	 */
	private String courseName;
	/**
	 * List of indices under this course
	 */
	private ArrayList <Index> indices=new ArrayList<Index>();
	/**
	 * Type of this course
	 */
	private String type;
	/** 
	 * List of students registered under this course
	 */
	private Hashtable<String, String> registered;
	/** 
	 * List of students waitlisted under this course
	 */
	private Hashtable<String, String> Waitlist;
	/**
	 * Number of AUs of this course
	 */
	private int au;

	/**
	 * Default constructor to create a course object
	 */
	public Course() {
		courseName=null;
		courseCode= null;
		indices=null;
		type=null;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
		au=0;
	}
	
	/** 
	 * Parameterized constructor to specify the name, ID, indices and number of AUs of this course
	 * @param id : this course's unique ID
	 * @param name : the name of this course 
	 * @param indices : indicies under this course
	 * @param au : Number of AUs of this course
	 */
	public Course(String id,String name,ArrayList<Index> indices, int au) {
		courseName=name;
		courseCode= id;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
		
		for(Index index : indices) {
			Index ind=new Index();
			ind=index;
			this.indices.add(ind);
		}
		
		this.au=au;
	}

	/**
	 * Method returns this course's unique ID
	 * @return this course's ID
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/** 
	 * Method to change this course's ID
	 * @param courseCode : new ID of this course
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Method returns this course's name
	 * @return this course's name
	 */
	public String getCourseName() {
		return courseName;
	}

	/** 
	 * Method to change this course's name
	 * @param courseName : new name of this course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Method returns the indices under this course
	 * @return this course's list of indices
	 */
	public ArrayList<Index> getIndices() {
		return indices;
	}

	/**
	 * Method changes the list of indices under this course
	 * @param indices : new list of indices under this course
	 */
	public void setIndices(ArrayList<Index> indices) {
		this.indices = indices;
	}

	/**
	 * Method returns the type of this course, can be lecture/tut/lab or a combination
	 * @return this course's type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method changes the type of this course
	 * @param type: new course type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Method returns students registered under this course
	 * @return : students registered under this course
	 */
	public Hashtable<String, String> getRegistered() {
		return registered;
	}

	/** 
	 * Method changes the students registered under this course
	 * @param registered : new list of students registered under this course
	 */
	public void setRegistered(Hashtable<String, String> registered) {
		this.registered = registered;
	}

	/**
	 * Method returns students waitlisted under this course
	 * @return : students waitlisted under this course
	 */
	public Hashtable<String, String> getWaitlist() {
		return Waitlist;
	}

	/** 
	 * Method changes the students waitlisted under this course
	 * @param waitlist : new list of students waitlisted under this course
	 */
	public void setWaitlist(Hashtable<String, String> waitlist) {
		Waitlist = waitlist;
	}

	/**
	 * Method returns the number of AUs of this course
	 * @return : this course's number of AUs
	 */
	public int getAu() {
		return au;
	}

	/** 
	 * Method changes the number of AUs of this course
	 * @param au : this course's new number of AUs
	 */
	public void setAu(int au) {
		this.au = au;
	}
	
}
