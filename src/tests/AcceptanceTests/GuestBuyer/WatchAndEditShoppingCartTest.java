package tests.AcceptanceTests.GuestBuyer;

import Domain.info.ProductDetails;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE_THAT_DONT_EXIST;
import static tests.AcceptanceTests.auxiliary.Products.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WatchAndEditShoppingCartTest extends BaseGuestTest {

    public static ProductDetails TEMPPRODUCT1 = new ProductDetails("product 1", Arrays.asList("cat1"), "store", 1, 10.0, 3);
    public static ProductDetails TEMPPRODUCT2 = new ProductDetails("product 2", Arrays.asList("cat2"), "store", 5,20.0,  4);


    @BeforeClass
    public static void setUpClass() {
        BaseGuestTest.setUpClass();
        system.addToBasket(guestID, STORE.getName(), PRODUCT1.getName(), 1);
        system.addToBasket(guestID, STORE.getName(), PRODUCT2.getName(), 5);
    }

    @Test
    public void A_watchShoppingCart() {
        List<ProductDetails> TrueProducts = Arrays.asList(TEMPPRODUCT1, TEMPPRODUCT2);  //amounts: 1, 5
        List<ProductDetails> products = system.watchShoppingCart(guestID);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void B_removeFromShoppingCartSomeAmount() {
        TEMPPRODUCT2.setAmount(3);
        assertTrue(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT2.getName(), 2));
    }

    @Test
    public void C_watchShoppingCartAfterEdit1() {
        List<ProductDetails> TrueProducts = Arrays.asList(TEMPPRODUCT1, TEMPPRODUCT2);    //amounts: 1, 3
        List<ProductDetails> products = system.watchShoppingCart(guestID);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void D_removeFromShoppingCartAllAmount() {
        TEMPPRODUCT1.setAmount(0);
        assertTrue(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT1.getName(), 1));
    }

    @Test
    public void E_watchShoppingCartAfterEdit2() {
        List<ProductDetails> TrueProducts = Arrays.asList(TEMPPRODUCT2);    //amount: 3
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
        assertFalse(system.removeProductsFromCart(guestID, STORE.getName(), PRODUCT3.getName(), 1));
    }

    @Test
    public void removeFromShoppingCartStoreDontExist() {
        assertFalse(system.removeProductsFromCart(guestID, STORE_THAT_DONT_EXIST, PRODUCT1.getName(), 1));
    }
}
