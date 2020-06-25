package tests.DALTests;

import DAL.Store_Inventory_DA;
import Domain.Store.Product;
import Domain.Store.Product_boundle;
import Domain.Store.Store_Inventory;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Store_Inventory_DATest {

    Store_Inventory_DA da;
    Store_Inventory si;
    Product_boundle pb;
    Product p;

    @Before
    public void setUp() throws Exception {
        da=new Store_Inventory_DA();
        si=new Store_Inventory();
        p= new Product("apple",new LinkedList<>(),new LinkedList<>(),1.22,1);
        pb=new Product_boundle(p,100);
    }

    @Test
    public void update() {
        si.items.put(p.getName(),pb);
        da.add(si);

        p.setPrice(1000);
        pb.setItem(p);
        si.items.replace(p.getName(),pb);
        List<Store_Inventory> list=da.getAll();
        assertTrue(list.size()==1);
        da.update(si);
        list=da.getAll();
        assertTrue(list.size()==1);
        assertTrue(list.get(0).items.get(p.getName()).getItem().getPrice()==p.getPrice());


        //TODO add additional update checks
    }

    @Test
    public void delete() {
        da.add(si);
        assertTrue(da.getAll().size()==1);
        da.delete(si);
        assertTrue(da.getAll().size()==0);
    }
}