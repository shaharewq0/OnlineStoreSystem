//TODO: REMOVE THIS CLASS
package tests.AcceptanceTests.GuestBuyer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.auxiliary.ProductDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

public class GuestBuyerTests extends BaseAccTest {
    private static String username = "user1";
    private static String password = "pass1";
    private static String validStoreName = "store";
    private static String invalidStoreName = "not-store";
    private static String validProductName = "product";
    private static String invalidProductName = "not-product";
    private static String validCategory = "category";
    private static String invalidCategory = "not-category";
    private static String productNameWithKeyword = "product with key word";
    private static String keyword = "key";

    @BeforeClass
    public static void setUpInit() {
        system.init();
        /* TODO:
            add some stores and products
         */
        system.openStore(new StoreDetails(validStoreName));
    }

    
    @After
    public  void tearDown() {
        if (system.isLoggedIn())
//            system.logout();
        system.clearShoppingCart();
    }




    @Test
    public void productDetails() {
    	//TODO need to make sore validstorename
        ProductDetails product = system.getProductDetails(validStoreName, validProductName);
        assertNotNull(product);
        assertEquals(product.getName(), validProductName);
        assertEquals(product.getStoreName(), validStoreName);

        assertNull(system.getProductDetails(invalidStoreName, validProductName));

        assertNull(system.getProductDetails(validStoreName, invalidProductName));

        assertNull(system.getProductDetails(invalidStoreName, invalidProductName));
    }

    @Test
    public void searchProductByName() {
        List<ProductDetails> products = system.searchProductByName(validProductName);
        assertFalse(products.isEmpty());
        for (ProductDetails p : products) {
            assertEquals(p.getName(), validProductName);
        }

        products = system.searchProductByName(invalidProductName);
        assertTrue(products.isEmpty());
    }

    @Test
    public void searchProductByCategory() {
        List<ProductDetails> products = system.searchProductByCategory(validCategory);
        assertFalse(products.isEmpty());
        for (ProductDetails p : products) {
            assertEquals(p.getCategory(), validCategory);
        }

        products = system.searchProductByCategory(invalidCategory);
        assertTrue(products.isEmpty());
    }

    @Test
    public void searchProductByKeyword() {
        List<ProductDetails> products = system.searchProductByKeyword(keyword);
        assertFalse(products.isEmpty());
        for (ProductDetails p : products) {
            assertTrue(p.getName().contains(keyword));
        }

        products = system.searchProductByKeyword("not_key_word");
        assertTrue(products.isEmpty());
    }

    @Test
    public void filter() {
        /*TODO*/
    }

    @Test
    public void saveProducts() {
        assertFalse(system.inBasket(validStoreName, validProductName));
        system.addToBasket(validStoreName, validProductName);
        assertTrue(system.inBasket(validStoreName, validProductName));

        system.addToBasket(invalidStoreName, validProductName);
        assertFalse(system.inBasket(invalidStoreName, validProductName));

        system.addToBasket(validStoreName, invalidProductName);
        assertFalse(system.inBasket(validStoreName, invalidProductName));

        system.addToBasket(invalidStoreName, invalidProductName);
        assertFalse(system.inBasket(invalidStoreName, invalidProductName));
    }

    @Test
    public void watchShoppingCart() {
        List<ProductDetails> cart = system.getShoppingCart();
        assertTrue(cart.isEmpty());

        system.addToBasket(validStoreName, validProductName);
        cart = system.getShoppingCart();
        assertFalse(cart.isEmpty());
        assertEquals(cart.get(0).getName(), validProductName);
        assertEquals(cart.get(0).getStoreName(), validStoreName);
    }

    @Test
    public void editShoppingCart() {
        /*TODO*/
    }

    @Test
    public void shopFromCart() {
        /*TODO*/
    }

    @Test
    public void shopFromBasket() {
        /*TODO*/
    }
}
