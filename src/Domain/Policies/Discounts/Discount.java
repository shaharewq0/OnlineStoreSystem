package Domain.Policies.Discounts;

import Domain.info.ProductDetails;

import java.util.List;

interface Discount {
    // at all discounts, productName can be 'ALL' to represent store discount

    /**
     * returns true if this discount can be applied on some or all of those products
     * otherwise, return false.
     */
    boolean hasDiscount(List<ProductDetails> products);

    /**
     * apply this discount on the products.
     * returns the difference that need to subtract from original price.
     * <p>
     * assumptions:
     * * hasDiscount is true
     * * products doesn't contain two products with same name
     */
    double applyDiscount(List<ProductDetails> products);
}
