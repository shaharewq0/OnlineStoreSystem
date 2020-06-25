package tests.DALTests;

import DAL.AcquisitionPolicy_DA;
import Domain.Policies.Acquisitions.*;
import Domain.Store.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

public class AcquisitionPolicy_DATest {
    AcquisitionPolicy_DA da;
    AcquisitionPolicy ap;
    Acquisition a,b,c,d;
    Acquisition andAcq;
    Acquisition orAcq;
    Acquisition xorAcq;
    @Before
    public void setUp() throws Exception {
        da=new AcquisitionPolicy_DA();
        ap=new AcquisitionPolicy();

        a=new AcqMaxAmount("shahar",12);
        a.replaceProducts(Arrays.asList(new Product("shahar",new LinkedList<>(),new LinkedList<>(),1,1)));
        b=new AcqMinAmount("shahar",100);
        c=new AcqMaxAmount("shahar",12);
        d=new AcqMinAmount("shahar",100);
        ap.addAcquisitionPolicy(a);
        ap.addAcquisitionPolicy(b);
        orAcq=new OrAcq(Arrays.asList(a,b));
        xorAcq=new XorAcq(Arrays.asList(c,d));
        andAcq=new AndAcq(Arrays.asList(a,b));
        ap.addAcquisitionPolicy(andAcq);
    }

    @Test
    public void add(){
        da.add(ap);
        assertTrue(da.getAll().get(0).getAcquisitions().size()==3);
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {
    }
}