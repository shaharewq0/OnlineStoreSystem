package Domain.Policies.Discounts;

import java.time.LocalDate;

public class ConditionalAmountDiscount extends ConditionalDiscount {
    private int minAmount;

    public ConditionalAmountDiscount(String productName, int percentage, LocalDate expirationDate, int minAmount) {
        super(productName, percentage, expirationDate);
        this.minAmount = minAmount;
        this.condition = (p, amount) -> amount >= minAmount;
    }

    @Override
    public String toString() {
        return super.toString() +
                " if Amount >= " + minAmount;
    }
}
