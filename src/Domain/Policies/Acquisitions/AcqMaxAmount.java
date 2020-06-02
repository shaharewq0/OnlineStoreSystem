package Domain.Policies.Acquisitions;

import Domain.info.ProductDetails;

import java.util.List;

class AcqMaxAmount extends AcqAmountCondition {

    AcqMaxAmount(String productName, int amount) {
        super(productName, amount);
    }

    @Override
    public boolean canPurchase(List<ProductDetails> products) {
        if (productName.equals("ALL"))
            return getTotalAmount(products) <= amount;
        ProductDetails p = findProduct(products);
        return p != null && p.getAmount() <= amount;
    }

    @Override
    public String toString() {
        return "Max. " + super.toString();
    }
}
