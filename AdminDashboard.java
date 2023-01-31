import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AdminDashboard extends JFrame implements ActionListener{
	private JLabel lslotno, lstatus, ltime, monitor, limage,luser;
	private JTextField slotno, time;
        private ImageIcon img2,icon1;
	private JComboBox<String> status;
	private JButton proceedinsert, proceedsearch, proceeddelete, proceeddisplay, profile, logout;
	private JButton insert, search, delete, display, displayall;
	private JScrollPane displaypane, displayallPane;
	
	User UserName;

	static Slot[] rooms = new Slot[100];
	
	private String[] roomstatus = {"Available", "Reserved"};
	
	public AdminDashboard(User user){
		this.setTitle("Admin Panel");
		this.setSize( 800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
                this.setLocationRelativeTo(null);
               // this.setVisible(true);
                
                img2 = new ImageIcon(getClass().getResource("panel.png"));
                limage= new JLabel(img2);
                limage.setBounds(0,0,800,600);
                this.add(limage);
                
                icon1= new ImageIcon(getClass().getResource("admin2.png"));
                luser= new JLabel(icon1);
                luser.setBounds(40,40,64,64);
                limage.add(luser);
		
		lslotno = new JLabel("Slot No");
		lslotno.setBounds(400,100,150, 20);
		limage.add(lslotno);
		
		slotno = new JTextField();
		slotno.setBounds(510,100,150, 20);
		limage.add(slotno);
		
		lstatus = new JLabel("Status");
		lstatus.setBounds(400,140,150, 20);
		limage.add(lstatus);
		
		status = new JComboBox<>(roomstatus);
		status.setBounds(510,140,150, 20);
		limage.add(status);
		
		ltime = new JLabel("Time (24 hr)");
		ltime.setBounds(400,180,150, 20);
		limage.add(ltime);
		
		time = new JTextField();
		time.setBounds(510,180,150, 20);
		limage.add(time);
		
		monitor = new JLabel();
		monitor.setBounds(340, 300, 400, 150);
		monitor.setOpaque(true);
		monitor.setBackground(new Color(194,238,194));
		monitor.setVisible(false);
		limage.add(monitor);
		
		proceedinsert = new JButton("Insert Slot");
		proceedinsert.setBounds(450,230,150, 20);
                proceedinsert.setBackground(new Color(144,238,144));
		limage.add(proceedinsert);
		proceedinsert.addActionListener(this);
		
		proceedsearch = new JButton("Search Slot");
		proceedsearch.setBounds(450,150,150, 20);
                proceedsearch.setBackground(new Color(144,238,144));
		limage.add(proceedsearch);
		proceedsearch.addActionListener(this);
		
		proceeddelete = new JButton("Delete Slot");
		proceeddelete.setBounds(450,150,150, 20);
                proceeddelete.setBackground(new Color(144,238,144));
		limage.add(proceeddelete);
		proceeddelete.addActionListener(this);
		
		proceeddisplay = new JButton("Display Slot");
		proceeddisplay.setBounds(450,150,150, 20);
                proceeddisplay.setBackground(new Color(144,238,144));
		limage.add(proceeddisplay);
		proceeddisplay.addActionListener(this);
		
		lslotno.setVisible(false);
		slotno.setVisible(false);
		lstatus.setVisible(false);
		status.setVisible(false);
		ltime.setVisible(false);
		time.setVisible(false);
		proceedinsert.setVisible(false);
		proceedsearch.setVisible(false);
		proceeddelete.setVisible(false);
		proceeddisplay.setVisible(false);
		
		insert = new JButton("Insert Slot");
		insert.setBounds(75,200,150, 35);
                insert.setBackground(Color.WHITE);
		limage.add(insert);
		insert.addActionListener(this);
		
		search = new JButton("Search Slot");
		search.setBounds(75,250,150, 35);
                search.setBackground(Color.WHITE);
		limage.add(search);
		search.addActionListener(this);
		
		delete = new JButton("Delete Slot");
		delete.setBounds(75,300,150, 35);
                delete.setBackground(Color.WHITE);
		limage.add(delete);
		delete.addActionListener(this);
		
		display = new JButton("Display Slot");
		display.setBounds(75,350,150, 35);
                display.setBackground(Color.WHITE);
		limage.add(display);
		display.addActionListener(this);
		
		displayall = new JButton("Display All");
		displayall.setBounds(75,400,150, 35);
                displayall.setBackground(Color.WHITE);
		limage.add(displayall);
		displayall.addActionListener(this);
		
		profile = new JButton(user.getUsername().toUpperCase());
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
		displayallPane = new JScrollPane();
	}
	
	public Slot[] getSlots() {
		return rooms.clone();
	}
	
	protected int getTotalSlots() {
		int total = 0;
		for(int i=0; i<rooms.length; i++) {
			if(rooms[i] != null) {
				total++;
			}
		}
		return total;
	}
	
	private boolean isSlotExists(String slotno) {
		boolean found = false;
		
		for(int i=0; i<rooms.length; i++) {
			if(rooms[i] == null) continue;
			if(rooms[i].getSlotno().equals(slotno)) {
				found = true;
				break;
			}
		}
		
		return found;
	}
	
	protected int getroomIndex(String slotno) {
		int index = -1;
		
		for(int i=0; i<rooms.length; i++) {
			if(rooms[i] == null) continue;
			if(rooms[i].getSlotno().equals(slotno)) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	private void insert(Slot room) {
		if(!isSlotExists(room.getSlotno())) {
			for(int i=0; i<rooms.length; i++) {
				if(rooms[i] == null) {
					rooms[i] = room;
					monitor.setText("Slot added successfully");
					break;
				}
			}
		}
		else {
			monitor.setText("Slot no " + room.getSlotno() + " is already exists");
		}
	}
	
	private boolean search(String slotno) {
		boolean found = false;
		found = isSlotExists(slotno);
		return found;
	}
	
	private boolean delete(String slotno) {
		boolean deleted = false;
		for(int i=0; i<rooms.length; i++) {
			if(rooms[i] != null) {
				if(rooms[i].getSlotno().equals(slotno)) {
					rooms[i] = null;
					deleted = true;
					break;
				}
			}
		}
		
		return deleted;
	}
	
	private JTable display(String slotno) {
		Slot room = null;
		int roomindex = this.getroomIndex(slotno);
		if(roomindex == -1) {
			return null;
		}
		
		room = rooms[roomindex];
		String data[][] = new String[1][4];
		String column[]={"Slot No","Status","Time"};
		data[0] = this.processTableData(room);
		JTable table = new JTable(data, column);
		
		return table;
	}
	
	private JTable displayAllSlots() {
		String data[][] = new String[this.getTotalSlots()][4];
		String column[]={"Slot No","Status","Time", "User_Name"};
		
		int index = 0;
		for(int i=0; i<rooms.length; i++) {
			if(rooms[i] != null) {
				data[index++] = this.processTableData(rooms[i]);
			}
		}
		
		JTable table = new JTable(data, column);
		
		return table;
	}
	
	protected String[] processTableData(Slot room) {
		String[] data = new String[4];
		if(room != null) {
			data[0] = room.getSlotno();
			if(room.getStatus()) {
				data[1] = "Available";
			}
			else {
				data[1] = "Reserved";
			}
			data[2] = String.valueOf(room.getTime());
			
			if(!room.getStatus() && room.getUser_Name() != null) {
				data[3] = room.getUser_Name().getUsername();
			}
			else {
				data[3] = "-";
			}
		}
		return data;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == proceedinsert) {
			String newslotno = slotno.getText();
			String newstatus = status.getItemAt(status.getSelectedIndex());
			boolean currentstatus = true;
			if(newstatus.equals("Available")) {
				currentstatus = true;
			}
			else {
				currentstatus = false;
			}
			String timeString = time.getText();
			int newtime = 0;
			if(!timeString.isEmpty()) {
				newtime = Integer.parseInt(timeString);	
			}
			
			if(newslotno.isEmpty() || timeString.isEmpty()) {
				monitor.setText("Please Fillup the fields.");
			}
			else {
				Slot newroom = new Slot(newslotno, currentstatus, newtime);
				this.insert(newroom);
			}
			
			slotno.setText("");
			time.setText("");
		}
		
		if(e.getSource() == proceedsearch) {
			String newslotno = slotno.getText();
			boolean found = false;
			if(newslotno.isEmpty()) {
				monitor.setText("Please Fillup the fields.");
			}
			else {
				found = this.search(newslotno);
				if(found) {
					monitor.setText("Slot no " + newslotno + " is found");
				}
				else {
					monitor.setText("Slot no " + newslotno + " is not found");
				}
			}
			slotno.setText("");
		}
		
		if(e.getSource() == proceeddelete) {
			String newslotno = slotno.getText();
			boolean deleted = false;
			if(newslotno.isEmpty()) {
				monitor.setText("Please Fillup the fields.");
			}
			else {
				deleted = this.delete(newslotno);
				if(deleted) {
					monitor.setText("Slot No " + newslotno + " is deleted");
				}
				else {
					monitor.setText("Slot No " + newslotno + " does not exist");
				}
			}
			slotno.setText("");
		}
		
		if(e.getSource() == proceeddisplay) {
			String newslotno = slotno.getText();
			
			if(newslotno.isEmpty()) {
				monitor.setText("Please Fillup the fields.");
			}
			else {
				JTable table = this.display(newslotno);
				if(table != null) {
					displaypane = new JScrollPane(table);
					displaypane.setBounds(340, 250, 400, 200);
					displaypane.setVisible(true);
					limage.add(displaypane);
					revalidate();
					repaint();
				}
			}
			slotno.setText("");
		}
		
		if(e.getSource() == insert) {
			lslotno.setVisible(true);
			slotno.setVisible(true);
			lstatus.setVisible(true);
			status.setVisible(true);
			ltime.setVisible(true);
			time.setVisible(true);
			proceedinsert.setVisible(true);
			proceedsearch.setVisible(false);
			proceeddisplay.setVisible(false);
			proceeddelete.setVisible(false);
			monitor.setVisible(true);
			monitor.setText("");
			displaypane.setVisible(false);
			displayallPane.setVisible(false);
		}
		if(e.getSource() == search) {
			lslotno.setVisible(true);
			slotno.setVisible(true);
			lstatus.setVisible(false);
			status.setVisible(false);
			ltime.setVisible(false);
			time.setVisible(false);
			proceedsearch.setVisible(true);
			proceedinsert.setVisible(false);
			proceeddisplay.setVisible(false);
			proceeddelete.setVisible(false);
			monitor.setVisible(true);
			monitor.setText("");
			displaypane.setVisible(false);
			displayallPane.setVisible(false);
		}
		if(e.getSource() == delete) {
			lslotno.setVisible(true);
			slotno.setVisible(true);
			lstatus.setVisible(false);
			status.setVisible(false);
			ltime.setVisible(false);
			time.setVisible(false);
			proceeddelete.setVisible(true);
			proceedsearch.setVisible(false);
			proceedinsert.setVisible(false);
			proceeddisplay.setVisible(false);
			monitor.setVisible(true);
			monitor.setText("");
			displaypane.setVisible(false);
			displayallPane.setVisible(false);
		}
		if(e.getSource() == display) {
			lslotno.setVisible(true);
			slotno.setVisible(true);
			lstatus.setVisible(false);
			status.setVisible(false);
			ltime.setVisible(false);
			time.setVisible(false);
			proceeddisplay.setVisible(true);
			proceedsearch.setVisible(false);
			proceedinsert.setVisible(false);
			proceeddelete.setVisible(false);
			monitor.setVisible(false);
			displaypane.setVisible(false);
			displayallPane.setVisible(false);
		}
		
		if(e.getSource() == displayall) {
			lslotno.setVisible(false);
			slotno.setVisible(false);
			lstatus.setVisible(false);
			status.setVisible(false);
			ltime.setVisible(false);
			time.setVisible(false);
			proceeddisplay.setVisible(false);
			proceedsearch.setVisible(false);
			proceedinsert.setVisible(false);
			proceeddelete.setVisible(false);
			monitor.setVisible(false);
			displaypane.setVisible(false);
			displayallPane.setVisible(false);
			
			JTable tableall = this.displayAllSlots();
			if(tableall != null) {
				displayallPane = new JScrollPane(tableall);
				displayallPane.setBounds(340, 50, 400, 300);
				displayallPane.setVisible(true);
				limage.add(displayallPane);
				revalidate();
				repaint();
			}
		}
		
		if(e.getSource() == logout) {
			this.dispose();
			Login login = new Login();
			login.setVisible(true);
		}
		
	}

    }