package DAL;

import extornal.Security.Password;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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
        da.addPassword(pass);
        pass.setPasswordHash("hash2");
        da.updatePassword(pass);
        List<Password> temp = da.getAllPassword();
        assertTrue(temp.get(temp.size()-1).getPasswordHash().equals(pass.getPasswordHash()));
    }

    @Test
    public void deletePassword() {
        da.addPassword(pass);
        int oldsize= da.getAllPassword().size();
        da.deletePassword(pass);
        assertEquals(da.getAllPassword().size(),oldsize-1);
    }

}