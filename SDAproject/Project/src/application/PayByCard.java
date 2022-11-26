package application;

public class PayByCard  extends Payment{

	private int card_number;
	
	public int getCard_number() {
		return card_number;
	}

	public void setCard_number(int card_number) {
		this.card_number = card_number;
	}

	public PayByCard(int Card_number, double grandTotal) {
		super(grandTotal);
	    setCard_number(Card_number);
	}
	
	public boolean PayBill() {
		boolean flag=false;
	
		
	PaymentAuthorizationService PAS = new PaymentAuthorizationService();
	if(PAS.AuthorizePay(card_number,this.getAmount())==true){
		flag=true;
	}
	else
		flag=false;
	
return flag;
	}

	@Override
	public String getPaymentMethod() {
		
		return "Pay By Card";
	}
	
}
