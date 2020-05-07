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
        system.login(LoginTest.reg_username, LoginTest.reg_password);
        // TODO: system.initialize, use systemTest setup
        //     maybe change all to open store setup
        system.openStore(LoginTest.reg_username, store);
        system.logout(LoginTest.reg_username);
    }

    @Test
    public void getStoreDetails() {
        assertEquals(system.getStoreDetails(storeName), expectedOutput);
    }

    @AfterClass
    public static void tearDownClass() {
        // TODO: open store teardown
        LoginTest.tearDownClass();
    }
}
