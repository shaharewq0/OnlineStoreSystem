package tests.UnitTests;

import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import Domain.UserClasses.shoppingBasket;
import Domain.Store.StoreImp;
import Domain.UserClasses.System;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class shoppingBasketTest {


    StoreImp store;
    ProductDetails PD1;
    ProductDetails PD2;

    @Before
    public void setup() {
        store = System.getInstance().openStore("Mystore", "tel aviv", 3);
        PD1 = new ProductDetails("item 1", new LinkedList<>(), "Mystore", 5, 23);
        PD2 = new ProductDetails("item 2", new LinkedList<>(), "Mystore", 2, 65);
        //Product p = new Product( "item 1", new LinkedList<>(), new LinkedList<>(),  12,  5,  store);
        store.addProduct(PD1);
        store.addProduct(PD2);

    }

    @Test
    public void addProduct() {
        // setup();
        shoppingBasket basket = new shoppingBasket(store);
        basket.addProduct(PD1.getName(), 1);
        assertTrue(basket.getProducts() != null);
        assertEquals(1, basket.getProducts().size());
        assertTrue(basket.getProducts().contains(PD1));

    }

    @Test
    public void addProductTwice() {
        shoppingBasket basket = new shoppingBasket(store);
        basket.addProduct(PD1.getName(), 1);
        basket.addProduct(PD2.getName(), 4);
        assertTrue(basket.getProducts() != null);
        assertEquals(2, basket.getProducts().size());
        assertTrue(basket.getProducts().contains(PD1));
        assertTrue(basket.getProducts().contains(PD2));
        assertEquals(5,basket.getProducts().get(0).getAmount()+basket.getProducts().get(1).getAmount());
    }

    //
    @Test
    public void removeProduct() {
        shoppingBasket basket = new shoppingBasket(store);
        basket.addProduct(PD1.getName(), 1);
        basket.addProduct(PD2.getName(), 5);
        int removed = basket.removeProduct(PD1.getName(), 2);

        assertTrue(basket.getProducts() != null);
        assertEquals(1, basket.getProducts().size());
        assertTrue(basket.getProducts().contains(PD2));
        assertEquals(1,removed);
    }

}