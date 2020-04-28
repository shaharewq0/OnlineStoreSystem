package Domain.Store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tests.AcceptanceTests.auxiliary.ProductDetails;

public class shoppingBasket implements IshoppingBasket {
	private Map<String, Integer> products;
	private StoreImp store;

	public shoppingBasket(StoreImp store) {
		this.store = store;
		products = new HashMap<>();
	}

	public void addProduct(String name, int amount) {
		if (products.containsKey(name)) {
			products.replace(name, products.get(name) + amount);
		} else {
			products.put(name, amount);
		}
	}

	public int removeProduct(String name, int num) {
		if (products.containsKey(name)) {
			int current = products.get(name);
			if (current > num) {
				products.replace(name, current - num);
				return num;
			} else {
				products.remove(name);
				return current;
			}
		}
		return 0;

	}

	private MyPair<ProductDetails, Integer> findInBasket(String name) {
		if (products.containsKey(name)) {
			Product a = store.findProductByName(name);
			MyPair<ProductDetails, Integer> item = new MyPair<ProductDetails, Integer>(new ProductDetails(a),
					products.get(name));
			return item;
		}
		return null;
	}

	public StoreImp getStore() {
		return store;
	}

	public List<ProductDetails> getProducts() {
		List<ProductDetails> output = new LinkedList<ProductDetails>();
		for (String name : products.keySet()) {
			output.add(new ProductDetails(store.findProductByName(name)));
		}
		return output;
	}
}
