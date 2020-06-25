package tests.AcceptanceTests.SystemManager;

import Domain.UserClasses.UserPurchase;
import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import Service_Layer.guest_accese.guest_accese;
import Service_Layer.userAddress;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.GuestBuyer.BaseGuestTest;
import tests.AcceptanceTests.GuestBuyer.LoginTest;
import tests.AcceptanceTests.GuestBuyer.PurchaseTest;
import tests.AcceptanceTests.auxiliary.CreditCards;
import tests.AcceptanceTests.auxiliary.PurchaseDetails;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT1;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT2;

public class ViewPurchasesHistoryTest extends BaseAccTest {

    private static int guestID;

    @BeforeClass
    public static void setUpClass() {
        BaseGuestTest.setUpClass();
        LoginTest.setUpClass();
        PurchaseTest.setUpClass();
        guestID = BaseGuestTest.guestID;

        system.login(guestID, USERNAME, PASSWORD);   // do login with guest id
        Assert.assertTrue(guest_accese.usecase2_8_Purchase_products(guestID, CreditCards.card1, new userAddress("england", "london", "Beckenham Place", "123456789", "Queen Elizabeth ")));         // valid card, should work
    }

    @Test
    public void reviewUserPurchaseHistory() {
        UserPurchase p = new UserPurchase();
        List<ProductDetails> prodects = new LinkedList<>();
        prodects.add(new ProductDetails(PRODUCT2.getName(),PRODUCT2.getCategory(), STORE.getName(), 5, PRODUCT2.getPrice(),PRODUCT2.getRating()));
        prodects.add(new ProductDetails(PRODUCT1.getName(),PRODUCT1.getCategory(), STORE.getName(), 1, PRODUCT1.getPrice(),PRODUCT1.getRating()));
        double price = 1*PRODUCT1.getPrice() + 5*PRODUCT2.getPrice();
        p.eachPurchase.add(new StorePurchase(prodects, STORE.getName(), price));
        p.TotalePrice = price;
        List<UserPurchase> TruePurchases = Collections.singletonList(p);

        List<UserPurchase> purchases = system.getPurchaseHistory(USERNAME, PASSWORD);



        assertEqualsLists(TruePurchases, purchases);
    }

    @Test
    public void reviewStoreSellingHistory() {

        List<ProductDetails> prodects = new LinkedList<>();
         prodects.add(new ProductDetails(PRODUCT2.getName(),PRODUCT2.getCategory(), STORE.getName(), 5, PRODUCT2.getPrice(),PRODUCT2.getRating()));
        prodects.add(new ProductDetails(PRODUCT1.getName(), PRODUCT1.getCategory(), STORE.getName(), 1, PRODUCT1.getPrice(),PRODUCT1.getRating()));
        double price = 1*PRODUCT1.getPrice() + 5*PRODUCT2.getPrice();

        List<StorePurchase> TruePurchases = Collections.singletonList(new StorePurchase(prodects, STORE.getName(), price));

        List<StorePurchase> purchases = system.getStoreSellingHistory(USERNAME, PASSWORD, STORE.getName());

        assertEqualsLists(TruePurchases, purchases);
    }

}
