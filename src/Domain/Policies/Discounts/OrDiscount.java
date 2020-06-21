package Domain.Policies.Discounts;

import java.util.List;

public class OrDiscount extends CompositeDiscount {

    public OrDiscount(List<Discount> discounts) {
        super(discounts, Boolean::logicalOr, false, Double::sum);
    }

    @Override
    public String toString() {
        return "OrDiscount{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
