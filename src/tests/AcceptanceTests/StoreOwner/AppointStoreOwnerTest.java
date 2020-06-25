package tests.AcceptanceTests.StoreOwner;


import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.MemberedBuyer.OpenStoreTest;

import java.util.Collection;

import static org.junit.Assert.*;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppointStoreOwnerTest extends BaseAccTest {
    static String APPOINTEE_USERNAME = "appointee username";
    static String APPOINTEE_PASSWORD = "appointee password";
    static String NOT_USERNAME = "wrong username";
    static String NOT_PASSWORD = "wrong password";

    @BeforeClass
    public static void setUpClass() {
        OpenStoreTest.setUpClass();
        system.openStore(USERNAME, PASSWORD, STORE);

        system.register(APPOINTEE_USERNAME, APPOINTEE_PASSWORD);
        system.login(APPOINTEE_USERNAME, APPOINTEE_PASSWORD);
    }

    @Test
    public void appoint(){
        assertTrue(system.appointOwner(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, APPOINTEE_PASSWORD));
    }

    @Test
    public void appointPending(){
        Collection<String> apootees = system.getPendingApointments(USERNAME, PASSWORD, STORE.getName());
        assertEquals(1, apootees.size());
        assertTrue(apootees.contains(APPOINTEE_USERNAME));
        assertTrue(system.AcceptPendingApointments(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME));
    }

    @Test
    public void appointNotMember(){
        assertFalse(system.appointOwner(USERNAME, PASSWORD, STORE.getName(), NOT_USERNAME, NOT_PASSWORD));
    }
}