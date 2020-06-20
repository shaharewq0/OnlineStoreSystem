package Domain.Policies.Acquisitions;

import Domain.Store.Product;

import java.util.List;
import java.util.Map;

public interface Acquisition {
    // at all acquisitions, productName can be 'ALL' to represent store condition


    /**
     * returns true if this Acquisition can be done
     * otherwise, return false.
     */
    boolean canPurchase(Map<Product, Integer> products);

    /**
     * return all product names (include sub discounts)
     */
    List<String> getProductsNames();

    /**
     * add to each discount the appropriate product according to product name (include sub discounts)
     */
    void replaceProducts(List<Product> products);
}
