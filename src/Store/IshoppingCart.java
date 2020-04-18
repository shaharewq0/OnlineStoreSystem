package Store;

import javafx.util.Pair;

import java.util.List;

public interface IshoppingCart {
    public List<shoppingBasket> getBaskets();
    public boolean addBasket(shoppingBasket b);
    public List<Pair<Product,Integer>> allProductsInCart();
}
