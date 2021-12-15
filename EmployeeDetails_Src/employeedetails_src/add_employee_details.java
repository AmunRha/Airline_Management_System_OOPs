package employeedetails_src;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import java.awt.List;
import java.awt.TextArea;
import java.awt.Choice;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Button;
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
public class employee_details extends JFrame implements JDBC_Creds  {
	private JFrame frame;
	private JTextField textField;
	private JLabel Name_passenger;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel Designation;
	private JPopupMenu popupMenu;
	private JLabel lblNewLabel;
	private JTextField textField_3;
	private JLabel lblNewLabel_1;
	private JTextField textField_4;
	
//	Implementation pending
//	private void addEmployeeDetails() {
//		PreparedStatement st;
//		ResultSet rs;
//		int count = 0;
//		try(Connection connection = DriverManager.getConnection(url, user, password);){
//			if(connection != null) {
//				System.out.println("Connected to PostgreSQL!");
//			}
//			else {
//				System.out.println("Failed to connect to PostgreSQL");
//				System.out.println("Something went very wrong!");
//			}
//			
//			try {
//				st = connection.prepareStatement("SELECT count(*) from Airline_Details WHERE AirlineID = ?");
//				st.setInt(1, al.getAirlineNo());
//				rs = st.executeQuery();
//				
//				if(rs.next()) {
//					count = rs.getInt(1);
//				}
//				
//				if(count != 0) {
//					System.out.println("Value already in table");
//					
//				}
//				
//				st = connection.prepareStatement("insert into Airline_Details values (?, ?, ?, ?, ?)");
//				st.setInt(1, al.getAirlineNo());
//				st.setString(2, al.getAirlineName());
//				st.setString(3, al.getAirlineType());
//				st.setInt(4, al.getSeatingCapacity());
//				st.setDouble(5, al.getPrice());
//				st.execute();
//				
//			}
//			catch (SQLException e) {
//				System.out.println("Execution of Query Failed!");
//				e.printStackTrace();
//			}
//			System.out.println("Values inserted into the DB successfully!");
//			
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//		}	
//		System.out.println("Connection to DB closed!");
//	}
	/**
	 * Launch the application.
	 */
	public void runEmployeeDetails() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					passenger_details window = new passenger_details();
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
	public passenger_details() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel heading = new JLabel("Add Employe details");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel textPane = new JLabel();
		
		Name_passenger = new JLabel("Employee ID ");
		
		JLabel age = new JLabel("Name ");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel Nationality = new JLabel("Age ");
		
		textField_2 = new JTextField();
		
		Designation = new JLabel("Designation ");
		popupMenu = new JPopupMenu();
		addPopup(frame.getContentPane(), popupMenu);
		JComboBox Designation_choice = new JComboBox();
		Designation_choice.setModel(new DefaultComboBoxModel(new String[] {"Pilot", "Air hostress", "SuperVisor", "Flight maintainer", "Flight Engineer"}));
		
		JButton btnNewButton = new JButton("Add Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		lblNewLabel = new JLabel("AirlineID");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Airline Name");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Name_passenger, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(240)
									.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(age, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(Designation)
									.addComponent(Nationality, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Designation_choice, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, 298, 298, 298))))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(textField_4)))
					.addGap(77))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(353, Short.MAX_VALUE)
					.addComponent(heading)
					.addGap(280))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(308)
					.addComponent(btnNewButton)
					.addContainerGap(320, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(heading, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(Name_passenger)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(age, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Nationality)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Designation)
						.addComponent(Designation_choice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(btnNewButton)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
