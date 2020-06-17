package Domain.Store;

import java.util.LinkedList;
import java.util.List;

import Domain.info.ProductDetails;
import extornal.payment.PaymentMethed;

public class Purchase {

	private double price;
	private List<ProductDetails> items;
	private PaymentMethed payMethed;
	private StoreImp store;

	public Purchase(List<ProductDetails> items, StoreImp store,double price) {
		this.items = items;
		this.store = store;
		this.price = price;
	}

//--------getters

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
		return payMethed;
	}
//----------- fucntions


}
