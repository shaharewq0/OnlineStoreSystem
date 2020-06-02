package tests.AcceptanceTests.StoreOwner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* 4.1 */           ManageInventoryTest.class,
        // TODO: add 4.2
        /* 4.3 */           AppointStoreOwnerTest.class,
        /* 4.5 & 4.7 */     AppointAndFireStoreManagerTest.class,
        /* 4.6 */           EditStoreManagerPermissionsTest.class,
        /* 4.10 */          //ViewStoreSellingHistoryTest.class,
})

public class StoreOwnerTests {
}