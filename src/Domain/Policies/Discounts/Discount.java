package Domain.Policies.Discounts;

import Domain.Store.Product;

import java.util.Map;

public interface Discount {
    // at all discounts, productName can be 'ALL' to represent store discount

    /**
     * returns true if this discount can be applied on some or all of those products
     * otherwise, return false.
     */
    boolean hasDiscount(Map<Product, Integer> products);

    /**
     * apply this discount on the products.
     * returns the difference that need to subtract from original price.
     * <p>
     * assumptions:
     * * hasDiscount is true
     * * products doesn't contain two products with same name
     */
    double applyDiscount(Map<Product, Integer> products);
}
