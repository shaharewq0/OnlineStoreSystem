package tests.AcceptanceTests.SystemManager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        /* 6.4 */   ViewPurchasesHistoryTest.class,
})

public class SystemManagerTests {
}

/*TODO: REMOVE BELOW*/

//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import tests.AcceptanceTests.BaseAccTest;
//import tests.AcceptanceTests.auxiliary.PurchaseDetails;
//
//public class SystemManagerTests extends BaseAccTest {
//    private static String username = "user1";
//    private static String password = "pass1";
//    private static String regUsername = "user2";
//    private static String unRegUsername = "user3";
//
//
//    @BeforeClass
//    public static void setUpInit() {
//        system.init();
//        system.register(username, password);
//        system.register(regUsername, password);
//        system.login(username, password);
//    }
//
//
//    @Test
//    public void viewUserPurchaseHistory() {
//        assertTrue(system.getPurchaseHistory(regUsername).isEmpty());
//
//        system.logout();
//        system.login(regUsername, password);
//        //TODO: buy product and check if hist contains it
//        system.logout();
//
//        system.login(username, password);
//        List<PurchaseDetails> hist = system.getPurchaseHistory(regUsername);
//        assertFalse(hist.isEmpty());
//
//
//        assertNull(system.getPurchaseHistory(unRegUsername));
//    }
//}
