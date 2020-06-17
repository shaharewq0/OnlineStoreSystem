package Domain.Policies.Acquisitions;

import java.util.List;

class OrAcq extends CompositeAcq {
    OrAcq(List<Acquisition> acquisitions) {
        super(acquisitions, Boolean::logicalOr, false);
    }

    @Override
    public String toString() {
        return "OrAcquisition{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
