package tests.AcceptanceTests.GuestBuyer;

import tests.AcceptanceTests.BaseAccTest;

// this class is for guest use cases that require guest id
class BaseGuestTest extends BaseAccTest {
    static int guestID;

    static void setUpClass() {
        GetStoreDetailsTest.setUpClass();
        guestID = system.newGuest();
    }

    static void tearDownClass() {
        GetStoreDetailsTest.tearDownClass();
    }

}
