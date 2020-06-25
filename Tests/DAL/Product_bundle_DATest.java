package DAL;

import Domain.Store.Product;
import Domain.Store.Product_boundle;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Product_bundle_DATest {

    Product_bundle_DA da;
    Product_boundle pb;
    @Before
    public void setUp() throws Exception {
        da=new Product_bundle_DA();
        pb=new Product_boundle(new Product("shahar",new LinkedList<>(),new LinkedList<>(),4.3,1),10);
    }

    @Test
    public void update() {
        da.add(pb);

        //amount update check  da.delete(pb)
        pb.setAmount(100);
        da.update(pb);
        List<Product_boundle> list=da.getAll();
        assertTrue(list.size()==1);
        assertTrue(list.get(0).getAmount()==100);

        //label update check
        pb.setLabel("new label");
        da.update(pb);
        list=da.getAll();
        assertTrue(list.size()==1);
        assertTrue(list.get(0).getLabel().equals("new label"));

        //product update check
        Product p=new Product("new Product",new LinkedList<>(),new LinkedList<>(),1.23,3);
        pb.setItem(p);
        da.update(pb);
        list=da.getAll();
        assertTrue(list.size()==1);
        assertTrue(list.get(0).getItem().equals(p));
    }

    @Test
    public void delete() {
        da.add(pb);
        List<Product_boundle> list=da.getAll();
        assertTrue(list.size()==1);
//        da.delete(pb);
//        assertTrue(da.getAll().size()==0);
    }
}