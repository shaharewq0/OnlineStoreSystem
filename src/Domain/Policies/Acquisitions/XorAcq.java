package Domain.Policies.Acquisitions;

import java.util.List;

public class XorAcq extends CompositeAcq {
    public XorAcq(List<Acquisition> acquisitions) {
        super(acquisitions, Boolean::logicalXor, false);
    }

    @Override
    public String toString() {
        return "XorAcquisition{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
