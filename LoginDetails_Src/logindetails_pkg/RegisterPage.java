package logindetails_pkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import airlinedetails_pkg.Airline_DetailsDB;
import java.awt.SystemColor;
import JDBCMisc_pkg.JDBC_Creds;
import javax.swing.DefaultComboBoxModel;

public class RegisterPage extends JPanel implements ActionListener, JDBC_Creds{
    private JTextField panelTitle;
    private JTextField txtEnterYourDetails;
    private JTextField txtAge;
    private JTextField txtDisabled;
    private JTextField txtEmailId;
    private JTextField txtFirstName;
    private JTextField txtNationality;
    private JTextField txtPhoneNo;
    private JTextField firstNameTextField;
    private JComboBox<String> ageComboBox, disabledComboBox;
    private JComboBox<String> nationalityComboBox;
    private JTextField emailIDTextField;
    private JTextField phoneNoTextField;
    private JTextField txtSetUsername;
    private JTextField txtSetPassword;
    private JTextField txtConfirmPassword;
    private JPasswordField setPasswordTextField;
    private JPasswordField confirmPasswordTextField;
    private JTextField setUsernameTextField;
    private String pageThemeColor = "#7CD1B8";
    private JTextField hr;
    private JButton submitButton;
    String firstName,lastName,email,nationality,isDisabled,age,username,password,confirmPassword,phno;
    private JTextField txtCardNo;
    private JTextField textcardNumber;
    private JTextField textField_2;
    private JTextField txtCardType;
    private JTextField textcardType;
    private JTextField txtPassportNo;
    private JTextField txtrPassportNo;
    
    
	private int addPassengerDetails(Passenger_DetailsDB p) {
		PreparedStatement st;
		ResultSet rs;
		int count = 0;
		try(Connection connection = DriverManager.getConnection(url, user, password);){
			if(connection != null) {
				System.out.println("Connected to PostgreSQL!");
			}
			else {
				System.out.println("Failed to connect to PostgreSQL");
				System.out.println("Something went very wrong!");
			}
			
			try {
				st = connection.prepareStatement("SELECT count(*) from Passenger_Details WHERE PassportNo = ?");
				st.setString(1, p.getPassportNo());
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count != 0) {
					System.out.println("Value already in table");
					return -2;
				}
				
				st = connection.prepareStatement("insert into Airline_Details values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				st.setString(1, p.getName());
				st.setInt(2, p.getAge());
				st.setString(3, p.getPassportNo());
				st.setInt(4, p.getDisabled());
				st.setString(5, p.getEmailID());
				st.setString(6, p.getPhoneNo());
				st.setString(7, p.getUsername());
				st.setString(8, p.getPassword());
				st.setString(9, p.getCardNumber());
				st.setString(10, p.getCardType());
				st.setInt(11, p.getIsAdmin());
				st.execute();
				return 1;
			}
			catch (SQLException e) {
				System.out.println("Execution of Query Failed!");
				e.printStackTrace();
			}
			System.out.println("Values inserted into the DB successfully!");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("Connection to DB closed!");
		return 0;
	}
	
	private int isDisabled(String dis) {
		if (dis.equalsIgnoreCase("YES")) return 1;
		else return 0;
	}
    
    /**
     * Create the panel.
     */
    public RegisterPage() {

        Insets inset = new Insets(3,10,0,0);
        Font registrationFont = new Font("Monospace",Font.PLAIN,16);
        Font textfieldFont = new Font("Consolas",Font.PLAIN,16);

        setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        panelTitle = new JTextField();
        panelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitle.setBackground(Color.decode(pageThemeColor));
        panelTitle.setBorder(BorderFactory.createEmptyBorder());
        panelTitle.setEditable(false);
        panelTitle.setFont(new Font("Calibri", Font.BOLD, 18));
        panelTitle.setText("Register Your Account: ");
        panelTitle.setBounds(12, 10, 709, 29);
        add(panelTitle);
        panelTitle.setColumns(10);

        txtEnterYourDetails = new JTextField();
        txtEnterYourDetails.setBackground(this.getBackground());
        txtEnterYourDetails.setBorder(BorderFactory.createEmptyBorder());
        txtEnterYourDetails.setEditable(false);
        txtEnterYourDetails.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        txtEnterYourDetails.setText("Enter your details:");
        txtEnterYourDetails.setBounds(71, 62, 183, 29);
        add(txtEnterYourDetails);
        txtEnterYourDetails.setColumns(10);

        JPanel registrationForm = new JPanel();
        registrationForm.setBounds(71, 104, 394, 575);
        add(registrationForm);
        registrationForm.setLayout(null);

        txtAge = new JTextField();
        txtAge.setBorder(BorderFactory.createEmptyBorder());
        txtAge.setEditable(false);
        txtAge.setFont(registrationFont);
        txtAge.setText("Age:");
        txtAge.setBounds(10, 79, 96, 27);
        registrationForm.add(txtAge);
        txtAge.setColumns(10);

        txtDisabled = new JTextField();
        txtDisabled.setBorder(BorderFactory.createEmptyBorder());
        txtDisabled.setEditable(false);
        txtDisabled.setFont(registrationFont);
        txtDisabled.setText("Disabled:");
        txtDisabled.setBounds(10, 153, 96, 27);
        registrationForm.add(txtDisabled);
        txtDisabled.setColumns(10);

        txtEmailId = new JTextField();
        txtEmailId.setBorder(BorderFactory.createEmptyBorder());
        txtEmailId.setEditable(false);
        txtEmailId.setFont(registrationFont);
        txtEmailId.setText("E-Mail ID:");
        txtEmailId.setBounds(10, 190, 96, 27);
        registrationForm.add(txtEmailId);
        txtEmailId.setColumns(10);

        txtFirstName = new JTextField();
        txtFirstName.setBorder(BorderFactory.createEmptyBorder());
        txtFirstName.setFont(registrationFont);
        txtFirstName.setEditable(false);
        txtFirstName.setText("Name:");
        txtFirstName.setBounds(10, 37, 96, 29);
        registrationForm.add(txtFirstName);
        txtFirstName.setColumns(10);

        txtNationality = new JTextField();
        txtNationality.setBorder(BorderFactory.createEmptyBorder());
        txtNationality.setFont(registrationFont);
        txtNationality.setEditable(false);
        txtNationality.setText("Nationality");
        txtNationality.setBounds(10, 116, 96, 27);
        registrationForm.add(txtNationality);
        txtNationality.setColumns(10);

        txtPhoneNo = new JTextField();
        txtPhoneNo.setBorder(BorderFactory.createEmptyBorder());
        txtPhoneNo.setFont(registrationFont);
        txtPhoneNo.setEditable(false);
        txtPhoneNo.setText("Phone No. :");
        txtPhoneNo.setBounds(10, 231, 96, 27);
        registrationForm.add(txtPhoneNo);
        txtPhoneNo.setColumns(10);

        firstNameTextField = new JTextField();
        firstNameTextField.setFont(textfieldFont);
        firstNameTextField.setMargin(inset);
        firstNameTextField.setBounds(116, 37, 214, 29);
        registrationForm.add(firstNameTextField);
        firstNameTextField.setColumns(10);

        String[] ageList = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "16", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54",
                "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99"};
        ageComboBox = new JComboBox(ageList);
        ageComboBox.setBackground(Color.WHITE);
        ageComboBox.setBounds(116, 79, 161, 27);
        registrationForm.add(ageComboBox);

        String[] countryList = {"Afghanistan","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla","Antarctica","Antigua and Barbuda","Argentina","Armenia","Aruba","Australia","Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bonaire","Bosnia and Herzegovina","Botswana","Bouvet Island","Brazil","Brunei Darussalam","Bulgaria","Burkina Faso","Burundi","Cabo Verde","Cambodia","Cameroon","Canada","Cayman Islands","Central African Republic","Chad","Chile","China","Christmas Island","Colombia","Comoros","Congo","Cook Islands","Costa Rica","Croatia","Cuba","Curaçao","Cyprus","Czechia","Côte d'Ivoire","Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Eswatini","Ethiopia","Falkland Islands (the) [Malvinas]","Faroe Islands (the)","Fiji","Finland","France","French Guiana","French Polynesia","French Southern Territories (the)","Gabon","Gambia (the)","Georgia","Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guadeloupe","Guam","Guatemala","Guernsey","Guinea","Guinea-Bissau","Guyana","Haiti","Heard Island and McDonald Islands","Holy See","Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan","Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg","Macao","Madagascar","Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands","Martinique","Mauritania","Mauritius","Mayotte","Mexico","Micronesia","Moldova","Monaco","Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal","Netherlands","New Caledonia","New Zealand","Nicaragua","Niger (the)","Nigeria","Niue","Norfolk Island","Northern Mariana Islands","Norway","Oman","Pakistan","Palau","Palestine","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Pitcairn","Poland","Portugal","Puerto Rico","Qatar","North Macedonia","Romania","Russia","Rwanda","Réunion","Saint Barthelemy","Saint Helena","Saint Kitts and Nevis","Saint Lucia","Saint Martin (French part)","Saint Pierre and Miquelon","Saint Vincent and the Grenadines","Samoa","San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone","Singapore","Sint Maarten (Dutch part)","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Sudan","Spain","Sri Lanka","Sudan","Suriname","Svalbard and Jan Mayen","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania","Thailand","Timor-Leste","Togo","Tokelau","Tonga","Trinidad and Tobago","Tunisia","Turkey","Turkmenistan","Turks and Caicos Islands","Tuvalu","Uganda","Ukraine","United Arab Emirates","United Kingdom","United States of America","Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam","Virgin Islands (British)","Virgin Islands (U.S.)","Wallis and Futuna","Western Sahara","Yemen","Zambia","Zimbabwe"};
        nationalityComboBox = new JComboBox(countryList);
        nationalityComboBox.setBackground(Color.WHITE);
        nationalityComboBox.setBounds(116, 116, 161, 27);
        registrationForm.add(nationalityComboBox);

        emailIDTextField = new JTextField();
        emailIDTextField.setFont(textfieldFont);
        emailIDTextField.setMargin(inset);
        emailIDTextField.setBounds(116, 193, 214, 27);
        registrationForm.add(emailIDTextField);
        emailIDTextField.setColumns(10);

        phoneNoTextField = new JTextField();
        phoneNoTextField.setFont(textfieldFont);
        phoneNoTextField.setMargin(inset);
        phoneNoTextField.setBounds(116, 234, 214, 27);
        registrationForm.add(phoneNoTextField);
        phoneNoTextField.setColumns(10);

        txtSetUsername = new JTextField();
        txtSetUsername.setBorder(BorderFactory.createEmptyBorder());
        txtSetUsername.setFont(registrationFont);
        txtSetUsername.setText("Set Username:");
        txtSetUsername.setEditable(false);
        txtSetUsername.setBounds(10, 351, 118, 27);
        registrationForm.add(txtSetUsername);
        txtSetUsername.setColumns(10);

        txtSetPassword = new JTextField();
        txtSetPassword.setBorder(BorderFactory.createEmptyBorder());
        txtSetPassword.setFont(registrationFont);
        txtSetPassword.setText("Set Password:");
        txtSetPassword.setEditable(false);
        txtSetPassword.setBounds(10, 388, 118, 24);
        registrationForm.add(txtSetPassword);
        txtSetPassword.setColumns(10);

        txtConfirmPassword = new JTextField();
        txtConfirmPassword.setBorder(BorderFactory.createEmptyBorder());
        txtConfirmPassword.setFont(new Font("Monospace",Font.PLAIN,16));
        txtConfirmPassword.setEditable(false);
        txtConfirmPassword.setText("Confirm password");
        txtConfirmPassword.setBounds(10, 422, 130, 27);
        registrationForm.add(txtConfirmPassword);
        txtConfirmPassword.setColumns(10);

        setPasswordTextField = new JPasswordField();
        setPasswordTextField.setFont(textfieldFont);
        setPasswordTextField.setMargin(inset);
        setPasswordTextField.setBounds(170, 388, 214, 27);
        registrationForm.add(setPasswordTextField);

        confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setFont(textfieldFont);
        confirmPasswordTextField.setMargin(inset);
        confirmPasswordTextField.setBounds(170, 425, 214, 27);
        registrationForm.add(confirmPasswordTextField);

        setUsernameTextField = new JTextField();
        setUsernameTextField.setFont(textfieldFont);
        setUsernameTextField.setMargin(inset);
        setUsernameTextField.setBounds(170, 351, 214, 27);
        registrationForm.add(setUsernameTextField);
        setUsernameTextField.setColumns(10);

        hr = new JTextField();
        hr.setBorder(BorderFactory.createEmptyBorder());
        hr.setBackground(registrationForm.getBackground());
        hr.setText("---------------------------------------------------------------------------------------------");
        hr.setBounds(10, 326, 374, 11);
        registrationForm.add(hr);
        hr.setColumns(10);
        
        txtCardNo = new JTextField();
        txtCardNo.setText("Card No");
        txtCardNo.setFont(new Font("Dialog", Font.PLAIN, 16));
        txtCardNo.setEditable(false);
        txtCardNo.setColumns(10);
        txtCardNo.setBorder(BorderFactory.createEmptyBorder());
        txtCardNo.setBounds(10, 487, 96, 27);
        registrationForm.add(txtCardNo);
        
        textcardNumber = new JTextField();
        textcardNumber.setMargin(new Insets(3, 10, 0, 0));
        textcardNumber.setFont(new Font("Consolas", Font.PLAIN, 16));
        textcardNumber.setColumns(10);
        textcardNumber.setBounds(116, 488, 214, 27);
        registrationForm.add(textcardNumber);
        
        textField_2 = new JTextField();
        textField_2.setText("---------------------------------------------------------------------------------------------");
        textField_2.setColumns(10);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        textField_2.setBackground(SystemColor.menu);
        textField_2.setBounds(10, 463, 374, 11);
        registrationForm.add(textField_2);
        
        txtCardType = new JTextField();
        txtCardType.setText("Card Type");
        txtCardType.setFont(new Font("Dialog", Font.PLAIN, 16));
        txtCardType.setEditable(false);
        txtCardType.setColumns(10);
        txtCardType.setBorder(BorderFactory.createEmptyBorder());
        txtCardType.setBounds(10, 534, 96, 27);
        registrationForm.add(txtCardType);
        
        textcardType = new JTextField();
        textcardType.setMargin(new Insets(3, 10, 0, 0));
        textcardType.setFont(new Font("Consolas", Font.PLAIN, 16));
        textcardType.setColumns(10);
        textcardType.setBounds(116, 535, 214, 27);
        registrationForm.add(textcardType);
        
        txtPassportNo = new JTextField();
        txtPassportNo.setText("Passport No. :");
        txtPassportNo.setFont(new Font("Dialog", Font.PLAIN, 16));
        txtPassportNo.setEditable(false);
        txtPassportNo.setColumns(10);
        txtPassportNo.setBorder(BorderFactory.createEmptyBorder());
        txtPassportNo.setBounds(10, 283, 105, 27);
        registrationForm.add(txtPassportNo);
        
        txtrPassportNo = new JTextField();
        txtrPassportNo.setMargin(new Insets(3, 10, 0, 0));
        txtrPassportNo.setFont(new Font("Consolas", Font.PLAIN, 16));
        txtrPassportNo.setColumns(10);
        txtrPassportNo.setBounds(126, 286, 214, 27);
        registrationForm.add(txtrPassportNo);
        
        disabledComboBox = new JComboBox();
        disabledComboBox.setModel(new DefaultComboBoxModel(new String[] {"NO", "YES"}));
        disabledComboBox.setBounds(118, 157, 159, 22);
        registrationForm.add(disabledComboBox);

        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        submitButton.setBounds(359, 705, 106, 29);
        add(submitButton);

    }
    

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==submitButton){
            if(firstNameTextField.getText().length()<10 || ageComboBox.getSelectedItem()==null || nationalityComboBox.getSelectedItem()==null || emailIDTextField.getText()==null||phoneNoTextField.getText()==null){
            	txtEnterYourDetails.setText("Enter every item!");
            }
            else {
            	String name = firstNameTextField.getText();
            	String nat = nationalityComboBox.getSelectedItem().toString();
            	String passport = txtPassportNo.getText();
            	String emailID = emailIDTextField.getText();
            	String phoneNo = phoneNoTextField.getText();
            	String username = setUsernameTextField.getText();
            	String password = String.copyValueOf(setPasswordTextField.getPassword());
            	String cardno = textcardNumber.getText();
            	String cardtype = textcardType.getText();
            	int age = Integer.parseInt(ageComboBox.getSelectedItem().toString());
            	int dis = isDisabled(disabledComboBox.getSelectedItem().toString());
            	
            	
            	Passenger_DetailsDB p = new Passenger_DetailsDB(name, nat, passport, emailID, phoneNo, username, password, cardno, cardtype, age, 0, dis);
            	addPassengerDetails(p);
            }
        }
    }
}
