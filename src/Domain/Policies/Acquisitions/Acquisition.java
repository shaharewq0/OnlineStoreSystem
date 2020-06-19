package Domain.Policies.Acquisitions;

import Domain.Store.Product;

import java.util.Map;

public interface Acquisition {
    // at all acquisitions, productName can be 'ALL' to represent store condition


    /**
     * returns true if this Acquisition can be done
     * otherwise, return false.
     */
    boolean canPurchase(Map<Product, Integer> products);

}
