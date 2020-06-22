package extornal.payment;

import Domain.Logs.EventLogger;

import java.util.LinkedList;
import java.util.List;

public class Pay_pal implements PaymentMethed {

	public static final String name = "Pay_pal";

	private List<CreditCard> validCards;

	public Pay_pal(){
		validCards = new LinkedList<>();
		validCards.add(new CreditCard("1234-4321-1234-4321", "06/23", "123", "yosi pil"));
	}


	@Override
	public boolean pay(CreditCard card_num, double amount) {
		//card_num.getMoney(amount);
		//System.out.println("thank you for using " + name);
		if( validCards.contains(card_num)){
			//System.out.println("we will charge " + String.valueOf(card_num) + String.valueOf(amount) + "dollars");
			return  true;
		}

		//System.out.println("the card " + String.valueOf(card_num) + " is no a valid card!");
		return false;
	}


}
