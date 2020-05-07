package tests.AcceptanceTests.GuestBuyer;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.AcceptanceTests.BaseAccTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class LoginTest extends BaseAccTest {
    private static String reg_username = "username";
    private static String reg_password = "password";
    private String username;
    private String password;
    private boolean expectedOutput;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {reg_username, reg_password, true},
                {reg_username, "wrong password", false},
                {"wrong username", reg_password, false},
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
        system.register(reg_username, reg_password);
    }

    @Test
    public void login() {
        assertEquals(system.login(username, password), expectedOutput);
    }

    @Test
    public void loginTwice() {
        if(expectedOutput) {
            assertEquals(system.login(username, password), expectedOutput); // #1
            assertFalse(system.register(username, password)); // #2 user can't login twice
        }
    }

    @After
    public void tearDown() {
        if(expectedOutput)
            system.logout(username);
    }

    @AfterClass
    public static void tearDownClass() {
        system.removeUser(reg_username);
    }
}
