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
    //register with new id and a password
    public void registerOldMember() {
        int Id = 2222;
        String pass = "notWorking";
        assertTrue(s.register(Id,pass));
        assertFalse(s.register(Id,pass));
    }
}