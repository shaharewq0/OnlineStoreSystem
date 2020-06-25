package tests.DALTests;

import DAL.Basket_DA;
import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.StoreImp;
import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import Domain.UserClasses.shoppingBasket;

import java.util.Arrays;
import java.util.LinkedList;

public class Basket_DATest {

    Basket_DA da;
    shoppingBasket basket;
    StoreImp storeImp;

    @Before
    public void setUp() throws Exception {
        da=new Basket_DA();
        storeImp=new StoreImp("shahar","ashdod",1);
        basket=new shoppingBasket(storeImp);
    }

    @Test
    public void update() {
        da.add(basket);

        Product_boundle product_boundle=new Product_boundle(new Product("shahar",new LinkedList<>(),new LinkedList<>(),1.2,1),311);
        basket.setItem_holder(Arrays.asList(product_boundle));
        da.update(basket);
        assertTrue(da.getAll().get(0).getProducts().get(0).getName().equals("shahar"));
    }

    @Test
    public void delete() {
        da.add(basket);
        assertTrue(da.getAll().size()==1);
        da.delete(basket);
        assertTrue(da.getAll().size()==0);
    }
}