package tests.AcceptanceTests.MemberedBuyer;

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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.fail;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT1;
import static tests.AcceptanceTests.MemberedBuyer.OpenStoreTest.STORE;
import static tests.AcceptanceTests.auxiliary.Products.PRODUCT2;

public class ReviewPurchaseHistoryTest extends BaseAccTest {
    private static int guestID;
// TODO: implement later

    @BeforeClass
    public static void setUpClass() {
        BaseGuestTest.setUpClass();
        LoginTest.setUpClass();
        PurchaseTest.setUpClass();
        guestID = BaseGuestTest.guestID;

        system.login(guestID, USERNAME, PASSWORD);   // do login with guest id
        Assert.assertTrue(guest_accese.usecase2_8_Purchase_products(guestID, CreditCards.card1, new userAddress("United States", "Washington, D.C.", "1600 Pennsylvania Avenue NW", "20500", "Donald Trump")));         // valid card, should work
    }

    @Test
    public void reviewPurchaseHistory() {
        UserPurchase p = new UserPurchase();
        List<ProductDetails> prodects = new LinkedList<>();
        prodects.add(new ProductDetails(PRODUCT1.getName(), new LinkedList<>(), STORE.getName(), 1, PRODUCT1.getPrice()));
        prodects.add(new ProductDetails(PRODUCT2.getName(), new LinkedList<>(), STORE.getName(), 5, PRODUCT2.getPrice()));
        double price = 1 * PRODUCT1.getPrice() + 5 * PRODUCT2.getPrice();
        p.eachPurchase.add(new StorePurchase(prodects, STORE.getName(), price));
        p.TotalePrice = price;

        List<UserPurchase> TruePurchases = Collections.singletonList(p);
        List<UserPurchase> purchases = system.getPurchaseHistory(USERNAME, PASSWORD);
        System.out.println(TruePurchases);
        System.out.println(purchases);

        assertEqualsLists(TruePurchases, purchases);
    }
}
