package logindetails_pkg;

public class Passenger_DetailsDB {
	String Name, Nationality, PassportNo, EmailID, PhoneNo, Username, Password, CardNumber, CardType;
	int Age, IsAdmin, Disabled;
	public String getName() {
		return Name;
	}
	
	public Passenger_DetailsDB() {
		Name = null;
		Nationality = null;
		PassportNo = null;
		EmailID = null;
		PhoneNo = null;
		Username = null;
		Password = null;
		CardNumber = null;
		CardType = null;
		Age = -1;
		IsAdmin = -1;
		Disabled = -1;
	}
	
	public Passenger_DetailsDB(Passenger_DetailsDB p2) {
		Name = p2.getName();
		Nationality = p2.getNationality();
		PassportNo = p2.getPassportNo();
		EmailID = p2.getEmailID();
		PhoneNo = p2.getPhoneNo();
		Username = p2.getUsername();
		Password = p2.getPassword();
		CardNumber = p2.getCardNumber();
		CardType = p2.getCardType();
		Age = p2.getAge();
		IsAdmin = p2.getIsAdmin();
		Disabled = p2.getDisabled();
	}
	
	public Passenger_DetailsDB(String name, String nationality, String passportNo, String emailID, String phoneNo,
			String username, String password, String cardNumber, String cardType, int age, int isAdmin, int disabled) {
		Name = name;
		Nationality = nationality;
		PassportNo = passportNo;
		EmailID = emailID;
		PhoneNo = phoneNo;
		Username = username;
		Password = password;
		CardNumber = cardNumber;
		CardType = cardType;
		Age = age;
		IsAdmin = isAdmin;
		Disabled = disabled;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getPassportNo() {
		return PassportNo;
	}
	public void setPassportNo(String passportNo) {
		PassportNo = passportNo;
	}
	public String getEmailID() {
		return EmailID;
	}
	public void setEmailID(String emailID) {
		EmailID = emailID;
	}
	public String getPhoneNo() {
		return PhoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getIsAdmin() {
		return IsAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		IsAdmin = isAdmin;
	}
	public int getDisabled() {
		return Disabled;
	}
	public void setDisabled(int disabled) {
		Disabled = disabled;
	}	
}
