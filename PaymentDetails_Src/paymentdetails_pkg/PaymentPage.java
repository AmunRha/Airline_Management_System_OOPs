package paymentdetails_pkg;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import enquirydetails_pkg.Schedule_DetailsDB;

public class PaymentPage extends JFrame {
    private ImageIcon image = new ImageIcon("weaccept.png");
    private JTextField txtPaymentPage;
    private JTextField txtCreditCardInfo;
    private String pageThemeColor = "#7CD1B8";
    private JTextField txtCardNumber;
    private JTextField txtCardType;
    private Schedule_DetailsDB s;
    private JTextField lblCardNumber;
    private JTextField lblCardType;
    private JTextField txtPassengerName;
    private JTextField lblPassengerName;
    private JTextField txtPassportNo;
    private JTextField lblPassportNo;
    private JTextField txtDestination;
    private JTextField lblDestination;
    private JTextField txtAirportName;
    private JTextField lblAirportName;
    private JTextField txtDateOfDeparture;
    private JTextField lblDate;
    private JTextField txtTimeOfDeparture;
    private JTextField lblTime;
    private JTextField txtTotalPrice;
    private JTextField lblPrice;
    private JTextField txtAirlineName;
    private JTextField lblAirline;

    
    public void runPaymentPage(Schedule_DetailsDB sch) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentPage frame = new PaymentPage();
					s = sch;
					frame.setSize(800, 800);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    /**
     * Create the panel.
     */
    
