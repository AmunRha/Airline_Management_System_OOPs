package passenger_details;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.sql.*;
import JDBCMisc.EmployeeDetailDB;
public class edit_Employee_details implements JDBC_Creds {
	private JFrame frame;
	private JTextField empid;
	private JTextField name;
	private JTextField age;
	private JTextField airid;
	private JTextField airno;
	private int editEmployeeDetails(int no,  databaseConnection al, String[] eq) {
		PreparedStatement st;
		ResultSet rs;
		int count = 0;
		try(Connection connection = DriverManager.getConnection(url, user, password);){
			if(connection != null) {
				System.out.println("Connected to PostgreSQL!");
			}else{
				System.out.println("Failed to connect to PostgreSQL");
				System.out.println("Something went very wrong!");
			}
			
			try {
				st = connection.prepareStatement("SELECT count(*) from employeeDetails WHERE employee_id = ?");
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
						case 0: st.setInt(1, al.getemployee_id()); break;
						case 1: st.setString(1, al.getName()); break;
						case 2: st.setString(1, al.getdesignation()); break;
						case 3: st.setString(1, al.getAirline_name()); break;
						case 4: st.setInt(1,al. getairlineID()); break;
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
				st = connection.prepareStatement("SELECT count(*) from employeeDetails WHERE employee_id = ?");
				st.setInt(1, no);
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("Value not found in table");
					return -1;
				}
				
				st = connection.prepareStatement("DELETE FROM employeeDetails WHERE employee_id = ?");
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
		String base = "update employeeDetails set %s = ? where employee_id = ?";
		if(!a_name.isEmpty()) temp[0] = String.format(base, "name");
		if(!a_type.isEmpty()) temp[1] = String.format(base, "designation");
		if(!ta_sc.isEmpty()) temp[2] = String.format(base, "airline_name");
		if(!ta_price.isEmpty()) temp[3] = String.format(base, "airline_id");
		return temp;
	}
	
	/**
	 * Launch the application.
	 */
public void runEditEmployeeDetails() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					edit_Employee_details window = new edit_Employee_details();
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
	public edit_Employee_details() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 693, 352);
		
		JLabel lblNewLabel = new JLabel("Edit Employee details");
		
		JLabel lblNewLabel_1 = new JLabel("Employee Id");
		
		empid = new JTextField();
		empid.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name ");
		
		name = new JTextField();
		name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Age ");
		
		age = new JTextField();
		
		JComboBox designation = new JComboBox();
		designation.setModel(new DefaultComboBoxModel(new String[] {"Pilot", "Air hostress", "SuperVisor", "Flight maintainer", "Flight Engineer"}));
		
		JLabel lblNewLabel_4 = new JLabel("Designation ");
		
		JButton btnNewButton = new JButton("Edit employee");
		
		JLabel lblNewLabel_5 = new JLabel("Airline ID");
		
		airid = new JTextField();
		airid.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Airline name");
		
		airno = new JTextField();
		airno.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Delete Employee");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(26)
									.addComponent(btnNewButton)
									.addGap(28)
									.addComponent(btnNewButton_1))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(39)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_4))
									.addGap(27)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(name, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
										.addComponent(age, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
										.addComponent(empid)
										.addComponent(designation, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(55)
									.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(airid, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addComponent(lblNewLabel_6)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(airno))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(245)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(149, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(lblNewLabel)
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(empid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(airid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6)
						.addComponent(airno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(age, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(designation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
