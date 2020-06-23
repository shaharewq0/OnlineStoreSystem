package Domain.Policies.Acquisitions;

import java.util.List;

public class OrAcq extends CompositeAcq {
    public OrAcq(List<Acquisition> acquisitions) {
        super(acquisitions, Boolean::logicalOr, false);
    }

    @Override
    public String toString() {
        return "OrAcquisition{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
