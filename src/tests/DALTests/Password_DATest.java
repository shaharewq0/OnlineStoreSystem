package tests.DALTests;

import DAL.Password_DA;
import extornal.Security.Password;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Password_DATest {

    Password_DA da;
    Password pass;

    @Before
    public void setUp() throws Exception {
    da=new Password_DA();
    pass=new Password("shahar","hash");

    }

    @Test
    public void updatePassword() {
        da.add(pass);
        pass.setPasswordHash("hash2");
        da.update(pass);
        List<Password> temp = da.getAll();
        assertTrue(temp.get(temp.size()-1).getPasswordHash().equals(pass.getPasswordHash()));
    }

    @Test
    public void deletePassword() {
        da.add(pass);
        int oldsize= da.getAll().size();
        da.delete(pass);
        assertEquals(da.getAll().size(),oldsize-1);
    }

}