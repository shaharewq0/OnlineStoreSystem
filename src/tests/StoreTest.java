package tests;

import Store.Store;
import Store.Product;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class StoreTest {

    @Test
    public void addProduct() {
        Store s = new Store("myStore","berlin",4);
        assertTrue(s.addProduct(new Product("ball","play",new LinkedList<>(),5,5,s)));
        assertFalse(s.addProduct(new Product("ball","play",new LinkedList<>(),5,5,s)));
    }
}