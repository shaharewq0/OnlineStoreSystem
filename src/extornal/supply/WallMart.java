package extornal.supply;

public class WallMart implements Supplyer {

	public static final String name = "WallMart";

	@Override
	public void order(Packet_Of_Prodacts items, inventory destantion) {
		// TODO add log
		System.out.println("thank you for ordering from " + name);
		System.out.println("we will only supply half your items");
		boolean coin = false;
		Packet_Of_Prodacts order = new Packet_Of_Prodacts();
		for (String item : items.items) {
			if (coin) {
				coin = false;
				order.items.add(item);
			} else {
				coin = true;
			}
		}
		destantion.recive_item(order);

	}
}
