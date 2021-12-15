package logindetails_pkg;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window() {
		this.setTitle("Airline Ticket Booking System");
		this.setSize(600,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
// *** NOTE *** : to change the page, insert the appropriate page class in src (will put functionality later).
		this.add(new AdminLoginPage());
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new Window();
		
	}

}
