package application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class RiderRegister {

	PersistenceHandler handler = DBHandler.getInstance();
	private ArrayList<Rider> activeRiderList= new ArrayList<Rider>();
	private ArrayList<Rider> inActiveRiderList= new ArrayList<Rider>();
	
	
	
	
	public ArrayList<Rider> getActiveRiderList() {
		return activeRiderList;
	}

	public void setActiveRiderList(ArrayList<Rider> activeRiderList) {
		this.activeRiderList = activeRiderList;
	}

	public ArrayList<Rider> getInActiveRiderList() {
		return inActiveRiderList;
	}

	public void setInActiveRiderList(ArrayList<Rider> inActiveRiderList) {
		this.inActiveRiderList = inActiveRiderList;
	}

	public void add(Rider rid) {
		this.activeRiderList.add(rid);
	}
	
	public void removefromActive(Rider rid) {
		this.activeRiderList.remove(rid);
	}
	
	public void inActiveRider(Rider rid) {
		this.inActiveRiderList.add(rid);
		removefromActive(rid);
	}
	
	
	// validate the rider before adding to the list
	
	public boolean validateRider(String username, String email, String contact) {
		boolean flag= true;
		for(int i=0; i<activeRiderList.size(); i++) {
			if(activeRiderList.get(i).getUsername().equals(username) || activeRiderList.get(i).getEmail().equals(email) || activeRiderList.get(i).getContact().equals(contact) ) {
				flag= false;
				break;
			}
			else {
				continue;
			}
		}
		if(flag) {
			for(int i=0; i<this.inActiveRiderList.size(); i++) {
				if(this.inActiveRiderList.get(i).getUsername().equals(username) || inActiveRiderList.get(i).getEmail().equals(inActiveRiderList) || inActiveRiderList.get(i).getContact().equals(contact) ) {
					flag= false;
					break;
				}
				else {
					continue;
				}
			}
		}
		if(flag) {
			String s="SELECT * From Rider Where Contact='"+contact+"' OR email='"+email+"'OR username='"+username+"';";
			 flag=handler.matchRecord(s);
		}
		return flag;
	}
	
	// add rider to the list after validation
	
	public void addRider(String username, String email, String contact, String name ) {
		Rider rid= new Rider(name, username,email,contact);
            this.add(rid);
	}
	
	//search for rider in Arraylist
	
	public Rider searchRider(String username) throws SQLException {
		Rider rider= null;
		boolean flag=false;
		for(int i=0; i<activeRiderList.size(); i++) {
			if(activeRiderList.get(i).getUsername().equals(username)) {
				rider= activeRiderList.get(i);
				flag=true;
				break;
			}
			else {
				continue;
			}
		}
		if(!flag) {
			String s = "SELECT * from Rider Where status='Active';";
			ResultSet rs= (ResultSet) handler.readRecord(s) ;
			while(rs.next()) {
				 rider = new Rider(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4));
				            flag=true;
				     
			}
		}
		return rider;
	}
	
	// remove rider 
	
	public Rider removeRider(String username) throws SQLException {
		Rider rider= searchRider(username);
		boolean flag=false;
		for(int i=0; i<activeRiderList.size(); i++) {
			if(activeRiderList.get(i).getUsername().equals(rider.getUsername())) {
				activeRiderList.remove(i);
				flag=true;
				break;
			}
		}
		if(!flag) {
			String s = "UPDATE Rider SET status='InActive' Where username='"+username+"';";
			handler.updateRecord(s);
		}
		return rider;
	}

	public void saveFeedback(String feedback, Rider rider) {
		rider.getFeedback().add(feedback);
		
	}

	public ArrayList<Customer> showMyCustomer(String username) throws SQLException {
		Rider rider = searchRider(username);
		ArrayList<Customer> custList = rider.getMyCustomers();
		return custList;
	}
	
	
	
}
