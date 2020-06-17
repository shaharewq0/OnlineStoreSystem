package extornal.supply;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Domain.Store.MyPair;
import Domain.Store.Product;

public class Packet_Of_Prodacts {

	public LinkedList<Product> items = new LinkedList<>();

	public Packet_Of_Prodacts() {
	}

	public void add_items(List<MyPair<Product,String>> items){
		for (MyPair<Product, String> MP:items) {
			this.items.add(MP.getKey());
		}
	}

	public Packet_Of_Prodacts(Collection<Product> products) {
		items.addAll(products);
	}

	public Packet_Of_Prodacts(Product p) {
		items.add(p);
	}

}