    public PaymentPage() {
        getContentPane().setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        txtPaymentPage = new JTextField();
        txtPaymentPage.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        txtPaymentPage.setBackground(this.getBackground());
        txtPaymentPage.setBorder(BorderFactory.createEmptyBorder());
        txtPaymentPage.setEditable(false);
        txtPaymentPage.setText("Payment Page");
        txtPaymentPage.setBounds(177, 10, 231, 40);
        getContentPane().add(txtPaymentPage);
        txtPaymentPage.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(100, 170, 550, 478);
        getContentPane().add(panel);
        panel.setLayout(null);

        txtCardNumber = new JTextField();
        txtCardNumber.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtCardNumber.setText("Card Number:");
        txtCardNumber.setEditable(false);
        txtCardNumber.setBounds(10, 94, 136, 29);
        panel.add(txtCardNumber);
        txtCardNumber.setColumns(10);

        txtCardType = new JTextField();
        txtCardType.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtCardType.setEditable(false);
        txtCardType.setText("Card Type:");
        txtCardType.setBounds(10, 136, 136, 29);
        panel.add(txtCardType);
        txtCardType.setColumns(10);
        
        lblCardNumber = new JTextField();
        lblCardNumber.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblCardNumber.setEditable(false);
        lblCardNumber.setColumns(10);
        lblCardNumber.setBounds(205, 94, 333, 29);
        panel.add(lblCardNumber);
        
        lblCardType = new JTextField();
        lblCardType.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblCardType.setEditable(false);
        lblCardType.setColumns(10);
        lblCardType.setBounds(205, 136, 333, 29);
        panel.add(lblCardType);
        
        txtPassengerName = new JTextField();
        txtPassengerName.setText("Passenger Name:");
        txtPassengerName.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtPassengerName.setEditable(false);
        txtPassengerName.setColumns(10);
        txtPassengerName.setBounds(10, 10, 136, 29);
        panel.add(txtPassengerName);
        
        lblPassengerName = new JTextField();
        lblPassengerName.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblPassengerName.setEditable(false);
        lblPassengerName.setColumns(10);
        lblPassengerName.setBounds(205, 10, 333, 29);
        panel.add(lblPassengerName);
        
        txtPassportNo = new JTextField();
        txtPassportNo.setText("Passport No:");
        txtPassportNo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtPassportNo.setEditable(false);
        txtPassportNo.setColumns(10);
        txtPassportNo.setBounds(10, 52, 136, 29);
        panel.add(txtPassportNo);
        
        lblPassportNo = new JTextField();
        lblPassportNo.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblPassportNo.setEditable(false);
        lblPassportNo.setColumns(10);
        lblPassportNo.setBounds(205, 52, 333, 29);
        panel.add(lblPassportNo);
        
        txtDestination = new JTextField();
        txtDestination.setText("Destination:");
        txtDestination.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtDestination.setEditable(false);
        txtDestination.setColumns(10);
        txtDestination.setBounds(10, 178, 136, 29);
        panel.add(txtDestination);
        
        lblDestination = new JTextField();
        lblDestination.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblDestination.setEditable(false);
        lblDestination.setColumns(10);
        lblDestination.setBounds(205, 178, 333, 29);
        panel.add(lblDestination);
        
        txtAirportName = new JTextField();
        txtAirportName.setText("Airport Name:");
        txtAirportName.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtAirportName.setEditable(false);
        txtAirportName.setColumns(10);
        txtAirportName.setBounds(10, 220, 136, 29);
        panel.add(txtAirportName);
        
        lblAirportName = new JTextField();
        lblAirportName.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblAirportName.setEditable(false);
        lblAirportName.setColumns(10);
        lblAirportName.setBounds(205, 220, 333, 29);
        panel.add(lblAirportName);
        
        txtDateOfDeparture = new JTextField();
        txtDateOfDeparture.setText("Date of departure:");
        txtDateOfDeparture.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtDateOfDeparture.setEditable(false);
        txtDateOfDeparture.setColumns(10);
        txtDateOfDeparture.setBounds(10, 262, 136, 29);
        panel.add(txtDateOfDeparture);
        
        lblDate = new JTextField();
        lblDate.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblDate.setEditable(false);
        lblDate.setColumns(10);
        lblDate.setBounds(205, 262, 333, 29);
        panel.add(lblDate);
        
        txtTimeOfDeparture = new JTextField();
        txtTimeOfDeparture.setText("Time of departure:");
        txtTimeOfDeparture.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtTimeOfDeparture.setEditable(false);
        txtTimeOfDeparture.setColumns(10);
        txtTimeOfDeparture.setBounds(10, 304, 136, 29);
        panel.add(txtTimeOfDeparture);
        
        lblTime = new JTextField();
        lblTime.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblTime.setEditable(false);
        lblTime.setColumns(10);
        lblTime.setBounds(205, 304, 333, 29);
        panel.add(lblTime);
        
        txtTotalPrice = new JTextField();
        txtTotalPrice.setText("Total Price:");
        txtTotalPrice.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtTotalPrice.setEditable(false);
        txtTotalPrice.setColumns(10);
        txtTotalPrice.setBounds(10, 388, 136, 29);
        panel.add(txtTotalPrice);
        
        lblPrice = new JTextField();
        lblPrice.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblPrice.setEditable(false);
        lblPrice.setColumns(10);
        lblPrice.setBounds(205, 388, 333, 29);
        panel.add(lblPrice);
        
        txtAirlineName = new JTextField();
        txtAirlineName.setText("Airline Name:");
        txtAirlineName.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtAirlineName.setEditable(false);
        txtAirlineName.setColumns(10);
        txtAirlineName.setBounds(10, 346, 136, 29);
        panel.add(txtAirlineName);
        
        lblAirline = new JTextField();
        lblAirline.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        lblAirline.setEditable(false);
        lblAirline.setColumns(10);
        lblAirline.setBounds(205, 346, 333, 29);
        panel.add(lblAirline);

        txtCreditCardInfo = new JTextField();
        txtCreditCardInfo.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        txtCreditCardInfo.setText("Confirm payment:");
        txtCreditCardInfo.setBounds(100, 100, 194, 40);
        getContentPane().add(txtCreditCardInfo);
        txtCreditCardInfo.setColumns(10);
        
                JButton ConfirmButton = new JButton("Confirm");
                ConfirmButton.setBounds(554, 661, 96, 29);
                getContentPane().add(ConfirmButton);
                ConfirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                ConfirmButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
                ConfirmButton.setFocusPainted(false);

    }
}
