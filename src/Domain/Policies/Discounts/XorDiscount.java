package Domain.Policies.Discounts;

import java.util.List;

public class XorDiscount extends CompositeDiscount {
    public XorDiscount(List<Discount> discounts) {
        // use or instead of xor because we need at least one
        // xor should apply only one discount, so we choose the max discount
        super(discounts, Boolean::logicalOr, false, Math::max);
    }

    @Override
    public String toString() {
        return "XorDiscount{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
