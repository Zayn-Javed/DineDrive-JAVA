package application;

public class PaymentAuthorizationService {

	double balance=10000;
	public boolean AuthorizePay(int card_number,double amount) {
		boolean flag=true;
		if(amount<=balance) {
			balance=balance-amount;
			flag=true;
		}
		else
			flag=false;
		return flag;
	}
	
}