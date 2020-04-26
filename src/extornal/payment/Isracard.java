package extornal.payment;

import Domain.store_System.Logs.EventLogger;

public class Isracard implements PaymentMethed {

	public static final String name = "Isracard";
	private final String logmsg = "A transaction,of size {0} has been made to {1}";

	@Override
	public void pay(bankAccount card_num, int amount) {
		card_num.getMoney(amount);
		EventLogger.GetInstance().Add_Log(logmsg + Integer.toString(amount) + "," + card_num.name);

	}
}
