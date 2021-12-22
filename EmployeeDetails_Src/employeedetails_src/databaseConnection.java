package passenger_details;
public class databaseConnection {
	private int employee_id,airlineID;
	private String Name, designation,airline_name;

	
	public databaseConnection() {
		employee_id = 0;
		airlineID = 0;
		Name = null;
		designation = null;
		airline_name = null;
	}
	
	public databaseConnection(int employee_id, String Name, String designation,String airline_name,int airlineID) {
		this.employee_id = employee_id;
		this.Name = Name;
		this.designation = designation;
		this.airline_name = airline_name;
		this.airlineID = airlineID;
	}		
	public int getemployee_id() {
		return employee_id;
	}
	public void setemployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getairlineID() {
		return airlineID;
	}
	public void setairlineID(int airlineID) {
		this.airlineID = airlineID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getdesignation() {
		return designation;
	}
	public void setdesignation(String designation) {
		this.designation = designation;
	}
	public String getAirline_name() {
		return airline_name;
	}
	public void setAirlineName(String airline_name) {
		this.airline_name = airline_name;
	}

}
