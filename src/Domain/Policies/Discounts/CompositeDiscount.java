package Domain.Policies.Discounts;

import Domain.Logs.ErrorLogger;
import Domain.Store.Product;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    public boolean hasDiscount(Map<Product, Integer> products) {
        return discounts.stream()
                .map(d -> d.hasDiscount(products))
                .reduce(hasDiscountIdentity, hasDiscountOperator);
    }

    @Override
    public double applyDiscount(Map<Product, Integer> products) {
        return discounts.stream()
                .filter(d -> d.hasDiscount(products))
                .map(d -> d.applyDiscount(products))
                .reduce(0.0, applyDiscountOperator);
    }

    @Override
    public List<String> getProductsNames() {
        List<String> productNames = new LinkedList<>();
        for (Discount d : getSubDiscounts()) {
            productNames.addAll(d.getProductsNames());
        }
        return productNames;
    }

    @Override
    public void replaceProducts(List<Product> products) {
        List<Discount> sub_dis = getSubDiscounts();
        if (products.size() != sub_dis.size())
            ErrorLogger.GetInstance().Add_Log("IN Discount : replace product error");
        for (int i = 0; i < products.size(); i++) {
            sub_dis.get(i).replaceProducts(Collections.singletonList(products.get(i)));
        }
    }

    private List<Discount> getSubDiscounts() {
        List<Discount> sub_dis = new LinkedList<>();
        for (Discount d : discounts) {
            if (d instanceof CompositeDiscount)
                sub_dis.addAll(((CompositeDiscount) d).getSubDiscounts());
            else
                sub_dis.add(d);
        }
        return sub_dis;
    }

    @Override
    public String toString() {
        return discounts.stream().map(Discount::toString).reduce("", (acc, cur) -> acc + "\n\t" + cur);
    }

}
