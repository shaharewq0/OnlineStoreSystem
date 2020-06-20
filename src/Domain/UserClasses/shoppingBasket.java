package Domain.UserClasses;

import java.util.*;

import Domain.Store.*;
import Domain.info.ProductDetails;

public class shoppingBasket implements IshoppingBasket {


    //      productName - amount
    // Product = the product we are talking about|| Integer how much of it is in the basket
    private List<Product_boundle> Item_holder;
    private StoreImp store;

    public shoppingBasket(StoreImp store) {
        this.store = store;
        Item_holder = new LinkedList<>();
    }

    public boolean addProduct(String name, int amount) {
        if (store.findProduct_bundleByName(name) == null)
            return false;
        Product_boundle temp = store.findProduct_bundleByName(name);
        if (temp != null) {
            for (Product_boundle PB : Item_holder) {
                if (PB.item.getName().equals(name)) {
                    PB.add(amount);
                    return true;
                }
            }
            Item_holder.add(new Product_boundle(temp.item, amount));
        }
        return true;
    }

    public int removeProduct(String name, int num) {

        for (Product_boundle PB : Item_holder) {
            if (PB.item.getName().equals(name)) {
                return  PB.remove(num);
            }
        }
        return 0;
//        if (Item_holder.containsKey(store.findProductByName(name))) {
//            int current = Item_holder.get(store.findProductByName(name));
//            if (current > num) {
//                Item_holder.replace(store.findProductByName(name), current - num);
//                return num;
//            } else {
//                Item_holder.remove(store.findProductByName(name));
//                return current;
//            }
//        }
//        return 0;

    }

    public StoreImp getStore() {
        return store;
    }

    public List<ProductDetails> getProducts() {
        List<ProductDetails> output = new LinkedList<ProductDetails>();

        for (Product_boundle pb : Item_holder) {
            output.add(new ProductDetails(pb.item, pb.size(), store.getName()));
        }
        return output;
    }

    public double CalcPrice() {
        List<ProductDetails> products = getProducts();
        return store.getPrice(products);
    }

    public List<MyPair<Product_boundle, String>> getItems() {
        List<MyPair<Product_boundle, String>> output = new LinkedList<>();
        for (Product_boundle pb : Item_holder) {
            output.add(store.TakeItem(pb.item.getName(),pb.size()));
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
        boolean b1 = Objects.equals(Item_holder, that.Item_holder);
        boolean b2 = Objects.equals(getStore(), that.getStore());
        return b1 && b2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Item_holder, getStore());
    }
}
