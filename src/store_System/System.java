package store_System;

import Store.Store;
import Store.shoppingCart;
import Store.Product;
import javafx.util.Pair;
import java.util.LinkedList;
import java.util.List;

public class System implements ISystem {
    List<Pair<Integer,String>> registered = new LinkedList<>();
    List<Pair<Integer,String>> logedin = new LinkedList<>();
    List<Store> stores = new LinkedList<>();
    List<Pair<Integer,shoppingCart>> order = new LinkedList<>();
    private static System instance = null;

    private System(){
        instance = new System();
    }

    public static System getInstance(){
        if(instance == null){
            instance = new System();
        }
        return instance;
    }

    public boolean register(int id, String password){
        if(contains(id,registered) != null){
            return false;
        }
        registered.add(new Pair<>(id,password));
        return true;
    }

    public boolean login(int id, String password){
        if(contains(id,registered) == null){
            return false;
        }
        if(contains(id,logedin) != null){
            return false;
        }
        logedin.add(new Pair<>(id,password));
        return true;
    }

    private Pair<Integer , String> contains(int id, List<Pair<Integer,String>> toSearch){
        for(Pair<Integer,String> existing:toSearch){
            if(existing.getKey() == id){
                return existing;
            }
        }
        return null;
    }

    public Store getStoreDetails(String name){
        for(Store s:stores){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    public List<Store> getAllStores(){
        return stores;
    }

    public List<Product> getProductsFromStore(String name){
        for(Store s: stores){
            if(s.getName().equals(name)){
                return s.getProducts();
            }
        }
        return null;
    }

    public List<Product> searchProductsByName(String name){
        List<Product> toReturn = new LinkedList<>();
        for(Store s : stores){
            List<Product> toAdd = s.findProductByName(name);
            concat(toReturn,toAdd);
        }
        return toReturn;
    }

    public List<Product> searchProductsByCategory(String category){
        List<Product> toReturn = new LinkedList<>();
        for(Store s : stores){
            List<Product> toAdd = s.findProductByCategory(category);
            concat(toReturn,toAdd);
        }
        return toReturn;
    }

    public List<Product> searchProductsByKeyword(String keyword){
        List<Product> toReturn = new LinkedList<>();
        for(Store s : stores){
            List<Product> toAdd = s.findProductByKeyword(keyword);
            concat(toReturn,toAdd);
        }
        return toReturn;
    }

    private void concat(List<Product> a , List<Product> b){
        for(Product p : b){
            if(!a.contains(p)){
                a.add(p);
            }
        }
    }

    public List<Product> filterByPrice(List<Product> base , int min , int max){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : base){
            if(p.getPrice()>= min & p.getPrice()<= max){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public List<Product> filterByRating(List<Product> base , int min , int max){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : base){
            if(p.getRating()>= min & p.getRating()<= max){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public List<Product> filterByCategory(List<Product> base , String category){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : base){
            if(p.getCategory().equals(category)){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public List<Product> filterByStoreRating(List<Product> base , int min, int max){
        List<Product> toReturn = new LinkedList<>();
        for(Product p : base){
            if(p.getStore().getRating()>= min & p.getStore().getRating()<= max){
                toReturn.add(p);
            }
        }
        return toReturn;
    }

    public boolean memberPurchase(int id,shoppingCart cart,int creditCard,String address){
        if(purchase(cart,creditCard,address)){
            order.add(new Pair<Integer, shoppingCart>(id,cart));
            return true;
        }
        return false;
    }

    public boolean purchase(shoppingCart cart,int creditCard,String address){
        if(!UpdateStorage(cart)){
            return false;
        }
        double price=calcPrice(cart);
        navigatePayment(creditCard,price);
        navigateSupply(cart,address);
        return true;
    }

    //TODO implement
    private double calcPrice(shoppingCart c){
        return 0.0;
    }
    //TODO implement
    private void navigatePayment(int creditCarNum,double price){

    }
    //TODO implement
    private boolean UpdateStorage(shoppingCart cart){
        return true;
    }
    //TODO implement
    private void navigateSupply(shoppingCart cart, String address){

    }

}
