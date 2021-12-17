package airportdetails_pkg;

public class Airport_DetailsDB {
	private int AirportID;
	private String Location, AirportName;
	private int Noofterminals;
	public int getAirportID() {
		return AirportID;
	}
	
	public Airport_DetailsDB() {
		AirportID = -1;
		Location = null;
		AirportName = null;
		Noofterminals = -1;
	}
	
	public Airport_DetailsDB(int airportID, String location, String airportName, int noofterminals) {
		AirportID = airportID;
		Location = location;
		AirportName = airportName;
		Noofterminals = noofterminals;
	}
	public void setAirportID(int airportID) {
		AirportID = airportID;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getAirportName() {
		return AirportName;
	}
	public void setAirportName(String airportName) {
		AirportName = airportName;
	}
	public int getNoofterminals() {
		return Noofterminals;
	}
	public void setNoofterminals(int noofterminals) {
		Noofterminals = noofterminals;
	}

}
