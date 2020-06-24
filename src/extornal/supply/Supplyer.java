package extornal.supply;

import Service_Layer.userAddress;

public interface Supplyer {

	
	public int order(Packet_Of_Prodacts items, inventory destantion);
	public int order(Packet_Of_Prodacts items, userAddress destantion);

	boolean cancel_supply(int transactionID);
}
