package Domain.Store.workers;

import java.util.LinkedList;
import java.util.List;

import Domain.RedClasses.IUser;
import Domain.Store.IStore;
import Domain.Store.Product;
import Domain.Store.Purchase;

public class StoreOwner_Imp implements StoreOwner, Store_role {
	IStore store;
	StoreOwner boss; // the Owner who appointed current owner, null for original store owner
	List<IUser> OwnerAppointeis;// Owners who got appointed by current owner, for future use
	List<IUser> ManagerAppointeis;// managers who got appointed by current owner

	public StoreOwner_Imp(IStore store) {
		this.store = store;
		boss = null;
		OwnerAppointeis = new LinkedList<>();
		ManagerAppointeis = new LinkedList<>();
	}

	public StoreOwner_Imp(IStore store, StoreOwner boss) {
		this.store = store;
		this.boss = boss;
		OwnerAppointeis = new LinkedList<>();
		ManagerAppointeis = new LinkedList<>();
	}

	@Override
	public boolean addItem(Product item) {
		return store.addProduct(item);
	}

	@Override
	public boolean removeItem(Product item) {
		return store.removeProduct(item.getName());
	}

	@Override
	public boolean editItem(String OLD_item, Product NEW_item) {
		return store.editProduct(OLD_item, NEW_item);
	}

	@Override
	public boolean appointOwner(IUser user) {
//		if (user.isOwner() | !user.isRegistered()) {
//			return false;
//		} else {
//			store.appointOwner(user);
//			OwnerAppointeis.add(user);
//			return true;
//		}
		return false;
	}

	@Override
	public boolean appointManager(IUser user) {
//		if (user.isOwner() | user.isManager() | !user.isRegistered()) {
//			return false;
//		} else {
//			store.appointManager(user);
//			ManagerAppointeis.add(user);
//			return true;
//		}
		return false;
	}

	@Override
	public <T> void setPremissions(IUser manager, List<T> Permissions) {

	}

	@Override
	public boolean fire(IUser manager) {
		if (!ManagerAppointeis.contains(manager))
			return false;
		store.fireManager(manager);
		return true;
	}



	@Override
	public boolean removeItem(String prodactname) {
		return store.removeProduct(prodactname);

	}

	
	@Override
	public List<Purchase> getPurchaseHistory() {
		return store.viewPurchaseHistory();
	}
}
