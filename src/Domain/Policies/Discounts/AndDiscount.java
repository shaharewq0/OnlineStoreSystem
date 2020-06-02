package Domain.Policies.Discounts;

import java.util.List;

class AndDiscount extends CompositeDiscount {

    AndDiscount(List<Discount> discounts) {
        super(discounts, Boolean::logicalAnd, true, Double::sum);
    }

    @Override
    public String toString() {
        return "AndDiscount{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
