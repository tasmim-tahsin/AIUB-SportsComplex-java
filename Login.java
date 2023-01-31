import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	
	private JLabel lusername, lpassword, message,limage, welcome;
        private ImageIcon img2;
	private JTextField username;
	private JPasswordField password;
	private JButton login, signup;
	
	AdminDashboard frame;
        UserDashboard dashboard;
	
	protected User[] users = new User[100];
	protected User admin = new User("admin", "admin");
	protected User user = new User("test", "123");
	
	Login(){
		this.setTitle("Login");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		users[0] = admin;
		users[1] = user;
                
                Font f1= new Font("Poppins",Font.BOLD,35);
                Font f2= new Font("Poppins",Font.BOLD,15);
                Font f3= new Font("Poppins",Font.BOLD,16);
                
                img2 = new ImageIcon(getClass().getResource("BgFinal.png"));
                limage= new JLabel(img2);
                limage.setBounds(0,0,800,600);
                this.add(limage);
                
                welcome= new JLabel("Log to Play");
                welcome.setBounds(350,135,300,50);
                welcome.setFont(f1);
                limage.add(welcome);
		
		message = new JLabel();
		message.setBounds(350, 355, 320, 20);
		limage.add(message);
		
		lusername = new JLabel("Username");
		lusername.setBounds(350, 203, 150, 40);
                lusername.setFont(f2);
		limage.add(lusername);
		
		username = new JTextField();
		username.setBounds(350, 240, 300, 30);
		limage.add(username);
		
		lpassword = new JLabel("Password");
		lpassword.setBounds(350, 265, 150, 40);
                lpassword.setFont(f2);
		limage.add(lpassword);
		
		password = new JPasswordField();
		password.setBounds(350, 300, 300, 30);
		limage.add(password);
		
		login = new JButton("Login");
		login.setBounds(350, 410, 300, 30);
                login.setBackground(new Color(144,238,144));
                login.setFont(f2);
		limage.add(login);
		login.addActionListener(this);
		
		signup = new JButton("Signup");
		signup.setBounds(655, 20, 100, 25);
                signup.setBackground(new Color(144,238,144));
                signup.setFont(f2);
		limage.add(signup);
		signup.addActionListener(this);
		
	}
	
	private boolean isCredentialsMatched(String username, String password) {
		
		for(int i=0; i<users.length; i++) {
			if(users[i] != null) {
				if(users[i].getUsername().equals(username) && users[i].getpassword().equals(password)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean isAdmin(String username, String password) {
		if(username.equals("admin") && password.equals("admin")) {
			return true;
		}
		return false;
	}
	
	private boolean createUser(User user) {
		if(isCredentialsMatched(user.getUsername(), user.getpassword())) {
			return false;
		}
		for(int i=0; i<users.length; i++) {
			if(users[i] == null) {
				users[i] = user;
				return true;
			}
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == signup) {
			String name = username.getText();
			String pass = password.getText();
			
			if(name.isEmpty() || pass.isEmpty()) {
				message.setForeground(Color.red);
				message.setText("Please fillup username and password");
				message.setVisible(true);
			}
			else {
				User newuser = new User(name, pass);
				if(this.createUser(newuser)) {
					message.setForeground(Color.green);
					message.setText("Account created successfully. You can login now");
				}
				else {
					message.setForeground(Color.red);
					message.setText("User already exists.");
				}
			}
		}
		
		if(e.getSource() == login) {
			String name = username.getText();
			String pass = password.getText();
			
			User user = new User(name, pass);
			
			if(name.isEmpty() || pass.isEmpty()) {
				message.setText("Please fillup username and password");
				message.setVisible(true);
			}
			else {
				if(isCredentialsMatched(name, pass)) {
					if(isAdmin(name, pass)) {
						this.dispose();
						frame = new AdminDashboard(user);
						frame.setVisible(true);
					}
					else {
						this.dispose();
						dashboard = new UserDashboard(user);
						dashboard.setVisible(true);
					}
				}
			}
		}
		
	}

}
