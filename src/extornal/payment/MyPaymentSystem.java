package extornal.payment;

public interface MyPaymentSystem {
	
	public PaymentMethed getPaymentMethed();
	
	public int pay(CreditCard card_num, int amount);

	public void changePaymentMethed(String name);
	
}
