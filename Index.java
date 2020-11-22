package P1;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Index implements Serializable {
	String course_id;
	String index_id;
	String type;
	ArrayList <Schedule> timings=new ArrayList<Schedule>();
	int Vacancies;
	ArrayList <Student> registered;
	int no_waitlist;
	ArrayList <Student> Waitlist;

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
		timings=sch;
	}
	
	public String getCourseId() {
		return course_id;
	}
	
	public void setCourseId(String cId) {
		course_id=cId;
	}
	
	public String getIndexId() {
		return index_id;
	}
	
	public void setIndexId(String iId) {
		index_id=iId;
	}
	
	public ArrayList<Schedule> getSchedule() {
		return timings;
	}
	
	public void setSchdule(ArrayList<Schedule> sch) {
		timings=sch;
	}
	
	public int getVacancy() {
		return Vacancies;
	}
	
	public void setVacancy(int slots) {
		Vacancies =slots;
	}
	
	public int getNoWaitlist(){
		return no_waitlist;
	}
	
	public void setNoWaitlist(int wait){
		no_waitlist=wait;
	}
	
	public ArrayList<Student> getRegistered() {
		return registered;
	}
			
	public void setRegistered(ArrayList<Student> reg) {
		registered=reg;
	}

	public ArrayList<Student> getWaitlist() {
		return Waitlist;
	}
	
	public void setWaitlist(ArrayList<Student> wait) {
		Waitlist=wait;
	}
}
