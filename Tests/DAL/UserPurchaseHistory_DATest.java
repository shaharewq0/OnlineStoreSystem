package DAL;

import Domain.Store.StorePurchase;
import Domain.UserClasses.UserPurchase;
import Domain.UserClasses.User_Purchase_History;
import Domain.info.ProductDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class UserPurchaseHistory_DATest {
    UserPurchaseHistory_DA da;
    User_Purchase_History uph;
    UserPurchase up;
    StorePurchase a;
    ProductDetails pd1,pd2;

    @Before
    public void setUp() throws Exception {
        da=new UserPurchaseHistory_DA();
        uph=new User_Purchase_History();
        up=new UserPurchase();
        pd1=new ProductDetails("pd1",new LinkedList<>(),new LinkedList<>(),"S1",1,1.2);
        pd2=new ProductDetails("pd2",new LinkedList<>(),new LinkedList<>(),"S1",11,1.21);
        a=new StorePurchase(Arrays.asList(pd1,pd2),"s1",12.32);
        up.setEachPurchase(Arrays.asList(a));
        uph.setHistory(Arrays.asList(up));
    }

    @Test
    public void update() {
        da.add(uph);
        uph.setHistory(new LinkedList<>());
        da.update(uph);
        assertTrue(da.getAll().get(0).history.size()==0);

        uph.setHistory(Arrays.asList(up));
        da.update(uph);
        assertTrue(da.getAll().get(0).history.size()==1);
    }

    @Test
    public void delete() {
        da.add(uph);
        assertTrue(da.getAll().size()==1);
        assertTrue(da.getAll().get(0).history.get(0).equals(up));
        da.delete(uph);
        assertTrue(da.getAll().size()==0);
    }
}