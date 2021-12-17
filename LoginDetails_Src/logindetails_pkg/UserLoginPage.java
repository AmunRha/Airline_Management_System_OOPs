package logindetails_pkg;

import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import JDBCMisc_pkg.JDBC_Creds;
import javax.swing.JLabel;

public class UserLoginPage extends JFrame implements JDBC_Creds {
    private JTextField txtPassengerLoginPage;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private String pageThemeColor = "#7CD1B8";
    private JButton loginButton;
    
    
    private int loginPage(String username, String pass) {
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
				st = connection.prepareStatement("SELECT count(*) from Passenger_Details WHERE Username = ?");
				st.setString(1, username);
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0 || count > 1 ) {
					System.out.println("Value not in table");
					return -3;
				}
				
				Passenger_DetailsDB p = new Passenger_DetailsDB(rs.getString("Name"), rs.getString("Nationality"), rs.getString("PassportNo"), rs.getString("EmailID"),
						rs.getString("PhoneNo"), rs.getString("Username"), rs.getString("password"), rs.getString("CardNumber"), rs.getString("CardType"),
						rs.getInt("Age"), rs.getInt("IsAdmin"), rs.getInt("Disabled"));
				if(pass.equals(p.getPassword())) {
					System.out.println("passowrd not matching");
					return -4;
				}
				
				if(p.getIsAdmin() == 1) return 100;
				else return 1;
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
    
    public void runUserLoginPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLoginPage frame = new UserLoginPage();
					frame.setSize(800, 800);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    /**
     * Create the panel.
     */
    public UserLoginPage() {
        getContentPane().setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        Font txtFont = new Font("Bahnschrift", Font.BOLD, 16);

        txtPassengerLoginPage = new JTextField();
        txtPassengerLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
        txtPassengerLoginPage.setBackground(this.getBackground());
        txtPassengerLoginPage.setBorder(BorderFactory.createEmptyBorder());
        txtPassengerLoginPage.setEditable(false);
        txtPassengerLoginPage.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        txtPassengerLoginPage.setText("Login Page");
        txtPassengerLoginPage.setBounds(12, 10, 605, 55);
        getContentPane().add(txtPassengerLoginPage);
        txtPassengerLoginPage.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(69, 114, 444, 151);
        getContentPane().add(panel);
        panel.setLayout(null);

        txtUsername = new JTextField();
        txtUsername.setEditable(false);
        txtUsername.setFont(txtFont);
        txtUsername.setText("Username:");
        txtUsername.setBounds(10, 10, 126, 34);
        panel.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setEditable(false);
        txtPassword.setFont(txtFont);
        txtPassword.setText("Password:");
        txtPassword.setBounds(10, 63, 126, 34);
        panel.add(txtPassword);
        txtPassword.setColumns(10);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Consolas",Font.PLAIN,15));
        usernameTextField.setMargin(new Insets(0,10,0,0));
        usernameTextField.setBounds(198, 11, 236, 34);
        panel.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setMargin(new Insets(0,10,0,0));
        passwordField.setBounds(198, 63, 236, 34);
        panel.add(passwordField);
        
        JLabel lblNewLabel = new JLabel("Enter the info");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(69, 85, 444, 16);
        getContentPane().add(lblNewLabel);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String username = usernameTextField.getText();
        		String pass = String.copyValueOf(passwordField.getPassword());
        		int res = loginPage(username, pass);
        		
        		if (res == -3) {
        			lblNewLabel.setText("Username does not exist!");
        		}
        		else if(res == -4) {
        			lblNewLabel.setText("Username and Password do not match");
        		}
        		else if(res == 100) {
        			System.out.println("Admin Login");
        			// AdminMainPage();
        		}
        		else if(res == 1) {
        			System.out.println("Passenger Login");
        			//PassengerMainPage();
        		}
        	}
        });
        loginButton.setFocusPainted(false);
        loginButton.setFont(txtFont);
        loginButton.setBounds(349, 107, 85, 34);
        panel.add(loginButton);

    }
}
