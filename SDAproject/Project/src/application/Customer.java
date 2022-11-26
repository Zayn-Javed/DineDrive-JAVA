package application;

import java.util.ArrayList;

public class Customer {

	private String userName;
	private String passWord;
	private String Name;
	private String address;
	private String phoneNumber;
	
	private ArrayList<Order> customer_orders = new ArrayList<Order>();
	private ArrayList<Rider> riderList = new ArrayList<Rider>();
	private ArrayList<String> feedback = new ArrayList<String>();
	public ArrayList<Order> getCustomer_orders() {
		return customer_orders;
	}

	public ArrayList<String> getFeedback() {
		return feedback;
	}

	public void setFeedback(ArrayList<String> feedback) {
		this.feedback = feedback;
	}

	public void setCustomer_orders(ArrayList<Order> customer_orders) {
		this.customer_orders = customer_orders;
	}

	public Customer(String name, String address, String phoneNumber, String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		Name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Rider> getRiderList() {
		return riderList;
	}

	public void setRiderList(ArrayList<Rider> riderList) {
		this.riderList = riderList;
	}
	
	
}
