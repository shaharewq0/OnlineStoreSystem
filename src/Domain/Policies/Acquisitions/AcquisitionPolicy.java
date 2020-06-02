package Domain.Policies.Acquisitions;

import Domain.Policies.BasePolicy;
import Domain.info.ProductDetails;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;


/*
    this class get string in the following format that represent Acquisition
    and parse it in order to create Acquisition objects.
    Note: the formats are without spaces

    simple Acquisition format:
        <type>#<product-name>#<condition>
        where:
        - <type>: 0 - min amount
                  1 - max amount
        - <product-name>: the name of the product
        - <condition>: according to type
                        0/1 - min/max amount

    Composite Acquisition format:
        <type>#<number> (#<acquisition>)*
        where:
        - <type>: 10 - And
                  11 - Or
                  12 - Xor
        - <discount>: acquisition in valid format
        - <number>: how many acquisitions are
        - * means repetition
 */

public class AcquisitionPolicy extends BasePolicy {
    private List<Acquisition> acquisitions;

    public AcquisitionPolicy() {
        this.acquisitions = new LinkedList<>();
    }

    private Acquisition acquisitionFactory(Stack<String> params) throws Exception {
        int type = Integer.parseInt(params.pop());
        switch (type) {
            case 0: //min amount
                return new AcqMinAmount(params.pop(), Integer.parseInt(params.pop()));

            case 1: //max amount
                return new AcqMaxAmount(params.pop(), Integer.parseInt(params.pop()));

            case 10: //composite and
                return new AndAcq(parseAcquisitionList(params));

            case 11: //composite or
                return new OrAcq(parseAcquisitionList(params));

            case 12: //composite xor
                return new XorAcq(parseAcquisitionList(params));

            default:
                throw new Exception();
        }
    }

    private List<Acquisition> parseAcquisitionList(Stack<String> params) throws Exception {
        List<Acquisition> acquisitionList = new LinkedList<>();
        int n = Integer.parseInt(params.pop());
        for (int i = 0; i < n; i++) {
            acquisitionList.add(acquisitionFactory(params));
        }
        return acquisitionList;
    }

    public boolean addAcquisitionPolicy(String discount) {
        Acquisition d;
        try {
            d = acquisitionFactory(stringSplitToStack(discount, REGEX));
        } catch (Exception e) {
            // error log "wrong format"
            return false;
        }
        acquisitions.add(d);
        return true;
    }

    public boolean canPurchase(List<ProductDetails> products) {
        return acquisitions.stream()
                .map(acquisition -> acquisition.canPurchase(products))
                .reduce(true, Boolean::logicalAnd);
    }

    public boolean removeAcquisition(int acqNum) {
        if (acqNum >= 0 && acqNum < acquisitions.size()) {
            acquisitions.remove(acqNum);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        AtomicInteger i = new AtomicInteger();
        return "Acquisition{" +
                acquisitions.stream()
                        .map(Acquisition::toString)
                        .reduce("", (acc, cur) -> acc + "\n\t" + (i.getAndIncrement()) + ". " + cur) +
                "\n}";
    }
}
