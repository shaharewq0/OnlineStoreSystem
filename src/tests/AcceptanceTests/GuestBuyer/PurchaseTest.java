package tests.AcceptanceTests.GuestBuyer;

import Service_Layer.guest_accese.guest_accese;
import Service_Layer.userAddress;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tests.AcceptanceTests.auxiliary.CreditCards;

import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;

public class PurchaseTest extends BaseGuestTest {

    @BeforeClass
    public static void setUpClass() {
        WatchAndEditShoppingCartTest.setUpClass();
    }

    @Test
    public void purchase() {
        Assert.assertFalse(guest_accese.usecase2_8_Purchase_products(guestID, CreditCards.unvalid_card, new userAddress("United States", "Washington, D.C.", "1600 Pennsylvania Avenue NW", "20500"))); // card with nad expiration date
        Assert.assertTrue(guest_accese.usecase2_8_Purchase_products(guestID, CreditCards.card1, new userAddress("United States", "Washington, D.C.", "1600 Pennsylvania Avenue NW", "20500")));         // valid card, should work
    }
}
