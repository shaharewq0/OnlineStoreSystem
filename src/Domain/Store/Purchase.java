package Domain.Store;

import java.util.LinkedList;
import java.util.List;

import Domain.info.ProductDetails;
import extornal.payment.PaymentMethed;

public class Purchase {

	private double price;
	private List<ProductDetails> items;
	private List<Discount> discounts;
	private Acquisition transaction;
	private PaymentMethed payMethed;
	private StoreImp store;

	public Purchase(List<ProductDetails> items, StoreImp store) {
		this.items = items;
		this.store = store;
		discounts = new LinkedList<Discount>(); 
		CalcPrice_AndFindDiscouns();
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

	public List<Discount> getDiscounts() {
		List<Discount> discounts = new LinkedList<Discount>();
		for (Discount dis : this.discounts) {
			discounts.add(Discount.Copy(dis));
		}
		return discounts;
	}

	public Acquisition getTransaction() {
		return Acquisition.Copy(transaction);
	}



	public PaymentMethed getPayMethed() {
		return payMethed;
	}
//----------- fucntions

	private void CalcPrice_AndFindDiscouns() {
		price = 0;
		for (ProductDetails pd : items) {
			double add = 0;
			add += store.getPrice(pd.getName()) * pd.getAmount();
			List<Discount> discounts = store.getDiscounts(pd.getName());
			discounts.addAll(discounts);
			for (Discount discount : discounts) {
				add = discount.implamentDis(add);
			}
			price += add;
		}
	}
}
