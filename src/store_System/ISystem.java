package store_System;

import Store.Store;
import Store.Product;
import java.util.List;

public interface ISystem {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
    public Store getStoreDetails(String name);
    public List<Store> getAllStores();
    public List<Product> getProductsFromStore(String name);
}
