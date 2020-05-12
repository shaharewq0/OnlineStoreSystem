package tests.AcceptanceTests.auxiliary;

import Domain.Store.StoreImp;

import java.util.Objects;

public class StoreDetails {
    public String getName() {
        return name;
    }

    private String name;
    private int rating;

    public StoreDetails(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public StoreDetails(StoreImp storeDetails) {
        name = storeDetails.getName();
        rating = storeDetails.getRating();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDetails that = (StoreDetails) o;
        return Objects.equals(name, that.name) &&
                rating == that.rating;
    }
}
