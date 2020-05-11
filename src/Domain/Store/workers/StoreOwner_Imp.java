package Domain.Store.workers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.RedClasses.IUser;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.info.Question;

public class StoreOwner_Imp implements StoreOwner, Store_role {
	protected StoreImp store;
	protected String workername = "";
	private Store_role boss; // the Owner who appointed current owner, null for original store owner
	protected Map<String, Store_role> OwnerAppointeis;// Owners who got appointed by current owner, for future use
	protected Map<String, Store_role> ManagerAppointeis;// managers who got appointed by current owner
	// -------------------------------------------------------------------------Contractors

	protected StoreOwner_Imp() {}
	
	public StoreOwner_Imp(Store_role creator, String myname) {
		workername = myname;
		boss = creator;
		store = creator.getStore();
		OwnerAppointeis = new HashMap<String, Store_role>();
		ManagerAppointeis = new HashMap<String, Store_role>();
	}

	@Override
	public StoreImp getStore() {
		return store;
	}

	// ---------------------------------------------store action
	@Override
	public boolean addItem(Product item) {
		return store.addProduct(item);

	}

	@Override
	public boolean addItem(ProductDetails item) {
		return store.addProduct(item);
	}

	@Override
	public boolean editItem(String OLD_item, Product NEW_item) {
		return store.editProduct(OLD_item, NEW_item);
	}

	@Override
	public boolean removeItem(String prodactname) {
		return store.removeProduct(prodactname);
	}

	@Override
	public List<Purchase> getPurchaseHistory() {
		return store.viewPurchaseHistory();

	}

	@Override
	public List<Question> viewQuestions() {
		return store.getQuestions();
	}

	@Override
	public boolean giveRespond(String ansewr, int qustionID) {

		return store.respondToQuestion(ansewr, qustionID);
	}

	// ------------------------------------------------------------Role actions

	@Override
	public boolean appointOwner(IUser user) {
		Store_role newRole = new StoreOwner_Imp(this, user.getName());
		if (user.appointAsOwner(newRole)) {
			OwnerAppointeis.put(user.getName(), newRole);
			return true;
		}
		return false;
	}

	@Override
	public boolean appointManager(IUser user) {
		Store_role newRole = new StoreManager_Imp(this, user.getName());
		if (user.appointAsManager(newRole)) {
			ManagerAppointeis.put(user.getName(), newRole);
			return true;
		}
		return false;
	}

	@Override
	public boolean fire(IUser manager) {
		return manager.getFired(store.getName());
	}

	@Override
	public boolean getfire() {
		boss.IgotFire(workername);
		return true;
	}

	@Override
	public boolean IgotFire(String worker) {
		if (OwnerAppointeis.containsKey(worker)) {
			OwnerAppointeis.remove(worker);
			return true;
		}
		if (ManagerAppointeis.containsKey(worker)) {
			ManagerAppointeis.remove(worker);
			return true;
		}
		return false;
	}

	@Override
	public boolean editManagerPermesions(String managername, List<String> permesions) {

		return store.editManagerPermesions(managername, permesions);
	}

	@Override
	public boolean canPromoteToOwner() {
		return false;
	}

}
