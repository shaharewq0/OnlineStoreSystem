package tests.AcceptanceTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SystemTests.class,
        GuestBuyerTests.class,
        MemberedBuyerTests.class,
        StoreOwnerTests.class,
        StoreManagerTests.class,
        SystemManagerTests.class,
        PayingSystemTests.class,
        SupplySystemTests.class,
})

public class AllAcceptanceTests {
}