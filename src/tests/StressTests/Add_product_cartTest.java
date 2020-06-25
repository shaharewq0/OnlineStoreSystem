package tests.StressTests;

import Service_Layer.guest_accese.guest_accese;
import Service_Layer.member_accese.member_accese;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE_THAT_DONT_EXIST;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT1;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT_THAT_DONT_EXIST;

public class Add_product_cartTest {
    @Test
    public void addproduct() {
        String user = RandomStringGenerator.randomString(10);
        int guestID = guest_accese.ImNew();
            assertTrue(guest_accese.usecase2_6_saveProductToBasket(guestID, STORE.getName(), PRODUCT1.getName(), 1));


            assertFalse(guest_accese.usecase2_6_saveProductToBasket(guestID, STORE.getName(), PRODUCT1.getName(), 100));


            assertFalse(guest_accese.usecase2_6_saveProductToBasket(guestID, STORE.getName(), PRODUCT_THAT_DONT_EXIST, 1));

            assertFalse(guest_accese.usecase2_6_saveProductToBasket(guestID, STORE_THAT_DONT_EXIST, PRODUCT1.getName(), 1));

    }
}
