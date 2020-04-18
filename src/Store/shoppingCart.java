package Store;

import java.util.LinkedList;
import java.util.List;

public class shoppingCart implements IshoppingCart {
    List<shoppingBasket> baskets;

    public shoppingCart(){
        baskets=new LinkedList<>();
    }

    public List<shoppingBasket> getBaskets() {
        return baskets;
    }

    public boolean addBasket(shoppingBasket b){
        if(!baskets.contains(b)){
            baskets.add(b);
            return true;
        }
        return false;
    }
}
