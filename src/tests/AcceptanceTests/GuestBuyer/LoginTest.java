package tests.AcceptanceTests.GuestBuyer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.AcceptanceTests.BaseAccTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class LoginTest extends BaseAccTest {
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    private String username;
    private String password;
    private boolean expectedOutput;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {USERNAME, PASSWORD, true},
                {USERNAME, "wrong password", false},
                {"wrong username", PASSWORD, false},
                {"wrong username", "wrong password", false},
        });
    }

    public LoginTest(String username, String password, boolean expectedOutput){
        this.username = username;
        this.password = password;
        this.expectedOutput = expectedOutput;
    }

    @BeforeClass
    public static void setUpClass() {
        system.register(USERNAME, PASSWORD);
    }

    @Test
    public void login() {
        assertEquals(system.login(username, password), expectedOutput);
    }

    @Test
    public void loginTwice() {
        if(expectedOutput) {
            assertEquals(system.login(username, password), expectedOutput); // #1
            assertFalse(system.login(username, password)); // #2 user can't login twice
        }
    }

    @After
    public void tearDown() {
        if(expectedOutput)
            system.logout(username, password);
    }

    @AfterClass
    public static void tearDownClass() {
        system.removeUser(USERNAME, PASSWORD);
    }
}
