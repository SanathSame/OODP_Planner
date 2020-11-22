package P1;

import java.io.Serializable;
import java.time.LocalTime;

public class Schedule implements Serializable{
	String type;
	int DayofWeek;
	LocalTime start;
	LocalTime end;
	boolean even;
	boolean odd;
	
	public Schedule() {
		type="lec";
		DayofWeek=0;
		start=null;
		end = null;
		even = true;
		odd = true;
	}

	public Schedule(String typ, int day, LocalTime st, LocalTime et, boolean e, boolean o) {
		type = typ;
		DayofWeek = day;
		start = st;
		end = et;
		even = e;
		odd = o;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String typ) {
		type = typ;
	}
	
	public int getDayofWeek() {
		return DayofWeek;
	}
	
	public void setDayofWeek(int date) {
		DayofWeek = date;
	}
	
	public LocalTime getStartTime() {
		return start;
	}

	public void setStartTime(LocalTime STime) {
		start = STime;
	}
	
	public LocalTime getEndTime() {
		return end;
	}

	public void setEndTime(LocalTime ETime) {
		end = ETime;
	}
	
	public boolean getisEvenWeek() {
		return even;
	}

	public void setEven(boolean Evenweek) {
		even = Evenweek;
	}
	
	public boolean getisOddWeek() {
		return odd;
	}

	public void setOdd(boolean Oddweek) {
		odd = Oddweek;
	}
	
}
