package tests.AcceptanceTests.GuestBuyer;

import Domain.info.ProductDetails;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;

import java.util.Arrays;
import java.util.List;

import static tests.AcceptanceTests.auxiliary.Products.*;

public class SearchAndFilterTest extends BaseAccTest {

    @BeforeClass
    public static void setUpClass() {
        GetStoreDetailsTest.setUpClass();
    }

    @Test
    public void searchProductByName() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1);
        List<ProductDetails> products = system.searchProductByName(PRODUCT1.getName());
        assertEqualsLists(TrueProducts, products);

        TrueProducts = Arrays.asList(PRODUCT2);
        products = system.searchProductByName(PRODUCT2.getName());
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void searchProductByCategory() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT3);
        List<ProductDetails> products = system.searchProductByCategory(PRODUCT1.getCategory().get(0));
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void searchProductByKeyword() {
        //assume keyword check words from product name
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3);
        List<ProductDetails> products = system.searchProductByCategory("product");
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void filterByPrice() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2);
        List<ProductDetails> products = system.filterByPrice(5.0, 25.0);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void filterByRating() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2);
        List<ProductDetails> products = system.filterByRating(3, 5);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void filterByStoreRating() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2, PRODUCT3);
        List<ProductDetails> products = system.filterByStoreRating(3, 5);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void filterByCategory() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT3);
        List<ProductDetails> products = system.filterByCategory(PRODUCT1.getCategory().get(0));
        assertEqualsLists(TrueProducts, products);
    }

    @AfterClass
    public static void tearDownClass() {
        GetStoreDetailsTest.tearDownClass();
    }
}
