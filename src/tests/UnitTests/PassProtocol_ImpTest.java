package tests.UnitTests;

import org.junit.Test;

import Domain.store_System.Security.PassProtocol_Imp;
import Domain.store_System.Security.PasswordProtocol;

import static org.junit.Assert.*;

public class PassProtocol_ImpTest {

    PasswordProtocol protocol1=PassProtocol_Imp.getInstance();
    PasswordProtocol protocol2=PassProtocol_Imp.getInstance();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Test
    public void getInstance() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol2.login("Shahar","YellowSubmarine"));
    }

    @Test
    public void addRegistry() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol1.login("Shahar","YellowSubmarine"));
    }

    @Test
    public void addRegistry2() {
       assertTrue( protocol1.addRegistry("daniel","YellowSubmarine"));
       assertFalse( protocol1.addRegistry("daniel","YellowSubmarine"));
        assertFalse( protocol1.addRegistry("daniel","else"));

    }

    @org.junit.Test
    public void login() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol1.login("Shahar","YellowSubmarine"));
    }

    @org.junit.Test
    public void login2() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertFalse(protocol1.login("Shahar","YellowSubmarine1"));
    }

    @org.junit.Test
    public void deleteRegistry() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol1.login("Shahar","YellowSubmarine"));
        assertTrue(protocol1.deleteRegistry("Shahar","YellowSubmarine"));
        assertFalse(protocol1.login("Shahar","YellowSubmarine"));
    }
}