package extornal.payment;

public interface PaymentMethed {

	public boolean pay(CreditCard card_num, double amount);
}
