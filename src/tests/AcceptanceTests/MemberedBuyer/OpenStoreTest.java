package tests.AcceptanceTests.MemberedBuyer;


import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.GuestBuyer.LoginTest;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OpenStoreTest extends BaseAccTest {
        /*
        In this class the order of tests is important
        so each method has a prefix: <letter>_<MethodName>
        to determine the order
     */

    public static StoreDetails STORE =  new StoreDetails("store", 4);
    public static String STORE_THAT_DONT_EXIST = "wrong store";

    @BeforeClass
    public static void setUpClass() {
        LoginTest.setUpClass();
        system.login(USERNAME, PASSWORD);
    }

    @Test
    public void A_openStore() {
        assertTrue(system.openStore(USERNAME, PASSWORD, STORE));
    }

    @Test
    public void B_openStoreTwice() {
        assertFalse(system.openStore(USERNAME, PASSWORD, STORE));    // #2 user can't open store twice
    }

    @Test
    public void C_openStoreTwiceWithDifferentUser() {
        assertFalse(system.openStore(USERNAME + "2", PASSWORD + "2", STORE));
            // #2 user can't open store with existing store name
    }
}
