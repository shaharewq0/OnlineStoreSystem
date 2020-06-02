package tests.UnitTests.Policies;

import Domain.Policies.Acquisitions.AcquisitionPolicy;
import org.junit.Test;

import static org.junit.Assert.*;

public class AcquisitionPolicyTest {
    // true
    private String a1 = "0#p1#20";
    private String a2 = "1#p2#15";
    private String a3 = "1#p3#10";
    private String a4 = "10#2#" + a1 + "#" + a3;
    private String a5 = "11#0";
    private String a6 = "11#0##";
    private String a7 = "11#2#" + a2 + "#" + a1;
    private String a8 = "12#5#" + a1 + "#" + a2 + "#" + a3 + "#" + a4 + "#" + a7;

    // false
    private String a9 = "6#5#" + a1 + "#" + a2 + "#" + a3 + "#" + a4 + "#" + a7;
    private String a10 = "10#3#" + a1;


    private String accepted = "Acquisition{\n" +
            "\t0. Min. amount of 'p1' is 20\n" +
            "\t1. Max. amount of 'p2' is 15\n" +
            "\t2. Max. amount of 'p3' is 10\n" +
            "\t3. AndAcquisition{\n" +
            "\t\tMin. amount of 'p1' is 20\n" +
            "\t\tMax. amount of 'p3' is 10\n" +
            "\t}\n" +
            "\t4. OrAcquisition{\n" +
            "\t}\n" +
            "\t5. OrAcquisition{\n" +
            "\t}\n" +
            "\t6. OrAcquisition{\n" +
            "\t\tMax. amount of 'p2' is 15\n" +
            "\t\tMin. amount of 'p1' is 20\n" +
            "\t}\n" +
            "\t7. XorAcquisition{\n" +
            "\t\tMin. amount of 'p1' is 20\n" +
            "\t\tMax. amount of 'p2' is 15\n" +
            "\t\tMax. amount of 'p3' is 10\n" +
            "\t\tAndAcquisition{\n" +
            "\t\t\tMin. amount of 'p1' is 20\n" +
            "\t\t\tMax. amount of 'p3' is 10\n" +
            "\t\t}\n" +
            "\t\tOrAcquisition{\n" +
            "\t\t\tMax. amount of 'p2' is 15\n" +
            "\t\t\tMin. amount of 'p1' is 20\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    @Test
    public void AddDiscounts() {
        AcquisitionPolicy a = new AcquisitionPolicy();
        assertTrue(a.addAcquisitionPolicy(a1));
        assertTrue(a.addAcquisitionPolicy(a2));
        assertTrue(a.addAcquisitionPolicy(a3));
        assertTrue(a.addAcquisitionPolicy(a4));
        assertTrue(a.addAcquisitionPolicy(a5));
        assertTrue(a.addAcquisitionPolicy(a6));
        assertTrue(a.addAcquisitionPolicy(a7));
        assertTrue(a.addAcquisitionPolicy(a8));

        assertFalse(a.addAcquisitionPolicy(a9));
        assertFalse(a.addAcquisitionPolicy(a10));

        assertEquals(a.toString(), accepted);
    }
}
