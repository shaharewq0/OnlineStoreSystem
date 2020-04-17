package store_System;

import Store.Store;
import javafx.util.Pair;
import java.util.LinkedList;
import java.util.List;

public class System implements ISystem {
    List<Pair<Integer,String>> registered = new LinkedList<>();
    List<Pair<Integer,String>> logedin = new LinkedList<>();
    List<Store> stores = new LinkedList<Store>();
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

}
