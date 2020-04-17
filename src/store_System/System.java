package store_System;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class System implements ISystem {
    List<Pair<Integer,String>> registered = new LinkedList<>();
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

    private Pair<Integer , String> contains(int id, List<Pair<Integer,String>> toSearch){
        for(Pair<Integer,String> existing:toSearch){
            if(existing.getKey() == id){
                return existing;
            }
        }
        return null;
    }

}
