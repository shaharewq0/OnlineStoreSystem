package Store;

import javafx.util.Pair;
import java.util.List;

public class shoppingBasket implements IshoppingBasket {
    private List<Pair<Product,Integer>> products;
    private Store store;

    public shoppingBasket(Store store) {
        this.store = store;
    }

    public void addProduct(Product p){
        if(p.getStore().getName().equals(store.getName())){
            Pair<Product,Integer> toChange = findInBasket(p);
            if(toChange == null){
                products.add(new Pair<Product, Integer>(p,1));
            }else{
                products.remove(toChange);
                products.add(new Pair<Product, Integer>(p,toChange.getValue()+1));
            }
        }
    }

    public int removeProduct(Product p , int num){
        if(p.getStore().getName().equals(store.getName())){
            Pair<Product,Integer> toChange = findInBasket(p);
            if(toChange == null){
                return 0;
            }else{
                products.remove(toChange);
                if(toChange.getValue()>num) {
                    products.add(new Pair<Product, Integer>(p, toChange.getValue() - 1));
                }
                return Math.min(toChange.getValue(),num);
            }
        }
        return 0;
    }

    private Pair<Product,Integer> findInBasket(Product p){
        for (Pair<Product,Integer> productInBasket : products){
            if(productInBasket.getKey().getName().equals(p.getName())){
                return productInBasket;
            }
        }
        return null;
    }

    public Store getStore() {
        return store;
    }

    public List<Pair<Product, Integer>> getProducts() {
        return products;
    }
}
