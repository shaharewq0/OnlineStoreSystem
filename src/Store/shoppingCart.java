package Store;

import javafx.util.Pair;

import java.util.*;

public class shoppingCart implements IshoppingCart {
    List<shoppingBasket> baskets;

    public shoppingCart(){
        baskets=new LinkedList<>();
    }

    public List<shoppingBasket> getBaskets() {
        return baskets;
    }

    public List<Pair<Product,Integer>> allProductsInCart(){
        List<Pair<Product,Integer>> toReturn = new LinkedList<>();
        for(shoppingBasket b:baskets){
            concat(toReturn,b.getProducts());
        }
        return toReturn;
    }

    private void concat(List<Pair<Product,Integer>> a , List<Pair<Product,Integer>> b){
        for(Pair<Product,Integer> p : b){
            if(!a.contains(p)){
                a.add(p);
            }
        }
    }

    public boolean addBasket(shoppingBasket b){
        if(!baskets.contains(b)){
            baskets.add(b);
            return true;
        }
        return false;
    }
}
