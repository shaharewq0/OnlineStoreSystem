package extornal.payment;

import Domain.Logs.EventLogger;

import java.util.LinkedList;
import java.util.List;

public class Isracard implements PaymentMethed {

	public static final String name = "Isracard";
	private final String logmsg = "A transaction,of size {0} has been made to {1}";

	private List<CreditCard> validCards;

	public Isracard(){
		validCards = new LinkedList<>();
		validCards.add(new CreditCard("1234-4321-1234-4321", "06/23", "123", "yosi pil"));
	}

	@Override
	public boolean pay(CreditCard card_num, double amount) {

		EventLogger.GetInstance().Add_Log(logmsg + amount + "," + card_num.getCardOwner());
		return validCards.contains(card_num);
	}
}