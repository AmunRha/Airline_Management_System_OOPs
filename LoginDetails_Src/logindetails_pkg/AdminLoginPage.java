package logindetails_pkg;

import javax.swing.*;
import java.awt.*;

class AdminLoginPage extends JPanel {
    private JTextField txtAdministratorLoginPage;
    private String pageThemeColor = "#7CD1B8";
    private JTextField txtUsername;
    private JTextField usernameTextfield;
    private JTextField passwordField;
    private JTextField txtPassword;
    private JLabel enterLoginDeets;

    /**
     * Create the panel.
     */
    public AdminLoginPage() {
        setLayout(null);
        this.setBackground(Color.decode(pageThemeColor));

        Font font1 = new Font("Bahnschrift",Font.BOLD,16);
        Font font2 = new Font("Bahnschrift",Font.BOLD,14);

        txtAdministratorLoginPage = new JTextField();
        txtAdministratorLoginPage.setBackground(this.getBackground());
        txtAdministratorLoginPage.setBorder(BorderFactory.createEmptyBorder());
        txtAdministratorLoginPage.setEditable(false);
        txtAdministratorLoginPage.setFont(new Font("Bahnschrift",Font.BOLD,18));
        txtAdministratorLoginPage.setText("Administrator Login Page");
        txtAdministratorLoginPage.setBounds(184, 10, 233, 31);
        add(txtAdministratorLoginPage);
        txtAdministratorLoginPage.setColumns(10);

        JPanel panel = new JPanel();
        panel.setBounds(80, 100, 430, 130);
        add(panel);
        panel.setLayout(null);

        txtUsername = new JTextField();
        txtUsername.setBorder(BorderFactory.createEmptyBorder());
        txtUsername.setFont(font2);
        txtUsername.setEditable(false);
        txtUsername.setText("USERNAME:");
        txtUsername.setBounds(50, 10, 120, 25);
        panel.add(txtUsername);
        txtUsername.setColumns(10);

        usernameTextfield = new JTextField();
        usernameTextfield.setMargin(new Insets(0,10,0,0));
        usernameTextfield.setFont(new Font("Consolas",Font.PLAIN,14));
        usernameTextfield.setBounds(180, 10, 190, 25);
        panel.add(usernameTextfield);
        usernameTextfield.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setMargin(new Insets(0,10,0,0));
        passwordField.setBounds(180, 45, 190, 25);
        panel.add(passwordField);
        passwordField.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setBorder(BorderFactory.createEmptyBorder());
        txtPassword.setFont(font2);
        txtPassword.setEditable(false);
        txtPassword.setText("PASSWORD:");
        txtPassword.setBounds(50, 46, 120, 25);
        panel.add(txtPassword);
        txtPassword.setColumns(10);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setFocusPainted(false);
        loginButton.setBounds(285, 80, 85, 25);
        panel.add(loginButton);

        enterLoginDeets = new JLabel();
        enterLoginDeets.setFont(font1);
        enterLoginDeets.setBackground(this.getBackground());
        enterLoginDeets.setText("Enter Admin Login:");
        enterLoginDeets.setBounds(80, 50, 160, 40);
        add(enterLoginDeets);

    }
}
