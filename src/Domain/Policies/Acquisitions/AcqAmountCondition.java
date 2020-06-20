package Domain.Policies.Acquisitions;

import Domain.Logs.ErrorLogger;
import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.util.Collections;
import java.util.List;
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

    int getTotalAmount(List<Product_boundle> products) {
        return products.stream().map(Product_boundle::size).reduce(0, Integer::sum);
    }

    @Override
    public List<String> getProductsNames() {
        return Collections.singletonList(productName);
    }

    @Override
    public void replaceProducts(List<Product> products) {
        if (products.size() != 1)
            ErrorLogger.GetInstance().Add_Log("IN Acquisition : replace product error");
        product = products.get(0);
    }

    @Override
    public String toString() {
        return "amount of '" + productName + "' is " + amount;
    }

    static Product_boundle findproduct(List<Product_boundle> PB,Product p) {
        for (Product_boundle pb : PB) {
            if(pb.item.equals(p))
                return pb;
        }
        return null;
    }
}
