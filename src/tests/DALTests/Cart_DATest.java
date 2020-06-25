package tests.DALTests;

import DAL.Cart_DA;
import Domain.Store.Product_boundle;
import Domain.Store.StoreImp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import Domain.UserClasses.shoppingCart;
import Domain.UserClasses.shoppingBasket;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Cart_DATest {

    Cart_DA da;
    shoppingCart shoppingCart;
    shoppingBasket shoppingBasket;
    StoreImp storeImp;

    @Before
    public void setUp() throws Exception {
        da=new Cart_DA();
        shoppingCart=new shoppingCart();
        storeImp=new StoreImp("store",new LinkedHashSet<Product_boundle>(),"address",1);
        shoppingBasket=new shoppingBasket(storeImp);
    }

    @Test
    public void update() {
        da.add(shoppingCart);

        Map<String,shoppingBasket> map=new HashMap<>();
        map.put(shoppingBasket.getStore().getName(),shoppingBasket);
        shoppingCart.setBaskets(map);
        da.update(shoppingCart);
        assertTrue(da.getAll().get(0).getBaskets().get(0).getStore().getName().equals("store"));
    }

    @Test
    public void delete() {
        da.add(shoppingCart);
        assertTrue(da.getAll().size()==1);
        da.delete(shoppingCart);
        assertTrue(da.getAll().size()==0);
    }
}