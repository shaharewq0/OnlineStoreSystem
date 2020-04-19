package tests;

import Store.Product;
import Store.Store;
import org.junit.Assert;
import org.junit.Test;
import store_System.*;
import store_System.System;

import java.util.LinkedList;
import java.util.List;

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

    @Test
    //open new store
    public void openStore() {
        assertTrue(s.openStore("ebay","london",5)!=null);
        assertTrue(s.openStore("ebay","london",5)==null);
    }

    @Test
    public void getStoreDetails() {
        Store goo = s.openStore("goo","london",5);
        assertTrue(goo!=null);
        assertTrue(s.getStoreDetails("goo")==goo);
        assertTrue(s.getStoreDetails("amazon")==null);
    }

    @Test
    public void getAllStore() {
        Store a = s.openStore("a","london",5);
        Store b = s.openStore("b","london",5);
        Store c = s.openStore("b","london",5);
        assertTrue(a!=null);
        assertTrue(b!=null);
        assertTrue(c==null);
        List<Store> allStores = s.getAllStores();
        assertTrue(allStores.contains(a));
        assertTrue(allStores.contains(b));
        assertTrue(!allStores.contains(c));
    }

    @Test
    public void getProductsFromStore() {
        Store a = s.openStore("d","london",5);
        Product first = new Product("sword","play",new LinkedList<>(),5,5,a);
        assertTrue(a!=null);
        a.addProduct(first);
        assertTrue(s.getProductsFromStore("d").contains(first));
    }
}