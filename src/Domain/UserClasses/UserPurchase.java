package Domain.UserClasses;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import Domain.Store.StorePurchase;

public class UserPurchase {

	public List<StorePurchase> eachPurchase = new LinkedList<StorePurchase>();
	public double TotalePrice = 0;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserPurchase that = (UserPurchase) o;
		return Double.compare(that.TotalePrice, TotalePrice) == 0 &&
				Objects.equals(eachPurchase, that.eachPurchase);
	}

	@Override
	public int hashCode() {
		return Objects.hash(eachPurchase, TotalePrice);
	}
}
