package Service_Layer.member_accese;

import java.util.List;

import Domain.RedClasses.User;
import Domain.RedClasses.UserPurchase;
import Domain.Store.Purchase;
import Domain.info.StoreInfo;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class member_accese {

	String username;
//with password
	public boolean usecase3_1_Logout(String myusername, String myPassword) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.logout();
	}

	public boolean usecase3_2_OpenStore(String myusername, String myPassword, StoreDetails store) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.openStore(new StoreInfo(store));
	}

	public List<UserPurchase> usecase3_7_ReviewPurchasesHistory(String myusername, String myPassword) {
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.getPurchaseHistory();

	}
	
	//with login ID
	
	public boolean usecase3_1_Logout(int guestId) {
		User me = System.getInstance().getMember(guestId);
		return me.logout();
	}

	public boolean usecase3_2_OpenStore(int guestId, StoreDetails store) {
		User me = System.getInstance().getMember(guestId);
		return me.openStore(new StoreInfo(store));
	}

	public List<UserPurchase> usecase3_7_ReviewPurchasesHistory(int guestId) {
		User me = System.getInstance().getMember(guestId);
		return me.getPurchaseHistory();

	}
}
