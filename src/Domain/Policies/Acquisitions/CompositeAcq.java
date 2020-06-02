package Domain.Policies.Acquisitions;

import Domain.info.ProductDetails;

import java.util.List;
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
    public boolean canPurchase(List<ProductDetails> products) {
        return acquisitions.stream()
                .map(acquisition -> acquisition.canPurchase(products))
                .reduce(canPurchaseIdentity, canPurchaseOperator);
    }

    @Override
    public String toString() {
        return acquisitions.stream().map(Acquisition::toString).reduce("", (acc, cur) -> acc + "\n\t" + cur);
    }

}