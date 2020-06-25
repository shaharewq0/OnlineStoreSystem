package Domain.Store;

import java.util.LinkedList;
import java.util.List;

public class Store_Purchase_History {
	private int id;
	List<StorePurchase> PastPurchase;

	public Store_Purchase_History(){
		PastPurchase= new LinkedList<StorePurchase>();
	}

	public List<StorePurchase> getPastPurchase() {
		return PastPurchase;
	}

	public void setPastPurchase(List<StorePurchase> pastPurchase) {
		PastPurchase = pastPurchase;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
