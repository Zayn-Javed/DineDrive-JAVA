package application;

import java.util.ArrayList;

public class Rider {
PersistenceHandler handler = DBHandler.getInstance();
	private String name;
	private String username;
	private String email;
	private String contact;
	
	
	private ArrayList<Order> pickedOrder= new ArrayList<Order>();
	private ArrayList<Order> deliveredOrder= new ArrayList<Order>();
	private ArrayList<Customer> myCustomers = new ArrayList<Customer>();
	private ArrayList<String> feedback = new ArrayList<String>();
	
	Rider(){
		
	}

	Rider(String name, String user, String mail,String cont){
		this.name=name;
		this.username=user;
		this.email=mail;
		this.contact=cont;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
	
	
	public ArrayList<Order> getPickedOrder() {
		return pickedOrder;
	}

	public void setPickedOrder(ArrayList<Order> pickedOrder) {
		this.pickedOrder = pickedOrder;
	}

	
	public ArrayList<Order> getDeliveredOrder() {
		return deliveredOrder;
	}

	public void setDeliveredOrder(ArrayList<Order> deliveredOrder) {
		this.deliveredOrder = deliveredOrder;
	}

	public void addOrder(Order ord) {
		ord.setStatus("picked");
		this.pickedOrder.add(ord);

		
	}
	
	public void adddeliveredOrder(Order ord, OrderRegister OR) {
		ord.setStatus("delivered");
		this.deliveredOrder.add(ord);
		this.pickedOrder.remove(ord);

		if(OR.getInActiveOrderList().size()<=10) {

			OR.getInActiveOrderList().add(ord);
			
			OR.getActiveOrderList().remove(ord);
		}
		else {
			for(int i=0;i<OR.getInActiveOrderList().size();i++) {
				String s1 ="INSERT INTO ORDERS VALUES('"+OR.getInActiveOrderList().get(i).getOrderid()+"','"+
						OR.getInActiveOrderList().get(i).getDesc()+"','"+
								OR.getInActiveOrderList().get(i).getOrder_Address()+"','"+"','"+getUsername()+"','"+"');";
									;
	            String  s3 = "INSERT INTO Bill VALUES ('"+OR.getInActiveOrderList().get(i).displayBill().getTotalAmount()+"','"+OR.getInActiveOrderList().get(i).getOrderid()+"');";
	            handler.saveRecord(s1);
	            handler.saveRecord(s3);
				for(int j=0;j<OR.getInActiveOrderList().get(i).displayBill().getQuantity().size();j++) {
			 
			String s2="INSERT INTO Orderdetails VALUES('"+OR.getInActiveOrderList().get(i).getOrderid()+"','"+OR.getInActiveOrderList().get(i).displayBill().getFoodName().get(j)
					+"','"+OR.getInActiveOrderList().get(i).displayBill().getQuantity().get(j)+"','"+OR.getInActiveOrderList().get(i).displayBill().getSubamount()[j];
		         handler.saveRecord(s2);
				}
			}
		}
		
	}
	
	
	public String toString() {
		
		return "name: "+name+", username: "+username+", contact: "+contact;
	}

	public ArrayList<Customer> getMyCustomers() {
		return myCustomers;
	}

	public void setMyCustomers(ArrayList<Customer> myCustomers) {
		this.myCustomers = myCustomers;
	}

	public ArrayList<String> getFeedback() {
		return feedback;
	}

	public void setFeedback(ArrayList<String> feedback) {
		this.feedback = feedback;
	}
	
	
}
