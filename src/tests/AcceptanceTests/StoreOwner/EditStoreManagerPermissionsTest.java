package tests.AcceptanceTests.StoreOwner;


import Domain.info.MangaerPermesions;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.AcceptanceTests.BaseAccTest;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.StoreOwner.AppointStoreOwnerTest.APPOINTEE_PASSWORD;
import static tests.AcceptanceTests.StoreOwner.AppointStoreOwnerTest.APPOINTEE_USERNAME;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditStoreManagerPermissionsTest extends BaseAccTest {
    private List<String> permissions = new LinkedList<>();

    @BeforeClass
    public static void setUpClass() {
        AppointAndFireStoreManagerTest.setUpClass();
        system.appointManager(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, APPOINTEE_PASSWORD);
    }

    @Test
    public void A_AddPermissions() {
        permissions.addAll(MangaerPermesions.defult_permesions);
        permissions.add(MangaerPermesions.permesions[0]);
        permissions.add(MangaerPermesions.permesions[1]);
        assertTrue(system.editManagerPermissions(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, permissions));
    }

    @Test
    public void B_RemovePermissions() {
        permissions.remove(MangaerPermesions.permesions[0]);
        permissions.remove(MangaerPermesions.permesions[1]);
        assertTrue(system.editManagerPermissions(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME, permissions));
    }

    @Test
    public void C_AddPermissionsToNotManager() {
        permissions.add(MangaerPermesions.permesions[0]);
        permissions.add(MangaerPermesions.permesions[1]);
        assertTrue(system.editManagerPermissions(USERNAME, PASSWORD, STORE.getName(), APPOINTEE_USERNAME + "2", permissions));
    }
}