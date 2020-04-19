package Store.StoreOwner;

import Store.Product;
import Store.IUser;

import java.util.List;

public interface StoreOwner {
    boolean addItem(Product item);
    boolean removeItem(Product item);
    boolean editItem(Product OLD_item, Product NEW_item);
    boolean appointOwner(IUser user);
    boolean appointManager(IUser user);
    <T> void setPremissions(IUser manager, List<T> Permissions); //generic for now
    boolean fire(IUser manager);
    List<String>  viewPurchaseHistory();
}
