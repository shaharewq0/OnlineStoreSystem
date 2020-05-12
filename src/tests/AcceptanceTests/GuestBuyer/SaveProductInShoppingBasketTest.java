package tests.AcceptanceTests.GuestBuyer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE_THAT_DONT_EXIST;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT1;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT_THAT_DONT_EXIST;

public class SaveProductInShoppingBasketTest extends BaseGuestTest {

    @BeforeClass
    public static void setUpClass() {
        BaseGuestTest.setUpClass();
    }

    @Test
    public void addToBasket() {
        assertTrue(system.addToBasket(guestID, STORE.getName(), PRODUCT1.getName(), 1));
    }

    @Test
    public void addToBasketMoreThenAmount() {
        assertFalse(system.addToBasket(guestID, STORE.getName(), PRODUCT1.getName(), 10));
    }

    @Test
    public void addToBasketProductDontExist() {
        assertFalse(system.addToBasket(guestID, STORE.getName(), PRODUCT_THAT_DONT_EXIST, 1));
    }

    @Test
    public void addToBasketStoreDontExist() {
        assertFalse(system.addToBasket(guestID, STORE_THAT_DONT_EXIST, PRODUCT1.getName(), 1));
    }

    @AfterClass
    public static void tearDownClass() {
        system.clearShoppingCart(guestID);
        BaseGuestTest.tearDownClass();
    }
}
