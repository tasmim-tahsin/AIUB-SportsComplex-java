
public class Slot {
	private String slotno;
	private boolean status;
	private int time;
	User UserName;
	
	Slot(String slotno, boolean status, int time){
		this.slotno = slotno;
		this.status = status;
		this.time = time;
	}
	
	public String getSlotno() {
		return this.slotno;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void assignto(User user) {
		this.UserName = user;
	}
	
	public User getUser_Name() {
		return this.UserName;
	}
	
	public void display() {
		System.out.println("Slot No : " + this.slotno);
		if(this.status) {
			System.out.println("Slot Status : Available");
		}
		else {
			System.out.println("Slot Status : Booked");
		}
		
		System.out.println("Time : " + this.time);
		
		if(!this.status && this.UserName != null) {
			System.out.println("Time : " + this.UserName.getUsername());
		}
	}
}
