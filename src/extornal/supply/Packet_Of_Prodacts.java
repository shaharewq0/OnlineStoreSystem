package extornal.supply;

import java.util.Collection;
import java.util.LinkedList;

import Domain.Store.Product;

public class Packet_Of_Prodacts {

	public LinkedList<Product> items = new LinkedList<>();

	public Packet_Of_Prodacts() {
	}

	public Packet_Of_Prodacts(Collection<Product> products) {
		items.addAll(products);
	}

	public Packet_Of_Prodacts(Product p) {
		items.add(p);
	}

}
