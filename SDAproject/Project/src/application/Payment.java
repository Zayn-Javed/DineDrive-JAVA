package application;

import java.util.ArrayList;

public abstract class Payment {

	
	protected double Amount;
	

	
	public Payment (double grandTotal) {
		
		this.Amount=grandTotal;
	}
	
	
public abstract String getPaymentMethod();
 

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	

}