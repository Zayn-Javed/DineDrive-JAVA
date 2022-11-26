package application;

import java.util.ArrayList;

public class Restaurant {
    PersistenceHandler handler = DBHandler.getInstance();
	private RestaurantManager manager;
	private String name;
	private String address;
	private String catagory;
	private ArrayList<Food> foodList= new ArrayList<Food>();
	private FoodCatalog foodCatalog;
	private ArrayList<Order> activeOrderList= new ArrayList<Order>();
	private ArrayList<Order> inActiveOrderList= new ArrayList<Order>();

	public Restaurant(String name, String address, String catagory) {
		this.name= name;
		this.address= address;
		this.catagory= catagory;
		this.foodCatalog= new FoodCatalog();
	}
	public ArrayList<Food> getFoodList() {
		return foodList;
	}
	public ArrayList<Order> getActiveOrderList() {
		return activeOrderList;
	}
	public void setActiveOrderList(ArrayList<Order> activeOrderList) {
		this.activeOrderList = activeOrderList;
	}
	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	public ArrayList<Order> getInActiveOrderList() {
		return inActiveOrderList;
	}
	public void setInActiveOrderList(ArrayList<Order> inActiveOrderList) {
		this.inActiveOrderList = inActiveOrderList;
	}
	public FoodCatalog getFoodCatalog() {
		return foodCatalog;
	}
	public void setFoodCatalog(FoodCatalog foodCatalog) {
		this.foodCatalog = foodCatalog;
	}
	public RestaurantManager getManager() {
		return manager;
	}
	public void setManager(RestaurantManager manager) {
		this.manager = manager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public void createRestaurantManager(String username, String password) {
		this.manager= new RestaurantManager(username, password);
	}
	public void addFood(String name, double price, int quantity, String detaileddes) {
	
		Food food= new Food(name, quantity);
		
		FoodDescription fdes= food.makeFoodDes(name, price, detaileddes);
		
			this.foodList.add(food);
		this.foodCatalog.add(fdes);
		
	}
	
	public void removeFood(String name) {
		Food food= null;
		boolean Flag=false;
		for(int i=0; i<this.foodList.size(); i++) {
			if(name.equals(this.foodList.get(i).getName())) {
				food= this.foodList.get(i);
				this.foodList.remove(i);
				Flag=true;
				break;
			}
		}
		this.foodCatalog.removeFoodDes(name);
		
		if(!Flag) {
			String s = "UPDATE Food_Item SET status='InActive' Where FoodName='"+name+"';";
			handler.updateRecord(s);
		}
		
	}
	
	public void updateFood(String name, double price, int quantity, String detaileddes) {
		Food food= null;
		boolean flag=false;
		for(int i=0; i<this.foodList.size(); i++) {
			if(name.equals(this.foodList.get(i).getName())) {
				food= this.foodList.get(i);
				flag=true;
				break;
			}
		}
		food.update(quantity);
		this.foodCatalog.updateFoodDes(name, price, detaileddes);
		
		if(flag==false) {
			String s = "UPDATE Food_Item SET Quantity='"+quantity+"' Where FoodName='"+name+"';";
			String s1 = "UPDATE Food_Description SET Price='"+price+"' , descp='"+detaileddes+"' Where FoodName='"+name+"';";
			handler.updateRecord(s);
			handler.updateRecord(s1);
		}
		
	}
public boolean checkQuantity(Integer ReqQuantity, String name) {
	int index = 0;
	     for(int i=0;i<foodList.size();i++) {
	    	 if(foodList.get(i).getName().equals(name)) {
	    		 index=i;
	    		 break;
	    	 }
	     }
		int quantity= foodList.get(index).getQuantity();
	    
		if(quantity>=ReqQuantity) {
			foodList.get(index).setQuantity(quantity-ReqQuantity);
			return true;
	}
		else 
			return false;
	}
}
