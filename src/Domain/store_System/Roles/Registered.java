package Domain.store_System.Roles;

import java.util.LinkedList;
import java.util.List;

import Domain.RedClasses.User;
import Domain.RedClasses.shoppingCart;
import Domain.Store.Purchase;

public class Registered{
    private String id;
    private List<Purchase> myPurcase = new LinkedList<Purchase>();
    
     public Registered(String id) {
        this.id = id;
        
    }

    public String getId() {
        return id;
    }

	public void LogLogin(User user) {
		// TODO Auto-generated method stub
		
	}


	public void LogHistory(Purchase p)
	{
		myPurcase.add(p);
	}
	
	public List<Purchase> getPurchesHistory() {
		return myPurcase;
	}

}
