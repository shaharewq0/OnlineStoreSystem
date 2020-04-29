package extornal.payment;

public interface PaymentMethed {

	public boolean pay(bankAccount card_num, double amount);
}
