package Domain.Policies.Discounts;

import java.time.LocalDate;

public class ConditionalPriceDiscount extends ConditionalDiscount {
    private double minPrice;

    public ConditionalPriceDiscount(String productName, int percentage, LocalDate expirationDate, double minPrice) {
        super(productName, percentage, expirationDate);
        this.minPrice = minPrice;
        this.condition = (p, amount) -> amount >= minPrice;
    }

    @Override
    public String toString() {
        return super.toString() +
                " if Price >= " + minPrice;
    }
}
