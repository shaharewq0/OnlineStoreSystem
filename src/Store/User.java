package Store;

import javafx.util.Pair;
import store_System.*;
import store_System.System;

import java.util.LinkedList;
import java.util.List;

public class User implements IUser {
    private System_Role system_role;
    private shoppingCart cart;
    private int id;
    private String address;
    private int creditCardNum;
    private List<Store_role> store_roles;

    public User(String address,int creditCardNum){
        system_role = Guest.getInstance();
        cart= new shoppingCart();
        this.address=address;
        this.creditCardNum=creditCardNum;
        store_roles = new LinkedList<>();
    }

    public boolean register(int id, String password){
        if(system_role == Guest.getInstance()){
            boolean reg = System.getInstance().register(id,password);
            if(reg){
                system_role = Registered.getInstance();
                return true;
            }
        }
        return false;
    }

    public boolean login(int id,String password){
        if(system_role == Registered.getInstance()){
            boolean log = System.getInstance().login(id,password);
            if(log){
                system_role = Member.getInstance();
                this.id=id;
                return true;
            }
        }
        return false;
    }

    public Store watchStoreDetails(String name){
        return System.getInstance().getStoreDetails(name);
    }

    public List<Store> watchAllStores(){
        return System.getInstance().getAllStores();
    }

    public List<Product> watchProductsInStore(String name){
        return System.getInstance().getProductsFromStore(name);
    }

    public List<Product> searchProductsByName(String name){
        return System.getInstance().searchProductsByName(name);
    }

    public List<Product> searchProductsByCategory(String category){
        return System.getInstance().searchProductsByCategory(category);
    }

    public List<Product> searchProductsByKeyword(String keyword){
        return System.getInstance().searchProductsByKeyword(keyword);
    }

    public List<Product> filterByPrice(List<Product> base , int min , int max){
        return System.getInstance().filterByPrice(base,min,max);
    }
    public List<Product> filterByRating(List<Product> base , int min , int max){
        return System.getInstance().filterByRating(base,min,max);
    }

    public List<Product> filterByCategory(List<Product> base , String category){
        return System.getInstance().filterByCategory(base,category);
    }

    public List<Product> filterByStoreRating(List<Product> base , int min, int max){
        return System.getInstance().filterByStoreRating(base,min,max);
    }

    //adding a product to a basket. if the product exists add 1 to the amount of the product in the basket
    public boolean saveProductInBasket(String productName , String storeName){
        Store myStore = System.getInstance().getStoreDetails(storeName);
        if(myStore==null){
            return false;
        }
        List<Product> Products = System.getInstance().searchProductsByName(productName);
        Product toSave = null;
        for(Product p : Products){
            if(p.getStore().getName().equals(storeName) & myStore.getProducts().contains(p)){
                toSave=p;
            }
        }
        if(toSave == null){
            return false;
        }
        shoppingBasket toAdd = cart.findBasket(myStore);
        if(toAdd == null){
            toAdd= new shoppingBasket(myStore);
            toAdd.addProduct(toSave);
            cart.addBasket(toAdd);
            return true;
        }
        toAdd.addProduct(toSave);
        return true;
    }

    //removing at most amount of num of a product from the basket
    public boolean deleteProductInBasket(String productName , String storeName,int num){
        Store myStore = System.getInstance().getStoreDetails(storeName);
        if(myStore==null){
            return false;
        }
        List<Product> Products = System.getInstance().searchProductsByName(productName);
        Product toDelete = null;
        for(Product p : Products){
            if(p.getStore().getName().equals(storeName) & myStore.getProducts().contains(p)){
                toDelete=p;
            }
        }
        if(toDelete == null){
            return false;
        }
        shoppingBasket toRemove = cart.findBasket(myStore);
        if(toRemove == null){
            return false;
        }
        return toRemove.removeProduct(toDelete,num)> 0 ;
    }



    public List<Pair<Product,Integer>> getProductsInCart(){
        return cart.allProductsInCart();
    }

    public boolean purchase(){
        boolean toReturn;
        if(system_role == Member.getInstance() || system_role == System_Manager.getInstance()){
             toReturn= System.getInstance().memberPurchase(id,cart,creditCardNum,address);
        }
        else{
            toReturn= System.getInstance().purchase(cart,creditCardNum,address);
        }
        if (toReturn){
            cart= new shoppingCart();
        }
        return toReturn;
    }

    public boolean logout(){
        if(system_role == Member.getInstance()){
            boolean log = System.getInstance().logout(id);
            if (log){
                system_role = Registered.getInstance();
                return true;
            }
        }
        return false;
    }

    public boolean openStore(String name, List<Product> products, String address, int rating){
        if(system_role == Member.getInstance()){
            Store s = System.getInstance().openStore(name,products,address,rating);
            if (s!= null){
                store_roles.add(new Creator(s));
                return true;
            }
        }
        return false;
    }

    public List<shoppingCart> watchHistory(){
        if(system_role == Member.getInstance()) {
            return System.getInstance().orderHistory(id);
        }
        return null;
    }
}
