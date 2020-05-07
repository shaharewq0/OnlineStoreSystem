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

/*TODO: REMOVE BELOW*/

//public class MemberedBuyerTests extends BaseAccTest {
//    private static String username = "user1";
//    private static String password = "pass1";
//
//    @BeforeClass
//    public static void setUpInit() {
//        system.init();
//        system.register(username, password);
//    }
//
//    @Before
//    public void setUp() {
//        system.login(username, password);
//    }
//
//    @Test
//    public void logout() {
//        assertTrue(system.isLoggedIn());
////        system.logout();
//        assertFalse(system.isLoggedIn());
//    }
//
//    @Test
//    public void OpenStore() {
//        String validStoreName = "valid", invalidStoreName = "invalid";
//        StoreDetails valid = new StoreDetails(validStoreName);
//        StoreDetails invalid = new StoreDetails(invalidStoreName);
//
//        assertTrue(system.openStore(valid));
//        assertTrue(system.hasStore(validStoreName));
//        assertEquals(system.getStoreManager(validStoreName), username);
//
//        assertFalse(system.openStore(invalid));
//        assertFalse(system.hasStore(validStoreName));
//    }
//
//    @Test
//    public void ReviewPurchaseHistory() {
//        assertTrue(system.getPurchaseHistory(username).isEmpty());
//
//        //TODO: buy product and check if hist contains it
//
//        List<PurchaseDetails> hist = system.getPurchaseHistory(username);
//        assertFalse(hist.isEmpty());
//    }
//}
