package Domain.Policies.Acquisitions;

import Domain.info.ProductDetails;

import java.util.List;

public interface Acquisition {
    // at all acquisitions, productName can be 'ALL' to represent store condition


    /**
     * returns true if this Acquisition can be done
     * otherwise, return false.
     */
    boolean canPurchase(List<ProductDetails> products);

}
