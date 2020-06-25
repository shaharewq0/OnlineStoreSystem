package tests.DALTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AcquisitionPolicy_DATest.class,
        Password_DATest.class,
        Product_bundle_DATest.class,
        Product_DATest.class,
        Product_Details_DATest.class,
        Question_DATest.class,
        Store_Inventory_DATest.class,
        Store_Purchase_History_DATest.class,
        StorePurchase_DATest.class,
        User_DATest.class,
        UserPurchase_DATest.class,
        UserPurchaseHistory_DATest.class,
        Basket_DATest.class,
        Cart_DATest.class,
})

public class AllDALTests {
}
