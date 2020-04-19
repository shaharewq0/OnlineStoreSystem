package store_System.Security;

import org.junit.Test;

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

    @org.junit.Test
    public void addRegistry() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol1.login("Shahar","YellowSubmarine"));
    }

    @org.junit.Test
    public void login() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol1.login("Shahar","YellowSubmarine"));
    }

    @org.junit.Test
    public void deleteRegistry() {
        protocol1.addRegistry("Shahar","YellowSubmarine");
        assertTrue(protocol1.login("Shahar","YellowSubmarine"));
        assertTrue(protocol1.deleteRegistry("Shahar","YellowSubmarine"));
        assertFalse(protocol1.login("Shahar","YellowSubmarine"));
    }
}