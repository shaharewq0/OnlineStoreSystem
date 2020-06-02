package Domain.UserClasses;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Domain.Store.Discount;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;

public class shoppingBasket implements IshoppingBasket {


    //      productName - amount
    private Map<String, Integer> Item_holder;
    private StoreImp store;

    public shoppingBasket(StoreImp store) {
        this.store = store;
        Item_holder = new HashMap<>();
    }

    public void addProduct(String name, int amount) {
        if (store.findProductByName(name) != null)
            if (Item_holder.containsKey(name)) {
                Item_holder.replace(name, Item_holder.get(name) + amount);
            } else {
                Item_holder.put(name, amount);
            }
    }

    public int removeProduct(String name, int num) {
        if (Item_holder.containsKey(name)) {
            int current = Item_holder.get(name);
            if (current > num) {
                Item_holder.replace(name, current - num);
                return num;
            } else {
                Item_holder.remove(name);
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
        for (String name : Item_holder.keySet()) {
            output.add(new ProductDetails(store.findProductByName(name), Item_holder.get(name)));
        }
        return output;
    }

    public double CalcPrice() {
        List<ProductDetails> products = getProducts();
        return store.getPrice(products);
    }

    public List<Product> getItems() {
        List<Product> output = new LinkedList<Product>();
        for (String item : Item_holder.keySet()) {
            output.add(store.TakeItem(item, Item_holder.get(item)));
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
}