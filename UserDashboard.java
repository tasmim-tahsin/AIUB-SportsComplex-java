ddddddddddddddddddddddddddimport java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class UserDashboard extends JFrame implements ActionListener{
	
	private JButton displayvacantrooms, bookroom, proceedBook, profile, logout;
	private JLabel lslotno, message, limage,luser;
	private JTextField slotno;
        private ImageIcon img2,icon1;
	private Slot[] rooms;
	private JScrollPane displaypane;
	User UserName;
	AdminDashboard frame;
	
	public UserDashboard() {}
	
	public UserDashboard(User user) {
            
		this.setTitle("UserDashboard");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		this.UserName = user;
                
                img2 = new ImageIcon(getClass().getResource("panel.png"));
                limage= new JLabel(img2);
                limage.setBounds(0,0,800,600);
                this.add(limage);
                
                icon1= new ImageIcon(getClass().getResource("user2.png"));
                luser= new JLabel(icon1);
                luser.setBounds(40,40,64,64);
                limage.add(luser);
		
		displayvacantrooms = new JButton("Available Slots");
		displayvacantrooms.setBounds(75,200,150, 35);
                displayvacantrooms.setBackground(Color.WHITE);
		limage.add(displayvacantrooms);
		displayvacantrooms.addActionListener(this);
		
		bookroom = new JButton("Book Slot");
		bookroom.setBounds(75,300,150, 35);
                bookroom.setBackground(Color.WHITE);
		limage.add(bookroom);
		bookroom.addActionListener(this);
		
		lslotno = new JLabel("Slot No");
		lslotno.setBounds(380,230,150, 20);
		lslotno.setVisible(false);
		limage.add(lslotno);
		
		slotno = new JTextField();
		slotno.setBounds(450,230,220, 30);
		slotno.setVisible(false);
		limage.add(slotno);
		
		proceedBook = new JButton("Book Now");
		proceedBook.setBounds(450,280,150, 20);
                proceedBook.setBackground(new Color(144,238,144));
		proceedBook.setVisible(false);
		limage.add(proceedBook);
		proceedBook.addActionListener(this);
		
		message = new JLabel();
		message.setBounds(450, 140, 310, 50);
		message.setFont(new Font("Serif", Font.BOLD, 20));
		limage.add(message);
		
		profile = new JButton("MR "+user.getUsername().toUpperCase());
		profile.setBounds(150,60,100, 20);
		profile.setBackground(new Color(144,238,144));
		limage.add(profile);
		//profile.addActionListener(this);
		
		logout = new JButton("Logout");
		logout.setBounds(110,520,80, 20);
                logout.setBackground(Color.WHITE);
                
		limage.add(logout);
		logout.addActionListener(this);
		
		displaypane = new JScrollPane();
		
		frame = new AdminDashboard(user);
		rooms = AdminDashboard.rooms;
	}
	
	private JTable displayAvailableSlots() {
		String data[][] = new String[frame.getTotalSlots()][3];
		String column[]={"Slot No","Status","Time"};
		
		int index = 0;
		for(int i=0; i<rooms.length; i++) {
			if(rooms[i] != null) {
				if(rooms[i].getStatus()) {
					data[index++] = frame.processTableData(rooms[i]);
				}
			}
		}
		
		JTable table = new JTable(data, column);
                
		
		return table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == proceedBook) {
			String newslotno = slotno.getText(); 
			
			if(newslotno.isEmpty()) {
				message.setForeground(Color.red);
				message.setText("Please fillup the field");
			}
			else {
				int index = frame.getroomIndex(newslotno);
				if(index != -1) {
					if(rooms[index] != null) {
						if(rooms[index].getStatus()) {
							rooms[index].setStatus(false);
							rooms[index].assignto(this.UserName);
							message.setForeground(Color.GREEN);
							message.setText("Slot No " + newslotno + " is booked successfully");
						}
						else {
							message.setForeground(Color.red);
							message.setText("Slot No " + newslotno + " is already booked");
						}
					}
				}
				else {
					message.setForeground(Color.red);
					message.setText("Slot No " + newslotno + " doesnot exist");
				}
			}
			slotno.setText("");
		}
		
		if(e.getSource() == displayvacantrooms) {
			lslotno.setVisible(false);
			slotno.setVisible(false);
			proceedBook.setVisible(false);
			displaypane.setVisible(false);
			
			JTable tableallvacatns = this.displayAvailableSlots();
			if(tableallvacatns != null) {
				displaypane = new JScrollPane(tableallvacatns);
				displaypane.setBounds(340, 50, 400, 480);
				displaypane.setVisible(true);
				limage.add(displaypane);
				revalidate();
				repaint();
			}
		}
		
		if(e.getSource() == bookroom) {
			lslotno.setVisible(true);
			slotno.setVisible(true);
			proceedBook.setVisible(true);
			displaypane.setVisible(false);
		}
		
		if(e.getSource() == logout) {
			this.dispose();
			Login login = new Login();
			login.setVisible(true);
		}
		
	}

}
