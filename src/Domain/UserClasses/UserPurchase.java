package Domain.UserClasses;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Domain.Store.StorePurchase;

public class UserPurchase {
	private int id;
	public List<StorePurchase> eachPurchase;
	public double TotalPrice;

	public UserPurchase(){
		eachPurchase=new LinkedList<>();
		TotalPrice =0;
	}

	public int getId() {
		return id;
	}

	public List<StorePurchase> getEachPurchase() {
		return eachPurchase;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEachPurchase(List<StorePurchase> eachPurchase) {
		this.eachPurchase = eachPurchase;
	}

	public void setTotalPrice(double totalPrice) {
		TotalPrice = totalPrice;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserPurchase that = (UserPurchase) o;
		return Double.compare(that.TotalPrice, TotalPrice) == 0 &&
				Objects.equals(eachPurchase, that.eachPurchase);
	}

	@Override
	public int hashCode() {
		return Objects.hash(eachPurchase, TotalPrice);
	}

	@Override
	public String toString() {
		return "UserPurchase{" +
				"eachPurchase=" + eachPurchase +
				", TotalePrice=" + TotalePrice +
				'}';
	}
}
