package P1;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of Index class containing the details of the index of the course
 * Implements Serializable interface to enable serialized storing of the class objects.
 *
 */
public class Index implements Serializable {
	/**
	 * Unique course ID which the index is under
	 */
	private String course_id;
	/**
	 * Unique ID of this index
	 */
	private String index_id;
	/**
	 * Type of lesson : Could be lecture/tutorial/lab
	 */
	private String type;
	/**
	 * List of schedules under this index
	 */
	private ArrayList <Schedule> timings=new ArrayList<Schedule>();
	/**
	 * Number of available vacancies for this index
	 */
	private int Vacancies;
	/**
	 * List of students that registered for this index
	 */
	private ArrayList <Student> registered;
	/**
	 * Number of students that have added this index into waitlist
	 */
	private int no_waitlist;
	/**
	 * List of students that have added this index into waitlist
	 */
	private ArrayList <Student> Waitlist;

	/**
	 * Default constructor for this Index object
	 */
	public Index() {
		course_id=null;
		index_id=null;
		type=null;
		timings=null;
		Vacancies=0;
		no_waitlist=0;
		registered=new ArrayList<Student>();
		Waitlist=new ArrayList<Student>();
	}
	
	/**
	 * Parameterized constructor to specify the course ID, index ID, type, number of vacancies
	 * , number of students waitlisted, list of students registered and list of students 
	 * waitlisted under this index
	 * 
	 * @param coursecode : Course ID which this index belonds to
	 * @param id : Unique ID of this index
	 * @param typ : Type of lesson for this index
	 * @param slot : Number of vacancies for this index
	 * @param sch : Array list of the schedule for this index
	 */
	public Index(String coursecode,String id,String typ,int slot, ArrayList<Schedule> sch) {
		course_id=coursecode;
		index_id=id;
		type=typ;
		Vacancies=slot;
		no_waitlist=0;
		registered=new ArrayList<Student>();
		Waitlist=new ArrayList<Student>();
		
		for(Schedule lesson : sch) {
			Schedule newLesson=new Schedule();
			newLesson=lesson;
			this.timings.add(newLesson);
		}
		
	}

	/**
	 * Method returns the course ID which this index belongs to
	 * @return : This index's course ID
	 */
	public String getCourse_id() {
		return course_id;
	}

	/** 
	 * Method changes the course ID which this index belongs to 
	 * @param course_id : new course ID of this index
	 */
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	/**
	 * Method returns the ID of this index
	 * @return : This index's unique ID
	 */
	public String getIndex_id() {
		return index_id;
	}

	/** 
	 * Method to change this index's ID
	 * @param index_id : New ID of this index
	 */
	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}

	/**
	 * Method to get the type of this index
	 * @return : This index's type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method to change the type of this index
	 * @param type : new type of this index
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Method returns this index's schedule timings
	 * @return : List of timings for this index's schedule
	 */
	public ArrayList<Schedule> getTimings() {
		return timings;
	}

	/**
	 * Method changes this index's list of schedule timings
	 * @param timings : new list of timings for this index's schedule
	 */
	public void setTimings(ArrayList<Schedule> timings) {
		this.timings = timings;
	}

	/**
	 * Method returns the number of vacancies of this index
	 * @return : this index's number of vacancies
	 */
	public int getVacancies() {
		return Vacancies;
	}

	/**
	 * Method changes this index's number of vacancies
	 * @param vacancies : This index's new number of vacancies
	 */
	public void setVacancies(int vacancies) {
		Vacancies = vacancies;
	}

	/**
	 * Method gets the list of students registered under this index
	 * @return : List of students registered for this index
	 */
	public ArrayList<Student> getRegistered() {
		return registered;
	}

	/**
	 * Method changes the list of students registered for this index
	 * @param registered : new list of students registered for this index
	 */
	public void setRegistered(ArrayList<Student> registered) {
		this.registered = registered;
	}

	/**
	 * Method returns the number of students waitlisted under this index
	 * @return nunber of students waitlisted under this index
	 */
	public int getNo_waitlist() {
		return no_waitlist;
	}

	/**
	 * Method changes the number of students waitlisted for this index
	 * @param no_waitlist : new number of students waitlisted for this index
	 */
	public void setNo_waitlist(int no_waitlist) {
		this.no_waitlist = no_waitlist;
	}

	/**
	 * Method gets the list of students waitlisted under this index
	 * @return : List of students waitlisted for this index
	 */
	public ArrayList<Student> getWaitlist() {
		return Waitlist;
	}

	/**
	 * Method changes the list of students waitlisted for this index
	 * @param waitlist : new list of students waitlisted for this index
	 */
	public void setWaitlist(ArrayList<Student> waitlist) {
		Waitlist = waitlist;
	}

	
}
