package DataBaseConn;

public class Plan {
	private int id;
	private int sms;
	private int minutes;
	private int mobile_data;
	private double price;
	public Plan(int id, int sms, int minutes, int mobile_data, double price, String name) {
		this.id = id;
		this.sms = sms;
		this.minutes = minutes;
		this.mobile_data = mobile_data;
		this.price = price;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSms() {
		return sms;
	}
	public void setSms(int sms) {
		this.sms = sms;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getMobile_data() {
		return mobile_data;
	}
	public void setMobile_data(int mobile_data) {
		this.mobile_data = mobile_data;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
}
