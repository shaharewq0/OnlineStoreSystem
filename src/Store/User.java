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
}
