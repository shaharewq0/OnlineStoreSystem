package tests.AcceptanceTests.GuestBuyer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import tests.AcceptanceTests.auxiliary.ProductDetails;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE_THAT_DONT_EXIST;
import static tests.AcceptanceTests.auxiliary.ProductDetails.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WatchAndEditShoppingCartTest extends BaseGuestTest {

    @BeforeClass
    public static void setUpClass() {
        BaseGuestTest.setUpClass();
        system.addToBasket(guestID, STORE.getName(), PRODUCT1.getName(), 1);
        system.addToBasket(guestID, STORE.getName(), PRODUCT2.getName(), 5);
    }

    @Test
    public void A_watchShoppingCart() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2);  //amounts: 1, 5
        List<ProductDetails> products = system.watchShoppingCart(guestID);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void B_removeFromShoppingCartSomeAmount() {
        assertTrue(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT2.getName(), 2));
    }

    @Test
    public void C_watchShoppingCartAfterEdit1() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2);    //amounts: 1, 3
        List<ProductDetails> products = system.watchShoppingCart(guestID);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void D_removeFromShoppingCartAllAmount() {
        assertTrue(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT1.getName(), 1));
    }

    @Test
    public void E_watchShoppingCartAfterEdit2() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT2);    //amount: 3
        List<ProductDetails> products = system.watchShoppingCart(guestID);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void F_removeFromShoppingCartAll() {
        assertTrue(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT2.getName(), 3));
    }

    @Test
    public void G_watchShoppingCartAfterEdit3() {
        List<ProductDetails> products = system.watchShoppingCart(guestID);
        assertTrue(products == null || products.isEmpty());
    }

    @Test
    public void removeFromShoppingCartProductDontExist() {
        assertTrue(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT3.getName(), 1));
    }

    @Test
    public void removeFromShoppingCartStoreDontExist() {
        assertTrue(system.removeProductsFromCart(guestID, STORE_THAT_DONT_EXIST, PRODUCT1.getName(), 1));
    }

    @AfterClass
    public static void tearDownClass() {
        BaseGuestTest.tearDownClass();
    }
}
