package extornal.supply;

import Domain.Store.Product;
import Domain.Store.Product_boundle;

public class WallMart implements Supplyer {

	public static final String name = "WallMart";

	@Override
	public boolean order(Packet_Of_Prodacts items, inventory destantion) {
		// TODO add log
		System.out.println("thank you for ordering from " + name);
		System.out.println("we will only supply half your items");
		boolean coin = false;
		Packet_Of_Prodacts order = new Packet_Of_Prodacts();
		for (Product_boundle item : items.items) {
			if (coin) {
				coin = false;
				order.items.add(item);
			} else {
				coin = true;
			}
		}
		destantion.recive_item(order);
		return true;

	}
}
