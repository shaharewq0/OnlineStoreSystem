package extornal.payment;

import java.util.HashMap;

public class MySupplySystem_Driver implements MySupplySystem {

	static PaymentMethed current_supplyer = null;

	static HashMap<String, PaymentMethed> list = null;
	static {
		list = new HashMap<String, PaymentMethed>();
		list.put(Pay_pal.name, new Pay_pal());
		list.put(Isracard.name, new Isracard());
		current_supplyer = list.get(Pay_pal.name);
	}

	public void pay(int card_num, int amount) {
		current_supplyer.pay(card_num, amount);
	}

	public void changePaymentMethed(String name) {
		if (list.containsKey(name)) {
			current_supplyer = list.get(name);
			// TODO add log
			System.out.println("ordering from " + name);
		} else {
			// TODO add log
			System.out.println("supplyer " + name + " dont exsist");
		}

	}
}
