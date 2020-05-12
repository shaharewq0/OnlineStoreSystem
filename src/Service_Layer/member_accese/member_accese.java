package Service_Layer.member_accese;

import java.util.List;

import Domain.RedClasses.User;
import Domain.Store.Purchase;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class member_accese {

	String username;

	public boolean usecase3_1_Logout(String myusername, String myPassword) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.logout();
	}

	public boolean usecase3_2_OpenStore(String myusername, String myPassword, StoreDetails store) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.openStore(store);
	}

	public List<Purchase> usecase3_7_ReviewPurchasesHistory(String myusername, String myPassword) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.getPurchaseHistory();

	}
}
