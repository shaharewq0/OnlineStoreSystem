package Domain.Store.workers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.RedClasses.IUser;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.Roles.Registered;

public class StoreOwner_Imp implements  Store_role {
	private Registered user;
	protected StoreImp store;
	protected String workername = "";
	private Store_role boss; // the Owner who appointed current owner, null for original store owner
	protected Map<String, Store_role> OwnerAppointeis;// Owners who got appointed by current owner, for future use
	protected Map<String, Store_role> ManagerAppointeis;// managers who got appointed by current owner
	// -------------------------------------------------------------------------Contractors

	protected StoreOwner_Imp() {
	}

	public StoreOwner_Imp(Store_role creator, String myname) {
		workername = myname;
		boss = creator;
		store = creator.getStore();
		OwnerAppointeis = new HashMap<String, Store_role>();
		ManagerAppointeis = new HashMap<String, Store_role>();
		EventLogger.GetInstance().Add_Log(this.toString() + "- Created Owner");
	}

	@Override
	public StoreImp getStore() {
		return store;
	}

	// ---------------------------------------------store action
	@Override
	public boolean addItem(Product item) {
		EventLogger.GetInstance().Add_Log(this.toString() + "Owner add item");
		return store.addProduct(item);

	}

	@Override
	public boolean addItem(ProductDetails item) {
		EventLogger.GetInstance().Add_Log(this.toString() + "-Owner add item");
		return store.addProduct(item);
	}

	@Override
	public boolean editItem(String OLD_item, Product NEW_item) {
		EventLogger.GetInstance().Add_Log(this.toString() + "-Owner edit item");
		return store.editProduct(OLD_item, NEW_item);
	}

	@Override
	public boolean removeItem(String prodactname) {
		EventLogger.GetInstance().Add_Log(this.toString() + "-Owner remove item");
		return store.removeProduct(prodactname);
	}

	@Override
	public List<Purchase> getPurchaseHistory() {
		return store.viewPurchaseHistory();

	}

	@Override
	public Collection<Question> viewQuestions() {
		return store.getQuestions();
	}

	@Override
	public boolean giveRespond(String ansewr, int qustionID) {
		EventLogger.GetInstance().Add_Log(this.toString() + "-Owner gives Respond");
		return store.respondToQuestion(ansewr, qustionID);
	}

	// ------------------------------------------------------------Role actions

	@Override
	public boolean appointOwner(IUser user) {
		Store_role newRole = new StoreOwner_Imp(this, user.getName());
		if (user.appointAsOwner(newRole)) {
			OwnerAppointeis.put(user.getName(), newRole);
			EventLogger.GetInstance().Add_Log(this.toString() + "Owner appoint new Owner");
			return true;
		}
		ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Owner");
		return false;
	}

	@Override
	public boolean appointManager(IUser user) {
		Store_role newRole = new StoreManager_Imp(this, user.getName());
		if (user.appointAsManager(newRole)) {
			ManagerAppointeis.put(user.getName(), newRole);
			EventLogger.GetInstance().Add_Log(this.toString() + "Owner appoint new Manager");
			return true;
		}
		ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Manager");
		return false;
	}

	@Override
	public boolean fire(String manager) {
		EventLogger.GetInstance().Add_Log(this.toString() + "Owner fire worker");

		return store.fireManager(manager);

	}

	@Override
	public boolean getfire() {
		boss.IgotFire(workername);
		return user.getFired(store.getName());
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
		EventLogger.GetInstance().Add_Log(this.toString() + "-Owner changed other manager permesions");
		return store.editManagerPermesions(managername, permesions);
	}

	@Override
	public boolean canPromoteToOwner() {
		return false;
	}

	@Override
	public boolean getNewPermesions(List<String> Permesions) {
		ErrorLogger.GetInstance().Add_Log(this.toString()+ "someone try to change my permsions");
		return false;
	}

	@Override
	public String getName() {

		return workername;
	}



}
