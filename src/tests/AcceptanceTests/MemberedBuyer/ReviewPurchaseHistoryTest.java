package tests.AcceptanceTests.MemberedBuyer;

import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.GuestBuyer.PurchaseTest;

import static org.junit.Assert.fail;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;

public class ReviewPurchaseHistoryTest extends BaseAccTest {
// TODO: implement later

    @BeforeClass
    public static void setUpClass() {
        PurchaseTest.setUpClass();
        system.login(USERNAME, PASSWORD);   // do login with guest id
        //TODO: check that login keep the guest cart
        //  call purchase
    }

    @Test
    public void reviewPurchaseHistory() {
//        List<PurchaseDetails> TruePurchases = Arrays.asList();
//        List<PurchaseDetails> purchases = system.viewPurchaseHistory();
//        assertEqualsLists(TruePurchases, purchases);
    }
}
