package Domain.UserClasses;

import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;

import java.util.*;

public class shoppingBasket implements IshoppingBasket {


    //      productName - amount
    private Map<Product, Integer> Item_holder;
    private StoreImp store;

    public shoppingBasket(StoreImp store) {
        this.store = store;
        Item_holder = new HashMap<>();
    }

    public boolean addProduct(String name, int amount) {
        if(store.findProductByName(name)== null)
            return false;
        if (store.findProductByName(name) != null)
            if (Item_holder.containsKey(store.findProductByName(name))) {
                Item_holder.replace(store.findProductByName(name), Item_holder.get(store.findProductByName(name)) + amount);
            } else {
                Item_holder.put(store.findProductByName(name), amount);
            }
        return true;
    }

    public int removeProduct(String name, int num) {
        if (Item_holder.containsKey(store.findProductByName(name))) {
            int current = Item_holder.get(store.findProductByName(name));
            if (current > num) {
                Item_holder.replace(store.findProductByName(name), current - num);
                return num;
            } else {
                Item_holder.remove(store.findProductByName(name));
                return current;
            }
        }
        return 0;

    }

    public StoreImp getStore() {
        return store;
    }

    public List<ProductDetails> getProducts() {
        List<ProductDetails> output = new LinkedList<ProductDetails>();
        for (Product product : Item_holder.keySet()) {
            output.add(new ProductDetails(product, Item_holder.get(product),store.getName()));
        }
        return output;
    }

    public double CalcPrice() {
        List<ProductDetails> products = getProducts();
        return store.getPrice(products);
    }

    public List<MyPair<Product,String>> getItems() {
        List<MyPair<Product,String>> output = new LinkedList<MyPair<Product,String>>();
        for (Product item : Item_holder.keySet()) {
            output.add(store.TakeItem(item.getName(), Item_holder.get(item)));
        }
        return output;
    }


    public StorePurchase Complet_Purchase() {

        StorePurchase SP = new StorePurchase(getProducts(), store.getName(), CalcPrice());
        store.addPurchase(SP);
        return SP;
    }

    public boolean CheckItemAvailable() {
        for (ProductDetails details : getProducts()) {
            // StoreImp s = s
            if (!store.CheckItemAvailable(details)) {
                return false;
            }
        }
        return true;
    }

    public boolean CheckAcquisitions() {
        if (!store.CheckAcquisitions(getProducts()))
            return false;
        return true;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        shoppingBasket that = (shoppingBasket) o;
        boolean b1 =  Objects.equals(Item_holder, that.Item_holder);
        boolean b2 =  Objects.equals(getStore(), that.getStore());
        return b1 && b2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Item_holder, getStore());
    }
}
