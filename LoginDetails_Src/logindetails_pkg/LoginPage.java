package logindetails_pkg;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPage extends JPanel {
    private JTextField pageTitle;
    ImageIcon image = new ImageIcon("airplane_pic.png");
    private JTextField txtChooseLoginOption;
    private String pageThemeColor = "#7CD1B8";
    /**
     * Create the panel.
     */
    public LoginPage() {
        setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        Font buttonFont = new Font("Consolas",Font.PLAIN,16);

        pageTitle = new JTextField();
        pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pageTitle.setEditable(false);
        pageTitle.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        pageTitle.setText("Welcome to Amrita Tours and Travels !");
        pageTitle.setBounds(12, 10, 579, 88);
        pageTitle.setBackground(Color.decode(pageThemeColor));
        add(pageTitle);
        pageTitle.setColumns(10);

        JLabel lblNewLabel = new JLabel(image);
        lblNewLabel.setBounds(419, 10, 134, 88);
        add(lblNewLabel);

        JPanel loginOptionPanel = new JPanel();
        loginOptionPanel.setBounds(120, 168, 343, 276);
        add(loginOptionPanel);
        loginOptionPanel.setLayout(null);

        txtChooseLoginOption = new JTextField();
        txtChooseLoginOption.setEditable(false);
        txtChooseLoginOption.setFont(new Font("Bahnschrift",Font.PLAIN,17));
        txtChooseLoginOption.setText("Choose Login Option:");
        txtChooseLoginOption.setBounds(10, 5, 323, 43);
        loginOptionPanel.add(txtChooseLoginOption);
        txtChooseLoginOption.setColumns(10);

        JButton userButton = new JButton("User");
        userButton.setFocusPainted(false);
        userButton.setFont(buttonFont);
        userButton.setBounds(10, 58, 133, 27);
        loginOptionPanel.add(userButton);
        //loginOptionPanel.setBackground(Color.decode(pageThemeColor));

        JButton adminButton = new JButton("Administrator");
        adminButton.setFocusPainted(false);
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        adminButton.setFont(buttonFont);
        adminButton.setBounds(153, 58, 180, 27);
        loginOptionPanel.add(adminButton);


    }
}
