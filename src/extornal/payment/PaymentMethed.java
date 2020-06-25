package extornal.payment;

public interface PaymentMethed {

	public int pay(CreditCard card_num, double amount);

    boolean cancel_pay(int transactionID);
}
