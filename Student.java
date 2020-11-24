package P1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends User{

	static final int maxAU=22;
	
	private String student_id;
	private LocalDate accessPeriod_Start;
	private LocalDate accessPeriod_End;
	private ArrayList<Index> registered_index;
	private ArrayList<Index> waitlisted_index;
	private String nationality;
	private String gender;
	private String email;
	private int registeredAU;
	
	
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

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	public LocalDate getAccessPeriod_Start() {
		return accessPeriod_Start;
	}

	public void setAccessPeriod_Start(LocalDate accessPeriod_Start) {
		this.accessPeriod_Start = accessPeriod_Start;
	}

	public LocalDate getAccessPeriod_End() {
		return accessPeriod_End;
	}

	public void setAccessPeriod_End(LocalDate accessPeriod_End) {
		this.accessPeriod_End = accessPeriod_End;
	}

	public ArrayList<Index> getRegistered_index() {
		return registered_index;
	}

	public void setRegistered_index(ArrayList<Index> registered_index) {
		this.registered_index = registered_index;
	}

	public ArrayList<Index> getWaitlisted_index() {
		return waitlisted_index;
	}

	public void setWaitlisted_index(ArrayList<Index> waitlisted_index) {
		this.waitlisted_index = waitlisted_index;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static int getMaxau() {
		return maxAU;
	}

	public int getRegisteredAU() {
		return registeredAU;
	}

	public void setRegisteredAU(int registeredAU) {
		this.registeredAU = registeredAU;
	}
	
}
