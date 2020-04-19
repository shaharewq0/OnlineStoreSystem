package tests;

import Store.Store;
import Store.Product;
import Store.MyPair;
import Store.shoppingBasket;
import org.junit.Test;
import store_System.System;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class shoppingBasketTest {

    @Test
    public void addProduct(){
        Store s = System.getInstance().openStore("r","tel aviv",3);
        Product p = new Product("qqq","fun",new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        shoppingBasket basket = new shoppingBasket(s);
        basket.addProduct(p);
        MyPair<Product,Integer> productInBasket=null;
        for(MyPair<Product,Integer> current :basket.getProducts()){
            if(current.getKey()==p){
                productInBasket = current;
            }
        }
        assertTrue(productInBasket!= null);
        assertTrue(productInBasket.getValue()==1);
    }

    @Test
    public void addProductTwice(){
        Store s = System.getInstance().openStore("p","tel aviv",3);
        Product p = new Product("qqq","fun",new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        shoppingBasket basket = new shoppingBasket(s);
        basket.addProduct(p);
        basket.addProduct(p);
        MyPair<Product,Integer> productInBasket=null;
        for(MyPair<Product,Integer> current :basket.getProducts()){
            if(current.getKey()==p){
                productInBasket = current;
            }
        }
        assertTrue(productInBasket!= null);
        assertTrue(productInBasket.getValue()==2);
        assertTrue(basket.getProducts().size()==1);
    }

}