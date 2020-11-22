package P1;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Course implements Serializable{
	//
	String courseCode;
	String courseName;
	ArrayList <Index> indices=new ArrayList<Index>();
	String type;
	Hashtable<String, String> registered;
	Hashtable<String, String> Waitlist;

	public Course() {
		courseName=null;
		courseCode= null;
		indices=null;
		type=null;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
	}
	
	public Course(String id,String name,ArrayList<Index> indices) {
		courseName=name;
		courseCode= id;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
		this.indices=indices;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String cname) {
		courseName=cname;
	}
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String ccode) {
		courseCode=ccode;
	}
	
	public ArrayList<Index> getIndices() {
		return indices;
	}
	
	public void setIndices(ArrayList<Index> update) {
		indices=update;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String typ) {
		type=typ;
	}
	
	public Hashtable<String, String> getRegistered() {
		return registered;
	}

	public void setRegistered(Hashtable<String, String> update) {
		registered=update;
	}

	public Hashtable<String, String> getWaitlist() {
		return Waitlist;
	}

	public void setWaitlist(Hashtable<String, String> update) {
		Waitlist=update;
	}
}
