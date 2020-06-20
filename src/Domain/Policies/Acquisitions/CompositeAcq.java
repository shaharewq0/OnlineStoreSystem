package Domain.Policies.Acquisitions;

import Domain.Logs.ErrorLogger;
import Domain.Store.Product;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

abstract class CompositeAcq implements Acquisition {
    private List<Acquisition> acquisitions;
    private BinaryOperator<Boolean> canPurchaseOperator;
    private Boolean canPurchaseIdentity;

    CompositeAcq(List<Acquisition> acquisitions, BinaryOperator<Boolean> canPurchaseOperator, Boolean canPurchaseIdentity) {
        this.acquisitions = acquisitions;
        this.canPurchaseOperator = canPurchaseOperator;
        this.canPurchaseIdentity = canPurchaseIdentity;
    }

    @Override
    public boolean canPurchase(Map<Product, Integer> products) {
        return acquisitions.stream()
                .map(acquisition -> acquisition.canPurchase(products))
                .reduce(canPurchaseIdentity, canPurchaseOperator);
    }

    @Override
    public List<String> getProductsNames() {
        List<String> productNames = new LinkedList<>();
        for (Acquisition a : getSubAcquisitions()) {
            productNames.addAll(a.getProductsNames());
        }
        return productNames;
    }

    @Override
    public void replaceProducts(List<Product> products) {
        List<Acquisition> sub_acq = getSubAcquisitions();
        if (products.size() != sub_acq.size())
            ErrorLogger.GetInstance().Add_Log("IN Acquisition : replace product error");
        for (int i = 0; i < products.size(); i++) {
            sub_acq.get(i).replaceProducts(Collections.singletonList(products.get(i)));
        }
    }

    private List<Acquisition> getSubAcquisitions() {
        List<Acquisition> sub_acq = new LinkedList<>();
        for (Acquisition a : acquisitions) {
            if (a instanceof CompositeAcq)
                sub_acq.addAll(((CompositeAcq) a).getSubAcquisitions());
            else
                sub_acq.add(a);
        }
        return sub_acq;
    }

    @Override
    public String toString() {
        return acquisitions.stream().map(Acquisition::toString).reduce("", (acc, cur) -> acc + "\n\t" + cur);
    }

}
