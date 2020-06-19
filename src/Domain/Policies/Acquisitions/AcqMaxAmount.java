package Domain.Policies.Acquisitions;

import Domain.Store.Product;

import java.util.Map;

class AcqMaxAmount extends AcqAmountCondition {

    AcqMaxAmount(String productName, int amount) {
        super(productName, amount);
    }

    @Override
    public boolean canPurchase(Map<Product, Integer> products) {
        if (productName.equals("ALL"))
            return getTotalAmount(products) <= amount;
        return products.containsKey(product) && products.get(product) <= amount;
    }

    @Override
    public String toString() {
        return "Max. " + super.toString();
    }
}
