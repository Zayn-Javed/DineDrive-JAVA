package application;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

public class RestaurantRegister {

	PersistenceHandler handler = DBHandler.getInstance();
	

	
	private ArrayList<Restaurant> activeResaurantList= new ArrayList<Restaurant>();
	private ArrayList<Restaurant> inActiveRestaurant= new ArrayList<Restaurant>();

	
	
	public ArrayList<Restaurant> getActiveResaurantList() {
		return activeResaurantList;
	}

	public void setActiveResaurantList(ArrayList<Restaurant> activeResaurantList) {
		this.activeResaurantList = activeResaurantList;
	}

	public ArrayList<Restaurant> getInActiveRestaurant() {
		return inActiveRestaurant;
	}

	public void setInActiveRestaurant(ArrayList<Restaurant> inActiveRestaurant) {
		this.inActiveRestaurant = inActiveRestaurant;
	}

	public void add(Restaurant restaurant) {
		this.activeResaurantList.add(restaurant);
	}
	
	public void inActiveRestaurant(Restaurant restaurant) {
		this.inActiveRestaurant.add(restaurant);
	}
	
	public boolean validateRestaurent(String username, String name) {
		boolean flag= true;
		for(int i=0; i<activeResaurantList.size(); i++) {
			if(activeResaurantList.get(i).getManager().getUserName().equals(username) || activeResaurantList.get(i).getName().equals(name)) {
				flag= false;
				break;
			}
			else {
				continue;
			}
		}
		
		for(int i=0; i<this.inActiveRestaurant.size(); i++) {
			if(this.inActiveRestaurant.get(i).getManager().getUserName().equals(username) || inActiveRestaurant.get(i).getName().equals(name)) {
				flag= false;
				break;
			}
			else {
				continue;
			}
		}
		if(flag) {
		String s ="SELECT username FROM Manager join Restaurant ON Manager.Restname=Restaurant.RestName  Where username='"+username+"' AND Manager.RestName='"+name +"';";
	   flag=handler.matchRecord(s);
	   
		}
		if(flag) {
		
		}
		return flag;
	}

	public void addRestaurant(String username, String password, String name, String address, String catagory) {
		Restaurant restaurant= new Restaurant(name, address, catagory);
		restaurant.createRestaurantManager(username, password);
		if(activeResaurantList.size()<10) {
		this.add(restaurant);
		}
		else {
			for(int i=0;i<activeResaurantList.size();) {
				Restaurant restaurant1 = activeResaurantList.get(i);
			String s ="Insert into Restaurant(Restname,Address,Category) VALUES('"+restaurant1.getName()+"','"+restaurant1.getAddress()+"','"+restaurant1.getCatagory()+"');";
			handler.saveRecord(s);
			String s1="Insert into Manager(username,password,RestName) VALUES('"+restaurant1.getManager().getUserName()+"','"+restaurant1.getManager().getPassword()+"','"+restaurant1.getName()+"');";
			handler.saveRecord(s1);
			activeResaurantList.remove(i);
			}
			this.add(restaurant);
		}
		
	}
	
	public Restaurant searchRestaurant(String name) {
		boolean flag = false;
		Restaurant restaurant= null;
		for(int i=0; i<activeResaurantList.size(); i++) {
			if(activeResaurantList.get(i).getName().equals(name)) {
				restaurant= activeResaurantList.get(i);
				flag=true;
				break;
			}
			else {
				continue;
			}
		}
		if(!flag) {
		String s ="SELECT Restname FROM Restaurant Where Restname='"+name+"' AND status='Active';";
        flag=handler.matchRecord(s);
        if(!flag)
        	flag=true;
        else {
        	flag=false;
        }
		}
		return restaurant ;
	}
	
	public Restaurant remveRestaurat(String name) {
	Restaurant restaurant = searchRestaurant(name);
	boolean flag=false;
	for(int i=0; i<activeResaurantList.size(); i++) {
		if(activeResaurantList.get(i).getName().equals(restaurant.getName())) {
			activeResaurantList.remove(i);
			flag=true;
			break;
		}
	} if(!flag){
		String s ="UPDATE Restaurant SET status='InActive' Where RestName='"+name+"'";
		handler.updateRecord(s);
	}
		return restaurant;
	}

       public boolean VerifyOrder(Restaurant restaurant ,ArrayList<String> foodName,ArrayList<Integer> quantity) throws SQLException {
		Boolean flag=false;
	int index = activeResaurantList.indexOf(restaurant);

		for(int i=0;i<foodName.size();i++) {
			
			for(int j=0;j<activeResaurantList.get(index).getFoodList().size();j++) {
				ArrayList<Food> foodList = activeResaurantList.get(index).getFoodList();
                if(checkFoodName(foodList,foodName,j)==true ) {
                	boolean flag1 =restaurant.checkQuantity(quantity.get(i),foodName.get(i));
                	if(!flag1) {
                		String s = " SELECT Quantity From Food_Item Where FoodName='"+foodName.get(j)+"' AND RestName='"+restaurant.getName()+"';";
                		ResultSet rs= (ResultSet) handler.readRecord(s) ;
                		while(rs.next()) {
                			int q = rs.getInt(1);
                			if(q>=quantity.get(i)) {
                				int result=q-quantity.get(i);
                			 String s1 = "UPDATE Food_Item SET Quantity='"+result+ "Where FoodName='"+foodName.get(j)+"' AND RestName='"+restaurant.getName()+"';";
                			 handler.updateRecord(s1);
                			 flag1=true;
                			}
                		}
                	}
                	if(flag1==true)
                	flag=true;
                	break;
                }
			}
			if(flag==false) {
			  break;
			}
					
		}
		return flag;
		
		
	}
	
	public boolean checkFoodName(ArrayList<Food> foodList,ArrayList<String> foodName,int index) {
		
      return true;
	
		
	}
}
