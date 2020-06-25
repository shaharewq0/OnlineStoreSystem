package DAL;

import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class Product_Details_DATest {

    Product_Details_DA da;
    ProductDetails pd;

    @Before
    public void setUp() throws Exception {
        da=new Product_Details_DA();
        pd= new ProductDetails("shahar", Arrays.asList("pdCategory"),new LinkedList<>(),"store",3,1.2);
    }

    @Test
    public void update() {
        da.add(pd);
        assertTrue(da.getAll().size()==1);

        //amount
        pd.setAmount(1);
        da.update(pd);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getAmount()==1);

        pd.setName("SSSSSS");
        da.update(pd);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getName().equals("SSSSSS"));

        pd.setPrice(100);
        da.update(pd);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).getPrice()==100);
    }

    @Test
    public void delete() {
        da.add(pd);
        assertTrue(da.getAll().size()==1);
        da.delete(pd);
        assertTrue(da.getAll().size()==0);
    }
}