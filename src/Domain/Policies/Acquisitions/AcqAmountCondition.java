package Domain.Policies.Acquisitions;

import Domain.Store.Product;

import java.util.Map;

abstract class AcqAmountCondition implements Acquisition {
    // productName can be 'ALL' to represent store condition
    protected String productName;
    protected Product product;
    protected int amount;

    AcqAmountCondition(String productName, int amount) {
        this.productName = productName;
        this.amount = amount;
    }

    int getTotalAmount(Map<Product, Integer> products) {
        return products.values().stream().reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "amount of '" + productName + "' is " + amount;
    }

}
