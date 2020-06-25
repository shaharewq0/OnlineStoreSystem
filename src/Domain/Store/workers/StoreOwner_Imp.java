package Domain.Store.workers;

import Domain.Logs.ErrorLogger;
import Domain.Logs.EventLogger;
import Domain.Policies.Acquisitions.Acquisition;
import Domain.Policies.Discounts.Discount;
import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.Store.workers.appoints.Appoint_Owner;
import Domain.Store.workers.appoints.Pending_appoint_Owner;
import Domain.info.MangaerPermesions;
import Domain.info.ProductDetails;
import Domain.info.Question;
import Domain.store_System.Roles.Registered;

import java.util.*;

public class StoreOwner_Imp implements Store_role {

    private int id;
    protected Registered user;
    public Appoint_Owner myJob = new Appoint_Owner();
    //	protected StoreImp store;
//	private Store_role boss;
    protected String workername = "";
    // the Owner who appointed current owner, null for original store owner
    protected Map<String, Store_role> OwnerAppointeis;// Owners who got appointed by current owner, for future use
    protected Map<String, Pending_appoint_Owner> OwnerPendingAppointeis = new HashMap<>();//users that i want to make manager
    protected Map<String, Store_role> ManagerAppointeis;// managers who got appointed by current owner

    protected Map<String, Pending_appoint_Owner> newOwners = new HashMap<>();//users that are waiting my accept to become an owner
    // -------------------------------------------------------------------------Contractors

    public StoreOwner_Imp() {
    }

    public int getId() {
        return id;
    }

    public Registered getUser() {
        return user;
    }

    public Appoint_Owner getMyJob() {
        return myJob;
    }

    public String getWorkername() {
        return workername;
    }

    public Map<String, Store_role> getOwnerAppointeis() {
        return OwnerAppointeis;
    }

    public Map<String, Pending_appoint_Owner> getOwnerPendingAppointeis() {
        return OwnerPendingAppointeis;
    }

    public Map<String, Store_role> getManagerAppointeis() {
        return ManagerAppointeis;
    }

    public Map<String, Pending_appoint_Owner> getNewOwners() {
        return newOwners;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(Registered user) {
        this.user = user;
    }

    public void setManagerAppointeis(Map<String, Store_role> managerAppointeis) {
        ManagerAppointeis = managerAppointeis;
    }

    public void setMyJob(Appoint_Owner myJob) {
        this.myJob = myJob;
    }

    public void setNewOwners(Map<String, Pending_appoint_Owner> newOwners) {
        this.newOwners = newOwners;
    }

    public void setOwnerAppointeis(Map<String, Store_role> ownerAppointeis) {
        OwnerAppointeis = ownerAppointeis;
    }

    public void setOwnerPendingAppointeis(Map<String, Pending_appoint_Owner> ownerPendingAppointeis) {
        OwnerPendingAppointeis = ownerPendingAppointeis;
    }


    public StoreOwner_Imp(Store_role creator, Registered myname) {
        user = myname;
        workername = myname.getId();
        myJob.grantor = creator;
        myJob.store = creator.getStore();
        OwnerAppointeis = new HashMap<>();
        ManagerAppointeis = new HashMap<>();
        myJob.grantee = this;
        EventLogger.GetInstance().Add_Log(this.toString() + "- Created Owner");

    }

    @Override
    public StoreImp getStore() {
        return myJob.store;
    }

    // ---------------------------------------------store action
    @Override
    public boolean addItem(Product_boundle item) {
        EventLogger.GetInstance().Add_Log(this.toString() + "Owner add item");
        return myJob.store.addProduct_bundle(item);

    }

    @Override
    public boolean addItem(ProductDetails item) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-Owner add item");
        return myJob.store.addProduct(item);
    }

