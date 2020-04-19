package tests;

import org.junit.Assert;
import org.junit.Test;
import store_System.*;
import store_System.System;

import static org.junit.Assert.*;

public class SystemTest {
    private System s = System.getInstance();

    @Test
    //register with new id and a password
    public void registerNewMember() {
        int newId = 1111;
        String pass = "working";
        assertTrue(s.register(newId,pass));
    }

    @Test
    //register with old id
    public void registerOldMember() {
        int Id = 2222;
        String pass = "notWorking";
        assertTrue(s.register(Id,pass));
        assertFalse(s.register(Id,pass));
    }

    @Test
    //login with existing id and pass
    public void loginOldMember() {
        int Id = 1;
        String pass = "1";
        assertTrue(s.register(Id,pass));
        assertTrue(s.login(Id,pass));
    }

    @Test
    //login with new id
    public void loginNewMember() {
        int Id = 2;
        String pass = "2";
        assertFalse(s.login(Id,pass));
    }

    @Test
    //login with wrong password
    public void loginPass() {
        int Id = 3;
        String pass = "3";
        assertTrue(s.register(Id,pass));
        String wrongPass = "1";
        assertFalse(s.login(Id,wrongPass));
    }
}