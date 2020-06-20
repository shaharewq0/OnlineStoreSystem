package Domain.Policies.Discounts;

import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

abstract class ConditionalDiscount extends VisibleDiscount {
    BiFunction<Product, Integer, Boolean> condition;

    ConditionalDiscount(String productName, int percentage, LocalDate expirationDate) {
        super(productName, percentage, expirationDate);
    }

    @Override
    public boolean hasDiscount(List<Product_boundle> products) {
        if (LocalDate.now().isAfter(expirationDate))
            return false;
        if (productName.equals("ALL"))
            return true;
        Product_boundle pb =  findproduct(products,product);
        return pb!=null && condition.apply(product, pb.size());
    }
}
