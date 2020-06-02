package Domain.Policies.Discounts;

import Domain.info.ProductDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

abstract class ConditionalDiscount extends VisibleDiscount {
    Function<ProductDetails, Boolean> condition;

    ConditionalDiscount(String productName, int percentage, LocalDate expirationDate) {
        super(productName, percentage, expirationDate);
    }

    @Override
    public boolean hasDiscount(List<ProductDetails> products) {
        if (LocalDate.now().isAfter(expirationDate))
            return false;
        if (productName.equals("ALL"))
            return true;
        ProductDetails p = getDiscountProduct(products);
        return p != null && condition.apply(p);
    }
}
