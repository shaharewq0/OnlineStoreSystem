package tests.UnitTests;

import Domain.info.ProductDetails;
import Domain.store_System.System;
import org.junit.Before;
import org.junit.Test;

import Domain.Store.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreTest {

    ProductDetails PD1;
    ProductDetails PD2;
    String storename = "Mystore";


    @Before
    public void setup() {
        PD1 = new ProductDetails("item 1", new LinkedList<>(), storename, 5, 23);
        PD2 = new ProductDetails("item 2", new LinkedList<>(), storename, 2, 65);
        //Product p = new Product( "item 1", new LinkedList<>(), new LinkedList<>(),  12,  5,  store);

    }

    @Test
    public void CreateStore() {
        StoreImp s = new StoreImp(storename,"berlin",4);
        assertEquals("myStore",s.getName());
        assertEquals("berlin",s.getAddress());
        assertEquals(4,s.getRating());
    }

    @Test
    public void addProducts() {
        StoreImp store = new StoreImp(storename,"berlin",4);
        store.addProduct(PD1);
        store.addProduct(PD2);

        assertEquals(2,store.getProductsDetails().size());
        assertEquals(PD1,store.findProductDetailsByName(PD1.getName()));
        assertEquals(PD2,store.findProductDetailsByName(PD2.getName()));

    }

}
