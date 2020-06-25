package tests.DALTests;

import DAL.Store_Purchase_History_DA;
import Domain.Store.StorePurchase;
import Domain.Store.Store_Purchase_History;
import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class Store_Purchase_History_DATest {

    Store_Purchase_History_DA da;
    Store_Purchase_History sph;
    StorePurchase sp;
    ProductDetails pd;

    @Before
    public void setUp() throws Exception {
    da=new Store_Purchase_History_DA();
    sph=new Store_Purchase_History();
    pd=new ProductDetails("shaharPD",new LinkedList<>(),new LinkedList<>(),"storename",1,1.2);
    sp=new StorePurchase(Arrays.asList(pd),"storename",1.2);
    sph.setPastPurchase(Arrays.asList(sp));
    }

    @Test
    public void update() {
        da.add(sph);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getPastPurchase().size()==1);
        sph.setPastPurchase(new LinkedList<>());
        da.update(sph);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getPastPurchase().size()==0);
        sph.setPastPurchase(Arrays.asList(sp));
        da.update(sph);
    }


    @Test
    public void delete() {
        da.add(sph);
        assertTrue(da.getAll().size()==1);
        da.delete(sph);
        assertTrue(da.getAll().size()==0);
    }
}