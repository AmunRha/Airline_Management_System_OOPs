package enquirydetails_pkg;

import java.util.Date;

public class Schedule_DetailsDB {
	private	int ScheduleID, AirlineNo, AirportID;
	private Date Dateofdeparture;
	
	public Schedule_DetailsDB() {
		ScheduleID = -1;
		AirlineNo = -1;
		AirportID = -1;
		Dateofdeparture = null;
		Timeofdeparture = null;
		Destination = null;
	}
	
	public Schedule_DetailsDB(int scheduleID, int airlineNo, int airportID, Date dateofdeparture,
			String timeofdeparture, String destination) {
		ScheduleID = scheduleID;
		AirlineNo = airlineNo;
		AirportID = airportID;
		Dateofdeparture = dateofdeparture;
		Timeofdeparture = timeofdeparture;
		Destination = destination;
	}
	public int getScheduleID() {
		return ScheduleID;
	}
	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}
	public int getAirlineNo() {
		return AirlineNo;
	}
	public void setAirlineNo(int airlineNo) {
		AirlineNo = airlineNo;
	}
	public int getAirportID() {
		return AirportID;
	}
	public void setAirportID(int airportID) {
		AirportID = airportID;
	}
	public String getDateofdeparture() {
		return Dateofdeparture.toString();
	}
	public void setDateofdeparture(Date dateofdeparture) {
		Dateofdeparture = dateofdeparture;
	}
	public String getTimeofdeparture() {
		return Timeofdeparture;
	}
	public void setTimeofdeparture(String timeofdeparture) {
		Timeofdeparture = timeofdeparture;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	private	String Timeofdeparture;
	private	String Destination;

}
