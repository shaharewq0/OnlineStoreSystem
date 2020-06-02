package tests.UnitTests.Policies;

import Domain.Policies.Discounts.DiscountPolicy;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountPolicyTest {
    // true
    private String d1 = "0#p1#20#10/10/2020";
    private String d2 = "1#p2#15#11/11/2020#2";
    private String d3 = "2#p3#10#01/11/2020#10.2";
    private String d4 = "10#2#" + d1 + "#" + d3;
    private String d5 = "11#0";
    private String d6 = "11#0##";
    private String d7 = "11#2#" + d2 + "#" + d1;
    private String d8 = "12#5#" + d1 + "#" + d2 + "#" + d3 + "#" + d4 + "#" + d7;

    // false
    private String d9 = "6#5#" + d1 + "#" + d2 + "#" + d3 + "#" + d4 + "#" + d7;
    private String d10 = "0#5#" + d1 + "#" + d2 + "#" + d3 + "#" + d4 + "#" + d7;
    private String d11 = "0#p1#20.5#10/10/2020";
    private String d12 = "0#p1#20#10/13/2020";
    private String d13 = "0#p1#20#/12/2020";
    private String d14 = "10#3" + d1;

    private String accepted = "Discounts{\n" +
            "\tDiscount on 'p1': 20% until 10/10/2020\n" +
            "\tDiscount on 'p2': 15% until 11/11/2020 if Amount >= 2\n" +
            "\tDiscount on 'p3': 10% until 01/11/2020 if Price >= 10.2\n" +
            "\tAndDiscount{\n" +
            "\t\tDiscount on 'p1': 20% until 10/10/2020\n" +
            "\t\tDiscount on 'p3': 10% until 01/11/2020 if Price >= 10.2\n" +
            "\t}\n" +
            "\tOrDiscount{\n" +
            "\t}\n" +
            "\tOrDiscount{\n" +
            "\t}\n" +
            "\tOrDiscount{\n" +
            "\t\tDiscount on 'p2': 15% until 11/11/2020 if Amount >= 2\n" +
            "\t\tDiscount on 'p1': 20% until 10/10/2020\n" +
            "\t}\n" +
            "\tXorDiscount{\n" +
            "\t\tDiscount on 'p1': 20% until 10/10/2020\n" +
            "\t\tDiscount on 'p2': 15% until 11/11/2020 if Amount >= 2\n" +
            "\t\tDiscount on 'p3': 10% until 01/11/2020 if Price >= 10.2\n" +
            "\t\tAndDiscount{\n" +
            "\t\t\tDiscount on 'p1': 20% until 10/10/2020\n" +
            "\t\t\tDiscount on 'p3': 10% until 01/11/2020 if Price >= 10.2\n" +
            "\t\t}\n" +
            "\t\tOrDiscount{\n" +
            "\t\t\tDiscount on 'p2': 15% until 11/11/2020 if Amount >= 2\n" +
            "\t\t\tDiscount on 'p1': 20% until 10/10/2020\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";


    @Test
    public void AddDiscounts() {
        DiscountPolicy d = new DiscountPolicy();
        assertTrue(d.addDiscount(d1));
        assertTrue(d.addDiscount(d2));
        assertTrue(d.addDiscount(d3));
        assertTrue(d.addDiscount(d4));
        assertTrue(d.addDiscount(d5));
        assertTrue(d.addDiscount(d6));
        assertTrue(d.addDiscount(d7));
        assertTrue(d.addDiscount(d8));

        assertFalse(d.addDiscount(d9));
        assertFalse(d.addDiscount(d10));
        assertFalse(d.addDiscount(d11));
        assertFalse(d.addDiscount(d12));
        assertFalse(d.addDiscount(d13));
        assertFalse(d.addDiscount(d14));

        assertEquals(d.toString(), accepted);
    }
}
