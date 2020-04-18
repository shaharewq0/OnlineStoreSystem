package Store;

import javafx.util.Pair;
import java.util.List;

public interface IshoppingBasket {
    public void addProduct(Product p);
    public Store getStore();
    public List<Pair<Product, Integer>> getProducts();
    public int removeProduct(Product p , int num);
}
