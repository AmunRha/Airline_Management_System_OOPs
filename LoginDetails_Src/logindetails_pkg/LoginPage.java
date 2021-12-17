package logindetails_pkg;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import airlinedetails_pkg.AddAirlineDetails;

public class LoginPage extends JFrame {
    private JTextField pageTitle;
    ImageIcon image = new ImageIcon("airplane_pic.png");
    private JTextField txtChooseLoginOption;
    private String pageThemeColor = "#7CD1B8";
    /**
     * Create the panel.
     */
    
    public void runLoginPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setSize(600,600);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public LoginPage() {
        getContentPane().setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        Font buttonFont = new Font("Consolas",Font.PLAIN,16);

        pageTitle = new JTextField();
        pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pageTitle.setEditable(false);
        pageTitle.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        pageTitle.setText("Welcome to Amrita Tours and Travels !");
        pageTitle.setBounds(12, 10, 579, 88);
        pageTitle.setBackground(Color.decode(pageThemeColor));
        getContentPane().add(pageTitle);
        pageTitle.setColumns(10);

        JLabel lblNewLabel = new JLabel(image);
        lblNewLabel.setBounds(419, 10, 134, 88);
        getContentPane().add(lblNewLabel);

        JPanel loginOptionPanel = new JPanel();
        loginOptionPanel.setBounds(120, 168, 343, 276);
        getContentPane().add(loginOptionPanel);
        loginOptionPanel.setLayout(null);

        txtChooseLoginOption = new JTextField();
        txtChooseLoginOption.setEditable(false);
        txtChooseLoginOption.setFont(new Font("Bahnschrift",Font.PLAIN,17));
        txtChooseLoginOption.setText("Choose Login Option:");
        txtChooseLoginOption.setBounds(10, 5, 323, 43);
        loginOptionPanel.add(txtChooseLoginOption);
        txtChooseLoginOption.setColumns(10);

        JButton signup = new JButton("Sign Up");
        signup.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.out.println("Register new user clicked");
        		RegisterPage r = new RegisterPage();
        		r.runRegisterPage();
        	}
        });
        signup.setFocusPainted(false);
        signup.setFont(buttonFont);
        signup.setBounds(10, 58, 133, 27);
        loginOptionPanel.add(signup);
        //loginOptionPanel.setBackground(Color.decode(pageThemeColor));

        JButton signin = new JButton("Sign In");
        signin.setFocusPainted(false);
        signin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Login clicked");
            	UserLoginPage u = new UserLoginPage();
            	u.runUserLoginPage();
            }
        });
        signin.setFont(buttonFont);
        signin.setBounds(153, 58, 180, 27);
        loginOptionPanel.add(signin);


    }
}
