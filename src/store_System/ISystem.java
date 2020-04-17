package store_System;

import Store.Store;
import java.util.List;

public interface ISystem {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
    public Store getStoreDetails(String name);
    public List<Store> getAllStores();
}
