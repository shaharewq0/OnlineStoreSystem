package Service_Layer.sys_manager_accese;

import Domain.RedClasses.User;
import Domain.Store.Purchase;
import Domain.store_System.System;

import java.util.List;

public class sys_mangaer_accese {

	public static List<Purchase> usecase6_4A_WatchPurchesHistoryofUser(String myusername,String myPassword,String username)
	{
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.ViewAquistionHistoryOfUser(username);
	}
	
	public static List<Purchase> usecase6_4B_WatchPurchesHistoryofStore(String myusername,String myPassword,String storename)
	{
		User me = System.getInstance().getMember(myusername, myPassword);
		return me.ViewAquistionHistory(storename);

		
	}
	
}
