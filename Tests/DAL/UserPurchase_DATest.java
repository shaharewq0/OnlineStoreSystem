package DAL;

import Domain.Store.StorePurchase;
import Domain.UserClasses.UserPurchase;
import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class UserPurchase_DATest {

    UserPurchase_DA da;
    UserPurchase up;
    StorePurchase a;
    ProductDetails pd1,pd2;

    @Before
    public void setUp() throws Exception {
    da=new UserPurchase_DA();
    up=new UserPurchase();
    pd1=new ProductDetails("pd1",new LinkedList<>(),new LinkedList<>(),"S1",1,1.2);
    pd2=new ProductDetails("pd2",new LinkedList<>(),new LinkedList<>(),"S1",11,1.21);
    a=new StorePurchase(Arrays.asList(pd1,pd2),"s1",12.32);
    up.setEachPurchase(Arrays.asList(a));
    }

    @Test
    public void update() {
        da.add(up);
        assertTrue(da.getAll().size()==1);

        up.setTotalPrice(15);
        da.update(up);
        assertTrue(da.getAll().get(0).TotalPrice==15);

        up.setEachPurchase(new LinkedList<>());
        da.update(up);
        assertTrue(da.getAll().get(0).eachPurchase.size()==0);

        up.setEachPurchase(Arrays.asList(a));
        da.update(up);

    }

    @Test
    public void delete() {
        da.add(up);
        assertTrue(da.getAll().size()==1);
        da.delete(up);
        assertTrue(da.getAll().size()==0);
    }
}