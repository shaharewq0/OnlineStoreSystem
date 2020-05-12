package tests.AcceptanceTests.StoreOwner;


import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.AcceptanceTests.BaseAccTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.StoreOwner.AppointStoreOwnerTest.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppointAndFireStoreManagerTest extends BaseAccTest {

    @BeforeClass
    public static void setUpClass() {
        AppointStoreOwnerTest.setUpClass();
    }

    @Test
    public void A_appoint(){
        assertTrue(system.appointManager(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, APPOINTEE_PASSWORD));
    }

    @Test
    public void B_appointTwice(){
        assertFalse(system.appointManager(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, APPOINTEE_PASSWORD));
    }

    @Test
    public void C_appointNotMember(){
        assertFalse(system.appointManager(USERNAME, PASSWORD, STORE.getName(), NOT_USERNAME, NOT_PASSWORD));
    }

    @Test
    public void D_fireManager(){
        assertTrue(system.fireManager(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME));
    }

    @Test
    public void E_fireNotManager(){
        assertFalse(system.fireManager(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME));
    }
}