package Store;

import java.util.List;

public interface IStore {
    public String getName();
    public List<Product> getProducts();
    public String getAddress();
    public boolean addProduct(Product p);
    public Product findProductByName(String name);
    public List<Product> findProductByCategory(String category);
    public List<Product> findProductByKeyword(String keyword);
    public int getRating();
    public boolean removeProduct(Product p);
    public boolean editProduct(Product OLD_p,Product NEW_p);
    public boolean appointOwner(IUser user);
    public boolean appointManager(IUser user);
    public boolean fireManager(IUser user);
    public List<String> viewPurchaseHistory();
}
