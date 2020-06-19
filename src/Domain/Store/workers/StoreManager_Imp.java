package Domain.Store.workers;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
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
        MyJob.grantee = this;
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


    //TODO delte option
    @Override
    public boolean appointOwner(Registered user) {
        StoreOwner_Imp newRole = new StoreOwner_Imp(this, user);
        if (user.appointAsOwner(newRole)) {
            MyJob.store.appointOwner(newRole.myJob);
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
        StoreManager_Imp newRole = new StoreManager_Imp(this, user);
        if (user.appointAsManager(newRole)) {
            MyJob.store.appointManager( newRole.MyJob);
            Manager_Appointees.put(user.getId(), newRole);
            EventLogger.GetInstance().Add_Log(this.toString() + "Owner appoint new Manager");
            return true;
        }
        ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Manager");
        return false;
    }

    @Override
    public boolean fireOwner(String Owner) {
        //TODO need testing
        EventLogger.GetInstance().Add_Log(this.toString() + "Owner fire Owner");
        if (!Owner_Appointees.containsKey(Owner))
            return false;

        return MyJob.store.fireWorker(Owner);
    }

    @Override
    public boolean fireManager(String manager) {
        if (!permission.contains("fire"))
            return false;
        return MyJob.store.fireWorker(manager);
        //return manager.getFired(store.getName());
    }

    @Override
    public boolean getfire() {
        MyJob.grantor.IgotFire(worker_name);

        //TODO check no infinity loop
        for (Store_role Manager : Manager_Appointees.values()) {
            EventLogger.GetInstance().Add_Log(this.toString() + "i try to fire manager " + Manager.getName());
            if (!MyJob.store.fireWorker(Manager.getName()))
                ErrorLogger.GetInstance().Add_Log((this.toString() + "fail to fire manager " + Manager.getName()));
        }

        for (Store_role Owner : Owner_Appointees.values()) {
            EventLogger.GetInstance().Add_Log(this.toString() + "i try to fire manager " + Owner.getName());
            if (!MyJob.store.fireWorker(Owner.getName()))
                ErrorLogger.GetInstance().Add_Log((this.toString() + "fail to fire manager " + Owner.getName()));
        }
        return user.getFired(MyJob.store.getName());

    }

    @Override
    public boolean IgotFire(String worker) {
        if (!permission.contains("appointOwner") || !permission.contains("appointManager"))
            return false;

        if (Owner_Appointees.containsKey(worker)) {
            Owner_Appointees.remove(worker);
            return CheckTegrati_ImMangaer() && true;
        }
        if (Manager_Appointees.containsKey(worker)) {
            Manager_Appointees.remove(worker);
            return CheckTegrati_ImMangaer() && true;
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

    //------------------------------------------------------------
    @Override
    public boolean addDiscount(String discount) {
        if (!permission.contains("addDiscount"))
            return false;
        return getStore().addDiscount(discount);
    }

    @Override
    public boolean removeDiscount(int discountID) {
        if (!permission.contains("removeDiscount"))
            return false;
        return getStore().removeDiscount(discountID);
    }


    @Override
    public boolean addacquisition(String acquisition) {
        if (!permission.contains("addacquisition"))
            return false;
        return getStore().addacquisition(acquisition);
    }

    @Override
    public boolean removeacquisition(int acquisitionID) {
        if (!permission.contains("removeacquisition"))
            return false;
        return getStore().removeacquisition(acquisitionID);
    }

    @Override
    public boolean canPromoteToOwner() {
        return CheckTegrati_ImMangaer() && true;
    }

    public boolean CheckTegrati_ImMangaer() {
        return user != null;

    }

}
