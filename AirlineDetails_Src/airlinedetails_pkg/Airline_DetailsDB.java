package airlinedetails_pkg;

public class Airline_DetailsDB {
	private int AirlineNo, SeatingCapacity;
	private String AirlineName, AirlineType;
	private double Price;
	
	public Airline_DetailsDB() {
		AirlineNo = 0;
		SeatingCapacity = 0;
		AirlineName = null;
		AirlineType = null;
		Price = 0;
	}
	
	public Airline_DetailsDB(int airlineNo, String airlineName, String airlineType, int seatingCapacity, double price) {
		AirlineNo = airlineNo;
		SeatingCapacity = seatingCapacity;
		AirlineName = airlineName;
		AirlineType = airlineType;
		Price = price;
	}
	public int getAirlineNo() {
		return AirlineNo;
	}
	public void setAirlineNo(int airlineNo) {
		AirlineNo = airlineNo;
	}
	public int getSeatingCapacity() {
		return SeatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		SeatingCapacity = seatingCapacity;
	}
	public String getAirlineName() {
		return AirlineName;
	}
	public void setAirlineName(String airlineName) {
		AirlineName = airlineName;
	}
	public String getAirlineType() {
		return AirlineType;
	}
	public void setAirlineType(String airlineType) {
		AirlineType = airlineType;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
}
