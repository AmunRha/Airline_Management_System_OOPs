package passengermainpage_pkg;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import enquirydetails_pkg.EnquiryDetails;
import logindetails_pkg.Passenger_DetailsDB;

public class PassengerMainPage extends JFrame {

	private JPanel contentPane;
	private Passenger_DetailsDB p;

	/**
	 * Launch the application.
	 */
	public void runPassengerMainPage(Passenger_DetailsDB p1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					p = new Passenger_DetailsDB(p1);
					PassengerMainPage frame = new PassengerMainPage(p);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PassengerMainPage() {}

	/**
	 * Create the frame.
	 */
	public PassengerMainPage(Passenger_DetailsDB p) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1043, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Show My Tickets");
		btnNewButton.setBounds(225, 245, 204, 42);
		contentPane.add(btnNewButton);
		
		JButton btnEnquireAndBook = new JButton("Enquire and Book Tickets");
		btnEnquireAndBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnquiryDetails en = new EnquiryDetails();
				en.runEnquiryDetails(p);
			}
		});
		btnEnquireAndBook.setBounds(585, 245, 204, 42);
		contentPane.add(btnEnquireAndBook);
		
		JLabel lblNewLabel = new JLabel("Passengers Home Screen");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
		lblNewLabel.setBounds(12, 13, 1011, 48);
		contentPane.add(lblNewLabel);
	}

}
