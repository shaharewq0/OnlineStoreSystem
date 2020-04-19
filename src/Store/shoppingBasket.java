package Store;


import java.util.List;

public class shoppingBasket implements IshoppingBasket {
    private List<MyPair<Product,Integer>> products;
    private Store store;

    public shoppingBasket(Store store) {
        this.store = store;
    }

    public void addProduct(Product p){
        if(p.getStore().getName().equals(store.getName())){
        	MyPair<Product,Integer> toChange = findInBasket(p);
            if(toChange == null){
                products.add(new MyPair<Product, Integer>(p,1));
            }else{
                products.remove(toChange);
                products.add(new MyPair<Product, Integer>(p,toChange.getValue()+1));
            }
        }
    }

    public int removeProduct(Product p , int num){
        if(p.getStore().getName().equals(store.getName())){
        	MyPair<Product,Integer> toChange = findInBasket(p);
            if(toChange == null){
                return 0;
            }else{
                products.remove(toChange);
                if(toChange.getValue()>num) {
                    products.add(new MyPair<Product, Integer>(p, toChange.getValue() - 1));
                }
                return Math.min(toChange.getValue(),num);
            }
        }
        return 0;
    }

    private MyPair<Product,Integer> findInBasket(Product p){
        for (MyPair<Product,Integer> productInBasket : products){
            if(productInBasket.getKey().getName().equals(p.getName())){
                return productInBasket;
            }
        }
        return null;
    }

    public Store getStore() {
        return store;
    }

    public List<MyPair<Product, Integer>> getProducts() {
        return products;
    }
}
