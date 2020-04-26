package tests.UnitTests;

import org.junit.Test;

import Domain.Store.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void addProduct() {
        StoreImp s = new StoreImp("myStore","berlin",4);
        assertTrue(s.addProduct(new Product("ball","play",new LinkedList<>(),5,5,s)));
        assertFalse(s.addProduct(new Product("ball","play",new LinkedList<>(),5,5,s)));
    }
}
