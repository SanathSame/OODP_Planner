package P1;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Implementation of Schedule class representing the lessons of each Index
 * Implements Serializable interface to enable serialized storing of the class objects.
 *
 */
public class Schedule implements Serializable{
	/**
	 * Type of lesson : Could be lecture/tutorial/lab
	 */
	private String type;
	/**
	 * The day of the week which the lesson falls on
	 */
	private int DayofWeek;
	/**
	 * Starting time of the lesson
	 */
	private LocalTime start;
	/**
	 * Ending time of the lesson
	 */
	private LocalTime end;
	/**
	 * Week the lesson is on : boolean value, true representing even and odd representing odd
	 */
	private boolean even;
	/**
	 * Week the lesson is on : boolean value, true representing odd and false representing even
	 */
	private boolean odd;
	/**
	 * Venue of this lesson
	 */
	private String venue;
	
	/**
	 * Default constructor class of this Schedule
	 */
	public Schedule() {
		type="lec";
		DayofWeek=0;
		start=null;
		end = null;
		even = true;
		odd = true;
		venue = null;
	}

	/**
	 * Parameterized constructor to specify the type, day, start time, end time,
	 * odd/even week and venue of the schedule.
	 * 
	 * @param typ : Type of this lesson, could be lecture/tutorial/lab or a combination.
	 * @param day : Day of the week of this lesson
	 * @param st : Starting time of this lesson
	 * @param et : Ending time of this lesson
	 * @param e : boolean value, true representing schedule falls on even week and false represents odd week
	 * @param o : boolean value, true representing schedule falls on odd week and false represents even week
	 * @param venue : venue of this schedule
	 */
	public Schedule(String typ, int day, LocalTime st, LocalTime et, boolean e, boolean o, String venue) {
		type = typ;
		DayofWeek = day;
		start = st;
		end = et;
		even = e;
		odd = o;
		this.venue=venue;
	}

	/**
	 * Method returns the type of this schedule
	 * @return : This schedule lesson type, could be lecture/tutorial/lab or a combination
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method changes the type of this schedule
	 * @param type : This schedule lesson type, could be lecture/tutorial/lab or a combination
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Method returns day of the week of this schedule
	 * @return : Day of the week of this schedule
	 */
	public int getDayofWeek() {
		return DayofWeek;
	}

	/**
	 * Method changes day of the week of this schedule
	 * @param dayofWeek : New day of the week of this schedule
	 */
	public void setDayofWeek(int dayofWeek) {
		DayofWeek = dayofWeek;
	}

	/**
	 * Method returns the starting time of this schedule
	 * @return : This schedule's starting time
	 */
	public LocalTime getStart() {
		return start;
	}

	/** 
	 * Method changes the starting time of this schedule
	 * @param start : New starting time of this schedule
	 */
	public void setStart(LocalTime start) {
		this.start = start;
	}

	/**
	 * Method returns the ending time of this schedule
	 * @return : This schedule's ending time
	 */
	public LocalTime getEnd() {
		return end;
	}

	/** 
	 * Method changes the ending time of this schedule
	 * @param end : New ending time of this schedule
	 */
	public void setEnd(LocalTime end) {
		this.end = end;
	}

	/**
	 * Method returns a boolean value where true represents even week schedule and
	 * false represents odd week schedule
	 * @return : boolean value of this lesson's schedule
	 */
	public boolean isEven() {
		return even;
	}
	
	/**
	 * Method changes the week of this schedule (odd/even)
	 * @param even : Boolean value where true represents even week and false represents odd week
	 */
	public void setEven(boolean even) {
		this.even = even;
	}

	/**
	 * Method returns a boolean value where true represents odd week schedule and
	 * false represents even week schedule
	 * @return : boolean value of this lesson's schedule
	 */
	public boolean isOdd() {
		return odd;
	}

	/**
	 * Method changes the week of this schedule (odd/even)
	 * @param odd : Boolean value where true represents even odd and false represents even week
	 */
	public void setOdd(boolean odd) {
		this.odd = odd;
	}

	/**
	 * Method returns the venue of this schedule
	 * @return : This schedule's venue
	 */
	public String getVenue() {
		return venue;
	}

	/**
	 * Method changes the venue of this schedule
	 * @param venue : the new venue of this schedule
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
}
