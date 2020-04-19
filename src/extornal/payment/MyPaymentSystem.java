package extornal.payment;

public interface MyPaymentSystem {
	
	public PaymentMethed getPaymentMethed();
	
	public boolean pay(long card_num, int amount);

	public void changePaymentMethed(String name);
}
