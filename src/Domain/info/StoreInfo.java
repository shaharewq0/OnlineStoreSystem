package Domain.info;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import tests.AcceptanceTests.auxiliary.StoreDetails;

public class StoreInfo {

	public String name;
	public String address;
	public int rating;
	public List<ProductDetails> products;

	public StoreInfo(String name, String address, int rating, Collection<ProductDetails> products) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		//maybe we dont need products here
		this.products = new LinkedList<ProductDetails>();
		for (ProductDetails product : products) {
			this.products.add(product);
		}
	}

	public StoreInfo(StoreDetails store) {
		this.name = store.getName();
		this.address = store.getAdress();
		this.rating = store.getRating();
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

}
