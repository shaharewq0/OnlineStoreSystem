package extornal.payment;

import Domain.Logs.EventLogger;

public class Isracard implements PaymentMethed {

	public static final String name = "Isracard";
	private final String logmsg = "A transaction,of size {0} has been made to {1}";

	@Override
	public boolean pay(CreditCard card_num, double amount) {
		//card_num.getMoney(amount);
		//EventLogger.GetInstance().Add_Log(logmsg + Double.toString(amount) + "," + card_num.name);
		return true;
	}
}
