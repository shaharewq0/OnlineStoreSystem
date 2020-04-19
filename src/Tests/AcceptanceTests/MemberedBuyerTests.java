package Tests.AcceptanceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Tests.AcceptanceTests.auxiliary.PurchaseDetails;
import Tests.AcceptanceTests.auxiliary.StoreDetails;

public class MemberedBuyerTests extends AccTest {
    private static String username = "user1";
    private static String password = "pass1";

    @BeforeClass
    public static void setUpInit() {
        system.init();
        system.register(username, password);
    }

    @Before
    public void setUp() {
        system.login(username, password);
    }

    @Test
    public void logout() {
        assertTrue(system.isLoggedIn());
        system.logout();
        assertFalse(system.isLoggedIn());
    }

    @Test
    public void OpenStore() {
        String validStoreName = "valid", invalidStoreName = "invalid";
        StoreDetails valid = new StoreDetails(validStoreName);
        StoreDetails invalid = new StoreDetails(invalidStoreName);

        assertTrue(system.openStore(valid));
        assertTrue(system.hasStore(validStoreName));
        assertEquals(system.getStoreManager(validStoreName), username);

        assertFalse(system.openStore(invalid));
        assertFalse(system.hasStore(validStoreName));
    }

    @Test
    public void ReviewPurchaseHistory() {
        assertTrue(system.getPurchaseHistory(username).isEmpty());

        //TODO: buy product and check if hist contains it

        List<PurchaseDetails> hist = system.getPurchaseHistory(username);
        assertFalse(hist.isEmpty());
    }
}
