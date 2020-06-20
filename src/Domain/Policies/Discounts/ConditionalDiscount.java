package Domain.Policies.Discounts;

import Domain.Store.Product;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.BiFunction;

abstract class ConditionalDiscount extends VisibleDiscount {
    BiFunction<Product, Integer, Boolean> condition;

    ConditionalDiscount(String productName, int percentage, LocalDate expirationDate) {
        super(productName, percentage, expirationDate);
    }

    @Override
    public boolean hasDiscount(Map<Product, Integer> products) {
        if (LocalDate.now().isAfter(expirationDate))
            return false;
        if (productName.equals("ALL"))
            return true;
        return products.containsKey(product) && condition.apply(product, products.get(product));
    }
}
