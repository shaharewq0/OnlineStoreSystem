package Store;

import java.util.List;

public interface IUser {
    public boolean register(int id, String password);
    public boolean login(int id, String password);
    public Store watchStoreDetails(String name);
    public List<Store> watchAllStores();
    public List<Product> watchProductsInStore(String name);
    public List<Product> searchProductsByName(String name);
    public List<Product> searchProductsByCategory(String category);
    public List<Product> searchProductsByKeyword(String keyword);
    public List<Product> filterByPrice(List<Product> base , int min , int max);
}
