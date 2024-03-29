package tests.AcceptanceTests;

import org.junit.AfterClass;

import java.util.List;

import static org.junit.Assert.*;

public class BaseAccTest {
    protected static SystemAdapter system = new SystemAdapter();

    protected  <T> void assertEqualsLists(List<T> expected, List<T> actual) {

        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        boolean temp1 = expected.containsAll(actual);
                boolean temp2 = actual.containsAll(expected);
        assertTrue( expected.containsAll(actual));
        assertTrue(actual.containsAll(expected));
    }

    @AfterClass
    public static void tearDownAll() {
        system.resetSystem();
    }

}
