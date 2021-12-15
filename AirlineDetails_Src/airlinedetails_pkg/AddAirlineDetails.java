package airlinedetails_pkg;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import JDBCMisc_pkg.JDBC_Creds;

public class AddAirlineDetails extends JFrame implements JDBC_Creds{

	private JPanel contentPane;
	
	
	private int addAirlineDetailsToDB(Airline_DetailsDB al) {
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
				st = connection.prepareStatement("SELECT count(*) from Airline_Details WHERE AirlineID = ?");
				st.setInt(1, al.getAirlineNo());
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count != 0) {
					System.out.println("Value already in table");
					return -2;
				}
				
				st = connection.prepareStatement("insert into Airline_Details values (?, ?, ?, ?, ?)");
				st.setInt(1, al.getAirlineNo());
				st.setString(2, al.getAirlineName());
				st.setString(3, al.getAirlineType());
				st.setInt(4, al.getSeatingCapacity());
				st.setDouble(5, al.getPrice());
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
	

	/**
	 * Launch the application.
	 */
	public void runAddAirlineDetails() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAirlineDetails frame = new AddAirlineDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddAirlineDetails() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1168, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddAirlineDetails = new JLabel("Add Airline Details");
		lblAddAirlineDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddAirlineDetails.setFont(new Font("Monospaced", Font.BOLD, 28));
		
		JLabel lblAirlineNo = new JLabel("Airline No");
		lblAirlineNo.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JLabel lblAirlineName = new JLabel("Airline Name");
		lblAirlineName.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JLabel lblAirlineType = new JLabel("Airline Type");
		lblAirlineType.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JLabel lblSeatingCapacity = new JLabel("Seating Capacity");
		lblSeatingCapacity.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JTextArea txtrAirlineNo = new JTextArea();
		txtrAirlineNo.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JTextArea txtrAirlineName = new JTextArea();
		txtrAirlineName.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JTextArea txtrAirlineType = new JTextArea();
		txtrAirlineType.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JTextArea txtrSeatingCapacity = new JTextArea();
		txtrSeatingCapacity.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JTextArea txtrPrice = new JTextArea();
		txtrPrice.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblAddResult = new JLabel("Please enter the details to be added");
		
		JButton btnAddAirline = new JButton("Add Airline");
		btnAddAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ta_no = txtrAirlineNo.getText(); 
				String a_name = txtrAirlineName.getText();
				String a_type = txtrAirlineType.getText();
				String ta_sc = txtrSeatingCapacity.getText(); 
				String ta_price = txtrPrice.getText(); 
				
				if(ta_no.isEmpty() || a_name.isEmpty() || a_type.isEmpty() || ta_sc.isEmpty() || ta_price.isEmpty()) {
					System.out.println("User Entered empty values");
					lblAddResult.setText("One or More Values are empty!");					
				}
				else {
					int a_no = Integer.parseInt(ta_no);
					int a_sc = Integer.parseInt(ta_sc);
					float a_price = Float.parseFloat(ta_price);
					Airline_DetailsDB al = new Airline_DetailsDB(a_no, a_name, a_type, a_sc, a_price);
					int res = addAirlineDetailsToDB(al);
					if (res == 1) {
						lblAddResult.setText("Values inserted successfully!");
					}
					else if(res == -2) {
						lblAddResult.setText("Value Exists, please enter another value");
					}
				}
			}
		});
		
		lblAddResult.setFont(new Font("Monospaced", Font.PLAIN, 26));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAddAirlineDetails, GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblAirlineNo)
									.addGap(63)
									.addComponent(txtrAirlineNo, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblAirlineType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAirlineName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrAirlineName, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrAirlineType, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
							.addGap(54)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSeatingCapacity, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrPrice, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtrSeatingCapacity, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
							.addGap(82))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddAirline, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(lblAddResult, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddAirlineDetails, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(66)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAirlineNo)
								.addComponent(txtrAirlineNo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addGap(91)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtrAirlineName, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAirlineName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(90)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtrAirlineType, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAirlineType, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(129)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtrSeatingCapacity, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSeatingCapacity, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(87)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtrPrice, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddAirline, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddResult, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
