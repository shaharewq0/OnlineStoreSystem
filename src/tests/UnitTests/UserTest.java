package tests.UnitTests;

import Store.StoreImp;
import Store.User;
import Store.Product;
import Store.MyPair;
import org.junit.Test;
import store_System.System;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    //register with new id and a password
    public void registerNewMember() {
        User testUser1 = new User("new york",123123);
        int newId = 4444;
        String pass = "pass3";
        assertTrue(testUser1.register(newId,pass));
    }

    @Test
    //register with new id and a password after other register
    public void registerOldMember() {
        User testUser2 = new User("new york",123123);
        int Id1 = 2222;
        String pass1 = "pass1";
        assertTrue(testUser2.register(Id1,pass1));
        assertFalse(testUser2.register(Id1,pass1));
        int Id = 3333;
        String pass2 = "pass2";
        assertFalse(testUser2.register(Id,pass2));
    }

    @Test
    //login with correct id and a password
    public void loginOldMember() {
        User testUser2 = new User("new york",123123);
        int Id1 = 11;
        String pass1 = "11";
        assertTrue(testUser2.register(Id1,pass1));
        assertTrue(testUser2.login(Id1,pass1));
    }

    @Test
    //login 2 times
    public void loginWrong() {
        User testUser2 = new User("new york",123123);
        int Id1 = 12;
        String pass1 = "12";
        assertTrue(testUser2.register(Id1,pass1));
        assertTrue(testUser2.login(Id1,pass1));
        User testUser1 = new User("new york",123123);
        int Id2 = 13;
        String pass2 = "13";
        assertTrue(testUser1.register(Id2,pass2));
        assertTrue(testUser1.login(Id2,pass2));
        assertFalse(testUser1.login(Id1,pass1));
    }

    @Test
    public void addProductToBasket(){
        StoreImp s = System.getInstance().openStore("x","tel aviv",3);
        Product p = new Product("qqq","fun",new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        User testUser = new User("new york",123123);
        assertTrue(testUser.saveProductInBasket("qqq","x"));
        assertTrue(testUser.getProductsInCart().size()==1);
        MyPair<Product,Integer> productInCart=null;
        for(MyPair<Product,Integer> current :testUser.getProductsInCart()){
            if(current.getKey()==p){
                productInCart = current;
            }
        }
        assertTrue(productInCart!=null);
        assertTrue(productInCart.getKey().getName().equals("qqq"));
        assertTrue(productInCart.getValue()==1);
    }

    @Test
    public void addWrongProduct(){
        StoreImp s = System.getInstance().openStore("lll","tel aviv",3);
        Product p = new Product("qqq","fun",new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        User testUser = new User("new york",123123);
        assertFalse(testUser.saveProductInBasket("qqq","xx"));
        assertFalse(testUser.saveProductInBasket("wrong","lll"));
    }

    @Test
    public void removeProductFromCart(){
        StoreImp s = System.getInstance().openStore("mmm","tel aviv",3);
        Product p = new Product("qqq","fun",new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        User testUser = new User("new york",123123);
        testUser.saveProductInBasket("qqq","mmm");
        assertTrue(testUser.deleteProductInBasket("qqq","mmm",1));
        MyPair<Product,Integer> productInCart=null;
        for(MyPair<Product,Integer> current :testUser.getProductsInCart()){
            if(current.getKey()==p){
                productInCart = current;
            }
        }
        assertTrue(productInCart==null);
        assertTrue(testUser.getProductsInCart().size()==0);
        testUser.saveProductInBasket("qqq","mmm");
        assertTrue(testUser.deleteProductInBasket("qqq","mmm",2));
    }

    @Test
    public void logout(){
        User testUser = new User("new york",123123);
        testUser.register(1234,"1234");
        testUser.login(1234,"1234");
        assertTrue(testUser.logout());
    }

    @Test
    public void logout2(){
        User testUser = new User("new york",123123);
        assertFalse(testUser.logout());
        testUser.register(1234,"1234");
        assertFalse(testUser.logout());
    }

}