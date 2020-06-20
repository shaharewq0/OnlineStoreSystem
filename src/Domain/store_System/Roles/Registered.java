package Domain.store_System.Roles;

import Domain.Logs.ErrorLogger;
import Domain.Store.workers.Creator;
import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;
import Domain.UserClasses.User;
import Domain.UserClasses.UserPurchase;
import Domain.UserClasses.User_Purchase_History;
import Domain.store_System.ClintObserver;
import Domain.store_System.MSGObservable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Registered implements MSGObservable {
    private String id;
    private Map<User,ClintObserver> clints = new HashMap<>();
    private List<String> MSG_box = new LinkedList<>();
    private List<String> TempMSG = new LinkedList<>();
    private User_Purchase_History history = new User_Purchase_History();
    public Map<String, Store_role> store_roles = new HashMap<String, Store_role>();

    // clint A.notfi --- Clint C.notfi
    //private List<Purchase> myPurcase = new LinkedList<Purchase>();


    public Registered(String id) {
        this.id = id;
        add_msg("welcome to the system");

    }


    public String getId() {
        return id;
    }

    public void LogLogin(User user, ClintObserver CO) {
        clints.put(user,CO);
        if(!MSG_box.isEmpty()) {
            TempMSG = MSG_box;
            MSG_box = new LinkedList<>();
            CO.Notifi_me(this);
        }
    }

    public void LogLogout(User user) {
        if(clints.remove(user) == null)
            ErrorLogger.GetInstance().Add_Log(this.toString() + "- when logout removing absorver that dont exsist");
    }

    //TODO
    public void LogHistory(UserPurchase p) {
        history.history.add(p);
    }

    public List<UserPurchase> getPurchesHistory() {
        return history.history;
    }



    public boolean getFired(String Storename) {
        add_msg("you where fired from " + Storename);
        return store_roles.remove(Storename) != null;
    }

    public List<String> storesOwned() {
        List<String> stores = new LinkedList<>();

        store_roles.forEach((store, role) -> {
            if (role instanceof StoreOwner_Imp || role instanceof StoreManager_Imp) {
                stores.add(store);
            }
        });

        return stores;
    }

    public List<String> roles() {
        List<String> types = new LinkedList<>();

        store_roles.forEach((store, role) -> {
            String type = role.getType();
            if (!types.contains(type)) {
                types.add(type);
            }
        });

        return types;
    }


    //TODO
    public boolean appointAsCreator(Creator role) {
        if (store_roles.containsKey(role.getStore().getName())) {
                ErrorLogger.GetInstance().Add_Log(this.toString() + " fatel error : im trying to beacome creator of exsisting store" );
                return false; // already owner

        }

        store_roles.put(role.getStore().getName(), role);
        return true;

    }

    public boolean appointAsOwner(Store_role role) {
        if (store_roles.containsKey(role.getStore().getName())) {
            if (store_roles.get(role.getStore().getName()) instanceof StoreOwner_Imp) {
                return false; // already owner
            }
        }

        store_roles.remove(role.getStore().getName());
        store_roles.put(role.getStore().getName(), role);
        return true;

    }

    public boolean appointAsManager(Store_role role) {

        if (store_roles.containsKey(role.getStore().getName())
                && !store_roles.get(role.getStore().getName()).canPromoteToOwner()) {
            return false;
        }

        if (store_roles.containsKey(role.getStore().getName())) {
            if (store_roles.get(role.getStore().getName()) instanceof StoreManager_Imp) {
                return false; // already manager
            }
        }

        store_roles.remove(role.getStore().getName());
        store_roles.put(role.getStore().getName(), role);
        return true;
    }

    public void add_msg(String msg) {
        MSG_box.add(msg);
        notifyUser();
    }

    public void notifyUser(){
        TempMSG = MSG_box;
        MSG_box = new LinkedList<>();
        for (ClintObserver CO: clints.values()) {
            CO.Notifi_me(this);
        }
    }

//    public boolean add_Observer(ClintObserver O) {
//        clints.add(O);
//        return true;
//    }

//    public boolean remove_Observer(ClintObserver O) {
//
//        if (clints.contains(O)) {
//            clints.remove(O);
//            return true;
//        }
//        return false;
//
//    }

    public List<String> getMessges() {
        return TempMSG;
    }
}
