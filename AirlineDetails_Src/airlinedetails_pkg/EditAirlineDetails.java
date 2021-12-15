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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import JDBCMisc_pkg.JDBC_Creds;

public class EditAirlineDetails extends JFrame implements JDBC_Creds{

	private JPanel contentPane;
	
	private int editAirlineDetails(int no, Airline_DetailsDB al, String[] eq) {
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
				st.setInt(1, no);
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("Value not found in table");
					return -1;
				}
				
				
				for(int i=0;i<4;i++) {
					if(!eq[i].isEmpty()) {
						st = connection.prepareStatement(eq[i]);
						switch(i) {
						case 0: st.setString(1, al.getAirlineName()); break;
						case 1: st.setString(1, al.getAirlineType()); break;
						case 2: st.setInt(1, al.getSeatingCapacity()); break;
						case 3: st.setDouble(1, al.getPrice()); break;
						default: System.out.println("Something went very wrong while matching query to edit statement"); return -2;
						}
						st.setInt(2, no);
						st.execute();
					}
				}
				System.out.println("Edit of airline id successful!");
				return 1;
			}
			catch (SQLException e) {
				System.out.println("Execution of Query Failed!");
				e.printStackTrace();
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("Connection to DB closed!");
		return 0;
	}
	
	private int deleteAirlineDetails(int no) {
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
				st.setInt(1, no);
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("Value not found in table");
					return -1;
				}
				
				st = connection.prepareStatement("DELETE FROM Airline_Details WHERE AirlineID = ?");
				st.setInt(1, no);
				st.execute();
				System.out.println("Deletion of airline id successful!");
				return 1;
			}
			catch (SQLException e) {
				System.out.println("Execution of Query Failed!");
				e.printStackTrace();
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("Connection to DB closed!");
		return 0;
	}
	
	private String[] buildEditQuery(String a_name, String a_type, String ta_sc, String ta_price) {
		String[] temp = {"", "", "", ""};
		String base = "update Airline_Details set %s = ? where AirlineID = ?";
		if(!a_name.isEmpty()) temp[0] = String.format(base, "AirlineName");
		if(!a_type.isEmpty()) temp[1] = String.format(base, "AirlineType");
		if(!ta_sc.isEmpty()) temp[2] = String.format(base, "SeatingCapacity");
		if(!ta_price.isEmpty()) temp[3] = String.format(base, "Price");
		return temp;
	}
	

	/**
	 * Launch the application.
	 */
	public void runAddAirlineDetails() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAirlineDetails frame = new EditAirlineDetails();
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
	public EditAirlineDetails() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1165, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEditAirlineDetails = new JLabel("Edit Airline Details");
		lblEditAirlineDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditAirlineDetails.setFont(new Font("Monospaced", Font.BOLD, 28));
		
		JLabel lblAirlineNo = new JLabel("Airline No");
		lblAirlineNo.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JTextArea txtrAirlineNo = new JTextArea();
		txtrAirlineNo.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblAirlineName = new JLabel("Airline Name");
		lblAirlineName.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JTextArea txtrAirlineName = new JTextArea();
		txtrAirlineName.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblSeatingCapacity = new JLabel("Seating Capacity");
		lblSeatingCapacity.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JTextArea txtrSeatingCapacity = new JTextArea();
		txtrSeatingCapacity.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JTextArea txtrPrice = new JTextArea();
		txtrPrice.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JTextArea txtrAirlineType = new JTextArea();
		txtrAirlineType.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		JLabel lblAirlineType = new JLabel("Airline Type");
		lblAirlineType.setFont(new Font("Monospaced", Font.PLAIN, 26));
		
		JLabel lblEditResult = new JLabel("Please enter the appropriate details to edit or delete records");
		lblEditResult.setFont(new Font("Monospaced", Font.PLAIN, 22));
		
		JButton btnEditAirline = new JButton("Edit Airline");
		btnEditAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ta_no = txtrAirlineNo.getText(); 
				String a_name = txtrAirlineName.getText();
				String a_type = txtrAirlineType.getText();
				String ta_sc = txtrSeatingCapacity.getText(); 
				String ta_price = txtrPrice.getText(); 
				int a_sc=-100, a_no, na_no=-100;
				float a_price=-100;
				
				if(ta_no.isEmpty()) {
					System.out.println("User Entered empty values");
					lblEditResult.setText("Please enter the Airline No to edit");					
				}
				else {
					a_no = Integer.parseInt(ta_no);
					
					if(!ta_sc.isEmpty()) {
						a_sc = Integer.parseInt(ta_sc);
					}
					if(!ta_price.isEmpty()) {
						a_price = Float.parseFloat(ta_price);
					}
					
					String[] editQuery = buildEditQuery(a_name, a_type, ta_sc, ta_price);
					int flag = 0;
					for(String q : editQuery) {
						if(!q.isEmpty()) {
							flag = 1;
							break;
						}
					}
					
					if(flag == 0) {
						System.out.println("User Entered empty values");
						lblEditResult.setText("Please enter some attribute to edit");	
					}
					else {
						Airline_DetailsDB al = new Airline_DetailsDB(a_no, a_name, a_type, a_sc, a_price);
						int res = editAirlineDetails(a_no, al, editQuery);
						if (res == 1) {
							lblEditResult.setText("Edit of AirlineID: " + ta_no + " successful");
						}
						else if (res == -1){
							lblEditResult.setText("AirlineID: " + ta_no + " not found");
						}
						else {
							lblEditResult.setText("Some error has occured");
						}
					}					
				}
			}
		});
		
		JButton btnDeleteAirline = new JButton("Delete Airline");
		btnDeleteAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ta_no = txtrAirlineNo.getText();
				
				if(ta_no.isEmpty()) {
					System.out.println("User Entered empty values");
					lblEditResult.setText("Please enter the Airline No to delete");					
				}
				else {
					int a_no = Integer.parseInt(ta_no);
					int res = deleteAirlineDetails(a_no);
					if (res == 1) {
						lblEditResult.setText("Deletion of AirlineID: " + ta_no + " successful");
					}
					else if (res == -1){
						lblEditResult.setText("AirlineID: " + ta_no + " not found");
					}
					else {
						lblEditResult.setText("Some error has occured");
					}
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAirlineName, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(txtrAirlineName, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblAirlineNo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(txtrAirlineNo, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblAirlineType, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtrAirlineType, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnEditAirline)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDeleteAirline, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblEditResult, GroupLayout.PREFERRED_SIZE, 843, GroupLayout.PREFERRED_SIZE)))))
					.addGap(464))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
							.addGap(69)
							.addComponent(txtrPrice, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblEditAirlineDetails, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1121, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSeatingCapacity, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtrSeatingCapacity, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
					.addGap(439))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEditAirlineDetails, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblAirlineNo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrAirlineNo, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSeatingCapacity, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrSeatingCapacity, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblAirlineName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtrAirlineName, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(3)
									.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtrPrice, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(lblAirlineType, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtrAirlineType, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addGap(77)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditAirline, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDeleteAirline, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEditResult, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(27))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
