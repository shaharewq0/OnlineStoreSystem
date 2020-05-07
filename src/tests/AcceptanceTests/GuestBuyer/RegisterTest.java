package tests.AcceptanceTests.GuestBuyer;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.AcceptanceTests.BaseAccTest;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class RegisterTest extends BaseAccTest {
    private String username;
    private String password;
    private boolean expectedOutput;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"username", "password", true},
                {"username", "very very very long password", true},
                {"very very very long username", "password", true},
                {"username", "", true},
                {"", "password", true},
        });
    }

    public RegisterTest(String username, String password, boolean expectedOutput){
        this.username = username;
        this.password = password;
        this.expectedOutput = expectedOutput;
    }

    @Test
    public void register() {
        assertEquals(system.register(username, password), expectedOutput);
    }

    @Test
    public void registerTwice() {
        assertEquals(system.register(username, password), expectedOutput); // #1
        assertFalse(system.register(username, password)); // #2 user can't register twice
    }

    @Test
    public void registerWithExistingUsername() {
        assertEquals(system.register(username, password), expectedOutput); // #1
        assertFalse(system.register(username, "dummy password")); // #2 user can't register with existing username
    }

    @After
    public void tearDown() {
        system.removeUser(username);
    }
}
