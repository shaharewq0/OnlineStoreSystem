package tests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import Store.Product;
import Store.StoreImp;
import store_System.System;

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
        StoreImp goo = s.openStore("goo","london",5);
        assertTrue(goo!=null);
        assertTrue(s.getStoreDetails("goo")==goo);
        assertTrue(s.getStoreDetails("amazon")==null);
    }

    @Test
    public void getAllStore() {
        StoreImp a = s.openStore("a","london",5);
        StoreImp b = s.openStore("b","london",5);
        StoreImp c = s.openStore("b","london",5);
        assertTrue(a!=null);
        assertTrue(b!=null);
        assertTrue(c==null);
        List<StoreImp> allStores = s.getAllStores();
        assertTrue(allStores.contains(a));
        assertTrue(allStores.contains(b));
        assertTrue(!allStores.contains(c));
    }

    @Test
    public void getProductsFromStore() {
        StoreImp a = s.openStore("d","london",5);
        Product first = new Product("sword","play",new LinkedList<>(),5,5,a);
        assertTrue(a!=null);
        a.addProduct(first);
        assertTrue(s.getProductsFromStore("d").contains(first));
    }

    @Test
    public void searchProductByName() {
        StoreImp a = s.openStore("e","london",5);
        Product first = new Product("armor","play",new LinkedList<>(),5,5,a);
        StoreImp b = s.openStore("f","london",5);
        Product second = new Product("armor","play",new LinkedList<>(),5,5,b);
        Product third = new Product("gun","play",new LinkedList<>(),5,5,b);
        assertTrue(a!=null);
        assertTrue(b!=null);
        a.addProduct(first);
        b.addProduct(second);
        b.addProduct(third);
        assertTrue(s.searchProductsByName("armor").contains(first));
        assertTrue(s.searchProductsByName("armor").contains(second));
        assertTrue(!s.searchProductsByName("armor").contains(third));
    }

    @Test
        public void searchProductByCategory() {
        StoreImp a = s.openStore("h","london",5);
        Product first = new Product("armor","play",new LinkedList<>(),5,5,a);
        Product second = new Product("sword","drama",new LinkedList<>(),5,5,a);
        Product third = new Product("gun","play",new LinkedList<>(),5,5,a);
        assertTrue(a!=null);
        a.addProduct(first);
        a.addProduct(second);
        a.addProduct(third);
        assertTrue(s.searchProductsByCategory("play").contains(first));
        assertTrue(!s.searchProductsByCategory("play").contains(second));
        assertTrue(s.searchProductsByCategory("play").contains(third));
    }

    @Test
    public void searchProductByKeywords() {
        StoreImp a = s.openStore("k","london",5);
        List<String> keywords1 = new LinkedList<>();
        keywords1.add("cheap");
        keywords1.add("solid");
        List<String> keywords2 = new LinkedList<>();
        keywords2.add("solid");
        Product first = new Product("armor","play",keywords1,5,5,a);
        Product second = new Product("sword","drama",keywords2,5,5,a);
        Product third = new Product("gun","play",keywords2,5,5,a);
        assertTrue(a!=null);
        a.addProduct(first);
        a.addProduct(second);
        a.addProduct(third);
        assertTrue(s.searchProductsByKeyword("solid").contains(first));
        assertTrue(s.searchProductsByKeyword("solid").contains(second));
        assertTrue(s.searchProductsByKeyword("solid").contains(third));
        assertTrue(s.searchProductsByKeyword("cheap").contains(first));
        assertTrue(!s.searchProductsByKeyword("cheap").contains(second));
        assertTrue(!s.searchProductsByKeyword("cheap").contains(third));
    }

    @Test
    public void filterByPrice() {
        StoreImp a = s.openStore("j","london",5);
        Product first = new Product("armor","play",new LinkedList<>(),7,5,a);
        Product second = new Product("sword","drama",new LinkedList<>(),5,5,a);
        Product third = new Product("gun","play",new LinkedList<>(),1,5,a);
        assertTrue(a!=null);
        a.addProduct(first);
        a.addProduct(second);
        a.addProduct(third);
        assertTrue(s.filterByPrice(a.getProducts(),4,8).contains(first));
        assertTrue(s.filterByPrice(a.getProducts(),4,8).contains(second));
        assertTrue(!s.filterByPrice(a.getProducts(),4,8).contains(third));
    }

    @Test
    public void filterByRating() {
        StoreImp a = s.openStore("z","london",5);
        Product first = new Product("armor","play",new LinkedList<>(),7,9,a);
        Product second = new Product("sword","drama",new LinkedList<>(),5,5,a);
        Product third = new Product("gun","play",new LinkedList<>(),1,3,a);
        assertTrue(a!=null);
        a.addProduct(first);
        a.addProduct(second);
        a.addProduct(third);
        assertTrue(!s.filterByRating(a.getProducts(),4,8).contains(first));
        assertTrue(s.filterByRating(a.getProducts(),4,8).contains(second));
        assertTrue(!s.filterByRating(a.getProducts(),4,8).contains(third));
    }

    @Test
    public void filterByCategory() {
        StoreImp a = s.openStore("y","london",5);
        Product first = new Product("armor","play",new LinkedList<>(),7,9,a);
        Product second = new Product("sword","drama",new LinkedList<>(),5,5,a);
        Product third = new Product("gun","play",new LinkedList<>(),1,3,a);
        assertTrue(a!=null);
        a.addProduct(first);
        a.addProduct(second);
        a.addProduct(third);
        assertTrue(!s.filterByCategory(a.getProducts(),"drama").contains(first));
        assertTrue(s.filterByCategory(a.getProducts(),"drama").contains(second));
        assertTrue(!s.filterByCategory(a.getProducts(),"drama").contains(third));
    }

    @Test
    public void filterByStoreRating() {
        StoreImp a = s.openStore("t","london",5);
        Product first = new Product("armor","play",new LinkedList<>(),7,9,a);
        Product second = new Product("sword","drama",new LinkedList<>(),5,5,a);
        StoreImp b = s.openStore("u","london",1);
        Product third = new Product("gun","play",new LinkedList<>(),1,3,b);
        assertTrue(a!=null);
        a.addProduct(first);
        a.addProduct(second);
        b.addProduct(third);
        assertTrue(s.filterByStoreRating(a.getProducts(),3,7).contains(first));
        assertTrue(s.filterByStoreRating(a.getProducts(),3,7).contains(second));
        assertTrue(!s.filterByStoreRating(a.getProducts(),3,7).contains(third));
    }

    @Test
    public void logout(){
        int Id = 666;
        String pass = "666";
        assertTrue(s.register(Id,pass));
        assertTrue(s.login(Id,pass));
        assertTrue(s.logout(Id));
    }

    @Test
    public void logout2(){
        int Id = 777;
        String pass = "777";
        assertTrue(s.register(Id,pass));
        assertFalse(s.logout(Id));
    }
}