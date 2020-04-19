package tests;

import Store.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User testUser1 = new User("new york",123123);
    private User testUser2 = new User("new york",123123);

    @Test
    //register with new id and a password
    public void registerNewMember() {
        int newId = 4444;
        String pass = "pass3";
        assertTrue(testUser1.register(newId,pass));
    }

    @Test
    //register with new id and a password
    public void registerOldMember() {
        int Id1 = 2222;
        String pass1 = "pass1";
        assertTrue(testUser2.register(Id1,pass1));
        assertFalse(testUser2.register(Id1,pass1));
        int Id = 3333;
        String pass2 = "pass2";
        assertFalse(testUser2.register(Id,pass2));
    }


}