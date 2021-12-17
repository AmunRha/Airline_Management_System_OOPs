package enquirydetails_pkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

import JDBCMisc_pkg.JDBC_Creds;
import logindetails_pkg.Passenger_DetailsDB;
import paymentdetails_pkg.PaymentPage;

public class EnquiryDetails extends JFrame implements JDBC_Creds {

	private JPanel contentPane;
	private JTable table;
	private Passenger_DetailsDB p;
	
	
	private java.sql.Time getTime(String t){
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa");
		long ms = 0;
		try {
			ms = sdf.parse(t).getTime();
		}
		catch(ParseException p) {
			p.printStackTrace();
		}
		
		return new java.sql.Time(ms);
	}
	
	private Date convDate(String t) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date d = null;
		try {
			d = sdf.parse(t);
		}
		catch(ParseException p) {
			p.printStackTrace();
		}
		
		return d;
	}
	
	private int checkAvailibility(String dest, Date date, String time, JTable sTable) {
		ArrayList<Schedule_DetailsDB> sch = new ArrayList<>();
		
		java.sql.Date datesql = new java.sql.Date(date.getTime());
		java.sql.Time timesql = getTime(time);
		System.out.println("Date: " + datesql);
		System.out.println("Time: " + timesql);
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
				st = connection.prepareStatement("select count(*) from Schedule where dateofdeparture = ? and timeofdeparture = ? and destination = ?");
				st.setDate(1, datesql);
				st.setTime(2, timesql);
				st.setString(3, dest);
				rs = st.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("Value not in table");
					return -3;
				}
				st = connection.prepareStatement("select * from Schedule where dateofdeparture = ? and timeofdeparture = ? and destination = ?");
				st.setDate(1, datesql);
				st.setTime(2, timesql);
				st.setString(3, dest);
				rs = st.executeQuery();
				/*
				 * Schedule_DetailsDB(int scheduleID, int airlineNo, int airportID, Date dateofdeparture,
				   String timeofdeparture, String destination)
				 */
				Schedule_DetailsDB s = new Schedule_DetailsDB();
				if(rs.next()) {
					s = new Schedule_DetailsDB(rs.getInt("scheduleID"), rs.getInt("airlineNo"), rs.getInt("airportID"), rs.getDate("dateofdeparture"),
							rs.getString("timeofdeparture"), rs.getString("destination"));
					sch.add(s);
				}
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
		
		DefaultTableModel model = (DefaultTableModel)sTable.getModel();
		
		if(model.getRowCount() != 0) {
			model.setRowCount(0);
			System.out.println("Refreshed!");
		}
		
		Object[] row = new Object[6];
		
		for(int i=0;i<sch.size();i++) {
			row[0] = sch.get(i).getScheduleID();
			row[1] = sch.get(i).getAirlineNo();
			row[2] = sch.get(i).getAirportID();
			row[3] = sch.get(i).getDateofdeparture();
			row[4] = sch.get(i).getTimeofdeparture();
			row[5] = sch.get(i).getDestination();
			model.addRow(row);
		}
		
		return 0;
	}

	/**
	 * Launch the application.
	 */
	public void runEnquiryDetails(Passenger_DetailsDB p1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					p = new Passenger_DetailsDB(p1);
					EnquiryDetails frame = new EnquiryDetails(p);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private int bookTicket(int id, JTable sTable, Passenger_DetailsDB p) {
		
		DefaultTableModel m = (DefaultTableModel)sTable.getModel();
		
		if(m.getRowCount() == 0) {
			System.out.println("Booking ticket without table view");
			return -1;
		}
		
		int sid=-1, flag = 0, row = -1;
		for(int i=0;i<m.getRowCount();i++) {
			sid = (int) m.getValueAt(i, 0);
			if (id == sid) {
				flag = 1;
				row = i;
			}
		}		
		
		if(flag != 1) {
			System.out.println("Given and expected sch id doesnt match");
			return -2;
		}
		
		int arid = (int) m.getValueAt(row, 1);
		int apid = (int) m.getValueAt(row, 2);
		String d = (String) m.getValueAt(row, 3);
		Date date = convDate(d);
		String time = (String) m.getValueAt(row, 4);
		String dest = (String) m.getValueAt(row, 5);
		
		Schedule_DetailsDB s = new Schedule_DetailsDB();
		s.setScheduleID(sid);
		s.setAirlineNo(arid);
		s.setAirportID(apid);
		s.setDateofdeparture(date);
		s.setTimeofdeparture(time);
		s.setDestination(dest);
		
		
		System.out.println("Moving to payment procedure");
		PaymentPage pm = new PaymentPage();
		System.out.println("Blah: " + p.getName());
		pm.runPaymentPage(p, s);
		
		return 0;
	}

	/**
	 * Create the frame.
	 */
	public EnquiryDetails() {}
	public EnquiryDetails(Passenger_DetailsDB p) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel("Date of journey");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDate.setBounds(12, 136, 133, 16);
		contentPane.add(lblDate);
		
		JLabel lblNewLabel_1 = new JLabel("Enquiry Center for Tickets");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(12, 40, 660, 16);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(157, 136, 100, 22);
		contentPane.add(dateChooser);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDestination.setBounds(12, 183, 133, 16);
		contentPane.add(lblDestination);
		
		JTextArea txtDestination = new JTextArea();
		txtDestination.setBounds(157, 181, 178, 22);
		contentPane.add(txtDestination);
		
		JLabel lblTime = new JLabel("Time of departure");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTime.setBounds(12, 239, 133, 16);
		contentPane.add(lblTime);
		
		TimePicker timePicker = new TimePicker();
		timePicker.setBounds(157, 237, 90, 23);
		contentPane.add(timePicker);
		
		JLabel lblResult = new JLabel("");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResult.setBounds(12, 90, 562, 16);
		contentPane.add(lblResult);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(370, 136, 600, 261);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Schedule ID", "Airline ID", "Airport ID", "Date of Departure", "Time of Departure", "Destination"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(96);
		table.getColumnModel().getColumn(3).setPreferredWidth(129);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);
		scrollPane.setViewportView(table);
		
		JButton btnCheck = new JButton("Check Availibility");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dest = txtDestination.getText();
				Date date = dateChooser.getDate();
				String time = timePicker.getText();
				
				if(dest.length() == 0 || date == null || time.length() == 0) {
					System.out.println("Value empty");
					lblResult.setText("Please enter all the values");
				}
				else {
					int res = checkAvailibility(dest, date, time, table);
					if(res == -3) {
						lblResult.setText("Schedule not found, please enter a new schedule");
					}	
				}
			}
		});
		btnCheck.setBounds(77, 329, 186, 36);
		contentPane.add(btnCheck);
		
		JLabel lblScheduleId = new JLabel("Schedule ID");
		lblScheduleId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblScheduleId.setBounds(12, 463, 133, 16);
		contentPane.add(lblScheduleId);
		
		JTextArea txtSchID = new JTextArea();
		txtSchID.setBounds(157, 461, 178, 22);
		contentPane.add(txtSchID);
		
		JButton btnBookTicket = new JButton("Book Ticket");
		btnBookTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tid = txtSchID.getText();
				if(tid.length() == 0) {
					System.out.println("Value empty");
					lblResult.setText("To book a ticket, enter the schedule ID");
				}
				else {
					int id = Integer.parseInt(tid);
					System.out.println("Blah: " + p.getName());
					int res = bookTicket(id, table, p);
					if(res == -1) {
						lblResult.setText("Please check the schedule before booking");
					}
					else if(res == -2) {
						lblResult.setText("Please enter a schedule ID which is present in the table");
					}
				}
			}
		});
		btnBookTicket.setBounds(77, 518, 186, 36);
		contentPane.add(btnBookTicket);
	}
}
