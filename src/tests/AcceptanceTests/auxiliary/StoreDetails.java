package tests.AcceptanceTests.auxiliary;

import Domain.Store.StoreImp;

import java.util.Objects;

public class StoreDetails {
    public String getName() {
        return name;
    }

    private String name;
    private String adress;
    private int rating;

    public StoreDetails(String name,String adress, int rating) {
        this.name = name;
        this.rating = rating;
        this.adress = adress;
    }

    public StoreDetails(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.adress = "unkwon";
    }

    public StoreDetails(StoreImp storeDetails) {
        name = storeDetails.getName();
        rating = storeDetails.getRating();
        adress = storeDetails.getAddress();
	}

    public int getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreDetails that = (StoreDetails) o;
        return getRating() == that.getRating() &&
                getName().equals(that.getName()) &&
                Objects.equals(getAdress(), that.getAdress());
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return "StoreDetails{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", rating=" + rating +
                '}';
    }
}
