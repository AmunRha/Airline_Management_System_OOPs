package passenger_details;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import JDBCMisc.EmployeeDetailDB;
import java.sql.*;
import JDBCMisc_pkg.JDBC_Creds;
public class add_employee_detail implements  JDBC_Creds{

	private JFrame frame;
	private JTextField emp_id;
	private JTextField name;
	private JTextField air_name;
	private JTextField airNo;
	private int addAirlineDetailsToDB(databaseConnection al) {
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
				st = connection.prepareStatement("SELECT count(*) from employeedetails WHERE employee_id = ?");
				st.setInt(1, al.getemployee_id());
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count != 0) {
					System.out.println("Value already in table");
					return -2;
				}
				
				st = connection.prepareStatement("insert into employeedetails values (?, ?, ?, ?, ?)");
				st.setInt(1, al.getemployee_id());
				st.setString(2, al.getName());
				st.setString(3, al.getdesignation());
				st.setString(4, al.getAirline_name());
				st.setInt(5, al.getairlineID());
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
	public  void runAddAirlineDetails() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					add_employee_detail window = new add_employee_detail();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public add_employee_detail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 406);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel Heading = new JLabel("Add Employee Details");
		Heading.setBackground(Color.BLACK);
		Heading.setFont(new Font("Monospaced", Font.PLAIN, 10));
		Heading.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel EmployeeID = new JLabel("EmployeeID");
		
		emp_id = new JTextField();
		emp_id.setColumns(10);
		
		JLabel Name = new JLabel("Name");
		
		name = new JTextField();
		name.setColumns(10);
		
		JList list = new JList();
		
		JList list_1 = new JList();
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pilot", "AirHostress", "Janitor", "FuelEngineeir", "Maintainer"}));
		
		JLabel Designation = new JLabel("Designation");
		
		air_name = new JTextField();
		air_name.setColumns(10);
		
		JLabel AirlineName = new JLabel("AirlineName");
		AirlineName.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel Airline_No = new JLabel("Airline No.");
		Airline_No.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel status = new JLabel("Fill the entries");
		airNo = new JTextField();
		airNo.setColumns(10);
		JButton addButtion = new JButton("Add Employee ");
		addButtion.addActionListener((ActionListener) new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				
					String ta_no = emp_id.getText(); 
					String a_name = name.getText();
					String a_type = comboBox.getName();
					String ta_sc = air_name.getText(); 
					String ta_price = airNo.getText(); 
					
					if(ta_no.isEmpty() || a_name.isEmpty()  || ta_sc.isEmpty() || ta_price.isEmpty()) {
						System.out.println("User Entered empty values");
						status.setText("One or More Values are empty!");					
					}
					else {
						int a_no = Integer.parseInt(ta_no);
						int a_price = Integer.parseInt(ta_price);
						databaseConnection al = new databaseConnection(a_no, a_name, a_type, ta_sc, a_price);
						int res = addAirlineDetailsToDB(al);
						if (res == 1) {
							status.setText("Values inserted successfully!");
							frame.dispose();
						}
						else if(res == -2) {
							status.setText("Value Exists, please enter another value");
							frame.dispose();
						}
					}
				}
				
			
				
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Designation, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(EmployeeID, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
										.addComponent(Name, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(name)
										.addComponent(emp_id, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(71)
											.addComponent(AirlineName, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(92)
											.addComponent(Airline_No, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(airNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(air_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(267)
							.addComponent(Heading, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(306)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(status, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(addButtion))))
					.addContainerGap(222, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(Heading, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(EmployeeID)
						.addComponent(emp_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(air_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AirlineName))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(Name)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(Airline_No)
							.addComponent(airNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addComponent(Designation))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(list)
					.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
					.addComponent(addButtion)
					.addGap(5)
					.addComponent(status)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
