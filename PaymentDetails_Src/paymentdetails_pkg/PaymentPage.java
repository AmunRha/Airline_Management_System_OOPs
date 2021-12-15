package paymentdetails_pkg;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentPage extends JPanel {
    private ImageIcon image = new ImageIcon("weaccept.png");
    private JTextField txtPaymentPage;
    private JTextField txtCreditCardInfo;
    private JLabel weAccept;
    private String pageThemeColor = "#7CD1B8";
    private JTextField txtCardNumber;
    private JTextField textField;
    private JTextField txtExpirationdate;
    private JPanel datePickerPanel;

    /**
     * Create the panel.
     */
    public PaymentPage() {
        setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        txtPaymentPage = new JTextField();
        txtPaymentPage.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        txtPaymentPage.setBackground(this.getBackground());
        txtPaymentPage.setBorder(BorderFactory.createEmptyBorder());
        txtPaymentPage.setEditable(false);
        txtPaymentPage.setText("Payment Page");
        txtPaymentPage.setBounds(177, 10, 231, 40);
        add(txtPaymentPage);
        txtPaymentPage.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(100, 170, 400, 269);
        add(panel);
        panel.setLayout(null);

        weAccept = new JLabel(image);
        weAccept.setBounds(10, 10, 282, 68);
        panel.add(weAccept);

        txtCardNumber = new JTextField();
        txtCardNumber.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtCardNumber.setText("Card Number:");
        txtCardNumber.setEditable(false);
        txtCardNumber.setBounds(10, 97, 112, 29);
        panel.add(txtCardNumber);
        txtCardNumber.setColumns(10);

        textField = new JTextField();
        textField.setBounds(132, 96, 140, 29);
        panel.add(textField);
        textField.setColumns(10);

        txtExpirationdate = new JTextField();
        txtExpirationdate.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
        txtExpirationdate.setEditable(false);
        txtExpirationdate.setText("Expiration Date:");
        txtExpirationdate.setBounds(10, 136, 112, 29);
        panel.add(txtExpirationdate);
        txtExpirationdate.setColumns(10);

        datePickerPanel = new JPanel();
        datePickerPanel.setBounds(132, 136, 140, 29);
        datePickerPanel.setLayout(null);
        JDateChooser chooser = new JDateChooser();
        chooser.setBounds(0, 0, 140, 29);
        chooser.setLocale(Locale.US);

        //datePickerPanel.add(new JLabel("Date of Birth:"));
        datePickerPanel.add(chooser);
        panel.add(datePickerPanel);

        JButton submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        submitButton.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        submitButton.setFocusPainted(false);
        submitButton.setBounds(176, 230, 96, 29);
        panel.add(submitButton);

        txtCreditCardInfo = new JTextField();
        txtCreditCardInfo.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
        txtCreditCardInfo.setText("Enter Credit Card Info:");
        txtCreditCardInfo.setBounds(100, 100, 194, 40);
        add(txtCreditCardInfo);
        txtCreditCardInfo.setColumns(10);

    }
}
