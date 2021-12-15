package airlinedetails_pkg;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import java.sql.*;

import JDBCMisc_pkg.JDBC_Creds;

public class AirlineDetails extends JFrame implements JDBC_Creds{

	private JPanel contentPane;
	private JTable AirlineTable;
	
	
	private ArrayList<Airline_DetailsDB> getAirlineList(){
		ArrayList<Airline_DetailsDB> AirlineList = new ArrayList<>();
		
		String query = "select * from Airline_Details";
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
					Airline_DetailsDB AirlineDetails = new Airline_DetailsDB(rs.getInt("AirlineID"), rs.getString("AirlineName"), rs.getString("AirlineType"), rs.getInt("SeatingCapacity"), rs.getFloat("Price"));
					AirlineList.add(AirlineDetails);
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
		return AirlineList;
	}
	
	private void populateAirlineTable(JTable ArTble) {
		ArrayList<Airline_DetailsDB> l = getAirlineList();
		DefaultTableModel model = (DefaultTableModel)ArTble.getModel();
		
		if(model.getRowCount() != 0) {
			model.setRowCount(0);
			System.out.println("Refreshed!");
		}
		
		Object[] row = new Object[5];
		
		for(int i=0;i<l.size();i++) {
			row[0] = l.get(i).getAirlineNo();
			row[1] = l.get(i).getAirlineName();
			row[2] = l.get(i).getAirlineType();
			row[3] = l.get(i).getSeatingCapacity();
			row[4] = l.get(i).getPrice();
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
					AirlineDetails frame = new AirlineDetails();
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
	public AirlineDetails() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAirlineTitle = new JLabel("Airline Updation Centre");
		lblAirlineTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblAirlineTitle.setFont(new Font("Monospaced", Font.BOLD, 28));
		
		JButton btnAddAirline = new JButton("Add Airline Details");
		btnAddAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddAirlineDetails window = new AddAirlineDetails();
				window.runAddAirlineDetails();
			}
		});
		
		JButton btnEditAirline = new JButton("Edit Airline Details");
		btnEditAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditAirlineDetails window = new EditAirlineDetails();
				window.runAddAirlineDetails();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRefresh = new JButton("Refresh");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblAirlineTitle, GroupLayout.DEFAULT_SIZE, 1052, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEditAirline, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddAirline, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAirlineTitle, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(77)
							.addComponent(btnAddAirline, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnEditAirline, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		AirlineTable = new JTable();
		AirlineTable.setFont(new Font("Arial", Font.PLAIN, 16));
		AirlineTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Airline ID", "Airline Name", "Airline Type", "Seating Capacity", "Price"
			}
		));
		AirlineTable.getColumnModel().getColumn(1).setPreferredWidth(136);
		AirlineTable.getColumnModel().getColumn(2).setPreferredWidth(111);
		AirlineTable.getColumnModel().getColumn(3).setPreferredWidth(121);
		AirlineTable.getColumnModel().getColumn(4).setPreferredWidth(86);
		
		populateAirlineTable(AirlineTable);
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populateAirlineTable(AirlineTable);
			}
		});
		
		scrollPane.setViewportView(AirlineTable);
		contentPane.setLayout(gl_contentPane);
	}
}
