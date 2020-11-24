package P1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Course implements Serializable{
	//
	private String courseCode;
	private String courseName;
	private ArrayList <Index> indices=new ArrayList<Index>();
	private String type;
	private Hashtable<String, String> registered;
	private Hashtable<String, String> Waitlist;
	private int au;

	public Course() {
		courseName=null;
		courseCode= null;
		indices=null;
		type=null;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
		au=0;
	}
	
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

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public ArrayList<Index> getIndices() {
		return indices;
	}

	public void setIndices(ArrayList<Index> indices) {
		this.indices = indices;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Hashtable<String, String> getRegistered() {
		return registered;
	}

	public void setRegistered(Hashtable<String, String> registered) {
		this.registered = registered;
	}

	public Hashtable<String, String> getWaitlist() {
		return Waitlist;
	}

	public void setWaitlist(Hashtable<String, String> waitlist) {
		Waitlist = waitlist;
	}

	public int getAu() {
		return au;
	}

	public void setAu(int au) {
		this.au = au;
	}
	
}
