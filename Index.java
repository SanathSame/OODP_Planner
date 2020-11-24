package P1;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Index implements Serializable {
	private String course_id;
	private String index_id;
	private String type;
	private ArrayList <Schedule> timings=new ArrayList<Schedule>();
	private int Vacancies;
	private ArrayList <Student> registered;
	private int no_waitlist;
	private ArrayList <Student> Waitlist;

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

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getIndex_id() {
		return index_id;
	}

	public void setIndex_id(String index_id) {
		this.index_id = index_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Schedule> getTimings() {
		return timings;
	}

	public void setTimings(ArrayList<Schedule> timings) {
		this.timings = timings;
	}

	public int getVacancies() {
		return Vacancies;
	}

	public void setVacancies(int vacancies) {
		Vacancies = vacancies;
	}

	public ArrayList<Student> getRegistered() {
		return registered;
	}

	public void setRegistered(ArrayList<Student> registered) {
		this.registered = registered;
	}

	public int getNo_waitlist() {
		return no_waitlist;
	}

	public void setNo_waitlist(int no_waitlist) {
		this.no_waitlist = no_waitlist;
	}

	public ArrayList<Student> getWaitlist() {
		return Waitlist;
	}

	public void setWaitlist(ArrayList<Student> waitlist) {
		Waitlist = waitlist;
	}

	
}
