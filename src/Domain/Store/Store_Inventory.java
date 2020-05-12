package Domain.Store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import extornal.supply.Packet_Of_Prodacts;
import extornal.supply.inventory;

public class Store_Inventory implements inventory {

	Map<String, Product> items = new HashMap<String, Product>();

	@Override
	public boolean recive_item(Packet_Of_Prodacts pack) {
		if (pack == null || pack.items == null || pack.items.isEmpty())
			return false;

		for (Product p : pack.items) {
			if (items.containsKey(p.getName()))
				items.get(p.getName()).addToAmount(p.getAmount());
			else
				items.put(p.getName(), p);
		}
		return true;

	}

	public boolean removeProduct(String productname) {
		if (!items.containsKey(productname))
			return false;
		items.remove(productname);
		return true;
	}

	public boolean editProduct(String old_p, Product new_p) {
		if (items.containsKey(old_p)) {
			items.replace(old_p, new_p);
			return true;
		}
		return false;
	}

	public List<Product> findProductByCategory(String category) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : items.values()) {
			if (p != null && p.getCategory().contains(category)) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	public List<Product> findProductByKeyword(String keyword) {
		List<Product> toReturn = new LinkedList<>();
		for (Product p : items.values()) {
			if (p != null && p.getKeyWords().contains(keyword)) {
				toReturn.add(p);
			}
		}
		return toReturn;
	}

}
