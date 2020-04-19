package Store;

import store_System.User;

import java.util.List;

public interface Store {
    boolean addItem(Item item);
    boolean removeItem(Item item);
    boolean editItem(Item OLD_item,Item NEW_item);
    void appointOwner(User user);
    void appointManager(User user);
    void fireManager(User user);
    List<String> viewPurchaseHistory();
}
