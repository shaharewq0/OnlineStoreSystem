package extornal.payment;

public interface MyPaymentSystem {
	
	public PaymentMethed getPaymentMethed();
	
	public boolean pay(bankAccount card_num, int amount);

	public void changePaymentMethed(String name);
	
}