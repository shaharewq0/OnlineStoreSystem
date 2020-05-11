package tests.AcceptanceTests.GuestBuyer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.auxiliary.ProductDetails;

import java.util.Arrays;
import java.util.List;

import static tests.AcceptanceTests.auxiliary.ProductDetails.*;

@RunWith(Parameterized.class)
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
        List<ProductDetails> products = system.searchProductByCategory(PRODUCT1.getCategory());
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void searchProductByKeyword() {
        //TODO
    }

    @Test
    public void filterByPrice() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT2);
        List<ProductDetails> products = system.filterByPrice(5.0, 25.0);
        assertEqualsLists(TrueProducts, products);
    }

    @Test
    public void filterByRating() {
        //TODO
    }

    @Test
    public void filterByStoreRating() {
        //TODO
    }

    @Test
    public void filterByCategory() {
        List<ProductDetails> TrueProducts = Arrays.asList(PRODUCT1, PRODUCT3);
        List<ProductDetails> products = system.filterByCategory(PRODUCT1.getCategory());
        assertEqualsLists(TrueProducts, products);
    }

    @AfterClass
    public static void tearDownClass() {
        GetStoreDetailsTest.tearDownClass();
    }
}
