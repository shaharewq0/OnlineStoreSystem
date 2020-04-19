package store_System;

public class Member implements System_Role {
    private static Member instance = null;
    public static Member getInstance(){
        if(instance == null){
            instance = new Member();
        }
        return instance;
    }
}
