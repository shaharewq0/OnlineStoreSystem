package tests.AcceptanceTests.auxiliary;

import Domain.info.ProductDetails;
import extornal.payment.CreditCard;

import java.util.Arrays;

public class CreditCards {
    public static CreditCard card1 = new CreditCard("1234-4321-1234-4321", "06/23", "123", "yosi pil");
    public static CreditCard unvalid_card = new CreditCard("1234-4321-1234-4321", "06/19", "123", "yosi pil");
}
