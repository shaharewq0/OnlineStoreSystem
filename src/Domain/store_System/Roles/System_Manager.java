package Domain.store_System.Roles;

import java.util.List;

import Domain.Store.Purchase;
import Domain.store_System.System;

public class System_Manager{// extends Member {
	
    public System_Manager(Registered registered) {
    	
     //   super(registered);
    }



	public List<Purchase> getPurchaseHistory(String storeName) {
		return	System.getInstance().getPurchaseHistory(storeName);
	//return null;
	}
}
