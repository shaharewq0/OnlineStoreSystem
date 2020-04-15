package extornal.payment;

public class Isracard implements PaymentMethed {


	static final String name = "Isracard";

	@Override
	public void pay(int card_num, int amount) {
		System.out.println("thank you for using " + name);
		System.out.println("we will charge " + String.valueOf(card_num) + String.valueOf(amount * 1.1) + "dollars due to extra needs");
	}
}
