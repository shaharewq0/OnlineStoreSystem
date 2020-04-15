package extornal.payment;

public class Pay_pal implements PaymentMethed {

	static final String name = "Pay_pal";

	@Override
	public void pay(int card_num, int amount) {
		System.out.println("thank you for using " + name);
		System.out.println("we will charge " + String.valueOf(card_num) + String.valueOf(amount) + "dollars");
	}


}
