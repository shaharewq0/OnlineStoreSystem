package extornal.supply;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.Product_boundle;

public class Packet_Of_Prodacts {

	public LinkedList<Product_boundle> items = new LinkedList<>();

	public Packet_Of_Prodacts() {
	}

	public void add_items(List<MyPair<Product_boundle,String>> items){
		for (MyPair<Product_boundle, String> MP:items) {
			this.items.add(MP.getKey());
		}
	}

	public Packet_Of_Prodacts(Collection<Product_boundle> products) {
		items.addAll(products);
	}

	public Packet_Of_Prodacts(Product_boundle p) {
		items.add(p);
	}

}
