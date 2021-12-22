package passenger_details;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import JDBCMisc_pkg.JDBC_Creds;
public class Employee_detail_log  implements  JDBC_Creds {
	private JFrame frame;
	private JButton btnNewButton_1;
	private JButton Refresh;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable table_1;
	private ArrayList<databaseConnection> getEmployeeList(){
		ArrayList<databaseConnection> EmployeeList = new ArrayList<>();
		
		String query = "select * from employeeDetails";
		Statement st;
		ResultSet rs;
		try(Connection connection = DriverManager.getConnection(url, user, password);){
			if(connection != null) {
				System.out.println("Connected to PostgreSQL!");
			}
			else {
				System.out.println("Failed to connect to PostgreSQL");
				System.out.println("Something went very wrong!");
			}
			st = connection.createStatement();
			try {
				rs = st.executeQuery(query);
				while(rs.next()) {
					databaseConnection EmployeeDetails = new databaseConnection(rs.getInt("employee_id"), rs.getString("Name"), rs.getString("designation"), rs.getString("airline_name"), rs.getInt("airline_id"));
					EmployeeList.add(EmployeeDetails);
				}
			}
			catch (SQLException e) {
				System.out.println("Execution of Query Failed!");
				e.printStackTrace();
			}
			System.out.println("Data from Airline_Details returned successfully");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("Connection to DB closed!");
		return EmployeeList;
	}
	
	private void populateAirlineTable(JTable ArTble) {
		ArrayList<databaseConnection> l = getEmployeeList();
		DefaultTableModel model = (DefaultTableModel)ArTble.getModel();
		
		if(model.getRowCount() != 0) {
			model.setRowCount(0);
			System.out.println("Refreshed!");
		}
		
		Object[] row = new Object[5];
		
		for(int i=0;i<l.size();i++) {
			row[0] = l.get(i).getemployee_id();
			row[1] = l.get(i).getName();
			row[2] = l.get(i).getdesignation();
			row[3] = l.get(i).getAirline_name();
			row[4] = l.get(i).getairlineID();
			model.addRow(row);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee_detail_log window = new Employee_detail_log();
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
	public Employee_detail_log() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 635, 361);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton Add = new JButton("Add Employee details");
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_employee_detail window = new add_employee_detail();
				window.runAddAirlineDetails();
			}
		});
		
		btnNewButton_1 = new JButton("Edit Employee details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit_Employee_details window = new edit_Employee_details();
				window.runEditEmployeeDetails();	
			}
		});
		
		Refresh = new JButton("Refresh");
		
		lblNewLabel = new JLabel("Employee Updation Center");
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(Add)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(Refresh, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(Add, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(Refresh, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Name", "Designation", "age", "AirlineID", "Airline Name"
			}) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, Integer.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table_1);
		frame.getContentPane().setLayout(groupLayout);
	}
}
