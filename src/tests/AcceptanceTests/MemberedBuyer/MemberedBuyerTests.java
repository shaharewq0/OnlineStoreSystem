package tests.AcceptanceTests.MemberedBuyer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* 3.1 */   LogoutTest.class,
        /* 3.2 */   OpenStoreTest.class,
        /* 3.7 */   ReviewPurchaseHistoryTest.class,
})

public class MemberedBuyerTests {
}
