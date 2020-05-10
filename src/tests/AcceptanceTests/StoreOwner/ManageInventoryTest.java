package tests.AcceptanceTests.StoreOwner;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.MemberedBuyer.OpenStoreTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE_THAT_DONT_EXIST;
import static tests.AcceptanceTests.auxiliary.ProductDetails.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManageInventoryTest extends BaseAccTest {
    /*
        In this class the order of tests is important
        so each method has a prefix: <letter>_<MethodName>
        to determine the order
     */

    @BeforeClass
    public static void setUpClass() {
        OpenStoreTest.setUpClass();
    }

    @Test
    public void A_AddProduct() {
        assertTrue(system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1));
        assertTrue(system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2));
        assertTrue(system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3));
    }

    @Test
    public void B_AddProductTwice() {
        assertFalse(system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1));
        assertFalse(system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2));
        assertFalse(system.addProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3));
    }

    @Test
    public void C_AddProductToStoreThatDontExist() {
        assertFalse(system.addProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT1));
        assertFalse(system.addProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT2));
        assertFalse(system.addProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT3));
    }

    @Test
    public void D_EditProduct() {
        assertTrue(system.EditProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1.getName(), PRODUCT1_CHANGED_PRICE));
        assertTrue(system.EditProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2.getName(), PRODUCT2_CHANGED_CATEGORY));
        assertTrue(system.EditProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3.getName(), PRODUCT3_CHANGED_NAME));
    }

    @Test
    public void E_RemoveProduct() {
        assertTrue(system.RemoveProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1.getName()));
        assertTrue(system.RemoveProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2.getName()));
        assertTrue(system.RemoveProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3.getName()));
    }

    @Test
    public void F_EditProductThatDontExist() {
        assertFalse(system.EditProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1.getName(), PRODUCT1_CHANGED_PRICE));
        assertFalse(system.EditProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2.getName(), PRODUCT2_CHANGED_CATEGORY));
        assertFalse(system.EditProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3.getName(), PRODUCT3_CHANGED_NAME));
    }


    @Test
    public void G_RemoveProductThatDontExist() {
        assertFalse(system.RemoveProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT1.getName()));
        assertFalse(system.RemoveProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT2.getName()));
        assertFalse(system.RemoveProduct(USERNAME, PASSWORD, STORE.getName(), PRODUCT3.getName()));
    }

    @Test
    public void H_EditProductFromStoreThatDontExist() {
        assertFalse(system.EditProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT1.getName(), PRODUCT1_CHANGED_PRICE));
        assertFalse(system.EditProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT2.getName(), PRODUCT2_CHANGED_CATEGORY));
        assertFalse(system.EditProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT3.getName(), PRODUCT3_CHANGED_NAME));
    }

    @Test
    public void I_RemoveProductFromStoreThatDontExist() {
        assertFalse(system.RemoveProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT1.getName()));
        assertFalse(system.RemoveProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT2.getName()));
        assertFalse(system.RemoveProduct(USERNAME, PASSWORD, STORE_THAT_DONT_EXIST, PRODUCT3.getName()));
    }

    @AfterClass
    public static void tearDownClass() {
        OpenStoreTest.tearDownClass();
    }
}
