package Domain.store_System.Roles;

import java.util.List;

import Domain.RedClasses.UserPurchase;
import Domain.Store.StorePurchase;
import Domain.store_System.System;

public class System_Manager {// extends Member {
	public String name;

	public System_Manager(String username) {
		name = username;
	}

	public List<StorePurchase> getPurchaseHistory(String storeName) {
		return System.getInstance().getPurchaseHistory(storeName);
		// return null;
	}

	public List<UserPurchase> getPurchaseHistoryofUser(String username) {
		return System.getInstance().getUser(username).getPurchaseHistory();
	}
}
