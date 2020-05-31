package Domain.store_System.Roles;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.RedClasses.User;
import Domain.RedClasses.UserPurchase;
import Domain.RedClasses.User_Purchase_History;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;

public class Registered {
	private String id;
	private User_Purchase_History history = new User_Purchase_History();
	//private List<Purchase> myPurcase = new LinkedList<Purchase>();
	public Map<String, Store_role> store_roles = new HashMap<String, Store_role>();

	public Registered(String id) {
		this.id = id;

	}

	public String getId() {
		return id;
	}

	public void LogLogin(User user) {
		// TODO Auto-generated method stub

	}

	public void LogHistory(UserPurchase p) {
		history.history.add(p);
	}

	public List<UserPurchase> getPurchesHistory() {
		return history.history;
	}

	public void LogLogout(User user) {
		// TODO Auto-generated method stub

	}

	public boolean getFired(String name) {
		return store_roles.remove(name) != null;
	}

	public List<String> storesOwned() {
		List<String> stores = new LinkedList<>();

		store_roles.forEach((store, role) -> {
			if (role instanceof StoreOwner_Imp) {
				stores.add(store);
			}
		});

		return  stores;
	}

}
