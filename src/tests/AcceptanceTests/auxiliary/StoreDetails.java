package tests.AcceptanceTests.auxiliary;

import Domain.Store.StoreImp;

public class StoreDetails {
    public String getName() {
        return name;
    }

    private String name;
    private String address;
    private int rating;

    public StoreDetails(String storeName) {
        name = storeName;
        address = "london";
        rating = 4;
    }

    public StoreDetails(String storeName, String address) {
        name = storeName;
        this.address = address;
        rating = 4;
    }

	public StoreDetails(StoreImp storeDetails) {
        name = storeDetails.getName();
        address = storeDetails.getAddress();
        rating = storeDetails.getRating();
	}
}
