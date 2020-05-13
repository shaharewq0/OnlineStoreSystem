package Domain.Store;

import java.util.LinkedList;
import java.util.List;

import Domain.info.ProductDetails;
import extornal.payment.PaymentMethed;

public class StorePurchase {

	private double price;
	private List<ProductDetails> items;
	private List<Discount> discounts;
	//private Acquisition transaction;
	//private PaymentMethed payMethed;
	private String store;

	public StorePurchase(List<ProductDetails> items, String store,double price,List<Discount> discounts) {
		this.items = items;
		this.store = store;
		this.price = price;
		discounts = new LinkedList<Discount>(); 
		discounts.addAll(Discount.Copy(discounts));
		//CalcPrice_AndFindDiscouns();
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
		return null;//TODO Acquisition.Copy(transaction);
	}

	public PaymentMethed getPayMethed() {
		return null;//TODO payMethed;
	}
	public String get_Store_Name() {
		return store;
	}
//----------- fucntions

//	private void CalcPrice_AndFindDiscouns() {
//		price = 0;
//		for (ProductDetails pd : items) {
//			double add = 0;
//			add += store.getPrice(pd.getName()) * pd.getAmount();
//			List<Discount> discounts = store.getDiscounts(pd.getName());
//			discounts.addAll(discounts);
//			for (Discount discount : discounts) {
//				add = discount.implamentDis(add);
//			}
//			price += add;
//		}
//	}


}
