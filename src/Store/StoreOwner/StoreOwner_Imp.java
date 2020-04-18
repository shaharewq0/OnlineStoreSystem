package Store.StoreOwner;

import Store.Item;
import Store.Store;
import Store.Store_role;
import store_System.User;

import java.util.List;

public class StoreOwner_Imp implements StoreOwner, Store_role {
    Store store;
    StoreOwner boss; // the Owner who appointed current owner, null for original store owner
    List <User> OwnerAppointeis;// Owners who got appointed by current owner, for future use
    List <User> ManagerAppointeis;// managers who got appointed by current owner
    @Override
    public boolean addItem(Item item) {
        return store.addItem(item);
    }

    @Override
    public boolean removeItem(Item item) {
        return store.removeItem(item);
    }

    @Override
    public boolean editItem(Item OLD_item,Item NEW_item) {
        return store.editItem(OLD_item,NEW_item);
    }

    @Override
    public boolean appointOwner(User user) {
        if(user.isOwner()){
            return false;
        }
        else {
            store.appointOwner(user);
            OwnerAppointeis.add(user);
            return true;
        }
    }

    @Override
    public boolean appointManager(User user) {
        if(user.isOwner() | user.isManager()){
            return false;
        }
        else {
            store.appointManager(user);
            ManagerAppointeis.add(user);
            return true;
        }
    }


    @Override
    public <T> void setPremissions(User manager, List<T> Permissions) {

    }

    @Override
    public boolean fire(User manager) {
        if(!ManagerAppointeis.contains(manager))
            return false;
        store.fireManager(manager);
        return true;
    }

    @Override
    public <T> List<T> viewPurchaseHistory() {
        return null;
    }
}
