package extornal.supply;

public class Rami_Lavy implements Supplyer {

	public static final String name = "Rami_Lavy";

	@Override
	public boolean order(Packet_Of_Prodacts items, inventory destantion) {
		// TODO add log
		//System.out.println("thank you for ordering from " + name);
		//System.out.println("we double your items due to new promotion");
		destantion.recive_item(items);
		destantion.recive_item(items);
		return true;
	}
}
