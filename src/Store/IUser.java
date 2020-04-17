package Store;

import java.util.List;

public interface IUser {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
    public Store watchStoreDetails(String name);
    public List<Store> watchAllStores();
    public List<Product> watchProductsInStore(String name);
}
