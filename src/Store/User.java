package Store;

import store_System.*;
import store_System.System;

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
}
