package store_System;

public class Registered {
    private static Registered instance = null;
    private Registered(){
        instance = new Registered();
    }
    public static Registered getInstance(){
        if(instance == null){
            instance = new Registered();
        }
        return instance;
    }
}
