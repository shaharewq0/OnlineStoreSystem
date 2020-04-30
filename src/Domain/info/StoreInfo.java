package Domain.info;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Domain.Store.Product;

public class StoreInfo {

	public String name;
	public String address;
	public int rating;
	public List<String> products;

	public StoreInfo(String name, String address, int rating, Collection<Product> products) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.products = new LinkedList<String>();
		for (Product product : products) {
			this.products.add(product.toString());
		}
	}

}
