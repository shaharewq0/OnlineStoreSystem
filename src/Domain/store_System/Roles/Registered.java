package Domain.store_System.Roles;

import Domain.Logs.ErrorLogger;
import Domain.Store.workers.Creator;
import Domain.Store.workers.StoreManager_Imp;
import Domain.Store.workers.StoreOwner_Imp;
import Domain.Store.workers.Store_role;
import Domain.UserClasses.User;
import Domain.UserClasses.UserPurchase;
import Domain.UserClasses.User_Purchase_History;
import Domain.UserClasses.shoppingCart;
import Domain.store_System.ClintObserver;
import Domain.store_System.MSGObservable;
import Domain.store_System.System;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Registered implements MSGObservable {

    private shoppingCart member_cart= new shoppingCart();
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

    public void setId(String id) {
        this.id = id;
    }

    //DA setters-----------------------------------------
    public void setClints(Map<User, ClintObserver> clints) {
        this.clints = clints;
    }

    public void setMSG_box(List<String> MSG_box) {
        this.MSG_box = MSG_box;
    }

    public void setHistory(User_Purchase_History history) {
        this.history = history;
    }

    public void setStore_roles(Map<String, Store_role> store_roles) {
        this.store_roles = store_roles;
    }

    public void setTempMSG(List<String> tempMSG) {
        TempMSG = tempMSG;
    }
    //DA setters-----------------------------------------


    public Map<User, ClintObserver> getClints() {
        return clints;
    }

    public User_Purchase_History getHistory() {
        return history;
    }

    public List<String> getMSG_box() {
        return MSG_box;
    }

    public List<String> getTempMSG() {
        return TempMSG;
    }

    public Map<String, Store_role> getStore_roles() {
        return store_roles;
    }


    public String getId() {
        return id;
    }

    public void LogLogin(User user, ClintObserver CO) {
        System.MemberLogin.getAndIncrement();
        boolean imManger = false;
        boolean imOwner = false;
        for (Store_role SR: store_roles.values()) {
            if(SR instanceof StoreOwner_Imp)
                imOwner = true;
            if(SR instanceof  StoreManager_Imp)
                imManger = true;
        }
        if(imOwner)
            System.OwnerLogin.getAndIncrement();
        else if (imManger)
            System.ManagerLogin.getAndIncrement();


        clints.put(user,CO);
        if(!MSG_box.isEmpty()) {
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

        add_msg("you have been appointed as owner of the store " + role.getStore().getName());
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

        add_msg("you have been appointed as manager of the store " + role.getStore().getName());
        store_roles.remove(role.getStore().getName());
        store_roles.put(role.getStore().getName(), role);
        return true;
    }

    public void add_msg(String msg) {
        MSG_box.add(msg);
        notifyUser();
    }
//testing

    public void notifyUser(){
        TempMSG = new LinkedList<>(MSG_box);
        //MSG_box = new LinkedList<>();
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
        MSG_box = new LinkedList<>();
        return TempMSG;
    }

    public shoppingCart getMemeberCart() {
        return member_cart;
    }
}
