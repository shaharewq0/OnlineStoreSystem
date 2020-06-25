package Domain.Policies.Acquisitions;

import java.util.List;

public class AndAcq extends CompositeAcq {
    public AndAcq(List<Acquisition> acquisitions) {
        super(acquisitions, Boolean::logicalAnd, true);
    }

    @Override
    public String toString() {
        return "AndAcquisition{" +
                super.toString().replace("\n", "\n\t") +
                "\n\t}";
    }
}
