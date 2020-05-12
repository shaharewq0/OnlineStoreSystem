package Domain.info;

import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StoreInfo {

	public String name;
	public String address;
	public int rating;
	public List<ProductDetails> products;

	public StoreInfo(String name, String address, int rating, Collection<ProductDetails> products) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.products = new LinkedList<ProductDetails>();
		for (ProductDetails product : products) {
			this.products.add(product);
		}
	}

	public StoreInfo(StoreDetails store) {
		this.name = store.getName();
		this.address = "unown";
		this.rating = 0;
		this.products = new LinkedList<ProductDetails>();

	}

	public StoreInfo(String name, String address, int rating) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.products = new LinkedList<ProductDetails>();
	}

//	public StoreInfo(String name, String address, int rating, Collection<Product> products) {
//		this.name = name;
//		this.address = address;
//		this.rating = rating;
//		this.products = new LinkedList<ProductDetails>();
//		for (Product product : products) {
//			this.products.add(new ProductDetails(product,product.getAmount()));
//		}
//	}

	public List<ProductDetails> getProducts() {
		return products;
	}
}
