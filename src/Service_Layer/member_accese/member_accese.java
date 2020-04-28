package Service_Layer.member_accese;

import java.util.LinkedList;
import java.util.List;

import Domain.Store.shoppingCart;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class member_accese {

	String username;
	public boolean usecase3_1_Logout(String myusername,String myPassword) {
		//TODO imp
		return false;
	}

	public boolean usecase3_2_OpenStore(String myusername,String myPassword,StoreDetails store) {
		return System.getInstance().openStore(store.getName(), "London", 9) != null;
	
	}

	public List<PurchaseDetails> usecase3_7_ReviewPurchasesHistory(String myusername,String myPassword) {
		List<PurchaseDetails> temp = new LinkedList<PurchaseDetails>();
		
		for (shoppingCart cart : System.getInstance().orderHistory(username)) {
			temp.add(new PurchaseDetails(cart));
		}
		return temp;
	}
}
