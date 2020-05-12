package tests.AcceptanceTests.GuestBuyer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* 2.2 */   RegisterTest.class,
        /* 2.3 */   LoginTest.class,
        /* 2.4 */   GetStoreDetailsTest.class,
        /* 2.5 */   SearchAndFilterTest.class,
        /* 2.6 */   SaveProductInShoppingBasketTest.class,
        /* 2.7 */   WatchAndEditShoppingCartTest.class,
        /* 2.8 */   PurchaseTest.class,
})

public class GuestBuyerTests {
}
