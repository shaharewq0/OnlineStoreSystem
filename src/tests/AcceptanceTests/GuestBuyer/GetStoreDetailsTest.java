package tests.AcceptanceTests.GuestBuyer;

import Domain.info.ProductDetails;
import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.StoreOwner.ManageInventoryTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE_THAT_DONT_EXIST;
import static tests.AcceptanceTests.auxiliary.Products.*;

public class GetStoreDetailsTest extends BaseAccTest {

    @BeforeClass
    public static void setUpClass() {
        // TODO: system.initialize, use systemTest setup
        ManageInventoryTest.setUpClass();
        system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1);
        system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2);
        system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3);

        system.logout(USERNAME, PASSWORD); //the test is for guest
    }

    @Test
    public void getStoreDetails() {
        assertEquals(system.getStoreDetails(STORE.getName()), STORE);
    }

    @Test
    public void getStoreDetailsDontExist() {
        assertNull(system.getStoreDetails(STORE_THAT_DONT_EXIST));
    }

    @Test
    public void getProductsFromStore() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3);
        List<ProductDetails> products = system.getProductsFromStore(STORE.getName());
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void getProductsFromStoreDontExist() {
        List<ProductDetails> products = system.getProductsFromStore(STORE_THAT_DONT_EXIST);
        assertTrue(products == null || products.isEmpty());
    }
}
