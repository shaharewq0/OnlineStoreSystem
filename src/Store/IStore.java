package Store;

import java.util.List;

public interface IStore {
    public String getName();
    public List<Product> getProducts();
    public String getAddress();
    public boolean addProduct(Product p);
    public List<Product> findProductByName(String name);
}
