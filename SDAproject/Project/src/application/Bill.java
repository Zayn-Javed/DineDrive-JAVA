package application;

import java.util.ArrayList;

public class Bill {

	private double TotalAmount;
	
	private Double[] subamount; 
	
	private ArrayList<String> FoodName;
	
    private ArrayList<Integer> quantity;
	
	public Bill(double totalAmount, Double[] subamount, ArrayList<String> foodName,
			ArrayList<Integer> quantity) {
		super();
		TotalAmount = totalAmount;
		this.subamount = subamount;
		FoodName = foodName;
		this.quantity = quantity;
	}


	public Double getTotalAmount() {
		return TotalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}

	public Double[] getSubamount() {
		return subamount;
	}

	public void setSubamount(Double[] subamount) {
		this.subamount = subamount;
	}

	public ArrayList<String> getFoodName() {
		return FoodName;
	}

	public void setFoodName(ArrayList<String> foodName) {
		FoodName = foodName;
	}

	public ArrayList<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(ArrayList<Integer> quantity) {
		this.quantity = quantity;
	}
	
	public Double getGrandTotal() {
		double grandTotal = getTotalAmount()+calculateGST();
		
		return grandTotal;
	}

	public Double calculateGST() {
	 double gst = getTotalAmount()*0.17;
		return gst;
	}




}
