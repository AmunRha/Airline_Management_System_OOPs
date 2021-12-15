package logindetails_pkg;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

public class UserLoginPage extends JPanel {
    private JTextField txtPassengerLoginPage;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JLabel bottomText;
    private JButton registerButton;
    private JLabel bottomText2;
    private String pageThemeColor = "#7CD1B8";
    private JButton loginButton;

    /**
     * Create the panel.
     */
    public UserLoginPage() {
        setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        Font txtFont = new Font("Bahnschrift", Font.BOLD, 16);

        txtPassengerLoginPage = new JTextField();
        txtPassengerLoginPage.setBackground(this.getBackground());
        txtPassengerLoginPage.setBorder(BorderFactory.createEmptyBorder());
        txtPassengerLoginPage.setEditable(false);
        txtPassengerLoginPage.setFont(new Font("Bahnschrift", Font.BOLD, 18));
        txtPassengerLoginPage.setText("Passenger Login Page");
        txtPassengerLoginPage.setBounds(199, 10, 207, 55);
        add(txtPassengerLoginPage);
        txtPassengerLoginPage.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(69, 114, 444, 151);
        add(panel);
        panel.setLayout(null);

        txtUsername = new JTextField();
        txtUsername.setEditable(false);
        txtUsername.setFont(txtFont);
        txtUsername.setText("Username:");
        txtUsername.setBounds(10, 10, 126, 34);
        panel.add(txtUsername);
        txtUsername.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setEditable(false);
        txtPassword.setFont(txtFont);
        txtPassword.setText("Password:");
        txtPassword.setBounds(10, 63, 126, 34);
        panel.add(txtPassword);
        txtPassword.setColumns(10);

        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Consolas",Font.PLAIN,15));
        usernameTextField.setMargin(new Insets(0,10,0,0));
        usernameTextField.setBounds(198, 11, 236, 34);
        panel.add(usernameTextField);
        usernameTextField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setMargin(new Insets(0,10,0,0));
        passwordField.setBounds(198, 63, 236, 34);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setFocusPainted(false);
        loginButton.setFont(txtFont);
        loginButton.setBounds(349, 107, 85, 34);
        panel.add(loginButton);

        bottomText = new JLabel("Don't have an account? Click ");
        bottomText.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        bottomText.setBounds(69, 288, 238, 23);
        add(bottomText);

        registerButton = new JButton("here");
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Bahnschrift",Font.PLAIN,16));
        registerButton.setMargin(new Insets(0,0,0,0));
        registerButton.setBounds(304, 288, 53, 23);
        add(registerButton);

        bottomText2 = new JLabel("to register!");
        bottomText2.setFont(new Font("Bahnschrift", Font.PLAIN, 18));
        bottomText2.setBounds(360, 288, 207, 23);
        add(bottomText2);

    }
}
