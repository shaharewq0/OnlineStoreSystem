package Store;


import java.util.*;

public class shoppingCart implements IshoppingCart {
    List<shoppingBasket> baskets;

    public shoppingCart(){
        baskets=new LinkedList<>();
    }

    public List<shoppingBasket> getBaskets() {
        return baskets;
    }

    public List<MyPair<Product,Integer>> allProductsInCart(){
        List<MyPair<Product,Integer>> toReturn = new LinkedList<>();
        for(shoppingBasket b:baskets){
            concat(toReturn,b.getProducts());
        }
        return toReturn;
    }

    // connect 2 lists
    private void concat(List<MyPair<Product,Integer>> a , List<MyPair<Product,Integer>> b){
        for(MyPair<Product,Integer> p : b){
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

    public shoppingBasket findBasket(StoreImp s){
        for(shoppingBasket basket : getBaskets()){
            if(basket.getStore().getName().equals(s.getName())){
                return basket;
            }
        }
        return null;
    }
}
