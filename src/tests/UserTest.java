package tests;

import Store.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    //register with new id and a password
    public void registerNewMember() {
        User testUser1 = new User("new york",123123);
        int newId = 4444;
        String pass = "pass3";
        assertTrue(testUser1.register(newId,pass));
    }

    @Test
    //register with new id and a password after other register
    public void registerOldMember() {
        User testUser2 = new User("new york",123123);
        int Id1 = 2222;
        String pass1 = "pass1";
        assertTrue(testUser2.register(Id1,pass1));
        assertFalse(testUser2.register(Id1,pass1));
        int Id = 3333;
        String pass2 = "pass2";
        assertFalse(testUser2.register(Id,pass2));
    }

    @Test
    //login with correct id and a password
    public void loginOldMember() {
        User testUser2 = new User("new york",123123);
        int Id1 = 11;
        String pass1 = "11";
        assertTrue(testUser2.register(Id1,pass1));
        assertTrue(testUser2.login(Id1,pass1));
    }

    @Test
    //login 2 times
    public void loginWrong() {
        User testUser2 = new User("new york",123123);
        int Id1 = 12;
        String pass1 = "12";
        assertTrue(testUser2.register(Id1,pass1));
        assertTrue(testUser2.login(Id1,pass1));
        User testUser1 = new User("new york",123123);
        int Id2 = 13;
        String pass2 = "13";
        assertTrue(testUser1.register(Id2,pass2));
        assertTrue(testUser1.login(Id2,pass2));
        assertFalse(testUser1.login(Id1,pass1));
    }

}