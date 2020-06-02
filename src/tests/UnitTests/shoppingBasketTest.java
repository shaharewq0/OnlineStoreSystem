package tests.UnitTests;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import Domain.RedClasses.shoppingBasket;
import Domain.Store.MyPair;
import Domain.Store.Product;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import Domain.store_System.System;

import static org.junit.Assert.*;

public class shoppingBasketTest {

    @Test
    public void addProduct(){
        StoreImp s = System.getInstance().openStore("r","tel aviv",3);
        ProductDetails p = new ProductDetails("itemname", new LinkedList<>(), s.getName(), 4, 7.2);// new Product("qqq","fun",new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        shoppingBasket basket = new shoppingBasket(s);
        basket.addProduct(p.getName(),1);
        ProductDetails productInBasket=null;
        for(ProductDetails current :basket.getProducts()){
                productInBasket = current;
        }
        assertNotNull(productInBasket);
        assertEquals(1, productInBasket.getAmount());
    }

    @Test
    public void addProductTwice(){
        StoreImp s = System.getInstance().openStore("p","tel aviv",3);
        List<String> lst = new LinkedList<>();
        lst.add("fun");
        Product p = new Product("qqq",lst,new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        shoppingBasket basket = new shoppingBasket(s);
        basket.addProduct(p);
        basket.addProduct(p);
        ProductDetails productInBasket=null;
        for(ProductDetails current :basket.getProducts()){
                productInBasket = current;
        }
        assertNotNull(productInBasket);
        assertEquals(2, productInBasket.getAmount());
        assertEquals(1, basket.getProducts().size());
    }

    @Test
    public void removeProduct(){
        StoreImp s = System.getInstance().openStore("jj","tel aviv",3);
        List<String> lst = new LinkedList<>();
        lst.add("fun");
        Product p = new Product("qqq",lst,new LinkedList<>(),5.5,3,s);
        s.addProduct(p);
        shoppingBasket basket = new shoppingBasket(s);
        basket.addProduct(p);
        assertEquals(1, basket.removeProduct(p.getName(), 1));
        ProductDetails productInBasket=null;
        for(ProductDetails current :basket.getProducts()){
            productInBasket = current;
        }
        assertNull(productInBasket);
        assertEquals(0, basket.getProducts().size());
    }

}