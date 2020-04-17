package Store;

import store_System.*;
import store_System.System;

import java.util.List;

public class User implements IUser {
    private System_Role system_role;

    public User(){
        system_role = Guest.getInstance();
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
}
