package Domain.Store.workers;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.RedClasses.IUser;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.Store.workers.appoints.Appoint_manager;
import Domain.info.MangaerPermesions;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.Roles.Registered;

import java.util.*;

public class StoreManager_Imp implements Store_role {
    private Registered user;
    protected Appoint_manager MyJob = new Appoint_manager();
    protected String worker_name = "";
    // the Owner who appointed current owner, null for original store owner
    protected Map<String, Store_role> Owner_Appointees;// Owners who got appointed by current owner, for future use
    protected Map<String, Store_role> Manager_Appointees;// managers who got appointed by current owner
    private List<String> permission = new LinkedList<String>();
    // -------------------------------------------------------------------------Contractors

    public StoreManager_Imp(Store_role creator, Registered myname) {
        user = myname;
        worker_name = myname.getId();
        MyJob.grantor = creator;
        MyJob.store = creator.getStore();
        Owner_Appointees = new HashMap<>();
        Manager_Appointees = new HashMap<>();
        this.permission.addAll(MangaerPermesions.defult_permesions);
        EventLogger.GetInstance().Add_Log(this.toString() + "- Created Manager");
    }

    public StoreManager_Imp(Store_role creator, String myname, List<String> permisions) {
        worker_name = myname;
        MyJob.grantor = creator;
        MyJob.store = creator.getStore();
        Owner_Appointees = new HashMap<String, Store_role>();
        Manager_Appointees = new HashMap<String, Store_role>();
        this.permission.addAll(permisions);
        EventLogger.GetInstance().Add_Log(this.toString() + "- Created Manager");
    }

    @Override
    public StoreImp getStore() {

        return MyJob.store;
    }

    @Override
    public String getName() {
        return worker_name;
    }

    // ---------------------------------------------store action
    @Override
    public boolean addItem(Product item) {
        if (!permission.contains("addItem"))
            return false;

        return MyJob.store.addProduct(item);

    }

    @Override
    public boolean addItem(ProductDetails item) {
        if (!permission.contains("addItem"))
            return false;
        return MyJob.store.addProduct(item);
    }

    @Override
    public boolean editItem(String OLD_item, Product NEW_item) {
        if (!permission.contains("editItem"))
            return false;
        return MyJob.store.editProduct(OLD_item, NEW_item);
    }

    @Override
    public boolean removeItem(String prodactname) {
        if (!permission.contains("removeItem"))
            return false;
        return MyJob.store.removeProduct(prodactname);
    }

    @Override
    public List<StorePurchase> getPurchaseHistory() {
        if (!permission.contains("getPurchaseHistory"))
            return null;
        return MyJob.store.viewPurchaseHistory();

    }

    @Override
    public Collection<Question> viewQuestions() {
        if (!permission.contains("viewQuestions"))
            return null;
        return MyJob.store.getQuestions();
    }

    @Override
    public boolean giveRespond(String ansewr, int qustionID) {
        if (!permission.contains("giveRespond"))
            return false;

        return MyJob.store.respondToQuestion(ansewr, qustionID);
    }

    // ------------------------------------------------------------Role actions

//	@Override
//	public boolean appointOwner(IUser user) {
//		if (!permission.contains("appointOwner"))
//			return false;
//		Store_role newRole = new StoreOwner_Imp(this, user.getName());
//		if (user.appointAsOwner(newRole)) {
//			MyJob.store.appointOwner((StoreOwner_Imp) newRole);
//			Owner_Appointees.put(user.getName(), newRole);
//			return true;
//		}
//		return false;
//	}

    @Override
    public boolean appointOwner(Registered user) {
        Store_role newRole = new StoreOwner_Imp(this, user);
        if (user.appointAsOwner(newRole)) {
            MyJob.store.appointOwner((StoreOwner_Imp) newRole);
            Owner_Appointees.put(user.getId(), newRole);
            EventLogger.GetInstance().Add_Log(this.toString() + "Owner appoint new Owner");
            return true;
        }
        ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Owner");
        return false;
    }

//	@Override
//	public boolean appointManager(IUser user) {
//		if (!permission.contains("appointManager"))
//			return false;
//		Store_role newRole = new StoreManager_Imp(this, user.getName() );
//		if (user.appointAsManager(newRole)) {
//			MyJob.store.appointManager((StoreManager_Imp) newRole);
//			Manager_Appointees.put(user.getName(), newRole);
//			return true;
//		}
//		return false;
//	}

    @Override
    public boolean appointManager(Registered user) {
        Store_role newRole = new StoreManager_Imp(this, user);
        if (user.appointAsManager(newRole)) {
            MyJob.store.appointManager((StoreManager_Imp) newRole);
            Manager_Appointees.put(user.getId(), newRole);
            EventLogger.GetInstance().Add_Log(this.toString() + "Owner appoint new Manager");
            return true;
        }
        ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Manager");
        return false;
    }

    @Override
    public boolean fire(String manager) {
        if (!permission.contains("fire"))
            return false;
        return MyJob.store.fireManager(manager);
        //return manager.getFired(store.getName());
    }

    @Override
    public boolean getfire() {
        MyJob.grantor.IgotFire(worker_name);
        return user.getFired(MyJob.store.getName());
    }

    @Override
    public boolean IgotFire(String worker) {
        if (!permission.contains("appointOwner") || !permission.contains("appointManager"))
            return false;
        if (Owner_Appointees.containsKey(worker)) {
            Owner_Appointees.remove(worker);
            return true;
        }
        if (Manager_Appointees.containsKey(worker)) {
            Manager_Appointees.remove(worker);
            return true;
        }
        return false;
    }

    @Override
    public boolean editManagerPermesions(String managername, List<String> permesions) {
        if (!permission.contains("editManagerPermesions"))
            return false;

        return MyJob.store.editManagerPermesions(managername, permesions);
    }

    @Override
    public boolean getNewPermesions(List<String> permesions) {
        EventLogger.GetInstance().Add_Log(this.toString() + "- my Permesions have changed");
        this.permission = (new LinkedList<String>());
        this.permission.addAll(permesions);
        return true;

    }

    @Override
    public boolean canPromoteToOwner() {
        return true;
    }

}
