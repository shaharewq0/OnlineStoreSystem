package Domain.Policies.Acquisitions;

import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.util.List;
import java.util.Map;

class AcqMaxAmount extends AcqAmountCondition {

    AcqMaxAmount(String productName, int amount) {
        super(productName, amount);
    }

    @Override
    public boolean canPurchase(List<Product_boundle> products) {
        if (productName.equals("ALL"))
            return getTotalAmount(products) <= amount;
        Product_boundle pb = findproduct(products,product);
        return pb!=null && pb.size() <= amount;
    }

    @Override
    public String toString() {
        return "Max. " + super.toString();
    }
}
