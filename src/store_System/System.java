package store_System;

import Store.Store;
import Store.shoppingCart;
import Store.MyPair;
import Store.Product;
import java.util.LinkedList;
import java.util.List;

public class System implements ISystem {
    List<MyPair<Integer,String>> registered = new LinkedList<>();
    List<MyPair<Integer,String>> logedin = new LinkedList<>();
    List<Store> stores = new LinkedList<>();
    List<MyPair<Integer,List<shoppingCart>>> order = new LinkedList<>();
    private static System instance = null;

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
        registered.add(new MyPair<>(id,password));
        return true;
    }

    public boolean login(int id, String password){
    	MyPair<Integer,String> reg = contains(id,registered);
        if(reg == null || !reg.getValue().equals(password)) {
            return false;
        }
        if(contains(id,logedin) != null){
            return false;
        }
        logedin.add(new MyPair<>(id,password));
        return true;
    }

    private MyPair<Integer , String> contains(int id, List<MyPair<Integer,String>> toSearch){
        for(MyPair<Integer,String> existing:toSearch){
            if(existing.getKey() == id){
                return existing;
            }
        }
        return null;
    }

    private MyPair<Integer , List<shoppingCart>> containsB(int id, List<MyPair<Integer,List<shoppingCart>>> toSearch){
        for(MyPair<Integer,List<shoppingCart>> existing:toSearch){
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
            Product toAdd = s.findProductByName(name);
            if(toAdd!=null) {
                toReturn.add(toAdd);
            }
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

    public List<Product> filterByPrice(List<Product> base , double min , double max){
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
        	MyPair<Integer,List<shoppingCart>> toChange = containsB(id,order);
            if(toChange == null){
                List<shoppingCart> cartAdd = new LinkedList<>();
                cartAdd.add(cart);
                order.add(new MyPair<>(id,cartAdd));
                return true;
            }else{
                order.remove(toChange);
                toChange.getValue().add(cart);
                order.add(toChange);
                return true;
            }
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

    public boolean logout(int id){
        if(contains(id,registered)== null){
            return false;
        }else if(contains(id,logedin)==null){
            return false;
        }else{
            logedin.remove(contains(id,logedin));
            return true;
        }
    }

    public Store openStore(String name, String address, int rating){
        for(Store s:stores){
            if(s.getName().equals(name)){
                return null;
            }
        }
        Store newStore = new Store(name,address,rating);
        stores.add(newStore);
        return newStore;
    }

    public List<shoppingCart> orderHistory(int id){
    	MyPair<Integer,List<shoppingCart>> toReturn = containsB(id,order);
        if(toReturn == null){
            return null;
        }
        return toReturn.getValue();
    }
}
