package Domain.Policies.Acquisitions;

import java.util.List;

class XorAcq extends CompositeAcq {
    XorAcq(List<Acquisition> acquisitions) {
        super(acquisitions, Boolean::logicalXor, false);
    }

    @Override
    public String toString() {
        return "XorAcquisition{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
