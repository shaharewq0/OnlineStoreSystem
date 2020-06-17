package tests.AcceptanceTests.GuestBuyer;

import tests.AcceptanceTests.BaseAccTest;

// this class is for guest use cases that require guest id
public class BaseGuestTest extends BaseAccTest {
    public static int guestID;

    public static void setUpClass() {
        GetStoreDetailsTest.setUpClass();
        guestID = system.newGuest();
    }
}
