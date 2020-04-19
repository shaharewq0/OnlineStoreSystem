package AcceptanceTests;

import AcceptanceTests.auxiliary.StoreDetails;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoreOwnerTests extends AccTest {
    private static String username = "user1";
    private static String password = "pass1";
    private static String validStoreName = "store";
    private static String validProductName = "product";
    private static String regUsername = "user2";
    private static String unRegUsername = "user3";

    @BeforeClass
    public static void setUpInit() {
        system.init();
        system.register(username, password);
        system.register(regUsername, password);
        system.login(username, password);
        system.openStore(new StoreDetails(validStoreName));
    }

    @Test
    public void addItem() {
        assertFalse(system.hasItem(validStoreName, validProductName));
        assertTrue(system.addProductToStore(validStoreName, validProductName));
        assertTrue(system.hasItem(validStoreName, validProductName));

        assertFalse(system.addProductToStore(validStoreName, validProductName));
    }

    @Test
    public void removeItem() {
        assertFalse(system.RemoveProduct(validStoreName, validProductName));

        system.addProductToStore(validStoreName, validProductName);
        assertTrue(system.hasItem(validStoreName, validProductName));

        assertTrue(system.RemoveProduct(validStoreName, validProductName));
        assertFalse(system.hasItem(validStoreName, validProductName));
    }

    @Test
    public void EditItem() {
        /*TODO*/
    }

    @Test
    public void appointStoreOwner() {
        assertTrue(system.appointStoreOwner(regUsername));
        assertTrue(system.isStoreOwner(validStoreName, regUsername));

        assertFalse(system.appointStoreOwner(unRegUsername));
        assertFalse(system.isStoreOwner(validStoreName, unRegUsername));
    }

    @Test
    public void appointStoreManager() {
        assertTrue(system.appointStoreManager(regUsername));
        assertTrue(system.isStoreManager(validStoreName, regUsername));

        assertFalse(system.appointStoreManager(unRegUsername));
        assertFalse(system.isStoreManager(validStoreName, unRegUsername));

        system.removeStoreManager(validStoreName, regUsername);     //for cleaning
    }

    @Test
    public void editManagerPermissions() {
        /*TODO*/
    }

    @Test
    public void fireManager() {
        assertFalse(system.removeStoreManager(validStoreName, regUsername));    //not manager

        system.appointStoreManager(regUsername);
        assertTrue(system.removeStoreManager(validStoreName, regUsername));    //not manager
        assertFalse(system.isStoreManager(validStoreName, regUsername));
    }

    @Test
    public void viewStoreSellingHistory() {
        assertTrue(system.getStoreSellingHistory(validStoreName).isEmpty());

        system.addProductToStore(validStoreName, validProductName);
        system.logout();

        system.addToBasket(validStoreName, validProductName);
        //TODO: buy from basket

        system.login(username, password);
        assertFalse(system.getStoreSellingHistory(validStoreName).isEmpty());
        //TODO: check selling history
    }
}
