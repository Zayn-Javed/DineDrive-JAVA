package application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class CustomerRegister {
	PersistenceHandler handler = DBHandler.getInstance();
    private String logedin;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Customer> BlockedCustomers = new ArrayList<Customer>();
    private ArrayList<Customer> reportedCustomers = new ArrayList<Customer>();
    private ArrayList<String>   reasons  = new ArrayList<String>();
    
    public ArrayList<Customer> getBlockedCustomers() {
		return BlockedCustomers;
	}

	public void setBlockedCustomers(ArrayList<Customer> blockedCustomers) {
		BlockedCustomers = blockedCustomers;
	}

	public ArrayList<Customer> getReportedCustomers() {
		return reportedCustomers;
	}

	public void setReportedCustomers(ArrayList<Customer> reportedCustomers) {
		this.reportedCustomers = reportedCustomers;
	}

	public ArrayList<String> getReasons() {
		return reasons;
	}

	public void setReasons(ArrayList<String> reasons) {
		this.reasons = reasons;
	}
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public String getLogedin() {
		return logedin;
	}

	public void setLogedin(String logedin) {
		this.logedin = logedin;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	public Customer getCustomer(String userName) throws SQLException {
		Customer customer=null;
		boolean flag= false;
		for(int i=0;i<customers.size();i++) {
		if(userName.equals(customers.get(i).getUserName())) {
			customer=customers.get(i);
			flag=true;
			break;
		}
		}
	if(!flag) {
		 String s = " SELECT * From Customer Where status='Active' AND username='"+userName+"';";
			ResultSet rs= (ResultSet)  handler.readRecord(s);
			while(rs.next()) {
				 customer = new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
	}
	}
		return customer;	
	}
	
	public boolean checkCredentialsCustomer(String userName, String password) {
		boolean flag= false;
		for(int i=0;i<customers.size();i++) {
		if(userName.equals(customers.get(i).getUserName()) && password.equals(customers.get(i).getPassWord())) {
			this.setLogedin(userName);
			flag= true;
		break;
		 }
		}
		if(flag==false) {
			String s="Select * from Customer Where username='"+userName+"' AND status='Active' AND password='"+password+"';";
			flag=handler.matchRecord(s);
			if(!flag)
				flag=true;
			else
				flag=false;
		}
		return flag;
	}

	public void blockcustomer(String custName) {
		boolean flag=false;
			  for(int j=0;j<customers.size();j++) {
				 
				  if(custName.equals(customers.get(j).getUserName())) {
					  Customer customer1=customers.remove(j);
					  BlockedCustomers.add(customer1);
				     flag=true;
				     break;
				  }
			  }
			  if(!flag) {
				 String s = "UPDATE Customer SET status='Blocked' Where username='"+custName+"';";
				 handler.updateRecord(s);
			  }
		
	}

	public boolean AddCustomer(String name, String address, String phoneNo, String username, String password) {
		boolean flag=true;
	for(int i=0;i<customers.size();i++) {
		if(username.equals(customers.get(i).getUserName()) || phoneNo.equals(customers.get(i).getPhoneNumber())) {
			flag=false;
		}	
	}
   if(flag) {
	   String s = "SELECT * from Customer Where username='"+username+"' OR phoneNumber='"+phoneNo+"';";
	  flag= handler.matchRecord(s);
	   
   }
   if(flag) {
	   if( customers.size()<=10) {
	Customer customer = new Customer(name, address, phoneNo, username, password);
	customers.add(customer);
	   }
	   else
	   {
		   for(int i=0;i<=customers.size();) {
			   String s ="INSERT INTO Customer(name,address,phoneNumber,username,password) VALUES('"+customers.get(i).getName()+
					   "','"+customers.get(i).getAddress()+"','"+customers.get(i).getPhoneNumber()+"','"+customers.get(i).getUserName()+
					   "','"+customers.get(i).getPassWord()+"');";
			   handler.saveRecord(s);
			   customers.remove(i);
		   }
		   Customer customer = new Customer(name, address, phoneNo, username, password);
			customers.add(customer);
	   }
   }
		return flag;
	}

	public ArrayList<Rider> showRider() {
		Customer cust = null;
		for(int i=0;i<customers.size();i++) {
			if(logedin.equals(customers.get(i).getUserName())) {
				 cust=customers.get(i);
			}
		}
		ArrayList<Rider> riderList = cust.getRiderList();
		return riderList;
	}

	public void saveFeedback(String feedback) throws SQLException {
		Customer cust = getCustomer(logedin);
		cust.getFeedback().add(feedback);
		
	}

	public Customer getMyCustomer(Order order) {
		Customer cust = null;
		for(int i=0;i<customers.size();i++) {
			    for(int j=0;j<customers.get(i).getCustomer_orders().size();j++) {
			    	if(order.equals(customers.get(i).getCustomer_orders().get(j))){
			    		cust = customers.get(i);
			    		return cust;
			    	}
			}
		}
		return null;
	}

	public Customer reportCustomer(String username, String reason) throws SQLException {
		Customer cust = getCustomer(username);
		reportedCustomers.add(cust);
		reasons.add(reason);

		return cust;
	}

	public void blockReportedcustomer(String username) {
		 Customer customer1=null;
		 boolean flag=false;
		  for(int j=0;j<customers.size();j++) {
			 
			  if(username.equals(customers.get(j).getUserName())) {
				  customer1= customers.remove(j);
				 reportedCustomers.remove(customer1);                    
				  BlockedCustomers.add(customer1);
				  flag=true;
			
			  }
		  }
		  if(!flag) {
				 String s = "UPDATE Customer SET status='Blocked' Where username='"+username+"';";
	             String s1 = " DELETE From reportCustomer Where customer='"+username+"';";
				 handler.updateRecord(s);
				 handler.deleteRecord(s1);
			  }
		
	}
	public void updateProfileInfo(String name, String address, String phoneNo, String username, String password, String custlogedin ) {
		boolean flag=false;
		for(int i=0;i<customers.size();i++) {
			if(custlogedin.equals(customers.get(i).getUserName())) {
				flag=true;
				customers.get(i).setName(name);
				customers.get(i).setAddress(address);
				customers.get(i).setPhoneNumber(phoneNo);
				customers.get(i).setUserName(username);
				customers.get(i).setPassWord(password);
				
			}
		}
		if(!flag) {
			String s=" Update Customer SET name='"+name+"', address='"+address+"', phoneNumber='"+phoneNo+"', username='"+username+"',password='"+password+"' Where username='"+username+"'; ";
			handler.updateRecord(s);
			
		}
	
		
	}

	
}
