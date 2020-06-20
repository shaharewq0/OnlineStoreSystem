package Domain.Policies.Discounts;

import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.util.List;
import java.util.Map;

public interface Discount {
    // at all discounts, productName can be 'ALL' to represent store discount

    /**
     * returns true if this discount can be applied on some or all of those products
     * otherwise, return false.
     */
    boolean hasDiscount(List<Product_boundle> products);

    /**
     * apply this discount on the products.
     * returns the difference that need to subtract from original price.
     * <p>
     * assumptions:
     * * hasDiscount is true
     * * products doesn't contain two products with same name
     */
    double applyDiscount(List<Product_boundle> products);

    /**
     * return all product names (include sub discounts)
     */
    List<String> getProductsNames();

    /**
     * add to each discount the appropriate product according to product name (include sub discounts)
     */
    void replaceProducts(List<Product> products);
}
