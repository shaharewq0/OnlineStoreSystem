package Service_Layer.member_accese;

import java.util.LinkedList;
import java.util.List;

import Domain.RedClasses.User;
import Domain.RedClasses.shoppingCart;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class member_accese {

	String username;
	public boolean usecase3_1_Logout(String myusername,String myPassword) {
		User me = null;// System.getInstance().getMember(myusername,myPassword);
		return me.logout();
	}

	public boolean usecase3_2_OpenStore(String myusername,String myPassword,StoreDetails store) {
		User me = null;// System.getInstance().getMember(myusername,myPassword);
		return me.openStore(store); 
	}

	public List<PurchaseDetails> usecase3_7_ReviewPurchasesHistory(String myusername,String myPassword) {
		User me = null;// System.getInstance().getMember(myusername,myPassword);
		
		List<PurchaseDetails> temp = new LinkedList<PurchaseDetails>();
		
		for (shoppingCart cart : me.watchHistory()) {
			temp.add(new PurchaseDetails(cart));
		}
		return temp;
	}
}
