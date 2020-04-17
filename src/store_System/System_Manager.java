package store_System;

public class System_Manager implements System_Role {
    private static System_Manager instance = null;
    private System_Manager(){
        instance = new System_Manager();
    }
    public static System_Manager getInstance(){
        if(instance == null){
            instance = new System_Manager();
        }
        return instance;
    }
}
