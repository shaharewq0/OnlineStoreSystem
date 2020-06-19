package Domain.Policies.Acquisitions;

import Domain.Store.Product;
import Domain.info.ProductDetails;

import java.util.List;

abstract class AcqAmountCondition implements Acquisition {
    // productName can be 'ALL' to represent store condition
    protected String productName;
    protected Product product;
    protected int amount;

    AcqAmountCondition(String productName, int amount) {
        this.productName = productName;
        this.amount = amount;
    }

    ProductDetails findProduct(List<ProductDetails> products) {
        for (ProductDetails p : products) {
            if (p.getName().equals(productName))        //TODO: product equals
                return p;
        }
        return null;
    }

    int getTotalAmount(List<ProductDetails> products) {
        return products.stream().map(ProductDetails::getAmount).reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        return "amount of '" + productName + "' is " + amount;
    }

}
