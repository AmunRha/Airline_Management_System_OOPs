package paymentdetails_pkg;

public class Booking_DetailsDB {
	private int TicketNo;
	public int getTicketNo() {
		return TicketNo;
	}
	public void setTicketNo(int ticketNo) {
		TicketNo = ticketNo;
	}
	public int getAirlineNo() {
		return AirlineNo;
	}
	public void setAirlineNo(int airlineNo) {
		AirlineNo = airlineNo;
	}
	public int getAirportNo() {
		return AirportNo;
	}
	public void setAirportNo(int airportNo) {
		AirportNo = airportNo;
	}
	public String getPassportNo() {
		return PassportNo;
	}
	public void setPassportNo(String passportNo) {
		PassportNo = passportNo;
	}
	
	public Booking_DetailsDB() {
		TicketNo = -1;
		AirlineNo = -1;
		AirportNo = -1;
		PassportNo = null;
		ScheduleID = -1;
		Price = -1;
	}
	
	public Booking_DetailsDB(int ticketNo, int airlineNo, int airportNo, String passportNo, int scheduleID,
			float price) {
		TicketNo = ticketNo;
		AirlineNo = airlineNo;
		AirportNo = airportNo;
		PassportNo = passportNo;
		ScheduleID = scheduleID;
		Price = price;
	}
	public int getScheduleID() {
		return ScheduleID;
	}
	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	private int AirlineNo;
	private int AirportNo;
	private String PassportNo;
	private int ScheduleID;
	private float Price;

}
