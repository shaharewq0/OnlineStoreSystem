package tests.AcceptanceTests.GuestBuyer;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;

@RunWith(Parameterized.class)
public class GetStoreDetailsTest extends BaseAccTest {
    public static StoreDetails store = new StoreDetails("store");
    private String storeName;
    private StoreDetails expectedOutput;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"store", store},
                {"wrong store", null},
        });
    }

    public GetStoreDetailsTest(String storeName, StoreDetails expectedOutput){
        this.storeName = storeName;
        this.expectedOutput = expectedOutput;
    }

    @BeforeClass
    public static void setUpClass() {
        LoginTest.setUpClass();
        system.login(USERNAME, PASSWORD);
        // TODO: system.initialize, use systemTest setup
        //     change all to add product setup(open store)
        system.openStore(USERNAME, store);
        system.logout(USERNAME, PASSWORD);
    }

    @Test
    public void getStoreDetails() {
        assertEquals(system.getStoreDetails(storeName), expectedOutput);
    }

    @Test
    public void getProductDetails() {
        //TODO: add later
    }

    @AfterClass
    public static void tearDownClass() {
        // TODO: change all to add product teardown(open store)
        LoginTest.tearDownClass();
    }
}
