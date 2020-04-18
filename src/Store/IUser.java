package Store;

import javafx.util.Pair;
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
    public List<Product> filterByRating(List<Product> base , int min , int max);
    public List<Product> filterByCategory(List<Product> base , String category);
    public List<Product> filterByStoreRating(List<Product> base , int min, int max);
    public boolean saveProductInBasket(String productName , String storeName);
    public List<Pair<Product,Integer>> getProductsInCart();
    public boolean deleteProductInBasket(String productName , String storeName,int num);
    public boolean purchase();
}
