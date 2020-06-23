package Domain.Policies.Acquisitions;

import Domain.Policies.BasePolicy;
import Domain.Store.Product;
import Domain.Store.Product_boundle;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;


/*
//      TODO: Acquisition: refactor out
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
    private int id;
    private List<Acquisition> acquisitions;

    public AcquisitionPolicy() {
        this.acquisitions = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public List<Acquisition> getAcquisitions() {
        return acquisitions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAcquisitions(List<Acquisition> acquisitions) {
        this.acquisitions = acquisitions;
    }

    //      TODO: Acquisition: refactor out
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

    //      TODO: Acquisition: refactor out
    private List<Acquisition> parseAcquisitionList(Stack<String> params) throws Exception {
        List<Acquisition> acquisitionList = new LinkedList<>();
        int n = Integer.parseInt(params.pop());
        for (int i = 0; i < n; i++) {
            acquisitionList.add(acquisitionFactory(params));
        }
        return acquisitionList;
    }

    public boolean addAcquisitionPolicy(Acquisition acquisition) {
//      TODO: Acquisition: refactor out
//        Acquisition d;
//        try {
//            d = acquisitionFactory(stringSplitToStack(acquisition, REGEX));
//        } catch (Exception e) {
//            // error log "wrong format"
//            return false;
//        }
        acquisitions.add(acquisition);
        return true;
    }

    public boolean canPurchase(List<Product_boundle> products) {
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
