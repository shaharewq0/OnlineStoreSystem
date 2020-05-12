package Domain.store_System.Roles;

import java.util.List;

import Domain.Store.Purchase;
import Domain.store_System.System;

public class System_Manager {// extends Member {
	public String name;

	public System_Manager(String username) {
		name = username;
	}

	public List<Purchase> getPurchaseHistory(String storeName) {
		return System.getInstance().getPurchaseHistory(storeName);
		// return null;
	}

	public List<Purchase> getPurchaseHistoryofUser(String username) {
		return System.getInstance().getUser(username).getPurchaseHistory();
	}
}
