package extornal.supply;

import extornal.supply.Eternal.EternalSupply;

import java.util.HashMap;

public class MySupplySystem_Driver implements MySupplySystem {
	

	public static Supplyer current_supplyer = null;

	static HashMap<String, Supplyer> list = null;
	static {
		list = new HashMap<String, Supplyer>();
		list.put(EternalSupply.name, new EternalSupply());
		list.put(Rami_Lavy.name, new Rami_Lavy());
		list.put(WallMart.name, new WallMart());
		current_supplyer = list.get(EternalSupply.name);
	}

	public void order(Packet_Of_Prodacts items, inventory destantion) {
		current_supplyer.order(items, destantion);
	}

	public void changeSupplayer(String name) {
		if (list.containsKey(name)) {
			current_supplyer = list.get(name);
			// TODO add log
			System.out.println("ordering from " + name);
		} else {
			// TODO add log
			System.out.println("supplyer " + name + " dont exsist");
		}

	}

	@Override
	public Supplyer getSupplayer() {
		return current_supplyer;
	}

}
