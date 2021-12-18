package passengermainpage_pkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import JDBCMisc_pkg.JDBC_Creds;
import airlinedetails_pkg.Airline_DetailsDB;
import airportdetails_pkg.Airport_DetailsDB;
import enquirydetails_pkg.Schedule_DetailsDB;
import logindetails_pkg.Passenger_DetailsDB;
import paymentdetails_pkg.Booking_DetailsDB;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowTicketPage extends JFrame implements JDBC_Creds {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtCancelTicket;
	private JTextField textField;
	private JTextField lblPassengerName;
	private JTextField textField_2;
	private JTextField lblPassportNo;
	private JTextField textField_4;
	private JTextField lblCardNumber;
	private JTextField textField_6;
	private JTextField lblCardType;
	private Passenger_DetailsDB p;
	private JLabel lblResult;

	/**
	 * Launch the application.
	 */
	public void runShowTicketPage(Passenger_DetailsDB p1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					p = new Passenger_DetailsDB(p1);
					System.out.println("Blah: " + p.getName());
					ShowTicketPage frame = new ShowTicketPage(p);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private ArrayList<Booking_DetailsDB> getBookingList(Passenger_DetailsDB p){
		ArrayList<Booking_DetailsDB> l = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		try(Connection connection = DriverManager.getConnection(url, user, password);){
			if(connection != null) {
				System.out.println("Connected to PostgreSQL!");
			}
			else {
				System.out.println("Failed to connect to PostgreSQL");
				System.out.println("Something went very wrong!");
			}
			try {
				st = connection.prepareStatement("SELECT * from bookings WHERE passportno = ?");
				st.setString(1, p.getPassportNo());
				// st.setString(1, "123456789");
				rs = st.executeQuery();
				while(rs.next()) {
					/*
					 * public Booking_DetailsDB(int ticketNo, int airlineNo, int airportNo, String passportNo, int scheduleID,
					 * float price)
					 */
					Booking_DetailsDB b = new Booking_DetailsDB(rs.getInt("TicketNo"), rs.getInt("AirlineNo"), rs.getInt("AirportNo"), rs.getString("PassportNo"), rs.getInt("ScheduleID"), rs.getFloat("Price"));
					l.add(b);
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
		return l;
	}
	
	private ArrayList<Airline_DetailsDB> getAirlineList(ArrayList<Booking_DetailsDB> b) {
		ArrayList<Airline_DetailsDB> l = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		for(int i=0;i<b.size();i++) {
			try(Connection connection = DriverManager.getConnection(url, user, password);){
				if(connection != null) {
					System.out.println("Connected to PostgreSQL!");
				}
				else {
					System.out.println("Failed to connect to PostgreSQL");
					System.out.println("Something went very wrong!");
				}
				try {
					st = connection.prepareStatement("SELECT * from Airline_Details WHERE AirlineID = ?");
					st.setInt(1, b.get(i).getAirlineNo());
					rs = st.executeQuery();
					while(rs.next()) {
						Airline_DetailsDB AirlineDetails = new Airline_DetailsDB(rs.getInt("AirlineID"), rs.getString("AirlineName"), rs.getString("AirlineType"), rs.getInt("SeatingCapacity"), rs.getFloat("Price"));
						l.add(AirlineDetails);
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
		}
		return l;
	}
	
	private ArrayList<Airport_DetailsDB> getAirportList(ArrayList<Booking_DetailsDB> b) {
		ArrayList<Airport_DetailsDB> l = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		for(int i=0;i<b.size();i++) {
			try(Connection connection = DriverManager.getConnection(url, user, password);){
				if(connection != null) {
					System.out.println("Connected to PostgreSQL!");
				}
				else {
					System.out.println("Failed to connect to PostgreSQL");
					System.out.println("Something went very wrong!");
				}
				try {
					st = connection.prepareStatement("SELECT * from Airport_Details WHERE AirportID = ?");
					st.setInt(1, b.get(i).getAirportNo());
					rs = st.executeQuery();
					while(rs.next()) {
						Airport_DetailsDB a = new Airport_DetailsDB(rs.getInt("AirportID"),  rs.getString("location"), rs.getString("AirportName"), rs.getInt("NoOfTerminals"));
						l.add(a);
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
		}
		return l;
	}
	
	private ArrayList<Schedule_DetailsDB> getScheduleList(ArrayList<Booking_DetailsDB> b) {
		ArrayList<Schedule_DetailsDB> l = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		for(int i=0;i<b.size();i++) {
			try(Connection connection = DriverManager.getConnection(url, user, password);){
				if(connection != null) {
					System.out.println("Connected to PostgreSQL!");
				}
				else {
					System.out.println("Failed to connect to PostgreSQL");
					System.out.println("Something went very wrong!");
				}
				try {
					st = connection.prepareStatement("SELECT * from schedule WHERE scheduleID = ?");
					st.setInt(1, b.get(i).getScheduleID());
					rs = st.executeQuery();
					while(rs.next()) {
						Schedule_DetailsDB s = new Schedule_DetailsDB(rs.getInt("scheduleID"), rs.getInt("airlineNo"), rs.getInt("airportID"), rs.getDate("dateofdeparture"),
								rs.getString("timeofdeparture"), rs.getString("destination"));
						l.add(s);
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
		}
		return l;
	}
	
	private void populateTicketTable(Passenger_DetailsDB p, JTable table) {
		ArrayList<Booking_DetailsDB> l = getBookingList(p);
		ArrayList<Airline_DetailsDB> arl = getAirlineList(l);
		ArrayList<Airport_DetailsDB> apl = getAirportList(l);
		ArrayList<Schedule_DetailsDB> sl = getScheduleList(l);
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		if(model.getRowCount() != 0) {
			model.setRowCount(0);
			System.out.println("Refreshed!");
		}
		
		Object[] row = new Object[7];
		
		for(int i=0;i<l.size();i++) {
			row[0] = l.get(i).getTicketNo();
			row[1] = arl.get(i).getAirlineName();
			row[2] = apl.get(i).getAirportName();
			row[3] = apl.get(i).getLocation();
			row[4] = sl.get(i).getDateofdeparture();
			row[5] = sl.get(i).getTimeofdeparture();
			row[6] = l.get(i).getPrice();
			model.addRow(row);
		}
	}
	
	private int cancelTicketBooking(Passenger_DetailsDB p, int tid) {
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
				st = connection.prepareStatement("SELECT count(*) from bookings WHERE TicketNo = ? and passportno = ?");
				st.setInt(1, tid);
				st.setString(2, p.getPassportNo());
				// st.setString(2, "123456789");
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("Value not in table");
					return -2;
				}
				
				st = connection.prepareStatement("delete from bookings WHERE TicketNo = ? and passportno = ?");
				st.setInt(1, tid);
				st.setString(2, p.getPassportNo());
				// st.setString(2, "123456789");
				st.execute();
				return 1;
			}
			catch (SQLException e) {
				System.out.println("Execution of Query Failed!");
				e.printStackTrace();
			}
			System.out.println("Delete value from DB successfully!");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		System.out.println("Connection to DB closed!");
		return 0;
	}

	/**
	 * Create the frame.
	 */
	public ShowTicketPage() {}
	
	public ShowTicketPage(Passenger_DetailsDB p) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1185, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Passenger HomeScreen");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 13, 1113, 51);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(510, 105, 655, 413);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ticket ID", "Airline Name", "Airport Name", "Destination", "Date of Departure", "Time of Departure", "Price"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(108);
		table.getColumnModel().getColumn(3).setPreferredWidth(92);
		table.getColumnModel().getColumn(4).setPreferredWidth(127);
		table.getColumnModel().getColumn(5).setPreferredWidth(134);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Cancel Ticket No: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 558, 138, 32);
		contentPane.add(lblNewLabel_1);
		
		txtCancelTicket = new JTextField();
		txtCancelTicket.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCancelTicket.setBounds(154, 564, 170, 22);
		contentPane.add(txtCancelTicket);
		txtCancelTicket.setColumns(10);
		
		lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResult.setBounds(551, 558, 614, 32);
		contentPane.add(lblResult);
		
		JButton btnNewButton = new JButton("Cancel Ticket");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stid = txtCancelTicket.getText();
				if(stid.length() == 0) {
					System.out.println("Value empty");
					lblResult.setText("Please enter the ticketid to cancel");
				}
				else {
					int tid = Integer.parseInt(stid);
					int res = cancelTicketBooking(p, tid);
					if(res == -2) {
						lblResult.setText("Value not found in table, try again");
					}
					else if(res == 1) {
						lblResult.setText("Cancelled ticket, please check with bank for refund");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(360, 562, 148, 25);
		contentPane.add(btnNewButton);
		
		JButton btnRefreshDetails = new JButton("Refresh Details");
		btnRefreshDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTicketTable(p, table);
			}
		});
		btnRefreshDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRefreshDetails.setBounds(169, 475, 199, 43);
		contentPane.add(btnRefreshDetails);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 105, 486, 344);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(49, 13, 126, 23);
		textField.setText("Passenger Name:");
		textField.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		textField.setEditable(false);
		textField.setColumns(10);
		panel.add(textField);
		
		lblPassengerName = new JTextField();
		lblPassengerName.setBounds(255, 13, 219, 23);
		lblPassengerName.setText((String) null);
		lblPassengerName.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPassengerName.setEditable(false);
		lblPassengerName.setColumns(10);
		lblPassengerName.setText(p.getName());
		panel.add(lblPassengerName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(49, 97, 126, 23);
		textField_2.setText("Passport No:");
		textField_2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		lblPassportNo = new JTextField();
		lblPassportNo.setBounds(255, 97, 219, 23);
		lblPassportNo.setText((String) null);
		lblPassportNo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblPassportNo.setEditable(false);
		lblPassportNo.setColumns(10);
		lblPassportNo.setText(p.getPassportNo());
		panel.add(lblPassportNo);
		
		textField_4 = new JTextField();
		textField_4.setBounds(49, 168, 126, 23);
		textField_4.setText("Card Number:");
		textField_4.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		panel.add(textField_4);
		
		lblCardNumber = new JTextField();
		lblCardNumber.setBounds(255, 168, 219, 23);
		lblCardNumber.setText((String) null);
		lblCardNumber.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCardNumber.setEditable(false);
		lblCardNumber.setColumns(10);
		lblCardNumber.setText(p.getCardNumber());
		panel.add(lblCardNumber);
		
		textField_6 = new JTextField();
		textField_6.setBounds(49, 248, 126, 23);
		textField_6.setText("Card Type:");
		textField_6.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		panel.add(textField_6);
		
		lblCardType = new JTextField();
		lblCardType.setBounds(255, 248, 219, 23);
		lblCardType.setText((String) null);
		lblCardType.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		lblCardType.setEditable(false);
		lblCardType.setColumns(10);
		lblCardType.setText(p.getCardType());
		panel.add(lblCardType);
		
		populateTicketTable(p, table);
	}

}
