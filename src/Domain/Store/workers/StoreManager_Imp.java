package Domain.Store.workers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.Logs.EventLogger;
import Domain.RedClasses.IUser;
import Domain.Store.Product;
import Domain.Store.Purchase;
import Domain.Store.StoreImp;
import Domain.info.MangaerPermesions;
import Domain.info.ProductDetails;
import Domain.info.Question;

public class StoreManager_Imp implements Store_role {
	protected StoreImp store;
	protected String workername = "";
	private Store_role boss; // the Owner who appointed current owner, null for original store owner
	protected Map<String, Store_role> OwnerAppointeis;// Owners who got appointed by current owner, for future use
	protected Map<String, Store_role> ManagerAppointeis;// managers who got appointed by current owner
	private List<String> permisions = new LinkedList<String>();
	// -------------------------------------------------------------------------Contractors

	public StoreManager_Imp(Store_role creator, String myname) {
		workername = myname;
		boss = creator;
		store = creator.getStore();
		OwnerAppointeis = new HashMap<String, Store_role>();
		ManagerAppointeis = new HashMap<String, Store_role>();
		this.permisions.addAll(MangaerPermesions.defult_permesions);
		EventLogger.GetInstance().Add_Log(this.toString() + "- Created Manager");
	}

	public StoreManager_Imp(Store_role creator, String myname, List<String> permisions) {
		workername = myname;
		boss = creator;
		store = creator.getStore();
		OwnerAppointeis = new HashMap<String, Store_role>();
		ManagerAppointeis = new HashMap<String, Store_role>();
		this.permisions.addAll(permisions);
		EventLogger.GetInstance().Add_Log(this.toString() + "- Created Manager");
	}

	@Override
	public StoreImp getStore() {

		return store;
	}

	@Override
	public String getName() {
		return workername;
	}
	// ---------------------------------------------store action
	@Override
	public boolean addItem(Product item) {
		if (!permisions.contains("addItem"))
			return false;

		return store.addProduct(item);

	}

	@Override
	public boolean addItem(ProductDetails item) {
		if (!permisions.contains("addItem"))
			return false;
		return store.addProduct(item);
	}

	@Override
	public boolean editItem(String OLD_item, Product NEW_item) {
		if (!permisions.contains("editItem"))
			return false;
		return store.editProduct(OLD_item, NEW_item);
	}

	@Override
	public boolean removeItem(String prodactname) {
		if (!permisions.contains("removeItem"))
			return false;
		return store.removeProduct(prodactname);
	}

	@Override
	public List<Purchase> getPurchaseHistory() {
		if (!permisions.contains("getPurchaseHistory"))
			return null;
		return store.viewPurchaseHistory();

	}

	@Override
	public Collection<Question> viewQuestions() {
		if (!permisions.contains("viewQuestions"))
			return null;
		return store.getQuestions();
	}

	@Override
	public boolean giveRespond(String ansewr, int qustionID) {
		if (!permisions.contains("giveRespond"))
			return false;

		return store.respondToQuestion(ansewr, qustionID);
	}

	// ------------------------------------------------------------Role actions

	@Override
	public boolean appointOwner(IUser user) {
		if (!permisions.contains("appointOwner"))
			return false;
		Store_role newRole = new StoreOwner_Imp(this, user.getName());
		if (user.appointAsOwner(newRole)) {
			OwnerAppointeis.put(user.getName(), newRole);
			return true;
		}
		return false;
	}

	@Override
	public boolean appointManager(IUser user) {
		if (!permisions.contains("appointManager"))
			return false;
		Store_role newRole = new StoreManager_Imp(this, user.getName() );
		if (user.appointAsManager(newRole)) {
			ManagerAppointeis.put(user.getName(), newRole);
			return true;
		}
		return false;
	}

	@Override
	public boolean fire(IUser manager) {
		if (!permisions.contains("fire"))
			return false;
		return manager.getFired(store.getName());
	}

	@Override
	public boolean getfire() {
		store.fireManager(this);
		boss.IgotFire(workername);
		return true;
	}

	@Override
	public boolean IgotFire(String worker) {
		if (!permisions.contains("appointOwner") || !permisions.contains("appointManager"))
			return false;
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
		if (!permisions.contains("editManagerPermesions"))
			return false;

		return store.editManagerPermesions(managername, permesions);
	}

	@Override
	public boolean getNewPermesions(List<String> permesions)
	{
		EventLogger.GetInstance().Add_Log(this.toString() + "- my Permesions have changed");
		this.permisions = (new LinkedList<String>());
		this.permisions.addAll(permesions);
		return true;
		
	}
	
	@Override
	public boolean canPromoteToOwner() {
		return true;
	}

}
