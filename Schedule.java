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
	String venue;
	
	public Schedule() {
		type="lec";
		DayofWeek=0;
		start=null;
		end = null;
		even = true;
		odd = true;
		venue = null;
	}

	public Schedule(String typ, int day, LocalTime st, LocalTime et, boolean e, boolean o, String venue) {
		type = typ;
		DayofWeek = day;
		start = st;
		end = et;
		even = e;
		odd = o;
		this.venue=venue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDayofWeek() {
		return DayofWeek;
	}

	public void setDayofWeek(int dayofWeek) {
		DayofWeek = dayofWeek;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public boolean isEven() {
		return even;
	}

	public void setEven(boolean even) {
		this.even = even;
	}

	public boolean isOdd() {
		return odd;
	}

	public void setOdd(boolean odd) {
		this.odd = odd;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
}
