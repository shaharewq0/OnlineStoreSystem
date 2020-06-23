package Domain.Store;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Domain.info.ProductDetails;
import extornal.payment.PaymentMethed;

public class StorePurchase {
	private int id;
	private double price;
	private List<ProductDetails> items;
	private String store;


	public StorePurchase(List<ProductDetails> items, String store,double price) {
		this.items = items;
		this.store = store;
		this.price = price;
	}

	public StorePurchase(){}

	public int getId() {
		return id;
	}

	public String getStore() {
		return store;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setItems(List<ProductDetails> items) {
		this.items = items;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public double getPrice() {
		return price;
	}

	public List<ProductDetails> getItems() {
		List<ProductDetails> items = new LinkedList<ProductDetails>();
		for (ProductDetails pd : this.items) {
			items.add(ProductDetails.Copy(pd));
		}
		return items;
	}

	public PaymentMethed getPayMethed() {
		return null;//TODO payMethed;
	}

	public String get_Store_Name() {
		return store;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StorePurchase that = (StorePurchase) o;
		return Double.compare(that.getPrice(), getPrice()) == 0 &&
				getItems().containsAll(that.getItems()) &&
				that.getItems().containsAll(getItems()) &&
				Objects.equals(store, that.store);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPrice(), getItems(), store);
	}
}
