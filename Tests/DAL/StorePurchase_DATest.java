package DAL;

import Domain.Store.StorePurchase;
import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StorePurchase_DATest {

    StorePurchase_DA da;
    StorePurchase sp;
    List<ProductDetails> list;


    @Before
    public void setUp() throws Exception {
        da=new StorePurchase_DA();
        list=new LinkedList<>();
        list.add(new ProductDetails("shahar",new LinkedList<>(),new LinkedList<>(),"shahar",1,3.3));
        list.add(new ProductDetails("shahar2",new LinkedList<>(),new LinkedList<>(),"shahar2",1,3.3));
        sp=new StorePurchase(list,"mystore",1.3);
    }

    @Test
    public void update() {
        da.add(sp);
        assertTrue(da.getAll().size()==1);

        sp.setPrice(100);
        da.update(sp);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getPrice()==100);

        sp.setStore("hello");
        da.update(sp);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getStore().equals("hello"));

        sp.setItems(new LinkedList<>());
        da.update(sp);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getItems().size()==0);


    }

    @Test
    public void delete() {
        da.add(sp);
        assertTrue(da.getAll().size()==1);
        da.delete(sp);
        assertTrue(da.getAll().size()==0);
    }
}