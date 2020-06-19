package Domain.UserClasses;


import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.store_System.System;

import java.util.*;

public class shoppingCart implements IshoppingCart {
    Map<String, shoppingBasket> baskets;

    public shoppingCart() {
        baskets = new HashMap<>();
    }

    public Collection<shoppingBasket> getBaskets() {
        return baskets.values();
    }

    public List<ProductDetails> allProductsInCart() {
        List<ProductDetails> output = new LinkedList<ProductDetails>();
        for (shoppingBasket basket : baskets.values()) {
            output.addAll(basket.getProducts());
        }
        return output;
    }

    public shoppingBasket findBasket(String name) {
        if (baskets.containsKey(name))
            return baskets.get(name);
        //else
        StoreImp store = System.getInstance().getStoreDetails(name);
        if(store == null)
            return null;
        baskets.put(name, new shoppingBasket(store));
        if (!CheckTegrati_CartPerStore())
            return null;
        return baskets.get(name);

    }

    public int removeItem(String Storename, String Itemname, int amount) {
        IshoppingBasket basket = baskets.get(Storename);
        if (basket == null)
            return 0;
        return basket.removeProduct(Itemname, amount);
    }

    @Override
    public double CalcPrice() {
        double cost = 0;
        for (shoppingBasket basket : baskets.values()) {
            cost += basket.CalcPrice();
        }
        return cost;
    }

    @Override
    public List<MyPair<Product,String>> getItems() {
        List<MyPair<Product,String>> takeout = new LinkedList<MyPair<Product,String>>();
        for (shoppingBasket basket : baskets.values()) {
            takeout.addAll( basket.getItems());
        }
        return takeout;
    }

    public UserPurchase Complet_Purchase() {
        UserPurchase p = new UserPurchase();
        for (shoppingBasket basket : baskets.values()) {
            p.eachPurchase.add(basket.Complet_Purchase());
        }
        return p;
    }

    //-------------------------------------------------------Tegrati
    public boolean CheckTegrati_CartPerStore() {
        List<String> shops = new LinkedList<>();
        for (shoppingBasket b : baskets.values()) {
            if(shops.contains(b.getStore().getName()))
                return false;
            shops.add(b.getStore().getName());
        }
        return true;
    }

    public boolean CheckItemAvailable() {
        for (shoppingBasket SB : baskets.values()) {
            if (!SB.CheckItemAvailable())
                return false;
        }
        return true;

    }

    public boolean CheckAcquisitions() {
        for (shoppingBasket SB : baskets.values()) {
            if(!SB.CheckAcquisitions())
                return false;
        }
        return true;
    }
}
