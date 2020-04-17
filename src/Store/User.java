package Store;

import store_System.Guest;
import store_System.Registered;
import store_System.System;
import store_System.System_Role;

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
}
