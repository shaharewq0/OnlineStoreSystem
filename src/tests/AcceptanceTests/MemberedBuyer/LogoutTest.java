package tests.AcceptanceTests.MemberedBuyer;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.AcceptanceTests.BaseAccTest;
import tests.AcceptanceTests.GuestBuyer.LoginTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.PASSWORD;
import static tests.AcceptanceTests.GuestBuyer.LoginTest.USERNAME;

@RunWith(Parameterized.class)
public class LogoutTest extends BaseAccTest {
    private String username;
    private String password;
    private boolean expectedOutput;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {USERNAME, PASSWORD, true},
                {"wrong username", PASSWORD, false},
                {"wrong username", "wrong password", false},
        });
    }

    public LogoutTest(String username, String password, boolean expectedOutput){
        this.username = username;
        this.password = password;
        this.expectedOutput = expectedOutput;
    }

    @BeforeClass
    public static void setUpClass() {
        LoginTest.setUpClass();
    }

    @Before
    public void setUp() {
        system.login(USERNAME, PASSWORD);
    }

    @Test
    public void logout() {
        assertEquals(system.logout(username, password), expectedOutput);
    }

    @Test
    public void logoutTwice() {
        if(expectedOutput) {
            assertEquals(system.logout(username, password), expectedOutput); // #1
            assertFalse(system.logout(username, password)); // #2 user can't logout twice
        }
    }

    @After
    public void tearDown() {
        if(!expectedOutput)
            system.logout(USERNAME, PASSWORD);
    }

    @AfterClass
    public static void tearDownClass() {
        LoginTest.tearDownClass();
    }
}
