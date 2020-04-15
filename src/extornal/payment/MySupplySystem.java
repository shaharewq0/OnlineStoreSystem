package extornal.payment;

public interface MySupplySystem {
	
	public void pay(int card_num, int amount);

	public void changePaymentMethed(String name);
}
