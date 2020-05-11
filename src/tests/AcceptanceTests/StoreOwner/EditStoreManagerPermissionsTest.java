package tests.AcceptanceTests.StoreOwner;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;

import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.StoreOwner.AppointStoreOwnerTest.APPOINTEE_PASSWORD;
import static tests.AcceptanceTests.StoreOwner.AppointStoreOwnerTest.APPOINTEE_USERNAME;

public class EditStoreManagerPermissionsTest extends BaseAccTest {

    @BeforeClass
    public static void setUpClass() {
        AppointAndFireStoreManagerTest.setUpClass();
        system.appointManager(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, APPOINTEE_PASSWORD);
    }

    @Test
    public void editPermissions() {
        //TODO
    }

    @AfterClass
    public static void tearDownClass() {
        AppointAndFireStoreManagerTest.tearDownClass();
    }
}