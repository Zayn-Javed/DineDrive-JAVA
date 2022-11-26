package application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class FoodOnWheels {
	PersistenceHandler handler = DBHandler.getInstance();
	private Admin admin= new Admin();
	private Rider rider= new Rider();
	private RestaurantRegister restRegister= new RestaurantRegister();
	private RiderRegister ridRegister= new RiderRegister();
	private OrderRegister ordRegister= new OrderRegister();
	private CustomerRegister custRegister = new CustomerRegister();

   private String logedin;
   private String riderlogedin;
    String custlogedin;
    Restaurant SelectedRestaurant;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public boolean checkCredentialsAdmin(String userName, String password) {
		boolean flag= false;
		if(userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
	
			flag= true;
		}
		return flag;
	}
	
	public boolean checkCredentialsCustomer(String userName, String password) {
		Boolean flag=custRegister.checkCredentialsCustomer(userName, password);
		if(flag==true) {
			this.custlogedin=userName;
		}
		
		return flag;
	}
	
	
	public boolean addRestaurant(String username, String password, String name, String address, String catagory) {
		boolean flag= this.restRegister.validateRestaurent(username, name);
		if(flag) {
			restRegister.addRestaurant(username, password, name, address, catagory);
		}
		return flag;
	}
	
	public ArrayList<Restaurant> showRestaurant() throws SQLException {
		
		ArrayList<Restaurant> list= this.restRegister.getActiveResaurantList();
		ArrayList<Restaurant> display = new ArrayList<Restaurant>();
		display.addAll(list);
		String s = "SELECT * from restaurant Join Manager On restaurant.Restname=Manager.RestName Where status='Active';";
		ResultSet rs= (ResultSet) handler.readRecord(s) ;
		while(rs.next()) {
			Restaurant restaurant = new Restaurant(rs.getString(1), rs.getString(2), rs.getString(3));
			           restaurant.createRestaurantManager(rs.getString(5), rs.getString(6));
			            display.add(restaurant);
			          
		}
	
		return display;
	}
	
	 public boolean removeRestaurant(String name) {
		 boolean flag=true;
		Restaurant restaurant= this.restRegister.remveRestaurat(name);
		restRegister.getInActiveRestaurant().add(restaurant);
		return flag;
	 }
	 
	 public boolean checkCredentialsManager(String userName, String password) {
		 boolean flag= false;
		 for(int i=0; i<this.restRegister.getActiveResaurantList().size(); i++) {
			 if(userName.equals(this.restRegister.getActiveResaurantList().get(i).getManager().getUserName()) && password.equals(this.restRegister.getActiveResaurantList().get(i).getManager().getPassword())) {
				 this.logedin= userName;
				 flag= true;
				 break;
			 }
		 }if(!flag) {
		  String s ="SELECT username,password From Manager join Restaurant On Restaurant.Restname=Manager.RestName Where username='"+userName+"' AND  password='"+password+"' AND status='Active';";
		  flag=handler.matchRecord(s);
		  if(!flag) {
			  flag=true;
			  this.logedin=userName;
		  }
		  else
			  flag=false;
		  
		 }
		 return flag;
	 }
	 
	 public Restaurant searchRestaurantOfManager() throws SQLException {
		 ArrayList<Restaurant> list= this.restRegister.getActiveResaurantList();
		 Restaurant restaurant= null;
		 boolean flag=false;
		 for(int i=0; i<list.size(); i++) {
			 if(this.logedin.endsWith(list.get(i).getManager().getUserName())) {
				 restaurant= list.get(i);
				 flag=true;
				 break;
			 }
		 }
		 String s = "SELECT * From Manager join Restaurant On Restaurant.Restname=Manager.RestName Where status='Active';";
		    ResultSet rs =  (ResultSet) handler.readRecord(s);
		    while(rs.next()) {
		    	if(rs.getString(1).equals(logedin)) {
		    		 restaurant = new Restaurant(rs.getString(4), rs.getString(5), rs.getString(6));
			           restaurant.createRestaurantManager(rs.getString(1), rs.getString(2));
		    		break;
		    	}
		    }
		 return restaurant;
	 }
	 
	 public boolean validateFoodName(Restaurant restaurant, String name) {
		 boolean flag= true;
		 for(int i=0; i<restaurant.getFoodCatalog().getFdeslist().size(); i++) {
			 if(name.equals(restaurant.getFoodCatalog().getFdeslist().get(i).getName())) {
				 flag= false;
				 break;
			 }
		 }
		 if(flag) {
			 String s = " SELECT FoodName From Food_Item Where FoodName='"+name+"' AND status='Active';";
			flag= handler.matchRecord(s);
		 }
		 return flag;
	 }
	 
	 public boolean addFood(String name, double price, int quantity, String detaileddes) throws SQLException {
		 Restaurant restaurant= searchRestaurantOfManager();
		 boolean flag= validateFoodName(restaurant, name);
		 if(flag) {
			 if(restaurant.getFoodList().size()<=10) {
			 restaurant.addFood(name, price, quantity, detaileddes);
			 }else
			 { for(int i=0;i<=restaurant.getFoodList().size();) {
				String s = "INSERT INTO Food_Item (FoodName,Quantity,RestName) VALUES('"+restaurant.getFoodList().get(i).getName()+"','"+restaurant.getFoodList().get(i).getQuantity()+"','"+restaurant.getName()+"');";
				String s1 = "INSERT INTO Food_Description(descp,price,FoodName) VALUES('"+restaurant.getFoodList().get(i).getFdes().getDetaildes()+"','"+restaurant.getFoodList().get(i).getFdes().getPrice()+"','"+restaurant.getFoodList().get(i).getName()+"');";
				 handler.saveRecord(s);
				 handler.saveRecord(s1);
				 restaurant.getFoodList().remove(i);
			 }
	
			 restaurant.addFood(name, price, quantity, detaileddes);
			 }
		 }
		 return flag;
		 
	 }
	 
	 public void RemoveFood(String name) throws SQLException {
		 Restaurant restaurant= searchRestaurantOfManager();
		 restaurant.removeFood(name);
	 }
	 
	 public void updateFood(String name, double price, int quantity, String detaileddes) throws SQLException {
		 Restaurant restaurant= searchRestaurantOfManager();
		 restaurant.updateFood(name, price, quantity, detaileddes);
	 }
	 
	 public ArrayList<String> showMenu() throws SQLException{
		 Restaurant restaurant= searchRestaurantOfManager();
		 ArrayList<String> menu= restaurant.getFoodCatalog().getMenu();
		 return menu;
	 }
	 
	 
	 public ArrayList<Food> showfoodList(Restaurant restaurant){
		
		 ArrayList<Food> menu= restaurant.getFoodList();
		 return menu;
	 }

	 
	 public boolean AddRider( String username, String email, String contact, String name ) {
			boolean flag= this.ridRegister.validateRider(username, email,contact);
			if(flag) {
				if(ridRegister.getActiveRiderList().size()<=10) {
				ridRegister.addRider(username, email, contact, name );
			   
				}
				else
				{   for(int i=0;i<ridRegister.getActiveRiderList().size();) {
					String s ="INSERT INTO Rider (Name,Contact,email,username) VALUES('"+ridRegister.getActiveRiderList().get(i).getName()+"','"+ridRegister.getActiveRiderList().get(i).getContact()+"','"+ridRegister.getActiveRiderList().get(i).getEmail()+"','"+ridRegister.getActiveRiderList().get(i).getUsername()+"');";
					handler.saveRecord(s);
					ridRegister.getActiveRiderList().remove(i);
					
				}
				ridRegister.addRider(username, email, contact, name );
				}
			}
			return flag;
		}
		
		public ArrayList<Rider> DisplayRiders() throws SQLException {
			ArrayList<Rider> list= this.ridRegister.getActiveRiderList();
			ArrayList<Rider> display = new ArrayList<Rider>();
			display.addAll(list);
			String s = "SELECT * from Rider Where status='Active';";
			ResultSet rs= (ResultSet) handler.readRecord(s) ;
			while(rs.next()) {
				Rider rider = new Rider(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4));
				            display.add(rider);
				           
			}
			return display;
		}
		
		 public boolean RemoveRiders(String username) throws SQLException {
			boolean flag= true;
			Rider rider= this.ridRegister.removeRider(username);
			this.ridRegister.inActiveRider(rider);
			this.ridRegister.removefromActive(rider);
			return flag;
		 }
		 
		 public Restaurant SelectRestaurant(String name) {
			 return SelectedRestaurant = restRegister.searchRestaurant(name);
			
	 }
	  public Boolean PlaceOrder(String userName,String address,String desc,ArrayList<String> foodName,ArrayList<Integer> quantity,ArrayList<Double> price) throws SQLException {
	
		  boolean flag=true;
		 Customer customer= custRegister.getCustomer(userName);
		  if( restRegister.VerifyOrder(SelectedRestaurant,foodName,quantity)==true) {
			  flag=true;
			 ordRegister.CreateOrder(customer,address,desc,foodName,quantity,price);
			  
		 }
		  else
			  flag=false;
       
		 return flag;
		 
	 }
	 public Bill displayBill() {
		 Bill bill=ordRegister.displayBill();
		 return bill;
	 }
	 
	 
		// ----------methods to pick order------------
		 
			 public ArrayList<Order> DisplayOrders() {
					ArrayList<Order> list= this.ordRegister.getActiveOrderList();
					
					return list;
				} 
			 
			 public ArrayList<Order> DisplayPicked() throws SQLException {
				   Rider Trider= searchRider();
					
				   ArrayList<Order> list = Trider.getPickedOrder();
				   return list;
				} 
			 
			 public boolean checkCredentialsRider(String userName, String password) {
				 boolean flag= false;
				 for(int i=0; i<this.ridRegister.getActiveRiderList().size(); i++) {
					 if(userName.equals(this.ridRegister.getActiveRiderList().get(i).getUsername()) && password.equals(this.ridRegister.getActiveRiderList().get(i).getEmail())) {
						 this.riderlogedin= userName;
						 flag= true;
						 break;
					 }
				 }
				 if(!flag) {
					 String  s ="SELECT * from Rider Where username='"+userName+"' AND status='Active' AND email='"+password+"';";
					 flag=handler.matchRecord(s);
					 if(!flag) {
						 flag=true;
					 }
					 else
						 flag=false;
				 }
				 return flag;
			 }
			 
			 
			 public Rider searchRider() throws SQLException {
				 ArrayList<Rider> list= this.ridRegister.getActiveRiderList();
				 Rider rider= null;
				 boolean flag= false;
				 for(int i=0; i<list.size(); i++) {
					 if(this.riderlogedin.endsWith(list.get(i).getUsername())) {
						 rider= list.get(i);
						 flag=true;
						 break;
					 }
				 }
				 if(!flag) {
				 String s = "SELECT * from Rider Where status='Active' AND username='"+this.riderlogedin+"';";
					ResultSet rs= (ResultSet) handler.readRecord(s) ;
					while(rs.next()) {
						 rider = new Rider(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4));     	           
					}
				 }
				 return rider;
			 }
			 
			 public Order searchOrder(int id) {
				 ArrayList<Order> list= this.ordRegister.getActiveOrderList();
				 Order order= null;
				 for(int i=0; i<list.size(); i++) {
					 if(list.get(i).getOrderid() == id ) {
						 order= list.get(i);
						 break;
					 }
				 }
				 return order;
			 }
			 
			 	//------------select------------------
			 
			 public boolean selectorder( int id ) throws SQLException {
				 Rider rider= searchRider();
				 Order order= searchOrder( id );
				 boolean flag=false;
				 if( order !=null ) {
					 rider.addOrder(order);
					 flag=true;
					 
					 //this.ordRegister.inActiveOrder(order);
					 //this.ordRegister.removeFromActive(order);
				 }
				 return flag;
				 
			 }

	            	//------------deliver------------------
			 
			 public boolean Deliver( int id ) throws SQLException {
				 Rider rider= searchRider();
				 Order order= searchOrder(id);
				Customer cust= custRegister.getMyCustomer( order);
				rider.getMyCustomers().add(cust);
				cust.getRiderList().add(rider);
				 boolean flag=false;
				 if( order !=null && order.getStatus().equalsIgnoreCase("picked") ) {
					 rider.adddeliveredOrder(order, this.ordRegister);
					 flag=true;
					 
					 //this.ordRegister.inActiveOrder(order);
					 //this.ordRegister.removeFromActive(order);
				 }
				 return flag;
				 
			 }
			 
			 
	public ArrayList<String> getPaymentMethod(){
		ArrayList<String> method= ordRegister.getPaymentMethod();
		return method;
	}
			 
		 
		 public boolean Payment(int card_number) throws SQLException {
			 boolean flag;
			   
			 flag=ordRegister.makePayment(card_number);
			 if(flag==true) {
			    	Order order= ordRegister.getInActiveOrderList().remove(ordRegister.getInActiveOrderList().size()-1);
			    	 ordRegister.getActiveOrderList().add(order);
                        SelectedRestaurant.getActiveOrderList().add(order);
                       custRegister.getCustomer(custRegister.getLogedin()).getCustomer_orders().add(order);
                       
			     }
				return flag;
			 
		 }
		 
		 public boolean Payment() throws SQLException {
			 boolean flag;
			   
			 flag=ordRegister.makePayment();
			 if(flag==true) {
			    	Order order= ordRegister.getInActiveOrderList().remove(ordRegister.getInActiveOrderList().size()-1);
			    	 ordRegister.getActiveOrderList().add(order);
                        SelectedRestaurant.getActiveOrderList().add(order);
                       custRegister.getCustomer(custRegister.getLogedin()).getCustomer_orders().add(order);
                       
			     }
				return flag;
			 
		 }
	
		 public ArrayList<Customer> showCustomers() throws SQLException{
			 ArrayList<Customer> customers = custRegister.getCustomers();
			 ArrayList<Customer> display = new  ArrayList<Customer>();
			 display.addAll(customers);
			 String s="SELECT * from Customer where status='Active'";
			  ResultSet rs = (ResultSet) handler.readRecord(s);
			  while(rs.next()) {
				  Customer customer= new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				  display.add(customer);
			  }
			return display;
			 	 
		 }
		public void selectCustomers(String custName) {
			custRegister.blockcustomer(custName);
				
		}

		public boolean AddCustomer(String name, String address, String phoneNo, String username, String password) {
			if(custRegister.AddCustomer(name,address,phoneNo,username,password)==true) {
				return true;
			}
			return false;
		}
		
		
	  public ArrayList<Rider> showRiders(){
		  
		  ArrayList<Rider> riderList = custRegister.showRider();
		return riderList;
		  
	  }
	  
	  public void giveFeedback(String Feedback, String rideruserName) throws SQLException {
		  
		  custRegister.saveFeedback(Feedback);
		 Rider rider = ridRegister.searchRider(rideruserName);
		  ridRegister.saveFeedback(Feedback,rider);
	  }
	  
	  public ArrayList<Customer> showMyCustomer() throws SQLException{
		  
		  ArrayList<Customer> custList = ridRegister.showMyCustomer(riderlogedin);
		  ArrayList<Customer> display = new ArrayList<Customer>();
		  display.addAll(custList);
		  String s = " SELECT * From Customer join Orders On Customer.username=Orders.customer Where Customer.status='Active' AND Orders.rider='"+riderlogedin+"';";
		ResultSet rs= (ResultSet)  handler.readRecord(s);
		while(rs.next()) {
			Customer customer = new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			display.add(customer);
		}
		  return display;
	  }
	
	  public void reportCustomer(String username,String reason) throws SQLException {
         if(custRegister.getReportedCustomers().size()<=10) {
		  Customer customer=custRegister.reportCustomer(username,reason); 
		  
		  Rider rider = ridRegister.searchRider(riderlogedin);
		  rider.getMyCustomers().remove(customer);
         }
		  else {
			     for(int i=0;i<custRegister.getReportedCustomers().size();) {
			    	 String s="INSERT INTO reportcustomer VALUES('"+custRegister.getReasons().get(i)+"','"+ rider.getUsername()+"','"+custRegister.getReportedCustomers().get(i).getUserName()+"');";
			    	 handler.saveRecord(s);
			    	 custRegister.getReportedCustomers().remove(i);
			     }
			     Customer customer=custRegister.reportCustomer(username,reason); 
				  
				  Rider rider = ridRegister.searchRider(riderlogedin);
				  rider.getMyCustomers().remove(customer);
			}
		
	  }
	  
	  public  ArrayList<Customer> getBlockRequests() {  
		  	  
		 return custRegister.getReportedCustomers();
	  }
	  
	  public ArrayList<String> getreportReasons(){
		  
		  return custRegister.getReasons();
	  }
	  
	  public void blockReportedCustomer(String username) {
		  
		 custRegister.blockReportedcustomer(username);
		 
	  }
	  
	  public ArrayList<String> showFeedback() throws SQLException {
		  Rider rider  = ridRegister.searchRider(riderlogedin);
		 ArrayList<String> feedback= rider.getFeedback();
		 return feedback;
	  }
	  
	  
	  public void updateprofile( String name, String address, String phoneNo, String username, String password) {
		
		  custRegister.updateProfileInfo(name,address,phoneNo,username,password,custlogedin);
				
	  }
	  
	  
}	  
