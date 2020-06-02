package Domain.Policies.Acquisitions;

import Domain.info.ProductDetails;

import java.util.List;

interface Acquisition {
    // at all acquisitions, productName can be 'ALL' to represent store condition


    /**
     * returns true if this discount can be applied on some or all of those products
     * otherwise, return false.
     */
    boolean canPurchase(List<ProductDetails> products);

}
