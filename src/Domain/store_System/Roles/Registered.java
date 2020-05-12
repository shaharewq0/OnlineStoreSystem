package Domain.store_System.Roles;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.RedClasses.User;
import Domain.Store.Purchase;
import Domain.Store.workers.Store_role;

public class Registered {
	private String id;
	private List<Purchase> myPurcase = new LinkedList<Purchase>();
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

	public void LogHistory(Purchase p) {
		myPurcase.add(p);
	}

	public List<Purchase> getPurchesHistory() {
		return myPurcase;
	}

	public void LogLogout(User user) {
		// TODO Auto-generated method stub

	}

	public boolean getFired(String name) {
		return store_roles.remove(name) != null;

	}

}
