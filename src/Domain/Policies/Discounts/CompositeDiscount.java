package Domain.Policies.Discounts;

import Domain.info.ProductDetails;

import java.util.List;
import java.util.function.BinaryOperator;

abstract class CompositeDiscount implements Discount {
    protected List<Discount> discounts;
    private BinaryOperator<Boolean> hasDiscountOperator;
    private Boolean hasDiscountIdentity;
    private BinaryOperator<Double> applyDiscountOperator;

    CompositeDiscount(List<Discount> discounts, BinaryOperator<Boolean> hasDiscountOperator,
                      Boolean hasDiscountIdentity, BinaryOperator<Double> applyDiscountOperator) {
        this.discounts = discounts;
        this.hasDiscountOperator = hasDiscountOperator;
        this.hasDiscountIdentity = hasDiscountIdentity;
        this.applyDiscountOperator = applyDiscountOperator;
    }

    @Override
    public boolean hasDiscount(List<ProductDetails> products) {
        return discounts.stream()
                .map(d -> d.hasDiscount(products))
                .reduce(hasDiscountIdentity, hasDiscountOperator);
    }

    @Override
    public double applyDiscount(List<ProductDetails> products) {
        return discounts.stream()
                .filter(d -> d.hasDiscount(products))
                .map(d -> d.applyDiscount(products))
                .reduce(0.0, applyDiscountOperator);
    }

    @Override
    public String toString() {
        return discounts.stream().map(Discount::toString).reduce("", (acc, cur) -> acc + "\n\t" + cur);
    }

}
