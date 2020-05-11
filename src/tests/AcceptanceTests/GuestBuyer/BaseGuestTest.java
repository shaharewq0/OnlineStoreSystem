package tests.AcceptanceTests.GuestBuyer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import tests.AcceptanceTests.BaseAccTest;

// this class is for guest use cases that require guest id
public class BaseGuestTest extends BaseAccTest {
    static int guestID;

    @BeforeClass
    public static void setUpClass() {
        GetStoreDetailsTest.setUpClass();
        guestID = system.newGuest();
    }

    @AfterClass
    public static void tearDownClass() {
        GetStoreDetailsTest.tearDownClass();
    }

}
