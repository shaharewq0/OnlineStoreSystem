package Service_Layer.member_accese;

import java.util.List;

import Domain.store_System.System;
import tests.AcceptanceTests.auxiliary.ProductDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class member_accese {

	public boolean usecase3_1_Logout() {
		//TODO imp
		return false;
	}

	public boolean usecase3_2_OpenStore(StoreDetails store) {
		return System.getInstance().openStore(store.getName(), "London", 9) != null;
	
	}

	public List<ProductDetails> usecase3_7_ReviewPurchasesHistory() {
		// TODO imp
		return null;
	}
}
