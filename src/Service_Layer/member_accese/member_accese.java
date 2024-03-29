package Service_Layer.member_accese;

import Domain.UserClasses.User;
import Domain.UserClasses.UserPurchase;
import Domain.info.StoreInfo;
import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.List;

public class member_accese {

//with password
	public static boolean usecase3_1_Logout(String myusername, String myPassword) {
		User me = System.getInstance().getMember(myusername, myPassword);
		if(me == null)
			return false;
		return me.logout();
	}

	public static boolean usecase3_2_OpenStore(String myusername, String myPassword, StoreDetails store) {
		User me = System.getInstance().getMember(myusername, myPassword);
		if(me == null)
			return false;
		return me.openStore(new StoreInfo(store));
	}

	public static List<UserPurchase> usecase3_7_ReviewPurchasesHistory(String myusername, String myPassword) {
		User me = System.getInstance().getMember(myusername, myPassword);
		if(me == null)
			return null;
		return me.getPurchaseHistory();
	}
	
	//with login ID
	
	public static boolean usecase3_1_Logout(int guestId) {
		User me = System.getInstance().getMember(guestId);
		if(me==null)
			return false;
		return me.logout();
	}

	public static boolean usecase3_2_OpenStore(int guestId, StoreDetails store) {
		User me = System.getInstance().getMember(guestId);
		if(me==null)
			return false;
		return me.openStore(new StoreInfo(store));
	}

	public static List<UserPurchase> usecase3_7_ReviewPurchasesHistory(int guestId) {
		User me = System.getInstance().getMember(guestId);
		if(me==null)
			return null;
		return me.getPurchaseHistory();

	}

	public static List<String> getRoles(int guestID) {
		List<String> roles;
		User me = System.getInstance().getMember(guestID);
		String sys_manager = System.getInstance().getManager();

		if (me == null) {
			return null;
		}

		roles =  me.roles();

		if(me.getName().equals(sys_manager)){
			roles.add("systemManager");
		}

		return roles;
	}
}
