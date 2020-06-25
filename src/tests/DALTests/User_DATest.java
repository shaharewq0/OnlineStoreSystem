package tests.DALTests;

import DAL.User_DA;
import Domain.UserClasses.User;
import Domain.UserClasses.shoppingCart;
import Domain.store_System.Roles.Member;
import Domain.store_System.Roles.Registered;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class User_DATest {
    User_DA da;
    User user;

    @Before
    public void setUp() throws Exception {
        da=new User_DA();
        user=new User("address",123);
        user.setCart(new shoppingCart());
        user.setLast_store_looked_at("ok store");
        user.setLogInstanse(new Member(user));
        user.setProfile(new Registered("1"));
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
        da.add(user);
        assertTrue(da.getAll().size()==1);
        da.delete(user);
        assertTrue(da.getAll().size()==0);
    }
}