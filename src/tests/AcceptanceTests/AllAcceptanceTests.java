package tests.AcceptanceTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.AcceptanceTests.GuestBuyer.GuestBuyerTests;
import tests.AcceptanceTests.MemberedBuyer.MemberedBuyerTests;
import tests.AcceptanceTests.StoreOwner.StoreOwnerTests;
import tests.AcceptanceTests.System.SystemTests;
import tests.AcceptanceTests.SystemManager.SystemManagerTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SystemTests.class,
        GuestBuyerTests.class,
        MemberedBuyerTests.class,
        StoreOwnerTests.class,
//        StoreManagerTests.class,
        SystemManagerTests.class,
//        PayingSystemTests.class,
//        SupplySystemTests.class,
})

public class AllAcceptanceTests {
}
