package tests.AcceptanceTests.MemberedBuyer;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.GuestBuyer.LoginTest;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;

public class OpenStoreTest extends BaseAccTest {
    public static StoreDetails STORE =  new StoreDetails("store");
    public static String STORE_THAT_DONT_EXIST = "wrong store";

    @BeforeClass
    public static void setUpClass() {
        LoginTest.setUpClass();
        system.login(USERNAME, PASSWORD);
    }

    @Test
    public void openStore() {
        assertTrue(system.openStore(USERNAME, PASSWORD, STORE));
    }

    @Test
    public void openStoreTwice() {
        assertTrue(system.openStore(USERNAME, PASSWORD, STORE));    // #1
        assertFalse(system.openStore(USERNAME, PASSWORD, STORE));    // #2 user can't open store twice
    }

    @Test
    public void openStoreTwiceWithDifferentUser() {
        assertTrue(system.openStore(USERNAME, PASSWORD, STORE));    // #1
        assertFalse(system.openStore(USERNAME + "2", PASSWORD + "2", STORE));
            // #2 user can't open store with existing store name
    }

    @After
    public void tearDown() {
        system.removeStore(STORE);
    }

    @AfterClass
    public static void tearDownClass() {
        system.logout(USERNAME, PASSWORD);
        LoginTest.tearDownClass();
    }
}