    @Override
    public boolean editItem(String OLD_item, ProductDetails NEW_item) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-Owner edit item");
        return myJob.store.editProduct(OLD_item, NEW_item);
    }

    @Override
    public boolean removeItem(String prodactname) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-Owner remove item");
        return myJob.store.removeProduct(prodactname);
    }

    @Override
    public List<StorePurchase> getPurchaseHistory() {
        return myJob.store.viewPurchaseHistory();

    }

    @Override
    public Collection<Question> viewQuestions() {
        return myJob.store.getQuestions();
    }

    @Override
    public boolean giveRespond(String ansewr, int qustionID) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-Owner gives Respond");
        return myJob.store.respondToQuestion(ansewr, qustionID);
    }

    // ------------------------------------------------------------Role actions


    @Override
    public boolean appointOwner(Registered user) {
        //when creating a new pendint appoint - it sends itself into store and all other owners that needs to confirm
        Pending_appoint_Owner appointment = new Pending_appoint_Owner(this, user, getStore());
        OwnerPendingAppointeis.put(user.getId(), appointment);
        return appointment.Accept(this);
        //TODO move to the pending appoint
//        if (user.appointAsOwner(newRole)) {
//            myJob.store.appointOwner(newRole);
        //           OwnerAppointeis.put(user.getId(), newRole);
//            EventLogger.GetInstance().Add_Log(this.toString() + "Owner appoint new Owner");
//            return true;
//        }
//        ErrorLogger.GetInstance().Add_Log(this.toString() + "Owner Failed to appoint new Owner");
//        return false;
    }

    public void finalise_appoint(Pending_appoint_Owner pending) {
        StoreOwner_Imp newRole = new StoreOwner_Imp(this, pending.grantee);
        if (OwnerPendingAppointeis.remove(pending.grantee.getId()) == null)
            ErrorLogger.GetInstance().Add_Log(this.toString() + ": Owner appointed without pending ");
        OwnerAppointeis.put(user.getId(), newRole);
    }


    @Override
    public boolean appointManager(Registered user) {
        StoreManager_Imp newRole = new StoreManager_Imp(this, user);
        if (user.appointAsManager(newRole)) {
            myJob.store.appointManager(newRole.MyJob);
            ManagerAppointeis.put(user.getId(), newRole);
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
        if (!OwnerAppointeis.containsKey(Owner))
            return false;

        return myJob.store.fireWorker(Owner);
    }


    @Override
    public boolean fireManager(String manager) {
        EventLogger.GetInstance().Add_Log(this.toString() + "Owner fire Manager");
        if (!ManagerAppointeis.containsKey(manager))
            return false;

        return myJob.store.fireWorker(manager);

    }

    @Override
    public boolean getfire() {
        myJob.grantor.IgotFire(workername);
        //TODO check no infinity loop
        for (Store_role Manager : ManagerAppointeis.values()) {
            EventLogger.GetInstance().Add_Log(this.toString() + "i try to fire manager " + Manager.getName());
            if (!myJob.store.fireWorker(Manager.getName()))
                ErrorLogger.GetInstance().Add_Log((this.toString() + "fail to fire manager " + Manager.getName()));
        }

        for (Store_role Owner : OwnerAppointeis.values()) {
            EventLogger.GetInstance().Add_Log(this.toString() + "i try to fire manager " + Owner.getName());
            if (!myJob.store.fireWorker(Owner.getName()))
                ErrorLogger.GetInstance().Add_Log((this.toString() + "fail to fire manager " + Owner.getName()));
        }
        return user.getFired(myJob.store.getName());
    }

    //when someone you appoint gets fired and he notify you
    @Override
    public boolean IgotFire(String worker) {
        if (OwnerAppointeis.containsKey(worker)) {
            OwnerAppointeis.remove(worker);
            return CheckTegrati_ImMangaer() && true;
        }
        if (ManagerAppointeis.containsKey(worker)) {
            ManagerAppointeis.remove(worker);
            return CheckTegrati_ImMangaer() && true;
        }
        return false;
    }

    @Override
    public boolean editManagerPermesions(String managername, List<String> permesions) {
        EventLogger.GetInstance().Add_Log(this.toString() + "-Owner changed other manager permesions");
        return myJob.store.editManagerPermesions(managername, permesions);
    }

    @Override
    public List<String> getManagerPermesions() {
        return Arrays.asList(MangaerPermesions.permesions);
    }

    @Override
    public boolean canPromoteToOwner() {
        return false;
    }

    @Override
    public boolean getNewPermesions(List<String> Permesions) {
        ErrorLogger.GetInstance().Add_Log(this.toString() + "someone try to change my permsions");
        return false;
    }

    @Override
    public boolean addDiscount(Discount discount) {
        return getStore().addDiscount(discount);
    }

    @Override
    public String getDiscounts() {
        return getStore().getDiscounts();
    }

    @Override
    public boolean removeDiscount(int discountID) {
        return getStore().removeDiscount(discountID);
    }

    @Override
    public String getName() {

        return workername;
    }

    @Override
    public boolean addacquisition(Acquisition acquisition) {
        return getStore().addacquisition(acquisition);
    }

    @Override
    public boolean removeacquisition(int acquisitionID) {
        return getStore().removeacquisition(acquisitionID);
    }

    @Override
    public String getAcquisition() {
        return getStore().getAcquisitions();
    }

    public boolean CheckTegrati_ImMangaer() {
        return user != null;
    }

    //this tells me that im waiting for you to confirm my appointment
    //(idealy this needs to move into store)
    public void needconfirmstion(Pending_appoint_Owner pending_appoint_owner) {
        //TODO
        newOwners.put(pending_appoint_owner.grantee.getId(), pending_appoint_owner);
        user.add_msg("a new Owner is being appointed for store:" + getStore().getName()
                + "- required your confirmation - " + pending_appoint_owner.grantee.getId());
    }

    @Override
    public String getType() {
        return "owner";
    }

    //TODO
    public boolean confirmOwner(String newOnwerName) {
        if (!newOwners.containsKey(newOnwerName))
            return false;
        boolean output = newOwners.get(newOnwerName).Accept(this);
        newOwners.remove(newOnwerName);
        return output;
    }

    public Collection<String> getWaitingAccep()
    {
        return newOwners.keySet();
    }
}
