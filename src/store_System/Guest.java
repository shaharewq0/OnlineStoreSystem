package store_System;

public class Guest {
    private static Guest instance = null;
    private Guest(){
        instance = new Guest();
    }
    public static Guest getInstance(){
        if(instance == null){
            instance = new Guest();
        }
        return instance;
    }
}
