package logindetails_pkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JPanel implements ActionListener {
    private JTextField panelTitle;
    private JTextField txtEnterYourDetails;
    private JTextField txtAge;
    private JTextField txtDisabled;
    private JTextField txtEmailId;
    private JTextField txtFirstName;
    private JTextField txtNationality;
    private JTextField txtPhoneNo;
    private JTextField firstNameTextField;
    private JComboBox<String> ageComboBox;
    private JComboBox<String> nationalityComboBox;
    private JRadioButton disabledOptionNO;
    private JRadioButton disabledOptionYES;
    private JTextField emailIDTextField;
    private JTextField txtLastName;
    private JTextField lastNameTextField;
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
        panelTitle.setBackground(Color.decode(pageThemeColor));
        panelTitle.setBorder(BorderFactory.createEmptyBorder());
        panelTitle.setEditable(false);
        panelTitle.setFont(new Font("Calibri", Font.BOLD, 18));
        panelTitle.setText("Register Your Account: ");
        panelTitle.setBounds(175, 10, 183, 29);
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
        registrationForm.setBounds(71, 101, 394, 418);
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
        txtFirstName.setText("First Name:");
        txtFirstName.setBounds(10, 0, 96, 29);
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
        firstNameTextField.setBounds(116, 0, 214, 29);
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

        disabledOptionYES = new JRadioButton("Y");
        disabledOptionYES.addActionListener(this);
        disabledOptionYES.setFocusPainted(false);
        disabledOptionYES.setBounds(116, 170, 43, 21);
        registrationForm.add(disabledOptionYES);

        disabledOptionNO = new JRadioButton("N");
        disabledOptionNO.setSelected(true);
        disabledOptionNO.addActionListener(this);
        disabledOptionNO.setFocusPainted(false);
        disabledOptionNO.setBounds(116, 148, 43, 21);
        registrationForm.add(disabledOptionNO);

        emailIDTextField = new JTextField();
        emailIDTextField.setFont(textfieldFont);
        emailIDTextField.setMargin(inset);
        emailIDTextField.setBounds(116, 193, 214, 27);
        registrationForm.add(emailIDTextField);
        emailIDTextField.setColumns(10);

        txtLastName = new JTextField();
        txtLastName.setBorder(BorderFactory.createEmptyBorder());
        txtLastName.setFont(registrationFont);
        txtLastName.setEditable(false);
        txtLastName.setText("Last Name");
        txtLastName.setBounds(10, 40, 96, 29);
        registrationForm.add(txtLastName);
        txtLastName.setColumns(10);

        lastNameTextField = new JTextField();
        lastNameTextField.setFont(textfieldFont);
        lastNameTextField.setMargin(inset);
        lastNameTextField.setBounds(116, 39, 214, 30);
        registrationForm.add(lastNameTextField);
        lastNameTextField.setColumns(10);

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
        txtSetUsername.setBounds(10, 296, 118, 27);
        registrationForm.add(txtSetUsername);
        txtSetUsername.setColumns(10);

        txtSetPassword = new JTextField();
        txtSetPassword.setBorder(BorderFactory.createEmptyBorder());
        txtSetPassword.setFont(registrationFont);
        txtSetPassword.setText("Set Password:");
        txtSetPassword.setEditable(false);
        txtSetPassword.setBounds(10, 333, 118, 24);
        registrationForm.add(txtSetPassword);
        txtSetPassword.setColumns(10);

        txtConfirmPassword = new JTextField();
        txtConfirmPassword.setBorder(BorderFactory.createEmptyBorder());
        txtConfirmPassword.setFont(new Font("Monospace",Font.PLAIN,16));
        txtConfirmPassword.setEditable(false);
        txtConfirmPassword.setText("Confirm password");
        txtConfirmPassword.setBounds(10, 367, 130, 27);
        registrationForm.add(txtConfirmPassword);
        txtConfirmPassword.setColumns(10);

        setPasswordTextField = new JPasswordField();
        setPasswordTextField.setFont(textfieldFont);
        setPasswordTextField.setMargin(inset);
        setPasswordTextField.setBounds(170, 333, 214, 27);
        registrationForm.add(setPasswordTextField);

        confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setFont(textfieldFont);
        confirmPasswordTextField.setMargin(inset);
        confirmPasswordTextField.setBounds(170, 370, 214, 27);
        registrationForm.add(confirmPasswordTextField);

        setUsernameTextField = new JTextField();
        setUsernameTextField.setFont(textfieldFont);
        setUsernameTextField.setMargin(inset);
        setUsernameTextField.setBounds(170, 296, 214, 27);
        registrationForm.add(setUsernameTextField);
        setUsernameTextField.setColumns(10);

        hr = new JTextField();
        hr.setBorder(BorderFactory.createEmptyBorder());
        hr.setBackground(registrationForm.getBackground());
        hr.setText("---------------------------------------------------------------------------------------------");
        hr.setBounds(10, 271, 374, 11);
        registrationForm.add(hr);
        hr.setColumns(10);

        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);
        submitButton.setFocusPainted(false);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        submitButton.setBounds(359, 529, 106, 29);
        add(submitButton);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==disabledOptionNO){
            disabledOptionYES.setSelected(false);
        }else if(e.getSource()==disabledOptionYES){
            disabledOptionNO.setSelected(false);
        }else if(e.getSource()==submitButton){
            if(firstNameTextField.getText().length()<10 || lastNameTextField.getText()==null || ageComboBox.getSelectedItem()==null || nationalityComboBox.getSelectedItem()==null || emailIDTextField.getText()==null||phoneNoTextField.getText()==null){
                System.out.println("Enter every item!");
            }
        }else{}
    }
}
