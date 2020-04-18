package Store;

import java.util.List;

public interface IshoppingCart {
    public List<shoppingBasket> getBaskets();
    public boolean addBasket(shoppingBasket b);
}
