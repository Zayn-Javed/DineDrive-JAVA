package application;

public class PayBYCash extends Payment{

	public PayBYCash( double grandTotal) {
		super(grandTotal);
		
		
		
	}
	
	@Override
	public String getPaymentMethod() {
		
		return "Pay By Cash";
	}
	

}
