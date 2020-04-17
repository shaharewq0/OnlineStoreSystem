package store_System;

public class Member {
    private static Member instance = null;
    private Member(){
        instance = new Member();
    }
    public static Member getInstance(){
        if(instance == null){
            instance = new Member();
        }
        return instance;
    }
}
