package application;

import java.util.ArrayList;

public class Order {

	private int orderid;
    private String  desc;
    private String  status="placed";
    private String order_Address;
    private ArrayList<Food> fList= new ArrayList<Food>();
    private Bill temp;
    private Double subtotals[] = new Double[6];
	public Order() {
		
	}
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder_Address() {
		return order_Address;
	}
	public void setOrder_Address(String order_Address) {
		this.order_Address = order_Address;
	}
	public Order(int id,String desc,String Order_Address) {
		this.orderid=id;
		this.desc=desc;
		this.order_Address=Order_Address;
		
	}
	
	 
	public void createFood(ArrayList<String> foodName, ArrayList<Integer> quantity,ArrayList<Double> price) {
		
		for(int i=0;i<foodName.size();i++) {
			Food food = new Food(foodName.get(i),quantity.get(i));
			food.setFoodPrice(foodName.get(i),price.get(i));
			 fList.add(food);
		
			
			
		}
		
	}
	
	public double getTotal() {
		double total =0;
		
		for(int i=0;i<fList.size();i++) {
			subtotals[i]=fList.get(i).getsubtotal();
			total=total+subtotals[i];
		}
		return total;
		
	
		
	}

	public void generateBill(ArrayList<String> foodName, ArrayList<Integer> quantity, double total) {
		
		Bill bill = new Bill(total,subtotals,foodName,quantity);
		bill.getGrandTotal();
		temp=bill;
	  
	}


	public Bill displayBill() {
		Bill bill=temp;
	
		 return bill;
	}

	public boolean makePayment(int card_number) {
		boolean flag=false;
		PayByCard payment = new PayByCard(card_number,temp.getGrandTotal());
	   if( payment.PayBill()==true)
	   {
		   flag=true;
	   }
	   else
		   flag=false;
		temp=null;
		return flag;
	}

	public ArrayList<String> getPaymentMethod() {
		
		ArrayList<String> method = new ArrayList<String>();
		method.add("Pay By Cash");
		method.add("Pay By Card");
		return method;
	}

	public boolean makePayment() {
		boolean flag=true;
		PayBYCash payment = new PayBYCash(temp.getGrandTotal());
	  
		temp=null;
		return flag;
	}
	
	
	
	
	
	
}
