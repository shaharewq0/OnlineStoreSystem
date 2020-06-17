package Domain.Policies.Discounts;

import java.time.LocalDate;

class ConditionalAmountDiscount extends ConditionalDiscount {
    private int minAmount;

    ConditionalAmountDiscount(String productName, int percentage, LocalDate expirationDate, int minAmount) {
        super(productName, percentage, expirationDate);
        this.minAmount = minAmount;
        this.condition = p -> p.getAmount() >= minAmount;
    }

    @Override
    public String toString() {
        return super.toString() +
                " if Amount >= " + minAmount;
    }
}
