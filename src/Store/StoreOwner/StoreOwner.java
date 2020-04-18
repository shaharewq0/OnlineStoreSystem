package Store.StoreOwner;

import Store.Item;
import store_System.User;

import java.util.List;

public interface StoreOwner {
    boolean addItem(Item item);
    boolean removeItem(Item item);
    boolean editItem(Item OLD_item,Item NEW_item);
    boolean appointOwner(User user);
    boolean appointManager(User user);
    <T> void setPremissions(User manager, List<T> Permissions); //generic for now
    boolean fire(User manager);
    <T> List<T> viewPurchaseHistory();//generic for now
}
