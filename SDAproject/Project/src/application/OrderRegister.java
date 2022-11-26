package application;

import java.util.ArrayList;

public class OrderRegister {

	
	private ArrayList<Order> activeOrderList= new ArrayList<Order>();
	private ArrayList<Order> inActiveOrderList= new ArrayList<Order>();
	
	
	
	public ArrayList<Order> getActiveOrderList() {
		return activeOrderList;
	}

	public void setActiveOrderList(ArrayList<Order> activeOrderList) {
		this.activeOrderList = activeOrderList;
	}

	public ArrayList<Order> getInActiveOrderList() {
		return inActiveOrderList;
	}

	public void setInActiveOrderList(ArrayList<Order> inActiveOrderList) {
		this.inActiveOrderList = inActiveOrderList;
	}

	public void add(Order ord) {
		this.activeOrderList.add(ord);
	}
	public void removeFromActive(Order ord) {
		this.activeOrderList.remove(ord);
	}
	
	public void inActiveOrder(Order ord) {
		this.inActiveOrderList.add(ord);
	}
    
		 
        public void CreateOrder(Customer customer,String address,String desc,ArrayList<String> FoodName,ArrayList<Integer> quantity,ArrayList<Double> price) {
    		int i=activeOrderList.size()+1;
    		for(int j=0;j<activeOrderList.size();j++) {
        	if(activeOrderList.get(j).getOrderid()==(activeOrderList.size()+1)){
        		i++;
        	   }
    		}
        	Order order = new Order(i,desc,address);
        	inActiveOrderList.add(order);
        		   order.createFood(FoodName,quantity,price);
        		  double total= order.getTotal();
        		   
        		  order.generateBill(FoodName,quantity,total);
        	    
        		  
                   
        	}
        public Bill displayBill() {
    		Order order = inActiveOrderList.get(inActiveOrderList.size()-1);
    	Bill bill= order.displayBill();
    		return bill;
    	}

    	public boolean makePayment(int card_number) {
    		boolean flag=false;
    		Order order = inActiveOrderList.get(inActiveOrderList.size()-1);
               if(order.makePayment(card_number)==true) {
            	   flag=true;
               }
               else
            	   flag=false;
               return flag;
    		
    	}

		

		public ArrayList<String> getPaymentMethod() {
			ArrayList<String> method = this.inActiveOrderList.get(this.inActiveOrderList.size()-1).getPaymentMethod();
			return method;
		}

		public boolean makePayment() {
			boolean flag=false;
    		Order order = inActiveOrderList.get(inActiveOrderList.size()-1);
               if(order.makePayment()==true) {
            	   flag=true;
               }
               else
            	   flag=false;
               return flag;
		}
    	
    	 
		 
	
}
