package tests.AcceptanceTests.auxiliary;

import Domain.Store.StoreImp;

public class StoreDetails {
    public String getName() {
        return name;
    }

    private String name;

    public StoreDetails(String storeName) {
        name = storeName;
    }

	public StoreDetails(StoreImp storeDetails) {
		 name = storeDetails.getName();
	}
}
