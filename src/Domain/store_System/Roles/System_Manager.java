package Domain.store_System.Roles;

import java.util.List;

import Domain.UserClasses.UserPurchase;
import Domain.Store.StorePurchase;
import Domain.store_System.System;

public class System_Manager implements System_Role {
	private int id;
	public String name;

	public System_Manager(String username) {
		name = username;
	}

	public System_Manager(){}

	@Override
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StorePurchase> getPurchaseHistory(String storeName) {
		return System.getInstance().getPurchaseHistory(storeName);
		// return null;
	}

	public List<UserPurchase> getPurchaseHistoryofUser(String username) {
		return System.getInstance().getUserProfile(username).getPurchesHistory();
	}
}
