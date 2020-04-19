package store_System;

public class Guest implements System_Role {
    private static Guest instance = null;
    public static Guest getInstance(){
        if(instance == null){
            instance = new Guest();
        }
        return instance;
    }
}
