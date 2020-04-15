package extornal.supply;

public interface MySupplySystem {
	public void order(Packet_Of_Prodacts items, inventory destantion);

	public void changeSupplayer(String name);
}
