package Domain.Policies.Acquisitions;

import Domain.Store.Product;

import java.util.Map;

class AcqMinAmount extends AcqAmountCondition {

    AcqMinAmount(String productName, int amount) {
        super(productName, amount);
    }

    @Override
    public boolean canPurchase(Map<Product, Integer> products) {
        if (productName.equals("ALL"))
            return getTotalAmount(products) >= amount;
        return products.containsKey(product) && products.get(product) >= amount;
    }

    @Override
    public String toString() {
        return "Min. " + super.toString();
    }
}
