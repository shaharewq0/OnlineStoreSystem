package Domain.Policies.Discounts;

import java.time.LocalDate;

class ConditionalPriceDiscount extends ConditionalDiscount {
    private double minPrice;

    ConditionalPriceDiscount(String productName, int percentage, LocalDate expirationDate, double minPrice) {
        super(productName, percentage, expirationDate);
        this.minPrice = minPrice;
        this.condition = p -> p.getAmount() >= minPrice;
    }

    @Override
    public String toString() {
        return super.toString() +
                " if Price >= " + minPrice;
    }
}
